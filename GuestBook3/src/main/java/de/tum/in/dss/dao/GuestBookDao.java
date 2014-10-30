/**
 * 
 */
package de.tum.in.dss.dao;

import java.sql.SQLException;
import java.util.List;

import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.Keyword;


public interface GuestBookDao {

		  /*void setDataSource(DataSource ds);
		  
		  void setJdbcTemplate(JdbcTemplate jdbcTemplate);*/

		  int createGuestBookEntry(String firstName, String lastName) throws SQLException ;
		  int createGuestBookEntry(GuestBook create) throws SQLException ;
		  int updateGuestBookEntry(GuestBook update) throws SQLException ;
		  List<GuestBook> retrieveGuestBook(String firstname, String lastname) throws SQLException ;
		  List<GuestBook> retrieveGuestBook(GuestBook guestBook) throws SQLException ;
		  List<GuestBook> retrieveAllGuestBooks() throws SQLException ;
		  void deleteAllGuestBooks() throws SQLException ;
		  int deleteGuestBook(GuestBook delete) throws SQLException ;
		  List<GuestBook> retrieveGuestBookWithKeywords(GuestBook guestBook);
		  List<Keyword> retrieveGuestBookKeywords(String keywordLike);
		  List<String> retrieveGuestBookKeywords(List<String> keywordLikeList);
		  List<GuestBook> retrieveGuestBookListByKeyword(String keyword);
		  int[] insertKeywords(List<String> keywords);
		  int updateGuestBookEntry(GuestBook updatedGuestBook, int guestBookId)
				throws SQLException;
		int updateKeyword(Keyword keywordUpdate, int keywordId);
		int removeKeywordMappingByGuestBookId(int guestBookId);
		GuestBook retrieveGuestBookByGuestBookId(int guestBookId);
		List<GuestBook> retrieveGuestBookListByKeywords(List<String> keywordList);
		int removeOrphanKeywords();

}
