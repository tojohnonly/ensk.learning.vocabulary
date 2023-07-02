package com.ensk.study.vocabulary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameContainer {

    // 各种字体
    static Font wordFont = new Font("Microsoft YaHei UI", Font.PLAIN, 25);
    static Font pronounceFont = new Font(Font.SERIF, Font.PLAIN, 17);
    static Font translationFont = new Font("霞鹜文楷", Font.PLAIN, 19);
    static Font exampleFont = new Font("霞鹜文楷", Font.PLAIN, 15);
    static Font buttonFont = new Font("Microsoft YaHei UI", Font.PLAIN, 15);

    // 颜色
    static Color panelBgColor = new Color(65, 63, 62);
    static Color buttonBgColor = new Color(83, 81, 80);

    // 主Frame
    static final JFrame frame = new JFrame("Learning");
    // 组装模式面板
    static JPanel modePanel = assembleModePanel();
    // 组装学习面板
    static JPanel learningPanel = assembleLearningPanel();


    public static void start() {
        // 主窗体设置大小
        frame.setSize(460, 310);
        // 主窗体设置位置
        // f.setLocation(200, 200);
        // 窗口居中
        frame.setLocationRelativeTo(null);
        // 窗体大小不可变化
        frame.setResizable(false);
        // 关闭窗体的时候，退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置背景色
        frame.getContentPane().setBackground(panelBgColor);
        // Set App Icon
        ImageIcon imageIcon = new ImageIcon("D:\\Software\\Windows\\Themes\\Icons\\Pngs\\meistertask-task-management-2019-05-20.png");
        frame.setIconImage(imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));

        frame.add(modePanel);
        // frame.add(learningPanel);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DataProcessor.closeConnection();
            }
        });

        frame.setVisible(true);
    }

    public static JPanel assembleModePanel() {

        JPanel panel = new JPanel();
        // 组件设置为绝对定位
        panel.setLayout(null);
        // Background Color
        panel.setBackground(panelBgColor);

        // Summary
        JLabel summaryLabel = new JLabel("All Words: 5366, Learned: 2377");
        //JLabel summaryLabel = new JLabel("<html><body style=\"width:400px;text-align:center\">" + "All Words: 5366   Learned: 2377" + "<body></html>");
        summaryLabel.setBounds(80, 20, 250, 35);
        summaryLabel.setFont(buttonFont);
        summaryLabel.setForeground(Color.WHITE);

        // Start Learning New Words Button
        final JRoundedButton learningModeBtn = new JRoundedButton("Start Learning New Words");
        learningModeBtn.setBounds(80, 110, 215, 35);
        learningModeBtn.setForeground(Color.WHITE);
        learningModeBtn.setFont(buttonFont);
        learningModeBtn.setBackground(buttonBgColor);
        learningModeBtn.setBorder(new RoundBorder());
        learningModeBtn.setBorderPainted(false);
        learningModeBtn.setFocusPainted(false);

        // Review Learned Content Button
        final JRoundedButton reviewModeBtn = new JRoundedButton("Review Learned Content");
        reviewModeBtn.setBounds(80, 160, 200, 35);
        reviewModeBtn.setForeground(Color.WHITE);
        reviewModeBtn.setFont(buttonFont);
        reviewModeBtn.setBackground(buttonBgColor);
        reviewModeBtn.setBorder(new RoundBorder());
        reviewModeBtn.setBorderPainted(false);
        reviewModeBtn.setFocusPainted(false);

        // Mixed Mode Button
        final JRoundedButton mixedModeBtn = new JRoundedButton("Mixed Mode");
        mixedModeBtn.setBounds(80, 210, 120, 35);
        mixedModeBtn.setForeground(Color.WHITE);
        mixedModeBtn.setFont(buttonFont);
        mixedModeBtn.setBackground(buttonBgColor);
        mixedModeBtn.setBorder(new RoundBorder());
        mixedModeBtn.setBorderPainted(false);
        mixedModeBtn.setFocusPainted(false);

        // Add component to panel
        panel.add(summaryLabel);
        panel.add(learningModeBtn);
        panel.add(reviewModeBtn);
        panel.add(mixedModeBtn);

        // Register Mode Panel Click Event
        registerModePanelClickEvent(learningModeBtn, reviewModeBtn, mixedModeBtn);

        return panel;
    }

    public static JPanel assembleLearningPanel() {
        JPanel panel = new JPanel();
        // 组件设置为绝对定位
        panel.setLayout(null);
        // 设置背景色
        panel.setBackground(new Color(65, 63, 62));

        // word
        JAnimationLabel wordLabel = new JAnimationLabel("", 20);
        wordLabel.setBounds(30, 15, 400, 35);
        wordLabel.setFont(wordFont);
        wordLabel.setForeground(Color.WHITE);

        // score
        JAnimationLabel scoreLabel = new JAnimationLabel("", 10);
        scoreLabel.setBounds(30, 60, 40, 25);
        scoreLabel.setFont(pronounceFont);
        scoreLabel.setForeground(Color.WHITE);

        // pronounce
        JAnimationLabel pronounceLabel = new JAnimationLabel("", 20);
        pronounceLabel.setBounds(80, 60, 400, 25);
        pronounceLabel.setFont(pronounceFont);
        pronounceLabel.setForeground(Color.WHITE);

        // translation
        final JAnimationLabel translationLabel = new JAnimationLabel("", 15);
        translationLabel.setBounds(30, 95, 400, 50);
        translationLabel.setFont(translationFont);
        translationLabel.setForeground(Color.WHITE);

        // example
        final JAnimationLabel exampleLabel = new JAnimationLabel("", 8);
        exampleLabel.setBounds(30, 145, 400, 55);
        exampleLabel.setFont(exampleFont);
        exampleLabel.setForeground(Color.WHITE);

        // Don't Know Button
        final JRoundedButton dkBtn = new JRoundedButton("Don't Know");
        dkBtn.setBounds(30, 215, 110, 35);
        dkBtn.setForeground(Color.WHITE);
        dkBtn.setFont(buttonFont);
        dkBtn.setBackground(buttonBgColor);
        dkBtn.setBorder(new RoundBorder());
        dkBtn.setBorderPainted(false);
        dkBtn.setFocusPainted(false);

        // Hazy Memory Button
        final JRoundedButton hmBtn = new JRoundedButton("Hazy Memory");
        hmBtn.setBounds(155, 215, 125, 35);
        hmBtn.setForeground(Color.WHITE);
        hmBtn.setFont(buttonFont);
        hmBtn.setBackground(buttonBgColor);
        hmBtn.setBorder(new RoundBorder());
        hmBtn.setBorderPainted(false);
        hmBtn.setFocusPainted(false);

        // Keep in Mind Button
        final JRoundedButton kimBtn = new JRoundedButton("Keep in Mind");
        kimBtn.setBounds(295, 215, 125, 35);
        kimBtn.setForeground(Color.WHITE);
        kimBtn.setFont(buttonFont);
        kimBtn.setBackground(buttonBgColor);
        kimBtn.setBorder(new RoundBorder());
        kimBtn.setBorderPainted(false);
        kimBtn.setFocusPainted(false);

        // Keep in Mind Button
        final JRoundedButton nwBtn = new JRoundedButton("Next Word");
        nwBtn.setBounds(95, 215, 190, 35);
        nwBtn.setForeground(Color.WHITE);
        nwBtn.setFont(buttonFont);
        nwBtn.setBackground(buttonBgColor);
        nwBtn.setBorder(new RoundBorder());
        nwBtn.setBorderPainted(false);
        nwBtn.setFocusPainted(false);
        nwBtn.setVisible(false);

        // Keep in Mind Button
        final JRoundedButton btmBtn = new JRoundedButton("Mode");
        btmBtn.setBounds(290, 215, 60, 35);
        btmBtn.setForeground(Color.WHITE);
        btmBtn.setFont(buttonFont);
        btmBtn.setBackground(buttonBgColor);
        btmBtn.setBorder(new RoundBorder());
        btmBtn.setBorderPainted(false);
        btmBtn.setFocusPainted(false);
        btmBtn.setVisible(false);

        // Add component to panel
        panel.add(wordLabel);
        panel.add(scoreLabel);
        panel.add(pronounceLabel);
        panel.add(translationLabel);
        panel.add(exampleLabel);
        panel.add(dkBtn);
        panel.add(hmBtn);
        panel.add(kimBtn);
        panel.add(nwBtn);
        panel.add(btmBtn);

        // Get First Word
        DataProcessor.nextWord();
        wordLabel.setAnimationText(DataProcessor.getCurrentWord().getWord());
        scoreLabel.setAnimationText(String.format("[%.2f]", DataProcessor.getCurrentWord().getLearnScore()));
        pronounceLabel.setAnimationText(DataProcessor.getCurrentWord().getPronounce());
        translationLabel.setAnimationText("*****************");
        exampleLabel.setAnimationText("******************************");

        // Add Mouse Listener
        registerLearningPanelClickEvent(wordLabel, scoreLabel, pronounceLabel, translationLabel, exampleLabel, dkBtn, hmBtn, kimBtn, nwBtn, btmBtn);

        return panel;
    }

    public static void registerModePanelClickEvent(JRoundedButton learningModeBtn, JRoundedButton reviewModeBtn, JRoundedButton mixedModeBtn) {

        learningModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.setMode(1);
                frame.remove(modePanel);
                frame.add(learningPanel);
                frame.validate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                learningModeBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                learningModeBtn.setBackground(new Color(83, 81, 80));
            }
        });

        reviewModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.setMode(2);
                frame.remove(modePanel);
                frame.add(learningPanel);
                frame.validate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                reviewModeBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                reviewModeBtn.setBackground(new Color(83, 81, 80));
            }
        });

        mixedModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.setMode(3);
                frame.remove(modePanel);
                frame.add(learningPanel);
                frame.validate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mixedModeBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mixedModeBtn.setBackground(new Color(83, 81, 80));
            }
        });

    }

    public static void registerLearningPanelClickEvent(JAnimationLabel wordLabel, JAnimationLabel scoreLabel, JAnimationLabel pronounceLabel, JAnimationLabel translationLabel, JAnimationLabel exampleLabel,
                                            JButton dkBtn, JButton hmBtn, JButton kimBtn, JButton nwBtn, JButton btmBtn) {
        dkBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.upadteScore(DataProcessor.getCurrentWord().getId(), 1);
                translationLabel.setAnimationText(DataProcessor.getCurrentWord().getTranslation());
                exampleLabel.setAnimationText(DataProcessor.getCurrentWord().getExample());
                dkBtn.setVisible(false);
                hmBtn.setVisible(false);
                kimBtn.setVisible(false);
                nwBtn.setVisible(true);
                btmBtn.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dkBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dkBtn.setBackground(new Color(83, 81, 80));
            }
        });

        hmBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.upadteScore(DataProcessor.getCurrentWord().getId(), 2);
                translationLabel.setAnimationText(DataProcessor.getCurrentWord().getTranslation());
                exampleLabel.setAnimationText(DataProcessor.getCurrentWord().getExample());
                dkBtn.setVisible(false);
                hmBtn.setVisible(false);
                kimBtn.setVisible(false);
                nwBtn.setVisible(true);
                btmBtn.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hmBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hmBtn.setBackground(new Color(83, 81, 80));
            }
        });

        kimBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.upadteScore(DataProcessor.getCurrentWord().getId(), 3);
                translationLabel.setAnimationText(DataProcessor.getCurrentWord().getTranslation());
                exampleLabel.setAnimationText(DataProcessor.getCurrentWord().getExample());
                dkBtn.setVisible(false);
                hmBtn.setVisible(false);
                kimBtn.setVisible(false);
                nwBtn.setVisible(true);
                btmBtn.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                kimBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                kimBtn.setBackground(new Color(83, 81, 80));
            }
        });

        nwBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.nextWord();
                wordLabel.setAnimationText(DataProcessor.getCurrentWord().getWord());
                scoreLabel.setAnimationText(String.format("[%.2f]", DataProcessor.getCurrentWord().getLearnScore()));
                pronounceLabel.setAnimationText(DataProcessor.getCurrentWord().getPronounce());
                translationLabel.setAnimationText("*****************");
                exampleLabel.setAnimationText("******************************");
                dkBtn.setVisible(true);
                hmBtn.setVisible(true);
                kimBtn.setVisible(true);
                nwBtn.setVisible(false);
                btmBtn.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                nwBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nwBtn.setBackground(new Color(83, 81, 80));
            }
        });

        btmBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.remove(learningPanel);
                frame.add(modePanel);
                frame.validate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btmBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btmBtn.setBackground(new Color(83, 81, 80));
            }
        });



    }

}