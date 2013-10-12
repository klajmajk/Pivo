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

    void addNizsiCukrotvorna();

    void addOdrmutovaci();

    void addPeptonizacni();

    void addVyssiCukrotvorna();

    void addVystirka();

    void holdTemp(String toHold, int tempToHold);

    void increaseTemp(int tempTo);

    void textOutput(String output);
    
}
