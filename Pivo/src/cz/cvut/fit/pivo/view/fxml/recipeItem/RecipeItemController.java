/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml.recipeItem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class RecipeItemController implements Initializable {
    @FXML
    Label vystiraciLabel;
    @FXML
    Label peptonizacniLabel;
    @FXML
    Label nizsiCukrLabel;
    @FXML
    Label vyssiCukrLabel;
    @FXML
    Label odrmutovaciLabel;
    @FXML
    Label recipeNameLabel;
    
    RecipeItemModel model;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setModel(RecipeItemModel model)
    {
        
        this.model = model;
        updateView();
    }
    
    void updateView(){
        recipeNameLabel.setText(model.recipe.getName());
        /*vystiraciLabel.setText(model.recipe.vystiraciTemp+"°C / "+model.recipe.vystiraciTime+" min");
        peptonizacniLabel.setText(model.recipe.peptonizacniTemp+"°C / "+model.recipe.peptonizacniTime+" min");
        nizsiCukrLabel.setText(model.recipe.nizsiCukrTemp+"°C / "+model.recipe.nizsiCukrTime+" min");
        vyssiCukrLabel.setText(model.recipe.vyssiCukrTemp+"°C / "+model.recipe.vyssiCukrTime+" min");
        odrmutovaciLabel.setText(model.recipe.odrmutovaciTemp+"°C / "+model.recipe.odrmutovaciTime+" min");*/
    }
}
