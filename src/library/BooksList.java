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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author Exodia
 */
public class BooksList implements  Serializable{
    
    public List<Book> konyvek; 
    
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
    
    public int meret()
    {
        return konyvek.size();
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
            konyvISBN.appendChild(doc.createTextNode(book.getISBN()));
            konyv.appendChild(konyvISBN);
            
            //kiadasEve element
            Element konyvKiadasEve = doc.createElement("KiadasEve");
            konyvKiadasEve.appendChild(doc.createTextNode(book.getKiadasEve()));
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
            konyvek = (List<Book>) out2.readObject();
            for(Book book : konyvek)
            {
                System.out.println(book);
            }
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
                
                Book k = new Book();
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    //System.out.println("Konyv id : " + eElement.getAttribute("id"));
                    System.out.println("Cim : " + eElement.getElementsByTagName("Cim").item(0).getTextContent());
                    System.out.println("Szerzo : " + eElement.getElementsByTagName("Szerzo").item(0).getTextContent());
                    System.out.println("Kiado : " + eElement.getElementsByTagName("Kiado").item(0).getTextContent());
                    System.out.println("ISBN : " + eElement.getElementsByTagName("ISBN").item(0).getTextContent());
                    System.out.println("KiadasEve : " + eElement.getElementsByTagName("KiadasEve").item(0).getTextContent());
                    
                    k.setID(eElement.getAttribute("id"));
                    k.setCim(eElement.getElementsByTagName("Cim").item(0).getTextContent());
                    k.setSzerzo(eElement.getElementsByTagName("Szerzo").item(0).getTextContent());
                    k.setKiado(eElement.getElementsByTagName("Kiado").item(0).getTextContent());
                    k.setISBN(eElement.getElementsByTagName("ISBN").item(0).getTextContent());
                    k.setKiadasEve(eElement.getElementsByTagName("KiadasEve").item(0).getTextContent());
                    konyvek.add(k);
                }
            }
           // System.out.println("Betoltes SIKERES");
            
            
            

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
