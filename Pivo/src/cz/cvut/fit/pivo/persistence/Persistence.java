/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.persistence;

import cz.cvut.fit.pivo.entities.Recipe;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Adam
 */
public class Persistence implements IPersistence {

    @Override
    public void saveRecipe(Recipe recipe) {

        File outputfile = new File("recipes.txt");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputfile));
            oos.writeObject(recipe);
            oos.close();
        } catch (IOException e) {
            System.err.println("Exception writing file " + outputfile + ": " + e);
        }
    }

    @Override
    public void saveGraph(BufferedImage image) {

        File outputfile = new File(getDateString() + ".png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.err.println("Exception writing file " + outputfile + ": " + e);
        }
    }

    @Override
    public List<Recipe> getRecipes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
