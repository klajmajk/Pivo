package cz.cvut.fit.pivo.swing;

import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.AbstractView;
import java.awt.*;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class GraphView extends AbstractView {

    TimeSeries series;
    Recipe recipe;
    TimeSeriesCollection dataset;
    TimeSeries recSeries;

    public GraphView(Controller controller, IModel model) {
        super(controller, model);
        //this.setLayout(new java.awt.BorderLayout());

        recipe = new Recipe();

        series = new TimeSeries("Naměřená teplota", Second.class);
        recSeries = new TimeSeries("Ideální teplota", Second.class);
        dataset = new TimeSeriesCollection();

        dataset.addSeries(series);
        dataset.addSeries(recSeries);
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Graf teploty/času",
                "Čas",
                "Teplota",
                dataset,
                true,
                true,
                false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setHorizontalAxisTrace(true);
        chartPanel.setVerticalAxisTrace(true);
        
        setLayout(new BorderLayout(0, 5));
        add(chartPanel, BorderLayout.CENTER);
        this.add(chartPanel);

        this.validate();       
        setVisible(true);
    }
    
     @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }
    

    public void addVystirka() {
        this.recipe = model.getCurrentRecipe();
        long millis = System.currentTimeMillis();
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vystiraciTemp);
        millis += recipe.vystiraciTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vystiraciTemp);
        /*try{
         dataset.removeSeries(1);
         }catch(Exception e){
         System.out.println(e.getMessage());
            
         }*/

    }

    public void addPeptonizacni() {
        long millis = System.currentTimeMillis();
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.peptonizacniTemp);
        millis += recipe.peptonizacniTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.peptonizacniTemp);
    }

    public void addNizsiCukrotvorna() {
        long millis = System.currentTimeMillis();
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.nizsiCukrTemp);
        millis += recipe.nizsiCukrTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.nizsiCukrTemp);
    }

    public void addVyssiCukrotvorna() {
        long millis = System.currentTimeMillis();
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vyssiCukrTemp);
        millis += recipe.vyssiCukrTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vyssiCukrTemp);
    }

    public void addOdrmutovaci() {
        long millis = System.currentTimeMillis();
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.odrmutovaciTemp);
        millis += recipe.odrmutovaciTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.odrmutovaciTemp);
    }

    private void addRecipe() {
        recipe = model.getCurrentRecipe();
        //System.out.println(recipe);

        long millis = System.currentTimeMillis();

        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vystiraciTemp);
        millis += recipe.vystiraciTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vystiraciTemp);
        millis += ((long) ((recipe.peptonizacniTemp - recipe.vystiraciTemp) / recipe.tolerance)) * 60000;

        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.peptonizacniTemp);
        millis += recipe.peptonizacniTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.peptonizacniTemp);
        millis += ((long) ((recipe.nizsiCukrTemp - recipe.peptonizacniTemp) / recipe.tolerance)) * 60 * 1000;

        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.nizsiCukrTemp);
        millis += recipe.nizsiCukrTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.nizsiCukrTemp);
        millis += ((long) ((recipe.vyssiCukrTemp - recipe.nizsiCukrTemp) / recipe.tolerance)) * 60 * 1000;

        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vyssiCukrTemp);
        millis += recipe.vyssiCukrTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vyssiCukrTemp);
        millis += ((long) ((recipe.odrmutovaciTemp - recipe.vyssiCukrTemp) / recipe.tolerance)) * 60 * 1000;

        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.odrmutovaciTemp);
        millis += recipe.odrmutovaciTime * 60 * 1000;
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.odrmutovaciTemp);

        notifyView();



    }

    @Override
    public void notifyView() {
        Second sec = new Second(model.getCurrent().getTime());
        series.addOrUpdate(sec, model.getCurrent().getTemp());
        if (!recipe.equals(model.getCurrentRecipe())) {
            reset();
            if (model.getCurrentRecipe() != null) {
                addRecipe();
            }
            //addVystirka();
        }
    }

    public void reset() {
        dataset.getSeries(1).clear();
        dataset.getSeries(0).clear();
    }

    @Override
    public void start() {
        System.out.println("Start");
        reset();
        addVystirka();
    }
}