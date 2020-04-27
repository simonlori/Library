/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.Serializable;

/**
 *
 * @author Exodia
 */
public class Book implements Serializable{
    private String cim;
    private String szerzo;
    private String kiado;
    private String kiadasEve;
    private String ISBN;
    private String ID;
    private String kolcsonozhetoe;
    
    public Book(String cim, String szerzo, String kiado, String kiadasEve, String ISBN, String ID, String kolcsonozhetoe){
        this.cim = cim;
        this.szerzo = szerzo;
        this.kiado = kiado;
        this.ISBN = ISBN;
        this.cim = cim;
        this.kiadasEve = kiadasEve;
        this.ID = ID;
        this.kolcsonozhetoe = kolcsonozhetoe;
    }

    public Book() {
        
    }
        
    public void setCim(String cim){
        this.cim = cim;
    }
    
    public void setKolcsonozhetoe(String kolcsonozhetoe){
        this.kolcsonozhetoe = kolcsonozhetoe;
    }
    
    public void setSzerzo(String szerzo){
        this.szerzo = szerzo;
    }

    public void setKiado(String kiado) {
        this.kiado = kiado;
    }

    public void setKiadasEve(String kiadasEve) {
        this.kiadasEve = kiadasEve;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getCim() {
        return cim;
    }
    
    public String getKolcsonozhetoe() {
        return kolcsonozhetoe;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public String getKiado() {
        return kiado;
    }

    public String getKiadasEve() {
        return kiadasEve;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getID() {
        return ID;
    }
    
    public String toString(){
        
        return "Szerzo: " + szerzo + " Cim: " + cim ;
    }
    
    
}
