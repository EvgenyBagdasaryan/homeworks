import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ExcelReader {

    public static final String MLP = "./mlp2.xlsx";
    public static final String POI_GENERATED_FILE = "./poi-generated-file.xlsx";
    public static Document doc;

    public static void main (String[] args) throws IOException, InvalidFormatException, ParserConfigurationException, TransformerException {

        Workbook workbook = WorkbookFactory.create(new File(MLP));
        DataFormatter dataFormatter = new DataFormatter();
        Sheet sheet = workbook.getSheetAt(1);

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();
        Element root = doc.createElement("root");
        doc.appendChild(root);

        for (Row row: sheet) {

            Element entity = doc.createElement("entity");

            for(Cell cell: row) {

                int rowIndex = cell.getRowIndex();
                int columnIndex = cell.getColumnIndex();

                if( (rowIndex > 1) && (columnIndex == 1) ){

                    root.appendChild(entity);
                    entity = setAttr(entity, "name", "REF_REFITEM");
                    entity = setAttr(entity, "id", (rowIndex+10+200)+"");
                    entity = setAttr(entity, "createMethod", "admReferenceItemAdd");
                    entity = setAttr(entity, "getMethod", "admReferenceItems");
                    entity = setAttr(entity, "createKeyParam", "REFITEMID");
                    entity = setAttr(entity, "getKeyParam", "REFITEMID");
                    entity = setAttr(entity, "service", "adminws");

                    Element attribute1 = doc.createElement("attribute");
                    entity.appendChild(attribute1);
                    attribute1 = setAttr(attribute1, "name", "REFERENCEID");
                    attribute1 = setAttr(attribute1, "valueType", "java.lang.Long");
                    attribute1 = setAttr(attribute1, "create", "true");
                    attribute1 = setAttr(attribute1, "search", "true");
                    attribute1 = setAttr(attribute1, "entityID", "125");

                    Element attribute2 = doc.createElement("attribute");
                    entity.appendChild(attribute2);
                    attribute2 = setAttr(attribute2, "name", "CODE");
                    attribute2 = setAttr(attribute2, "valueType", "java.lang.String");
                    attribute2 = setAttr(attribute2, "create", "true");
                    attribute2 = setAttr(attribute2, "search", "true");
                    //CellType type = cellA.getCellTypeEnum();
                    attribute2.appendChild(doc.createTextNode(dataFormatter.formatCellValue(cell)));

                    Element attribute3 = doc.createElement("attribute");
                    entity.appendChild(attribute3);
                    attribute3 = setAttr(attribute3, "name", "LOCALE");
                    attribute3 = setAttr(attribute3, "valueType", "java.lang.String");
                    attribute3 = setAttr(attribute3, "create", "true");
                    attribute3.appendChild(doc.createTextNode("ru"));
                }

                if( (rowIndex > 1) && (columnIndex == 2) ){

                    Element attribute6 = doc.createElement("attribute");
                    entity.appendChild(attribute6);
                    attribute6 = setAttr(attribute6, "name", "SHORTVALUE");
                    attribute6 = setAttr(attribute6, "valueType", "java.lang.String");
                    attribute6 = setAttr(attribute6, "create", "true");
                    attribute6.appendChild(doc.createTextNode(dataFormatter.formatCellValue(cell)));
                }

                if( (rowIndex > 1) && (columnIndex == 3) ){

                    Element attribute4 = doc.createElement("attribute");
                    entity.appendChild(attribute4);
                    attribute4 = setAttr(attribute4, "name", "FULLVALUE");
                    attribute4 = setAttr(attribute4, "valueType", "java.lang.String");
                    attribute4 = setAttr(attribute4, "create", "true");
                    attribute4.appendChild(doc.createTextNode(dataFormatter.formatCellValue(cell)));
                }

                if( (rowIndex > 1) && (columnIndex == 4) ){

                    Element attribute5 = doc.createElement("attribute");
                    entity.appendChild(attribute5);
                    attribute5 = setAttr(attribute5, "name", "RICOMMENT");
                    attribute5 = setAttr(attribute5, "valueType", "java.lang.String");
                    attribute5 = setAttr(attribute5, "create", "true");
                    attribute5.appendChild(doc.createTextNode(dataFormatter.formatCellValue(cell)));
                }
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult res = new StreamResult(new File("./file.xml"));
        transformer.setOutputProperty("indent", "yes");
        transformer.transform(source, res);

        workbook.close();
    }

    public static Element setAttr(Element elem, String attrName, String attrValue){
        try {
            Attr attr1 = doc.createAttribute(attrName);
            attr1.setValue(attrValue);
            elem.setAttributeNode(attr1);
        } catch(Exception e){
            //logger.debug(e.getStackTrace());
        }
        return elem;
    }
}
