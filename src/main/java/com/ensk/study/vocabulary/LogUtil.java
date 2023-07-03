package com.ensk.study.vocabulary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LogUtil {

    private static File file;
    private static FileWriter fileWriter;
    private static PrintWriter printWriter;

    static {
        try {
            // 如果文件存在，则追加内容；如果文件不存在，则创建文件
            file = new File("runoob.txt");
            fileWriter = new FileWriter(file, true);
            printWriter = new PrintWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        try {
            printWriter.println((new Date()).toString() + " - " + message);
            printWriter.flush();
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeLog() {
        try {
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
