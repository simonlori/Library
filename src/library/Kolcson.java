/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package library;

import java.io.Serializable;

/**
 *
 * @author Lori
 */
public class Kolcson implements  Serializable{
        String ID;
        String datum;
        Book konyv;
        User felhasznalo;

    public Kolcson(String ID, String datum, Book konyv, User felhasznalo) {
        this.ID = ID;
        this.datum = datum;
        this.konyv = konyv;
        this.felhasznalo = felhasznalo;
    }
        

    public void toString(Book konyv, User felhasznalo){
        System.out.println(konyv);
        System.out.println(felhasznalo); 
    }
        
    public String getID() {
        return ID;
    }

    public String getDatum() {
        return datum;
    }

    public Book getKonyv() {
        return konyv;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setKonyv(Book konyv) {
        this.konyv = konyv;
    }

    public void setFelhasznalo(User felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    public User getFelhasznalo() {
        return felhasznalo;
    }
}
