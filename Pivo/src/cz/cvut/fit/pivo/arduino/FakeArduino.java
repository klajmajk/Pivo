/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.arduino;

import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam
 */
public class FakeArduino implements IArduino {

    float infusionTemp;
    float decoctionTemp;

    public FakeArduino() {
        this.decoctionTemp = 40;
        this.infusionTemp = 40;
    }

    @Override
    public List<TempTime> getTemp() {
        /*List<PinOneWire> pinList = new LinkedList<>();
         TempTime tempTime = new TempTime(temp);
         temp += 0.1;
         TempTime tempTime1 = new TempTime(-1);
         List<TempTime> list = new ArrayList<>();
         list.add(tempTime);
         list.add(tempTime1);
         return list;*/
        IModel model = ViewFacadeFX.getInstanceOf().getModel();
        if (model.getKettle(true).isHeating()) {
            infusionTemp++;
        } else {
            infusionTemp--;
        }
        if(model.getCurrentRecipe().getActiveRest().isDecoction()){
            if (model.getKettle(false).isHeating()) {
                decoctionTemp++;
            } else {
                decoctionTemp--;
            }
        }
        List<TempTime> list = new ArrayList<>();
        TempTime tempTimeInfusion = new TempTime(infusionTemp);
        TempTime tempTimeDecoction = new TempTime(decoctionTemp);
        list.add(tempTimeInfusion);
        list.add(tempTimeDecoction);
        return list;
    }
}
