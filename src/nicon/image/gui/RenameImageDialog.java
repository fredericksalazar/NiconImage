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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nicon.image.core.conf.NiconImageConf;
import nicon.image.core.NiconImageCore;

/**
 * esta clase define una herramienta gráfica para renombrar una imagen que 
 * esta siendo visualizada, hereda directamente de JDialog y su aspecto
 * minimalista debe permitir al usuario poder hacer la operacion rápida
 * y fácilmente, con dos funciones básicas ESC ayuda a cerrar la caja de 
 * Dialogo y ENTER ayuda a renombrar la imagen.
 *
 * @author Frederick Adolfo Salazar Sanchez
 * @version 1.1
 * @fecha: 17/05/2013
 */

public class RenameImageDialog extends JDialog{
    
    private NiconImageCore niconImageCore;
    private NiconImageConf niConf;
    private File renaneImage;
    
    private JPanel panel;
    private JLabel titulo;
    
    private JTextField textEditor;
    
    private int keyCounter=0;
    
    /**
     * Unico constructor por defecto para esta herramienta, define todos los
     * aspectos basicos de la interfaz y recibe como parametro el Objeto File
     * que representa la imagen que será renombrada, hace uso del API de 
     * NiconImageCore para las operaciones.
     * 
     * @param file 
     */
    public RenameImageDialog(File file){
        setSize(540,120);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setModal(true);
        this.renaneImage=file;
        niConf=NiconImageConf.getInstance();
        init();
    }

    private void init() {        
        NiconImageCore.addEscapeListenerWindowDialog(this);
        
        panel=new JPanel();
        panel.setBackground(niConf.getPanelColor());
        panel.setLayout(null);
        
        titulo=new JLabel("Renombrar Archivo");
        titulo.setFont(niConf.getTitleFont());
        titulo.setForeground(Color.white);
        titulo.setBounds(10, 5, 600, 30);
        
        textEditor=new JTextField("Ingrese el nuevo nombre de la imágen:");
        textEditor.setFont(niConf.getTextItalicFont());
        textEditor.setForeground(Color.gray);
        textEditor.setBounds(20,60, 500, 30);
        textEditor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                textEditor.setText("");
            }
        });
        textEditor.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(keyCounter==0){
                    textEditor.setText("");
                    keyCounter++;
                }
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    niconImageCore=new NiconImageCore();
                        if(niconImageCore.renameImage(renaneImage, textEditor.getText())){
                            dispose();
                        }
                }
            }
        });
        
        
        panel.add(titulo);
        panel.add(textEditor);
        
        this.getContentPane().add(panel);
        
    }
}
