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

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    
    private JFileChooser fileChooser;    
    private FileNameExtensionFilter filter;
    
    private int value;
    private boolean state;  
    private String nameFile;
    private String extensionFile;

    /**
     * este contructor inicializa una nueva instancia del ImageCore que brinda
     * todos los metodos de base del visor de imagenes.
     */
    public NiconImageCore() {
        fileChooser = new JFileChooser();
        filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif", "png");
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
    public File openFileImage() {
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
    public File openDirectory() {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        value = fileChooser.showOpenDialog(null);
        
            if (value == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            }
        return selectedFile;
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
    public boolean renameFile(File imageFile, String name) {
        if (imageFile.exists()) {
            nameFile = imageFile.getParent() + File.separator + name + getExtensionFile(imageFile);
            renameFile = new File(nameFile);
                if (imageFile.renameTo(renameFile)) {
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
        return extensionFile;
    }
}
