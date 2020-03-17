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
public class BooksList implements  Serializable{
    
    private List<Book> konyvek; 
    
    public BooksList(){
        konyvek = new ArrayList<Book>();
    }
    public Book keres(String ID){
        for(Book konyv : konyvek){
            if(konyv.getID().equals(ID))
                System.out.println(konyv);
            
        }
        return null;
    }
    public void hozzad(Book konyv){
        konyvek.add(konyv);
    }
    public Boolean torol(String ID){
        for(Book konyv : konyvek){
            if(konyv.getID().equals(ID))
                konyvek.remove(konyv);
            return true;
        }
        return false;
    }
    public Boolean modosit(String Id, Book konyv){
        for(Book konyvElem : konyvek){
            if(konyvElem.getID().equals(Id) ){
                konyvElem.setCim(konyv.getCim());
                konyvElem.setISBN(konyv.getISBN());
                konyvElem.setKiadasEve(konyv.getKiadasEve());
                konyvElem.setKiado(konyv.getKiado());
                konyv.setSzerzo(konyv.getSzerzo());
                return true;
            }
        }
        return false;
    }
    public void kiListaz(){
        for(Book konyv : konyvek)
            
        System.out.println(konyv.toString()+ " Kiado: " + konyv.getKiado()+ " ID: " + konyv.getID());
    }
    public Boolean konyvListaMentese(){
        String filename = "konyvek.txt";
        
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(konyvek);
            out.close();
            file.close();
            System.out.println("Sikeres kiiras");
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean konyvListaMentesXML(){
    try{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = (Document) docBuilder.newDocument();
        
	Element rootElement = doc.createElement("KonyvLista");
        doc.appendChild(rootElement);
        
        //most vegigmegyunk a listan
	for(Book book: konyvek){
            Element konyv = doc.createElement("Konyv");
            rootElement.appendChild(konyv);
            Attr attr = doc.createAttribute("id");
            
            attr.setValue(book.getID());
            konyv.setAttributeNode(attr);
            
            Element konyvCim = doc.createElement("Cim");
            konyvCim.appendChild(doc.createTextNode(book.getCim()));
            konyv.appendChild(konyvCim);
                
            Element konyvSzerzo = doc.createElement("Szerzo");
            konyvSzerzo.appendChild(doc.createTextNode(book.getSzerzo()));
            konyv.appendChild(konyvSzerzo);
                
            //kiado element
            Element konyvKiado = doc.createElement("Kiado");
            konyvKiado.appendChild(doc.createTextNode(book.getKiado()));
            konyv.appendChild(konyvKiado);
            
            //ISBN element
            Element konyvISBN = doc.createElement("ISBN");
            Integer i = book.getISBN();
            konyvISBN.appendChild(doc.createTextNode(i.toString()));
            konyv.appendChild(konyvISBN);
            
            //kiadasEve element
            Element konyvKiadasEve = doc.createElement("KiadasEve");
            konyvKiadasEve.appendChild(doc.createTextNode( ((Integer)book.getKiadasEve()).toString()  ));
            konyv.appendChild(konyvKiadasEve);
        }
        
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("konyvLista.xml"));
        
        transformer.transform(source, result);
 
        System.out.println("File saved!");
        return true;


    }catch(Exception e){
        e.printStackTrace();
    }
    return false;
}
    public Boolean konyvListaBetoltes(){
        String filename = "konyvek.txt";
        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream out2 = new ObjectInputStream(file);
            List<Book> ujLista = new ArrayList<Book>();
            ujLista = (List<Book>) out2.readObject();
            System.out.println("Sikeres beolvasas");
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return false;
    } 
    
    public void konyvListaBetoltesXML(){
        try{
            File fXmlFile = new File("konyvLista.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

	
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Konyv");
            //Node = Konyvtol konyvig
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                //System.out.println(nNode.getChildNodes());
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    //System.out.println("Konyv id : " + eElement.getAttribute("id"));
                    System.out.println("Konyv cim : " + eElement.getElementsByTagName("Cim").item(0).getTextContent());
                    System.out.println("Konyv szerzo : " + eElement.getElementsByTagName("Szerzo").item(0).getTextContent());
                    System.out.println("Konyv kiado : " + eElement.getElementsByTagName("Kiado").item(0).getTextContent());
                    System.out.println("Konyv ISBN : " + eElement.getElementsByTagName("ISBN").item(0).getTextContent());
                    System.out.println("Konyv kiadasEve : " + eElement.getElementsByTagName("KiadasEve").item(0).getTextContent());
                    
                }
            }
           // System.out.println("Betoltes SIKERES");
            
            
            

        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
        
}
