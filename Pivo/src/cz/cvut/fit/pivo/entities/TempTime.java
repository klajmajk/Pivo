/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

import java.sql.Time;

/**
 *
 * @author Adam
 */
public class TempTime {
    private Time time;
    private float temp;

    public TempTime(float temp) {
        this.time = new Time(System.currentTimeMillis());
        this.temp = temp;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "TempTime{" + "time=" + time + ", temp=" + temp + '}';
    }
    
    
    
}
