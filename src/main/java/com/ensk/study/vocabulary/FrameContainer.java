package com.ensk.study.vocabulary;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

    // Summary
    private static JLabel summaryLabel;
    // Start Learning New Words Button
    private static JRoundedButton learningModeBtn;
    // Review Learned Content Button
    private static JRoundedButton reviewModeBtn;
    // Mixed Mode Button
    private static JRoundedButton mixedModeBtn;

    // word
    private static JAnimationLabel wordLabel;
    // score
    private static JAnimationLabel scoreLabel;
    // pronounce
    private static JAnimationLabel pronounceLabel;
    // translation
    private static JAnimationLabel translationLabel;
    // example
    private static JAnimationLabel exampleLabel;
    // Don't Know Button
    private static JRoundedButton dkBtn;
    // Hazy Memory Button
    private static JRoundedButton hmBtn;
    // Keep in Mind Button
    private static JRoundedButton kimBtn;
    // Next Word Button
    private static JRoundedButton nwBtn;
    // Edit Button
    private static JRoundedButton editBtn;
    // Back to Mode Select Button
    private static JRoundedButton btmBtn;

    // Word Edit Label
    private static JLabel wordEditLabel;
    // Word Text Field
    private static JTextField wordTextField;
    // Pronounce Edit Label
    private static JLabel pronounceEditLabel;
    // Pronounce Text Field
    private static JTextField pronounceTextField;
    // Translation Edit Label
    private static JLabel translationEditLabel;
    // Translation Text Field
    private static JTextField translationTextField;
    // Example Edit Label
    private static JLabel exampleEditLabel;
    // Example Text Field
    private static JTextField exampleTextField;
    // Edit Confirm Button
    private static JRoundedButton editCfmBtn;
    // Edit Cancel Button
    private static JRoundedButton editCxlBtn;

    // 组装模式面板
    static JPanel modePanel = assembleModePanel();
    // 组装学习面板
    static JPanel learningPanel = assembleLearningPanel();
    // 组装学习面板
    static JPanel editPanel = assembleEditPanel();

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
        summaryLabel = new JLabel("All Words: 5366, Learned: 2377");
        //JLabel summaryLabel = new JLabel("<html><body style=\"width:400px;text-align:center\">" + "All Words: 5366   Learned: 2377" + "<body></html>");
        summaryLabel.setBounds(80, 20, 250, 35);
        summaryLabel.setFont(buttonFont);
        summaryLabel.setForeground(Color.WHITE);

        // Start Learning New Words Button
        learningModeBtn = new JRoundedButton("Start Learning New Words");
        learningModeBtn.setBounds(80, 110, 215, 35);
        learningModeBtn.setForeground(Color.WHITE);
        learningModeBtn.setFont(buttonFont);
        learningModeBtn.setBackground(buttonBgColor);
        learningModeBtn.setBorder(new RoundBorder());
        learningModeBtn.setBorderPainted(false);
        learningModeBtn.setFocusPainted(false);

        // Review Learned Content Button
        reviewModeBtn = new JRoundedButton("Review Learned Content");
        reviewModeBtn.setBounds(80, 160, 200, 35);
        reviewModeBtn.setForeground(Color.WHITE);
        reviewModeBtn.setFont(buttonFont);
        reviewModeBtn.setBackground(buttonBgColor);
        reviewModeBtn.setBorder(new RoundBorder());
        reviewModeBtn.setBorderPainted(false);
        reviewModeBtn.setFocusPainted(false);

        // Mixed Mode Button
        mixedModeBtn = new JRoundedButton("Mixed Mode");
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
        registerModePanelClickEvent();

        return panel;
    }

    public static JPanel assembleLearningPanel() {
        JPanel panel = new JPanel();
        // 组件设置为绝对定位
        panel.setLayout(null);
        // 设置背景色
        panel.setBackground(new Color(65, 63, 62));

        // word
        wordLabel = new JAnimationLabel("", 20);
        wordLabel.setBounds(30, 15, 400, 35);
        wordLabel.setFont(wordFont);
        wordLabel.setForeground(Color.WHITE);

        // score
        scoreLabel = new JAnimationLabel("", 10);
        scoreLabel.setBounds(30, 60, 40, 25);
        scoreLabel.setFont(pronounceFont);
        scoreLabel.setForeground(Color.WHITE);

        // pronounce
        pronounceLabel = new JAnimationLabel("", 20);
        pronounceLabel.setBounds(80, 60, 400, 25);
        pronounceLabel.setFont(pronounceFont);
        pronounceLabel.setForeground(Color.WHITE);

        // translation
        translationLabel = new JAnimationLabel("", 15);
        translationLabel.setBounds(30, 95, 400, 50);
        translationLabel.setFont(translationFont);
        translationLabel.setForeground(Color.WHITE);

        // example
        exampleLabel = new JAnimationLabel("", 8);
        exampleLabel.setBounds(30, 145, 400, 55);
        exampleLabel.setFont(exampleFont);
        exampleLabel.setForeground(Color.WHITE);

        // Don't Know Button
        dkBtn = new JRoundedButton("Don't Know");
        dkBtn.setBounds(30, 215, 110, 35);
        dkBtn.setForeground(Color.WHITE);
        dkBtn.setFont(buttonFont);
        dkBtn.setBackground(buttonBgColor);
        dkBtn.setBorder(new RoundBorder());
        dkBtn.setBorderPainted(false);
        dkBtn.setFocusPainted(false);

        // Hazy Memory Button
        hmBtn = new JRoundedButton("Hazy Memory");
        hmBtn.setBounds(155, 215, 125, 35);
        hmBtn.setForeground(Color.WHITE);
        hmBtn.setFont(buttonFont);
        hmBtn.setBackground(buttonBgColor);
        hmBtn.setBorder(new RoundBorder());
        hmBtn.setBorderPainted(false);
        hmBtn.setFocusPainted(false);

        // Keep in Mind Button
        kimBtn = new JRoundedButton("Keep in Mind");
        kimBtn.setBounds(295, 215, 125, 35);
        kimBtn.setForeground(Color.WHITE);
        kimBtn.setFont(buttonFont);
        kimBtn.setBackground(buttonBgColor);
        kimBtn.setBorder(new RoundBorder());
        kimBtn.setBorderPainted(false);
        kimBtn.setFocusPainted(false);

        // Next Word Button
        nwBtn = new JRoundedButton("Next Word");
        nwBtn.setBounds(60, 215, 200, 35);
        nwBtn.setForeground(Color.WHITE);
        nwBtn.setFont(buttonFont);
        nwBtn.setBackground(buttonBgColor);
        nwBtn.setBorder(new RoundBorder());
        nwBtn.setBorderPainted(false);
        nwBtn.setFocusPainted(false);
        nwBtn.setVisible(false);

        // Edit Button
        editBtn = new JRoundedButton("Edit");
        editBtn.setBounds(270, 215, 50, 35);
        editBtn.setForeground(Color.WHITE);
        editBtn.setFont(buttonFont);
        editBtn.setBackground(buttonBgColor);
        editBtn.setBorder(new RoundBorder());
        editBtn.setBorderPainted(false);
        editBtn.setFocusPainted(false);
        editBtn.setVisible(false);

        // Back to Mode Select Button
        btmBtn = new JRoundedButton("Mode");
        btmBtn.setBounds(330, 215, 60, 35);
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
        panel.add(editBtn);
        panel.add(btmBtn);

        // Add Mouse Listener
        registerLearningPanelClickEvent();

        return panel;
    }

    public static JPanel assembleEditPanel() {
        JPanel panel = new JPanel();
        // 组件设置为绝对定位
        panel.setLayout(null);
        // 设置背景色
        panel.setBackground(new Color(65, 63, 62));





        // Word Edit Label
        wordEditLabel = new JLabel("Word:");
        wordEditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        wordEditLabel.setBounds(40, 30, 90, 30);
        wordEditLabel.setFont(buttonFont);
        wordEditLabel.setForeground(Color.WHITE);

        // Pronounce Edit Label
        pronounceEditLabel = new JLabel("Pronounce:");
        pronounceEditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pronounceEditLabel.setBounds(40, 70, 90, 30);
        pronounceEditLabel.setFont(buttonFont);
        pronounceEditLabel.setForeground(Color.WHITE);

        // Translation Edit Label
        translationEditLabel = new JLabel("Translation:");
        translationEditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        translationEditLabel.setBounds(40, 110, 90, 30);
        translationEditLabel.setFont(buttonFont);
        translationEditLabel.setForeground(Color.WHITE);

        // Example Edit Label
        exampleEditLabel = new JLabel("Example:");
        exampleEditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        exampleEditLabel.setBounds(40, 150, 90, 30);
        exampleEditLabel.setFont(buttonFont);
        exampleEditLabel.setForeground(Color.WHITE);

        // Word Text Field
        wordTextField = new JTextField();
        wordTextField.setBounds(140, 30, 230, 30);
        wordTextField.setFont(buttonFont);
        //wordTextField.setBorder(new LineBorder(new Color(100,100,100), 0, true));
        wordTextField.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));

        wordTextField.setForeground(Color.WHITE);
        wordTextField.setBackground(buttonBgColor);

        // Pronounce Text Field
        pronounceTextField = new JTextField();
        pronounceTextField.setBounds(140, 70, 230, 30);
        pronounceTextField.setFont(pronounceFont);
        pronounceTextField.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        pronounceTextField.setForeground(Color.WHITE);
        pronounceTextField.setBackground(buttonBgColor);

        // Translation Text Field
        translationTextField = new JTextField();
        translationTextField.setBounds(140, 110, 230, 30);
        translationTextField.setFont(buttonFont);
        translationTextField.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        translationTextField.setForeground(Color.WHITE);
        translationTextField.setBackground(buttonBgColor);

        // Example Text Field
        exampleTextField = new JTextField();
        exampleTextField.setBounds(140, 150, 230, 30);
        exampleTextField.setFont(buttonFont);
        exampleTextField.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        exampleTextField.setForeground(Color.WHITE);
        exampleTextField.setBackground(buttonBgColor);

        // Edit Confirm Button
        editCfmBtn = new JRoundedButton("Confirm");
        editCfmBtn.setBounds(100, 215, 100, 35);
        editCfmBtn.setForeground(Color.WHITE);
        editCfmBtn.setFont(buttonFont);
        editCfmBtn.setBackground(buttonBgColor);
        editCfmBtn.setBorder(new RoundBorder());
        editCfmBtn.setBorderPainted(false);
        editCfmBtn.setFocusPainted(false);

        // Edit Cancel Button
        editCxlBtn = new JRoundedButton("Cancel");
        editCxlBtn.setBounds(240, 215, 100, 35);
        editCxlBtn.setForeground(Color.WHITE);
        editCxlBtn.setFont(buttonFont);
        editCxlBtn.setBackground(buttonBgColor);
        editCxlBtn.setBorder(new RoundBorder());
        editCxlBtn.setBorderPainted(false);
        editCxlBtn.setFocusPainted(false);

        // Add component to panel
        panel.add(wordEditLabel);
        panel.add(pronounceEditLabel);
        panel.add(translationEditLabel);
        panel.add(exampleEditLabel);
        panel.add(wordTextField);
        panel.add(pronounceTextField);
        panel.add(translationTextField);
        panel.add(exampleTextField);
        panel.add(editCfmBtn);
        panel.add(editCxlBtn);

        // Add Mouse Listener
        registerEditPanelClickEvent();

        return panel;
    }

    /**
     * Register Mode Panel Click Event
     * <p>
     *
     * @author tojohnonly
     * @date 2023/07/02 12:51
     * @version 1.0.0
     */
    public static void registerModePanelClickEvent() {

        learningModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.setMode(1);
                frame.remove(modePanel);
                frame.add(learningPanel);

                dkBtn.setVisible(true);
                hmBtn.setVisible(true);
                kimBtn.setVisible(true);
                nwBtn.setVisible(false);
                editBtn.setVisible(false);
                btmBtn.setVisible(false);

                // Get First Word
                DataProcessor.nextWord();
                wordLabel.setAnimationText(DataProcessor.getCurrentWord().getWord());
                scoreLabel.setAnimationText(String.format("[%.2f]", DataProcessor.getCurrentWord().getLearnScore()));
                pronounceLabel.setAnimationText(DataProcessor.getCurrentWord().getPronounce());
                translationLabel.setAnimationText("*****************");
                exampleLabel.setAnimationText("******************************");

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

                dkBtn.setVisible(true);
                hmBtn.setVisible(true);
                kimBtn.setVisible(true);
                nwBtn.setVisible(false);
                editBtn.setVisible(false);
                btmBtn.setVisible(false);

                // Get First Word
                DataProcessor.nextWord();
                wordLabel.setAnimationText(DataProcessor.getCurrentWord().getWord());
                scoreLabel.setAnimationText(String.format("[%.2f]", DataProcessor.getCurrentWord().getLearnScore()));
                pronounceLabel.setAnimationText(DataProcessor.getCurrentWord().getPronounce());
                translationLabel.setAnimationText("*****************");
                exampleLabel.setAnimationText("******************************");

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

                dkBtn.setVisible(true);
                hmBtn.setVisible(true);
                kimBtn.setVisible(true);
                nwBtn.setVisible(false);
                editBtn.setVisible(false);
                btmBtn.setVisible(false);

                // Get First Word
                DataProcessor.nextWord();
                wordLabel.setAnimationText(DataProcessor.getCurrentWord().getWord());
                scoreLabel.setAnimationText(String.format("[%.2f]", DataProcessor.getCurrentWord().getLearnScore()));
                pronounceLabel.setAnimationText(DataProcessor.getCurrentWord().getPronounce());
                translationLabel.setAnimationText("*****************");
                exampleLabel.setAnimationText("******************************");

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

    /**
     * Register Learning Panel Click Event
     * <p>
     *
     * @author tojohnonly
     * @date 2023/07/02 10:23
     * @version 1.0.0
     */
    public static void registerLearningPanelClickEvent() {
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
                editBtn.setVisible(true);
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
                editBtn.setVisible(true);
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
                editBtn.setVisible(true);
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
                editBtn.setVisible(false);
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

        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.remove(learningPanel);
                frame.add(editPanel);

                wordTextField.setText(DataProcessor.getCurrentWord().getWord());
                pronounceTextField.setText(DataProcessor.getCurrentWord().getPronounce());
                translationTextField.setText(DataProcessor.getCurrentWord().getTranslation());
                translationTextField.setCaretPosition(0);
                exampleTextField.setText(DataProcessor.getCurrentWord().getExample());
                exampleTextField.setCaretPosition(0);

                frame.validate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editBtn.setBackground(new Color(83, 81, 80));
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

    /**
     * Register Edit Panel Click Event
     * <p>
     *
     * @author tojohnonly
     * @date 2023/07/02 22:15
     * @version 1.0.0
     */
    public static void registerEditPanelClickEvent() {
        editCfmBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                // TODO : registerEditPanelClickEvent
                String word = wordTextField.getText();
                String pronounce = pronounceTextField.getText();
                String translation = translationTextField.getText();
                String example = exampleTextField.getText();

                if (DataProcessor.checkEqual(word, DataProcessor.getCurrentWord().getWord())) {

                }

                JOptionPane.showMessageDialog(editPanel, word, "Warning", JOptionPane.WARNING_MESSAGE);

                frame.remove(editPanel);
                frame.add(learningPanel);
                frame.validate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editCfmBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editCfmBtn.setBackground(new Color(83, 81, 80));
            }
        });

        editCxlBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.remove(editPanel);
                frame.add(learningPanel);
                frame.validate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editCxlBtn.setBackground(new Color(98, 96, 95));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editCxlBtn.setBackground(new Color(83, 81, 80));
            }
        });
    }

}