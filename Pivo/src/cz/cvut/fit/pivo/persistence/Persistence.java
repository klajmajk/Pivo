/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.persistence;

import cz.cvut.fit.pivo.entities.Recipe;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public void saveRecipes(List<Recipe> recipes) {

        File outputfile = new File("recipes.txt");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputfile));
            oos.writeObject(recipes);
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

    @Override
    public List<Recipe> readRecipes() {
        try {
            //use buffering
            InputStream file = new FileInputStream("recipes.txt");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try {
                //deserialize the List
                List<Recipe> redRecipes = (List<Recipe>) input.readObject();
                return redRecipes;
            } finally {
                input.close();
            }
        }
         catch (ClassNotFoundException ex) {
             //System.out.println("Cannot perform input. Class not found.");
                Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
                Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return new ArrayList<Recipe>();
    }
}
