/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.model;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.exceptions.ConnectionError;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author Adam
 */
public interface IModel {
    public TempTime getCurrent();    
    public TempTime getCurrent1();
    
    public boolean hasTwoSensors();    
    
    public Recipe getCurrentRecipe();
    public void setCurrentRecipe(Recipe currentRecipe);
    
    void addTempTimeReading();
    void loadProcedure();
    void refresh() throws ConnectionError;
    void start(); 
    void stop();     
    boolean isRunning(); 
    void reset();
    
    public List<Recipe> getRecipes();
    public void setRecipes(List<Recipe> recipes);
    
    public Time getStartTime() ;
}
