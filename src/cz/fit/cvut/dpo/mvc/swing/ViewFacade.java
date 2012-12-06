/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.dpo.mvc.swing;

import cz.fit.cvut.dpo.mvc.controller.FacadeController;
import cz.fit.cvut.dpo.mvc.model.IModel;
import cz.fit.cvut.dpo.mvc.view.IView;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;

/**
 *
 * @author honza
 */
public class ViewFacade implements IView{
    private JFrame frame;
    private CanvasView canvas;

    public ViewFacade(FacadeController controller, IModel model) {
        this.canvas=new CanvasView(controller,model); 
        frame = new JFrame();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        LayoutManager layout = new FlowLayout(FlowLayout.CENTER);
        frame.getContentPane().setLayout(layout);
        frame.add(canvas);
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
        canvas.notifyView();
    }
    
}
