package me.kitsou.kas;

import javax.swing.*;
import java.awt.*;

public class KasWindow extends JFrame {
    public KasWindow(String title){
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new FlowLayout());

        JLabel label = new JLabel("Welcome to kas.");
        contentPane.add(label);
    }
}
