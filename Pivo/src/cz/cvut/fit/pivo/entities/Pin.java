/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

/**
 *
 * @author Adam
 */
public class Pin {
    private boolean input;
    private boolean on;
    private int status;
    private float value;
    private int inputPin;

    public Pin() {
    }
    
    

    public Pin(boolean input, boolean on, int status, float value, int inputPin) {
        this.input = input;
        this.on = on;
        this.status = status;
        this.value = value;
        this.inputPin = inputPin;
    } 
    
    
    public boolean isInput() {
        return input;
    }

    public void setInput(boolean input) {
        this.input = input;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getInputPin() {
        return inputPin;
    }

    public void setInputPin(int inputPin) {
        this.inputPin = inputPin;
    }
    
    
}
