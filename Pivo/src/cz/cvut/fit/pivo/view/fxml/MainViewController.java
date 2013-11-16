/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml;

import cz.cvut.fit.pivo.other.NumberToStringConverter;
import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Settings;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import cz.cvut.fit.pivo.view.chart.IChart;
import cz.cvut.fit.pivo.view.chart.MyChart;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class MainViewController implements IInitializableView {

    @FXML
    private Label temperatureLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private Circle heatingIndicator;
    IModel model;
    IController controller;
    Recipe recipe;
    private Stage stage;
    IChart chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        model = ViewFacadeFX.getInstanceOf().getModel();  

        controller = ViewFacadeFX.getInstanceOf().getController();
        recipe = new Recipe();
        //Chart init
        chart = new MyChart(lineChart);
        System.out.println("controller init");

        ViewFacadeFX.getInstanceOf().getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                closeClicked(new ActionEvent());
            }
        });


    }

    private void saveWindowParameters() {
        Settings settings = ViewFacadeFX.getInstanceOf().getModel().getSettings();
        settings.setWindowSizeWidth(((Double) stage.getWidth()).intValue());
        settings.setWindowsSizeHeigth(((Double) stage.getHeight()).intValue());
        settings.setWindowsX(((Double) stage.getX()).intValue());
        settings.setWindowsY(((Double) stage.getY()).intValue());
    }

    public void setWindowSizeAndPos() {

        stage.setWidth(model.getSettings().getWindowSizeWidth());
        stage.setHeight(model.getSettings().getWindowsSizeHeigth());
        stage.setX(model.getSettings().getWindowsX());
        stage.setY(model.getSettings().getWindowsY());
    }

    

    @FXML
    private void saveImageClicked(ActionEvent event) {
        controller.saveGraph(getChartBufferedImage());
    }

    @FXML
    private void closeClicked(ActionEvent event) {
        saveWindowParameters();   
        ViewFacadeFX.getInstanceOf().getController().applicationExit();
        Platform.exit();
    }

    @FXML
    private void selectRecipe(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("\\cz\\cvut\\fit\\pivo\\view\\fxml\\recipeSelectView.fxml"));
            root = (Parent) loader.load();
            RecipeSelectViewController selectRecipeController = (RecipeSelectViewController) loader.getController();
            Stage stage = new Stage();
            selectRecipeController.setStage(stage);
            stage.setTitle("Vyberte recept");
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void startClicked(ActionEvent event) {
        System.out.println("start");
        ViewFacadeFX.getInstanceOf().controllerStart();
    }

    @FXML
    private void stopClicked(ActionEvent event) {
        ViewFacadeFX.getInstanceOf().controllerStop();
    }

    @FXML
    private void resetClicked(ActionEvent event) {
        ViewFacadeFX.getInstanceOf().controllerReset();
    }

    @Override
    public void notifyView() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                handleNotifyLabels();
                handleNotifyChart();
            }

            private void handleNotifyLabels() {
                //labels change
                temperatureLabel.setText((new DecimalFormat("#.##").format((Float) model.getCurrent().getTemp())) + "°C");
                Time time = new Time(model.getCurrent().getTime().getTime() - model.getStartTime().getTime() - (60 * 60 * 1000));
                timeLabel.setText(time.toString());
            }

            private void handleNotifyChart() {
                //chart change
                if (model.isRunning()) {
                    if (model.hasTwoSensors()) {
                        chart.series2Add(model.getCurrent1().getTemp());
                    }
                    //musi tu neco byt
                    chart.series1Add(model.getCurrent().getTemp());
                }
                if (!recipe.equals(model.getCurrentRecipe())) {
                    reset();
                    recipe = model.getCurrentRecipe();
                    if (recipe != null) {
                        
                        chart.addRecipe(recipe);
                        controller.notifyView();
                    }
                }
            }
        });

    }
    
    public void setHeating(boolean heat){
        if(heat) heatingIndicator.setFill(Color.RED);
        else heatingIndicator.setFill(Color.GRAY);
    }

    @Override
    public void reset() {
        chart.resetChart();
    }

    @Override
    public void start() {
        reset();
        chart.addNext(recipe);
        //TODO zamknout volby receptu
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BufferedImage getChartBufferedImage() {
        WritableImage image = lineChart.snapshot(new SnapshotParameters(), null);
        return SwingFXUtils.fromFXImage(image, null);
    }    

    public void textOutput(String output) {
        final String out = output;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText(out);
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void drawNextPartOfRecipe() {
        chart.addNext(recipe);
    }
    
    
    
}
