/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.persistence;

import cz.cvut.fit.pivo.entities.Recipe;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Adam
 */
public interface IPersistence {
    void saveRecipes(Collection<Recipe> recipes);
    Collection<Recipe> readRecipes();
    void saveGraph(BufferedImage image, String path);
    Collection<Recipe> getRecipes();
}
