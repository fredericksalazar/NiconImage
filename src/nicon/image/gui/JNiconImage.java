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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nicon.image.core.extensions.FilterImageExtension;
import nicon.image.core.NiconImageCore;
import nicon.image.core.NiconImageData;
import nicon.image.core.NiconImageViewer;
import nicon.image.core.conf.NiconImageConf;
import nicon.image.util.NiconImageObject;

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
    private JMenuItem deleteFile;
    
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
    private List<NiconImageObject> listFiles;
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
        
        renameFile=new JMenuItem("Cambiar Nombre");
        renameFile.setIcon(new ImageIcon(getClass().getResource(niconConfig.getIconsPath()+"NiconEdit.png")));
        renameFile.setToolTipText("Permite cambiar el Nombre de la actual Imágen");
        renameFile.addActionListener(this);
        
        deleteFile=new JMenuItem("Eliminar Imágen");
        deleteFile.setIcon(new ImageIcon(getClass().getResource(niconConfig.getIconsPath()+"NiconDelete.png")));
        deleteFile.setToolTipText("Eliminar la actual imágen del disco");
        deleteFile.addActionListener(this);
        
        menuEdit.add(renameFile);
        menuEdit.addSeparator();
        menuEdit.add(deleteFile);
        
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
    
    /**
     * Este metodo permite Ajustar el actual titulo de la Imagen en el visor de
     * imagenes principal, puede ser accedido desde cualquier prte del programa
     * para su edicion.
     * 
     * @param title 
     */
    public static void setTitleImage(String title){
        titleImage.setText(title);
    }
    
    /**
     * Este metodo es el encargdo de abrir y obtener la imagen del archivo que
     * el usuario ha intentado abrir y mostrarlo en el visor de imagenes de 
     * NiconImage, Hace uso del NiconImageCore, este metodo solo permite cargar
     * y mostrar una sola Imagen.
     * 
     */
    private void showImage(){       
        selectedFile = niconImage.openImage();
        
            if(selectedFile!=null){
                titleImage.setText(selectedFile.getName());
                image=new ImageIcon(selectedFile.getPath());
                panelViewer.setImage(image);
                panelViewer.cleanNiconViewer();
            }                        
    }
    
    /**
     * Este metodo es el encargado de obtener un directorio y cargar todas
     * sus imagenes en una lista de objetos que serán cargados en el visor de
     * Imágenes, el usuario podrá cambiar de imágen hacia adelante o atras del
     * vector con los comandos next and previos.
     */
    private void openDirectory(){ 
        niconImage.openImageDirectory();
        listFiles = niconImage.getListImages();
        System.out.println("Total archivo de imagen cargados: "+listFiles.size());
        nextImage();
    }
    
    private void nextImage(){
        panelViewer.cleanNiconViewer();
        listFiles = niconImage.getListImages();
        try{
            if(index<listFiles.size()){
                index++;
                NiconImageObject img;
                img= (NiconImageObject) listFiles.get(index);
                
                selectedFile = new File(img.getPath());
                titleImage.setText(selectedFile.getName());
                image=new ImageIcon(selectedFile.getPath());
                panelViewer.setImage(image);  
                panelViewer.cleanNiconViewer();
            }
        }catch(Exception e ){
            System.err.println("Ocurrio un error al obtener la imagen:\n"+e);
            index=listFiles.size()-1;
        }
    }
    
    public void previosImage(){
        try{
           if(index<listFiles.size()){
                index--;
                 NiconImageObject img;
                img= (NiconImageObject) listFiles.get(index);
                
                selectedFile = new File(img.getPath());
                titleImage.setText(selectedFile.getName());
                image=new ImageIcon(selectedFile.getPath());
                panelViewer.setImage(image);  
                panelViewer.cleanNiconViewer();           
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
    
    /**
     * este es el metodo que permite eliminar un archivo de imagen actual del 
     * disco duro hace uso de API NiconImageCore para el proceso de eliminacion,
     * 
     */
    private void deleteFile(){
        int option = JOptionPane.showConfirmDialog(null, "Esta a punto de eliminar la actual Imágen de su sistema\n ¿Realmente desea Hacerlo?");
            if(option==0){
                niconImage.deleteImage(selectedFile);
                panelViewer.loadNiconHome();
            }
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
        
        if(ae.getSource()==deleteFile){
            deleteFile();
        }
        
        if(ae.getSource()==viewDetail){
            niconDeatilsImage();
        }
        
    }   
}
