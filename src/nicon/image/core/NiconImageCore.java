/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.core;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * esta clase define todos los metodos basicos del sistema para la carga de
 * imagenes, puede bien abrir una sola imagen y retornarla para ser cargada
 * dentro del visor.
 *
 * @author frederick
 */
public class NiconImageCore {

    private JFileChooser fileChooser;
    private File selectedFile;
    private int value;
    private boolean state;
    FileNameExtensionFilter filter;
    private File renameFile;
    private String nameFile;
    private String extensionFile;

    public NiconImageCore() {
        fileChooser = new JFileChooser();
        filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif", "png");
    }

    public FileNameExtensionFilter getFilter() {
        return filter;
    }

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

    public File openDirectory() {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        value = fileChooser.showOpenDialog(null);
        if (value == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getName());
        }
        return selectedFile;
    }

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
            JOptionPane.showMessageDialog(null, "El archivo NO existe, no se pudo renombrar");
        }
        return state;
    }

    public String getExtensionFile(File file) {
        if (file.getName().endsWith(".png")) {
            extensionFile = ".png";
        }
        if (file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
            extensionFile = ".jpg";
        }
        System.out.println(extensionFile);
        return extensionFile;

    }
}
