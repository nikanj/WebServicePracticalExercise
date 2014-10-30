package de.tum.in.dss.helper;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.dss.guestbook.Guestbook;
import de.tum.in.dss.guestbooktype.Guestbooktype;
import de.tum.in.dss.keyword.Keywords;
import de.tum.in.dss.model.GuestBook;

public class ModelConverterHelper {

	public GuestBook copyGuestbooktypeToGuestBook(Guestbooktype type){
		GuestBook book = null;
		
		if(type!=null){
			book = new GuestBook();
			book.setGuestBookId(type.getBOOKID());
			book.setTitle(type.getAUTHORTITLE());
			book.setName(type.getAUTHORNAME());
			book.setText(type.getBOOKDATA());
			book.setEmail(type.getGUESTEMAILID());
			book.setCreateDate(type.getDATEOFCREATION());
			book.setLastModifiedDate(type.getLASTMODIFICATIONDATE());
			
			if(type.getKeywords()!=null){
				book.setKeywordList(type.getKeywords().getKeyword());
			}
		}
		return book;
	}
	

	public List<GuestBook> copyGuestbooktypeListToGuestBookList(List<Guestbooktype> guestBookList){
		List<GuestBook> bookList = null;
		
		if(guestBookList!=null){
			bookList = new ArrayList<GuestBook>();
			
			for(Guestbooktype type:guestBookList){
				GuestBook book = copyGuestbooktypeToGuestBook(type);
				
				if(book!=null){
					bookList.add(book);
				}
			}
		}
		return bookList;
	}
	
	public List<Guestbooktype> copyGuestBookListToGuestbooktypeList(List<GuestBook> bookList){
		List<Guestbooktype> guestBookList = null;
		
		if(bookList!=null){
			 guestBookList = new ArrayList<Guestbooktype>();
			 
			 for(GuestBook book:bookList){
					Guestbooktype type = copyGuestBookToGuestbooktype(book);
					
					if(type!=null){
						guestBookList.add(type);
					}
				}
		}
		return guestBookList;
	}
	
	public Guestbooktype copyGuestBookToGuestbooktype(GuestBook book ){
		Guestbooktype type = null;
		
		if(book!=null){
			type = new Guestbooktype();
			type.setBOOKID(book.getGuestBookId());
			type.setAUTHORTITLE(book.getTitle());
			type.setAUTHORNAME(book.getName());
			type.setBOOKDATA(book.getText());
			type.setGUESTEMAILID(book.getEmail());
			type.setLASTMODIFICATIONDATE(book.getLastModifiedDate());
			type.setDATEOFCREATION(book.getCreateDate());
			
			if(book.getKeywordList()!=null){
				Keywords keywords = new Keywords(); 
				keywords.getKeyword().addAll(book.getKeywordList());
				type.setKeywords(keywords);
			}
		}
		return type;
	}
	
	public Guestbook copyGuestBookListToGuestbook(List<GuestBook> bookList){
		Guestbook book = new Guestbook();
		
		if(bookList!=null){
			
			List<Guestbooktype> guestBookList =copyGuestBookListToGuestbooktypeList(bookList);
			book.getEntry().addAll(guestBookList);
		}
		return book;
	}
	
	public Keywords copyKeywordListToKeywords(List<String> keywordList){
		Keywords keywords = new Keywords();
		
		if(keywordList!=null){
			keywords.getKeyword().addAll(keywordList);
		}
		return keywords;
	}
	
}
