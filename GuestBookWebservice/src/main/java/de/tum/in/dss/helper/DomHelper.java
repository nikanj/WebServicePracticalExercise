/**
 * 
 */
package de.tum.in.dss.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.tum.in.dss.constant.GuestBookXMLConstants;
import de.tum.in.dss.model.GuestBook;


public class DomHelper {
	 
	public String getTagValue(String sTag, Element eElement) {
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
	 
	 public GuestBook getGuestBookWithKeywords(Document doc,int entryIndex,Element eElement){
			GuestBook book = new GuestBook();

			int id = Integer.parseInt(getTagValue(GuestBookXMLConstants.BOOK_ID, eElement));
			book.setGuestBookId(id);
			book.setName(getTagValue(GuestBookXMLConstants.AUTHOR_NAME, eElement));
			book.setText(getTagValue(GuestBookXMLConstants.BOOK_DATA, eElement));
			book.setTitle(getTagValue(GuestBookXMLConstants.AUTHOR_TITLE, eElement));
			book.setEmail(getTagValue(GuestBookXMLConstants.GUEST_EMAIL, eElement));
			book.setCreateDate(GuestBookHelper.formatDate(getTagValue(GuestBookXMLConstants.GUEST_BOOK_DATE_OF_CREATION, eElement)));
			book.setLastModifiedDate(GuestBookHelper.formatDate(getTagValue(GuestBookXMLConstants.GUEST_BOOK_LAST_MODIFICATION_DATE, eElement)));
			
			book.setKeywordList(getKeywordList(doc,entryIndex));
			
			return book;
			
		}
	 
	 public List<String> getKeywordList(Document doc ,int entryIndex){
			
			NodeList keyNodeList = doc.getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_KEYWORDS);
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
	 
		public Document getParseDocument(DocumentBuilder builder, File file) {

			Document doc = null;

			try {
				doc = builder.parse(file);
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
			//setDocument(doc);
			return doc;

		}
		
		public Document getParseDocument(DocumentBuilder builder, InputStream stream) {

			Document doc = null;

			try {
				doc = builder.parse(stream);
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
			//setDocument(doc);
			return doc;

		}
}
