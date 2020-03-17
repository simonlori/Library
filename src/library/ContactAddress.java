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
public class ContactAddress implements Serializable{
    
    private String telSzam;
    private String utcaNev;
    private int hazSzam;
    private String email;
    
    public ContactAddress(){
        
    }
    public ContactAddress(String email, String telSzam, String lakcim) {
        if(email.contains("@")){
            this.email = email; 
            this.utcaNev = lakcim;
            this.telSzam = telSzam;
        }
        /*else{
            throw new Exception("Email kell tartalmazzon @-jelt");
            
        } */ 
    }

    public String getTelSzam() {
        return telSzam;
    }

    public void setTelSzam(String telSzam) {
        this.telSzam = telSzam;
    }

    public String getUtcaNev() {
        return utcaNev;
    }

    public void setUtcaNev(String utcaNev) {
        this.utcaNev = utcaNev;
    }

    public int getHazSzam() {
        return hazSzam;
    }

    public void setHazSzam(int hazSzam) {
        this.hazSzam = hazSzam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
        return (" TelSzam: " + this.telSzam + " Utca: "+ this.utcaNev +  " E-mail: " + this.email);
    }
    
}
    

    
    
    

