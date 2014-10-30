/**
 * 
 */
package de.tum.in.dss.constant;


public class XMLConstant extends CommonConstants {
	
	
	
	public static String GUEST_BOOK_MAIN_PREFIX = "gb";
	public static String GUEST_BOOK_MAIN_SUFFIX = "guestbook";
	
	public static String GUEST_BOOK_ENTRY_SUFFIX = "entry";
	public static String GUEST_BOOK_ELEMENT_PREFIX = "entry";
	
	public static String BOOK_ID_SUFFIX = "BOOK_ID";
	public static String AUTHOR_TITLE_SUFFIX = "AUTHOR_TITLE";
	public static String AUTHOR_NAME_SUFFIX = "AUTHOR_NAME";
	public static String BOOK_DATA_SUFFIX = "BOOK_DATA";
	public static String GUEST_EMAIL_ID_SUFFIX = "GUEST_EMAIL_ID";
	public static String DATE_OF_CREATION_SUFFIX = "DATE_OF_CREATION";
	public static String LAST_MODIFICATION_DATE_SUFFIX = "LAST_MODIFICATION_DATE";
	
	public static String GUEST_BOOK_KEYWORDS_SUFFIX = "keywords";
	public static String GUEST_BOOK_KEYWORD_PREFIX = "k";
	public static String GUEST_BOOK_KEYWORD_SUFFIX = "keyword";
	
	public static final String GUEST_BOOK_ENTRY_FIRST_ELEMENT = GUEST_BOOK_MAIN_PREFIX + ":" + GUEST_BOOK_MAIN_SUFFIX;
	public static final String GUEST_BOOK_ENTRY = GUEST_BOOK_MAIN_PREFIX + ":" + GUEST_BOOK_ENTRY_SUFFIX;
	public static final String BOOK_ID = GUEST_BOOK_ELEMENT_PREFIX + ":" + BOOK_ID_SUFFIX;
	public static final String AUTHOR_NAME = GUEST_BOOK_ELEMENT_PREFIX + ":" + AUTHOR_NAME_SUFFIX;
	public static final String BOOK_DATA = GUEST_BOOK_ELEMENT_PREFIX + ":" + BOOK_DATA_SUFFIX;
	public static final String AUTHOR_TITLE = GUEST_BOOK_ELEMENT_PREFIX + ":" + AUTHOR_TITLE_SUFFIX;
	public static final String GUEST_EMAIL_ID = GUEST_BOOK_ELEMENT_PREFIX + ":" + GUEST_EMAIL_ID_SUFFIX;
	public static final String DATE_OF_CREATION = GUEST_BOOK_ELEMENT_PREFIX + ":" + DATE_OF_CREATION_SUFFIX;
	public static final String LAST_MODIFICATION_DATE = GUEST_BOOK_ELEMENT_PREFIX + ":" + LAST_MODIFICATION_DATE_SUFFIX;
	public static final String GUEST_BOOK_KEYWORDS = GUEST_BOOK_ELEMENT_PREFIX + ":" + GUEST_BOOK_KEYWORDS_SUFFIX;
	public static final String GUEST_BOOK_KEYWORD =  GUEST_BOOK_KEYWORD_PREFIX + ":" + GUEST_BOOK_KEYWORD_SUFFIX;
	
	public static final String NAMESAPCE_URI = "";
	
}
