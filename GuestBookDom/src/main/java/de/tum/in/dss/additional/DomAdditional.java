/**
 * 
 */
package de.tum.in.dss.additional;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.tum.in.dss.constant.XMLConstant;
import de.tum.in.dss.model.GuestBook;


public class DomAdditional {
	 
	public static String getTagValue(String sTag, Element eElement) {
		NodeList list = eElement.getElementsByTagName(sTag);
		
		if(list!=null){
			Node node = list.item(0);
			
			if(node!=null){
				NodeList nlList = node.getChildNodes();
				
				if(nlList!=null){
				    Node nValue = (Node) nlList.item(0);
				 
					return nValue.getNodeValue();
				}
			}
		}
		return null;
 }
	 
	 public static GuestBook getGuestBookWithKeywords(Document doc,int entryIndex,Element eElement){
			GuestBook book = new GuestBook();

			int id = Integer.parseInt(DomAdditional.getTagValue(XMLConstant.BOOK_ID, eElement));
			book.setGuestBookId(id);
			book.setName(DomAdditional.getTagValue(XMLConstant.AUTHOR_NAME, eElement));
			book.setText(DomAdditional.getTagValue(XMLConstant.BOOK_DATA, eElement));
			book.setTitle(DomAdditional.getTagValue(XMLConstant.AUTHOR_TITLE, eElement));
			book.setEmail(DomAdditional.getTagValue(XMLConstant.GUEST_EMAIL_ID, eElement));
			book.setCreateDate(GuestBookAdditional.formatDate(DomAdditional.getTagValue(XMLConstant.DATE_OF_CREATION, eElement)));
			book.setLastModifiedDate(GuestBookAdditional.formatDate(DomAdditional.getTagValue(XMLConstant.LAST_MODIFICATION_DATE, eElement)));
			
			book.setKeywordList(getKeywordList(doc,entryIndex));
			
			return book;
			
		}
	 
	 public static List<String> getKeywordList(Document doc ,int entryIndex){
			
			NodeList keyNodeList = doc.getElementsByTagName(XMLConstant.GUEST_BOOK_KEYWORDS);
			List<String> keywordList = null;
			
			if(keyNodeList.getLength()>0){
				if (keywordList == null) {
						keywordList = new ArrayList<String>();
				}
				Node keywordNode = keyNodeList.item(entryIndex);
				
				if(keywordNode!=null){
					NodeList nNodeList = keywordNode.getChildNodes();
					for (int temp1 = 0; temp1 < nNodeList.getLength(); temp1++) {
						
						Node nNode = nNodeList.item(temp1);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			
							//System.out.println(nNode.getNodeName() +":::"+nNode.getChildNodes().item(0).getNodeValue());
							keywordList.add(nNode.getChildNodes().item(0).getNodeValue());
						}
					}
				}
			}
			return keywordList;
		}
}
