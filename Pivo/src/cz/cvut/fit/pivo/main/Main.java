/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.main;

import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.model.Model;
import cz.cvut.fit.pivo.view.IView;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;

/**
 *
 * @author Adam
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        IModel model = new Model();
        Controller controller = new Controller(model);
        IView view = new ViewFacadeFX(controller, model);
        controller.setView(view);
        try {
            ViewFacadeFX.getInstanceOf().show(stage);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

