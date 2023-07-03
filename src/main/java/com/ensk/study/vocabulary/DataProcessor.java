package com.ensk.study.vocabulary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.filechooser.FileSystemView;

public class DataProcessor {

    private static Connection connection = null;
    private static Statement statement = null;
    private static WordEntity currentWord;
    private static Integer studyMode = 3;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            // TODO - ENSK - 待处理 校验数据库文件是否存在
            connection = DriverManager.getConnection("jdbc:sqlite:" +  FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "\\VOCABULARY.db");
            connection.setAutoCommit(true);
            System.out.println("Open Database Successfully");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println("Open Database Error: " + e.getMessage());
            LogUtil.log("Open Database Error: " + e.getMessage());
            // TODO - ENSK - 待处理抽出来
            DataProcessor.closeConnection();
            LogUtil.closeLog();
            System.exit(0);
        }
    }

    protected static void closeConnection() {
        System.out.println("Close Database Connection");
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Close Database Connection Error: " + e.getMessage());
            LogUtil.log("Close Database Connection Error: " + e.getMessage());
            // TODO - ENSK - 待处理抽出来
            DataProcessor.closeConnection();
            LogUtil.closeLog();
            System.exit(0);
        }
    }

    protected static void setMode(Integer mode) {
        studyMode = mode;
    }

    protected static Boolean checkModeAvailable(Integer mode) {
        try {
            String sql = "";
            if (mode == 1) {
                sql = "SELECT * FROM VOCABULARY WHERE LEARN_SCORE < 0.3 ORDER BY RANDOM() LIMIT 0,1";
            } else if (mode == 2) {
                sql = "SELECT * FROM VOCABULARY WHERE LEARN_SCORE > 0.3 AND LEARN_SCORE < 0.8 ORDER BY RANDOM() LIMIT 0,1";
            } else if (mode == 3) {
                sql = "SELECT * FROM VOCABULARY ORDER BY RANDOM() LIMIT 0,1";
            }

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Get Next Word Error: " + e.getMessage());
            LogUtil.log("Get Next Word Error: " + e.getMessage());
            // TODO - ENSK - 待处理抽出来
            DataProcessor.closeConnection();
            LogUtil.closeLog();
            System.exit(0);
        }
        return true;
    }

    public static void nextWord() {
        try {
            String sql = "";
            if (studyMode == 1) {
                sql = "SELECT * FROM VOCABULARY WHERE LEARN_SCORE < 0.3 ORDER BY RANDOM() LIMIT 0,1";
            } else if (studyMode == 2) {
                sql = "SELECT * FROM VOCABULARY WHERE LEARN_SCORE > 0.3 AND LEARN_SCORE < 0.8 ORDER BY RANDOM() LIMIT 0,1";
            } else if (studyMode == 3) {
                sql = "SELECT * FROM VOCABULARY ORDER BY RANDOM() LIMIT 0,1";
            }

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                WordEntity word = new WordEntity();
                word.setId(resultSet.getInt("ID"));
                word.setWord(resultSet.getString("WORD"));
                word.setPronounce(resultSet.getString("PRONOUNCE"));
                word.setTranslation(resultSet.getString("TRANSLATION"));
                word.setExample(resultSet.getString("EXAMPLE"));
                word.setLearnTimes(resultSet.getInt("LEARN_TIMES"));
                word.setDnkTimes(resultSet.getInt("DNK_TIMES"));
                word.setHmTimes(resultSet.getInt("HM_TIMES"));
                word.setKimTimes(resultSet.getInt("KIM_TIMES"));
                word.setLearnScore(resultSet.getFloat("LEARN_SCORE"));
                resultSet.close();
                currentWord = word;
            } else {
                System.err.println("Get Next Word Error: No Word In Database");
                LogUtil.log("Get Next Word Error: No Word In Database");
                // TODO - ENSK - 待处理抽出来
                DataProcessor.closeConnection();
                LogUtil.closeLog();
                System.exit(0);
            }
        } catch (SQLException e) {
            System.err.println("Get Next Word Error: " + e.getMessage());
            LogUtil.log("Get Next Word Error: " + e.getMessage());
            // TODO - ENSK - 待处理抽出来
            DataProcessor.closeConnection();
            LogUtil.closeLog();
            System.exit(0);
        }
    }

    public static void upadteScore(Integer wordId, Integer mode) {
        String sqlUpdateTimes = "";
        String sqlUpdateScore = "UPDATE VOCABULARY SET LEARN_SCORE = IIF(LEARN_TIMES > 0, (DNK_TIMES * 0.1 + HM_TIMES * 0.5 + KIM_TIMES * 1) / LEARN_TIMES , 0) WHERE ID = " + wordId;
        if (mode == 1) {
            sqlUpdateTimes = "UPDATE VOCABULARY SET LEARN_TIMES = LEARN_TIMES + 1, DNK_TIMES = DNK_TIMES + 1 WHERE ID = " + wordId;
        } else if (mode == 2) {
            sqlUpdateTimes = "UPDATE VOCABULARY SET LEARN_TIMES = LEARN_TIMES + 1, HM_TIMES = HM_TIMES + 1 WHERE ID = " + wordId;
        } else if (mode == 3) {
            sqlUpdateTimes = "UPDATE VOCABULARY SET LEARN_TIMES = LEARN_TIMES + 1, KIM_TIMES = KIM_TIMES + 1 WHERE ID = " + wordId;
        }
        try {
            statement.executeUpdate(sqlUpdateTimes);
            statement.executeUpdate(sqlUpdateScore);
        } catch (SQLException e) {
            System.err.println("Upadte Score Error: " + e.getMessage());
            LogUtil.log("Upadte Score Error: " + e.getMessage());
            // TODO - ENSK - 待处理抽出来
            DataProcessor.closeConnection();
            LogUtil.closeLog();
            System.exit(0);
        }
    }

    public static WordEntity getCurrentWord() {
        return currentWord;
    }

    public static Boolean checkEqual(String a, String b) {
        if (null == a && null == b) {
            return true;
        } else if (null == a || null == b) {
            return false;
        } else {
            return a.equals(b);
        }
    }

    public static void updateCurrentWord(String word, String pronounce, String translation, String example) {
        StringBuilder sqlUpdateWord = new StringBuilder("UPDATE VOCABULARY SET ");
        Boolean needUpdate = false;
        if (!DataProcessor.checkEqual(word, DataProcessor.getCurrentWord().getWord())) {
            sqlUpdateWord.append("WORD = '" + word.replace("'", "''") + "', ");
            needUpdate = true;
        }
        if (!DataProcessor.checkEqual(pronounce, DataProcessor.getCurrentWord().getPronounce())) {
            if (null == example || example.equals("")) {
                sqlUpdateWord.append("PRONOUNCE = NULL, ");
            } else {
                sqlUpdateWord.append("PRONOUNCE = '" + pronounce.replace("'", "''") + "', ");
            }
            needUpdate = true;
        }
        if (!DataProcessor.checkEqual(translation, DataProcessor.getCurrentWord().getTranslation())) {
            sqlUpdateWord.append("TRANSLATION = '" + translation.replace("'", "''") + "', ");
            needUpdate = true;
        }
        if (!DataProcessor.checkEqual(example, DataProcessor.getCurrentWord().getExample())) {
            if (null == example || example.equals("")) {
                sqlUpdateWord.append("EXAMPLE = NULL, ");
            } else {
                sqlUpdateWord.append("EXAMPLE = '" + example.replace("'", "''") + "', ");
            }
            needUpdate = true;
        }
        if (needUpdate) {
            String sql = sqlUpdateWord.substring(0, sqlUpdateWord.length() - 2) + " WHERE ID = " + DataProcessor.getCurrentWord().getId();
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.err.println("Update Current Word Error: " + e.getMessage());
                LogUtil.log("Update Current Word Error: " + e.getMessage());
                // TODO - ENSK - 待处理抽出来
                DataProcessor.closeConnection();
                LogUtil.closeLog();
                System.exit(0);
            }
        }

    }

}