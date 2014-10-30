/**
 * 
 */
package de.tum.in.dss.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.Resource;

import de.tum.in.dss.guestbook.Guestbook;
import de.tum.in.dss.guestbooktype.Guestbooktype;
import de.tum.in.dss.keyword.Keywords;


public class WebserviceHelper {

	private List<Resource> testFileResourceList;
	
	/**
	 * @return the testFileResourceList
	 */
	public List<Resource> getTestFileResourceList() {
		return testFileResourceList;
	}

	/**
	 * @param testFileResourceList the testFileResourceList to set
	 */
	public void setTestFileResourceList(List<Resource> testFileResourceList) {
		this.testFileResourceList = testFileResourceList;
	}

	public void printGuestBookElements(Guestbook book){
		
		if(book!=null && book.getEntry()!=null && book.getEntry().size()>0){
			System.out.println("-------------Records-------------");
			System.out.println("[id,title,name,text,email,createdate,lastmodifieddate,[keywords]]");
			Iterator<Guestbooktype> bookIt = book.getEntry().iterator();
			
			while(bookIt!=null && bookIt.hasNext()){
				Guestbooktype type = bookIt.next();
				String bookitems = "["+type.getBOOKID()+","+type.getAUTHORTITLE()+","+
										type.getAUTHORNAME()+","+type.getBOOKDATA()+","+
										type.getGUESTEMAILID()+","+type.getDATEOFCREATION()+","+
										type.getLASTMODIFICATIONDATE()+",";
				
				if(type.getKeywords()!=null && type.getKeywords().getKeyword().size()>0){
					String keywords = printkeywordList(type.getKeywords().getKeyword());
					bookitems+= keywords;
				}
				else{
					bookitems+="[]";
				}
				bookitems+="]";
				System.out.println(bookitems);
				
			}
			System.out.println("---------------------------------");
		}
		else{
			System.out.println("-------------No record available-------------");
		}
		
	}
	
	private String printkeywordList(List<String> list){
		String keywordList = "[";
		Iterator<String> keywordIt = list.iterator();
		int i=0;
		
		while(keywordIt!=null && keywordIt.hasNext()){
			keywordList+= i!=0 ? "," : "";
			keywordList+=keywordIt.next();
			i++;
		}
		
		return keywordList+"]";
	}
	
	public static void main(String[] args) throws DatatypeConfigurationException{
		WebserviceHelper help = new WebserviceHelper();
		help.printGuestBookElements(help.getGuestBookListTestData());
	}
	
	@SuppressWarnings("unchecked")
	public Guestbook getGuestBookListTestData() {
		JSONParser parser = new JSONParser();
		 
		try {
			
			if(testFileResourceList!=null){
				Guestbook book = new Guestbook();
				
				for(Resource file:testFileResourceList){
					//System.out.println(testFileResourceList.get(0).getFile().getCanonicalPath());
					
					Object obj = parser.parse(new FileReader(file.getFile().getCanonicalPath()));
			 
					JSONArray records = (JSONArray) obj;
					Iterator<JSONObject> recorditerator = records.iterator();
					
		            
					while (recorditerator!=null && recorditerator.hasNext()) {
						Guestbooktype bookData = new Guestbooktype();
						JSONObject jsonObject = recorditerator.next();
						
						Long id = (Long) jsonObject.get("id");
						String title = (String) jsonObject.get("title");
						String name = (String) jsonObject.get("name");
						String text = (String) jsonObject.get("text");
						String email = (String) jsonObject.get("email");
						String createdate = (String) jsonObject.get("createdate");
						String lastmodifieddate = (String) jsonObject.get("lastmodifieddate");
						JSONArray keywordarray = (JSONArray) jsonObject.get("keywords");
						Keywords keys = new Keywords();
						keys.getKeyword().addAll(keywordarray);
						
						bookData.setBOOKID(id.intValue());
						bookData.setAUTHORTITLE(title);
						bookData.setAUTHORNAME(name);
						bookData.setBOOKDATA(text);
						bookData.setGUESTEMAILID(email);
						bookData.setDATEOFCREATION(GuestBookHelper.formatDate(createdate,"yyyy-MM-dd HH:mm:SS"));
						bookData.setLASTMODIFICATIONDATE(GuestBookHelper.formatDate(lastmodifieddate,"yyyy-MM-dd HH:mm:SS"));
						bookData.setKeywords(keys);
		
						book.getEntry().add(bookData);
					}
				}
				
				return book;
				
			}
		} catch (FileNotFoundException e) {
			System.err.println("Test data file not found");
		} catch (IOException e) {
			System.err.println("Error while reading test data");
		} catch (ParseException e) {
			System.err.println("Error parsing test data");
		}
		return null;
	}
	
	public XMLGregorianCalendar getXMLDate(String datestr){
		try {
			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
	        Date temp = (Date) formatter.parse(datestr);

			gc.setTimeInMillis(temp.getTime());
			XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			return date2;
		} catch (Exception e) {
			System.err.println("Could not parse date to XML date type");
		}
		return null;
	}
	
	
}
