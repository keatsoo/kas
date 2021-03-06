package me.kitsou.kas.ui;

import me.kitsou.kas.KasApp;
import me.kitsou.kas.KasMessage;
import me.kitsou.kas.ui.menuBarListeners.ConnectMenuListener;
import me.kitsou.kas.ui.menuBarListeners.MyIPListener;
import me.kitsou.kas.ui.menuBarListeners.SaveMenuItemListener;
import me.kitsou.kas.ui.menuBarListeners.quitMenuItemListener;
import me.kitsou.kas.ui.sendPanelListeners.CancelBtnListener;
import me.kitsou.kas.ui.sendPanelListeners.SendBtnListener;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KasWindow extends JFrame {
    private static final JTextField nameTextField = new JTextField("", 15);
    private static final JTextField receiverIPTextField = new JTextField("", 15);
    private static final JTextArea messageTextArea = new JTextArea("", 7, 15);

    public KasWindow(String title){
        // WINDOW DATA
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        System.out.println("[INFO] : Window set to visible.");

        // LOOK AND FEEL
        System.out.println("[INFO] : Trying to load touch and feel...");
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            System.out.println("[INFO] : Nimbus look and feel loaded!");
        } catch (Exception e) {
            System.err.println("[ERROR] : COULDN'T LOAD LOOK AND FEEL");
        }
        setDefaultLookAndFeelDecorated(true);

        // --------------------------- ACTUAL UI --------------------------------------

        // CONTENT PANE
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridBagLayout());

        // MENU BAR
        this.setJMenuBar(kasCreateMenuBar());


        // SENDING PANEL CREATION
        JPanel sendingPanel = new JPanel();
        sendingPanel.setBorder(BorderFactory.createTitledBorder("Send"));
        GridBagConstraints sendPanelConstr = new GridBagConstraints();
        sendPanelConstr.gridx = 1;
        sendPanelConstr.gridy = 1;
        contentPane.add(sendingPanel, sendPanelConstr);
        sendingPanel.setLayout(new GridBagLayout());
        //sendingPanel.add(Box.createRigidArea(new Dimension(150,150)));

        // SENDING PANEL LAYOUT
        JLabel nameLabel = new JLabel("Enter your name :");
        nameLabel.setSize(180,20);
        GridBagConstraints nameLabelConstr = new GridBagConstraints();
        nameLabelConstr.gridx = 1;
        nameLabelConstr.gridy = 1;
        nameLabelConstr.gridwidth = 2;
        sendingPanel.add(nameLabel, nameLabelConstr);

        GridBagConstraints nameTextFieldConstr = new GridBagConstraints();
        nameTextFieldConstr.gridx = 1;
        nameTextFieldConstr.gridy = 2;
        nameTextFieldConstr.gridwidth = 2;
        sendingPanel.add(nameTextField, nameTextFieldConstr);

        JLabel receiverIPLabel = new JLabel("Enter the receiver's IP address :");
        receiverIPLabel.setSize(180,20);
        GridBagConstraints receiverIPLabelConstr = new GridBagConstraints();
        receiverIPLabelConstr.gridx = 1;
        receiverIPLabelConstr.gridy = 3;
        receiverIPLabelConstr.gridwidth = 2;
        sendingPanel.add(receiverIPLabel, receiverIPLabelConstr);

        GridBagConstraints recIPTextFieldConstr = new GridBagConstraints();
        recIPTextFieldConstr.gridx = 1;
        recIPTextFieldConstr.gridy = 4;
        recIPTextFieldConstr.gridwidth = 2;
        sendingPanel.add(receiverIPTextField, recIPTextFieldConstr);

        JLabel messageLabel = new JLabel("Enter your message :");
        messageLabel.setSize(180,20);
        GridBagConstraints messageLabelConstr = new GridBagConstraints();
        messageLabelConstr.gridx = 1;
        messageLabelConstr.gridy = 5;
        messageLabelConstr.gridwidth = 2;
        sendingPanel.add(messageLabel, messageLabelConstr);

        GridBagConstraints messTextAreaConstr = new GridBagConstraints();
        messTextAreaConstr.gridx = 1;
        messTextAreaConstr.gridy = 6;
        messTextAreaConstr.gridwidth = 2;
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        sendingPanel.add(messageTextArea, messTextAreaConstr);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new CancelBtnListener());
        GridBagConstraints cancelBtnConstr = new GridBagConstraints();
        cancelBtnConstr.gridx = 1;
        cancelBtnConstr.gridy = 8;
        cancelBtnConstr.gridwidth = 1;
        sendingPanel.add(cancelBtn,cancelBtnConstr);

        JButton sendBtn = new JButton("Send !");
        sendBtn.addActionListener(new SendBtnListener());
        GridBagConstraints sendBtnConstr = new GridBagConstraints();
        sendBtnConstr.gridx = 2;
        sendBtnConstr.gridy = 8;
        sendBtnConstr.gridwidth = 1;
        sendingPanel.add(sendBtn,sendBtnConstr);


        // MESSAGES PANEL CREATION
        JPanel messagesPanel = new JPanel();
        messagesPanel.setBorder(BorderFactory.createTitledBorder("Messages"));
        GridBagConstraints messPanConstr = new GridBagConstraints();
        messPanConstr.gridx = 2;
        messPanConstr.gridy = 1;
        messPanConstr.weightx = 0.1;
        messagesPanel.setLayout(new BorderLayout());
        contentPane.add(messagesPanel, messPanConstr);

        revalidate();
        repaint();

        // MESSAGES PANEL VIEWPORT
        JScrollPane messScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        messagesPanel.add(messScrollPane);
        //messScrollPane.setPreferredSize(new Dimension(300,200));
        JPanel messagesContainer = new JPanel();
        messagesContainer.setLayout(new BoxLayout(messagesContainer, BoxLayout.PAGE_AXIS));
        messagesContainer.setPreferredSize(new Dimension(300,200));
        messagesPanel.add(messagesContainer);

        // ADD MESSAGES
        boolean noMessMess = false;
        JLabel noMessLabel = new JLabel("Sorry, there is no message yet :(");
        int printedNb = 0;
        while (KasApp.getMessagesArray().size() < 20){
            if(KasApp.getMessagesArray().isEmpty()){
                if(!noMessMess){
                    messagesContainer.add(noMessLabel);
                    messagesContainer.revalidate();
                    noMessMess = true;
                    System.out.println("[INFO] : No message");
                }
                messagesContainer.revalidate();
            } else {
                messagesContainer.remove(noMessLabel);
                messagesContainer.revalidate();
                noMessMess = false;
                if(printedNb < KasApp.getMessagesArray().size()){
                    messagesContainer.removeAll();
                    printedNb = 0;
                    ArrayList<KasMessage> messagesArray = KasApp.getMessagesArray();
                    for (int i = 0; i < messagesArray.size(); i++) {
                        KasMessage messageItem = messagesArray.get(i);
                        messagesContainer.add(new KasMessageUI(messageItem));
                        messagesContainer.revalidate();
                        System.out.println("[INFO] : Message found");
                        printedNb++;
                    }
                }
            }
            sendingPanel.revalidate();
        }
    }


    private JMenuBar kasCreateMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        menuBar.add(file);

        JMenuItem saveDiscussion = new JMenuItem("Save receiver...");
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

        file.addSeparator();

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new quitMenuItemListener());
        file.add(quit);

        return menuBar;
    }

    public static KasMessage getMessageData(){
        return new KasMessage(nameTextField.getText(), receiverIPTextField.getText(), messageTextArea.getText());
    }

    public static void clearMessage(){
        messageTextArea.setText("");
        receiverIPTextField.setText("");
    }

}
