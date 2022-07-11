package me.kitsou.kas.sendPanelListeners;

import me.kitsou.kas.KasMessage;
import me.kitsou.kas.KasWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendBtnListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
        JFrame ogFrame = (JFrame)(((JButton)(e.getSource())).getTopLevelAncestor());
        confirmMessage(ogFrame);
    }

    private void confirmMessage(JFrame window){
        KasMessage message = KasWindow.getMessageData();
        JOptionPane.showMessageDialog(window, "Do you want to send this message to \"" + message.getReceiver() + "\" (" + message.getReceiverIP() + ") : \" " + message.getMessageContents() + "\" ?");
    }
}
