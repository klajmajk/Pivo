/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.swing;

import cz.cvut.fit.pivo.entities.Recipe;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Adam
 */
public class JListComponentPanel extends javax.swing.JPanel implements ListCellRenderer{
    /**
     * Creates new form JListComponentPanel
     */
    public JListComponentPanel() {
        initComponents();
        //fillIn(recipe);
    }
    
    void fillIn(Recipe recipe){
        nameLabel.setText(recipe.name);
        vystiraciLabel.setText(recipe.vystiraciTemp+"°C / "+recipe.vystiraciTime+" min");
        peptonizacniLabel.setText(recipe.peptonizacniTemp+"°C / "+recipe.peptonizacniTime+" min");
        nizsiCukrLabel.setText(recipe.nizsiCukrTemp+"°C / "+recipe.nizsiCukrTime+" min");
        vyssiCukrLabel.setText(recipe.vyssiCukrTemp+"°C / "+recipe.vyssiCukrTime+" min");
        odrmutovaciLabel.setText(recipe.odrmutovaciTemp+"°C / "+recipe.odrmutovaciTime+" min");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        vystiraciLabel = new javax.swing.JLabel();
        peptonizacniLabel = new javax.swing.JLabel();
        nizsiCukrLabel = new javax.swing.JLabel();
        vyssiCukrLabel = new javax.swing.JLabel();
        odrmutovaciLabel = new javax.swing.JLabel();

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLabel.setText("Name");

        jLabel1.setText("Vystírací:");

        jLabel2.setText("Peptonizační:");

        jLabel3.setText("Nižší cukrotvorná:");

        jLabel4.setText("Vyšší cukrotvorná:");

        jLabel5.setText("Odrmutovací:");

        vystiraciLabel.setText("jLabel7");

        peptonizacniLabel.setText("jLabel7");

        nizsiCukrLabel.setText("jLabel7");

        vyssiCukrLabel.setText("jLabel7");

        odrmutovaciLabel.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(odrmutovaciLabel)
                    .addComponent(vystiraciLabel)
                    .addComponent(vyssiCukrLabel)
                    .addComponent(nizsiCukrLabel)
                    .addComponent(peptonizacniLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(vystiraciLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(peptonizacniLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nizsiCukrLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(vyssiCukrLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(odrmutovaciLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nizsiCukrLabel;
    private javax.swing.JLabel odrmutovaciLabel;
    private javax.swing.JLabel peptonizacniLabel;
    private javax.swing.JLabel vyssiCukrLabel;
    private javax.swing.JLabel vystiraciLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        fillIn((Recipe)value);
        return this;
    }
}
