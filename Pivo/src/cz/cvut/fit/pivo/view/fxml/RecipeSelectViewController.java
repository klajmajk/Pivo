/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml;

import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
import cz.cvut.fit.pivo.view.fxml.recipeEdit.RecipeEditViewController;
import cz.cvut.fit.pivo.view.fxml.recipeItem.RecipeItemCell;
import cz.cvut.fit.pivo.view.fxml.recipeItem.RecipeItemModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class RecipeSelectViewController implements IInitializableView {

    Stage stage;
    @FXML
    private ListView<RecipeItemModel> recipeList;
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recipeList.setCellFactory(new Callback<ListView<RecipeItemModel>, ListCell<RecipeItemModel>>() {
            public ListCell<RecipeItemModel> call(ListView<RecipeItemModel> p) {
                return new RecipeItemCell();
            }
        });
        recipeList.setItems(getObsArrayList());
    }

    private ObservableList<RecipeItemModel> getObsArrayList() {
        List list = new ArrayList<RecipeItemModel>();
        for (Recipe r : ViewFacadeFX.getInstanceOf().getModel().getRecipes()) {
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

    private void startNewEditRecipe(boolean edit) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("\\cz\\cvut\\fit\\pivo\\view\\fxml\\recipeEdit\\recipeEditView.fxml"));
            Parent root = (Parent) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Editace receptu");
            stage.setScene(new Scene(root));
            stage.show();

            RecipeEditViewController controller = (RecipeEditViewController) loader.getController();
            if (edit) {
                Recipe recipe = recipeList.getSelectionModel().getSelectedItem().getRecipe();
                controller.setRecipe(recipe);
            }
            controller.setRecipeSelectViewController(this);
        } catch (IOException ex) {
            Logger.getLogger(RecipeSelectViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editClicked(ActionEvent event) {
        startNewEditRecipe(true);
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        Recipe recipe = recipeList.getSelectionModel().getSelectedItem().getRecipe();
        ViewFacadeFX.getInstanceOf().getController().deleteRecipe(recipe);
        notifyView();
    }

    @FXML
    private void newClicked(ActionEvent event) {
        startNewEditRecipe(false);
    }

    @FXML
    private void useClicked(ActionEvent event) {
        try {
            Recipe recipe = recipeList.getSelectionModel().getSelectedItem().getRecipe();
            ViewFacadeFX.getInstanceOf().getModel().setCurrentRecipe(recipe);
            ViewFacadeFX.getInstanceOf().getController().notifyView();
            //zavrit
            Stage stage = (Stage) closeButton.getScene().getWindow();

            // do what you have to do
            stage.close();
        } catch (NullPointerException e) {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            Scene scene = new Scene(new Group(new Text(20,20,"Musí být vybrán recept")), 240,80);
            dialog.setScene(scene);
            dialog.show();
        }
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyView() {
        recipeList.setItems(getObsArrayList());
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
