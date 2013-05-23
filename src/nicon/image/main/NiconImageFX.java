/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.main;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import nicon.image.gui.JNiconImage;

/**
 *
 * @author Donaldo José Arteta Chagüi
 * @version 0.1.10
 * 
 */
public class NiconImageFX extends Application {
    
    @Override
    public void start(final Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("NiconImageFXML.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          @Override
          public void handle(WindowEvent we) {
          if(we.isConsumed()==false){primaryStage.close();
          System.exit(0);}
          System.out.println(we.toString());
          System.out.println("Stage is closing");
          }
      });        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
         try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            JNiconImage niconImage=new JNiconImage();
            niconImage.setLocationRelativeTo(null);
            niconImage.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
           System.out.println("Se produjo error en NiconImageFX main: "+ex);
        }
    }
}
