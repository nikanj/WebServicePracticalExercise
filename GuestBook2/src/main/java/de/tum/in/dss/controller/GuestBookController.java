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




public class GuestBookController extends SimpleFormController {
	private boolean del = false;
	private boolean edit = false;
	private boolean add = false;
	public GuestBookController(){
		setCommandClass(GuestBookData.class);
		setCommandName("guestbookhomeform");
	}
	public boolean isDel() {
		return del;
	}
	
	public void setDel(boolean del) {
		this.del = del;
	}
	
	public boolean isEdit() {
		return edit;
	}
	
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	public boolean isAdd() {
		return add;
	}
	
	public void setAdd(boolean add) {
		this.add = add;
	}
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		GuestBookData req = (GuestBookData) command;
		GuestBookData Session = null;
		
		HttpSession session_1 = request.getSession(true);
		Object sessionObject = session_1.getAttribute( "guestBookListKey");
		
		if(sessionObject!=null){
			Session= (GuestBookData)sessionObject;
		}
		
			if(isAdd()){
			Session = GuestBookAdd.createNewGuestBookEntry(Session, req.getdata());
			session_1.setAttribute ("guestBookListKey", Session);
			
			Map<String,GuestBookData> modelMap = new HashMap<String,GuestBookData>();
			modelMap.put("guestbookdata", Session);
			modelMap.put("homeform", Session);
			
			return new ModelAndView("guestbookhome",modelMap);
		}
		else if(isDel()){
			
		}
		else if(isEdit()){
			
		}
		else{
			return new ModelAndView("home","guestbookentries",Session);
		}
		return super.onSubmit(request, response, command, errors);
	}
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String del =  request.getParameter("delete");
		String sort =  request.getParameter("sort");
		GuestBookData Session = null;
		
		HttpSession session_1 = request.getSession();
		Object sessionObject = session_1.getAttribute("guestBookListKey");
		
		if(sessionObject!=null){
			Session= (GuestBookData)sessionObject;
			
			if(del!=null){
				GuestBook bookPresent = null;
				try{
					int id = Integer.parseInt(del);
					bookPresent = Session.searchdataById(id);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				if(bookPresent!=null){
					Session.removedata(bookPresent);
				}
			}
			
			if(sort!=null){
				Session.sortGuestBookList();
				session_1.setAttribute("homeform", Session);
			}
		}
		else{
			Session = new GuestBookData();
		}
		
		Session.setdata(new GuestBook());
		return Session;
	}

	
}
