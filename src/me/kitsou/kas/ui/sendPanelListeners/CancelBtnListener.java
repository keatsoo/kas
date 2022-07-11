package me.kitsou.kas.ui.sendPanelListeners;

import me.kitsou.kas.ui.KasWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelBtnListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        KasWindow.clearMessage();
    }
}
