/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.guifx.Util;

import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author Dony
 */
public class coreImageChooser{
private FileChooser chooser = new FileChooser();  
public String path;
public coreImageChooser(){
chooser = new FileChooser();

//Extention filter
FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg","*.gif");
chooser.getExtensionFilters().add(extentionFilter);

//Set to user directory or go to default if cannot access
String userDirectoryString = System.getProperty("user.home");
File userDirectory = new File(userDirectoryString);
if(!userDirectory.canRead()) {
    userDirectory = new File("c:/");
}
chooser.setInitialDirectory(userDirectory);

//Choose the file
File chosenFile = chooser.showOpenDialog(null);
//Make sure a file was selected, if not return default

if(chosenFile != null) {
    path = chosenFile.getPath();
} else {
    //default return value
    path = null;
}
}    
}
