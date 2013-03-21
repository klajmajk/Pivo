/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.swing;

import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.AbstractView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Adam
 */
public class RecipeSelectView extends AbstractView {

    /**
     * Creates new form RecipeSelectView
     */
    public RecipeSelectView(Controller controller, IModel model) {
        super(controller, model);
        initComponents();
        addMyMouseEventListener();
        jList1.setCellRenderer(new JListComponentPanel());

    }

    void addMyMouseEventListener() {
        MouseListener mouseListener = new MouseAdapter() {

            public void mouseReleased(MouseEvent mouseEvent) {
                mouseEventHappend(mouseEvent);
            }
        };
        jList1.addMouseListener(mouseListener);
    }

    void mouseEventHappend(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        int index = theList.locationToIndex(mouseEvent.getPoint());
        if (mouseEvent.isPopupTrigger()) {
            DeletePopUp menu = new DeletePopUp(controller, (Recipe)jList1.getModel().getElementAt(index));
            menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
        model.setCurrentRecipe((Recipe)jList1.getSelectedValue());
        controller.notifyView();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        jScrollPane1.setViewportView(jTree1);

        jList1.setModel(new RecipeListModel(model.getRecipes()));
        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void notifyView() {
        System.out.println("Notyfi selectview");
        RecipeListModel model = (RecipeListModel)jList1.getModel();
        model.fireContentsChanged(model, 0, model.getSize());
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
