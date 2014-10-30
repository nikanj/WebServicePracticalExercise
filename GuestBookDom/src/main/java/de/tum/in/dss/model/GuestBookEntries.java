/**
 * 
 */
package de.tum.in.dss.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class GuestBookEntries {

	private List<String> searchedKeywords;
	private List<GuestBook> guestBookEntryList;

	/**
	 * 
	 */
	public GuestBookEntries() {
	}

	/**
	 * @param guestBookEntryList
	 */
	public GuestBookEntries(List<GuestBook> guestBookEntryList) {
		this.guestBookEntryList = guestBookEntryList;
	}

	
	
	/**
	 * @return the guestBookEntryList
	 */
	public List<GuestBook> getGuestBookEntryList() {
		return guestBookEntryList;
	}

	/**
	 * @param guestBookEntryList the guestBookEntryList to set
	 */
	public void setGuestBookEntryList(List<GuestBook> guestBookEntryList) {
		this.guestBookEntryList = guestBookEntryList;
	}

	

	/**
	 * @return the searchedKeywords
	 */
	public List<String> getSearchedKeywords() {
		return searchedKeywords;
	}

	/**
	 * @param searchedKeywords the searchedKeywords to set
	 */
	public void setSearchedKeywords(List<String> searchedKeywords) {
		this.searchedKeywords = searchedKeywords;
	}

	public void addGuestBookEntry(GuestBook book){
		if(!isBookAlreadyPresent(book)){
			this.guestBookEntryList.add(book);
		}
	}
	
	public void removeGuestBookEntry(GuestBook book){
		if(isBookAlreadyPresent(book)){
			this.guestBookEntryList.remove(book);
		}
	}
	
	public void updateGuestBookEntry(GuestBook book){
		if(isBookAlreadyPresent(book)){
			removeGuestBookEntry(book);
			addGuestBookEntry(book);
		}
	}
	
	public GuestBook searchGuestBookEntry(String name){
		
		if(name!=null){
			Iterator<GuestBook> listIt = guestBookEntryList.iterator();
			
			while(listIt!=null && listIt.hasNext()){
				GuestBook book  = listIt.next();
				
				if(book.getName().equalsIgnoreCase(name)){
					return book;
				}
			}
		}
		return null;
	}
	
	public GuestBook searchGuestBookEntryById(int Id){
		
		Iterator<GuestBook> listIt = guestBookEntryList.iterator();
		
		while(listIt!=null && listIt.hasNext()){
			GuestBook book  = listIt.next();
			
			if(book.getGuestBookId()==Id){
				return book;
			}
		}
	
		return null;
	}
	
	public boolean isBookAlreadyPresent(GuestBook book){
		return this.guestBookEntryList.contains(book);
	}

	public void sortCurrentDisplayList(){
		Collections.sort(getGuestBookEntryList());
	}
	
	public void sortGuestBookList(){
		Collections.sort(getGuestBookEntryList());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GuestBookEntries [guestBookEntryList=" + guestBookEntryList + "]";
	}
	
	
		
}
