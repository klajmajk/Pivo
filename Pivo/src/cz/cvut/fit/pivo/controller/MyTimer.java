/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.controller;

import java.awt.Toolkit;
import java.util.TimerTask;
import java.util.Timer;

/**
 *
 * @author Adam
 */
public class MyTimer {

    Toolkit toolkit;
    Timer timer;
    IController controller;

    public MyTimer(int seconds, IController controller) {
        this.controller = controller;
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, seconds * 1000);        
    }
    
    void cancel(){
        timer.cancel();
    }

    class RemindTask extends TimerTask {

        public void run() {
            controller.tick();
        }
    }
}
