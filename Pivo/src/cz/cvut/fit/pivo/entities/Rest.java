/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

import java.io.Serializable;

/**
 *
 * Prodleva
 * @author Adam
 */
public class Rest implements Serializable{
    /**
     * doba prodlevy
     */
    private int length;
    /**
     * teplota prodlevy
     */
    private float temp;
    private boolean decoction;
    private RestType restsType;

    public Rest(int length, float temp, boolean decoction, RestType restsType) {
        this.length = length;
        this.temp = temp;
        this.decoction = decoction;
        this.restsType = restsType;
    }    

    public int getLength() {
        return length;
    }

    public float getTemp() {
        return temp;
    }

    public boolean isDecoction() {
        return decoction;
    }

    public RestType getRestsType() {
        return restsType;
    }

    @Override
    public String toString() {
        return "Rest{" + "length=" + length + ", temp=" + temp + ", decoction=" + decoction + ", restsType=" + restsType + '}';
    }
    
    
    
    
    
    
}
