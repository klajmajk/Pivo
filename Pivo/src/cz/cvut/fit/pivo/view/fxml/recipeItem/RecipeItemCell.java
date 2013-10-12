/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.view.fxml.recipeItem;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

/**
 *
 * @author Adam
 */
public class RecipeItemCell extends ListCell<RecipeItemModel> {

    @Override
    protected void updateItem(RecipeItemModel model, boolean bln) {
        super.updateItem(model, bln);

        if (model != null) {
            try {
                URL location = RecipeItemController.class.getResource("recipeItem.fxml");

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

                Node root = (Node) fxmlLoader.load(location.openStream());
                RecipeItemController controller = (RecipeItemController) fxmlLoader.getController();
                controller.setModel(model);
                setGraphic(root);
            } catch (IOException ex) {
                Logger.getLogger(RecipeItemCell.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
