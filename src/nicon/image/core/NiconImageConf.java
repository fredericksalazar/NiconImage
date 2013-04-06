/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.core;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author frederick
 */
public class NiconImageConf {
    
    private Color panelColor;
    private Font  titleFont;
    private String iconsPath;
    
    private static NiconImageConf instance;
    
    private NiconImageConf(){
      panelColor=new java.awt.Color(35, 35, 35); 
      
      titleFont=new Font("Ubuntu",Font.PLAIN,29);
      
      iconsPath="/nicon/image/gui/icons/";
    }

    public Color getPanelColor() {
        return panelColor;
    }

    public Font getTitleFont() {
        return titleFont;
    }    

    public String getIconsPath() {
        return iconsPath;
    }
    
        
    public static NiconImageConf getInstance(){
        if(instance==null){
            instance=new NiconImageConf();
        }
        return instance;
    }
    
}
