package com.ensk.study.vocabulary;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.border.Border;

public class JRoundedButton extends JButton {
    public JRoundedButton(String s) {
        super(s);
        // 去除文字与按钮的边沿
        setMargin(new Insets(0, 0, 0, 0));
        // 圆角矩形边界
        setBorder(new RoundBorder());
        // 取消原先画矩形的设置
        setContentAreaFilled(false);
        // 会导致按钮没有明显边界
        // setBorderPainted(false);
        // 去除文字周围的虚线框
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        // 抗锯齿
        // JDK文档：http://tool.oschina.net/uploads/apidocs/jdk-zh/java/awt/RenderingHints.html
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            // 按下后按钮变成绿色
            // UIManager.put("Button.select", new Color(118, 116, 115));
            g2d.setColor(new Color(118, 116, 115));
        } else {
            g2d.setColor(getBackground());
        }
        // 填充圆角矩形边界
        g2d.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 10, 10);

        // 这个调用会画一个标签和焦点矩形
        super.paintComponent(g);
    }

}

class RoundBorder implements Border {
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        // 使用黑色在组件的外边缘绘制一个圆角矩形
        g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 10, 10);
    }
}