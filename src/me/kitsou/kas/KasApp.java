package me.kitsou.kas;

import me.kitsou.kas.ui.KasMessage;
import me.kitsou.kas.ui.KasWindow;

import java.util.ArrayList;

public class KasApp {
    private static ArrayList<KasMessage> messagesArray = new ArrayList<>();

    public static void main(String[] args){
        System.out.println("Started kas... :)");
        KasWindow window = new KasWindow("kas - STILL IN PROTOTYPE PHASE");
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
}
