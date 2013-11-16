/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.chart;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Rest;

/**
 *
 * @author Adam
 */
public interface IChart {

    public long addRest(Rest rest, long millis);

    public void addNext(Recipe recipe);

    public void addRecipe(Recipe recipe);

    public void resetChart();

    public void series1Add(float temp);

    public void series2Add(float temp);
}
