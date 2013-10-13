/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml.recipeEdit;

import cz.cvut.fit.pivo.entities.Constants;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import cz.cvut.fit.pivo.view.fxml.RecipeSelectViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class RecipeEditViewController implements Initializable {
    @FXML
    ChoiceBox<Object> vystirChoice;
    @FXML
    ChoiceBox<Object> peptoChoice;
    @FXML
    ChoiceBox<Object> nizsiChoice;
    @FXML
    ChoiceBox<Object> vyssiChoice;
    @FXML
    ChoiceBox<Object> odrmutovaciChoice;
    
    @FXML
    Label vystirkaTimeLabel;
    @FXML
    Label peptoTimeLabel;
    @FXML
    Label nizsiTimeLabel;
    @FXML
    Label vyssiTimeLabel;
    @FXML
    Label odrmutTimeLabel;
    
    @FXML
    Slider vystirkaSlider;
    @FXML
    Slider peptoniSlider;
    @FXML
    Slider nizsiSlider;
    @FXML
    Slider vyssiSlider;
    @FXML
    Slider odrmutovaciSlider;
    
    
    @FXML
    TextField recipeName;
    
    Recipe recipe;
    
    private RecipeSelectViewController recipeSelectViewController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        initSlider(vystirkaSlider, vystirkaTimeLabel);
        initSlider(peptoniSlider, peptoTimeLabel);
        initSlider(nizsiSlider, nizsiTimeLabel);
        initSlider(vyssiSlider, vyssiTimeLabel);
        initSlider(odrmutovaciSlider, odrmutTimeLabel);
        
        initChoices();
    }    
    
    private void initChoice (int min, int max, ChoiceBox cb){
        
        cb.setItems(FXCollections.observableArrayList());
        for (int i = min; i <= max; i++) {
            cb.getItems().add(i);
            cb.getSelectionModel().select(i);
        }        
        cb.getSelectionModel().select((max-min)/2);
        
    }
    
    private void initSlider(final Slider slider, final Label label){
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    label.setText("Čas: "+((Double)slider.getValue()).intValue()+" min");
            }
        });
    }
    
    public void setRecipeSelectViewController (RecipeSelectViewController recipeSelectViewController){
        this.recipeSelectViewController = recipeSelectViewController;
    }
    
    private void initChoices(){
        initChoice(Constants.VYSTIRKA_MIN_TEMP, Constants.VYSTIRKA_MAX_TEMP, vystirChoice);
        initChoice(Constants.PEPTONIZACNI_MIN_TEMP, Constants.PEPTONIZACNI_MAX_TEMP, peptoChoice);
        initChoice(Constants.NIZSI_CUKROTVORNA_MIN_TEMP, Constants.NIZSI_CUKROTVORNA_MAX_TEMP, nizsiChoice);
        initChoice(Constants.VYSSI_CUKROTVORNA_MIN_TEMP, Constants.VYSSI_CUKROTVORNA_MAX_TEMP, vyssiChoice);
        initChoice(Constants.ODRMUTOVACI_MIN_TEMP, Constants.ODRMUTOVACI_MAX_TEMP, odrmutovaciChoice);
    }
    
    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
        recipeName.setText(recipe.name);
        
        initRecipePart(vystirChoice, vystirkaSlider, recipe.vystiraciTemp, recipe.vystiraciTime);
        initRecipePart(peptoChoice, peptoniSlider, recipe.peptonizacniTemp, recipe.peptonizacniTime);
        initRecipePart(nizsiChoice, nizsiSlider, recipe.nizsiCukrTemp, recipe.nizsiCukrTime);
        initRecipePart(vyssiChoice, vyssiSlider, recipe.vyssiCukrTemp, recipe.vyssiCukrTime);
        initRecipePart(odrmutovaciChoice, odrmutovaciSlider, recipe.odrmutovaciTemp, recipe.odrmutovaciTime);
       
    }

    private void initRecipePart(ChoiceBox choice, Slider slider, int temp, int time){
        choice.getSelectionModel().select((Integer)temp);
        slider.setValue(time);
    }
    
    
    @FXML
    private void addClicked(ActionEvent event) {
        Recipe recipeToAdd = new Recipe(recipeName.getText(), Constants.TEMP_TOLERANCE, 
                Integer.parseInt(vystirChoice.getSelectionModel().getSelectedItem().toString()), ((Double) vystirkaSlider.getValue()).intValue(), 
                Integer.parseInt(peptoChoice.getSelectionModel().getSelectedItem().toString()), ((Double) peptoniSlider.getValue()).intValue(), 
                Integer.parseInt(nizsiChoice.getSelectionModel().getSelectedItem().toString()), ((Double) nizsiSlider.getValue()).intValue(), 
                Integer.parseInt(vyssiChoice.getSelectionModel().getSelectedItem().toString()), ((Double) vyssiSlider.getValue()).intValue(), 
                Integer.parseInt(odrmutovaciChoice.getSelectionModel().getSelectedItem().toString()), ((Double) odrmutovaciSlider.getValue()).intValue()
                );
        ViewFacadeFX.getInstanceOf().getController().saveRecipe(recipeToAdd);
        ViewFacadeFX.getInstanceOf().getController().notifyView();
        recipeSelectViewController.notifyView();
        closeWindow();
        
    }
    private void closeWindow(){
        Stage stage = (Stage) vystirChoice.getScene().getWindow();
        // do what you have to do
        stage.close();
        System.out.println("cancel");
    }
    
    

    @FXML
    private void cancellClicked(ActionEvent event) {
        closeWindow();
    }
}
