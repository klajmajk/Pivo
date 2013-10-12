/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import cz.cvut.fit.pivo.view.fxml.recipeItem.RecipeItemCell;
import cz.cvut.fit.pivo.view.fxml.recipeItem.RecipeItemModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class RecipeSelectViewController implements Initializable {
    @FXML
    private ListView<RecipeItemModel> recipeList;
    @FXML
    private Button closeButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recipeList.setCellFactory(new Callback<ListView<RecipeItemModel>, ListCell<RecipeItemModel>>()
        {
            public ListCell<RecipeItemModel> call(ListView<RecipeItemModel> p)
            {
                return new RecipeItemCell();
            }
        });
        recipeList.setItems(getObsArrayList());
    }    
    
    private ObservableList<RecipeItemModel> getObsArrayList(){
        List list = new ArrayList<RecipeItemModel>();
        for(Recipe r : ViewFacadeFX.getInstanceOf().getModel().getRecipes()){
            list.add(new RecipeItemModel(r));
        }
        
        return FXCollections.observableArrayList(list);
    }
    
    @FXML
    private void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void useClicked(ActionEvent event) {
        Recipe recipe = recipeList.getSelectionModel().getSelectedItem().getRecipe();
        ViewFacadeFX.getInstanceOf().getModel().setCurrentRecipe(recipe);
        ViewFacadeFX.getInstanceOf().getController().notifyView();
        //zavrit
        Stage stage = (Stage) closeButton.getScene().getWindow();
        
        // do what you have to do
        stage.close();
    }
    
  
}
