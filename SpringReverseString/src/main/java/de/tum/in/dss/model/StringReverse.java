package de.tum.in.dss.model;

public class StringReverse {

	private String text;
	private String reverseText;

	// used to return a text
	public String getText() {
		return text;
	}

	// sets the value to variable text
	public void setText(String text) {
		this.text = text;

	}

	// return the reverseText
	public String getReverseText() {
		return reverseText;
	}

	// sets the reverse of the text to inverse
	public void setReverseText(String text) {
		this.reverseText = text;
	}

	@Override
	public String toString() {
		return "StringReverse [actualtext=" + text + ", reverseText="
				+ reverseText + "]";
	}

	// the string is reversed here
	public String reverseString(String inputText) {
		String reversestr = null;
		int lt = inputText.length();
		StringBuilder rev = new StringBuilder();

		for (int i = lt - 1; i >= 0; i--) {
			rev.append(text.charAt(i));
		}
		reversestr = rev.toString();

		return reversestr;
	}
}
