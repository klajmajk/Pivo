/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.arduino;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.entities.PinOneWire;
import cz.cvut.fit.pivo.entities.TempTime;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Adam
 */
public class Arduino implements IArduino {


    public Arduino() {
    }

    @Override
    public List<TempTime> getTemp() throws IOException {
        
        String json = getHttpResponseBody(Constants.ADDRESS);
        List<PinOneWire> pinList = getPinList(json);
        TempTime tempTime = new TempTime(pinList.get(0).getValue());
        TempTime tempTime1 = new TempTime(pinList.get(0).getValue1());
        List<TempTime> list = new ArrayList<TempTime>();
        list.add(tempTime);
        list.add(tempTime1);
        System.out.println("Teplota: " + tempTime.getTemp() + " °C");
        
        System.out.println("Teplota1: " + tempTime1.getTemp() + " °C");


        return list;
    }

    private List<PinOneWire> getPinList(String JSON) {
        ObjectMapper mapper = new ObjectMapper();

        List<PinOneWire> pinList = new ArrayList<PinOneWire>();
        String temp = new String(JSON);
        temp = temp.replaceAll("\r\n", "");
        temp = temp.replaceAll("\n", "");
        temp = temp.replaceAll("},", "}");
        temp = temp.replaceAll("}}", "}");
        List<String> PinJSONList = new LinkedList<String>(Arrays.asList(temp.split("\"\\d\\\":")));
        PinJSONList.remove(0);
        try {
                pinList.add(mapper.readValue(PinJSONList.get(1), PinOneWire.class));
            
        } catch (IOException ex) {
            Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pinList;
    }

    private String getHttpResponseBody(String address) throws IOException {
        String body = "err";
        URL url = new URL("http://" + address);
        //URLConnection con = url.openConnection();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        HttpURLConnection.setFollowRedirects(false);
        con.setConnectTimeout(Constants.TIMEOUT);
        con.setReadTimeout(Constants.TIMEOUT);
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
        con.connect();
        InputStream input = con.getInputStream();
        body = IOUtils.toString(input, "UTF-8");
        //System.out.println(body);
        return body;
    }
}
