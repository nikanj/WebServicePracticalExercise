/**
 * 
 */
package de.tum.in.dss.model;

import java.util.Date;
import java.util.List;



public class GuestBook implements Comparable<GuestBook> {

	private int guestBookId;
	private String title;
	private String name;
	private String text;
	private String email;
	private Date createDate;
	private Date lastModifiedDate;
	private String keywords;
	
	private List<String> keywordList;
	
	/**
	 * 
	 */
	public GuestBook() {
	}



	/**
	 * @param title
	 * @param name
	 * @param text
	 * @param createDate
	 */
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if(email!=null && email.trim().isEmpty()){
			this.email = null;
		}
		else {
			this.email = email;
		}
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


	
	/**
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}



	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}



	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}



	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}



	/**
	 * @return the keywordList
	 */
	public List<String> getKeywordList() {
		return keywordList;
	}



	/**
	 * @param keywordList the keywordList to set
	 */
	public void setKeywordList(List<String> keywordList) {
		this.keywordList = keywordList;
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
	@Override
	public String toString() {
		return "GuestBook [guestBookId=" + guestBookId + ", title=" + title
				+ ", name=" + name + ", text=" + text + ", email=" + email
				+ ", createDate=" + createDate + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}



	public int compareTo(GuestBook arg0) {

		//increasing order by date
		//int dateCompare  = this.createDate.compareTo(arg0.createDate);
				
		//increasing order by date
		int dateCompare  = this.lastModifiedDate.compareTo(arg0.lastModifiedDate);
	
		
		if(dateCompare == 0){
			return arg0.name.compareTo(this.name);
		}
		else{
			return dateCompare;
		}
	}
	
	
}
