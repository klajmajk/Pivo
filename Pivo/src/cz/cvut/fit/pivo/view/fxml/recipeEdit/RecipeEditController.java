/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml.recipeEdit;

import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Rest;
import cz.cvut.fit.pivo.entities.RestType;
import static cz.cvut.fit.pivo.entities.RestType.VAR_RMUT;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import java.util.ArrayList;
import java.util.List;

/**
 * Tohle je napul controller a napul model k recipe edit view, mel by obsahovat vsechnu bussines
 * logiku
 *
 * @author Adam
 */
public class RecipeEditController {

    List<Rest> rests;
    RestType activeRestType;
    Recipe recipe;
    RecipeEditViewController view;

    RecipeEditController(RecipeEditViewController view) {
        this.view = view;
        this.rests = new ArrayList<>();
        activeRestType = RestType.VYSTIRKA;
    }

    public RestType getActiveRestType() {
        return activeRestType;
    }
    
    /**
     * tohle je tu kvuli budouci moznosti editace receptu
     * 
     * @param recipe 
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    void addRest(int time, int temp, boolean decoction) {
        Rest rest = new Rest(time, temp, decoction,
                activeRestType);
        System.out.println(rest);
        this.rests.add(rest);
        if (rest.isDecoction()) {
            view.decoctionStart();
        }
        //tohle je jen pro zobrazení nikam se to neukládá
        view.updateChart(new Recipe(null, (float) Constants.TOLERANCE, rests));

        if ((activeRestType == RestType.ODRMUTOVACI) && (!rest.isDecoction())) {
            view.setAddRestDisable(true);
        }
        //musi byt pred nastavneim na dalsi
        checkDecoctionEnd();
        activeRestType = getNextRestType(activeRestType);        
        view.initChoiceBasedOnRest(activeRestType);
    }

    private void checkDecoctionEnd() {
        if (activeRestType == VAR_RMUT) {
            view.decoctionStop();
        }
    }

    private RestType getNextRestType(RestType rt) {
        switch (rt) {
            case VAR_RMUT:
                return getLastNonDecoctionRest().getRestsType().getNext();
            default:
                if(rt.getNext() != null){                    
                    return rt.getNext();
                }else{
                    return getLastNonDecoctionRest().getRestsType().getNext();
                }
        }
    }

    private Rest getLastNonDecoctionRest() {
        int i = rests.size() - 2;
        Rest rest = rests.get(i);
        while (rest.isDecoction()) {
            i--;
            rest = rests.get(i);
        }
        return rest;
    }

    void addRecipe(String name, float tolerance) {
        Recipe recipeToAdd = new Recipe(name, tolerance, rests);
        ViewFacadeFX.getInstanceOf().getController().saveRecipe(recipeToAdd);
        ViewFacadeFX.getInstanceOf().getController().notifyView();
    }
}
