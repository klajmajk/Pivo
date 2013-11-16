/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml.recipeEdit;

import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.entities.Rest;
import cz.cvut.fit.pivo.entities.RestType;
import static cz.cvut.fit.pivo.entities.RestType.NIZSI_CUKROTVORNA;
import static cz.cvut.fit.pivo.entities.RestType.ODRMUTOVACI;
import static cz.cvut.fit.pivo.entities.RestType.PEPTONIZACE;
import static cz.cvut.fit.pivo.entities.RestType.VAR_RMUT;
import static cz.cvut.fit.pivo.entities.RestType.VYSSI_CUKROTVORNA;
import static cz.cvut.fit.pivo.entities.RestType.VYSTIRKA;
import cz.cvut.fit.pivo.other.NumberToStringConverter;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import cz.cvut.fit.pivo.view.chart.IChart;
import cz.cvut.fit.pivo.view.chart.MyChart;
import cz.cvut.fit.pivo.view.fxml.RecipeSelectViewController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class RecipeEditViewController implements Initializable {

    @FXML
    ChoiceBox<Object> choice;
    @FXML
    Label timeLabel;
    @FXML
    Slider slider;
    @FXML
    Button addRestButton;
    @FXML
    CheckBox decoctionRestCheckBox;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    TextField recipeName;
    Recipe recipe;
    IChart myChart;
    List<Rest> rests;
    RestType activeRestType;
    private RecipeSelectViewController recipeSelectViewController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initChoice(Constants.VYSTIRKA_MIN_TEMP, Constants.VYSTIRKA_MAX_TEMP, choice);
        this.myChart = new MyChart(lineChart);
        this.rests = new ArrayList<>();
        initSlider(slider, timeLabel);
        activeRestType = RestType.VYSTIRKA;
        initChoiceBasedOnRest(activeRestType);
    }

    private void initChoiceBasedOnRest(RestType rt) {
        switch (rt) {
            case VYSTIRKA:
                initChoice(Constants.VYSTIRKA_MIN_TEMP, Constants.VYSTIRKA_MAX_TEMP, choice);
                break;
            case PEPTONIZACE:
                initChoice(Constants.PEPTONIZACNI_MIN_TEMP, Constants.PEPTONIZACNI_MAX_TEMP, choice);
                break;
            case NIZSI_CUKROTVORNA:
                initChoice(Constants.NIZSI_CUKROTVORNA_MIN_TEMP, Constants.NIZSI_CUKROTVORNA_MAX_TEMP, choice);
                break;
            case VYSSI_CUKROTVORNA:
                initChoice(Constants.VYSSI_CUKROTVORNA_MIN_TEMP, Constants.VYSSI_CUKROTVORNA_MAX_TEMP, choice);
                break;
            case ODRMUTOVACI:
                initChoice(Constants.ODRMUTOVACI_MIN_TEMP, Constants.ODRMUTOVACI_MAX_TEMP, choice);
                break;
            case VAR_RMUT:
                initChoice(Constants.VAR, Constants.VAR, choice);
                break;
        }
    }

    private void initChoice(int min, int max, ChoiceBox cb) {

        cb.setItems(FXCollections.observableArrayList());
        for (int i = min; i <= max; i++) {
            cb.getItems().add(i);
            cb.getSelectionModel().select(i);
        }
        cb.getSelectionModel().select((max - min) / 2);

    }

    private void initSlider(final Slider slider, final Label label) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                ((Double) slider.getValue()).intValue();
                label.setText("Čas: " + ((Double) slider.getValue()).intValue() + " min");
            }
        });
    }

    public void setRecipeSelectViewController(RecipeSelectViewController recipeSelectViewController) {
        this.recipeSelectViewController = recipeSelectViewController;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipeName.setText(recipe.getName());
        throw new UnsupportedOperationException("TODO -- not yet implemented");
        /*initRecipePart(vystirChoice, vystirkaSlider, recipe.vystiraciTemp, recipe.vystiraciTime);
         initRecipePart(peptoChoice, peptoniSlider, recipe.peptonizacniTemp, recipe.peptonizacniTime);
         initRecipePart(nizsiChoice, nizsiSlider, recipe.nizsiCukrTemp, recipe.nizsiCukrTime);
         initRecipePart(vyssiChoice, vyssiSlider, recipe.vyssiCukrTemp, recipe.vyssiCukrTime);
         initRecipePart(odrmutovaciChoice, odrmutovaciSlider, recipe.odrmutovaciTemp, recipe.odrmutovaciTime);*/

    }

    @FXML
    private void addRest(ActionEvent event) {
        Rest rest = new Rest(((Double) slider.getValue()).intValue(),
                Integer.parseInt(choice.getSelectionModel().getSelectedItem().toString()),
                decoctionRestCheckBox.isSelected(),
                activeRestType);
        this.rests.add(rest);
        if(rest.isDecoction()) decoctionRestCheckBox.setDisable(true);
        //tohle je jen pro zobrazení nikam se to neukládá
        myChart.addRecipe(new Recipe(null, Constants.TEMP_TOLERANCE, rests));
        if ((activeRestType == RestType.ODRMUTOVACI) && (!rest.isDecoction())) {
            addRestButton.setDisable(true);
        }
        activeRestType = getNextRestType(activeRestType);  
        initChoicesForNext(rests);

    }

    @FXML
    private void addClicked(ActionEvent event) {
        Recipe recipe = new Recipe(recipeName.getText(), Constants.TEMP_TOLERANCE, rests);

        ViewFacadeFX.getInstanceOf().getController().saveRecipe(recipe);
        ViewFacadeFX.getInstanceOf().getController().notifyView();
        recipeSelectViewController.notifyView();
        closeWindow();

    }

    private void closeWindow() {
        Stage stage = (Stage) choice.getScene().getWindow();
        // do what you have to do
        stage.close();
        System.out.println("cancel");
    }

    @FXML
    private void cancellClicked(ActionEvent event) {
        closeWindow();
    }

    private Rest getLastNonDecoctionRest() {
        int i = rests.size() - 1;
        Rest rest = rests.get(i);
        while (rest.isDecoction()) {
            i--;
            rest = rests.get(i);
        }
        return rest;
    }

    /**
     * podle stavu posledni pridane prodlevy nastaví hodnoty do choiceboxu
     *
     * @param rests
     */
    private void initChoicesForNext(List<Rest> rests) {
        Rest last = rests.get(rests.size() - 1);
        RestType newRestType = getNextRestType(last.getRestsType());
        initChoiceBasedOnRest(newRestType);
        //tohle to uvede zpatky do stavu pred zacaktem dekokce
        if (last.getRestsType() == VAR_RMUT) {
            decoctionRestCheckBox.setSelected(false);            
            decoctionRestCheckBox.setDisable(false);
        }

    }

    private RestType getNextRestType(RestType rt) {
        switch (rt) {
            case VAR_RMUT:
                return getLastNonDecoctionRest().getRestsType().getNext();
            default:
                return rt.getNext();
        }
    }
}
