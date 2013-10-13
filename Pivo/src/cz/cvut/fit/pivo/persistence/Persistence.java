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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam
 */
public class Persistence implements IPersistence {

    @Override
    public void saveRecipes(Collection<Recipe> recipes) {

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
    public void saveGraph(BufferedImage image, String path) {
        File outputfile;
        if (path.endsWith(".png")) {
            outputfile = new File(path);
        } else {
            outputfile = new File(path + ".png");
        }
        try {
            if (!outputfile.exists()) {

                ImageIO.write(image, "png", outputfile);
                JOptionPane.showMessageDialog(null, "Warning file created succesfully in \n" + path);
            } else {
                String message = "File already exist in \n" + path + ":\n" + "Do you want to overwrite?";
                String title = "Warning";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    outputfile.delete();
                    outputfile.createNewFile();
                    ImageIO.write(image, "png", outputfile);
                    JOptionPane.showMessageDialog(null, "File overwritten succesfully in \n" + path);

                }
            }
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.err.println("Exception writing file " + outputfile + ": " + e);
        }
    }

    @Override
    public Collection<Recipe> getRecipes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public Collection<Recipe> readRecipes() {
        try {
            //use buffering
            InputStream file = new FileInputStream("recipes.txt");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try {
                //deserialize
                Collection<Recipe> redRecipes = (Collection<Recipe>) input.readObject();
                return redRecipes;
            } finally {
                input.close();
            }
        } catch (FileNotFoundException ex) {
            saveRecipes(new HashSet<Recipe>());
        } catch (ClassNotFoundException | IOException ex) {
            //System.out.println("Cannot perform input. Class not found.");
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new HashSet<Recipe>();    
    }
}
