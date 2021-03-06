/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks;

import br.com.parks.entity.OLT;
import br.com.parks.entity.ONU;
import br.com.parks.util.ControllerOlt;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiago
 */
public class ManagerParks extends javax.swing.JFrame {

    private ControllerOlt colt;
    private OLT selectedOlt;
    private ONU selectedOnu;
    private List<ONU> cOnus;
    private boolean isConnected;

    /**
     * Creates new form MangerParks
     */
    public ManagerParks() {
        initComponents();
        colt = new ControllerOlt();

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPanel = new javax.swing.JTabbedPane();
        tabOlt = new javax.swing.JPanel();
        txtMgmtIpOlt = new javax.swing.JTextField();
        lbMgmtOlt = new javax.swing.JLabel();
        lbSerialONU = new javax.swing.JLabel();
        txtSerialOnu = new javax.swing.JTextField();
        lbUserOlt = new javax.swing.JLabel();
        txtUserOlt = new javax.swing.JTextField();
        txtMgmtOnu = new javax.swing.JTextField();
        lbPassOlt = new javax.swing.JLabel();
        txtPassOlt = new javax.swing.JPasswordField();
        lbIpMgmtOnu = new javax.swing.JLabel();
        btFindOnus = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbOnus = new javax.swing.JTable();
        btCleanTable = new javax.swing.JButton();
        btSaveConf = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        bwProfileItem = new javax.swing.JMenuItem();
        flowProfileItem = new javax.swing.JMenuItem();
        vlanTranslateProfile = new javax.swing.JMenuItem();
        multicastGoupItem = new javax.swing.JMenuItem();
        multicastOperationItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtMgmtIpOlt.setText("192.168.201.130");

        lbMgmtOlt.setForeground(new java.awt.Color(29, 23, 230));
        lbMgmtOlt.setText("Mgmt IP OLT:");

        lbSerialONU.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbSerialONU.setForeground(new java.awt.Color(7, 168, 1));
        lbSerialONU.setText("Serial ONU:");

        lbUserOlt.setForeground(new java.awt.Color(29, 23, 230));
        lbUserOlt.setText("User:");

        txtUserOlt.setText("admin");

        lbPassOlt.setForeground(new java.awt.Color(29, 23, 230));
        lbPassOlt.setText("Pass:");

        txtPassOlt.setText("parks");

        lbIpMgmtOnu.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbIpMgmtOnu.setForeground(new java.awt.Color(7, 168, 1));
        lbIpMgmtOnu.setText("or IP:");

        btFindOnus.setText("Find");
        btFindOnus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFindOnusActionPerformed(evt);
            }
        });

        tbOnus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ifPon", "IDX", "Serial", "Alias", "IP Mgmt"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbOnus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbOnusMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbOnus);
        if (tbOnus.getColumnModel().getColumnCount() > 0) {
            tbOnus.getColumnModel().getColumn(0).setMinWidth(70);
            tbOnus.getColumnModel().getColumn(0).setMaxWidth(70);
            tbOnus.getColumnModel().getColumn(1).setMinWidth(40);
            tbOnus.getColumnModel().getColumn(1).setMaxWidth(40);
            tbOnus.getColumnModel().getColumn(2).setMinWidth(110);
            tbOnus.getColumnModel().getColumn(2).setMaxWidth(110);
        }

        btCleanTable.setText("Clear");
        btCleanTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCleanTableActionPerformed(evt);
            }
        });

        btSaveConf.setText("Save Conf");
        btSaveConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveConfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabOltLayout = new javax.swing.GroupLayout(tabOlt);
        tabOlt.setLayout(tabOltLayout);
        tabOltLayout.setHorizontalGroup(
            tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabOltLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabOltLayout.createSequentialGroup()
                        .addGroup(tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbSerialONU)
                            .addComponent(lbMgmtOlt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMgmtIpOlt, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                            .addComponent(txtSerialOnu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUserOlt)
                            .addComponent(lbIpMgmtOnu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tabOltLayout.createSequentialGroup()
                                .addComponent(txtUserOlt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPassOlt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassOlt))
                            .addComponent(txtMgmtOnu)))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabOltLayout.createSequentialGroup()
                        .addComponent(btSaveConf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCleanTable, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFindOnus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabOltLayout.setVerticalGroup(
            tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabOltLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMgmtIpOlt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMgmtOlt)
                    .addComponent(lbUserOlt)
                    .addComponent(txtUserOlt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPassOlt)
                    .addComponent(txtPassOlt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSerialONU)
                    .addComponent(txtSerialOnu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMgmtOnu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbIpMgmtOnu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabOltLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btFindOnus)
                    .addComponent(btCleanTable)
                    .addComponent(btSaveConf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPanel.addTab("OLT", tabOlt);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        bwProfileItem.setMnemonic('o');
        bwProfileItem.setText("BWProfile");
        bwProfileItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bwProfileItemActionPerformed(evt);
            }
        });
        fileMenu.add(bwProfileItem);

        flowProfileItem.setMnemonic('s');
        flowProfileItem.setText("FlowProfile");
        flowProfileItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flowProfileItemActionPerformed(evt);
            }
        });
        fileMenu.add(flowProfileItem);

        vlanTranslateProfile.setText("VTProfile");
        vlanTranslateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vlanTranslateProfileActionPerformed(evt);
            }
        });
        fileMenu.add(vlanTranslateProfile);

        multicastGoupItem.setMnemonic('a');
        multicastGoupItem.setText("MCGroups");
        multicastGoupItem.setEnabled(false);
        fileMenu.add(multicastGoupItem);

        multicastOperationItem.setText("MCOperation");
        multicastOperationItem.setEnabled(false);
        fileMenu.add(multicastOperationItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPanel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        colt.disconnect();
        int opt=JOptionPane.showConfirmDialog(rootPane, "Do you like save configuration on OLT?","Save configuration?", JOptionPane.YES_OPTION);
        if(opt==0){
            btSaveConfActionPerformed(null);
        }
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void btFindOnusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFindOnusActionPerformed
        if (!txtUserOlt.getText().isEmpty() && !txtPassOlt.getText().isEmpty()) {
            cleanDisplayedResults();
            colt.setUser(txtUserOlt.getText());
            colt.setPass(txtPassOlt.getText());
            colt.setIpAccess(txtMgmtIpOlt.getText());
            colt.connect();
            selectedOlt = colt.getOlt();
            cOnus = colt.getOnus();

            if (txtSerialOnu.getText().isEmpty()) {
                displayResultOnuTable(cOnus);
            } else {
                displayResultOnuTable(colt.getOnusBySerial(txtSerialOnu.getText(), cOnus));
            }
            colt.disconnect();
        } else {
            JOptionPane.showMessageDialog(rootPane, "User or password is empty!");
        }
    }//GEN-LAST:event_btFindOnusActionPerformed

    private void btCleanTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCleanTableActionPerformed
        while(tabbedPanel.getTabCount()>1){
            tabbedPanel.remove(1);
        }
        cleanDisplayedResults();
    }//GEN-LAST:event_btCleanTableActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        colt.disconnect();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtMgmtIpOlt.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void tbOnusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbOnusMouseClicked
        if (evt.getClickCount() == 2 && cOnus != null) {
            int row = tbOnus.getSelectedRow();
            ONU onu = colt.getOnu(tbOnus.getModel().getValueAt(row, 2).toString(), cOnus);
            if (tabbedPanel.indexOfTab(onu.getAlias()) == -1 && tabbedPanel.indexOfTab(onu.getSerial()) == -1) {
                OnuPanel op = new OnuPanel(tabbedPanel, onu, selectedOlt);
                tabbedPanel.add(onu.getSerial(), op);
                tabbedPanel.setSelectedComponent(op);
                int idxt = tabbedPanel.getTabCount();
                if (onu.getAlias() != null) {
                    tabbedPanel.setTitleAt(idxt - 1, onu.getAlias());
                }
            } else {
                if(tabbedPanel.indexOfTab(onu.getAlias())!=-1){
                    tabbedPanel.setSelectedIndex(tabbedPanel.indexOfTab(onu.getAlias()));
                }else{
                    tabbedPanel.setSelectedIndex(tabbedPanel.indexOfTab(onu.getSerial()));
                }
            }

        }
    }//GEN-LAST:event_tbOnusMouseClicked

    private void bwProfileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bwProfileItemActionPerformed
        if(selectedOlt==null){
            colt.setUser(txtUserOlt.getText());
            colt.setPass(txtPassOlt.getText());
            colt.setIpAccess(txtMgmtIpOlt.getText());
            colt.connect();
            selectedOlt=colt.getOlt();
            colt.disconnect();
        }
        BWProfilePanel bwp= new BWProfilePanel(tabbedPanel,selectedOlt);
        tabbedPanel.add(bwp);
        tabbedPanel.setTitleAt(tabbedPanel.indexOfComponent(bwp),"BW Profiles");
        tabbedPanel.setSelectedComponent(bwp);
    }//GEN-LAST:event_bwProfileItemActionPerformed

    private void flowProfileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flowProfileItemActionPerformed
        if(selectedOlt==null){
            colt.setUser(txtUserOlt.getText());
            colt.setPass(txtPassOlt.getText());
            colt.setIpAccess(txtMgmtIpOlt.getText());
            colt.connect();
            selectedOlt=colt.getOlt();
            colt.disconnect();
        }
        FlowProfilePanel fpp= new FlowProfilePanel(tabbedPanel,selectedOlt);
        tabbedPanel.add(fpp);
        tabbedPanel.setTitleAt(tabbedPanel.indexOfComponent(fpp),"Flow Profiles");
        tabbedPanel.setSelectedComponent(fpp);
    }//GEN-LAST:event_flowProfileItemActionPerformed

    private void vlanTranslateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vlanTranslateProfileActionPerformed
         if(selectedOlt==null){
            colt.setUser(txtUserOlt.getText());
            colt.setPass(txtPassOlt.getText());
            colt.setIpAccess(txtMgmtIpOlt.getText());
            colt.connect();
            selectedOlt=colt.getOlt();
            colt.disconnect();
        }
        VlanTranslateProfilePanel fpp= new VlanTranslateProfilePanel(tabbedPanel,selectedOlt);
        tabbedPanel.add(fpp);
        tabbedPanel.setTitleAt(tabbedPanel.indexOfComponent(fpp),"Flow Profiles");
        tabbedPanel.setSelectedComponent(fpp);
    }//GEN-LAST:event_vlanTranslateProfileActionPerformed

    private void btSaveConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveConfActionPerformed
        if (selectedOlt!=null) {
            colt.connect();
            colt.saveConfiguration();
            colt.disconnect();
        }else{
            JOptionPane.showMessageDialog(rootPane, "Not connected yet! Click to find first before saving.");
        }
    }//GEN-LAST:event_btSaveConfActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerParks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerParks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerParks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerParks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManagerParks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btCleanTable;
    private javax.swing.JButton btFindOnus;
    private javax.swing.JButton btSaveConf;
    private javax.swing.JMenuItem bwProfileItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem flowProfileItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbIpMgmtOnu;
    private javax.swing.JLabel lbMgmtOlt;
    private javax.swing.JLabel lbPassOlt;
    private javax.swing.JLabel lbSerialONU;
    private javax.swing.JLabel lbUserOlt;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem multicastGoupItem;
    private javax.swing.JMenuItem multicastOperationItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JPanel tabOlt;
    private javax.swing.JTabbedPane tabbedPanel;
    private javax.swing.JTable tbOnus;
    private javax.swing.JTextField txtMgmtIpOlt;
    private javax.swing.JTextField txtMgmtOnu;
    private javax.swing.JPasswordField txtPassOlt;
    private javax.swing.JTextField txtSerialOnu;
    private javax.swing.JTextField txtUserOlt;
    private javax.swing.JMenuItem vlanTranslateProfile;
    // End of variables declaration//GEN-END:variables

    private void displayResultOnuTable(List<ONU> result) {
        DefaultTableModel dtm = (DefaultTableModel) tbOnus.getModel();
        for (ONU o : result) {
            dtm.addRow(new String[]{o.getIfGpon(), Integer.toString(o.getIndex()), o.getSerial(),o.getAlias(), o.getMgmtIp()});
        }
        tbOnus.setModel(dtm);
    }

    /**
     * Clean the table of ONUs result
     */
    private void cleanDisplayedResults() {
        DefaultTableModel dtm = (DefaultTableModel) tbOnus.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
}
