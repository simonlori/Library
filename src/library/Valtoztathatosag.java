/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

/**
 *
 * @author Exodia
 */
public interface Valtoztathatosag {
    public void hozzaad(Book konyv);
            
     public Boolean torol(String s);
     
     public Boolean modosit(String s, Book k);
     
     public Book keres(String Id);
}
