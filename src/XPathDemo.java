import java.io.File;
import java.io.FileInputStream;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class XPathDemo {
    private static Document doc;
    private static XPath xpath;
 
    public static void main(String[] args) throws Exception {
        init();
        getRootEle();
        getChildEles();
        getPartEles();
        haveChildsEles();
        getLevelEles();
        getAttrEles();
         
       
       //System.out.println(doc.getDocumentElement().getChildNodes().getLength());
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                System.out.print(nodeList.item(i).getNodeName() + " ");
            }
        }
    }
 
    // ��ʼ��Document��XPath����
    public static void init() throws Exception {
        // ����Document����
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new FileInputStream(new File("demo.xml")));
 
        // ����XPath����
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }
 
    // ��ȡ��Ԫ��
    // ���ʽ���Ը���Ϊ/*,/rss
    public static void getRootEle() throws XPathExpressionException {
        Node node = (Node) xpath.evaluate("/rss", doc, XPathConstants.NODE);
        System.out.println(node.getNodeName() + "--------"
                + node.getNodeValue());
    }
 
    // ��ȡ��Ԫ�ز���ӡ
    public static void getChildEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("/rss/channel/*", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + " ");
        }
        System.out.println();
    }
 
    // ��ȡ����Ԫ��
    // ֻ��ȡԪ������Ϊtitle��Ԫ��
    public static void getPartEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//*[name() = 'title']",
                doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent());
        }
        System.out.println();
    }
 
    // ��ȡ�����ӽڵ��Ԫ��
    public static void haveChildsEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//*[*]", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + " ");
        }
        System.out.println();
    }
 
    // ��ȡָ���㼶��Ԫ��
    public static void getLevelEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("/*/*/*/*", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
        }
        System.out.println("-----------------------------");
    }
 
    // ��ȡָ�����Ե�Ԫ��
    // ��ȡ���д���ָ���۸������
    public static void getAttrEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//bookstore/book[price>35.00]/title", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
        }
        System.out.println();
    }
}