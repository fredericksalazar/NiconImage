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

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import nicon.image.core.NiconImageCore;
import nicon.image.core.NiconImageData;
import nicon.image.core.conf.NiconImageConf;

/**
 * Una instancia de esta clase representa al visor de detalles de una imagen
 * seleccionada, esta caja de dialogo permitirá mostrar informacion básica de la
 * imagen que se obtiene a partir del objeto NiconImageData.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */

public class NiconViewerDetail extends JDialog{
    
    private JPanel panelAdmin;
    private NiconImageConf niconConf;
    private NiconImageData imageData;
    
    private JLabel title;
    private JLabel jlName;
    private JLabel jlWidth;
    private JLabel nameImage;
    private JLabel widthImage;
    private JLabel jlHeight;
    private JLabel heightImage;
    private JLabel jlExtension;
    private JLabel extensionImage;
    private JLabel jlPound;
    private JLabel poundImage;
    
    private JButton btnClose;
    
    public NiconViewerDetail(NiconImageData imageData){
        this.setUndecorated(true);
        this.setModal(true);
        this.setSize(550,300);
        this.setLocationRelativeTo(null);
        NiconImageCore.addEscapeListenerWindowDialog(this);
        this.imageData=imageData;
        init();
    }

    private void init() {
        
        niconConf=NiconImageConf.getInstance();
        
        panelAdmin=new JPanel();
        panelAdmin.setBackground(niconConf.getPanelColor());
        panelAdmin.setLayout(null);
        
        btnClose=new JButton();
        btnClose.setIcon(new ImageIcon(getClass().getResource(niconConf.getIconsPath()+"NiconCloseBtn.png")));
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setToolTipText("Close ");        
        btnClose.setBounds(520,2, 32, 32);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        title=new JLabel("Información:");
        title.setFont(niconConf.getTitleFont());
        title.setBounds(10, 10, 600, 30);
        title.setForeground(niconConf.getTitleColor());
        
        jlName=new JLabel("Nombre Imágen:");
        jlName.setForeground(niconConf.getTitleColor());
        jlName.setFont(niconConf.getTextFont());
        jlName.setBounds(100, 80, 300, 20);
        
        nameImage=new JLabel(imageData.getNameImage());
        nameImage.setForeground(niconConf.getTextColor());
        nameImage.setFont(niconConf.getTextFont());
        nameImage.setBounds(240, 80,300, 20);
        
        jlWidth=new JLabel("Ancho Imágen:");
        jlWidth.setForeground(niconConf.getTitleColor());
        jlWidth.setFont(niconConf.getTextFont());
        jlWidth.setBounds(100,110, 150, 20);
        
        widthImage=new JLabel(String.valueOf(imageData.getWitdhImage())+" Pixeles");
        widthImage.setForeground(niconConf.getTextColor());
        widthImage.setFont(niconConf.getTextFont());
        widthImage.setBounds(240, 110, 300, 20);
        
        jlHeight=new JLabel("Alto Imágen:");
        jlHeight.setFont(niconConf.getTextFont());
        jlHeight.setForeground(niconConf.getTitleColor());
        jlHeight.setBounds(100, 140, 150, 20);
        
        heightImage=new JLabel(String.valueOf(imageData.getHeightImage()+" Pixeles"));
        heightImage.setForeground(niconConf.getTextColor());
        heightImage.setFont(niconConf.getTextFont());
        heightImage.setBounds(240, 140, 300, 20);
        
        jlExtension=new JLabel("Tipo de Imágen:");
        jlExtension.setForeground(niconConf.getTitleColor());
        jlExtension.setFont(niconConf.getTextFont());
        jlExtension.setBounds(100, 170, 150, 20);
        
        extensionImage=new JLabel("Imágen de tipo "+imageData.getExtensionImage());
        extensionImage.setForeground(niconConf.getTextColor());
        extensionImage.setFont(niconConf.getTextFont());
        extensionImage.setBounds(240, 170, 300, 20);
        
        jlPound=new JLabel("Peso de Archivo:");
        jlPound.setForeground(niconConf.getTitleColor());
        jlPound.setFont(niconConf.getTextFont());
        jlPound.setBounds(100,200,150,20);
        
        poundImage=new JLabel(String.valueOf(imageData.getPundImage()/2048));
        poundImage.setForeground(niconConf.getTextColor());
        poundImage.setFont(niconConf.getTextFont());
        poundImage.setBounds(240, 200, 250, 20);
        
        panelAdmin.add(btnClose);
        panelAdmin.add(title);
        panelAdmin.add(jlName);
        panelAdmin.add(nameImage);
        panelAdmin.add(jlWidth);
        panelAdmin.add(widthImage);
        panelAdmin.add(jlHeight);
        panelAdmin.add(heightImage);
        panelAdmin.add(jlExtension);
        panelAdmin.add(extensionImage);
        panelAdmin.add(jlPound);
        panelAdmin.add(poundImage);
        
        this.getContentPane().add(panelAdmin);
    }
    
}
