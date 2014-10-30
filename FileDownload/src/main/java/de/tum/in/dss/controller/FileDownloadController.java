/**
 * 
 */
package de.tum.in.dss.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import de.tum.in.dss.dao.GuestBookDao;
import de.tum.in.dss.model.GuestBook;


public class FileDownloadController extends MultiActionController {

	private GuestBookDao guestBookDao;
	
	/**
	 * @return the guestBookDao
	 */
	public GuestBookDao getGuestBookDao() {
		return guestBookDao;
	}

	/**
	 * @param guestBookDao the guestBookDao to set
	 */
	public void setGuestBookDao(GuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
	}

	public ModelAndView download(HttpServletRequest request,HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		String content="";
		String filename="guestbook.txt";
		List<GuestBook> guestBookList = null;
		
		String id = request.getParameter("id");
		
		if(id!=null && !id.trim().isEmpty()){
			int guestBookId =  Integer.parseInt(id);
			GuestBook book = guestBookDao.retrieveGuestBookByGuestBookId(guestBookId);
			if(book!=null){
				guestBookList = new ArrayList<GuestBook>();
				guestBookList.add(book);
			}
			filename = "guestbook"+guestBookId+".txt";
		}
		else{
			guestBookList = guestBookDao.retrieveAllGuestBooks(); 
		}
		
		content = getFileContent(guestBookList);
		out.println(content);
		response.setContentType("application/text");
		response.setHeader("Content-disposition", "attachment; filename=" + filename);
	
		return null;
	}
	
	private String getFileContent(List<GuestBook> guestBookList) {
		String content = "";
		if (guestBookList != null && guestBookList.size()>0) {
			content +="Title,Name,Text,Email,Creation Date,Last Modification Date,Keywords(Pipe separated)"+"\n";
			Iterator<GuestBook> bookIt = guestBookList.iterator();
			
			while (bookIt != null && bookIt.hasNext()) {
				GuestBook book = bookIt.next();
				String keywords = getKeywordsAsPipeSeparated(book.getKeywordList());
				content += (book.getTitle() != null ? book.getTitle() : "")
						+ "," + (book.getName() != null ? book.getName() : "")
						+ "," + (book.getText() != null ? book.getText() : "")
						+ "," + (book.getEmail() != null ? book.getEmail() : "")
						+ "," + (book.getCreateDate() != null ? book.getCreateDate() : "")
						+ "," + (book.getLastModifiedDate() != null ? book.getLastModifiedDate() : "")
						+ "," + ((keywords!=null && !keywords.trim().isEmpty())? keywords:"")
						+ "\n";
			}
		}
		return content;
	}
	
	private String getKeywordsAsPipeSeparated(List<String> keywordsList){
		String keywords = "";
		
		if(keywordsList!=null){
			Iterator<String> keywordIt = keywordsList.iterator();
			
			while(keywordIt!=null && keywordIt.hasNext()){
				String keyword = keywordIt.next();
				
				if(!keywords.trim().isEmpty()){
					keywords += "|"+ keyword;
				}
				else{
					keywords += keyword;
				}
			}
			
		}
		
		return keywords;
	}
}
