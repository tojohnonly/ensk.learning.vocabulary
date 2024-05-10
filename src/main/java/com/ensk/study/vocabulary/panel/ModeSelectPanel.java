package com.ensk.study.vocabulary.panel;

import com.ensk.study.vocabulary.config.EsvConfig;
import com.ensk.study.vocabulary.service.DataProcessor;
import com.ensk.study.vocabulary.ui.JAnimationLabel;
import com.ensk.study.vocabulary.ui.JRoundedButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class ModeSelectPanel extends JPanel {

    private JAnimationLabel summaryLabel;
    // Add Word Button
    private JRoundedButton addWordBtn;
    // Start Learning New Words Button
    private JRoundedButton learningModeBtn;
    // Choose Book Button
    private JRoundedButton chooseBookBtn;
    // Review Learned Content Button
    private JRoundedButton reviewModeBtn;
    // Mixed Mode Button
    private JRoundedButton mixedModeBtn;
    // Recent 100 Mode Button
    private JRoundedButton recent100ModeBtn;

    public ModeSelectPanel() {
        super();
        // Component Absolute Positioning
        this.setLayout(null);
        // Background Color
        this.setBackground(EsvConfig.bgColor);
        Integer allWordCount = DataProcessor.getWordCount(0);
        Integer unstudiedWordCount = DataProcessor.getWordCount(1);
        Integer masteredWordCount = DataProcessor.getWordCount(4);

        // Summary
        summaryLabel = new JAnimationLabel("All Words:&nbsp;&nbsp;&nbsp;" + allWordCount
                + "<br/>Unstudied:&nbsp;&nbsp;" + unstudiedWordCount + "<br/>Mastered:&nbsp;&nbsp;&nbsp;" + masteredWordCount, 10);
        // JLabel summaryLabel = new JLabel("<html><body style=\"width:400px;text-align:center\">" + "All Words: 5366
        // Learned: 2377" + "<body></html>");
        summaryLabel.setBounds(80, 20, 150, 60);
        summaryLabel.setFont(EsvConfig.buttonFont);
        summaryLabel.setForeground(Color.WHITE);

        // Start Learning New Words Button
        addWordBtn = new JRoundedButton("Add Word");
        addWordBtn.setBounds(250, 20, 115, 60);
        addWordBtn.setForeground(Color.WHITE);
        addWordBtn.setFont(EsvConfig.buttonFont);
        addWordBtn.setBackground(EsvConfig.buttonBgColor);
        addWordBtn.setBorderPainted(false);
        addWordBtn.setFocusPainted(false);

        // Start Learning New Words Button
        learningModeBtn = new JRoundedButton("Start Learning New Words");
        learningModeBtn.setBounds(80, 110, 215, 35);
        learningModeBtn.setForeground(Color.WHITE);
        learningModeBtn.setFont(EsvConfig.buttonFont);
        learningModeBtn.setBackground(EsvConfig.buttonBgColor);
        learningModeBtn.setBorderPainted(false);
        learningModeBtn.setFocusPainted(false);

        // Choose Book Button
        chooseBookBtn = new JRoundedButton("Books");
        chooseBookBtn.setBounds(305, 110, 60, 35);
        chooseBookBtn.setForeground(Color.WHITE);
        chooseBookBtn.setFont(EsvConfig.buttonFont);
        chooseBookBtn.setBackground(new Color(232, 151, 34));
        chooseBookBtn.setBorderPainted(false);
        chooseBookBtn.setFocusPainted(false);

        // Review Learned Content Button
        reviewModeBtn = new JRoundedButton("Review Learned Content");
        reviewModeBtn.setBounds(80, 160, 285, 35);
        reviewModeBtn.setForeground(Color.WHITE);
        reviewModeBtn.setFont(EsvConfig.buttonFont);
        reviewModeBtn.setBackground(EsvConfig.buttonBgColor);
        reviewModeBtn.setBorderPainted(false);
        reviewModeBtn.setFocusPainted(false);

        // Mixed Mode Button
        mixedModeBtn = new JRoundedButton("Mixed Mode");
        mixedModeBtn.setBounds(80, 210, 145, 35);
        mixedModeBtn.setForeground(Color.WHITE);
        mixedModeBtn.setFont(EsvConfig.buttonFont);
        mixedModeBtn.setBackground(EsvConfig.buttonBgColor);
        mixedModeBtn.setBorderPainted(false);
        mixedModeBtn.setFocusPainted(false);

        // Recent 100 Mode Button
        recent100ModeBtn = new JRoundedButton("Recent 100");
        recent100ModeBtn.setBounds(235, 210, 130, 35);
        recent100ModeBtn.setForeground(Color.WHITE);
        recent100ModeBtn.setFont(EsvConfig.buttonFont);
        recent100ModeBtn.setBackground(EsvConfig.buttonBgColor);
        recent100ModeBtn.setBorderPainted(false);
        recent100ModeBtn.setFocusPainted(false);

        // Add component to panel
        this.add(summaryLabel);
        this.add(addWordBtn);
        this.add(chooseBookBtn);
        this.add(learningModeBtn);
        this.add(reviewModeBtn);
        this.add(mixedModeBtn);
        this.add(recent100ModeBtn);

        // Register Mode Panel Click Event
        this.registerModePanelClickEvent();

        // Check Mode Available
        if (!DataProcessor.checkModeAvailable(1)) {
            learningModeBtn.setEnabled(false);
        }
        if (!DataProcessor.checkModeAvailable(2)) {
            reviewModeBtn.setEnabled(false);
        }
        if (!DataProcessor.checkModeAvailable(3)) {
            mixedModeBtn.setEnabled(false);
        }
        if (!DataProcessor.checkModeAvailable(4)) {
            recent100ModeBtn.setEnabled(false);
        }
    }



    /**
     * Register Mode Panel Click Event
     * <p>
     *
     * @author tojohnonly
     * @date 2023/07/02 12:51
     * @version 1.0.0
     */
    private void registerModePanelClickEvent() {

        addWordBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!addWordBtn.isEnabled()) {
                    return;
                }
                addWordBtn.setBackground(EsvConfig.buttonBgColor);
                FrameContainer.switchModeSelectToAddWord();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!addWordBtn.isEnabled()) {
                    return;
                }
                addWordBtn.setBackground(new Color(0, 136, 242));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!addWordBtn.isEnabled()) {
                    return;
                }
                addWordBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        learningModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!learningModeBtn.isEnabled()) {
                    return;
                }
                FrameContainer.switchModeSelectToStudy(1);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!learningModeBtn.isEnabled()) {
                    return;
                }
                learningModeBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!learningModeBtn.isEnabled()) {
                    return;
                }
                learningModeBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        chooseBookBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!chooseBookBtn.isEnabled()) {
                    return;
                }
                chooseBookBtn.setBackground(EsvConfig.buttonBgColor);

                // Open FileChooser
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public String getDescription() {
                        return "Sqlite Database File";
                    }
                    @Override
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        } else {
                            return (file.getName().toLowerCase().endsWith(".db") || file.getName().toLowerCase().endsWith(".db3"));
                        }
                    }
                });
                int chooseResult = fileChooser.showOpenDialog(null);
                if (chooseResult == JFileChooser.APPROVE_OPTION) {
                    String dbPath = fileChooser.getSelectedFile().getAbsolutePath();
                    // Switch Database
                    DataProcessor.switchDatabase(dbPath);
                    refreshModePanel();
                    FrameContainer.refresh();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!chooseBookBtn.isEnabled()) {
                    return;
                }
                chooseBookBtn.setBackground(new Color(252, 171, 54));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!chooseBookBtn.isEnabled()) {
                    return;
                }
                chooseBookBtn.setBackground(new Color(232, 151, 34));
            }
        });

        reviewModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!reviewModeBtn.isEnabled()) {
                    return;
                }
                FrameContainer.switchModeSelectToStudy(2);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!reviewModeBtn.isEnabled()) {
                    return;
                }
                reviewModeBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!reviewModeBtn.isEnabled()) {
                    return;
                }
                reviewModeBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        mixedModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                FrameContainer.switchModeSelectToStudy(3);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mixedModeBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mixedModeBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        recent100ModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!recent100ModeBtn.isEnabled()) {
                    return;
                }
                FrameContainer.switchModeSelectToStudy(4);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!recent100ModeBtn.isEnabled()) {
                    return;
                }
                recent100ModeBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!recent100ModeBtn.isEnabled()) {
                    return;
                }
                recent100ModeBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });
    }



    /**
     * Refresh Mode Panel
     * <p>
     *
     * @author tojohnonly
     * @date 2023/07/02 12:51
     * @version 1.0.0
     */
    public void refreshModePanel() {
        // Summary
        Integer allWordCount = DataProcessor.getWordCount(0);
        Integer unstudiedWordCount = DataProcessor.getWordCount(1);
        Integer masteredWordCount = DataProcessor.getWordCount(4);
        summaryLabel.setAnimationText("All Words:&nbsp;&nbsp;&nbsp;" + allWordCount + "<br/>Unstudied:&nbsp;&nbsp;" + unstudiedWordCount + "<br/>Mastered:&nbsp;&nbsp;&nbsp;" + masteredWordCount);

        DataProcessor.clearMode4Word();

        // Check Mode Available
        if (!DataProcessor.checkModeAvailable(1)) {
            learningModeBtn.setEnabled(false);
        } else {
            learningModeBtn.setEnabled(true);
        }
        if (!DataProcessor.checkModeAvailable(2)) {
            reviewModeBtn.setEnabled(false);
        } else {
            reviewModeBtn.setEnabled(true);
        }
        if (!DataProcessor.checkModeAvailable(3)) {
            mixedModeBtn.setEnabled(false);
        } else {
            mixedModeBtn.setEnabled(true);
        }
        if (!DataProcessor.checkModeAvailable(4)) {
            recent100ModeBtn.setEnabled(false);
        } else {
            recent100ModeBtn.setEnabled(true);
        }
    }

}
