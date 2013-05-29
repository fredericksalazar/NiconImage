/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.guifx.Util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Dony
 */
public class coreImage extends Pane{
private String url,Name;
private ImageView view = new ImageView();
public coreImage(String a,String b){
this.setMinSize(150,150);
this.setStyle("-fx-background-color:linear-gradient(#000000, #000000);");
url = a;
Name = b;
System.out.println(a);

Image img = new Image("file:"+a,150,150, true, true);
view.setImage(img);
view.setPreserveRatio(true);
view.setSmooth(true);
view.setCache(false);
view.setFitHeight(0);
view.setVisible(true);
this.getChildren().add(view);
}

public String getURL(){
return url;
}

public String getNameL(){
    
return Name;
}


}
