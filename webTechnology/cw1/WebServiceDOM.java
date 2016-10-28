import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WebServiceDOM {
    List abstractMethodList;
    Document doc;

    public WebServiceDOM(){
        abstractMethodList = new ArrayList();
    }

    public static void main(String[] args){
        WebServiceDOM webServiceDOM = new WebServiceDOM();
        webServiceDOM.parXMLFile();
    }

    public void parXMLFile(){
        try{
            DOMParser parser = new DOMParser();
            parser.parse("WebService.xml");
            doc = parser.getDocument();
            traverse_tree(doc);
            printInterface();

        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

    public void printInterface(){
        Iterator iterator = abstractMethodList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }

    public void traverse_tree(Node node){
        //get the root element
        Element docEle = doc.getDocumentElement();

        //get a nodeList of <abstract_method> elements
        NodeList nodeList = docEle.getElementsByTagName("abstract_method");
        if(nodeList != null && nodeList.getLength()>0){
            for(int i = 0;i<nodeList.getLength();i++){
                //get the abstractMethodList element
                Element element = (Element)nodeList.item(i);

                //get the abstract_method object
                AbstractMethod abstractMeth = getAbstractMethod(element);

                //add to list
                abstractMethodList.add(abstractMeth);
            }
        }
    }

    public String getTextValue (Element element, String tagName) {
        String textVal = null;
        NodeList nodeList = element.getElementsByTagName(tagName);
        if(nodeList != null && nodeList.getLength() > 0){
            Element element1 = (Element)nodeList.item(0);
            textVal = element1.getFirstChild().getNodeValue();
        }

        return textVal;
    }

    public AbstractMethod getAbstractMethod(Element element){

        //for each <abstract_method> element get text or int values of
        //name, modifier, return type
        String modifier = getTextValue(element,"modifier");
        String returnType = getTextValue(element,"return");
        String menthodName = element.getAttribute("name");

        //create a new AbstractMethod with the value read form the xml nodes
        AbstractMethod abstractMethod = new AbstractMethod(menthodName,modifier,returnType);

        return abstractMethod;
    }

    public class AbstractMethod
    {
        private String name;
        private String modifier;
        private String returnType;

        public AbstractMethod(String name,String modifier,String returnType){
            this.name = name;
            this.modifier = modifier;
            this.returnType = returnType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModifier() {
            return modifier;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public String getReturnType() {
            return returnType;
        }

        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }

        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append(getModifier()+" ");
            sb.append(getReturnType()+" ");
            sb.append(getName());

            return sb.toString();
        }

    }
}
