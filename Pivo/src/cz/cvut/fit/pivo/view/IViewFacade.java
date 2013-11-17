/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view;

/**
 *
 * @author Adam
 */
public interface IViewFacade extends IView {

    void holdTemp(String toHold, float tempToHold);

    void increaseTemp(double tempTo);

    void textOutput(String output);
    void setHeatingInfusion(boolean heat);
    void drawNextPartOfRecipe();
}
