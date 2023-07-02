package com.ensk.study.vocabulary;

import javax.swing.*;
import java.sql.*;

public class DataProcessor {


    private static Connection connection = null;
    private static Statement statement = null;
    private static WordEntity currentWord;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\tojoh\\Desktop\\Vocabulary.db3");
            connection.setAutoCommit(false);
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



    public static void nextWord() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM VOCABULARY ORDER BY RANDOM() LIMIT 0,1;");
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


    public static WordEntity getCurrentWord() {
        return currentWord;
    }

    public static void getNextWord1() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM VOCABULARY;");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID");
                String word = resultSet.getString("WORD");
                String pronounce = resultSet.getString("PRONOUNCE");
                String translation = resultSet.getString("TRANSLATION");
                String example = resultSet.getString("EXAMPLE");
                Integer learnTimes = resultSet.getInt("LEARN_TIMES");
                Integer dnkTimes = resultSet.getInt("DNK_TIMES");
                Integer hmTimes = resultSet.getInt("HM_TIMES");
                Integer kimTimes = resultSet.getInt("KIM_TIMES");
                float learnScore = resultSet.getFloat("LEARN_SCORE");
                System.out.println("ID = " + id);
                System.out.println("WORD = " + word);
                System.out.println("PRONOUNCE = " + pronounce);
                System.out.println("TRANSLATION = " + translation);
                System.out.println("EXAMPLE = " + example);
                System.out.println("LEARN_TIMES = " + learnTimes);
                System.out.println("DNK_TIMES = " + dnkTimes);
                System.out.println("HM_TIMES = " + hmTimes);
                System.out.println("KIM_TIMES = " + kimTimes);
                System.out.println("LEARN_SCORE = " + learnScore);
                System.out.println();
                resultSet.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}