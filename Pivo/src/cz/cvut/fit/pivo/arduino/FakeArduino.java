/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.arduino;

import cz.cvut.fit.pivo.entities.PinOneWire;
import cz.cvut.fit.pivo.entities.TempTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adam
 */
public class FakeArduino implements IArduino{
    float temp;

    public FakeArduino() {
        temp = 22;
    }
    
    

    @Override
    public List<TempTime> getTemp() throws IOException {
        List<PinOneWire> pinList = new LinkedList<>();
        TempTime tempTime = new TempTime(temp);
        temp += 0.1;
        TempTime tempTime1 = new TempTime(-1);
        List<TempTime> list = new ArrayList<>();
        list.add(tempTime);
        list.add(tempTime1);
        return list;
    }
    
}
