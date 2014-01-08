/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.pivo.entities;

/**
 *
 * @author Adam
 */
public class Heater {
    private boolean heating;
    private int heaterOutput;

    public Heater() {        
        this.heating = false;
    }   

    public boolean isHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    
    public int getHeaterOutput(float currentTemp, float desiredTemp) {
        
        /*boolean heatMore = false;
        heatMore = currentTemp<desiredTemp;
        System.out.println(currentTemp+":"+desiredTemp+":"+heatMore);
        float difference = Math.abs(desiredTemp-currentTemp);
        //o kolik procent se upravi vykon
        int toChange = 0;
        if(difference > Settings.getTempTolerance()){
            toChange = (int) Math.ceil(difference/Settings.getTempTolerance()) * 10;
        }
        if(heatMore) heaterOutput += toChange;
        else heaterOutput -= toChange;
        if(heaterOutput < 0) heaterOutput = 0;
        if(heaterOutput == 0) setHeating(false);
        else setHeating(true);
        if(heaterOutput >100) heaterOutput = 100;
        System.out.println("heatMore:"+ heatMore+" diference: "+ difference+" toChange: "+ toChange+" heaterOutput: "+ heaterOutput);*/
        
        float output = desiredTemp;
        float difference = desiredTemp - currentTemp;
        int toChange = (int) Math.ceil(difference/Settings.getTempTolerance()) * 10;
        heaterOutput = (int) (output + (float)toChange);
        if(heaterOutput < 0) heaterOutput = 0;
        if(heaterOutput == 0) setHeating(false);
        else setHeating(true);
        if(heaterOutput >100) heaterOutput = 100;
        return heaterOutput;
    }
    
    
}
