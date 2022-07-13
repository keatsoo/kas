package me.kitsou.kas.ui.menuBarListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyIPListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame)(((JMenuItem)(e.getSource())).getTopLevelAncestor());
         try {
             JOptionPane.showMessageDialog(frame, "Your device appears as this on the network : " + InetAddress.getLocalHost());
         } catch (UnknownHostException ex){
             ex.printStackTrace();
             JOptionPane.showMessageDialog(frame, "There was an error ! Couldn't find the IP address of this device !", "Big error !", JOptionPane.ERROR_MESSAGE);
         }
    }
}
