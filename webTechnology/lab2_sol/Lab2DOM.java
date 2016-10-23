package uk.ac.le.cs.CO3098;
import org.w3c.dom.*;
import org.apache.xerces.parsers.DOMParser;

public class Lab2DOM {
	public static void main(String[] args)
    {
       try{
  		DOMParser parser = new DOMParser();
  		parser.parse("student.xml");
  		Document doc = parser.getDocument();
  		display_names(doc);
       }
       catch(Exception e){
          e.printStackTrace(System.err);
	}
    }

	static int numberOfSpaces=0;	
	public static void display_names(Node node)
	   {
		  numberOfSpaces++;
	      if(node == null) {return;}
	      int type = node.getNodeType();
	      switch (type) {
	         case Node.DOCUMENT_NODE: {
	            	display_names(((Document)node).getDocumentElement());
	    		break;
		    }
	         case Node.ELEMENT_NODE: {
	        	 System.out.print("" + spaces(numberOfSpaces) + node.getNodeName());
	        	 
	        	 if(node.getChildNodes().getLength()==1){
	        		 System.out.print(":"+node.getTextContent());
	        	 }
	        	 System.out.println();
	        	 NodeList childNodes = node.getChildNodes();	
	             if(childNodes != null) {
	                int length = childNodes.getLength();
	                for (int index = 0; index < length ; index++)
	                {
	                   display_names(childNodes.item(index));
	                }
	             }
		         break;
	         }   
	      }  
	      
	      
	      
	      numberOfSpaces--;
	   }
	public static String spaces(int t){
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<t;i++){
			sb.append("\t");
		}		
		return sb.toString();
	}
}
