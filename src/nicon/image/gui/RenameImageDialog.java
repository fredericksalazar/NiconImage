/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.gui;

import java.awt.Color;
import java.awt.Font;
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
 *
 * @author frederick
 */
public class RenameImageDialog extends JDialog{
    
    private NiconImageCore niconCore;
    private NiconImageConf niConf;
    private File file;
    
    private JPanel panel;
    private JLabel titulo;
    
    private JTextField textEditor;
    
    private int keyCounter=0;
    
    public RenameImageDialog(File file){
        setSize(540,120);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setModal(true);
        this.file=file;
        niConf=NiconImageConf.getInstance();
        init();
    }

    private void init() {
        panel=new JPanel();
        panel.setBackground(niConf.getPanelColor());
        panel.setLayout(null);
        
        titulo=new JLabel("Renombrar Archivo");
        titulo.setFont(niConf.getTitleFont());
        titulo.setForeground(Color.white);
        titulo.setBounds(10, 5, 600, 30);
        
        textEditor=new JTextField("Ingrese el nuevo nombre de la imágen:");
        textEditor.setFont(new Font("Ubuntu",Font.ITALIC,14));
        textEditor.setForeground(Color.darkGray);
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
                    niconCore=new NiconImageCore();
                        if(niconCore.renameImage(file, textEditor.getText())){
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
