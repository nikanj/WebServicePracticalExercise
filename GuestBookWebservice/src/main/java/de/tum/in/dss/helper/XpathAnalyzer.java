/**
 * 
 */
package de.tum.in.dss.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import de.tum.in.dss.model.GuestBook;


public class XpathAnalyzer {
	private DocumentBuilder documentBuilder;
	private DomHelper domHelper;
	private Document document;
	private XPath xPath;
	
	/**
	 * @return the documentBuilder
	 */
	public DocumentBuilder getDocumentBuilder() {
		return documentBuilder;
	}

	/**
	 * @param documentBuilder the documentBuilder to set
	 */
	public void setDocumentBuilder(DocumentBuilder documentBuilder) {
		this.documentBuilder = documentBuilder;
	}

	/**
	 * @return the domHelper
	 */
	public DomHelper getDomHelper() {
		return domHelper;
	}

	/**
	 * @param domHelper the domHelper to set
	 */
	public void setDomHelper(DomHelper domHelper) {
		this.domHelper = domHelper;
	}
	
	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * @return the xPath
	 */
	public XPath getxPath() {
		return xPath;
	}

	/**
	 * @param xPath the xPath to set
	 */
	public void setxPath(XPath xPath) {
		this.xPath = xPath;
	}

	public List<GuestBook> analyzeFile(MultipartFile file) throws IOException, XPathExpressionException{
		List<GuestBook> bookList = null;
		Document doc = domHelper.getParseDocument(documentBuilder, file.getInputStream());
		setDocument(doc);
		bookList = parseGuestBookObject();
		return bookList;
	}
	
	public List<GuestBook> parseGuestBookObject() throws XPathExpressionException {
		List<GuestBook> bookList = null;
		XPathExpression expr1 = xPath.compile("//guestbook/*");
		Object result = expr1.evaluate(getDocument(),XPathConstants.NODESET);
		
		if(result!=null){
			bookList = new ArrayList<GuestBook>();
			NodeList nodes = (NodeList) result;
			
			for (int i = 0; i < nodes.getLength(); i++) {
				GuestBook book = new GuestBook();
				String idStr = getTagValue("//guestbook/entry["+(i+1)+"]/GUEST_BOOK_ID/text()");
				int id = Integer.parseInt(idStr);
				book.setGuestBookId(id);
				
				book.setTitle(getTagValue("//guestbook/entry["+(i+1)+"]/GUEST_BOOK_TITLE/text()"));
				book.setName(getTagValue("//guestbook/entry["+(i+1)+"]/GUEST_BOOK_NAME/text()"));
				book.setText(getTagValue("//guestbook/entry["+(i+1)+"]/GUEST_BOOK_TEXT/text()"));
				book.setEmail(getTagValue("//guestbook/entry["+(i+1)+"]/GUEST_BOOK_EMAIL_ID/text()"));
				
				Date createdate = DatatypeConverter.parseDate(getTagValue("//guestbook/entry["+(i+1)+"]/CREATION_DATE/text()")).getTime();
				book.setCreateDate(createdate);
				
				
				Date lastmodifieddate = DatatypeConverter.parseDate(getTagValue("//guestbook/entry["+(i+1)+"]/LAST_MODIFICATION_DATE/text()")).getTime();
				book.setLastModifiedDate(lastmodifieddate);
			
				XPathExpression keyword = xPath.compile("//guestbook/entry["+(i+1)+"]/keywords/*");
				
				Object keywordresult = keyword.evaluate(getDocument(),XPathConstants.NODESET);
				List<String> keywordsList = null;
				
				if(keywordresult!=null){
					keywordsList = new ArrayList<String>();
					NodeList keywordnodes = (NodeList) keywordresult;

					if(keywordnodes.getLength()>0){
					
						for (int j = 0; j < keywordnodes.getLength(); j++) {
							keywordsList.add(getTagValue("//guestbook/entry["+(i+1)+"]/keywords/keyword["+(j+1)+"]/text()"));
						}
						book.setKeywordList(keywordsList);
					}
				}
				
								
				bookList.add(book);
			}
		}
		return bookList;
	}
	
	
	private String getTagValue(String pattern) throws XPathExpressionException {
		XPathExpression expr  = xPath.compile(pattern);
		Object result = expr.evaluate(getDocument(),XPathConstants.NODESET);
		
		if(result!=null) {
			NodeList entryNodes = (NodeList) result;
			return entryNodes.item(0).getNodeValue();
		}
		
		return null;
	}

	

}
