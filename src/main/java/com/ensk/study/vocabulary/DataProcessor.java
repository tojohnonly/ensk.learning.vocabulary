package com.ensk.study.vocabulary;

import javax.swing.*;
import java.sql.*;

public class DataProcessor {


    private static Connection connection = null;
    private static Statement statement = null;
    private static WordEntity currentWord;
    private static Integer studyMode = 3;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\tojoh\\Desktop\\Vocabulary.db3");
            connection.setAutoCommit(true);
            System.out.println("Opened database successfully");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
        }
    }

    protected static void setMode(Integer mode) {
        studyMode = mode;
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

            ResultSet resultSet = statement.executeQuery("SELECT * FROM VOCABULARY ORDER BY RANDOM() LIMIT 0,1");
            while (resultSet.next()) {
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
            }
        } catch (SQLException e) {
            System.err.println("Get Next Word Error: " + e.getMessage());
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
            System.err.println("Get Next Word Error: " + e.getMessage());
        }
    }

    public static WordEntity getCurrentWord() {
        return currentWord;
    }


}