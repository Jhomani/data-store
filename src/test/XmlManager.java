import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

public class XmlManager {
   static public void main(String[] argv) throws ParserConfigurationException, IOException, SAXException {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(true);
      factory.setIgnoringElementContentWhitespace(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      File file = new File("./test.xml");
      Document doc = builder.parse(file);

      // printElements(doc);
      printElementAttributes(doc);
      // Do something with the document here.
   }

   static void printElements(Document doc) {
      NodeList nl = doc.getElementsByTagName("*");
      Node n;
         
      for (int i=0; i<nl.getLength(); i++) {
         n = nl.item(i);
         System.out.print(n.getNodeName() + " ");
      }

      System.out.println();
   }

   static void printElementAttributes(Document doc) {
      NodeList nl = doc.getElementsByTagName("*");
      Element e;
      Node n;
      NamedNodeMap nnm;

      String attrname;
      String attrval;
      int i, len;

      len = nl.getLength();

      for (int j=0; j < len; j++) {
         e = (Element)nl.item(j);
         System.out.println(e.getTagName() + ":");
         nnm = e.getAttributes();

         if (nnm != null) {
            for (i=0; i<nnm.getLength(); i++) {
               n = nnm.item(i);
               attrname = n.getNodeName();
               attrval = n.getNodeValue();
               System.out.print(" " + attrname + " = " + attrval);
            }
         }
         System.out.println();
      }
   }
}
