package cz.cvut.fit.pivo.swing;

import cz.cvut.fit.pivo.controller.Controller;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.AbstractView;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class GraphView extends AbstractView {
    TimeSeries series ;

    public GraphView(Controller controller, IModel model) {
        super(controller, model);
        this.setLayout(new java.awt.BorderLayout());

        series = new TimeSeries("Per Minute Data", Second.class);
        final TimeSeriesCollection dataset = new TimeSeriesCollection(series);

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

    @Override
    public void notifyView() {
        Second sec = new Second(model.getCurrent().getTime());        
        series.addOrUpdate(sec, model.getCurrent().getTemp());
    }
}