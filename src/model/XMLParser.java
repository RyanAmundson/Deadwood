package model;
import java.io.File;
import java.lang.Object;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
public class XMLParser{






  public static void readXML(File xml){

    Document dom;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try{


      DocumentBuilder db = dbf.newDocumentBuilder();
      dom=db.parse(xml);
      Element doc = dom.getDocumentElement();

      System.out.println("Root element: " + doc.getAttribute("name"));
      NodeList setList = doc.getElementsByTagName("set");
      int i = 0;
      while(i < setList.getLength()){
        System.out.println(setList.item(i).getAttributes().getNamedItem("name"));
        Element sets = (Element)setList.item(i);
        System.out.println(sets.getAttributes().getNamedItem("name"));
        NodeList neighborList = sets.getElementsByTagName("neighbor");
        NodeList takeList = sets.getElementsByTagName("take");
        NodeList partList = sets.getElementsByTagName("part");
        System.out.println("  first Neighbor: "+ neighborList.item(0).getAttributes().getNamedItem("name"));
        System.out.println("  second Neighbor: "+ neighborList.item(1).getAttributes().getNamedItem("name"));
        System.out.println("  third Neighbor: "+ neighborList.item(2).getAttributes().getNamedItem("name"));

        System.out.println("  take "+ takeList.item(0).getAttributes().getNamedItem("number"));
        if(takeList.getLength() > 1){
          System.out.println("  take "+ takeList.item(1).getAttributes().getNamedItem("number"));
        }
        if(takeList.getLength() > 2){
          System.out.println("  take "+ takeList.item(2).getAttributes().getNamedItem("number"));
        }
        System.out.println("  part one: "+ partList.item(0).getAttributes().getNamedItem("name"));
        if(partList.getLength() > 1){
        System.out.println("  part two: "+ partList.item(1).getAttributes().getNamedItem("name"));
        }
        if(partList.getLength() > 2){
          System.out.println("  part three: "+ partList.item(2).getAttributes().getNamedItem("name"));
        }
        System.out.println();
        i++;
      }
      //System.out.println(setList.getLength());

        //Node set = setList.item(0);
        //NodeList neighbors = set.getChildNodes().item(0).getChildNodes();


        //System.out.println(set.getAttributes().getNamedItem("name"));
        //System.out.println(set.hasChildNodes());
        //System.out.println(set.getChildNodes().item(0).getNodeType());
        //System.out.println(set.getChildNodes().item(0).getNodeName());
        //System.out.println(set.getChildNodes().item(1).getChildNodes().item(0));
        //System.out.println(set.getChildNodes().item(0).getChildNodes().item(0).getAttributes().getNamedItem("name"));

        //System.out.println(neighbors.item(0).getAttributes().getNamedItem("name"));
        //System.out.println(set.getFirstChild().getAttributes().getNamedItem("name"));
        //System.out.println(set.getLastChild().getAttributes().getNamedItem("name").getTextContent());



    } catch (ParserConfigurationException pce) {
      System.out.println(pce.getMessage());
    } catch (SAXException se) {
      System.out.println(se.getMessage());
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }










}
