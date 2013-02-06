/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view;
import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.swing.CurrentView;
import cz.cvut.fit.pivo.swing.GraphView;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.text.TableView;

/**
 *
 * @author honza
 */
public class ViewFacade implements IView{
    private JFrame frame;
    private CurrentView currentView;
    private GraphView graphView;

    public ViewFacade(Controller controller, IModel model) {
        this.currentView=new CurrentView(controller,model); 
        this.graphView = new GraphView(controller, model);
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        LayoutManager layout = new FlowLayout(FlowLayout.CENTER);
        frame.getContentPane().setLayout(layout);
        frame.add(currentView);
        frame.add(graphView);
    }
    
    public void show() {                 
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


//    @Override
//    public void elementsCollectionUpdated(List<Element> elements) {        
//        panel.setMessage("Elements count:"+elements.size(), false);
//        
//        this.window.revalidate();
//        this.window.repaint();
//    }
//
//    @Override
//    public void notifyError(Exception exception) {
//        panel.setMessage("Error: "+ exception.getMessage(), false);
//        
//        this.window.revalidate();
//        this.window.repaint();
//    }

    @Override
    public void notifyView() {
        currentView.notifyView();
        graphView.notifyView();
    }
    
}
