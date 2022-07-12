package me.kitsou.kas.ui.sendPanelListeners;

import me.kitsou.kas.KasApp;
import me.kitsou.kas.ui.KasMessage;
import me.kitsou.kas.ui.KasWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.commons.validator.routines.InetAddressValidator;

public class SendBtnListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
        JFrame ogFrame = (JFrame)(((JButton)(e.getSource())).getTopLevelAncestor());
        sendMessage(ogFrame);
    }

    private void sendMessage(JFrame window){
        KasMessage message = KasWindow.getMessageData();

        if (!message.getSenderName().isBlank()){
            if (InetAddressValidator.getInstance().isValidInet4Address(message.getReceiverIP())){
                if (!message.getMessageContents().isBlank()){
                    KasApp.addMessage(message);
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


}
