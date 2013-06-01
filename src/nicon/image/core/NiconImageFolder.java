/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import nicon.image.util.NiconImageObject;

/**
 *
 * @author Dony
 */
public class NiconImageFolder extends Thread{
public boolean Estado = true;    
private File Carpeta;
private List<NiconImageObject> ListIMG = new ArrayList();
private File[] ListFiles;
private String Name;
private String Path;
private NiconImageObject IMG;
public NiconImageFolder(File a){
Carpeta = a;

}

public List getImagesList(){
return ListIMG;  
}

@Override
public void run(){
if (Carpeta.exists()==false){
//Si la Carpeta no existe...
}else{
ListFiles = Carpeta.listFiles();
int zero = 0;

while(zero<ListFiles.length){
Name = ListFiles[zero].getName();
Path = ListFiles[zero].getPath();

if(Name.endsWith(".png")==true || Name.endsWith(".bmp")==true || Name.endsWith(".gif")==true
        || Name.endsWith(".jpg")==true || Name.endsWith(".jpeg")==true){
    
IMG = new NiconImageObject(Name,Path);    
ListIMG.add(IMG);
System.out.println("Añadiendo: "+Name);
}
zero++;
}
} 
Estado = false;
}
}
