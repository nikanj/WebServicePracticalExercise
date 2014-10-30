/**
 * 
 */
package de.tum.in.dss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import de.tum.in.dss.constant.CommonConstants;
import de.tum.in.dss.dao.GuestBookDao;
import de.tum.in.dss.helper.GuestBookHelper;
import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.GuestBookEntries;



public class GuestBookController extends SimpleFormController {

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
	
	public GuestBookController(){
		setCommandClass(GuestBookEntries.class);
		setCommandName("guestbookhomeform");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		GuestBookEntries guestBookEntriesSession = null;
		
		HttpSession session = request.getSession(true);
		Object sessionObject = session.getAttribute(CommonConstants.GUEST_BOOK_LIST_KEY);
		
		if(sessionObject!=null){
			guestBookEntriesSession= (GuestBookEntries)sessionObject;
		}
		else{
			guestBookEntriesSession = new GuestBookEntries();
		}
		
		if(request.getRequestURI().contains("searchkeyword.app")){
		  return getKeywords(request,response,command,errors);
		}
		
		String searchText = request.getParameter("searchtext");
		
		if((request.getParameter("filtersubmit")!=null && searchText!=null && !searchText.trim().isEmpty()) || (searchText!=null && !searchText.trim().isEmpty())){
			  return refreshDisplayList(request, response, command, errors);
		}
		
		try{
			guestBookEntriesSession.setGuestBookEntryList(guestBookDao.retrieveAllGuestBooks());
		}
		catch(Exception ae){
			// Provide error message
		}
		session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY, guestBookEntriesSession);
			
		Map<String,GuestBookEntries> modelMap = new HashMap<String,GuestBookEntries>();
		modelMap.put("guestbookentries", guestBookEntriesSession);
		modelMap.put("guestbookhomeform", guestBookEntriesSession);
		
		return new ModelAndView("guestbookhome","guestbookhomeform",guestBookEntriesSession);
		
	}

	public ModelAndView getKeywords(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors){

		HttpSession session = request.getSession(true);
		Object sessionObject = session.getAttribute(CommonConstants.GUEST_BOOK_LIST_KEY);
		GuestBookEntries guestBookEntriesSession = null;
		
		if(sessionObject!=null){
			guestBookEntriesSession= (GuestBookEntries)sessionObject;
		}
		else{
			guestBookEntriesSession = new GuestBookEntries();
		}
		
		List<String> keywords = GuestBookHelper.separateKeywordsToList(request.getParameter("keywordInput").trim(), CommonConstants.WHITESPACE);
		try{
			List<String> keywordStrList = guestBookDao.retrieveGuestBookKeywords(keywords);
			guestBookEntriesSession.setSearchedKeywords(keywordStrList);
			session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY,guestBookEntriesSession);
			return new ModelAndView("keyword","searchedKeywords",keywordStrList);
		}
		catch (Exception e) {
			//Throw appropriate message here
		}
		session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY,guestBookEntriesSession);
		return new ModelAndView("keyword","searchedKeywords",new ArrayList<String>());

	}
	
	public ModelAndView refreshDisplayList(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors){

		HttpSession session = request.getSession(true);
		Object sessionObject = session.getAttribute(CommonConstants.GUEST_BOOK_LIST_KEY);
		GuestBookEntries guestBookEntriesSession = null;
		
		if(sessionObject!=null){
			guestBookEntriesSession= (GuestBookEntries)sessionObject;
		}
		else{
			guestBookEntriesSession = new GuestBookEntries();
		}
		
		String inputSearch = request.getParameter("searchtext");
		
		if(inputSearch!=null && !inputSearch.trim().isEmpty()){
			List<GuestBook> displayList = new ArrayList<GuestBook>();
			List<String> keywordList = GuestBookHelper.separateKeywordsToList(inputSearch, CommonConstants.WHITESPACE);
			try{
				List<GuestBook> displayGuestBookEntries1 = guestBookDao.retrieveGuestBookListByKeywords(keywordList);
				List<GuestBook> displayGuestBookEntries2 = guestBookDao.retrieveGuestBookListByKeywordsInText(keywordList);
				
				if(displayGuestBookEntries1!=null)
					displayList.addAll(displayGuestBookEntries1);
				if(displayGuestBookEntries2!=null)
					displayList.addAll(displayGuestBookEntries2);
			}
			catch (Exception e) {
				//  handle exception
			}
			displayList = GuestBookHelper.removeDuplicateGuestBookObjects(displayList);
			guestBookEntriesSession.setGuestBookEntryList(displayList);
			session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY,guestBookEntriesSession);
		}
		
		return new ModelAndView("guestbookhome","guestbookhomeform",guestBookEntriesSession);

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String param1 =  request.getParameter("delete");
		String param2 =  request.getParameter("sortByDate");
		GuestBookEntries guestBookEntriesSession = null;
		//System.out.println(request.getRequestURI());
		HttpSession session = request.getSession();
		Object sessionObject = session.getAttribute(CommonConstants.GUEST_BOOK_LIST_KEY);
		try{
			

		if(sessionObject!=null){
			guestBookEntriesSession= (GuestBookEntries)sessionObject;
			
			String filtersubmit = request.getParameter("filtersubmit");
			String searchText = request.getParameter("searchtext");
			
 			if(request.getParameter("viewall")!=null || filtersubmit==null || (filtersubmit!=null && searchText==null)){
				  guestBookEntriesSession.setGuestBookEntryList(guestBookDao.retrieveAllGuestBooks());
			}
 			
			if(param1!=null){
				GuestBook bookPresent = null;
				try{
					int id = Integer.parseInt(param1);
					bookPresent = guestBookDao.retrieveGuestBookByGuestBookId(id);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				if(bookPresent!=null){
					guestBookDao.deleteGuestBook(bookPresent);
					guestBookEntriesSession.setGuestBookEntryList(guestBookDao.retrieveAllGuestBooks());
				}
			}

			if(param2!=null){
				guestBookEntriesSession.sortGuestBookList();
			}
		}
		else{
			guestBookEntriesSession = new GuestBookEntries();
			guestBookEntriesSession.setGuestBookEntryList(guestBookDao.retrieveAllGuestBooks());
		}
		}
		catch (Exception e) {
			//  handle exception
		}		
		session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY,guestBookEntriesSession);
		return guestBookEntriesSession;
	}

}
