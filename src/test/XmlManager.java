import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlManager {
   static public void main(String[] argv) throws ParserConfigurationException, IOException, SAXException, Exception {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setIgnoringElementContentWhitespace(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      File file = new File("./test.xml");
      Document doc = builder.parse(file);

      // saveData(doc);
      // printElements(doc);
      readData(doc);
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

   static void readData(Document doc) {
      NodeList objects = doc.getElementsByTagName("person");
      Element elem;
      int len;

      len = objects.getLength();
      elem = (Element) objects.item(len - 1);

      NodeList features = elem.getChildNodes();

      len = features.getLength();

      for (int i=0; i < len; i++) {
         Node node = features.item(i);

         if (node.getNodeType() == Node.ELEMENT_NODE) {
            elem = (Element) node;

            System.out.println(elem.getTagName() + " : " + elem.getTextContent());
         }
      }
   }

   static void saveData(Document doc) throws Exception {
      Element element = doc.getDocumentElement();

      Node node = doc.createElement("person");

      Node nom = doc.createElement("name");
      nom.setTextContent("Juan");

      Node lastN = doc.createElement("lastName");
      lastN.setTextContent("Romero");

      node.appendChild(nom);
      node.appendChild(lastN);

      element.appendChild(node);

      replaceOldFile(doc);
   }

   public static void replaceOldFile(Document xml) throws Exception {
      Transformer tf = TransformerFactory.newInstance().newTransformer();
      tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      tf.setOutputProperty(OutputKeys.INDENT, "yes");

      Writer out = new StringWriter();
      tf.transform(new DOMSource(xml), new StreamResult(out));

      String allFile = out.toString();

      FileWriter file = new FileWriter("./test.xml"); 
      file.write(allFile);
      file.close();
   }
}
