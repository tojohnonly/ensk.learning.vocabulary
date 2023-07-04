package com.ensk.study.vocabulary;

import javax.swing.UIManager;

public class Application {

    /**
     * Main Program
     * <p>
     *
     * @author tojohnonly
     * @date 2023/07/03 09:30
     * @version 1.0.0
     */
    public static void main(String[] args) throws Exception {
        String lookAndFeel =UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        FrameContainer.start();
    }

}