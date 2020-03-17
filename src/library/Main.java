/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.Arrays;

/**
 *
 * @author Exodia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        Book konyv1 = new Book("Maffiózók mackónadrágban", "Dezső András", "21. Század Kiadó", 2019, 955365, "1");
        Book konyv2 = new Book("Szupermarket", "Bobby Hall", "21. Század Kiadó", 2019, 321654, "2");
        Book konyv3 = new Book("Helymeghatarozo meresek", "Simon Lorand", "Sapientia", 2020, 111111, "3");
        
        BooksList konyvLista = new BooksList();
        konyvLista.hozzad(konyv1);
        konyvLista.hozzad(konyv2);
        konyvLista.hozzad(konyv3);
        konyvLista.kiListaz();
        System.out.println("\n");
        
        Book konyv4 = konyv3;
        konyvLista.hozzad(konyv4);
        konyvLista.kiListaz();
        System.out.println("\n");
        
        konyv4.setKiado("nincs");
        konyvLista.modosit(konyv4.getID(), konyv4);
        konyvLista.kiListaz();
        System.out.println("\n");
        
        konyvLista.keres(konyv1.getID());
        System.out.println("\n");
        
        konyvLista.torol(konyv1.getID());
        konyvLista.kiListaz();
        System.out.println("\n");
        
        konyvLista.konyvListaMentese();
        konyvLista.konyvListaBetoltes();
        */
        
        //Felhasznalok
        
        
        User user1 = new User("Barabas", new ContactAddress("barabas@gmail.com", "0748255986", "Vasarhley 1"), "1234567890");
        User user2 = new User("Valeria", new ContactAddress("valeria@yahoo.com", "0755202356", "Vasarhely 2"), "2345678901");
        User user3 = new User("Louluuu", new ContactAddress("louluou@12345.com", "0101010101", "Vasarhely 919"), "1212121212");
        
        UsersList felhasznaloLista = new UsersList();
        felhasznaloLista.hozzaad(user1);
        felhasznaloLista.hozzaad(user2);
        felhasznaloLista.hozzaad(user3);
        felhasznaloLista.kiListaz();
        System.out.println("\n");
        
        felhasznaloLista.keres(user2.getCNP());
        System.out.println("\n");
        
        felhasznaloLista.torol(user1.getCNP());
        felhasznaloLista.kiListaz();
        System.out.println("\n");
        
        //user3.setElerhetoseg("nincs","semmi@semmi","nincs");
        user3.setJelszo("asdasd");
        user3.setCNP("12312312312123123123");
        System.out.println("Jelszo: " + user3.getJelszo());
        System.out.println("CNP: " + user3.getCNP());
        System.out.println(user3.getElerhetoseg());
        user2.setNev("Kosa Valeria");
        felhasznaloLista.modosit(user2.getCNP(), user2);
        felhasznaloLista.kiListaz();
        System.out.println("\n");
        
        felhasznaloLista.userListMentese();
        felhasznaloLista.userListBetoltese();
        
        //konyvLista.konyvListaMentesXML();
        //konyvLista.konyvListaBetoltesXML();
        
        System.out.println("\n");
        felhasznaloLista.userListaMentesXML();
    }
}
