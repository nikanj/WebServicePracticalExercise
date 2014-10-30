
package de.tum.in.dss.model;

import java.util.Date;



public class GuestBook implements Comparable<GuestBook> {

	private int guestBookId;
	private static int counter;
	private String title;
	private String name;
	private String text;
	private Date createDate;
	public GuestBook()
	{
		
	}
	
	    public GuestBook(String title, String name, String text, Date createDate) {
		this.title = title;
		this.name = name;
		this.text = text;
		this.createDate = createDate;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the guestBookId
	 */
	public int getGuestBookId() {
		return guestBookId;
	}
	/**
	 * @param guestBookId the guestBookId to set
	 */
	public void setGuestBookId(int guestBookId) {
		this.guestBookId = guestBookId;
	}


	/**
	 * @return the counter
	 */
	public static int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int counter) {
		GuestBook.counter = counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public static int incrementCounter() {
		return ++GuestBook.counter;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GuestBook other = (GuestBook) obj;
		if (guestBookId != other.guestBookId)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public int compareTo(GuestBook arg0) {

		//increasing order by date
		//int dateCompare  = this.createDate.compareTo(arg0.createDate);
				
		//increasing order by date
		int dateCompare  = arg0.createDate.compareTo(this.createDate);
	
		
		if(dateCompare == 0){
			return arg0.name.compareTo(this.name);
		}
		else{
			return dateCompare;
		}
	}

}
