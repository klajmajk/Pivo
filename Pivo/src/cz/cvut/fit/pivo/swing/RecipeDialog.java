/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.swing;

import cz.cvut.fit.pivo.controller.Controller;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Adam
 */
class RecipeDialog extends JDialog {
    RecipeView recipeView;

    public RecipeDialog(RecipeView recipeView) {

        this.recipeView = recipeView;
        initUI();
    }

    public final void initUI() {

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(recipeView);  // (2) 

        JButton close = new JButton("Zrušit");
        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });

        close.setAlignmentX(0.3f);
        add(close);
        
        JButton add = new JButton("Přidat");
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                
                recipeView.callSaveRecipe();
            }
        });

        add.setAlignmentX(0.7f);
        add(add);

        setModalityType(ModalityType.APPLICATION_MODAL);

        setTitle("Přidání receptu");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);
        setSize(400, 500);
        //show();
    }
    
}
