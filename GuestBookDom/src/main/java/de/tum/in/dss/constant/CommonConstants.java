package de.tum.in.dss.constant;


public class CommonConstants {
	
	public static String FIELD_NAME_TITLE = "title";
	public static String FIELD_NAME_TEXT = "text";
	public static String FIELD_NAME_NAME = "name";
	public static String FIELD_NAME_EMAIL = "email";
	public static String FIELD_NAME_KEYWORDS = "keywords";

	public static String ERROR_MSG_FIELD_SIDE_EMPTY = "Field is required";
	public static String ERROR_MSG_FIELD_EMPTY = "Field is empty !!";
	public static String ERROR_MSG_FIELD_CHARACTERS_LENGTH = "Length of input should be min 3 and max 10 characters";
	public static String ERROR_MSG_FIELD_INVALID_CHARACTERS = "Accepted characters are a-z,A-Z,0-9 and whitespaces";
	public static String ERROR_MSG_FIELD_INVALID_EMAIL = "Email provided is not valid";
	public static String ERROR_MSG_FIELD_INVALID_KEYWORD = "Keyword(s) entered is/are not valid";
	
	public static final String ACCEPTED_CHARACTERS_PATTERN = "^[a-zA-Z0-9\\s]+$";
	//public static final String ACCEPTED_KEYWORDS_PATTERN = "^[a-zA-Z0-9\\s]+(\\,[a-zA-Z0-9]+[\\s]*)*$";
	public static final String ACCEPTED_KEYWORDS_PATTERN = "^[a-zA-Z0-9@-_\\.\\s]+([\\,\\s[a-zA-Z0-9@-_\\.]+[\\s]*]+)*$";
	public static final String ACCEPTED_EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String GUEST_BOOK_LIST_KEY = "guestBookListKey";
	
	public static final String COMMA = ",";
	public static final String LIKE_OP = "%";
	public static final String WHITESPACE = " ";
}
