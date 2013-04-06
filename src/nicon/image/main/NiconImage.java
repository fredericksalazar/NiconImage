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

package nicon.image.main;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import nicon.image.gui.JNiconImage;

/**
 *
 * @author frederick
 */
public class NiconImage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            JNiconImage niconImage=new JNiconImage();
            niconImage.setLocationRelativeTo(null);
            niconImage.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NiconImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
