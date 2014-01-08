/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.arduino;

import cz.cvut.fit.pivo.entities.Rest;
import cz.cvut.fit.pivo.entities.TempTime;
import cz.cvut.fit.pivo.model.IModel;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam
 */
public class FakeArduino implements IArduino {

    float infusionTemp;
    float decoctionTemp;
    private int heatingInfusion;
    private int heatingDecoction;

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

        Rest rest = model.getCurrentRecipe().getActiveRest();
        if ((rest == null)||(!model.isRunning())) {
            infusionTemp -= 0.1;
            decoctionTemp -= 0.1;
        } else {
            infusionTemp = handleTempChange(infusionTemp, heatingInfusion);           
            if (model.getCurrentRecipe().getActiveRest().isDecoction()) {
                decoctionTemp = handleTempChange(decoctionTemp, heatingDecoction);
               
            }
        }
        List<TempTime> list = new ArrayList<>();
        TempTime tempTimeInfusion = new TempTime(infusionTemp);
        TempTime tempTimeDecoction = new TempTime(decoctionTemp);
        list.add(tempTimeInfusion);
        list.add(tempTimeDecoction);
        return list;
    }

    @Override
    public void setHeatingOutput(int heating, boolean infusion) {
        if (infusion) this.heatingInfusion = heating;
        else this.heatingDecoction = heating;
        System.out.println("Ohřev nastaven na: "+heating+"% na vystírací pánvi: "+infusion);
    }

    private float handleTempChange(float temp, int heating) {
        float difference = heating - temp;
        return temp += (difference/60) - 0.1;
    }
}
