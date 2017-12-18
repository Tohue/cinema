package config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Парсер настроек коннекта к БД из конфиг-файла
 */
public class DBPropertiesParser {

    DocumentBuilderFactory dbf;
    DocumentBuilder builder;


    public DBPropertiesParser() throws ParserConfigurationException {

        dbf = DocumentBuilderFactory.newInstance();
        builder = dbf.newDocumentBuilder();

    }

    /**
     * Парсинг из существующего файла
     */
    public void parseBDProperties () throws IOException, SAXException {

        System.out.println("парсим");
        Document document = builder.parse("./resources/configFiles/DBConfig.xml");
        Element root = document.getDocumentElement();

        DBConfig.setHOSTNAME(root.getElementsByTagName("host").item(0).getTextContent());
        DBConfig.setPORT(root.getElementsByTagName("port").item(0).getTextContent());
        DBConfig.setDRIVER(root.getElementsByTagName("driver").item(0).getTextContent());
        DBConfig.setDATABASEURL(root.getElementsByTagName("DatabaseURL").item(0).getTextContent());


    }



    /**
     * Сорздание нового файла с дефолтными настройками
     */
    public void newDBProperties() throws TransformerException, IOException {

        Document document = builder.newDocument();

        Element root = document.createElement("database_connection");

        Element hostName = document.createElement("host");
        hostName.appendChild(document.createTextNode("127.0.0.1"));
        root.appendChild(hostName);

        Element port = document.createElement("port");
        port.appendChild(document.createTextNode("3306"));
        root.appendChild(port);

        Element SQLdriver = document.createElement("driver");
        SQLdriver.appendChild(document.createTextNode("com.mysql.jdbc.Driver"));
        root.appendChild(SQLdriver);

        Element databaseURL = document.createElement("DatabaseURL");
        databaseURL.appendChild(document.createTextNode("jdbc:mysql://localhost:3306/a218696_cinema"));
        root.appendChild(databaseURL);

        document.appendChild(root);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        File DBConfig =  new File("./resources/configFiles/DBConfig.xml");
        DBConfig.createNewFile();
        transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("./resources/configFiles/DBConfig.xml")));
    }
}
