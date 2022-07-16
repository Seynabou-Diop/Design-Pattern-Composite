import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
public class Main {
    public static String pathToxml (String path) {
        File dir  = new File(path);
        String chaine ="<Directory name=\""+dir.getName() +"\" >\n"; ;
            File[] liste = dir.listFiles();
            for(File item : liste){
             if(item.isDirectory())
              {
 
                chaine+=pathToxml(item.getAbsolutePath());
      
              } 
              else if(item.isFile())
              { 
                chaine+= "  <File name=\""+item.getName()+"\"/>\n";
              }  
            }
            chaine+="</Directory>\n";

            return chaine;
        }
        public static Composant xmlToDoc(String newTextxml) throws ParserConfigurationException, SAXException, IOException{

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringBuilder newXml = new StringBuilder();
            newXml.append(newTextxml);
            ByteArrayInputStream input=new ByteArrayInputStream(newXml.toString().getBytes());
            Document document= builder.parse(input);
            Element element= document.getDocumentElement();

            return (ajoutItem(element)); 
        }
        
        public static Composant ajoutItem(Element element)  {
            Dossier cheminDossier= new Dossier(element.getAttribute("name"));
            NodeList nodes=element.getChildNodes();
            for (int i=0; i<nodes.getLength(); i++) {
              Node node = nodes.item(i);
              if (node.getNodeType()==Node.ELEMENT_NODE) {
                Element element2 = (Element)node;
                if (node.getNodeName().equals("File")) {
                    Composant fichier = new Fichier(element2.getAttribute("name"));
                    cheminDossier.ajouter(fichier);
                }
                else if (node.getNodeName().equals("Directory")) {
                    cheminDossier.ajouter(ajoutItem(element2));
                }
              }
            }
            return cheminDossier;
        }

        public static  void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
            String path=pathToxml(args[0]);
            System.out.println("===================La Chaine Xml==================");
            System.out.println(path);
            System.out.println("===================Le resulat Xml vers Doc==================");
            Composant chemin= xmlToDoc(path);
            chemin.affiche(0);
            

        }
}
