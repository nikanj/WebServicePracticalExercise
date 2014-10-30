/**
 * 
 */
package de.tum.in.dss.additional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.tum.in.dss.constant.CommonConstants;
import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.GuestBookEntries;
import de.tum.in.dss.model.Keyword;


public class GuestBookAdditional {

	public static GuestBookEntries createNewGuestBookEntry(final GuestBookEntries guestBookEntries,final GuestBook newbook){
		GuestBookEntries temp = null;
		final Date currentDate = Calendar.getInstance().getTime();
		newbook.setCreateDate(currentDate);
		
		if(guestBookEntries==null){
			final List<GuestBook> guestBookList = new ArrayList<GuestBook>();
			guestBookList.add(newbook);
			temp = new GuestBookEntries();
			temp.setGuestBookEntryList(guestBookList);
		}
		else{
			if(newbook.getGuestBookId()>=1){
				guestBookEntries.updateGuestBookEntry(newbook);
			}
			else{
				guestBookEntries.addGuestBookEntry(newbook);
			}
			temp = guestBookEntries;
		}
	
		return temp;
	}
	
	public static Date formatDate(final Date date){
		Date tempDate = null;
		
        try {
        	if(date!=null){
        		final SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
        		tempDate = (Date) formatter.parse(date.toString());
        	}
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return tempDate;
	}
	
	public static String formatDateStr(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		if(date!=null){
			return formatter.format(date);
		}
		return null;
	}
	
	
	public static Date formatDate(String strdate){
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		
        try {
        	if(strdate!=null){
        		date = (Date) formatter.parse(strdate);
        	}
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
	
	public static List<GuestBook> removeDuplicateGuestBookObjects(List<GuestBook> arlList) {
		List<GuestBook> newList = new ArrayList<GuestBook>();
		for (Iterator<GuestBook> iter = arlList.iterator(); iter.hasNext();) {
			GuestBook element = iter.next();
			if (!newList.contains(element))
				newList.add(element);
		}
		arlList.clear();
		arlList.addAll(newList);
		return newList;

	}
	
	public static List<Keyword> removeDuplicateKeywordObjects(List<Keyword> arlList) {
		List<Keyword> newList = new ArrayList<Keyword>();
		for (Iterator<Keyword> iter = arlList.iterator(); iter.hasNext();) {
			Keyword element = iter.next();
			if (!newList.contains(element))
				newList.add(element);
		}
		arlList.clear();
		arlList.addAll(newList);
		return newList;
	}
	
	public static List<String> separateKeywordsToList(String input,String delimiter){
		List<String> keywordList = new ArrayList<String>();
		
		if(input!=null && !input.trim().isEmpty()){
			String[] keywordArray = input.split(delimiter);
			keywordList = Arrays.asList(keywordArray);
			keywordList = removeWhitespace(keywordList);
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
	
	public static List<String> removeWhitespace(List<String> inputList){
		List<String> newList = null ;
		
		if(inputList!=null){
			Iterator<String> listIt = inputList.iterator();
			newList = new ArrayList<String>();
			
			while(listIt!=null && listIt.hasNext()){
				String item = listIt.next().trim();
				
				if(item!=null && !item.isEmpty()){
					newList.add(item.trim());
				}
			}
		}
		return newList;
	}
	
	public static List<String> getMatchingStrings(List<String> list, String likeString) {

		List<String> matches = new ArrayList<String>();

		if(list!=null){
			for (String s : list) {
				
				if (s!=null && likeString!=null && s.contains(likeString.trim())) {
					matches.add(s);
				}
			}
		}
		return matches;
	}
	
	public static List<String> getExactMatchingStrings(List<String> list, String exactString) {

		List<String> matches = new ArrayList<String>();
		
		if(list!=null){
			for (String s : list) {
				
				if (s!=null && exactString!=null && s.equals(exactString)) {
					matches.add(s);
				}
			}
		}
		return matches;
	}
}
