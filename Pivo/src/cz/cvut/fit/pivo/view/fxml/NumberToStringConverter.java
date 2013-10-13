/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml;

import java.util.Date;
import javafx.util.StringConverter;
import javafx.util.converter.TimeStringConverter;

/**
 *
 * @author Adam
 */
class NumberToStringConverter extends StringConverter<Number> {

    TimeStringConverter tsc = new TimeStringConverter("HH:mm:ss");

    @Override
    public String toString(Number t) {
        return tsc.toString(new Date(t.longValue()));
    }

    @Override
    public Number fromString(String string) {
        return 1;
    }
}