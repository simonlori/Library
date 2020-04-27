/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package library;

import java.io.File;
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
 * @author Lori
 */
public class KolcsonLista implements  Serializable{
    
    public List<Kolcson> kolcson;
    
    public KolcsonLista()
    {
        kolcson = new ArrayList<Kolcson>();
    }
    
    public void hozzaad (Kolcson kikolcsonzott)
    {
        kolcson.add(kikolcsonzott);
    }
    
    public int meret()
    {
        return kolcson.size();
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
    
    public Boolean torol(String ID)
    {
        for(Kolcson kikolcsonzott : kolcson)
        {
            if(kikolcsonzott.getID().equals(ID))
            {
                kolcson.remove(kikolcsonzott);
                return true;
            }       
        }
        return false;
    }
    
    public void Listazas()
    {
        for(Kolcson kikolcsonzott : kolcson)
        {
            System.out.println(kikolcsonzott.toString());
        }
    }
    
    public Boolean konlcsonListaMentesXML(){
    try{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = (Document) docBuilder.newDocument();
        
	Element rootElement = doc.createElement("KolcsonLista");
        doc.appendChild(rootElement);
        
        //most vegigmegyunk a listan
	for(Kolcson kolcson1: kolcson){
            Element konyv = doc.createElement("Kolcson");
            rootElement.appendChild(konyv);
            Attr attr = doc.createAttribute("id");
            
            attr.setValue(kolcson1.getID());
            konyv.setAttributeNode(attr);
            
            Element konyvCim = doc.createElement("Datum");
            konyvCim.appendChild(doc.createTextNode(kolcson1.getDatum()));
            konyv.appendChild(konyvCim);
                
            Element konyvSzerzo = doc.createElement("Konyv_cim");
            konyvSzerzo.appendChild(doc.createTextNode(kolcson1.getKonyv().getCim()));
            konyv.appendChild(konyvSzerzo);
                
            //kiado element
            Element konyvKiado = doc.createElement("Konyv_id");
            konyvKiado.appendChild(doc.createTextNode(kolcson1.getKonyv().getID()));
            konyv.appendChild(konyvKiado);
            
            //ISBN element
            Element konyvISBN = doc.createElement("User_nev");
            konyvISBN.appendChild(doc.createTextNode(kolcson1.getFelhasznalo().getNev()));
            konyv.appendChild(konyvISBN);
            
            //kiadasEve element
            Element konyvKiadasEve = doc.createElement("User_id");
            konyvKiadasEve.appendChild(doc.createTextNode(kolcson1.getFelhasznalo().getCNP()));
            konyv.appendChild(konyvKiadasEve);
        }
        
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("kolcsonLista.xml"));
        
        transformer.transform(source, result);
 
        System.out.println("File saved!");
        return true;


    }catch(Exception e){
        e.printStackTrace();
    }
    return false;
}
    
    public void kolcsonListaBetoltesXML(){
        try{
            File fXmlFile = new File("kolcsonLista.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
	
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Kolcson");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
               
                Book k = new Book();
                User u = new User();
                String a,b;
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("ID : " + eElement.getAttribute("id"));
                    System.out.println("Datum : " + eElement.getElementsByTagName("Datum").item(0).getTextContent());
                    System.out.println("Konyv cime : " + eElement.getElementsByTagName("Konyv_cim").item(0).getTextContent());
                    System.out.println("Konyv id : " + eElement.getElementsByTagName("Konyv_id").item(0).getTextContent());
                    System.out.println("User neve : " + eElement.getElementsByTagName("User_nev").item(0).getTextContent());
                    System.out.println("User id : " + eElement.getElementsByTagName("User_id").item(0).getTextContent());
                    
                    a = (eElement.getAttribute("id"));
                    b = (eElement.getElementsByTagName("Datum").item(0).getTextContent());
                    k.setCim(eElement.getElementsByTagName("Konyv_cim").item(0).getTextContent());
                    k.setID(eElement.getElementsByTagName("Konyv_id").item(0).getTextContent());
                    u.setNev(eElement.getElementsByTagName("User_nev").item(0).getTextContent());
                    u.setCNP(eElement.getElementsByTagName("User_id").item(0).getTextContent());
                    Kolcson x = new Kolcson(a,b,k,u);
                    kolcson.add(x);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
