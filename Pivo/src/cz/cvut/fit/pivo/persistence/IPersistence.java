/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.persistence;

import cz.cvut.fit.pivo.entities.Recipe;
import java.awt.image.BufferedImage;
import java.util.Collection;

/**
 *
 * @author Adam
 */
public interface IPersistence {
    void saveRecipes(Collection<Recipe> recipes);
    /**
     * Reads persistently saved recipes
     * 
     * @return recipes collection
     */
    Collection<Recipe> readRecipes();
    void saveGraph(BufferedImage image, String path);
    Collection<Recipe> getRecipes();
    //void saveSettingsToXml (Object o);
    //Object loadSettingsFromSml ();
}
