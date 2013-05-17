/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.image.core;

import java.util.Date;

/**
 * NiconImageData es una representacion de la informacion básica que se
 * mostrará al usuario con respecto a una imagen abierta, la informacion a 
 * mostrar llevara la siguiente estructura:
 * 
 *  Nombre de la imagen * 
 *  tamaño de la imagen
 *  extension de la imagen
 *  ubicacion de la imagen
 *  peso de la imagen
 *  fecha de creacion
 * @author frederick
 */


public class NiconImageData {
    
    private String nameImage;
    private int witdhImage;
    private int heightImage;
    private String extensionImage;
    private String urlImage;
    private double pundImage;
    private Date dateCreation;

    public NiconImageData(String nameImage, int witdhImage, int heightImage, String extensionImage, String urlImage, double pundImage, Date dateCreation) {
        this.nameImage = nameImage;
        this.witdhImage = witdhImage;
        this.heightImage = heightImage;
        this.extensionImage = extensionImage;
        this.urlImage = urlImage;
        this.pundImage = pundImage;
        this.dateCreation = dateCreation;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public int getWitdhImage() {
        return witdhImage;
    }

    public void setWitdhImage(int witdhImage) {
        this.witdhImage = witdhImage;
    }

    public int getHeightImage() {
        return heightImage;
    }

    public void setHeightImage(int heightImage) {
        this.heightImage = heightImage;
    }

    public String getExtensionImage() {
        return extensionImage;
    }

    public void setExtensionImage(String extensionImage) {
        this.extensionImage = extensionImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public double getPundImage() {
        return pundImage;
    }

    public void setPundImage(double pundImage) {
        this.pundImage = pundImage;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
            
}
