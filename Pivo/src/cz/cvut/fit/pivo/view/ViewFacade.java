/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view;
import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.swing.CurrentView;
import cz.cvut.fit.pivo.swing.GraphView;
import cz.cvut.fit.pivo.swing.MyJFrame;
import cz.cvut.fit.pivo.swing.list.RecipeSelectView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author honza
 */
public class ViewFacade implements IViewFacade{
    private JFrame frame;
    private CurrentView currentView;
    private GraphView graphView;
    private RecipeSelectView recipeSelectView;
    private static ViewFacade singleton;

    public ViewFacade(Controller controller, IModel model) {        
        setLookandFeel();
        this.currentView=new CurrentView(controller,model); 
        this.graphView = new GraphView(controller, model);
        this.recipeSelectView = new RecipeSelectView(controller, model);   
        
        frame = new MyJFrame(controller,model, currentView, graphView, recipeSelectView);
        
        
        //setMyLayout();
        
        singleton = this;
    }
    
    
    public static ViewFacade getInstanceOf(){
        return singleton;
    }
    
    private void setLookandFeel(){
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ViewFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void show() {                 
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void notifyView() {
        currentView.notifyView();
        graphView.notifyView();
        recipeSelectView.notifyView();
    }

    @Override
    public void reset() {
        recipeSelectView.reset();
        graphView.reset();
    }
    
    
    @Override
    public void textOutput(String output){
        currentView.textOutput(output);
    }
    
    @Override
    public void increaseTemp(int tempTo){        
        textOutput("Zvyšujte teplotu na  "+ tempTo+"°C");
    }
    
    @Override
    public void holdTemp(String toHold, int tempToHold){
        
        textOutput("Probíhá "+toHold+" prodleva při teplotě "+ tempToHold+"°C");
    }
    
    @Override
    public void addVystirka(){
        graphView.addVystirka();        
    }
    
    @Override
    public void addPeptonizacni(){
        graphView.addPeptonizacni();
    }
    @Override
    public void addNizsiCukrotvorna(){
        graphView.addNizsiCukrotvorna();
    }
    
    @Override
    public void addVyssiCukrotvorna(){
        graphView.addVyssiCukrotvorna();
    }
    
    @Override
    public void addOdrmutovaci(){
        graphView.addOdrmutovaci();
                
    }

    @Override
    public void start() {
        graphView.start();
        recipeSelectView.start();
    }
    
}
