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
import cz.cvut.fit.pivo.view.fxml.RecipeSelectViewController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
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
        super(controller,model);
        
        singleton = this;
    }
    
    public IModel getModel(){
        return this.model;
    }
    public IController getController(){
        return this.controller;
    }
    public Stage getStage(){
        return stage;
    }
    
    public static ViewFacadeFX getInstanceOf(){
        return singleton;
    }
    
    
    public void start(Stage stage) throws Exception {
        
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = (Pane) fxmlLoader.load(getClass().getResource(".\\fxml\\mainView.fxml").openStream());
        mainViewController = (MainViewController) fxmlLoader.getController();        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(p, screenBounds.getWidth()-40, screenBounds.getHeight()-40);
        stage.setMinHeight(Constants.MIN_WINDOW_SIZE_HEIGHT);
        stage.setMinWidth(Constants.MIN_WINDOW_SIZE_WIDTH);       
   
        stage.setScene(scene);
        stage.show();
        //aby se to spravne vyplo
        /*scene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
        public void handle(WindowEvent ev) {
            controllerStop();
        }
        });*/
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
    public void textOutput(String output){
        mainViewController.textOutput(output);
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
        mainViewController.addVystirka();        
    }
    
    @Override
    public void addPeptonizacni(){
        mainViewController.addPeptonizacni();
    }
    @Override
    public void addNizsiCukrotvorna(){
        mainViewController.addNizsiCukrotvorna();
    }
    
    @Override
    public void addVyssiCukrotvorna(){
        mainViewController.addVyssiCukrotvorna();
    }
    
    @Override
    public void addOdrmutovaci(){
        mainViewController.addOdrmutovaci();
                
    }

    @Override
    public void start() {
        mainViewController.start();
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void controllerStart(){
        controller.startCooking();
    }
    
    public void controllerStop(){
        controller.stopCooking();
    }
    
    public void controllerReset(){
        controller.resetCooking();
    }

   
    
}
