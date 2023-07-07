package com.ensk.study.vocabulary;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProcessor {

    private static Connection connection = null;
    private static Statement statement = null;
    private static WordEntity currentWord;
    private static Integer studyMode = 3;

    protected static void connectDatabase() {
        // Get Database File Path
        String dbPath = System.getProperty("bookpath");
        if (null == dbPath || dbPath.equals("")) {
            File userDir = new File(System.getProperty("user.dir"));
            File[] dbs = userDir.listFiles(userDirFile -> {
                if (userDirFile.isFile() && userDirFile.getName().endsWith(".db")) {
                    return true;
                } else {
                    return false;
                }
            });
            if (dbs.length <= 0) {
                throw new RuntimeException("No Database File In: " + System.getProperty("user.dir").replace("\\", " -> "));
            }
            dbPath = dbs[0].getAbsolutePath();
        }
        // Check Database File Exists
        File file = new File(dbPath);
        if (!file.exists()) {
            System.err.println("Connect to " + dbPath + ", Database File Not Exists");
            throw new RuntimeException(
                "Connect to \"" + dbPath.replace("\\", " -> ") + "\" Failed, Database File Not Exists");
        }
        try {
            // Connect Database
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            connection.setAutoCommit(true);
            System.out.println("Open Database (" + dbPath + ") Successfully");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println("Open Database Error: " + e.getMessage());
            throw new RuntimeException("Open Database Error: " + e.getMessage());
        }
    }

    protected static void switchDatabase(String dbPath) {
        try {
            // Close Current Connection
            if (null != statement) {
                statement.close();
            }
            if (null != connection) {
                connection.close();
            }
            System.out.println("Close Current Database Connection Successfully");

            // Check Database File Exists
            File file = new File(dbPath);
            if (!file.exists()) {
                System.err.println("Connect to " + dbPath + ", Database File Not Exists");
                throw new RuntimeException("Connect to \"" + dbPath.replace("\\", " -> ") + "\" Failed, Database File Not Exists");
            }

            // Connect Database
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            connection.setAutoCommit(true);
            System.out.println("Open New Database Successfully");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println("Switch Database Error: " + e.getMessage());
            throw new RuntimeException("Switch Database Error: " + e.getMessage());
        }
    }

    protected static void closeConnection() {
        try {
            if (null != statement) {
                statement.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Close Database Connection Error: " + e.getMessage());
        }
        System.out.println("Close Database Connection Successfully");
    }

    protected static void setMode(Integer mode) {
        studyMode = mode;
    }

    protected static Boolean checkModeAvailable(Integer mode) {
        try {
            String sql = "";
            if (mode == 1) {
                sql = "SELECT * FROM VOCABULARY WHERE LEARN_SCORE <= 0 LIMIT 0,1";
            } else if (mode == 2) {
                sql = "SELECT * FROM VOCABULARY WHERE LEARN_SCORE > 0 AND LEARN_SCORE < 0.8 LIMIT 0,1";
            } else if (mode == 3) {
                sql = "SELECT * FROM VOCABULARY LIMIT 0,1";
            }

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Check Mode Available Error: " + e.getMessage());
            throw new RuntimeException("Check Mode Available Error: " + e.getMessage());
        }
    }

    public static void nextWord() {
        try {
            String sql = "";
            if (studyMode == 1) {
                sql = "SELECT * FROM VOCABULARY WHERE LEARN_SCORE <= 0 ORDER BY RANDOM() LIMIT 0,1";
            } else if (studyMode == 2) {
                sql = "SELECT * FROM VOCABULARY WHERE LEARN_SCORE > 0 AND LEARN_SCORE < 0.7 ORDER BY RANDOM() LIMIT 0,1";
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
                System.err.println("Get Next Word Error, No Eligible Word of This Mode In Database, Try a Different Study Mode");
                throw new RuntimeException("Get Next Word Error, No Eligible Word of This Mode In Database, Try a Different Study Mode");
            }
        } catch (SQLException e) {
            System.err.println("Get Next Word Error: " + e.getMessage());
            throw new RuntimeException("Get Next Word Error: " + e.getMessage());
        }
    }

    public static WordEntity queryWord(String wordReq) {
        try {
            String sql = "SELECT * FROM VOCABULARY WHERE WORD = '" + wordReq + "'";
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
                return word;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Get Next Word Error: " + e.getMessage());
            throw new RuntimeException("Get Next Word Error: " + e.getMessage());
        }
    }


    public static void upadteScore(Integer wordId, Integer mode) {
        String sqlUpdateTimes = "";
        String sqlUpdateScore =
            "UPDATE VOCABULARY SET LEARN_SCORE = IIF(LEARN_TIMES > 0, (DNK_TIMES * 0.1 + HM_TIMES * 0.5 + KIM_TIMES * 1) / LEARN_TIMES , 0) WHERE ID = " + wordId;
        if (mode == 1) {
            sqlUpdateTimes =
                "UPDATE VOCABULARY SET LEARN_TIMES = LEARN_TIMES + 1, DNK_TIMES = DNK_TIMES + 1 WHERE ID = " + wordId;
        } else if (mode == 2) {
            sqlUpdateTimes =
                "UPDATE VOCABULARY SET LEARN_TIMES = LEARN_TIMES + 1, HM_TIMES = HM_TIMES + 1 WHERE ID = " + wordId;
        } else if (mode == 3) {
            sqlUpdateTimes =
                "UPDATE VOCABULARY SET LEARN_TIMES = LEARN_TIMES + 1, KIM_TIMES = KIM_TIMES + 1 WHERE ID = " + wordId;
        }
        try {
            statement.executeUpdate(sqlUpdateTimes);
            statement.executeUpdate(sqlUpdateScore);
        } catch (SQLException e) {
            System.err.println("Upadte Score Error: " + e.getMessage());
            throw new RuntimeException("Upadte Score Error: " + e.getMessage());
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

    public static Integer getWordCount(Integer studyStage) {
        try {
            String sql = "";
            if (studyStage == 0) {
                sql = "SELECT COUNT(*) AS WORD_COUNT FROM VOCABULARY";
            } else if (studyStage == 1) {
                sql = "SELECT COUNT(*) AS WORD_COUNT FROM VOCABULARY WHERE LEARN_SCORE <= 0";
            } else if (studyStage == 2) {
                sql = "SELECT COUNT(*) AS WORD_COUNT FROM VOCABULARY WHERE LEARN_SCORE > 0 AND LEARN_SCORE <= 0.3";
            } else if (studyStage == 3) {
                sql = "SELECT COUNT(*) AS WORD_COUNT FROM VOCABULARY WHERE LEARN_SCORE > 0.3 AND LEARN_SCORE <= 0.8";
            } else if (studyStage == 4) {
                sql = "SELECT COUNT(*) AS WORD_COUNT FROM VOCABULARY WHERE LEARN_SCORE > 0.8";
            }

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Integer wordCount = resultSet.getInt("WORD_COUNT");
                resultSet.close();
                return wordCount;
            } else {
                System.err.println("Get Word Count Error, No Eligible Word of This Mode In Database");
                throw new RuntimeException("Get Word Count Error, No Eligible Word of This Mode In Database");
            }
        } catch (SQLException e) {
            System.err.println("Get Word Count Error: " + e.getMessage());
            throw new RuntimeException("Get Word Count Error: " + e.getMessage());
        }
    }

    public static void addWord(String word, String pronounce, String translation, String example) {
        String sqlAddWord = "INSERT INTO VOCABULARY (WORD, PRONOUNCE, TRANSLATION, EXAMPLE) VALUES ('" + word + "', "
            + ((null == pronounce || pronounce.equals("")) ? "NULL, '" : ("'" + pronounce + "', '")) + translation
            + "', " + ((null == example || example.equals("")) ? "NULL);" : ("'" + example + "');"));
            try {
                statement.executeUpdate(sqlAddWord);
            } catch (SQLException e) {
                System.err.println("Add Word Error: " + e.getMessage());
                throw new RuntimeException("Add Word Error: " + e.getMessage());
            }
    }

    public static void updateCurrentWord(String word, String pronounce, String translation, String example) {
        StringBuilder sqlUpdateWord = new StringBuilder("UPDATE VOCABULARY SET ");
        Boolean needUpdate = false;
        if (!DataProcessor.checkEqual(word, DataProcessor.getCurrentWord().getWord())) {
            DataProcessor.getCurrentWord().setWord(word);
            sqlUpdateWord.append("WORD = '" + word.replace("'", "''") + "', ");
            needUpdate = true;
        }
        if (!DataProcessor.checkEqual(pronounce, DataProcessor.getCurrentWord().getPronounce())) {
            DataProcessor.getCurrentWord().setPronounce(pronounce);
            if (null == example || example.equals("")) {
                sqlUpdateWord.append("PRONOUNCE = NULL, ");
            } else {
                sqlUpdateWord.append("PRONOUNCE = '" + pronounce.replace("'", "''") + "', ");
            }
            needUpdate = true;
        }
        if (!DataProcessor.checkEqual(translation, DataProcessor.getCurrentWord().getTranslation())) {
            DataProcessor.getCurrentWord().setTranslation(translation);
            sqlUpdateWord.append("TRANSLATION = '" + translation.replace("'", "''") + "', ");
            needUpdate = true;
        }
        if (!DataProcessor.checkEqual(example, DataProcessor.getCurrentWord().getExample())) {
            DataProcessor.getCurrentWord().setExample(example);
            if (null == example || example.equals("")) {
                sqlUpdateWord.append("EXAMPLE = NULL, ");
            } else {
                sqlUpdateWord.append("EXAMPLE = '" + example.replace("'", "''") + "', ");
            }
            needUpdate = true;
        }
        if (needUpdate) {
            String sql = sqlUpdateWord.substring(0, sqlUpdateWord.length() - 2) + " WHERE ID = "
                + DataProcessor.getCurrentWord().getId();
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.err.println("Update Current Word Error: " + e.getMessage());
                throw new RuntimeException("Update Current Word Error: " + e.getMessage());
            }
        }
    }

}