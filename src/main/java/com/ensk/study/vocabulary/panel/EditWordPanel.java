package com.ensk.study.vocabulary.panel;

import com.ensk.study.vocabulary.config.EsvConfig;
import com.ensk.study.vocabulary.service.DataProcessor;
import com.ensk.study.vocabulary.ui.JAnimationLabel;
import com.ensk.study.vocabulary.ui.JRoundedButton;
import com.ensk.study.vocabulary.ui.JRoundedTextField;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditWordPanel extends JPanel {

    // Word Edit Label
    private static JLabel wordEditLabel;
    // Word Edit Text Field
    private static JRoundedTextField wordEditTextField;
    // Pronounce Edit Label
    private static JLabel pronounceEditLabel;
    // Pronounce Edit Text Field
    private static JRoundedTextField pronounceEditTextField;
    // Translation Edit Label
    private static JLabel translationEditLabel;
    // Translation Edit Text Field
    private static JRoundedTextField translationEditTextField;
    // Example Edit Label
    private static JLabel exampleEditLabel;
    // Example Edit Text Field
    private static JRoundedTextField exampleEditTextField;
    // Edit Notice Label
    private static JAnimationLabel editNoticeLabel;
    // Edit Confirm Button
    private static JRoundedButton editConfirmBtn;
    // Edit Cancel Button
    private static JRoundedButton editCancelBtn;

    public EditWordPanel() {
        super();
        // 组件设置为绝对定位
        this.setLayout(null);
        // 设置背景色
        this.setBackground(EsvConfig.bgColor);

        // Word Edit Label
        wordEditLabel = new JLabel("Word:");
        wordEditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        wordEditLabel.setBounds(40, 30, 90, 30);
        wordEditLabel.setFont(EsvConfig.buttonFont);
        wordEditLabel.setForeground(Color.WHITE);

        // Pronounce Edit Label
        pronounceEditLabel = new JLabel("Pronounce:");
        pronounceEditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pronounceEditLabel.setBounds(40, 70, 90, 30);
        pronounceEditLabel.setFont(EsvConfig.buttonFont);
        pronounceEditLabel.setForeground(Color.WHITE);

        // Translation Edit Label
        translationEditLabel = new JLabel("Translation:");
        translationEditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        translationEditLabel.setBounds(40, 110, 90, 30);
        translationEditLabel.setFont(EsvConfig.buttonFont);
        translationEditLabel.setForeground(Color.WHITE);

        // Example Edit Label
        exampleEditLabel = new JLabel("Example:");
        exampleEditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        exampleEditLabel.setBounds(40, 150, 90, 30);
        exampleEditLabel.setFont(EsvConfig.buttonFont);
        exampleEditLabel.setForeground(Color.WHITE);

        // Word Edit Text Field
        wordEditTextField = new JRoundedTextField();
        wordEditTextField.setBounds(140, 30, 230, 30);
        wordEditTextField.setFont(EsvConfig.buttonFont);
        wordEditTextField.setForeground(Color.WHITE);
        wordEditTextField.setCaretColor(Color.WHITE);
        wordEditTextField.setBackground(EsvConfig.bgColor);

        // Pronounce Edit Text Field
        pronounceEditTextField = new JRoundedTextField();
        pronounceEditTextField.setBounds(140, 70, 230, 30);
        pronounceEditTextField.setFont(EsvConfig.pronounceFont);
        pronounceEditTextField.setForeground(Color.WHITE);
        pronounceEditTextField.setCaretColor(Color.WHITE);
        pronounceEditTextField.setBackground(EsvConfig.bgColor);

        // Translation Edit Text Field
        translationEditTextField = new JRoundedTextField();
        translationEditTextField.setBounds(140, 110, 230, 30);
        translationEditTextField.setFont(EsvConfig.buttonFont);
        translationEditTextField.setForeground(Color.WHITE);
        translationEditTextField.setCaretColor(Color.WHITE);
        translationEditTextField.setBackground(EsvConfig.bgColor);

        // Example Edit Text Field
        exampleEditTextField = new JRoundedTextField();
        exampleEditTextField.setBounds(140, 150, 230, 30);
        exampleEditTextField.setFont(EsvConfig.buttonFont);
        exampleEditTextField.setForeground(Color.WHITE);
        exampleEditTextField.setCaretColor(Color.WHITE);
        exampleEditTextField.setBackground(EsvConfig.bgColor);

        // Edit Notice Label
        editNoticeLabel = new JAnimationLabel("", 15);
        editNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editNoticeLabel.setBounds(60, 190, 300, 15);
        editNoticeLabel.setFont(EsvConfig.exampleFont);
        editNoticeLabel.setForeground(Color.RED);

        // Edit Confirm Button
        editConfirmBtn = new JRoundedButton("Confirm");
        editConfirmBtn.setBounds(100, 215, 100, 35);
        editConfirmBtn.setForeground(Color.WHITE);
        editConfirmBtn.setFont(EsvConfig.buttonFont);
        editConfirmBtn.setBackground(EsvConfig.buttonBgColor);
        editConfirmBtn.setBorderPainted(false);
        editConfirmBtn.setFocusPainted(false);

        // Edit Cancel Button
        editCancelBtn = new JRoundedButton("Cancel");
        editCancelBtn.setBounds(240, 215, 100, 35);
        editCancelBtn.setForeground(Color.WHITE);
        editCancelBtn.setFont(EsvConfig.buttonFont);
        editCancelBtn.setBackground(EsvConfig.buttonBgColor);
        editCancelBtn.setBorderPainted(false);
        editCancelBtn.setFocusPainted(false);

        // Add component to panel
        this.add(wordEditLabel);
        this.add(pronounceEditLabel);
        this.add(translationEditLabel);
        this.add(exampleEditLabel);
        this.add(wordEditTextField);
        this.add(pronounceEditTextField);
        this.add(translationEditTextField);
        this.add(exampleEditTextField);
        this.add(editNoticeLabel);
        this.add(editConfirmBtn);
        this.add(editCancelBtn);

        // Add Mouse Listener
        this.registerEditPanelClickEvent();
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
        editConfirmBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (null == wordEditTextField.getText() || wordEditTextField.getText().equals("")) {
                    editNoticeLabel.setAnimationText("Word Can't Be Null!");
                    return;
                }
                if (null == translationEditTextField.getText() || translationEditTextField.getText().equals("")) {
                    editNoticeLabel.setAnimationText("Translation Can't Be Null!");
                    return;
                }
                DataProcessor.updateCurrentWord(wordEditTextField.getText(), pronounceEditTextField.getText(), translationEditTextField.getText(), exampleEditTextField.getText());
                FrameContainer.switchEditToStudy(wordEditTextField.getText(), pronounceEditTextField.getText(), translationEditTextField.getText(), exampleEditTextField.getText());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editConfirmBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editConfirmBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        editCancelBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                FrameContainer.switchEditToStudy();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editCancelBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editCancelBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });
    }



    public void preparePanel() {
        wordEditTextField.setText(DataProcessor.getCurrentWord().getWord());
        pronounceEditTextField.setText(DataProcessor.getCurrentWord().getPronounce());
        translationEditTextField.setText(DataProcessor.getCurrentWord().getTranslation());
        translationEditTextField.setCaretPosition(0);
        exampleEditTextField.setText(DataProcessor.getCurrentWord().getExample());
        exampleEditTextField.setCaretPosition(0);
    }

}
