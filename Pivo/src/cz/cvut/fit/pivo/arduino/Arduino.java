/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.arduino;

import cz.cvut.fit.pivo.entities.Pin;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fit.pivo.entities.TempTime;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
public class Arduino {

    private static final String ADDRESS = "192.168.2.22";
    private static final int TEMP_PIN_POS = 1;

    public Arduino() {
    }

    public TempTime getTemp() {
        String json = getHttpResponseBody(ADDRESS);
        List<Pin> pinList = getPinList(json);
        TempTime tempTime = new TempTime(pinList.get(TEMP_PIN_POS).getValue());
        System.out.println("Teplota: " + tempTime.getTemp() + " °C");


        return tempTime;
    }

    private List<Pin> getPinList(String JSON) {
        ObjectMapper mapper = new ObjectMapper();

        List<Pin> pinList = new ArrayList<Pin>();
        String temp = new String(JSON);
        temp = temp.replaceAll("\r\n", "");
        temp = temp.replaceAll("\n", "");
        temp = temp.replaceAll("},", "}");
        temp = temp.replaceAll("}}", "}");
        List<String> PinJSONList = new LinkedList<String>(Arrays.asList(temp.split("\"\\d\\\":")));
        PinJSONList.remove(0);
        try {
            for (String string : PinJSONList) {
                pinList.add(mapper.readValue(string, Pin.class));
            }
        } catch (IOException ex) {
            Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pinList;
    }

    private String getHttpResponseBody(String address) {
        String body = "err";
        try {
            URL url = new URL("http://" + address);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
            //System.out.println(body);
        } catch (IOException ex) {
            Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return body;
    }
}
