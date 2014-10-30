/**
 * 
 */
package de.tum.in.dss.model;


public class Keyword {

	private int keywordId;
	private String keywordValue;
	
	/**
	 * @return the keywordId
	 */
	public int getKeywordId() {
		return keywordId;
	}
	/**
	 * @param keywordId the keywordId to set
	 */
	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}
	/**
	 * @return the keywordValue
	 */
	public String getKeywordValue() {
		return keywordValue;
	}
	/**
	 * @param keywordValue the keywordValue to set
	 */
	public void setKeywordValue(String keywordValue) {
		this.keywordValue = keywordValue;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		Keyword other = (Keyword) obj;
		if (keywordId != other.keywordId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Keyword [keywordId=" + keywordId + ", keywordValue="
				+ keywordValue + "]";
	}
	
}
