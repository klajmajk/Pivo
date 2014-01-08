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

    public void increaseTemp(double tempTo, boolean infusion);
    public void showInformationDialog(String text);

    void textOutput(String output);
    void drawNextPartOfRecipe();

    public void setHeating(boolean heat, boolean infusion);
    public void notifyTempsChanged(double temp1, double temp2);
    public void notifyRecipeChanged();
}
