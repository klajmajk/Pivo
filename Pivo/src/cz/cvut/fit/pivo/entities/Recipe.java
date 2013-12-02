/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam
 */
public class Recipe implements Serializable{
    //rychlost kterou se teploty zvyšují
    private String name;
    private float speed; 
    private List<Rest> rests;
    private Rest activeRest;
    

    private static final long serialVersionUID = 7526471155622776148L;

    public Recipe() {
        this.rests = new ArrayList<>();
    }    
    
    
    
    public Recipe(String name, float speed, List<Rest> rests) {
        this.name = name;
        this.speed = speed;
        this.rests = rests;
        activeRest = rests.get(0);
    }    
    
    public Rest getPrecidingNonDecoction(Rest rest){
        int i = rests.indexOf(rest)-1;
        Rest result = rests.get(i);
        while (result.isDecoction()) {
            i--;
            result = rests.get(i);
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public float getSpeed() {
        return speed;
    }

    public List<Rest> getRests() {
        return rests;
    }

    public Rest getActiveRest() {
        return activeRest;
    }
    
    public void moveToNextRest(){
        activeRest = rests.get(1 + rests.indexOf(activeRest));
    }
    
    public boolean hasNextRest(){
        if(rests.size()==1 + rests.indexOf(activeRest)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Recipe{" + "name=" + name + ", speed=" + speed + ", rests=" + rests + ", activeRest=" + activeRest + '}';
    }
    
    
    
}
