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

        // --------------------------- ACTUAL UI --------------------------------------

        // CONTENT PANE
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridBagLayout());

        // MENU BAR
        this.setJMenuBar(kasCreateMenuBar());

        // SENDING PANEL CREATION
        JPanel sendingPanel = new JPanel();
        GridBagConstraints sendPanelConstr = new GridBagConstraints();
        sendPanelConstr.gridx = 1;
        sendPanelConstr.gridy = 1;
        contentPane.add(sendingPanel, sendPanelConstr);
        sendingPanel.setLayout(new GridBagLayout());

        // SENDING PANEL LAYOUT
        JLabel nameLabel = new JLabel("Enter your name :");
        nameLabel.setSize(180,20);
        GridBagConstraints nameLabelConstr = new GridBagConstraints();
        nameLabelConstr.gridx = 1;
        nameLabelConstr.gridy = 1;
        nameLabelConstr.gridwidth = 2;
        sendingPanel.add(nameLabel, nameLabelConstr);

        JTextField nameTextField = new JTextField();
        nameTextField.setColumns(15);
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

        JTextField receiverIPTextField = new JTextField();
        receiverIPTextField.setColumns(15);
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

        JTextArea messageTextArea = new JTextArea();
        messageTextArea.setRows(7);
        messageTextArea.setColumns(15);
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

        // JButton cancelBtn = new JButton("Cancel");
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
}
