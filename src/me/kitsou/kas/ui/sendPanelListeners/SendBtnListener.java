package me.kitsou.kas.ui.sendPanelListeners;

import me.kitsou.kas.ui.KasMessage;
import me.kitsou.kas.ui.KasWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SendBtnListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
        JFrame ogFrame = (JFrame)(((JButton)(e.getSource())).getTopLevelAncestor());
        sendMessage(ogFrame);
    }

    private void sendMessage(JFrame window){
        KasMessage message = KasWindow.getMessageData();

        if (!message.getSenderName().isBlank()){
            if (isValidInet4Address(message.getReceiverIP())){
                if (!message.getMessageContents().isBlank()){
                    KasWindow.addMessage(message);
                } else {
                    JOptionPane.showMessageDialog(window, "Please enter a message !");
                    System.err.println("EMPTY MESSAGE !");
                }
            } else {
                JOptionPane.showMessageDialog(window, "The IP must be valid !");
                System.err.println("INVALID IP !");
            }
        } else {
            JOptionPane.showMessageDialog(window, "The name can't be empty !");
            System.err.println("EMPTY NAME !");
        }
    }

    private boolean isValidInet4Address(String ip)
    {
        String[] groups = ip.split("\\.");

        if (groups.length != 4) {
            return false;
        }

        try {
            return Arrays.stream(groups)
                    .filter(s -> s.length() > 1 && s.startsWith("0"))
                    .map(Integer::parseInt)
                    .filter(i -> (i >= 0 && i <= 255))
                    .count() == 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
