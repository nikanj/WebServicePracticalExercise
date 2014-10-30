/**
 * 
 */
package de.tum.in.dss;

import java.util.Date;



public class GuestBook implements Comparable<GuestBook> {

	private String itemName;
	private String itemText;
	private Date createDate;
	
	/**
	 * 
	 */
	public GuestBook() {
	}


	/**
	 * @param itemName
	 * @param itemText
	 * @param createDate
	 */
	public GuestBook(String itemName, String itemText, Date createDate) {
		this.itemName = itemName;
		this.itemText = itemText;
		this.createDate = createDate;
	}


	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}


	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
			this.itemName = itemName;
	}


	/**
	 * @return the itemText
	 */
	public String getItemText() {
		return itemText;
	}


	/**
	 * @param itemText the itemText to set
	 */
	public void setItemText(String itemText) {
		this.itemText = itemText;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GuestBook [itemName=" + itemName + ", createDate=" + createDate
				+ "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		return result;
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
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}

	public int compareTo(GuestBook arg0) {

		//increasing order by date
		//int dateCompare  = this.createDate.compareTo(arg0.createDate);
				
		//increasing order by date
		int dateCompare  = arg0.createDate.compareTo(this.createDate);
	
		
		if(dateCompare == 0){
			return arg0.itemName.compareTo(this.itemName);
		}
		else{
			return dateCompare;
		}
	}

}
