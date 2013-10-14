/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.entities;

import cz.cvut.fit.pivo.persistence.Persistence;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Slouží k ukládání stavu aplikace a nastavení.
 * 
 * @author Adam
 */

@XmlRootElement(name="Settings")
public class Settings {
    
    private float tempTolerance;    
    private int windowSizeWidth;
    private int windowsSizeHeigth;    
    private int tempPinPos;
    private int timeout;    
    private int maxBadRequests;    
    private String arduinoAddress;
    private int windowsY;

    public void setWindowsY(int windowsY) {
        this.windowsY = windowsY;
    }

    public void setWindowsX(int windowsX) {
        this.windowsX = windowsX;
    }
    private int windowsX;

    public  Settings() {
        tempTolerance = (float) 1.5;
        windowSizeWidth = 600;
        windowsSizeHeigth = 480;
        tempPinPos = 1;
        timeout = 5000;
        maxBadRequests = 20;
        arduinoAddress = "192.168.2.22";        
    }

    public float getTempTolerance() {
        return tempTolerance;
    }

    public void setTempTolerance(float tempTolerance) {
        this.tempTolerance = tempTolerance;
    }

    public int getWindowSizeWidth() {
        return windowSizeWidth;
    }

    public void setWindowSizeWidth(int windowSizeWidth) {
        this.windowSizeWidth = windowSizeWidth;
        System.out.println(windowSizeWidth);
    }

    public int getWindowsSizeHeigth() {
        return windowsSizeHeigth;
    }
    
    public int getWindowsX() {
        return windowsX;
    }
    
    public int getWindowsY() {
        return windowsY;
    }

    public void setWindowsSizeHeigth(int windowsSizeHeigth) {
        this.windowsSizeHeigth = windowsSizeHeigth;
    }

    public int getTempPinPos() {
        return tempPinPos;
    }

    public void setTempPinPos(int tempPinPos) {
        this.tempPinPos = tempPinPos;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxBadRequests() {
        return maxBadRequests;
    }

    public void setMaxBadRequests(int maxBadRequests) {
        this.maxBadRequests = maxBadRequests;
    }

    public String getArduinoAddress() {
        return arduinoAddress;
    }

    public void setArduinoAddress(String arduinoAddress) {
        this.arduinoAddress = arduinoAddress;
    }
    
    public void saveSettings(){
        //new Persistence().saveSettingsToXml(this);
        Persistence.saveSettingsToXml(this);
    }
    
    public static Settings loadSettings(){
        return Persistence.loadSettingsFromXml();
    }

    @Override
    public String toString() {
        return "Settings{" + "tempTolerance=" + tempTolerance + ", windowSizeWidth=" + windowSizeWidth + ", windowsSizeHeigth=" + windowsSizeHeigth + ", tempPinPos=" + tempPinPos + ", timeout=" + timeout + ", maxBadRequests=" + maxBadRequests + ", arduinoAddress=" + arduinoAddress + '}';
    }
    
    
    
}
