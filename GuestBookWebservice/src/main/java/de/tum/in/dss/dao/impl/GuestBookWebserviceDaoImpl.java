/**
 * 
 */
package de.tum.in.dss.dao.impl;

import java.util.List;

import de.tum.in.dss.dao.GuestBookDao;
import de.tum.in.dss.guestbook.Guestbook;
import de.tum.in.dss.guestbook.wsdl.GuestBookService;
import de.tum.in.dss.guestbooktype.Guestbooktype;
import de.tum.in.dss.helper.ModelConverterHelper;
import de.tum.in.dss.keyword.Keywords;
import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.Keyword;


public class GuestBookWebserviceDaoImpl implements GuestBookDao {
	private GuestBookService guestBookService;
	private ModelConverterHelper modelConverterHelper;
	/**
	 * @return the guestBookService
	 */
	public GuestBookService getGuestBookService() {
		return guestBookService;
	}

	/**
	 * @param guestBookService the guestBookService to set
	 */
	public void setGuestBookService(GuestBookService guestBookService) {
		this.guestBookService = guestBookService;
	}

	/**
	 * @return the modelConverterHelper
	 */
	public ModelConverterHelper getModelConverterHelper() {
		return modelConverterHelper;
	}

	/**
	 * @param modelConverterHelper the modelConverterHelper to set
	 */
	public void setModelConverterHelper(ModelConverterHelper modelConverterHelper) {
		this.modelConverterHelper = modelConverterHelper;
	}

	@Override
	public int createGuestBookEntry(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createGuestBookEntry(GuestBook create) {
		Guestbooktype type = modelConverterHelper.copyGuestBookToGuestbooktype(create);
		int created = guestBookService.createGuestBookEntry(type);
		return created;
	}

	@Override
	public int addGuestBookEntries(List<GuestBook> entries) {
		Guestbook book = new Guestbook();
		book.getEntry().addAll(modelConverterHelper.copyGuestBookListToGuestbooktypeList(entries));
		int created = guestBookService.addGuestBookEntries(book);
		return created;
	}

	@Override
	public int updateGuestBookEntry(GuestBook update) {
		Guestbooktype type = modelConverterHelper.copyGuestBookToGuestbooktype(update);
		int updated = guestBookService.updateGuestBookEntry(type);
		return updated;
	}

	@Override
	public List<GuestBook> retrieveGuestBook(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuestBook> retrieveGuestBook(GuestBook guestBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuestBook> retrieveAllGuestBooks() {
		Guestbook guestbook = guestBookService.retrieveAllGuestBooks();
		return modelConverterHelper.copyGuestbooktypeListToGuestBookList(guestbook.getEntry());
	}

	@Override
	public void deleteAllGuestBooks() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteGuestBook(GuestBook delete) {
		Guestbooktype type = modelConverterHelper.copyGuestBookToGuestbooktype(delete);
		int deleted = guestBookService.deleteGuestBook(type);
		return deleted;
	}

	@Override
	public List<GuestBook> retrieveGuestBookWithKeywords(GuestBook guestBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Keyword> retrieveGuestBookKeywords(String keywordLike) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> retrieveGuestBookKeywords(List<String> keywordLikeList) {
		Keywords keywords = new Keywords();
		keywords.getKeyword().addAll(keywordLikeList);
		Keywords keywordsreponse = guestBookService.retrieveGuestBookKeywords(keywords);
		return (keywordsreponse!=null ? keywordsreponse.getKeyword() : null);
	}

	@Override
	public List<GuestBook> retrieveGuestBookListByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] insertKeywords(List<String> keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateGuestBookEntry(GuestBook updatedGuestBook, int guestBookId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateKeyword(Keyword keywordUpdate, int keywordId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeKeywordMappingByGuestBookId(int guestBookId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GuestBook retrieveGuestBookByGuestBookId(int guestBookId) {
		Guestbooktype type = guestBookService.retrieveGuestBookByGuestBookId(guestBookId);
		return modelConverterHelper.copyGuestbooktypeToGuestBook(type);
	}

	@Override
	public List<GuestBook> retrieveGuestBookListByKeywords(List<String> keywordList) {
		Keywords keywords = new Keywords();
		keywords.getKeyword().addAll(keywordList);
		Guestbook book = guestBookService.retrieveGuestBookListByKeywords(keywords);
		return modelConverterHelper.copyGuestbooktypeListToGuestBookList(book.getEntry());
	}

	@Override
	public int removeOrphanKeywords() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GuestBook> retrieveGuestBookListByKeywordsInText(List<String> keywordList) {
		Keywords keywords = new Keywords();
		keywords.getKeyword().addAll(keywordList);
		Guestbook book = guestBookService.retrieveGuestBookListByKeywordsInText(keywords);
		return modelConverterHelper.copyGuestbooktypeListToGuestBookList(book.getEntry());
	}

	@Override
	public GuestBook retrieveGuestBookByGuestBookName(String guestBookName) {
		// TODO Auto-generated method stub
		return null;
	}

}
