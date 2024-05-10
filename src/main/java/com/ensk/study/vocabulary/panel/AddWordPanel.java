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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddWordPanel extends JPanel {

    // Add Word Label
    private static JLabel addWordLabel;
    // Add Word Field
    private static JRoundedTextField addWordTextField;
    // Add Pronounce Label
    private static JLabel addPronounceLabel;
    // Add Pronounce Field
    private static JRoundedTextField addPronounceTextField;
    // Add Translation Label
    private static JLabel addTranslationLabel;
    // Add Translation Text Field
    private static JRoundedTextField addTranslationTextField;
    // Add Example Label
    private static JLabel addExampleLabel;
    // Add Example Text Field
    private static JRoundedTextField addExampleTextField;
    // Add Notice Label
    private static JAnimationLabel addNoticeLabel;
    // Add Confirm Button
    private static JRoundedButton addConfirmBtn;
    // Add Cancel Button
    private static JRoundedButton addCancelBtn;


    public AddWordPanel() {
        super();
        // 组件设置为绝对定位
        this.setLayout(null);
        // 设置背景色
        this.setBackground(EsvConfig.bgColor);

        // Add Word Label
        addWordLabel = new JLabel("Word:");
        addWordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addWordLabel.setBounds(40, 30, 90, 30);
        addWordLabel.setFont(EsvConfig.buttonFont);
        addWordLabel.setForeground(Color.WHITE);

        // Add Pronounce Label
        addPronounceLabel = new JLabel("Pronounce:");
        addPronounceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addPronounceLabel.setBounds(40, 70, 90, 30);
        addPronounceLabel.setFont(EsvConfig.buttonFont);
        addPronounceLabel.setForeground(Color.WHITE);

        // Add Translation Label
        addTranslationLabel = new JLabel("Translation:");
        addTranslationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addTranslationLabel.setBounds(40, 110, 90, 30);
        addTranslationLabel.setFont(EsvConfig.buttonFont);
        addTranslationLabel.setForeground(Color.WHITE);

        // Add Example Label
        addExampleLabel = new JLabel("Example:");
        addExampleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addExampleLabel.setBounds(40, 150, 90, 30);
        addExampleLabel.setFont(EsvConfig.buttonFont);
        addExampleLabel.setForeground(Color.WHITE);

        // Add Word Text Field
        addWordTextField = new JRoundedTextField();
        addWordTextField.setBounds(140, 30, 230, 30);
        addWordTextField.setFont(EsvConfig.buttonFont);
        addWordTextField.setForeground(Color.WHITE);
        addWordTextField.setCaretColor(Color.WHITE);
        addWordTextField.setBackground(EsvConfig.bgColor);

        // Add Pronounce Text Field
        addPronounceTextField = new JRoundedTextField();
        addPronounceTextField.setBounds(140, 70, 230, 30);
        addPronounceTextField.setFont(EsvConfig.pronounceFont);
        addPronounceTextField.setForeground(Color.WHITE);
        addPronounceTextField.setCaretColor(Color.WHITE);
        addPronounceTextField.setBackground(EsvConfig.bgColor);

        // Add Translation Text Field
        addTranslationTextField = new JRoundedTextField();
        addTranslationTextField.setBounds(140, 110, 230, 30);
        addTranslationTextField.setFont(EsvConfig.buttonFont);
        addTranslationTextField.setForeground(Color.WHITE);
        addTranslationTextField.setCaretColor(Color.WHITE);
        addTranslationTextField.setBackground(EsvConfig.bgColor);

        // Add Example Text Field
        addExampleTextField = new JRoundedTextField();
        addExampleTextField.setBounds(140, 150, 230, 30);
        addExampleTextField.setFont(EsvConfig.buttonFont);
        addExampleTextField.setForeground(Color.WHITE);
        addExampleTextField.setCaretColor(Color.WHITE);
        addExampleTextField.setBackground(EsvConfig.bgColor);

        // Add Notice Label
        addNoticeLabel = new JAnimationLabel("", 15);
        addNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addNoticeLabel.setBounds(60, 190, 300, 15);
        addNoticeLabel.setFont(EsvConfig.exampleFont);
        addNoticeLabel.setForeground(Color.RED);

        // Add Confirm Button
        addConfirmBtn = new JRoundedButton("Add");
        addConfirmBtn.setBounds(100, 215, 100, 35);
        addConfirmBtn.setForeground(Color.WHITE);
        addConfirmBtn.setFont(EsvConfig.buttonFont);
        addConfirmBtn.setBackground(EsvConfig.buttonBgColor);
        addConfirmBtn.setBorderPainted(false);
        addConfirmBtn.setFocusPainted(false);

        // Add Cancel Button
        addCancelBtn = new JRoundedButton("Cancel");
        addCancelBtn.setBounds(240, 215, 100, 35);
        addCancelBtn.setForeground(Color.WHITE);
        addCancelBtn.setFont(EsvConfig.buttonFont);
        addCancelBtn.setBackground(EsvConfig.buttonBgColor);
        addCancelBtn.setBorderPainted(false);
        addCancelBtn.setFocusPainted(false);

        // Add component to panel
        this.add(addWordLabel);
        this.add(addPronounceLabel);
        this.add(addTranslationLabel);
        this.add(addExampleLabel);
        this.add(addWordTextField);
        this.add(addPronounceTextField);
        this.add(addTranslationTextField);
        this.add(addExampleTextField);
        this.add(addNoticeLabel);
        this.add(addConfirmBtn);
        this.add(addCancelBtn);

        // Add Mouse Listener
        registerAddPanelClickEvent();
    }



    /**
     * Register Add Panel Click Event
     * <p>
     *
     * @author tojohnonly
     * @date 2023/07/02 22:15
     * @version 1.0.0
     */
    private void registerAddPanelClickEvent() {

        addWordTextField.addFocusListener(new FocusListener(){
            @Override
            public void focusLost(FocusEvent e){
                if (null == addWordTextField.getText() || addWordTextField.getText().equals("")) {
                    addNoticeLabel.setAnimationText("Word Can't Be Null!");
                    return;
                }
                if (null != DataProcessor.queryWord(addWordTextField.getText())) {
                    addNoticeLabel.setAnimationText("Word Alread Exist!");
                    return;
                } else {
                    addNoticeLabel.setAnimationText("");
                    return;
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
            }
        });

        addConfirmBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (null == addWordTextField.getText() || addWordTextField.getText().equals("")) {
                    addNoticeLabel.setAnimationText("Word Can't Be Null!");
                    return;
                }
                if (null != DataProcessor.queryWord(addWordTextField.getText())) {
                    addNoticeLabel.setAnimationText("Word Alread Exist!");
                    return;
                }
                if (null == addTranslationTextField.getText() || addTranslationTextField.getText().equals("")) {
                    addNoticeLabel.setAnimationText("Translation Can't Be Null!");
                    return;
                }

                DataProcessor.addWord(addWordTextField.getText(), addPronounceTextField.getText(), addTranslationTextField.getText(), addExampleTextField.getText());

                addWordTextField.setText("");
                addPronounceTextField.setText("");
                addTranslationTextField.setText("");
                addExampleTextField.setText("");
                addNoticeLabel.setAnimationText("");
                FrameContainer.switchAddWordToModeSelect();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                addConfirmBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addConfirmBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });

        addCancelBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addWordTextField.setText("");
                addPronounceTextField.setText("");
                addTranslationTextField.setText("");
                addExampleTextField.setText("");
                addNoticeLabel.setAnimationText("");
                FrameContainer.switchAddWordToModeSelect();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                addCancelBtn.setBackground(EsvConfig.buttonHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addCancelBtn.setBackground(EsvConfig.buttonBgColor);
            }
        });
    }

}
