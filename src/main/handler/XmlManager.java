package handler;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlManager {
   private String path;
   private Document doc;

   public XmlManager(String path) {
      this.path = path;
      start();
   }

   public void start()  {
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setValidating(false);
         DocumentBuilder builder = factory.newDocumentBuilder();

         File file = new File(path);
         this.doc = builder.parse(file);
         
      } catch (Exception e) {
         // throws ParserConfigurationException, IOException, SAXException
         System.out.println(e);
      }
   }

   public void setPath(String path) {
      this.path = path;
      start();
   }

   public <T> T readData(Class<T> klazz) {
      Element elem;
      int len;
      String name;
      String values = "";
      T res = null;

      name = klazz.getSimpleName().toLowerCase();

      NodeList tags = doc.getElementsByTagName(name);

      len = tags.getLength();

      if(len > 0) {
         elem = (Element) tags.item(len - 1);

         tags = elem.getChildNodes();
         len = tags.getLength();

         for (int i=0; i < len; i++) {
            Node node = tags.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
               elem = (Element) node;

               values += elem.getTextContent() + ", ";
            }
         }

         res = Utils.toObjectInstace(klazz, values);
      }

      return res;
   }

   public <T> T readData(Class<T> klazz, int nObject) {
      Element elem;
      int len;
      String name;
      String values = "";
      T res = null;

      name = klazz.getSimpleName().toLowerCase();

      NodeList tags = doc.getElementsByTagName(name);

      len = tags.getLength();

      if(len > 0) {
         if(nObject > len || nObject < 1) nObject = len;

         elem = (Element) tags.item(nObject - 1);

         tags = elem.getChildNodes();
         len = tags.getLength();

         for (int i=0; i < len; i++) {
            Node node = tags.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
               elem = (Element) node;

               values += elem.getTextContent() + ", ";
            }
         }

         res = Utils.toObjectInstace(klazz, values);
      }

      return res;
   }
   public <T> void saveData(SuperData model) throws Exception {
      Element element = doc.getDocumentElement();
      String name;
      String[] keys;
      String[] values;
      Node features;

      name = model.getClass().getSimpleName().toLowerCase();
      
      Node node = doc.createElement(name);

      keys = model.getHeader(model);
      values = model.getValues(model);

      for(int i=0; i < keys.length; i++) {
         features = doc.createElement(keys[i]);
         features.setTextContent(values[i]);

         node.appendChild(features);
      }


      element.appendChild(node);

      replaceOldFile();
   }

   public void replaceOldFile() throws Exception {
      Transformer tf = TransformerFactory.newInstance().newTransformer();
      tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      tf.setOutputProperty(OutputKeys.INDENT, "yes");

      Writer out = new StringWriter();
      tf.transform(new DOMSource(doc), new StreamResult(out));

      String allFile = out.toString();
      FileWriter file = new FileWriter(path); 
      file.write(allFile);
      file.close();
   }
}
