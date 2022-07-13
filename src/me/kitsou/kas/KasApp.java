package me.kitsou.kas;

import me.kitsou.kas.socketManager.KasSocketManager;
import me.kitsou.kas.ui.KasWindow;

import javax.swing.*;
import java.util.ArrayList;

public class KasApp {
    private static final ArrayList<KasMessage> messagesArray = new ArrayList<>();
    static private final KasSocketManager sockMgr = new KasSocketManager(64209);
    static private KasWindow window;
    public static void main(String[] args){
        System.out.println("Started kas... :)");
        setWindow(new KasWindow("kas - STILL IN PROTOTYPE PHASE"));
        sockMgr.createListenThread();
    }

    public static void addMessage(KasMessage mess){
        messagesArray.add(mess);
        System.out.println("added message.");
        System.out.println("is messagesArray not empty ?" + !messagesArray.isEmpty());
        System.out.println("messagesArray.size() = " + messagesArray.size());

    }

    public static ArrayList<KasMessage> getMessagesArray(){
        return messagesArray;
    }
    public static void sendMessageOverIP(KasMessage messageToSend){
        sockMgr.createSendThread("192.168.1.30", 64209, new KasMessage("me","192.168.1.30","test"));
    }

    public static KasWindow getWindow() {
        return window;
    }

    public static void setWindow(KasWindow window) {
        KasApp.window = window;
    }

    public static void showErrorMessage(String error){
        JOptionPane.showMessageDialog(getWindow(), error, "Error !", JOptionPane.ERROR_MESSAGE);
    }

}
