/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

/**
 *
 * @author Adam
 */
public class Recipe {
    //rychlost kterou se teploty zvyšují
    public float speed;
    public int vystiraciTemp;
    public int vystiraciTime;
    public int peptonizacniTemp;
    public int peptonizacniTime;
    public int nizsiCukrTemp;
    public int nizsiCukrTime;
    public int vyssiCukrTemp;
    public int vyssiCukrTime;    
    public int odrmutovaciTemp;
    public int odrmutovaciTime;

    public Recipe() {
    }

    
    
    public Recipe(float speed, int vystiraciTemp, int vystiraciTime, int peptonizacniTemp, int peptonizacniTime, int nizsiCukrTemp, int nizsiCukrTime, int vyssiCukrTemp, int vyssiCukrTime, int odrmutovaciTemp, int odrmutovaciTime) {
        this.speed = speed;
        this.vystiraciTemp = vystiraciTemp;
        this.vystiraciTime = vystiraciTime;
        this.peptonizacniTemp = peptonizacniTemp;
        this.peptonizacniTime = peptonizacniTime;
        this.nizsiCukrTemp = nizsiCukrTemp;
        this.nizsiCukrTime = nizsiCukrTime;
        this.vyssiCukrTemp = vyssiCukrTemp;
        this.vyssiCukrTime = vyssiCukrTime;
        this.odrmutovaciTemp = odrmutovaciTemp;
        this.odrmutovaciTime = odrmutovaciTime;
    }

    @Override
    public String toString() {
        return "Recipe{" + "speed=" + speed + ", vystiraciTemp=" + vystiraciTemp + ", vystiraciTime=" + vystiraciTime + ", peptonizacniTemp=" + peptonizacniTemp + ", peptonizacniTime=" + peptonizacniTime + ", nizsiCukrTemp=" + nizsiCukrTemp + ", nizsiCukrTime=" + nizsiCukrTime + ", vyssiCukrTemp=" + vyssiCukrTemp + ", vyssiCukrTime=" + vyssiCukrTime + ", odrmutovaciTemp=" + odrmutovaciTemp + ", odrmutovaciTime=" + odrmutovaciTime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Float.floatToIntBits(this.speed);
        hash = 13 * hash + this.vystiraciTemp;
        hash = 13 * hash + this.vystiraciTime;
        hash = 13 * hash + this.peptonizacniTemp;
        hash = 13 * hash + this.peptonizacniTime;
        hash = 13 * hash + this.nizsiCukrTemp;
        hash = 13 * hash + this.nizsiCukrTime;
        hash = 13 * hash + this.vyssiCukrTemp;
        hash = 13 * hash + this.vyssiCukrTime;
        hash = 13 * hash + this.odrmutovaciTemp;
        hash = 13 * hash + this.odrmutovaciTime;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if (Float.floatToIntBits(this.speed) != Float.floatToIntBits(other.speed)) {
            return false;
        }
        if (this.vystiraciTemp != other.vystiraciTemp) {
            return false;
        }
        if (this.vystiraciTime != other.vystiraciTime) {
            return false;
        }
        if (this.peptonizacniTemp != other.peptonizacniTemp) {
            return false;
        }
        if (this.peptonizacniTime != other.peptonizacniTime) {
            return false;
        }
        if (this.nizsiCukrTemp != other.nizsiCukrTemp) {
            return false;
        }
        if (this.nizsiCukrTime != other.nizsiCukrTime) {
            return false;
        }
        if (this.vyssiCukrTemp != other.vyssiCukrTemp) {
            return false;
        }
        if (this.vyssiCukrTime != other.vyssiCukrTime) {
            return false;
        }
        if (this.odrmutovaciTemp != other.odrmutovaciTemp) {
            return false;
        }
        if (this.odrmutovaciTime != other.odrmutovaciTime) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
