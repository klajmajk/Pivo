/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.chart;

import cz.cvut.fit.pivo.other.NumberToStringConverter;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Adam
 */
public class HoveredThresholdNode extends StackPane {
    HoveredThresholdNode(long priorValue, double value) {
      setPrefSize(2, 2);
        System.out.println("constr");
 
      final Label label = createDataThresholdLabel(priorValue, value);
 
      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getChildren().setAll(label);
          setCursor(Cursor.NONE);
          toFront();
        }
      });
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getChildren().clear();
          setCursor(Cursor.CROSSHAIR);
        }
      });
    }
 
    private Label createDataThresholdLabel(long time, double value) {
      final Label label = new Label(value + "°C, "+new NumberToStringConverter().toString(time));
      label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
      label.setStyle("-fx-font-size: 10; -fx-font-weight: bold;");
      
 
      if (time == 0) {
        label.setTextFill(Color.DARKGRAY);
      } else if (value > time) {
        label.setTextFill(Color.FORESTGREEN);
      } else {
        label.setTextFill(Color.FIREBRICK);
      }
 
      label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
      return label;
    }
  }
