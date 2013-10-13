/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.arduino;

import cz.cvut.fit.pivo.entities.TempTime;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Adam
 */
public interface IArduino {

    List<TempTime> getTemp();
    
}
