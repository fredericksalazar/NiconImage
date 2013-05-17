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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import nicon.image.core.extensions.FilterImageExtension;
import nicon.image.core.NiconImageCore;
import nicon.image.core.NiconImageData;
import nicon.image.core.NiconImageViewer;
import nicon.image.core.conf.NiconImageConf;

/**
 * JNiconImage es la interfaz grafica principal de todo el software de
 * NiconImage, provee todas las herramientas graficas con las que el 
 * usuario puede controlar el software y las imagenes que son cargadas y
 * mostradas.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class JNiconImage extends JFrame implements ActionListener{
    
    private JPanel panelTools;
    private JPanel panelTitle;
    private JPanel panelOptions;   
    private JPanel containerViewer;
    private NiconImageViewer panelViewer;
    
    private JMenuBar menuBar;
    private JMenu menuNicon;
    private JMenu menuEdit;
    
    private JMenuItem close;
    
    private JMenuItem renameFile;
    
    private static JLabel titleImage; 
        
    private JButton openImage;
    private JButton openDirectory;
    private JButton viewDetail;
    private JButton nextImage;
    private JButton previousImage;
    
    private NiconImageConf niconConfig;
    private NiconImageCore niconImage;
    private ImageIcon image;
    
    private File selectedFile;
    private int index=-1;
    private File[] listFiles;
    private FilterImageExtension filer;
    
    public JNiconImage(){
        setTitle("NiconImage");
        setResizable(false);
        setSize(1100,685);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        niconImage=new NiconImageCore();
        niconConfig=NiconImageConf.getInstance();
                
        menuBar=new JMenuBar();
        menuBar.setBounds(0, 0, 1100, 20);
        
        menuNicon=new JMenu("NiconImage");
        
        close=new JMenuItem("Salir de NiconImage");
        close.setIcon(new ImageIcon(getClass().getResource(niconConfig.getIconsPath()+"NiconClose.png")));
        close.addActionListener(this);
        
        menuNicon.add(close);
        
        menuEdit=new JMenu("Editar");
        
        renameFile=new JMenuItem("Renombrar");
        renameFile.addActionListener(this);
        
        menuEdit.add(renameFile);
        
        menuBar.add(menuNicon);
        menuBar.add(menuEdit);
        
        panelTitle=new JPanel();
        panelTitle.setBackground(niconConfig.getPanelColor());
        panelTitle.setBounds(0, 20,1000,50);
        
        titleImage=new JLabel("NiconImage Viewer v 0.1");
        titleImage.setFont(niconConfig.getTitleFont());
        titleImage.setForeground(Color.white);
        titleImage.setBounds(10, 30, 1100, 30);
        
        panelTitle.add(titleImage);
        
        panelOptions=new JPanel();
        panelOptions.setBackground(niconConfig.getPanelColor());
        panelOptions.setBounds(900, 0,200, 665);
        
        containerViewer=new JPanel();
        containerViewer.setBounds(0, 70, 900, 505);
        
        panelViewer=new NiconImageViewer();
        panelViewer.setBackground(niconConfig.getPanelColor());
        panelViewer.setBounds(0,70, 1000, 505);
                
        panelTools=new JPanel();
        panelTools.setBackground(niconConfig.getPanelColor());
        panelTools.setBounds(0,575,900,90);
        panelTools.setLayout(null);
        
        openImage=new JButton();
        openImage.setToolTipText("Permite abrir una nueva imágen desde el disco duro");
        openImage.setIcon(new ImageIcon(getClass().getResource(niconConfig.getIconsPath()+"NiconAdd.png")));
        openImage.setBounds(10,15, 60,60);
        openImage.addActionListener(this);
        
        openDirectory=new JButton();
        openDirectory.setToolTipText("Permite abrir un directorio de imagenes");
        openDirectory.setIcon(new ImageIcon(getClass().getResource(niconConfig.getIconsPath()+"NiconDirectory.png")));
        openDirectory.setBounds(85, 15, 60, 60);
        openDirectory.addActionListener(this);
        
        viewDetail=new JButton();
        viewDetail.setToolTipText("Muestra información detallada de la imágen actual");
        viewDetail.setIcon(new ImageIcon(getClass().getResource(niconConfig.getIconsPath()+"Info.png")));
        viewDetail.setBounds(155, 15, 60, 60);
        viewDetail.addActionListener(this);
        
        nextImage=new JButton();
        nextImage.setToolTipText("Permite mostrar la siguiente imágen");
        nextImage.setIcon(new ImageIcon(getClass().getResource(niconConfig.getIconsPath()+"NiconRight.png")));
        nextImage.setBounds(840, 15, 60, 60);
        nextImage.addActionListener(this);
        
        previousImage=new JButton();
        previousImage.setToolTipText("Permite ver la imágen anterior ");
        previousImage.setIcon(new ImageIcon(getClass().getResource(niconConfig.getIconsPath()+"NiconLeft.png")));
        previousImage.setBounds(770, 15, 60, 60);
        previousImage.addActionListener(this);        
        
        panelTools.add(openImage);
        panelTools.add(openDirectory);
        panelTools.add(viewDetail);
        panelTools.add(nextImage);
        panelTools.add(previousImage);
        
        getContentPane().add(menuBar);
        getContentPane().add(panelTitle);
        getContentPane().add(panelOptions);
        getContentPane().add(panelViewer);
        getContentPane().add(panelTools);        
    }
    
    public static void setTitleImage(String title){
        titleImage.setText(title);
    }
    
    private void showImage(){       
        selectedFile = niconImage.openImage();
        
            if(selectedFile!=null){
                titleImage.setText(selectedFile.getName());
                image=new ImageIcon(selectedFile.getPath());
                panelViewer.setImage(image);
            }                        
    }
    
    private void openDirectory(){ 
        filer= new FilterImageExtension();
        selectedFile=niconImage.openImageDirectory();
        System.out.println("Direcotrio seleccionado: "+selectedFile.getPath());
        listFiles = selectedFile.listFiles(filer);
        System.out.println("Total archivo de imagen cargados: "+listFiles.length);
        nextImage();
    }
    
    private void nextImage(){
        try{
            if(index<listFiles.length){
                index++;
                selectedFile=listFiles[index];
                titleImage.setText(selectedFile.getName());
                image=new ImageIcon(selectedFile.getPath());
                panelViewer.setImage(image);                 
            }
        }catch(Exception e ){
            System.err.println("Ocurrio un error al obtener la imagen:\n"+e);
            index=listFiles.length-1;
        }
    }
    
    public void previosImage(){
        try{
           if(index<listFiles.length){
                index--;
                selectedFile=listFiles[index];
                titleImage.setText(selectedFile.getName());
                image=new ImageIcon(selectedFile.getPath());
                panelViewer.setImage(image);                 
            } else{
                index=0;
            } 
        }catch(Exception e){
            index=-1;
        }
    }
    
    private void niconDeatilsImage(){
        NiconImageData imageData=niconImage.getNiconImageData(selectedFile);
        NiconViewerDetail visor=new NiconViewerDetail(imageData);
        visor.setLocationRelativeTo(null);
        visor.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==openImage){
            showImage();
        }
        
        if(ae.getSource()==openDirectory){
            openDirectory();
        }
        
        if(ae.getSource()==nextImage){
            nextImage();
        }
        
        if(ae.getSource()==previousImage){
            previosImage();
        }
        
        if(ae.getSource()==close){
            System.exit(0);
        }
        
        if(ae.getSource()==renameFile){
            RenameImageDialog renameDialog=new RenameImageDialog(selectedFile);
            renameDialog.setVisible(true);
        }
        
        if(ae.getSource()==viewDetail){
            this.niconDeatilsImage();
        }
        
    }   
}
