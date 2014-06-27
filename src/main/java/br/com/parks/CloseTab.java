/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.parks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import static sun.misc.ClassFileTransformer.add;

/**
 *
 * @author tiago
 */
public class CloseTab extends JPanel {

    JLabel title = new JLabel();
    JButton closeButton = new JButton();
    int tabIndex;
    JTabbedPane tabbedPane = null;
    public static int SELECTED_TAB_INDEX;
  

    public static void setSELECTED_TAB_INDEX(int SELECTED_TAB_INDEX) {
        CloseTab.SELECTED_TAB_INDEX = SELECTED_TAB_INDEX;
    }

   
    public void setCloseAction(ActionListener al) {
        closeButton.addActionListener(al);
        closeButton.setSize(10, 10);
        closeButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/parks/icons/delete.gif")));

    }

    public void setTabIndex(int index) {
        this.tabIndex = index;
        System.out.println(tabIndex);
    }

    public void init() {
        add(title);
        add(closeButton);
        setOpaque(false);
        setCloseAction(closeActoion);      

    }



    ActionListener closeActoion = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            // System.out.println(tabIndex);
            if(tabbedPane.getTabCount() != 0 && tabbedPane.getSelectedIndex() == SELECTED_TAB_INDEX){
                tabbedPane.remove(SELECTED_TAB_INDEX);
            }   
        }
    };

}