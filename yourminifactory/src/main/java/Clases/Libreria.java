/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author angelozurita
 */
public class Libreria {
    private int id_libreria;
    private String name;

    public Libreria(int id_libreria, String name) {
        this.id_libreria = id_libreria;
        this.name = name;
    }

    public int getId_libreria() {
        return id_libreria;
    }

    public void setId_libreria(int id_libreria) {
        this.id_libreria = id_libreria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
