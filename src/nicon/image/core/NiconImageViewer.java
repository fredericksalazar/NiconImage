/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package nicon.image.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import nicon.image.core.conf.NiconImageConf;

/**
*
* @author frederick
*/
public class NiconImageViewer extends JPanel {
    
    private static Image image;
    private ImageIcon icon;
    private NiconImageConf niConfig;
    private static Image imageTemp;
    
    private JButton NiconFace;
    private JButton NiconWeb;
    
    private String urlHome="/nicon/image/gui/icons/background.png";

    public NiconImageViewer(){
        niConfig=NiconImageConf.getInstance();
        setSize(1000,505);
        setBackground(niConfig.getPanelColor());
        loadNiconHome();
    }
    
    /**
     * Este metodo es el encargado de cargar el panel de bienvenida dentro del
     * visor de imagenes, se usa cuando inicia la app o cuando se elimina una
     * imágen del sistema.
     */
    public  void loadNiconHome(){
        icon=new ImageIcon(getClass().getResource("/nicon/image/gui/icons/NiconHome.png"));
        image=icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);
        
        NiconFace=new JButton();
        NiconFace.setToolTipText("Siguenos en Facebook.com");
        NiconFace.setBounds(700, 470, 30, 30);
        NiconFace.setIcon(new ImageIcon(getClass().getResource(niConfig.getIconsPath()+"NiconFace.png")));
        NiconFace.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NiconImageCore.openNiconURL(0);
            }
        });
        
        NiconWeb=new JButton();
        NiconWeb.setToolTipText("Abrir nuestra página web");
        NiconWeb.setBounds(750, 470, 30, 30);
        NiconWeb.setIcon(new ImageIcon(getClass().getResource(niConfig.getIconsPath()+"NiconWeb.png")));
        NiconWeb.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NiconImageCore.openNiconURL(1);
            }
        });
        
        setLayout(null);
        add(NiconFace);
        add(NiconWeb);
    }
    
    public void cleanNiconViewer(){
        NiconFace.setVisible(false);
        NiconWeb.setVisible(false);
        this.remove(NiconFace);
        this.remove(NiconWeb);
        
    }

    public void setImage(ImageIcon image) {
        imageTemp=image.getImage();
        NiconImageViewer.image = imageTemp.getScaledInstance(800, 500, Image.SCALE_DEFAULT);
        this.repaint();
    }
    
    /**
     * Este metodo retorna el valor de ancho de la imagen que esta siendo 
     * vista en ese momento
     * @return 
     */
    public static int getWidthImage(){
        return imageTemp.getWidth(null);
    }
    
    public static int getHeigthImage(){
        return imageTemp.getHeight(null);
    }
        
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image,0, 0,this.getWidth(),this.getHeight(),this);
    }
    
    public void cleanPanel(){
        this.removeAll();
    }
    
}