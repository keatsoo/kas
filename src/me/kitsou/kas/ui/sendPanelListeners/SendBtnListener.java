package me.kitsou.kas.ui.sendPanelListeners;

import me.kitsou.kas.KasApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.kitsou.kas.ui.KasWindow;
import org.apache.commons.validator.routines.InetAddressValidator;

public class SendBtnListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
        sendMessage();
    }

    private void sendMessage(){
        if (!KasWindow.getMessageData().getSenderName().isBlank()){
            if (InetAddressValidator.getInstance().isValidInet4Address(KasWindow.getMessageData().getReceiverIP())){
                if (!KasWindow.getMessageData().getMessageContents().isBlank()){
                    KasApp.sendMessageOverIP(KasWindow.getMessageData());
                } else {
                    KasApp.showErrorMessage("Please enter a message !");
                    System.err.println("EMPTY MESSAGE !");
                }
            } else {
                KasApp.showErrorMessage("The IP must be valid !");
                System.err.println("INVALID IP !");
            }
        } else {
            KasApp.showErrorMessage("The name can't be empty !");
            System.err.println("EMPTY NAME !");
        }
    }


}
