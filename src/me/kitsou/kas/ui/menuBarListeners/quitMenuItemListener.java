package me.kitsou.kas.ui.menuBarListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class quitMenuItemListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame)(((JMenuItem)(e.getSource())).getTopLevelAncestor());
        int confirmDialog = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit ?", "Quit ?", JOptionPane.YES_NO_OPTION);
        if(confirmDialog == 0){
            System.exit(0);
        }
    }
}
