/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconImage y NiconSystem son marcas registradas de NiconSystem Inc.
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */
package nicon.image.core.conf;

import java.awt.Color;
import java.awt.Font;

/**
 *NiconImageConf define todas las configuraciones usadas por NiconImage en cuanto a fuentes, colores,
 * url iconos y demas, un objeto de esta clase solo puede instanciado una vez por lo que se usa el 
 * patron Singleton para el acceso a la misma.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class NiconImageConf {
    
    private Color panelColor;
    private Color titleColor;
    private Color textColor;
    
    private Font  titleFont;
    private Font  textFont;
    private String iconsPath;
    
    private static NiconImageConf instance;
    
    private NiconImageConf(){
      panelColor=new java.awt.Color(35, 35, 35); 
      titleColor=new Color(18, 151, 220);
      textColor=    Color.lightGray;      
      
      titleFont=new Font("Ubuntu",Font.PLAIN,29);    
      textFont=new Font("Ubuntu",Font.PLAIN,16);
      iconsPath="/nicon/image/gui/icons/";
    }

    /**
     * Retorna el color de fondo del JPanel que es usado en el sistema
     * @return Color panelColor
     */
    public Color getPanelColor() {
        return panelColor;
    }

    /**
     * retorna la fuente que se usa para los titulos en NiconImage
     * @return Font titleFont
     */
    public Font getTitleFont() {
        return titleFont;
    }    

    public Font getTextFont() {
        return textFont;
    }
    
    public String getIconsPath() {
        return iconsPath;
    }

    public Color getTitleColor() {
        return titleColor;
    }

    public Color getTextColor() {
        return textColor;
    }
                
    public static NiconImageConf getInstance(){
        if(instance==null){
            instance=new NiconImageConf();
        }
        return instance;
    }
    
}
