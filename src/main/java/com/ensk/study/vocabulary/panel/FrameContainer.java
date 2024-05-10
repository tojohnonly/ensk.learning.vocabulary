package com.ensk.study.vocabulary.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.ensk.study.vocabulary.config.EsvConfig;
import com.ensk.study.vocabulary.service.DataProcessor;
import com.ensk.study.vocabulary.ui.JAnimationLabel;
import com.ensk.study.vocabulary.ui.JRoundedButton;

public class FrameContainer {
    
    // Main Frame
    static final JFrame frame = new JFrame("Learning");
    // Assemble Mode Select Panel
    private static ModeSelectPanel modeSelectPanel;
    // Assemble Add Word Panel
    private static AddWordPanel addWordPanel;
    // Assemble Study Panel
    private static StudyPanel studyPanel;
    // Assemble Edit Word Panel
    private static EditWordPanel editWordPanel;



    public static void start() {
        // Set Main Window Size
        frame.setSize(460, 315);
        // Set Main Window Location
        // f.setLocation(200, 200);
        // Center the Main Window
        frame.setLocationRelativeTo(null);
        // Set Main Window Size Unchangeable
        frame.setResizable(false);
        // Set Default Close Operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set Background Color
        frame.getContentPane().setBackground(EsvConfig.bgColor);
        // Set App Icon
        URL iconURL = FrameContainer.class.getResource("/appicon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());

        // frame.add(studyPanel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DataProcessor.closeConnection();
            }
        });
        frame.setVisible(true);

        // Connect Database
        DataProcessor.connectDatabase();

        // Load First Panel
        modeSelectPanel = new ModeSelectPanel();
        addWordPanel = new AddWordPanel();
        studyPanel = new StudyPanel();
        editWordPanel = new EditWordPanel();
        frame.add(modeSelectPanel);
        frame.validate();
        frame.repaint();
    }



    public static void refresh() {
        frame.validate();
        frame.repaint();
    }



    public static void switchModeSelectToAddWord() {
        frame.remove(modeSelectPanel);
        frame.add(addWordPanel);
        frame.validate();
        frame.repaint();
    }



    public static void switchModeSelectToStudy(Integer mode) {
        DataProcessor.setMode(mode);
        frame.remove(modeSelectPanel);
        frame.add(studyPanel);
        studyPanel.preparePanel();
        frame.validate();
        frame.repaint();
    }



    public static void switchAddWordToModeSelect() {
        frame.remove(addWordPanel);
        frame.add(modeSelectPanel);
        modeSelectPanel.refreshModePanel();
        frame.validate();
        frame.repaint();
    }



    public static void switchStudyToModeSelect() {
        frame.remove(studyPanel);
        frame.add(modeSelectPanel);
        modeSelectPanel.refreshModePanel();
        frame.validate();
        frame.repaint();
    }



    public static void switchStudyToEdit() {
        frame.remove(studyPanel);
        frame.add(editWordPanel);
        editWordPanel.preparePanel();
        frame.validate();
        frame.repaint();
    }



    public static void switchEditToStudy(String word, String pronounce, String translation, String example) {
        studyPanel.fillPanel(word, pronounce, translation, example);
        frame.remove(editWordPanel);
        frame.add(studyPanel);
        frame.validate();
        frame.repaint();
    }



    public static void switchEditToStudy() {
        frame.remove(editWordPanel);
        frame.add(studyPanel);
        frame.validate();
        frame.repaint();
    }



    public static void noticeAndQuit(String message) {
        if (null != modeSelectPanel) {
            frame.remove(modeSelectPanel);
        }
        if (null != addWordPanel) {
            frame.remove(addWordPanel);
        }
        if (null != studyPanel) {
            frame.remove(studyPanel);
        }
        if (null != editWordPanel) {
            frame.remove(editWordPanel);
        }

        frame.setLayout(null);
        // Notice Label
        JAnimationLabel noticeLabel = new JAnimationLabel(message, 10);
        noticeLabel.setBounds(60, 30, 340, 150);
        noticeLabel.setFont(EsvConfig.translationFont);
        noticeLabel.setForeground(Color.RED);

        // OK Button
        JRoundedButton okBtn = new JRoundedButton("OK");
        okBtn.setBounds(150, 200, 140, 35);
        okBtn.setForeground(Color.RED);
        okBtn.setFont(EsvConfig.buttonFont);
        okBtn.setBackground(EsvConfig.buttonBgColor);
        okBtn.setBorderPainted(false);
        okBtn.setFocusPainted(false);
        okBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.closeConnection();
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!okBtn.isEnabled()) {
                    return;
                }
                okBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!okBtn.isEnabled()) {
                    return;
                }
                okBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        frame.add(noticeLabel);
        frame.add(okBtn);
        frame.validate();
        frame.repaint();
    }

}