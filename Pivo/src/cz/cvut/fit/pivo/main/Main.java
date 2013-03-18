/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.main;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.model.Model;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Adam
 */
public class Main {
    public static void main(String[] args) {
        
         IModel model = new Model();      
        Controller controller = new Controller(model); 
        IView view= new ViewFacade(controller, model);
        controller.setView(view);
        view.show();
    }
}
