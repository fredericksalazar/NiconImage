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

package nicon.image.gui;

import javax.swing.JDialog;

/**
 * Esta clase creará hereda de JDialog y es usada para mostrar informacion básica
 * sobre la imagen que se esta mostrando, en esta vista se recibe un objeto del
 * tipo Image a traes del cual se conocerán las propiedades y se mostrará una vista
 * previa de la misma.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Viewer extends JDialog{
    
    public Viewer(){
        this.setUndecorated(true);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);
    }
    
}
