/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nicon.image.guifx.Util.coreImage;
import nicon.image.guifx.Util.coreImageChooser;
import nicon.image.guifx.Util.coreImageFolderChooser;

/**
 * FXML Controller class
 *
 * @author Dony
 */
public class NiconImageFXMLController implements Initializable {
private coreImageChooser openImage;
private coreImageFolderChooser openFolder;
private Pane[][] matriz;
private Pane BPane = new Pane();
private Button zoomMa = new Button();
private Button zoomMe = new Button();
private Button zoomRe = new Button();
private Button closeV = new Button();
private Button rotI = new Button();
private Button rotD = new Button();
private Label imgName = new Label();
private ImageView viewer= new ImageView();
private Double zoom = 1.0;
private Double Giro = 0.0;


private AnchorPane transitor= new AnchorPane();
@FXML
private AnchorPane primary;
@FXML
private AnchorPane visor;

    
@FXML 
private void OpenImage(ActionEvent event){
this.ClearPanels();
openImage=null;
Giro = 0.0;
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
viewer.setRotate(0.0);


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
Giro = 0.0;                
zoom = 1.0;                
viewer.setFitHeight(0);
viewer.setRotate(Giro);
            }
        });

rotI.setMinSize(30, 30);
rotI.setLayoutX(101);
rotI.setVisible(true);
BPane.getChildren().add(rotI);
rotI.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
Giro = Giro-90;
                viewer.setRotate(Giro);
            }
        });

rotD.setMinSize(30, 30);
rotD.setLayoutX(133);
rotD.setVisible(true);
BPane.getChildren().add(rotD);
rotD.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Giro = Giro+90;
viewer.setRotate(Giro);
            }
        });
}
}

@FXML
private void OpenFolder(ActionEvent event){
this.ClearPanels();

openFolder=null;      
openFolder = new coreImageFolderChooser();
File folder = new File(openFolder.path);
if(folder.isDirectory()==true){
List archivos = new ArrayList();
List Names = new ArrayList();
File[] archs = folder.listFiles();
int zero = 0;

while(zero <archs.length){
Double pros = Double.valueOf(zero/archs.length);

String a = archs[zero].getAbsoluteFile().toString();
String b = archs[zero].getName().toString();
if (a.contains(".png") == true || a.contains(".jpg") == true || a.contains(".bmp") == true || a.contains(".gif") == true){
archivos.add(a);
Names.add(b);
}
zero++;
}


int zero2 = 0;
int zero3 = 0;
while(zero2 < archivos.size()){
final coreImage MiniImage = new coreImage((String) archivos.get(zero2), (String) Names.get(zero2));
int cantidadx = (int) (primary.getWidth()/160);
matriz = new Pane[1000][cantidadx];
matriz[zero2][zero3] = MiniImage;

Double alto = Double.valueOf((zero2/cantidadx));
if (alto == 0.0){
AnchorPane.setTopAnchor(MiniImage, 5.0);
}else{
AnchorPane.setTopAnchor(MiniImage, 160.0*alto);}
if (zero3 == 0){
AnchorPane.setLeftAnchor(MiniImage, 5.0);
}else{
AnchorPane.setLeftAnchor(MiniImage, 160.0*zero3);}
MiniImage.setVisible(true);
MiniImage.setCursor(Cursor.HAND);
MiniImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                String url = MiniImage.getURL();
                String Name = MiniImage.getNameL();
                OpenImage(url,Name);
            }
        });



visor.getChildren().add(MiniImage);

zero2++;
zero3++;
if (zero3 == cantidadx){zero3 = 0;}
}    
}

}

private void OpenImage(String a,String b){
Giro = 0.0;
AnchorPane.setTopAnchor(BPane, 30.0);
AnchorPane.setLeftAnchor(BPane, 5.0);
BPane.setMinSize(600, 32);
BPane.setVisible(true);
primary.getChildren().add(BPane);
 
System.out.println(a);
if (a==null){}else{
AnchorPane.setTopAnchor(viewer, 0.0);
AnchorPane.setBottomAnchor(viewer, 0.0);
AnchorPane.setLeftAnchor(viewer, 0.0);
AnchorPane.setRightAnchor(viewer, 0.0);
transitor.getChildren().add(viewer);

AnchorPane.setTopAnchor(transitor, 0.0);
AnchorPane.setBottomAnchor(transitor, 0.0);
AnchorPane.setLeftAnchor(transitor, 0.0);
AnchorPane.setRightAnchor(transitor, 0.0);

visor.getChildren().add(transitor);
Image img= new Image("file:"+a,0,visor.getHeight(), true, true);
viewer.setImage(img);
viewer.setPreserveRatio(true);
viewer.setFitHeight(0);
viewer.setSmooth(true);
viewer.setCache(true);
viewer.setRotate(0.0);
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
Giro = 0.0;                
zoom = 1.0;                
viewer.setFitHeight(0);
viewer.setRotate(Giro);
            }
        });

closeV.setMinSize(30, 30);
closeV.setLayoutX(101);
closeV.setVisible(true);
BPane.getChildren().add(closeV);
closeV.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
transitor.getChildren().removeAll(viewer);
visor.getChildren().removeAll(transitor);
primary.getChildren().removeAll(BPane);
BPane.getChildren().removeAll(zoomMa,zoomMe,zoomRe,closeV,imgName,rotI,rotD);
            }
        });

rotI.setMinSize(30, 30);
rotI.setLayoutX(133);
rotI.setVisible(true);
BPane.getChildren().add(rotI);
rotI.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
Giro = Giro-90;
viewer.setRotate(Giro);
            }
        });

rotD.setMinSize(30, 30);
rotD.setLayoutX(165);
rotD.setVisible(true);
BPane.getChildren().add(rotD);
rotD.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
Giro = Giro+90;
viewer.setRotate(Giro);
            }
        });


imgName.setLayoutX(200);
imgName.setLayoutY(5);
imgName.setVisible(true);
imgName.setText("Nombre: "+b);
imgName.setFont(new Font("Arial", 20));
imgName.setTextFill(Color.web("#FFFFFF"));
imgName.setStyle("-fx-background-color:rgba(0,0,0,255);");
BPane.getChildren().add(imgName);


}
}



private void ClearPanels(){
primary.getChildren().removeAll(BPane);
BPane.getChildren().removeAll(zoomMa,zoomMe,zoomRe,closeV,imgName,rotI,rotD);
visor.getChildren().clear();


}

private void BPanelistener(){
BPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
    zoomMa.setVisible(true);
    zoomMe.setVisible(true);
    zoomRe.setVisible(true);
    closeV.setVisible(true);
    imgName.setVisible(true);
    rotI.setVisible(true);
    rotD.setVisible(true);
            }
        });
    BPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
    zoomMa.setVisible(false);
    zoomMe.setVisible(false);
    zoomRe.setVisible(false);
    closeV.setVisible(false);
    imgName.setVisible(false);
    rotI.setVisible(false);
    rotD.setVisible(false);
            }
        });
}
    /**
     * Initializes the controller class.
     */


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    zoomMa.setCursor(Cursor.HAND);
    zoomMe.setCursor(Cursor.HAND);
    zoomRe.setCursor(Cursor.HAND);
    closeV.setCursor(Cursor.HAND);
    rotI.setCursor(Cursor.HAND);
    rotD.setCursor(Cursor.HAND);    
    
   
    
    
    transitor.getStyleClass().add("primaryAnchorPane");
    zoomMa.getStyleClass().add("zoomMas");
    zoomMe.getStyleClass().add("zoomMenos");
    zoomRe.getStyleClass().add("zoomRest");
    closeV.getStyleClass().add("CloserB");
    rotI.getStyleClass().add("rotI");
    rotD.getStyleClass().add("rotD");        
    this.BPanelistener();
    
    
    }    
}
