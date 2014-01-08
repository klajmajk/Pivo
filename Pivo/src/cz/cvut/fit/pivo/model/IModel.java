/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.model;
import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Settings;
import cz.cvut.fit.pivo.entities.TempTime;
import java.sql.Time;
import java.util.Set;

/**
 *
 * @author Adam
 */
public interface IModel {
    public TempTime getKettleTempTime(boolean infusion);  
    
    public boolean hasTwoSensors();    
    
    public Recipe getCurrentRecipe();
    public void setCurrentRecipe(Recipe currentRecipe);
    
    void addTempTimeReading();
    void loadProcedure();
    void start(); 
    void stop();     
    boolean isRunning(); 
    void reset();
    
    public Set<Recipe> getRecipes();
    public void addRecipe(Recipe recipe);
    public void setRecipes(Set<Recipe> recipes);
    public Settings getSettings();
    public void setSettings(Settings settings) ;
    
    public Time getStartTime() ;

    public Kettle getKettle(boolean infusion);

    boolean isRunningDecoction();

    void setRunningDecoction(boolean runningDecoction);
    
    public Set<Kettle> getKettles();

    public void setHasTwoSensors(boolean hasTwoSensors) ;
}
