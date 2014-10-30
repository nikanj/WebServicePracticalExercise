/**
 * 
 */
package de.tum.in.dss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GuestBookEntries {

	private int displayItemCount;
	private int currentStartIndex;
	private int currentEndIndex;
	
	private List<GuestBook> guestBookEntryList;
	private List<GuestBook> currentDisplayList;

	/**
	 * 
	 */
	public GuestBookEntries() {
	}

	/**
	 * @param displayItemCount
	 */
	public GuestBookEntries(int displayItemCount) {
		this.displayItemCount = displayItemCount;
	}

	/**
	 * @return the currentStartIndex
	 */
	public int getCurrentStartIndex() {
		return currentStartIndex;
	}

	/**
	 * @param currentStartIndex the currentStartIndex to set
	 */
	public void setCurrentStartIndex(int currentStartIndex) {
		this.currentStartIndex = currentStartIndex;
	}

	/**
	 * @return the currentEndIndex
	 */
	public int getCurrentEndIndex() {
		return currentEndIndex;
	}

	/**
	 * @param currentEndIndex the currentEndIndex to set
	 */
	public void setCurrentEndIndex(int currentEndIndex) {
		this.currentEndIndex = currentEndIndex;
	}

	/**
	 * @return the currentDisplayList
	 */
	public List<GuestBook> getCurrentDisplayList() {
		return currentDisplayList;
	}

	/**
	 * @param currentDisplayList the currentDisplayList to set
	 */
	public void setCurrentDisplayList(List<GuestBook> currentDisplayList) {
		this.currentDisplayList = currentDisplayList;
	}

	/**
	 * @param displayItemCount
	 * @param guestBookEntryList
	 */
	public GuestBookEntries(int displayItemCount,List<GuestBook> guestBookEntryList) {
		this.displayItemCount = displayItemCount;
		this.guestBookEntryList = guestBookEntryList;
	}

	/**
	 * @return the displayItemCount
	 */
	public int getDisplayItemCount() {
		return displayItemCount;
	}

	/**
	 * @param displayItemCount the displayItemCount to set
	 */
	public void setDisplayItemCount(int displayItemCount) {
		this.displayItemCount = displayItemCount;
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
	
	public boolean isBookAlreadyPresent(GuestBook book){
		return this.guestBookEntryList.contains(book);
	}

	public List<GuestBook> firstDisplayListUpdate(){
		List<GuestBook> currentList = new ArrayList<GuestBook>();
		int i=0;
		int j=currentStartIndex;
		while(i<getDisplayItemCount() && j<getGuestBookEntryList().size()){
			currentList.add(i, getGuestBookEntryList().get(j));
			i++;j++;
		}
		if(currentList.size()>0){
			setCurrentDisplayList(currentList);
			currentEndIndex = currentStartIndex + i--;
			return currentList;
		}
		else{
			return getCurrentDisplayList();
		}
	}
	
	public List<GuestBook> nextDisplayListUpdate(){
		List<GuestBook> currentList = new ArrayList<GuestBook>();
		int i=0;
		int j=currentEndIndex;
		while(i<getDisplayItemCount() && j<getGuestBookEntryList().size()){
			currentList.add(i, getGuestBookEntryList().get(j));
			i++;
			j++;
		}
		
		if(currentList.size()>0){
			setCurrentDisplayList(currentList);
			currentStartIndex = currentEndIndex;
			currentEndIndex = currentStartIndex + i--;
			return currentList;
		}
		else{
			return getCurrentDisplayList();
		}
		
	}
	
	public List<GuestBook> previousDisplayListUpdate(){
		List<GuestBook> currentList = new ArrayList<GuestBook>();
		int i=0;
		int j = currentStartIndex - getDisplayItemCount();
		
		while(i<getDisplayItemCount() && j<getGuestBookEntryList().size() && j>=0){
			currentList.add(i, getGuestBookEntryList().get(j));
			i++;
			j++;
		}
		
		if(currentList.size()>0){
			setCurrentDisplayList(currentList);
			currentEndIndex = currentStartIndex;
			currentStartIndex = currentStartIndex - getDisplayItemCount();
			return currentList;
		}
		else{
			return getCurrentDisplayList();
		}
		
	}
	
	public void sortCurrentDisplayList(){
		Collections.sort(getCurrentDisplayList());
	}
	
	public void sortGuestBookList(){
		Collections.sort(getGuestBookEntryList());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GuestBookEntries [displayItemCount=" + displayItemCount
				+ ", guestBookEntryList=" + guestBookEntryList + "]";
	}
		
}
