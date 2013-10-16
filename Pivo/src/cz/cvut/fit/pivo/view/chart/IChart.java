/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.chart;

import cz.cvut.fit.pivo.entities.Recipe;

/**
 *
 * @author Adam
 */
public interface IChart {
     public void addVystirka(Recipe recipe);
    public void addPeptonizacni(Recipe recipe);
    public void addNizsiCukrotvorna(Recipe recipe);
    public void addVyssiCukrotvorna(Recipe recipe);
    public void addOdrmutovaci(Recipe recipe) ;
    public void addRecipe(Recipe recipe) ;
    public void resetChart() ;
    public void series1Add(float temp);
    public void series2Add(float temp);
}
