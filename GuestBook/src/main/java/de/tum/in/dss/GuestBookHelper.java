/**
 * 
 */
package de.tum.in.dss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GuestBookHelper {

	public static GuestBookEntries createNewBookList(GuestBookEntries guestBookEntries,GuestBook newbook){
		
		if(guestBookEntries==null){
			List<GuestBook> guestBookList = new ArrayList<GuestBook>();
			guestBookList.add(newbook);
			guestBookEntries = new GuestBookEntries(15,guestBookList);
			
		}
		else{
			guestBookEntries.addGuestBookEntry(newbook);
		}
	
		return guestBookEntries;
	}
	
	public static Date formatDate(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
			date = (Date) formatter.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
	}
}
