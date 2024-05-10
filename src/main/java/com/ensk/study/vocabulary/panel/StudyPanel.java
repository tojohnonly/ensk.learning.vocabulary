package com.ensk.study.vocabulary.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.ensk.study.vocabulary.config.EsvConfig;
import com.ensk.study.vocabulary.service.DataProcessor;
import com.ensk.study.vocabulary.ui.JAnimationLabel;
import com.ensk.study.vocabulary.ui.JRoundedButton;

public class StudyPanel extends JPanel {

    // Word Label
    private static JAnimationLabel wordLabel;
    // Score Label
    private static JAnimationLabel scoreLabel;
    // Pronounce Label
    private static JAnimationLabel pronounceLabel;
    // translation Label
    private static JAnimationLabel translationLabel;
    // Example Label
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



    public StudyPanel() {
        super();
        // Component Absolute Positioning
        this.setLayout(null);
        // Set Background Color
        this.setBackground(EsvConfig.bgColor);

        // word
        wordLabel = new JAnimationLabel("", 20);
        wordLabel.setBounds(30, 15, 400, 35);
        wordLabel.setFont(EsvConfig.wordFont);
        wordLabel.setForeground(Color.WHITE);

        // score
        scoreLabel = new JAnimationLabel("", 10);
        scoreLabel.setBounds(30, 60, 40, 25);
        scoreLabel.setFont(EsvConfig.pronounceFont);
        scoreLabel.setForeground(Color.WHITE);

        // pronounce
        pronounceLabel = new JAnimationLabel("", 20);
        pronounceLabel.setBounds(80, 60, 400, 25);
        pronounceLabel.setFont(EsvConfig.pronounceFont);
        pronounceLabel.setForeground(Color.WHITE);

        // translation
        translationLabel = new JAnimationLabel("", 15);
        translationLabel.setBounds(30, 95, 400, 50);
        translationLabel.setFont(EsvConfig.translationFont);
        translationLabel.setForeground(Color.WHITE);

        // example
        exampleLabel = new JAnimationLabel("", 5);
        exampleLabel.setBounds(30, 145, 400, 70);
        exampleLabel.setFont(EsvConfig.exampleFont);
        exampleLabel.setForeground(Color.WHITE);

        // Don't Know Button
        dkBtn = new JRoundedButton("Don't Know");
        dkBtn.setBounds(30, 230, 110, 35);
        dkBtn.setForeground(Color.WHITE);
        dkBtn.setFont(EsvConfig.buttonFont);
        dkBtn.setBackground(EsvConfig.buttonBgColor);
        dkBtn.setBorderPainted(false);
        dkBtn.setFocusPainted(false);

        // Hazy Memory Button
        hmBtn = new JRoundedButton("Hazy Memory");
        hmBtn.setBounds(155, 230, 125, 35);
        hmBtn.setForeground(Color.WHITE);
        hmBtn.setFont(EsvConfig.buttonFont);
        hmBtn.setBackground(EsvConfig.buttonBgColor);
        hmBtn.setBorderPainted(false);
        hmBtn.setFocusPainted(false);

        // Keep in Mind Button
        kimBtn = new JRoundedButton("Keep in Mind");
        kimBtn.setBounds(295, 230, 125, 35);
        kimBtn.setForeground(Color.WHITE);
        kimBtn.setFont(EsvConfig.buttonFont);
        kimBtn.setBackground(EsvConfig.buttonBgColor);
        kimBtn.setBorderPainted(false);
        kimBtn.setFocusPainted(false);

        // Next Word Button
        nwBtn = new JRoundedButton("Next Word");
        nwBtn.setBounds(60, 230, 200, 35);
        nwBtn.setForeground(Color.WHITE);
        nwBtn.setFont(EsvConfig.buttonFont);
        nwBtn.setBackground(EsvConfig.buttonBgColor);
        nwBtn.setBorderPainted(false);
        nwBtn.setFocusPainted(false);
        nwBtn.setVisible(false);

        // Edit Button
        editBtn = new JRoundedButton("Edit");
        editBtn.setBounds(270, 230, 50, 35);
        editBtn.setForeground(Color.WHITE);
        editBtn.setFont(EsvConfig.buttonFont);
        editBtn.setBackground(EsvConfig.buttonBgColor);
        editBtn.setBorderPainted(false);
        editBtn.setFocusPainted(false);
        editBtn.setVisible(false);

        // Back to Mode Select Button
        btmBtn = new JRoundedButton("Mode");
        btmBtn.setBounds(330, 230, 60, 35);
        btmBtn.setForeground(Color.WHITE);
        btmBtn.setFont(EsvConfig.buttonFont);
        btmBtn.setBackground(EsvConfig.buttonBgColor);
        btmBtn.setBorderPainted(false);
        btmBtn.setFocusPainted(false);
        btmBtn.setVisible(false);

        // Add component to panel
        this.add(wordLabel);
        this.add(scoreLabel);
        this.add(pronounceLabel);
        this.add(translationLabel);
        this.add(exampleLabel);
        this.add(dkBtn);
        this.add(hmBtn);
        this.add(kimBtn);
        this.add(nwBtn);
        this.add(editBtn);
        this.add(btmBtn);

        // Add Mouse Listener
        this.registerStudyPanelClickEvent();
    }



    /**
     * Register Study Panel Click Event
     * <p>
     *
     * @author tojohnonly
     * @date 2023/07/02 10:23
     * @version 1.0.0
     */
    public static void registerStudyPanelClickEvent() {
        dkBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.updateScore(DataProcessor.getCurrentWord(), 1);
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
                dkBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dkBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        hmBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.updateScore(DataProcessor.getCurrentWord(), 2);
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
                hmBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hmBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        kimBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DataProcessor.updateScore(DataProcessor.getCurrentWord(), 3);
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
                kimBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                kimBtn.setBackground(EsvConfig.buttonBgColor);
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
                nwBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nwBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                FrameContainer.switchStudyToEdit();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        btmBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                FrameContainer.switchStudyToModeSelect();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btmBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btmBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });
    }



    public void preparePanel() {
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
    }


    public void fillPanel(String word, String pronounce, String translation, String example) {
        wordLabel.setAnimationText(word);
        pronounceLabel.setAnimationText(pronounce);
        translationLabel.setAnimationText(translation);
        exampleLabel.setAnimationText(example);
    }

}
