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
    TimeSeries series ;
    Recipe recipe;
    TimeSeriesCollection dataset;

    public GraphView(Controller controller, IModel model) {
        super(controller, model);
        this.setLayout(new java.awt.BorderLayout());
        recipe = new Recipe();

        series = new TimeSeries("Per Minute Data", Second.class);
        dataset = new TimeSeriesCollection(series);

        dataset.addSeries(series);
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Graf teploty/času",
                "Čas",
                "Teplota",
                dataset,
                true,
                true,
                false);
        ChartPanel CP = new ChartPanel(chart);
        this.add(CP, BorderLayout.CENTER);
        
        this.validate();
    }
    
    private void addRecipe(){
        Recipe recipe = model.getCurrentRecipe();
        TimeSeries recSeries = new TimeSeries("", Second.class);
        long millis =System.currentTimeMillis() ;
        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vystiraciTemp);
        millis += recipe.vystiraciTime * 60 * 1000;        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vystiraciTemp);
        millis += ((long) ((recipe.peptonizacniTemp - recipe.vystiraciTemp)/recipe.tolerance)) * 60000;  
        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.peptonizacniTemp);        
        millis += recipe.peptonizacniTime * 60 * 1000;        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.peptonizacniTemp);
        millis += ((long) ((recipe.nizsiCukrTemp - recipe.peptonizacniTemp)/recipe.tolerance)) * 60 * 1000;  
        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.nizsiCukrTemp);        
        millis += recipe.nizsiCukrTime * 60 * 1000;        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.nizsiCukrTemp);
        millis += ((long) ((recipe.vyssiCukrTemp - recipe.nizsiCukrTemp)/recipe.tolerance)) * 60 * 1000; 
        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vyssiCukrTemp);        
        millis += recipe.vyssiCukrTime * 60 * 1000;        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.vyssiCukrTemp);
        millis += ((long) ((recipe.odrmutovaciTemp - recipe.vyssiCukrTemp)/recipe.tolerance)) * 60 * 1000; 
        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.odrmutovaciTemp);        
        millis += recipe.odrmutovaciTime * 60 * 1000;        
        recSeries.addOrUpdate(new Second(new Date(millis)), recipe.odrmutovaciTemp);
        
        
        try{
            dataset.removeSeries(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
            
        }
        dataset.addSeries(recSeries);
        this.recipe = recipe;
        
    }

    @Override
    public void notifyView() {
        Second sec = new Second(model.getCurrent().getTime());        
        series.addOrUpdate(sec, model.getCurrent().getTemp());
        if(!recipe.equals(model.getCurrentRecipe())){
            addRecipe();
        }
    }
    
    public void reset(){
        dataset.removeSeries(1);
        dataset.getSeries(0).clear();
    }
}