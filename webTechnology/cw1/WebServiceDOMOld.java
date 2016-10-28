import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.*;

import java.util.ArrayList;

public class WebServiceDOM {

    static AbstractMethod abstractMethod = new AbstractMethod();
    static ArrayList<AbstractMethod> abstractMethodArrayList = new ArrayList<>();
    static Argument argument = new Argument();
    static ArrayList<Argument> argumentArrayList = new ArrayList<>();

    public static class Argument{
        public String getParameterType() {
            return parameterType;
        }

        public void setParameterType(String parameterType) {
            this.parameterType = parameterType;
        }

        public String getParameterName() {
            return parameterName;
        }

        public void setParameterName(String parameterName) {
            this.parameterName = parameterName;
        }

        @Override
                public String toString(){
            return getParameterType() +" " + getParameterName();
        }

        String parameterType;
        String parameterName;
    }

    public static class AbstractMethod{

        String methodName;
        String modifier;
        String returnType;

        public ArrayList<Argument> getArguments() {
            return arguments;
        }

        public void setArguments(ArrayList<Argument> arguments) {
            this.arguments = arguments;
        }

        ArrayList<Argument> arguments;

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

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public String toString(){
            return getModifier()+" "+getReturnType()+" "+getMethodName()+" "+getArguments();
        }


    }

    static int numberOfAbstractMethod = 0;
    public static void main(String[] args){
        try{
            DOMParser parser = new DOMParser();
            parser.parse("WebService.xml");
            Document doc = parser.getDocument();
            traverse_tree(doc);
//            printInterface();

        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

    public static void traverse_tree(Node node)
    {
        if(node == null) {return;}
        int type = node.getNodeType();
        switch (type){
            case Node.DOCUMENT_NODE: {
                traverse_tree(((Document)node).getDocumentElement());
                break;
            }
            case Node.ELEMENT_NODE: {
                handleElement(node);
                break;
            }
            case Node.ATTRIBUTE_NODE: {
                handleAttribute(node);
                break;
            }
        }
    }

    private static void handleAttribute(Node node){
        String nodeName = node.getLocalName();
        String nodeValue = node.getTextContent();

        if(nodeName.equals("parameter")){
            String attributeNodeValue =
                    node.getAttributes().getNamedItem("type").getNodeValue();
            argument.setParameterType(attributeNodeValue);
            argument.setParameterName(nodeValue);
        }
        if(nodeName.equals("abstract_method")){
            String methodName = node.getAttributes().getNamedItem("name").getNodeValue();
            abstractMethod.setMethodName(methodName);

        }
    }

    private static void handleElement(Node node){
        String elementName = node.getLocalName();
        String nodeValue = node.getTextContent();

        if(elementName.equals("abstract_method")){
            handleAttribute(node);
            numberOfAbstractMethod++;
        }
        else if(elementName.equals("modifier")){

            abstractMethod.setModifier(nodeValue);
        }
        else if(elementName.equals("return")){
            abstractMethod.setReturnType(nodeValue);
            abstractMethodArrayList.add(abstractMethod);
            printInterface();
        }
        else if(elementName.equals("parameter")){
            handleAttribute(node);
            argumentArrayList.add(argument);
            abstractMethod.setArguments(argumentArrayList);
        }

//        if(abstractMethod.getModifier()!=null && abstractMethod.getReturnType()!=null && abstractMethod.getMethodName()!= null){
//            abstractMethodArrayList.add(abstractMethod);
//        }
        NodeList childNodes = node.getChildNodes();

        if(childNodes != null){
            int length = childNodes.getLength();
            for (int loopIndex = 0; loopIndex < length; loopIndex++){
                traverse_tree(childNodes.item(loopIndex));
            }
        }
    }

    private static void printInterface(){
        int size = abstractMethodArrayList.size();
        System.out.println("size is:"+size);
        for(AbstractMethod a:abstractMethodArrayList){

            System.out.println(a.toString());
        }
    }
}
