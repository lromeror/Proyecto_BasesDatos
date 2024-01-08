/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;

/**
 *
 * @author davsu
 */
public class Model {
    private int idModel;
    private String description;
    private double price;
    private String title;
    private String publicationDate; // Assuming a date without time information
    private int libraryId; // this connect it with the user
    private int visibility; 
    private String image;

    public Model(int idModel, String description, double price, String title, String publicationDate, int libraryId, int visibility) {
        this.idModel = idModel;
        this.description = description;
        this.price = price;
        this.title = title;
        this.publicationDate = publicationDate;
        this.libraryId = libraryId;
        this.visibility=visibility;
        if(idModel<=20){
            this.image= "Images/Images_compras/im_"+idModel+".jpg";
        }
    }
    
    public Model(int idModel, String description, double price, String title, String publicationDate, int libraryId, int visibility, String img){
        this.idModel = idModel;
        this.description = description;
        this.price = price;
        this.title = title;
        this.publicationDate = publicationDate;
        this.libraryId = libraryId;
        this.visibility=visibility;
        this.image= img;
    }
    
    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int isVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
}
