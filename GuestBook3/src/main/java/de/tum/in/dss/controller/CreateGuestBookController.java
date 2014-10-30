/**
 * 
 */
package de.tum.in.dss.controller;

import java.util.List;

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

public class CreateGuestBookController extends SimpleFormController {

private GuestBookDao guestBookDao;
	
	public CreateGuestBookController(){
		setCommandClass(GuestBook.class);
		setCommandName("guestbookcreateform");
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
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest, java.lang.Object)
	 */
	@Override
	protected void onBind(HttpServletRequest request, Object command)
			throws Exception {
		String cancelform =  request.getParameter("cancelcreate");
		
		if(cancelform!=null){
			setValidateOnBinding(false);
		}else{
			setValidateOnBinding(true);
		}
		super.onBind(request, command);
	}


	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		GuestBook guestBookRequest = (GuestBook) command;
		GuestBookEntries guestBookEntriesSession = null;
		
		HttpSession session = request.getSession(true);
		Object sessionObject = session.getAttribute(CommonConstants.GUEST_BOOK_LIST_KEY);
		
		if(sessionObject!=null){
			guestBookEntriesSession= (GuestBookEntries)sessionObject;
		}
		else{
			guestBookEntriesSession = new GuestBookEntries();
		}
		if(request.getParameter("clearedit")!=null){
			return clearForm(request, response, command, errors);
		}
		else if(request.getParameter("cancelcreate")!=null){
			return new ModelAndView("guestbookhome","guestbookhomeform",guestBookEntriesSession);
		}
		try{
			List<GuestBook> guestBookList =guestBookDao.retrieveGuestBook(guestBookRequest);
			guestBookRequest.setKeywordList(GuestBookHelper.separateKeywordsToList(guestBookRequest.getKeywords(), CommonConstants.COMMA));
			guestBookRequest.setKeywordList(GuestBookHelper.removeDuplicates(guestBookRequest.getKeywordList()));
			
			if(guestBookList!=null && guestBookList.size()>=1){
				guestBookDao.updateGuestBookEntry(guestBookRequest);
			}
			else{
				guestBookDao.createGuestBookEntry(guestBookRequest);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		guestBookEntriesSession.setGuestBookEntryList(guestBookDao.retrieveAllGuestBooks());
		session.setAttribute(CommonConstants.GUEST_BOOK_LIST_KEY, guestBookEntriesSession);
		
		return new ModelAndView("guestbookhome","guestbookhomeform",guestBookEntriesSession);
	}

	public ModelAndView clearForm(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors){
		
		return new ModelAndView("createguestbook","guestbookcreateform",new GuestBook());
	}
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		String param1 =  request.getParameter("edit");
		String clearform =  request.getParameter("clearedit");
		
		GuestBook temp = null;
		
		if(param1!=null){
			try{
				int id = Integer.parseInt(param1);
				GuestBook book = new GuestBook();
				book.setGuestBookId(id);
				List<GuestBook> guestBookList = guestBookDao.retrieveGuestBookWithKeywords(book);
				if(guestBookList!=null && guestBookList.size()==1){
					temp = guestBookList.get(0);
					temp.setKeywords(GuestBookHelper.listToKeyWords(temp.getKeywordList(), CommonConstants.COMMA));
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		if(temp==null || clearform!=null){
			 temp = new GuestBook();
		}
		
		return temp;
	}

}
