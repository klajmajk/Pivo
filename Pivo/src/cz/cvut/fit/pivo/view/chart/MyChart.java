/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.chart;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Rest;
import cz.cvut.fit.pivo.other.NumberToStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Adam
 */
public class MyChart implements IChart {

    private LineChart<Number, Number> lineChart;
    LineChart.Series<Number, Number> sensor1Series;
    LineChart.Series<Number, Number> sensor2Series;
    LineChart.Series<Number, Number> recipeSeries;
    LineChart.Series<Number, Number> recipeDecoctionSeries;

    public MyChart(LineChart<Number, Number> lineChart) {
        this.lineChart = lineChart;
        resetChart();
    }

    @Override
    public void resetChart() {
        ObservableList<XYChart.Series<Number, Number>> lineChartData = FXCollections.observableArrayList();
        ((NumberAxis) lineChart.getXAxis()).setTickLabelFormatter(new NumberToStringConverter());
        ((NumberAxis) lineChart.getXAxis()).setForceZeroInRange(false);
        sensor1Series = new LineChart.Series<>();
        sensor1Series.setName("Čidlo 1");
        sensor2Series = new LineChart.Series<>();
        sensor2Series.setName("Čidlo 2");
        recipeSeries = new LineChart.Series<>();
        recipeSeries.setName("Recept");
        recipeDecoctionSeries = new LineChart.Series<>();
        recipeDecoctionSeries.setName("Rmuty");


        lineChartData.add(recipeDecoctionSeries);
        lineChartData.add(recipeSeries);
        lineChartData.add(sensor1Series);
        lineChartData.add(sensor2Series);
        lineChart.setCursor(Cursor.CROSSHAIR);
        lineChart.setAnimated(false);
        lineChart.setData(lineChartData);
        lineChart.createSymbolsProperty();
    }

    @Override
    public void addNext(Recipe recipe) {
        long millis = System.currentTimeMillis();
        if (!recipe.getActiveRest().isDecoction()) {
            recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.getActiveRest().getTemp()));
            millis += recipe.getActiveRest().getLength() * 60 * 1000;
            recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.getActiveRest().getTemp()));
        }
        else{
            recipeDecoctionSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.getActiveRest().getTemp()));
            millis += recipe.getActiveRest().getLength() * 60 * 1000;
            recipeDecoctionSeries.getData().add(new XYChart.Data<Number, Number>(millis, recipe.getActiveRest().getTemp()));
        }
    }

    @Override
    public long addRest(Rest rest, long millis) {
        if (rest.isDecoction()) {
            return addToDecoctionSeries(rest, millis);
        } else {
            return addToInfusionSeries(rest, millis);
        }
    }

    private long addToInfusionSeries(Rest rest, long millis) {
        recipeSeries.getData().add(getDataToAdd(millis, rest.getTemp()));
        millis += rest.getLength() * 60 * 1000;
        recipeSeries.getData().add(getDataToAdd(millis, rest.getTemp()));
        return millis;
    }

    private long addToDecoctionSeries(Rest rest, long millis) {
        recipeDecoctionSeries.getData().add(getDataToAdd(millis, rest.getTemp()));
        millis += rest.getLength() * 60 * 1000;
        recipeDecoctionSeries.getData().add(getDataToAdd(millis, rest.getTemp()));
        return millis;
    }

    private XYChart.Data<Number, Number> getDataToAdd(long millis, double temp) {
        XYChart.Data<Number, Number> data = new XYChart.Data<Number, Number>(millis, temp);
        data.setNode(new HoveredThresholdNode(millis, temp));
        return data;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        resetChart();
        long millis = System.currentTimeMillis();
        Rest prev = null;
        for (Rest rest : recipe.getRests()) {
            if (prev != null) {
                //leze to pomalu jen když neplati ze predchozi je dekoce a rest neni
                if (prev.isDecoction() && !rest.isDecoction()) {
                    decoctionEnd(rest, prev, millis, recipe);
                } else {
                    if (!prev.isDecoction() && rest.isDecoction()) {
                        decoctionStart(prev, millis);
                    }
                    millis += (Math.abs(rest.getTemp() - prev.getTemp()) * 1000 * 60);
                }
            }
            millis = addRest(rest, millis);
            prev = rest;
        }

    }

    @Override
    public void series2Add(float temp) {
        sensor2Series.getData().add(new XYChart.Data<Number, Number>(System.currentTimeMillis(), temp));
    }

    @Override
    public void series1Add(float temp) {
        sensor1Series.getData().add(new XYChart.Data<Number, Number>(System.currentTimeMillis(), temp));
    }

    private void decoctionEnd(Rest rest, Rest prev, long millis, Recipe recipe) {
        recipeDecoctionSeries.getData().add(new XYChart.Data<Number, Number>(millis - 1, rest.getTemp()));
        recipeSeries.getData().add(new XYChart.Data<Number, Number>(millis - 1,
                recipe.getPrecidingNonDecoction(prev).getTemp()));
    }

    private void decoctionStart(Rest prev, long millis) {
        recipeDecoctionSeries.getData().add(new XYChart.Data<Number, Number>(millis - 1, prev.getTemp()));
    }
}
