package xml;


import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class DocumentXml {
    public static void main(String[] args) throws DocumentException {
        DocumentXml s = new DocumentXml();
        System.out.println(s.getClass().getClassLoader().getResource("contesdnt.xml"));
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("students");
        System.out.println(root.getName());

        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(new File("D:\\hanyh\\project\\code_demo\\growup\\src\\main\\resources\\content.xml"));
        Element rootElement = read.getRootElement();
        getNodes(rootElement);
    }

    static public void getNodes(Element rootElement) {
        System.out.println("当前节点名称:" + rootElement.getName());
        // 获取属性ID
        List<Attribute> attributes = rootElement.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性:" + attribute.getName() + "---" + attribute.getText());
        }
        if (!rootElement.getTextTrim().equals("")) {
            System.out.println(rootElement.getName() + "--" + rootElement.getText());
        }
        // 使用迭代器遍历
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();
            getNodes(next);
        }

    }

}
