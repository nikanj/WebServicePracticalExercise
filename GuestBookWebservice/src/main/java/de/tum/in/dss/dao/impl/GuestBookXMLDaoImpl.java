/**
 * 
 */
package de.tum.in.dss.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.tum.in.dss.constant.GuestBookXMLConstants;
import de.tum.in.dss.dao.GuestBookDao;
import de.tum.in.dss.helper.DomHelper;
import de.tum.in.dss.helper.GuestBookHelper;
import de.tum.in.dss.helper.StaxHelper;
import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.Keyword;


public class GuestBookXMLDaoImpl implements GuestBookDao {
 
	private int counter;
	private Resource fileResource;
	private DocumentBuilder documentBuilder;
	private Document document;
	private StaxHelper staxHelper;
	private DomHelper domHelper;
	
	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * @return the fileResource
	 */
	public Resource getFileResource() {
		return fileResource;
	}

	/**
	 * @param fileResource the fileResource to set
	 */
	public void setFileResource(Resource fileResource) {
		this.fileResource = fileResource;
	}
	
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
	 * @return the staxHelper
	 */
	public StaxHelper getStaxHelper() {
		return staxHelper;
	}

	/**
	 * @param staxHelper the staxHelper to set
	 */
	public void setStaxHelper(StaxHelper staxHelper) {
		this.staxHelper = staxHelper;
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

	@Override
	public int createGuestBookEntry(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createGuestBookEntry(GuestBook create) {
		int created = 1;

		Document doc = getParseDocument();
			
		NodeList nList = doc.getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_ENTRY);
		
		if(create.getGuestBookId()<=0){
			create.setGuestBookId(nextAvailableGuestBookId());
			create.setCreateDate(Calendar.getInstance().getTime());
		}
		
		create.setLastModifiedDate(Calendar.getInstance().getTime());
		
		if(nList.getLength()>0){
			created = getStaxHelper().addGuestBookNode(create,false);
			
		}
		else{
			created = getStaxHelper().addGuestBookNode(create,true);
		}
		return created;
	}

	@Override
	public int updateGuestBookEntry(GuestBook update) {
		return updateGuestBookEntry(update,update.getGuestBookId());
	}

	@Override
	public List<GuestBook> retrieveGuestBook(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuestBook> retrieveGuestBook(GuestBook guestBook) {
		List<GuestBook> guestBookList = null;
		Document doc = getParseDocument();
		
		NodeList nList = doc.getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_ENTRY);
		for (int entryIndex = 0; entryIndex < nList.getLength(); entryIndex++) {
			if (guestBookList == null) {
				guestBookList = new ArrayList<GuestBook>();
			}
			Node nNode = nList.item(entryIndex);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				guestBookList.add(domHelper.getGuestBookWithKeywords(getDocument(),entryIndex,eElement));
			}
		}
		return guestBookList;
	}

	@Override
	public List<GuestBook> retrieveAllGuestBooks() {
		List<GuestBook> guestBookList = null;
		Document doc = getParseDocument();
		
		NodeList nList = doc.getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_ENTRY);
		for (int entryIndex = 0; entryIndex < nList.getLength(); entryIndex++) {
			if (guestBookList == null) {
				guestBookList = new ArrayList<GuestBook>();
			}
			Node nNode = nList.item(entryIndex);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				guestBookList.add(domHelper.getGuestBookWithKeywords(getDocument(),entryIndex,eElement));
			}
		}
		return guestBookList;
	}

	@Override
	public void deleteAllGuestBooks() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteGuestBook(GuestBook delete) {
		//Node nodeToDelete = getGuestBookNodeElementByGuestBookId(delete.getGuestBookId());
		int deleted = 1;
		
		getStaxHelper().removeGuestBookNode(delete);
		
		return deleted;
	}

	@Override
	public List<GuestBook> retrieveGuestBookWithKeywords(GuestBook guestBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Keyword> retrieveGuestBookKeywords(String keywordLike) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> retrieveGuestBookKeywords(List<String> keywordLikeList) {
		List<String> guestBookKeywordsListFull = new ArrayList<String>();
		getParseDocument();
		
		List<String> tempKeywordList = getAllDistinctKeywordList();
		
		for(String keywordlike : keywordLikeList){
			List<String> matchedList = GuestBookHelper.getMatchingStrings(tempKeywordList,keywordlike);
			guestBookKeywordsListFull.addAll(matchedList);
		}
		
		guestBookKeywordsListFull = GuestBookHelper.removeDuplicates(guestBookKeywordsListFull);
	    return guestBookKeywordsListFull;
	}

	@Override
	public List<GuestBook> retrieveGuestBookListByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] insertKeywords(List<String> keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateGuestBookEntry(GuestBook updatedGuestBook, int guestBookId) {
		int updated = 0;
					
		//List<String> keywords = updatedGuestBook.getKeywordList();
		GuestBook bookFound = retrieveGuestBookByGuestBookId(guestBookId);
		
		if(bookFound!=null){
			updatedGuestBook.setCreateDate(bookFound.getCreateDate());
			updatedGuestBook.setLastModifiedDate(bookFound.getLastModifiedDate());
			getStaxHelper().removeGuestBookNode(bookFound);
			createGuestBookEntry(updatedGuestBook);
		}
		
		return updated;
	}
	
	public void mappingGuestBookKeywords(GuestBook guestBookToMap) {
		
	}

	@Override
	public int updateKeyword(Keyword keywordUpdate, int keywordId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeKeywordMappingByGuestBookId(int guestBookId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GuestBook retrieveGuestBookByGuestBookId(int guestBookId) {
		GuestBook book = null;
		Document doc = getParseDocument();
		
		NodeList nList = doc.getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_ENTRY);
		for (int entryIndex = 0; entryIndex < nList.getLength(); entryIndex++) {
			
			Node nNode = nList.item(entryIndex);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				GuestBook tempbook = domHelper.getGuestBookWithKeywords(getDocument(),entryIndex,eElement);
				
				if(tempbook.getGuestBookId()==guestBookId){
					return tempbook;
				}
			}
		}
		return book;
	}

	@Override
	public GuestBook retrieveGuestBookByGuestBookName(String guestBookName) {
		GuestBook book = null;
		Document doc = getParseDocument();
		
		NodeList nList = doc.getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_ENTRY);
		for (int entryIndex = 0; entryIndex < nList.getLength(); entryIndex++) {
			
			Node nNode = nList.item(entryIndex);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				GuestBook tempbook = domHelper.getGuestBookWithKeywords(getDocument(),entryIndex,eElement);
				
				if(tempbook.getName()!=null && guestBookName!=null && guestBookName.trim().equals(tempbook.getName())){
					return tempbook;
				}
			}
		}
		return book;
	}
	
	@Override
	public List<GuestBook> retrieveGuestBookListByKeywords(List<String> keywordList) {
		List<GuestBook> guestBookList = null;
		Document doc = getParseDocument();
		
		NodeList nList = doc.getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_ENTRY);
		for (int entryIndex = 0; entryIndex < nList.getLength(); entryIndex++) {
			if (guestBookList == null) {
				guestBookList = new ArrayList<GuestBook>();
			}
			Node nNode = nList.item(entryIndex);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				GuestBook book = domHelper.getGuestBookWithKeywords(getDocument(),entryIndex,eElement);
				
				for(String keywordlike : keywordList){
					List<String> matchedList = GuestBookHelper.getMatchingStrings(book.getKeywordList(),keywordlike);
					
					if(matchedList!=null && matchedList.size()>0){
						guestBookList.add(book);
					}
				}
			}
		}
		guestBookList = GuestBookHelper.removeDuplicateGuestBookObjects(guestBookList);
		return guestBookList;
	}

	@Override
	public List<GuestBook> retrieveGuestBookListByKeywordsInText(List<String> keywordList) {
		List<GuestBook> guestBookList = null;
		Document doc = getParseDocument();
		
		NodeList nList = doc.getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_ENTRY);
		for (int entryIndex = 0; entryIndex < nList.getLength(); entryIndex++) {
			if (guestBookList == null) {
				guestBookList = new ArrayList<GuestBook>();
			}
			Node nNode = nList.item(entryIndex);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				GuestBook book = domHelper.getGuestBookWithKeywords(getDocument(),entryIndex,eElement);
				
				for(String keywordlike : keywordList){
					//List<String> matchedList = GuestBookHelper.getExactMatchingStrings(book.getKeywordList(),keywordlike);
					
					if(keywordlike!=null && book.getText()!=null && book.getText().contains(keywordlike)){
						guestBookList.add(book);
					}
				}
			}
		}
		guestBookList = GuestBookHelper.removeDuplicateGuestBookObjects(guestBookList);
		return guestBookList;
	}
	
	@Override
	public int removeOrphanKeywords() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private Document getParseDocument() {

		Document doc = null;
		try {
			doc = domHelper.getParseDocument(documentBuilder, fileResource.getFile());
		} catch (IOException e) {
			//e.printStackTrace();
		}

		setDocument(doc);
		return doc;

	}

	private List<String> getAllDistinctKeywordList(){
		
		NodeList keyNodeList = getDocument().getElementsByTagName(GuestBookXMLConstants.GUEST_BOOK_KEYWORDS);
		List<String> keywordList = null;
		int entryIndex = 0;
		
		while(keyNodeList.getLength()>0 && entryIndex<keyNodeList.getLength()){
			
			if (keywordList == null) {
					keywordList = new ArrayList<String>();
			}
			NodeList nNodeList = keyNodeList.item(entryIndex++).getChildNodes();
			
			for (int temp1 = 0; temp1 < nNodeList.getLength(); temp1++) {
				
				Node nNode = nNodeList.item(temp1);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
					//System.out.println(nNode.getNodeName() +":::"+nNode.getChildNodes().item(0).getNodeValue());
					if(!keywordList.contains(nNode.getChildNodes().item(0).getNodeValue())){
						keywordList.add(nNode.getChildNodes().item(0).getNodeValue());
					}
				}
			}
		}
		return keywordList;
	}
	
	public int getMaxGuestBookId(){
		
		NodeList idNodeList = getDocument().getElementsByTagName(GuestBookXMLConstants.BOOK_ID);
		int entryIndex = 0;
		int maxGuestBookId = 0;
		
		while(idNodeList.getLength()>0 && entryIndex<idNodeList.getLength()){
			NodeList nNodeList = idNodeList.item(entryIndex++).getChildNodes();
			String idStr = nNodeList.item(0).getNodeValue();
			//System.out.println("GuestBookId::"+idStr);
			int id = Integer.parseInt(idStr);
			if(maxGuestBookId < id){
				maxGuestBookId = id;
			}
		}
		
		setCounter(maxGuestBookId);
		return maxGuestBookId;
	}
	
	public int nextAvailableGuestBookId(){
		getMaxGuestBookId();
		return ++counter;
	}

	@Override
	public int addGuestBookEntries(List<GuestBook> entries) {
		int added = 0;
		if(entries!=null){
			Iterator<GuestBook> bookIt = entries.iterator();
			
			while(bookIt!=null && bookIt.hasNext()){
				GuestBook temp = bookIt.next();
				GuestBook found = retrieveGuestBookByGuestBookName(temp.getName());
				
				if(found==null){
					createGuestBookEntry(temp);
					added++;
				}
			}
		}
		return added;
	}
}
 