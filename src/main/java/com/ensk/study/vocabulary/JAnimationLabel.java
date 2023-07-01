package com.ensk.study.vocabulary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JAnimationLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private int DELAY = 20;
	private static Timer timer;
	private String animationText = "";
	private int animationTextIndex = 0;

	public JAnimationLabel() {
		this("", 20);
	}

	public JAnimationLabel(String text) {
		this(text, 20);
	}

	public JAnimationLabel(String text, int delay) {
		this.DELAY = delay;
		this.animationText = text;
		this.animationTextIndex = 0;
		timer = new Timer(DELAY, new ReboundListener());
		timer.start();
	}


	private class ReboundListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			animateText();
			//repaint();
		}
	}

	public void setAnimationText(String text) {
		if (animationText.equals(text)) {
			return;
		}
		super.setText("");
		this.animationText = text;
		this.animationTextIndex = 0;
	}

	public void animateText() {
		if (getText().equals("<html><body>" + animationText + "<body></html>")) {
			return;
		}
		super.setText("<html><body>" + animationText.substring(0, animationTextIndex++) + "<body></html>");
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("JAnimationLabel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new JAnimationLabel("asdoflaslfdhasdldfhlA"));
		frame.setSize(300, 300);
		frame.setLocation(400, 400);
		frame.setVisible(true);
	}

}
