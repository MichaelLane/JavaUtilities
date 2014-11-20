package jaxp;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

/**
 * Some useful methods not included in the standard JAXP API.
 * 
 * @author Michael Lane
 */
public final class JAXPSnippets {
    
    private static final String IDREF_ATT = "idref";
    private static final String ID_ATT = "id";
    
    /**
     * Behaves as you would expect, except that if node implements Document,
     * then this returns the child elements of node.getDocumentElement().
     * 
     * @param node non-null
     * @return
     */
    public static List<Element> getChildElements(Node node) {
        
        if (node == null) {
            return null;
        }
        
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            node = ((Document) node).getDocumentElement();
        }
		List<Element> nodesToReturn = new ArrayList();
		NodeList childNodes = node.getChildNodes();
		int size = childNodes.getLength();
		for (int i = 0; i < size; i++) {
			Node thisNode = childNodes.item(i);
            if (thisNode.getNodeType() == Node.ELEMENT_NODE) {
                nodesToReturn.add((Element) thisNode);
            }
		}
		return nodesToReturn;
	}
    
    /** 
     * Returns the idref attribute or the id attribute, depending on whichever
     * exists. If both exist, then return the idref attribute.
     * 
     * @param element must be non-null
     * @return 
     */
    public static String getIdOrIdref(Element element) {
        
        if (element == null) {
            return null;
        }
        
		String idref = element.getAttribute(IDREF_ATT);
		String id = element.getAttribute(ID_ATT);
		return (idref != null) ? idref : id;
	}
    
    /**
     * Find the Element children of a given node by a tag name.
     * 
     * @param tagName non-null and non-empty
     * @param node non-null
     * @return 
     */
    public static List<Element> getChildElementsBy(String tagName, Node node) {

        if (tagName == null || tagName.equals("") || node == null) {
            return null;
        }
        
		List<Element> nodesToReturn = new ArrayList();
		NodeList childNodes = node.getChildNodes();
        int size = childNodes.getLength();
		for (int i = 0; i < size; i++) {
			Node thisNode = childNodes.item(i);
			if (thisNode.getNodeType() == Node.ELEMENT_NODE
			&& thisNode.getLocalName().equals(tagName)) {
				nodesToReturn.add((Element) thisNode);
			}
		}

		return nodesToReturn;
	}
}
