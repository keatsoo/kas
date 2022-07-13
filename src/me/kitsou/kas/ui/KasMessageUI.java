package me.kitsou.kas.ui;

import me.kitsou.kas.KasMessage;

import javax.swing.*;
import java.awt.*;

public class KasMessageUI extends JPanel {
    public KasMessageUI(KasMessage messObj){
        this.setLayout(new GridBagLayout());

        JLabel senderNameLabel = new JLabel("Sender : ");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(senderNameLabel, gbc);

        JLabel senderName = new JLabel(messObj.getSenderName());
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(senderName, gbc);

        JLabel senderIPLabel = new JLabel("To IP : ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(senderIPLabel, gbc);

        JLabel senderIP = new JLabel(messObj.getReceiverIP());
        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(senderIP, gbc);

        JLabel messLabel = new JLabel("Message : ");
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(messLabel, gbc);

        JLabel message = new JLabel(messObj.getMessageContents());
        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(message, gbc);
    }
}
