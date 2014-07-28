/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks;

import br.com.parks.entity.OLT;
import br.com.parks.entity.ONU;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiago
 */
public class FlowProfilePanel extends javax.swing.JPanel {

    private final JTabbedPane panel;
    private OLT olt;
    private ONU onu;

    /**
     * Creates new form FlowProfilePanel
     *
     * @param panel
     */
    public FlowProfilePanel(JTabbedPane panel) {
        this.panel = panel;
        initComponents();

    }

    public FlowProfilePanel(JTabbedPane panel, OLT olt) {
        this.panel = panel;
        this.olt = olt;
        initComponents();
        displayFlowNameProfileTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spFlowItem = new javax.swing.JScrollPane();
        tbFlowItem = new javax.swing.JTable();
        txtFlowName = new javax.swing.JTextField();
        btAddFlowName = new javax.swing.JButton();
        spFlowName = new javax.swing.JScrollPane();
        tbFlowName = new javax.swing.JTable();
        cbVlan = new javax.swing.JComboBox();
        lbVlan = new javax.swing.JLabel();
        cbEncryption = new javax.swing.JComboBox();
        lbFlowType = new javax.swing.JLabel();
        btAddFlow = new javax.swing.JButton();
        cbFlowType = new javax.swing.JComboBox();
        lbEncryption = new javax.swing.JLabel();
        cbService = new javax.swing.JComboBox();
        lbService = new javax.swing.JLabel();
        btRemoveFlow = new javax.swing.JButton();
        btClose1 = new javax.swing.JButton();

        tbFlowItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDX", "Type", "VLAN", "COS", "Encryption", "DS", "BName", "PBMP Ports"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFlowItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFlowItemMouseClicked(evt);
            }
        });
        spFlowItem.setViewportView(tbFlowItem);
        if (tbFlowItem.getColumnModel().getColumnCount() > 0) {
            tbFlowItem.getColumnModel().getColumn(0).setMinWidth(40);
            tbFlowItem.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        btAddFlowName.setText("Add");

        tbFlowName.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFlowName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFlowNameMouseClicked(evt);
            }
        });
        spFlowName.setViewportView(tbFlowName);
        if (tbFlowName.getColumnModel().getColumnCount() > 0) {
            tbFlowName.getColumnModel().getColumn(0).setMinWidth(40);
            tbFlowName.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        lbVlan.setText("Vlan:");

        lbFlowType.setText("FType:");

        btAddFlow.setForeground(new java.awt.Color(1, 139, 4));
        btAddFlow.setText("Add Flow");

        lbEncryption.setText("Enc.:");

        lbService.setText("Serv.:");

        btRemoveFlow.setForeground(new java.awt.Color(227, 3, 3));
        btRemoveFlow.setText("Remove Flow");

        btClose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/DeleteRed2.png"))); // NOI18N
        btClose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClose1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spFlowItem)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbEncryption)
                                        .addGap(24, 24, 24)
                                        .addComponent(cbEncryption, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbFlowType)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbFlowType, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbVlan)
                                    .addComponent(lbService))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbVlan, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 76, Short.MAX_VALUE))
                                    .addComponent(cbService, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btAddFlow)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btRemoveFlow)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(spFlowName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtFlowName, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btAddFlowName)))
                            .addComponent(btClose1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spFlowItem, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFlowType)
                            .addComponent(lbVlan)
                            .addComponent(cbVlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFlowType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbEncryption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEncryption)
                            .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbService)))
                    .addComponent(spFlowName, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFlowName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAddFlowName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btAddFlow)
                        .addComponent(btRemoveFlow))
                    .addComponent(btClose1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btClose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClose1ActionPerformed
        panel.remove(panel.indexOfComponent(this));
    }//GEN-LAST:event_btClose1ActionPerformed

    private void tbFlowNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFlowNameMouseClicked
        if (olt != null) {
            cleanTableResultsFlowItem();
            int row = tbFlowName.getSelectedRow();
            List<String> flows = new ArrayList<String>();
            String f = tbFlowName.getModel().getValueAt(row, 1).toString();
            for (String flow : olt.getFlowProfiles()) {
                if (flow.contains(f)) {
                    flows.add(flow);
                }
            }
            displayFlowItemProfileTable(flows);
        }
    }//GEN-LAST:event_tbFlowNameMouseClicked

    private void tbFlowItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFlowItemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFlowItemMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddFlow;
    private javax.swing.JButton btAddFlowName;
    private javax.swing.JButton btClose1;
    private javax.swing.JButton btRemoveFlow;
    private javax.swing.JComboBox cbEncryption;
    private javax.swing.JComboBox cbFlowType;
    private javax.swing.JComboBox cbService;
    private javax.swing.JComboBox cbVlan;
    private javax.swing.JLabel lbEncryption;
    private javax.swing.JLabel lbFlowType;
    private javax.swing.JLabel lbService;
    private javax.swing.JLabel lbVlan;
    private javax.swing.JScrollPane spFlowItem;
    private javax.swing.JScrollPane spFlowName;
    private javax.swing.JTable tbFlowItem;
    private javax.swing.JTable tbFlowName;
    private javax.swing.JTextField txtFlowName;
    // End of variables declaration//GEN-END:variables

    private void displayFlowItemProfileTable(List<String> flow) {
        DefaultTableModel dtm = (DefaultTableModel) tbFlowItem.getModel();
        for (String r : flow) {
            String[] rfp = r.split(",");
            dtm.addRow(new String[]{rfp[1], rfp[2], rfp[3], rfp[4], rfp[5], rfp[6], rfp[7], rfp.length==9 ? rfp[8] : ""});
        }
        tbFlowItem.setModel(dtm);
    }

    private void displayFlowNameProfileTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbFlowName.getModel();
        int count = 0;
        cleanTableResultsFlowName();
        String fname = "";
        for (String r : olt.getFlowProfiles()) {
            String[] rfnp = r.split(",");
            if (!rfnp[0].equals(fname)) {
                count++;
                dtm.addRow(new String[]{Integer.toString(count), rfnp[0]});
            }
            fname = rfnp[0];
        }
        tbFlowName.setModel(dtm);
        count = 0;
    }

    private void cleanTableResultsFlowItem() {
        DefaultTableModel dtm = (DefaultTableModel) tbFlowItem.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

    }

    private void cleanTableResultsFlowName() {
        DefaultTableModel dtm = (DefaultTableModel) tbFlowName.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
}
