/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.guifx.Util;

import java.io.File;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Dony
 */
public class coreImageFolderChooser {
private DirectoryChooser chooser;  
public String path;
public coreImageFolderChooser(){
chooser = new DirectoryChooser();

//Set to user directory or go to default if cannot access
String userDirectoryString = System.getProperty("user.home");
File userDirectory = new File(userDirectoryString);
if(!userDirectory.canRead()) {
    userDirectory = new File("c:/");
}
chooser.setInitialDirectory(userDirectory);

//Choose the file
File chosenFile = chooser.showDialog(null);
//Make sure a file was selected, if not return default

if(chosenFile != null) {
    path = chosenFile.getPath();
} else {
    //default return value
    path = null;
}
}        
}
