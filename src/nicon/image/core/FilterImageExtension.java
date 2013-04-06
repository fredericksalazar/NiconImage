/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.core;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author frederick
 */
public class FilterImageExtension implements FilenameFilter {

    boolean acept = false;

    @Override
    public boolean accept(File dir, String name) {
        
        if (name != null) {
            if(name.toLowerCase().endsWith(".png")||name.toLowerCase().endsWith(".jpg")||
               name.toLowerCase().endsWith(".jpeg")){
                acept=true;
            }
        } 
       return acept;
    }
}
