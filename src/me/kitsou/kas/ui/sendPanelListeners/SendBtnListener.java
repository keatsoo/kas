package me.kitsou.kas.ui.sendPanelListeners;

import me.kitsou.kas.ui.KasMessage;
import me.kitsou.kas.ui.KasWindow;

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
        KasWindow.addMessage(message);
    }
}
