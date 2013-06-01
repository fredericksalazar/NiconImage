/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.util;

import javax.swing.JLabel;

/**
 *
 * @author Dony
 */
public class NiconImageObject extends JLabel{
private String Name;
private String Path;
public NiconImageObject(String name, String path){
Name = name;
Path = path;

}

public String getNombre(){
return Name;
}

public String getPath(){
return Path;
}


}
