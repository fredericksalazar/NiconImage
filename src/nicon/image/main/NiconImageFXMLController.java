/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import nicon.image.guifx.Util.coreImageChooser;

/**
 * FXML Controller class
 *
 * @author Dony
 */
public class NiconImageFXMLController implements Initializable {
private coreImageChooser openImage;
private Pane BPane = new Pane();
private Button zoomMa = new Button();
private Button zoomMe = new Button();
private Button zoomRe = new Button();
private ImageView viewer= new ImageView();
private Double zoom = 1.0;
@FXML
private AnchorPane primary;
@FXML
private AnchorPane visor;

    
@FXML 
private void OpenImage(ActionEvent event){
openImage=null;
primary.getChildren().removeAll(BPane);
BPane.getChildren().removeAll(zoomMa,zoomMe,zoomRe);
visor.getChildren().clear();

AnchorPane.setTopAnchor(BPane, 30.0);
AnchorPane.setLeftAnchor(BPane, 5.0);
BPane.setMinSize(100, 32);
BPane.setVisible(true);
primary.getChildren().add(BPane);
    
openImage = new coreImageChooser();
System.out.println(openImage.path);
if (openImage.path==null){}else{
AnchorPane.setTopAnchor(viewer, 0.0);
AnchorPane.setBottomAnchor(viewer, 0.0);
AnchorPane.setLeftAnchor(viewer, 0.0);
AnchorPane.setRightAnchor(viewer, 0.0);
visor.getChildren().add(viewer);
Image img= new Image("file:"+openImage.path,0,visor.getHeight(), true, true);
viewer.setImage(img);
viewer.setPreserveRatio(true);
viewer.setFitHeight(0);
viewer.setSmooth(true);
viewer.setCache(true);

zoomMa.setMinSize(30, 30);
zoomMa.setLayoutX(5);
zoomMa.setVisible(true);
BPane.getChildren().add(zoomMa);
zoomMa.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
zoom = zoom+0.1;                
viewer.setFitHeight(viewer.getImage().getHeight()*zoom);
            }
        });
zoomMe.setMinSize(30, 30);

zoomMe.setLayoutX(37);
zoomMe.setVisible(true);
BPane.getChildren().add(zoomMe);
zoomMe.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
zoom = zoom-0.1;                
viewer.setFitHeight(viewer.getImage().getHeight()*zoom);
            }
        });


zoomRe.setMinSize(30, 30);
zoomRe.setLayoutX(69);
zoomRe.setVisible(true);
BPane.getChildren().add(zoomRe);
zoomRe.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
zoom = 1.0;                
viewer.setFitHeight(0);
            }
        });


}
}

private void BPanelistener(){
BPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
    zoomMa.setVisible(true);
    zoomMe.setVisible(true);
    zoomRe.setVisible(true);
            }
        });
    BPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
    zoomMa.setVisible(false);
    zoomMe.setVisible(false);
    zoomRe.setVisible(false);
            }
        });
}
    /**
     * Initializes the controller class.
     */


    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    zoomMa.getStyleClass().add("zoomMas");
    zoomMe.getStyleClass().add("zoomMenos");
    zoomRe.getStyleClass().add("zoomRest");
    this.BPanelistener();
    
    
    }    
}
