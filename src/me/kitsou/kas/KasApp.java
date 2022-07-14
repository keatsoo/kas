package me.kitsou.kas;

import me.kitsou.kas.socketManager.KasSocketManager;
import me.kitsou.kas.socketManager.ListenThread;
import me.kitsou.kas.ui.KasWindow;

import javax.swing.*;
import java.util.ArrayList;

public class KasApp {
    private static final ArrayList<KasMessage> messagesArray = new ArrayList<>();
    static private final KasSocketManager sockMgr = new KasSocketManager(64209);
    static private KasWindow window;
    public static void main(String[] args){
        System.out.println("[STARING] : Started kas... :)");

        System.out.println("[INFO] : Starting thread");
        ListenThread listenThreadRunnable = new ListenThread();
        Thread listenThread = new Thread(listenThreadRunnable);
        listenThread.start();
        System.out.println("[INFO] : Thread started !");

        System.out.println("[INFO] : Creating the window...");
        setWindow(new KasWindow("kas - STILL IN PROTOTYPE PHASE")); // ALWAYS RUN LAST
    }

    public static void addMessage(KasMessage mess){
        messagesArray.add(mess);
        System.out.println("[INFO] : Added message.");
    }

    public static ArrayList<KasMessage> getMessagesArray(){
        return messagesArray;
    }
    public static void sendMessageOverIP(KasMessage messageToSend){
        sockMgr.createSendThread(messageToSend.getReceiverIP(), 64209, messageToSend);
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

    public static KasSocketManager getSockMgr(){
        return sockMgr;
    }
}
