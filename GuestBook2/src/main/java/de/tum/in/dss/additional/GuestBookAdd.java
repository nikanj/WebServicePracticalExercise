package de.tum.in.dss.additional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.GuestBookData;



public class GuestBookAdd {
public static GuestBookData createNewGuestBookEntry(GuestBookData gbd,GuestBook newbook){
		
		Date currentDate = Calendar.getInstance().getTime();
		newbook.setCreateDate(currentDate);
		
		if(gbd==null){
			List<GuestBook> guestBookList = new ArrayList<GuestBook>();
			newbook.setGuestBookId(GuestBook.incrementCounter());
			guestBookList.add(newbook);
			gbd = new GuestBookData();
			gbd.setdata(newbook);
			gbd.setGuestBookList(guestBookList);
		}
		else{
			if(newbook.getGuestBookId()>=1){
				gbd.setdata(newbook);
				gbd.updatedata(newbook);
			}
			else{
				newbook.setGuestBookId(GuestBook.incrementCounter());
				gbd.setdata(newbook);
				gbd.adddata(newbook);
			}
		}
	
		return gbd;
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
