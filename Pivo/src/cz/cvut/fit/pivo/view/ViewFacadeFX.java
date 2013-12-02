/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view;

import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.fxml.MainViewController;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author honza
 */
public class ViewFacadeFX extends AbstractView implements IViewFacade {

    private static ViewFacadeFX singleton;
    private MainViewController mainViewController;
    private Stage stage;

    public ViewFacadeFX(Controller controller, IModel model) {
        super(controller, model);
        singleton = this;
    }

    public IModel getModel() {
        return this.model;
    }

    public IController getController() {
        return this.controller;
    }

    public Stage getStage() {
        return stage;
    }
  

    

    public static ViewFacadeFX getInstanceOf() {
        return singleton;
    }

    public void start(Stage stage) throws Exception {

        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = (Pane) fxmlLoader.load(getClass().getResource(".\\fxml\\mainView.fxml").openStream());
        mainViewController = (MainViewController) fxmlLoader.getController();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(p, screenBounds.getWidth() - 40, screenBounds.getHeight() - 40);
        stage.setMinHeight(Constants.MIN_WINDOW_SIZE_HEIGHT);
        stage.setMinWidth(Constants.MIN_WINDOW_SIZE_WIDTH);
        mainViewController.setStage(stage);
        stage.setScene(scene);

        stage.show();
        mainViewController.setWindowSizeAndPos();

        System.out.println("pane");
        
        //aby se to spravne vyplo
        /*scene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
         public void handle(WindowEvent ev) {
         controllerStop();
         }
         });*/
    }

    @Override
    public void showInformationDialog(final String text) {
        FutureTask dialogThread;       
        dialogThread = new FutureTask(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                Dialogs.showInformationDialog(stage, text);
                return "";//To change body of generated methods, choose Tools | Templates.
            }
        });
        
        Platform.runLater(dialogThread);
        try {
            System.out.println("cekam");
            dialogThread.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(ViewFacadeFX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ViewFacadeFX.class.getName()).log(Level.SEVERE, null, ex);
        }        
        System.out.println("docekano");
        
        
       
    }

    public void show(Stage stage) throws Exception {
        start(stage);
    }

    @Override
    public void notifyView() {
        /*currentView.notifyView();
         graphView.notifyView();
         recipeSelectView.notifyView();*/
        mainViewController.notifyView();
    }

    @Override
    public void reset() {
        //recipeSelectView.reset();
        mainViewController.reset();
    }

    @Override
    public void textOutput(String output) {
        mainViewController.textOutput(output);
    }

    @Override
    public void increaseTemp(double tempTo, boolean infusion) {
        if(infusion)
            textOutput("Zvyšujte teplotu na vystírací pánvi na   " + tempTo + "°C");
        else             
            textOutput("Zvyšujte teplotu na rmutovací pánvi na   " + tempTo + "°C");
    }

    @Override
    public void holdTemp(String toHold, float tempToHold) {
        textOutput("Probíhá " + toHold + " při teplotě " + tempToHold + "°C");
    }

    @Override
    public void start() {
        mainViewController.start();
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void controllerStart() {
        controller.startCooking();
    }

    public void controllerStop() {
        controller.stopCooking();
    }

    public void controllerReset() {
        controller.resetCooking();
    }

    @Override
    public void setHeating(boolean heat, boolean infusion) {
        if(infusion)
            mainViewController.setHeatingInfusion(heat);
        else 
            mainViewController.setHeatingDecoction(heat);
    }

    @Override
    public void drawNextPartOfRecipe() {
        mainViewController.drawNextPartOfRecipe();
    }

    public void brewingEnd() {
        textOutput("Recept byl úspěšně dokončen.");
    }

    public BufferedImage getChartImage(){
        return mainViewController.getChartBufferedImageNonblocking();
    }
    
}
