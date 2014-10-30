package de.tum.in.dss.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GuestBookData {
	private GuestBook data;
	private List<GuestBook> guestBookList;
	public GuestBookData()
	{
		
	}

	
	public GuestBookData(List<GuestBook> guestBookList) {
		this.guestBookList = guestBookList;
	}

	
	
	
	public List<GuestBook> getGuestBookList() {
		return guestBookList;
	}

	
	public void setGuestBookList(List<GuestBook> guestBookList) {
		this.guestBookList = guestBookList;
	}

	
	public GuestBook getdata() {
		return data;
	}

	
	public void setdata(GuestBook details) {
		this.data = details;
	}

	public void adddata(GuestBook book){
		if(!isBookAlreadyPresent(book)){
			this.guestBookList.add(book);
		}
	}
	
	public void removedata(GuestBook book){
		if(isBookAlreadyPresent(book)){
			this.guestBookList.remove(book);
		}
	}
	
	public void updatedata(GuestBook book){
		if(isBookAlreadyPresent(book)){
			removedata(book);
			adddata(book);
		}
	}
	
	public GuestBook searchdata(String name){
		
		if(name!=null){
			Iterator<GuestBook> listIt = guestBookList.iterator();
			
			while(listIt!=null && listIt.hasNext()){
				GuestBook book  = listIt.next();
				
				if(book.getName().equalsIgnoreCase(name)){
					return book;
				}
			}
		}
		return null;
	}
	
	public GuestBook searchdataById(int Id){
		
		Iterator<GuestBook> listIt = guestBookList.iterator();
		
		while(listIt!=null && listIt.hasNext()){
			GuestBook book  = listIt.next();
			
			if(book.getGuestBookId()==Id){
				return book;
			}
		}
	
		return null;
	}
	
	public boolean isBookAlreadyPresent(GuestBook book){
		return this.guestBookList.contains(book);
	}

	public void sortCurrentDisplayList(){
		Collections.sort(getGuestBookList());
	}
	
	public void sortGuestBookList(){
		Collections.sort(getGuestBookList());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GuestBookData [guestBookdata=" + data
				+ ", guestBookEntryList=" + guestBookList + "]";
	}
	
}
