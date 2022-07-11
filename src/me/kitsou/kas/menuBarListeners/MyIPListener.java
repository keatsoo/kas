package me.kitsou.kas.menuBarListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyIPListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame)(((JMenuItem)(e.getSource())).getTopLevelAncestor());
        JOptionPane.showMessageDialog(frame, "IP not implemented yet.");
    }
}
