package de.tum.in.dss.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import de.tum.in.dss.additional.GuestBookAdd;
import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.GuestBookData;



public class NewGuestBookController extends SimpleFormController {
	public NewGuestBookController(){
		setCommandClass(GuestBookData.class);
		setCommandName("newguestbookform");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		GuestBookData req = (GuestBookData) command;
		GuestBookData Session = null;
		
		HttpSession session_1 = request.getSession(true);
		Object sessionObject = session_1.getAttribute("guestBookListKey");
		
		if(sessionObject!=null){
			Session= (GuestBookData)sessionObject;
		}
		
		Session = GuestBookAdd.createNewGuestBookEntry(Session, req.getdata());
		session_1.setAttribute("guestBookListKey", Session);
		
				
		return new ModelAndView("home","homeform",Session);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		String param1 =  request.getParameter("edit");
		GuestBookData Session = null;
		GuestBook temp = null;
		
		HttpSession session_1 = request.getSession();
		Object sessionObject = session_1.getAttribute("guestBookListKey");
		
		if(sessionObject!=null){
			Session= (GuestBookData)sessionObject;
			
			if(param1!=null){
				try{
					int id = Integer.parseInt(param1);
					temp = Session.searchdataById(id);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		
		else{
			Session = new GuestBookData();
			
		}
		if(temp==null){
			 temp = new GuestBook();
		}
		Session.setdata(temp);
		return Session;
	}

}
