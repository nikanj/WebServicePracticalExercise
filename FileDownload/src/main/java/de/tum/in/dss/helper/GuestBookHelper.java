/**
 * 
 */
package de.tum.in.dss.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tum.in.dss.constant.CommonConstants;
import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.GuestBookEntries;


public class GuestBookHelper {

	public static GuestBookEntries createNewGuestBookEntry(GuestBookEntries guestBookEntries,GuestBook newbook){
		
		Date currentDate = Calendar.getInstance().getTime();
		newbook.setCreateDate(currentDate);
		
		if(guestBookEntries==null){
			List<GuestBook> guestBookList = new ArrayList<GuestBook>();
			guestBookList.add(newbook);
			guestBookEntries = new GuestBookEntries();
			//guestBookEntries.setGuestBookEntry(newbook);
			guestBookEntries.setGuestBookEntryList(guestBookList);
		}
		else{
			if(newbook.getGuestBookId()>=1){
				//guestBookEntries.setGuestBookEntry(newbook);
				guestBookEntries.updateGuestBookEntry(newbook);
			}
			else{
				//guestBookEntries.setGuestBookEntry(newbook);
				guestBookEntries.addGuestBookEntry(newbook);
			}
		}
	
		return guestBookEntries;
	}
	
	public static Date formatDate(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
        try {
			date = (Date) formatter.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
	}
	
	public static List<String> removeDuplicates(List<String> list) {
        Set<String> set = new HashSet<String>(list);
        List<String> newList = new ArrayList<String>();
        newList.addAll(set);
        newList.removeAll(Arrays.asList("", null));
        return newList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicateObjects(List list) {
		if(list!=null){
	        Set set = new HashSet(list);
	        List newList = new ArrayList();
	        newList.addAll(set);
	        return newList;
		}
		return list;
	}
	
	public static List<String> separateKeywordsToList(String input,String delimiter){
		List<String> keywordList = new ArrayList<String>();
		
		if(input!=null && !input.trim().isEmpty()){
			String[] keywordArray = input.split(delimiter);
			keywordList = Arrays.asList(keywordArray);
		}
		return keywordList;
	}

	public static String listToKeyWords(List<String> inputList, String delimiter) {
		StringBuilder sb = new StringBuilder();
		String loopDelim = "";

		for (String s : inputList) {
			sb.append(loopDelim);
			sb.append(s);
			loopDelim = delimiter;
		}

		return sb.toString();
	}
}
