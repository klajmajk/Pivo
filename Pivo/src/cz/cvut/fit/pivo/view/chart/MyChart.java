/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.chart;

import cz.cvut.fit.pivo.controller.IController;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.model.IModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Adam
 */
public class MyChart implements IChart{
    
    private LineChart<Number, Number> lineChart;
    LineChart.Series<Number, Number> sensor1Series;
    LineChart.Series<Number, Number> sensor2Series;
    LineChart.Series<Number, Number> recipeSeries;

    public MyChart(LineChart<Number, Number> lineChart) {
        this.lineChart = lineChart;        
        resetChart();
    }
    
    public void resetChart() {
        ObservableList<XYChart.Series<Number, Number>> lineChartData = FXCollections.observableArrayList();
        sensor1Series = new LineChart.Series<>();
        sensor1Series.setName("Čidlo 1");
        sensor2Series = new LineChart.Series<>();
        sensor2Series.setName("Čidlo 2");
        recipeSeries = new LineChart.Series<Number, Number>();
        recipeSeries.setName("Recept");
        lineChartData.add(recipeSeries);
        lineChartData.add(sensor1Series);
        lineChartData.add(sensor2Series);
        lineChart.setAnimated(false);
        lineChart.setData(lineChartData);
        lineChart.createSymbolsProperty();
    }
    
    
    public void addVystirka(Recipe recipe) {
        //this.recipe = model.getCurrentRecipe();
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vystiraciTemp));
        millis += recipe.vystiraciTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vystiraciTemp));

    }

    public void addPeptonizacni(Recipe recipe) {
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.peptonizacniTemp));
        millis += recipe.peptonizacniTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.peptonizacniTemp));
    }

    public void addNizsiCukrotvorna(Recipe recipe) {
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.nizsiCukrTemp));
        millis += recipe.nizsiCukrTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.nizsiCukrTemp));
    }

    public void addVyssiCukrotvorna(Recipe recipe) {
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vyssiCukrTemp));
        millis += recipe.vyssiCukrTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.vyssiCukrTemp));
    }

    public void addOdrmutovaci(Recipe recipe) {
        long millis = System.currentTimeMillis();
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.odrmutovaciTemp));
        millis += recipe.odrmutovaciTime * 60 * 1000;
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.odrmutovaciTemp));
    }

    public void addRecipe(Recipe recipe) {

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

    }
    
    @Override
    public void series2Add(float temp){
        sensor2Series.getData().add(new XYChart.Data<Number, Number>(System.currentTimeMillis(), temp));
    }
    
    @Override
    public void series1Add(float temp){
        sensor1Series.getData().add(new XYChart.Data<Number, Number>(System.currentTimeMillis(), temp));
    }
}
