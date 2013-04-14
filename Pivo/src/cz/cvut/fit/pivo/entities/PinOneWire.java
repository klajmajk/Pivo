/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

/**
 *
 * @author Adam
 */
public class PinOneWire {
    private boolean input;
    private float value;
    private float value1;

    public PinOneWire() {
    }
    
    

    public PinOneWire(boolean input,float value,float value1) {
        this.input = input;
        this.value = value;
        this.value1 = value1;
    } 
    
    
    public boolean isInput() {
        return input;
    }

    public void setInput(boolean input) {
        this.input = input;
    }

    

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue1() {
        return value1;
    }

    public void setValue1(float value1) {
        this.value1 = value1;
    }

    
    
    
}
