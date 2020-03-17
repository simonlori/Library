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
public class User implements Serializable{
    private String nev;
    ContactAddress elerhetoseg;
    private String jelszo;
    private String CNP;
    
    public User(){
        
    }
    
    public User(String nev, ContactAddress elerhetoseg, String CNP){
        this.nev = nev;
        this.elerhetoseg = elerhetoseg;
        this.CNP = CNP;
    }
    @Override
    public String toString(){
        return ("Nev: " + this.nev + this.elerhetoseg.toString() + " CNP: " + this.CNP );
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public ContactAddress getElerhetoseg() {
        return elerhetoseg;
    }

    public void setElerhetoseg(ContactAddress elerhetoseg) {
        this.elerhetoseg = elerhetoseg;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
    
}
