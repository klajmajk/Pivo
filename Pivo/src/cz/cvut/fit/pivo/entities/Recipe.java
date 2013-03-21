/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

import cz.cvut.fit.pivo.state.RecipeState;
import cz.cvut.fit.pivo.state.RecipeStateVystrika;
import cz.cvut.fit.pivo.view.IView;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Adam
 */
public class Recipe implements Serializable{
    //rychlost kterou se teploty zvyšují
    public String name;
    public float tolerance;
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

    private static final long serialVersionUID = 7526471155622776147L;
    public Recipe() {
    }

    
    
    public Recipe(String name, float speed, int vystiraciTemp, int vystiraciTime, int peptonizacniTemp, int peptonizacniTime, int nizsiCukrTemp, int nizsiCukrTime, int vyssiCukrTemp, int vyssiCukrTime, int odrmutovaciTemp, int odrmutovaciTime, IView view) {
        this.name = name;
        this.tolerance = speed;
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
        return "Recipe{" + "name=" + name + ", tolerance=" + tolerance + ", vystiraciTemp=" + vystiraciTemp + ", vystiraciTime=" + vystiraciTime + ", peptonizacniTemp=" + peptonizacniTemp + ", peptonizacniTime=" + peptonizacniTime + ", nizsiCukrTemp=" + nizsiCukrTemp + ", nizsiCukrTime=" + nizsiCukrTime + ", vyssiCukrTemp=" + vyssiCukrTemp + ", vyssiCukrTime=" + vyssiCukrTime + ", odrmutovaciTemp=" + odrmutovaciTemp + ", odrmutovaciTime=" + odrmutovaciTime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Float.floatToIntBits(this.tolerance);
        hash = 89 * hash + this.vystiraciTemp;
        hash = 89 * hash + this.vystiraciTime;
        hash = 89 * hash + this.peptonizacniTemp;
        hash = 89 * hash + this.peptonizacniTime;
        hash = 89 * hash + this.nizsiCukrTemp;
        hash = 89 * hash + this.nizsiCukrTime;
        hash = 89 * hash + this.vyssiCukrTemp;
        hash = 89 * hash + this.vyssiCukrTime;
        hash = 89 * hash + this.odrmutovaciTemp;
        hash = 89 * hash + this.odrmutovaciTime;
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (Float.floatToIntBits(this.tolerance) != Float.floatToIntBits(other.tolerance)) {
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
