/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.controller;

import cz.cvut.fit.pivo.entities.Kettle;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.IView;
import java.awt.image.BufferedImage;

/**
 *
 * @author Adam
 */
public interface IController {
    void startCooking();
    void stopCooking();
    void resetCooking();
    void tick();
    void notifyView();
    void saveRecipe(Recipe recipe);
    void deleteRecipe(Recipe recipe);
    public void saveGraph(BufferedImage image);
    public void applicationExit();
    IView getView();
    public void setHeating(boolean heat, boolean infusion);
    public Kettle getKettle(boolean infusion);

    public void setRunningDecoction(boolean b);

    public void brewingFinished();

    public void recipeSelected(Recipe recipe);

    public void setHeatingOutput(int heating, boolean infusion);
    
}
