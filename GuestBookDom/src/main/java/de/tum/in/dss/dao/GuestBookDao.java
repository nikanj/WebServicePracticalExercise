/**
 * 
 */
package de.tum.in.dss.dao;

import java.util.List;

import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.Keyword;


public interface GuestBookDao {

	int createGuestBookEntry(String firstName, String lastName);

	int createGuestBookEntry(GuestBook create);

	int updateGuestBookEntry(GuestBook update);

	List<GuestBook> retrieveGuestBook(String firstname, String lastname);

	List<GuestBook> retrieveGuestBook(GuestBook guestBook);

	List<GuestBook> retrieveAllGuestBooks();

	void deleteAllGuestBooks();

	int deleteGuestBook(GuestBook delete);

	List<GuestBook> retrieveGuestBookWithKeywords(GuestBook guestBook);

	List<Keyword> retrieveGuestBookKeywords(String keywordLike);

	List<String> retrieveGuestBookKeywords(List<String> keywordLikeList);

	List<GuestBook> retrieveGuestBookListByKeyword(String keyword);

	int[] insertKeywords(List<String> keywords);

	int updateGuestBookEntry(GuestBook updatedGuestBook, int guestBookId);

	int updateKeyword(Keyword keywordUpdate, int keywordId);

	int removeKeywordMappingByGuestBookId(int guestBookId);

	GuestBook retrieveGuestBookByGuestBookId(int guestBookId);

	List<GuestBook> retrieveGuestBookListByKeywords(List<String> keywordList);

	int removeOrphanKeywords();

	List<GuestBook> retrieveGuestBookListByKeywordsInText(List<String> keywordList);

}
