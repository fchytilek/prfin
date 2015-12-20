/**
 * 
 */
package fin.parse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;
/**
 * 
 * @author Gerhard
 *
 */
public class Parser {

	public static void main(String[] args){
		Document xmlDoc = getDocument("information.xml");//returns a dom object
		System.out.println("Absolute Rootelement: "+xmlDoc.getDocumentElement().getNodeName());
	
		NodeList listOfQuotes = xmlDoc.getElementsByTagName("quote"); 
		System.out.println("Element: "+listOfQuotes+", anz: "+listOfQuotes.getLength());
		
		
		List<Node> listeAdjClose=getElementAndAttrib(listOfQuotes, "Adj_Close", "");
		List<Node> listeClose=getElementAndAttrib(listOfQuotes, "Close", "");
		List<Node> listeLow=getElementAndAttrib(listOfQuotes, "Low", "");
		List<Node> listeHigh=getElementAndAttrib(listOfQuotes, "High", "");
		List<Node> listeVolume=getElementAndAttrib(listOfQuotes, "Volume", "");
		List<Node> listeDate=getElementAndAttrib(listOfQuotes, "Date", "");
		
		double durchschnittClose = 0;
		double durchschnittAdjClose = 0;
		double durchschnittHigh = 0;
		double durchschnittLow = 0;
		double durchschnittVolume = 0;
		
		int i=0;
		for(i=0;i<listeClose.size();i++){
			System.out.println("Am "+listeDate.get(i).getNodeValue()+" ist der Close: "+listeClose.get(i).getNodeValue() 
					+ ", Adj_Close: "+listeAdjClose.get(i).getNodeValue()+", Low: "+listeLow.get(i).getNodeValue()
					+ ", High: "+listeHigh.get(i).getNodeValue()+", Volume: "+listeVolume.get(i).getNodeValue());
			durchschnittClose+=Double.parseDouble(listeClose.get(i).getNodeValue());
			durchschnittAdjClose+=Double.parseDouble(listeAdjClose.get(i).getNodeValue());
			durchschnittHigh+=Double.parseDouble(listeHigh.get(i).getNodeValue());
			durchschnittLow+=Double.parseDouble(listeLow.get(i).getNodeValue());
			durchschnittVolume+=Double.parseDouble(listeVolume.get(i).getNodeValue());
		}
		System.out.println("DurchschnittClose: "+(durchschnittClose/i));
		System.out.println("DurchschnittAdjClose: "+(durchschnittAdjClose/i));
		System.out.println("DurchschnittHigh: "+(durchschnittHigh/i));
		System.out.println("DurchschnittLow: "+(durchschnittLow/i));
		System.out.println("DurchschnittVolume: "+(durchschnittVolume/i));
	}

	private static Document getDocument(String docString) {
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			//factory.setValidating(true);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(docString));
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return null;
	}

	private static List<Node> getElementAndAttrib(NodeList listOfNodes, String elementName, String attrName){
		
		try{
	        List<Node> values = new ArrayList<Node>();
			// Cycle through the number of shows
			
        	for(int i=0; i < listOfNodes.getLength(); i++){
        	
        		// Get the first show node
        		
        		Node showNode = listOfNodes.item(i);
        		
        		
        		// Convert into an element to gain access to element methods
        		
        		Element showElement = (Element)showNode;
        		
        		// Create a list of nodes that have the name defined in elementName
        		
        		NodeList networkList = showElement.getElementsByTagName(elementName);
        		
        		// Get the first and only element in this situation
        		
        		Element networkElement = (Element)networkList.item(0);

        		// Returns a list of node elements
        		// Each value is in a node in side of the network node
        		// That's why you have to get the child nodes for network
        		
                NodeList elementList = networkElement.getChildNodes();
                
                // Check if the element has the attribute set
                
                if(networkElement.hasAttribute(attrName)){
                	
                	System.out.println(elementName + " : " + ((Node)elementList.item(0)).getNodeValue().trim() +
                		" has an attribute " + networkElement.getAttribute(attrName));
                	
                } else {
                	
                	//System.out.println(((Node)elementList.item(0)).getNodeValue().trim());
                	
                }
                values.add((Node)elementList.item(0));
        	}
        	
        	return values;
        }
        
        catch(Exception ex){
			
			System.out.println(ex.getMessage());
			return null;
			
		}
		
	}
}
