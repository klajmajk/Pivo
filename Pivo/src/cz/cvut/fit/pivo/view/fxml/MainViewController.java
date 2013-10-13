/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    private NumberAxis xAxis;
    @FXML
    private LineChart<Number, Number> lineChart;
    IModel model;
    IController controller;
    LineChart.Series<Number, Number> sensor1Series;
    LineChart.Series<Number, Number> sensor2Series;
    LineChart.Series<Number, Number> recipeSeries;
    Recipe recipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model = ViewFacadeFX.getInstanceOf().getModel();
        controller = ViewFacadeFX.getInstanceOf().getController();
        xAxis.setTickLabelFormatter(new NumberToStringConverter());
        xAxis.setForceZeroInRange(false);
        recipe = new Recipe();

        //Chart init
        resetChart();
        System.out.println("controller init");
        
        ViewFacadeFX.getInstanceOf().getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {                
                ViewFacadeFX.getInstanceOf().controllerStop();   
                //aby se to správně ukončovalo
                Platform.exit();
            }
        });


    }
    
    private void resetChart(){
        ObservableList<XYChart.Series<Number, Number>> lineChartData = FXCollections.observableArrayList();
        sensor1Series = new LineChart.Series<>();
        sensor1Series.setName("Čidlo 1");
        sensor2Series = new LineChart.Series<>();
        sensor2Series.setName("Čidlo 2");
        recipeSeries = new LineChart.Series<Number, Number>();
        recipeSeries.setName("Recept");
        lineChartData.add(sensor1Series);
        lineChartData.add(sensor2Series);
        lineChartData.add(recipeSeries);



        lineChart.setData(lineChartData);
        lineChart.createSymbolsProperty();
    }

    @FXML
    private void saveImageClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"));

        //System.out.println(pic.getId());
        File file = fileChooser.showSaveDialog(ViewFacadeFX.getInstanceOf().getStage());
        if (!file.getName().contains(".")) {
            file = new File(file.getAbsolutePath() + ".png");
        }
        try {
            ImageIO.write(getChartBufferedImage(), "png", file);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void closeClicked(ActionEvent event) {        
        Platform.exit();
    }  
   

    @FXML
    private void selectRecipe(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("\\cz\\cvut\\fit\\pivo\\view\\fxml\\recipeSelectView.fxml"));
            root = (Parent) loader.load();
            RecipeSelectViewController controller = (RecipeSelectViewController) loader.getController();
            Stage stage = new Stage();            
            controller.setStage(stage);
            stage.setTitle("Vyberte recept");
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addRecipeClicked(ActionEvent event) {
        throw new NotImplementedException();
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
                //labels change
                temperatureLabel.setText((new DecimalFormat("#.##").format((Float) model.getCurrent().getTemp())) + "°C");
                Time time = new Time(model.getCurrent().getTime().getTime() - model.getStartTime().getTime() - (60 * 60 * 1000));
                timeLabel.setText(time.toString());

                //chart change
                if (model.isRunning()) {
                    if (model.hasTwoSensors()) {
                        sensor2Series.getData().add(new XYChart.Data<Number, Number>(System.currentTimeMillis(), model.getCurrent1().getTemp()));
                    } 
                    //musi tu neco byt
                    sensor1Series.getData().add(new XYChart.Data<Number, Number>(System.currentTimeMillis(), model.getCurrent().getTemp()));
                }
                if (!recipe.equals(model.getCurrentRecipe())) {
                    reset();
                    if (model.getCurrentRecipe() != null) {
                        addRecipe();
                    }
                }
            }
        });

    }

    @Override
    public void reset() {
        System.out.println(sensor1Series.getData().isEmpty());
        resetChart();
    }

    @Override
    public void start() {        
        reset();
        addVystirka();        
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
    
    public void addVystirka() {
        //this.recipe = model.getCurrentRecipe();
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vystiraciTemp));
        millis += recipe.vystiraciTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vystiraciTemp));        

    }
    

    public void addPeptonizacni() {
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.peptonizacniTemp));
        millis += recipe.peptonizacniTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.peptonizacniTemp));
    }

    public void addNizsiCukrotvorna() {
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.nizsiCukrTemp));
        millis += recipe.nizsiCukrTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.nizsiCukrTemp));
    }

    public void addVyssiCukrotvorna() {
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vyssiCukrTemp));
        millis += recipe.vyssiCukrTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vyssiCukrTemp));
    }

    public void addOdrmutovaci() {
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.odrmutovaciTemp));
        millis += recipe.odrmutovaciTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.odrmutovaciTemp));
    }

    private void addRecipe() {
        recipe = model.getCurrentRecipe();
        //System.out.println(recipe);

        long millis = System.currentTimeMillis();

        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vystiraciTemp));
        millis += recipe.vystiraciTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vystiraciTemp));
        millis += ((long) ((recipe.peptonizacniTemp - recipe.vystiraciTemp) / recipe.tolerance)) * 60000;

        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.peptonizacniTemp));
        millis += recipe.peptonizacniTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.peptonizacniTemp));
        millis += ((long) ((recipe.nizsiCukrTemp - recipe.peptonizacniTemp) / recipe.tolerance)) * 60 * 1000;

        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.nizsiCukrTemp));
        millis += recipe.nizsiCukrTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.nizsiCukrTemp));
        millis += ((long) ((recipe.vyssiCukrTemp - recipe.nizsiCukrTemp) / recipe.tolerance)) * 60 * 1000;

        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vyssiCukrTemp));
        millis += recipe.vyssiCukrTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vyssiCukrTemp));
        millis += ((long) ((recipe.odrmutovaciTemp - recipe.vyssiCukrTemp) / recipe.tolerance)) * 60 * 1000;

        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.odrmutovaciTemp));
        millis += recipe.odrmutovaciTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.odrmutovaciTemp));

        notifyView();



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
}
