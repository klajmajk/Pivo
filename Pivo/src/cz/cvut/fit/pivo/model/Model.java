/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.model;

import cz.cvut.fit.pivo.arduino.Arduino;
import cz.cvut.fit.pivo.arduino.FakeArduino;
import cz.cvut.fit.pivo.arduino.IArduino;
import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Settings;
import cz.cvut.fit.pivo.entities.TempTime;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Adam
 */
public class Model implements IModel {

    private IArduino arduino;
    private Time startTime;
    private int badRequests;
    private Recipe currentRecipe;
    private Set<Recipe> recipes;
    private Set<Kettle> kettles;
    private boolean hasTwoSensors;
    private boolean isRunning;
    private Settings settings;
    //jestli běží rmut
    private boolean runningDecoction;
    

    public Model() {
        settings = new Settings();
        this.kettles = new HashSet<>();
        kettles.add(new Kettle(true));
        kettles.add(new Kettle(false));
        this.runningDecoction = false;
        reset();
        //this.current = arduino.getTemp();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isRunningDecoction() {
        return runningDecoction;
    }

    @Override
    public void setRunningDecoction(boolean runningDecoction) {
        this.runningDecoction = runningDecoction;
    }
    
    

    /**
     *
     * @return recipes
     */
    @Override
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    /**
     *
     * @param recipes
     */
    @Override
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public Settings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(Settings settings) {
        this.settings = settings;
        System.out.println(settings);
    }

    @Override
    public Time getStartTime() {
        return startTime;
    }

    @Override
    public Recipe getCurrentRecipe() {
        return currentRecipe;
    }

    @Override
    public void setCurrentRecipe(Recipe currentRecipe) {
        this.currentRecipe = currentRecipe;
        System.out.println(currentRecipe);
    }

    @Override
    public TempTime getKettleTempTime(boolean infusion) {
        //System.out.println("getTimeTemp " + infusion);
        for (Kettle kettle : kettles) {
            //System.out.println(("kettle infusion" + kettle.isInfusion()) + " infusion:" + (infusion));
            if(kettle.isInfusion()&&(infusion)) return kettle.getTempTime();
            else if ((!kettle.isInfusion()) && (!infusion))  return kettle.getTempTime();
//            if ((kettle.isInfusion()) && (infusion)) {
//                System.out.println("in in " + kettle.getTempTime());
//                return kettle.getTempTime();
//            } else if ((!kettle.isInfusion()) && (!infusion)) {
//                if (currentRecipe.getActiveRest().isDecoction()) {
//                    System.out.println("dec dec" + kettle.getTempTime());
//                    return kettle.getTempTime();
//                } else {
//                    System.out.println(getKettle(false).getTempTime());
//                    return getKettle(true).getTempTime();
//                }
//            }
        }
        return null;
    }

    @Override
    public void addTempTimeReading() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loadProcedure() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh() {
        //try {
        List<TempTime> tempTimeList = arduino.getTemp();
        for (Kettle kettle : kettles) {
            if (kettle.isInfusion()) {
                kettle.setTempTime(tempTimeList.get(0));
            } else {
                kettle.setTempTime(tempTimeList.get(1));
            }
        }
        if (tempTimeList.get(1).getTemp() != -1) {
            hasTwoSensors = true;
        } else {
            hasTwoSensors = false;
        }
        badRequests = 0;
        /*} catch (IOException ex) {
         badRequests++;
         if(badRequests == Constants.MAX_BAD_REQUESTS) {
         throw new ConnectionError("Posledních "+ Constants.MAX_BAD_REQUESTS+" skončilo timeoutem");
         }
         }*/
    }

    @Override
    public void start() {
        startTime = new Time(System.currentTimeMillis());
        isRunning = true;
    }

    @Override
    public boolean hasTwoSensors() {
        return hasTwoSensors;
    }

    @Override
    public final void reset() {
        arduino = new FakeArduino();
        badRequests = 0;
        for (Kettle kettle : kettles) {
            kettle.setTempTime(new TempTime(0));
        }
        this.startTime = new Time(0);
        currentRecipe = new Recipe();
        hasTwoSensors = false;
        isRunning = false;
        currentRecipe.reset();
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void addRecipe(Recipe recipe
    ) {
        Recipe toDelete = null;
        for (Recipe r : recipes) {
            if (r.getName().equals(recipe.getName())) {
                toDelete = r;
            }
        }
        if (toDelete != null) {
            recipes.remove(toDelete);
        }
        recipes.add(recipe);
    }

    @Override
    public Kettle getKettle(boolean infusion
    ) {
        for (Kettle kettle : kettles) {
            if ((kettle.isInfusion()) && (infusion)) {
                return kettle;
            }
            if ((!kettle.isInfusion()) && (!infusion)) {
                return kettle;
            }
        }
        return null;
    }

}
