/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml;

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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
    private Label temperatureInfusionLabel;
    @FXML
    private Label temperatureDecoctionLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private Circle heatingInfusionIndicator;
    @FXML
    private Circle heatingDecoctionIndicator;
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
        System.out.println("save image clicked");
        controller.saveGraph(getChartBufferedImageBlocking());
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
                temperatureInfusionLabel.setText((new DecimalFormat("#.##").format((Float) model.getKettleTempTime(true).getTemp())) + "°C");
                temperatureDecoctionLabel.setText((new DecimalFormat("#.##").format((Float) model.getKettleTempTime(false).getTemp())) + "°C");
                Time time = new Time(model.getKettleTempTime(true).getTime().getTime() - model.getStartTime().getTime() - (60 * 60 * 1000));
                timeLabel.setText(time.toString());
            }

            private void handleNotifyChart() {
                //chart change
                if (model.isRunning()) {
                    if (model.isRunningDecoction()) {
                        chart.series2Add(model.getKettleTempTime(false).getTemp());
                    }
                    //musi tu neco byt
                    chart.series1Add(model.getKettleTempTime(true).getTemp());
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

    public void setHeatingInfusion(boolean heat) {
        if (heat) {
            heatingInfusionIndicator.setFill(Color.RED);
        } else {
            heatingInfusionIndicator.setFill(Color.GRAY);
        }
    }

    public void setHeatingDecoction(boolean heat) {
        if (heat) {
            heatingDecoctionIndicator.setFill(Color.RED);
        } else {
            heatingDecoctionIndicator.setFill(Color.GRAY);
        }
    }

    @Override
    public void reset() {
        chart.resetChart();
    }

    @Override
    public void start() {
        reset();
        if(recipe!=null)
            chart.addNext(recipe);
        //TODO zamknout volby receptu
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        public BufferedImage getChartBufferedImageNonblocking() {
        FutureTask fxThread;
        fxThread = new FutureTask(new Callable<BufferedImage>() {

            @Override
            public BufferedImage call() throws Exception {
                System.out.println("called");
                return getChartBufferedImageBlocking();
            }
        });
        Platform.runLater(fxThread);
        System.out.println("waiting");
        try {
            return (BufferedImage) fxThread.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(ViewFacadeFX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ViewFacadeFX.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("finished waitng fail");
        return null;
        

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

    private BufferedImage getChartBufferedImageBlocking() {
        return SwingFXUtils.fromFXImage(lineChart.snapshot(new SnapshotParameters(), null), null);
    }

}
