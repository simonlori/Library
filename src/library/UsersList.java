/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//import javax.lang.model.element.Element;
//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
//import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Exodia
 */
public class UsersList implements  Serializable{
    public List<User> userList;
    
    public UsersList(){
        userList = new ArrayList<User>();
    }
    
     public User keres(String ID){
         
             for(User user : userList){
             if(user.getCNP().equals(ID))
                System.out.println(user);
        }
        return null;
    }
     
     public int meret()
    {
        return userList.size();
    }
     
     public void hozzaad(User user){
        userList.add(user);
    }
     
     public Boolean torol(String ID){
        for(User user : userList){
            if(user.getCNP().equals(ID))
                userList.remove(user);
            return true;
        }
        return false;
    }
     
     public Boolean modosit(String CNP, User user){
         for(User u: userList){
             if(u.getCNP().equals(CNP) ){
                 u.setNev(user.getNev());
                 u.setElerhetoseg(user.getElerhetoseg());
                 return true;
             }
         }
         return false;
     }
     
     public void kiListaz(){
        for(User user : userList)
            
        System.out.println(user.toString());
     }
     
     public Boolean userListMentese(){
        String filename = "users.txt";
        //Konyv object = konyvek.get(0);
        
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(userList);
            out.close();
            file.close();
            System.out.println("Sikeres mentes!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
     
    public Boolean userListMentesXML(){
        try{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = (Document) docBuilder.newDocument();
        
	Element rootElement = doc.createElement("UserLista");
        doc.appendChild(rootElement);
        
        for(User u : userList){
                Element user = doc.createElement("User");
                rootElement.appendChild(user);
                user.setAttribute("id", u.getCNP());
                
                Element name = doc.createElement("Nev");
                name.appendChild(doc.createTextNode(u.getNev()));
                user.appendChild(name);
                
                    Element contactAddress = doc.createElement("Elerhetoseg");

                    Element telSzam = doc.createElement("telSzam");
                    telSzam.appendChild(doc.createTextNode(u.getElerhetoseg().getTelSzam()));

                    Element utcaNev = doc.createElement("UtcaNev");
                    utcaNev.appendChild(doc.createTextNode(u.getElerhetoseg().getUtcaNev()));

                    Element email = doc.createElement("Email");
                    email.appendChild(doc.createTextNode(u.getElerhetoseg().getEmail()));

                    contactAddress.appendChild(telSzam);
                    contactAddress.appendChild(utcaNev);
                    contactAddress.appendChild(email);
                    
                user.appendChild(contactAddress);

            }
         // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("userLista.xml"));
        
        transformer.transform(source, result);
 
        System.out.println("File saved!");
        return true;
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
     
    public Boolean userListBetoltese(){
        String filename = "users.txt";
        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream out2 = new ObjectInputStream(file);
            userList = (List<User>) out2.readObject();
            for(User user : userList)
            {
                System.out.println(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    } 
    
    public void userListBetoltesXML(){
        try{
            File fXmlFile = new File("userLista.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
	
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("User");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                
                User u = new User();
                ContactAddress c = new ContactAddress();

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("ID : " + eElement.getAttribute("id"));
                    System.out.println("Nev : " + eElement.getElementsByTagName("Nev").item(0).getTextContent());
                    System.out.println("Telefonszam : " + eElement.getElementsByTagName("telSzam").item(0).getTextContent());
                    System.out.println("Utca/hazszam : " + eElement.getElementsByTagName("UtcaNev").item(0).getTextContent());
                    System.out.println("Email : " + eElement.getElementsByTagName("Email").item(0).getTextContent());
                    
                    u.setCNP(eElement.getAttribute("id"));
                    u.setNev(eElement.getElementsByTagName("Nev").item(0).getTextContent());
                    c.setTelSzam(eElement.getElementsByTagName("telSzam").item(0).getTextContent());
                    c.setUtcaNev(eElement.getElementsByTagName("UtcaNev").item(0).getTextContent());
                    c.setEmail(eElement.getElementsByTagName("Email").item(0).getTextContent());
                    u.setElerhetoseg(c);
                    userList.add(u);
                  
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void removeSelectedFromTable(JTable from)
    {
        int[] rows = from.getSelectedRows();
        TableModel tm= from.getModel();

        while(rows.length>0)
        {
            ((DefaultTableModel)tm).removeRow(from.convertRowIndexToModel(rows[0]));

            rows = from.getSelectedRows();
        }
            from.clearSelection();
    }
}
