package me.kitsou.kas;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class KasWindow extends JFrame {
    public KasWindow(String title){
        // WINDOW DATA
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // LOOK AND FEEL
        System.out.println("[INFO] : Trying to load touch and feel...");
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            System.out.println("[INFO] : Nimbus look and feel loaded!");
        } catch (Exception e) {
            System.err.println("[ERROR] : COULDN'T LOAD LOOK AND FEEL");
        }
        setDefaultLookAndFeelDecorated(true);

        // ACTUAL UI
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridBagLayout());

        // MENU BAR
        this.setJMenuBar(kasCreateMenuBar());


    }


    private JMenuBar kasCreateMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        menuBar.add(file);

        JMenuItem saveDiscussion = new JMenuItem("Save discussion as...");
        saveDiscussion.setMnemonic('S');
        saveDiscussion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveDiscussion.addActionListener(new SaveMenuItemListener());
        file.add(saveDiscussion);

        JMenuItem connect = new JMenuItem("Connect...");
        connect.setMnemonic('C');
        connect.addActionListener(new ConnectMenuListener());
        file.add(connect);

        file.addSeparator();

        JMenuItem myIP = new JMenuItem("Whats my IP?");
        myIP.addActionListener(new MyIPListener());
        file.add(myIP);

        return menuBar;
    }
}
