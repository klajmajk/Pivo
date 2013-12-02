/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.persistence;

import cz.cvut.fit.pivo.entities.Settings;
import cz.cvut.fit.pivo.entities.Recipe;
import cz.cvut.fit.pivo.view.ViewFacadeFX;
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
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
    public void saveGraphWithDialog(BufferedImage image) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"));

        //System.out.println(pic.getId());
        File outputfile = fileChooser.showSaveDialog(ViewFacadeFX.getInstanceOf().getStage());
        if (!outputfile.getName().contains(".")) {
            outputfile = new File(outputfile.getAbsolutePath() + ".png");
        }
        String path = outputfile.getAbsolutePath();
        writeFile(image, outputfile);
    }

    @Override
    public void saveGraphWithoutDialog(BufferedImage image, String path, String recipeName) {
        
        File file = new File(path);
        file.mkdir();
        path+= getDateString() +"_"+recipeNameForFile(recipeName)+".png";
        file = new File(path);
        file.mkdir();
        writeFile(image, file);
    }
    
    private String recipeNameForFile(String name){
        return name.replace(" ", " ");
    }

    private void writeFile(BufferedImage image, File outputfile) {
        try {
            outputfile.createNewFile();
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
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

    public static void saveSettingsToXml(Settings o) {
        try {
            try {
                FileOutputStream os = new FileOutputStream("settings.xml");
                JAXBContext jc = JAXBContext.newInstance(Settings.class);
                Marshaller m = jc.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.marshal(o, os);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Settings loadSettingsFromXml() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Settings.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File xml = new File("settings.xml");
            return (Settings) unmarshaller.unmarshal(xml);
        } catch (JAXBException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



}
