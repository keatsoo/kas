package me.kitsou.kas;

import me.kitsou.kas.ui.KasWindow;

public class KasApp {
    public static void main(String[] args){
        System.out.println("Started kas... :)");

        KasWindow window = new KasWindow("kas - STILL IN PROTOTYPE PHASE");
        window.setVisible(true);
        System.out.println("[INFO] : Window set to visible.");
    }
}
