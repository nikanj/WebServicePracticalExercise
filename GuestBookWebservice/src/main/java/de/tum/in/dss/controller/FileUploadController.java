/**
 * 
 */
package de.tum.in.dss.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import de.tum.in.dss.constant.CommonConstants;
import de.tum.in.dss.dao.GuestBookDao;
import de.tum.in.dss.helper.XpathAnalyzer;
import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.GuestBookEntries;


public class FileUploadController extends SimpleFormController {

	private XpathAnalyzer xpathAnalyzer;
	private GuestBookDao guestBookDao;
	/**
	 * 
	 */
	public FileUploadController() {
		setCommandClass(GuestBookEntries.class);
		setCommandName("guestbookuploadform");
		setFormView("uploadedguestbooklist");
	}

	/**
	 * @return the xpathAnalyzer
	 */
	public XpathAnalyzer getXpathAnalyzer() {
		return xpathAnalyzer;
	}

	/**
	 * @param xpathAnalyzer the xpathAnalyzer to set
	 */
	public void setXpathAnalyzer(XpathAnalyzer xpathAnalyzer) {
		this.xpathAnalyzer = xpathAnalyzer;
	}

	
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

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		  // cast the bean
		GuestBookEntries entries = (GuestBookEntries) command;
		GuestBookEntries guestBookEntriesSession = null;
		
		HttpSession session = request.getSession(true);
		Object sessionObject = session.getAttribute(CommonConstants.GUEST_BOOK_LIST_KEY);
		
		if(sessionObject!=null){
			guestBookEntriesSession= (GuestBookEntries)sessionObject;
		}
		else{
			guestBookEntriesSession = new GuestBookEntries();
		}

		MultipartFile file = entries.getFile();
		
		
		if(entries.getSelectedGuestBookIds()!=null && entries.getSelectedGuestBookIds().length>0){
			List<GuestBook> selectedBookList = getBookList(Arrays.asList(entries.getSelectedGuestBookIds()),guestBookEntriesSession.getUploadedGuestBookEntryList());
			if(selectedBookList!=null && selectedBookList.size()>0){
				guestBookDao.addGuestBookEntries(selectedBookList);
				guestBookEntriesSession.setGuestBookEntryList(guestBookDao.retrieveAllGuestBooks());
			}
		}
		else if (file!= null) {
        	
			try {
        		List<GuestBook> bookList = xpathAnalyzer.analyzeFile(file);
        		
        		if(bookList!=null){
        			guestBookEntriesSession.setUploadedGuestBookEntryList(bookList);
        		}
        		
        		session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY,guestBookEntriesSession);
        		return new ModelAndView("uploadedguestbooklist","guestbookuploadform",guestBookEntriesSession);
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
        }
		session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY,guestBookEntriesSession);
        return new ModelAndView("guestbookhome","guestbookhomeform",guestBookEntriesSession);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	
 		GuestBookEntries guestBookEntriesSession = null;
		HttpSession session = request.getSession(true);
		Object sessionObject = session.getAttribute(CommonConstants.GUEST_BOOK_LIST_KEY);
		if(sessionObject!=null){
				guestBookEntriesSession= (GuestBookEntries)sessionObject;
				//guestBookEntriesSession.setSelectedGuestBookIds(null);
				session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY,guestBookEntriesSession);
				
		}
		return guestBookEntriesSession;
	}
	
	
	private List<GuestBook> getBookList(List<String> ids,List<GuestBook> mainBookList){
		List<GuestBook> filterdList = null;
		
		if(ids!=null){
			filterdList = new ArrayList<GuestBook>();
			Iterator<String> idIt = ids.iterator();
			
			while(idIt!=null && idIt.hasNext()){
				String idStr = idIt.next();
				
				if(idStr!=null && !idStr.trim().isEmpty()){
					try{
						int id = Integer.parseInt(idStr);
						GuestBook tempBook = new GuestBook();
						tempBook.setGuestBookId(id);
						int pos = mainBookList.indexOf(tempBook);
					
						if(pos!=-1){
							filterdList.add(mainBookList.get(pos));
						}
					}
					catch(Exception e){
						
					}
				}
			}
		}
		
		return filterdList;
	}
	

}
