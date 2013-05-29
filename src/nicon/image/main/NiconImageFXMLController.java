/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nicon.image.guifx.Util.coreImageChooser;

/**
 * FXML Controller class
 *
 * @author Dony
 */
public class NiconImageFXMLController implements Initializable {
private coreImageChooser openImage;
private ImageView viewer;
@FXML
private AnchorPane primary;
@FXML
private AnchorPane visor;

    
@FXML 
private void OpenImage(ActionEvent event){
openImage = new coreImageChooser();

if (openImage.path==null){}else{
AnchorPane.setTopAnchor(viewer, 20.0);
AnchorPane.setBottomAnchor(viewer, 20.0);
AnchorPane.setLeftAnchor(viewer, 0.0);
AnchorPane.setRightAnchor(viewer, 0.0);
visor.getChildren().add(viewer);
Image img= new Image(openImage.path);
viewer.setImage(img);
viewer.setFitWidth(100);
viewer.setPreserveRatio(true);

}
}
    /**
     * Initializes the controller class.
     */


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    
    }    
}
