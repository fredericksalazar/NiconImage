/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.core;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author frederick
 */
public class NiconImageViewer extends JPanel {
    
    private Image image; 
    private Image imageTmp;
    private ImageIcon icon;
    private Image imageTemp;
    private NiconImageConf niConfig;
    
    public NiconImageViewer(){
        niConfig=NiconImageConf.getInstance();
        setSize(1000,505);
        setBackground(niConfig.getPanelColor());
        icon=new ImageIcon(getClass().getResource("/nicon/image/gui/icons/background.png"));
        image=icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING);
    }

    public void setImage(ImageIcon image) {
        this.image = image.getImage().getScaledInstance(800, 500, Image.SCALE_DEFAULT);
        this.repaint();
    }    
        
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image,0, 0,this.getWidth(),this.getHeight(),this);
    }
    
    public void cleanPanel(){
        this.removeAll();
    }
    
}
