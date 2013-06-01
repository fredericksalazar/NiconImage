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

package nicon.image.core;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URI;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import nicon.image.gui.JNiconImage;

/**
 * esta clase define todos los metodos basicos del sistema para la carga de
 * imagenes, puede bien abrir una sola imagen y retornarla para ser cargada
 * dentro del visor.
 *
 * @author Frederick Adolfo Salazar Sanchez
 */
public class NiconImageCore {

    private File selectedFile;
    private File renameFile;
    private NiconImageFolder selectedFolder;
    private JFileChooser fileChooser;    
    private FileNameExtensionFilter filter;
    
    private int value;
    private boolean state;  
    private String nameFile;
    private String extensionFile;
    private NiconImageData imageData;

    /**
     * este contructor inicializa una nueva instancia del ImageCore que brinda
     * todos los metodos de base del visor de imagenes.
     */
    public NiconImageCore() {
        fileChooser = new JFileChooser();
        filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "*.jpg", "*.gif", "*.png","*.bmp");
    }

    /**
     * Retorna el fileExtensionFilter del sistema (Filtro de extensiones de archivos)
     * @return filter
     */
    public FileNameExtensionFilter getFilter() {
        return filter;
    }

    /**
     * Este metodo permite abrir un archivo a traves de la caja de dialogo de 
     * FileChooser, al hacer la carga del archivo solo permite cuyos archivos
     * sean imagenes, solo permite cargar un archivo.
     * 
     * @return File selectedFile
     */
    public File openImage() {
        fileChooser.setSize(300, 200);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(filter);
        value = fileChooser.showOpenDialog(null);
        
            if (value == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            }
        return selectedFile;
    }

    /**
     * Este metodo permite obtener un File que representa un directorio
     * dentro del sistema de archivos, este metodo es usado para la carga de
     * directorio de imagenes dentro del visor de imagenes, Hace uso del 
     * JFileChooser.
     * 
     * @return File selectedFile (directorio).
     */
    public void openImageDirectory() {
        selectedFolder = null;
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        value = fileChooser.showOpenDialog(null);
        if (value == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            
        selectedFolder = new NiconImageFolder(selectedFile);
        selectedFolder.start();
        while(selectedFolder.Estado == true){
        System.out.println("Cargando ...");
        }
        }  
        
    }
    
    public List getListImages(){
    return selectedFolder.getImagesList();
    }

    /**
     * Permite renombrar el archivo que contiene una imagen dentro del sistema
     * de archivos, este metodo es usado para cambiar de nombre la actual imagen
     * que esta siendo visualizada por el usuario, recibe como parametros el archivo
     * que será renombrado y el nuevo nombre de la imagen, retorna un valor boolean
     * con el resultado de la operación.
     * 
     * @param imageFile
     * @param name
     * @return 
     */
    public boolean renameImage(File imageFile, String name) {
        if (imageFile.exists()) {
            nameFile = imageFile.getParent() + File.separator + name + getExtensionFile(imageFile);
            renameFile = new File(nameFile);
                if (imageFile.renameTo(renameFile)) {
                    JNiconImage.setTitleImage(name);
                    state = true;
                } else {
                    state = false;
                }
        } else {
            state=false;
        }
        return state;
    }
    
    /**
     * Este metodo permite eliminar una imagen del sistema de archivos, recibe
     * como parametros el archivo que almacena la imagen que sera eliminada.
     * 
     * @param file
     * @return boolean state
     */
    public boolean deleteImage(File file){
        if(file.exists()){
            state=file.delete();
        }else{
            state=false;
        }
        return state;
    }

    /**
     * Este metodo permite conocer la extension de un archivo, recibe como
     * parametros el File (Archivo) y retorna el tipo de extension del mismo.
     * 
     * @param file
     * @return extensionFile
     */
    public String getExtensionFile(File file) {
        
        if (file.getName().endsWith(".png")) {
            extensionFile = ".png";
        }
        if (file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
            extensionFile = ".jpg";
        }
        if (file.getName().endsWith(".bmp")) {
            extensionFile = ".bmp";
        }
        
        
        return extensionFile;
    }
    
    /**
     * Este metodo permite obtener informacion básica de la imágen que esta siendo
     * vista actualmente, hace uso de NiconImageData para los metaDatos de la 
     * imagen que será mostrados al usuario en una JDialog.
     * 
     * @param file
     * @return NiconImagedata imageData
     */
    public NiconImageData getNiconImageData(File file){
         imageData=new NiconImageData(file.getName(),
                                      NiconImageViewer.getWidthImage(),NiconImageViewer.getHeigthImage()
                                     ,getExtensionFile(file),file.getAbsolutePath(),file.length(),null);
        return imageData;
    }
    
    /**
     * Este metodo permite agregar un evento a un JDialog que permite que el usuario pueda cerrar dicho
     * window al presionar la tecla ESCAPE, este metodo puede ser usado desde cualquier tipo de JDialog
     * e implementa un ActionListener para el manejador de eventos de la ventana, al mismo tiempo
     * a la v¡JDialog le es asignado un registerKeyBoardAction y le es pasado el escAction para cuando el 
     * componente sea el poseedor del foco.
     * 
     * @param windowDialog 
     */
    public static void addEscapeListenerWindowDialog( final JDialog windowDialog) {    
        ActionListener escAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowDialog.dispose();
            }            
        };
        windowDialog.getRootPane().registerKeyboardAction(escAction,
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);        
    }
    
    /**
     * Este metodo permite abrir en el navegador web predeterminado del sistema
     * operativo los enlaces a las páginas oficiales de NiconSystem en 
     * Facebook y demas servicios, recibe como parametro el id del sitio web
     * al que accederá y hace el llamado al OS.
     * 
     * @param site 
     */
    public static void openNiconURL(int site){
        if(java.awt.Desktop.isDesktopSupported()){
            if(site==0){
                try{
                    Desktop dk = Desktop.getDesktop();
                    dk.browse(new URI("https://www.facebook.com/NiconSystem"));
                }catch(Exception e){
                    System.out.println("Error al abrir URL: "+e.getMessage());
                }
            }
            if(site==1){
                try{
                    Desktop dk = Desktop.getDesktop();
                    dk.browse(new URI("http://niconsystem.wordpress.com/productos-y-servicios/niconimage/"));
                }catch(Exception e){
                    System.out.println("Error al abrir URL: "+e.getMessage());
                }
            }
        }  
    }
    
}
