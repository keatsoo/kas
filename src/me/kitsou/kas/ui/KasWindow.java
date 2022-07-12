package me.kitsou.kas.ui;

import me.kitsou.kas.ui.menuBarListeners.ConnectMenuListener;
import me.kitsou.kas.ui.menuBarListeners.MyIPListener;
import me.kitsou.kas.ui.menuBarListeners.SaveMenuItemListener;
import me.kitsou.kas.ui.sendPanelListeners.CancelBtnListener;
import me.kitsou.kas.ui.sendPanelListeners.SendBtnListener;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KasWindow extends JFrame {

    private static JTextField nameTextField = new JTextField("", 15);
    private static JTextField receiverIPTextField = new JTextField("", 15);
    private static JTextArea messageTextArea = new JTextArea("", 7, 15);

    private static ArrayList<KasMessage> messagesArray = new ArrayList<>();
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
        sendingPanel.add(messageTextArea, messTextAreaConstr);

        JLabel errorLabel = new JLabel();
        errorLabel.setSize(180,20);
        GridBagConstraints errorLabelConstr = new GridBagConstraints();
        errorLabelConstr.gridx = 1;
        errorLabelConstr.gridy = 7;
        errorLabelConstr.gridwidth = 2;
        sendingPanel.add(errorLabel,errorLabelConstr);

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
        contentPane.add(messagesPanel, messPanConstr);
        //messagesPanel.add(Box.createRigidArea(new Dimension(80, 300)));

        revalidate();
        repaint();

        // MESSAGES PANEL VIEWPORT
        JScrollPane messScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        messagesPanel.add(messScrollPane);
        JPanel messagesContainer = new JPanel();
        messagesContainer.setLayout(new BoxLayout(messagesContainer, BoxLayout.PAGE_AXIS));
        messagesPanel.add(messagesContainer);

        // ADD MESSAGES
        boolean noMessMess = false;
        JLabel noMessLabel = new JLabel("Sorry, there is no message yet :(");
        int printedNb = 0;
        while (messagesArray.size() < 20){
            if(messagesArray.isEmpty()){
                if(!noMessMess){
                    messagesContainer.add(noMessLabel);
                    messagesContainer.revalidate();
                    noMessMess = true;
                    System.out.println("[INFO] : No message");
                }
                System.out.println("[INFO] : Messages Array is empty");
            } else {
                messagesContainer.remove(noMessLabel);
                System.out.println((printedNb < messagesArray.size()) + " cuz nb " + printedNb + " size " + messagesArray.size());
                noMessMess = false;
                if(printedNb < messagesArray.size()){
                    messagesContainer.removeAll();
                    printedNb = 0;
                    for (KasMessage messageItem : messagesArray) {
                        messagesContainer.add(new KasMessageUI(messageItem));
                        messagesContainer.revalidate();
                        System.out.println("[INFO] : Message found");
                        printedNb++;
                        System.out.println(printedNb);
                    }
                }
            }
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

        return menuBar;
    }

    public static KasMessage getMessageData(){
        return new KasMessage(nameTextField.getText(), receiverIPTextField.getText(), messageTextArea.getText());
    }

    public static void clearMessage(){
        messageTextArea.setText("");
        receiverIPTextField.setText("");
    }

    public static void addMessage(KasMessage mess){
        messagesArray.add(mess);
        System.out.println("added message.");
        System.out.println("is messagesArray not empty ?" + !messagesArray.isEmpty());
        System.out.println("messagesArray.size() = " + messagesArray.size());
    }

}
