/**
 * 
 */
package de.tum.in.dss.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import de.tum.in.dss.additional.GuestBookAdditional;
import de.tum.in.dss.constant.CommonConstants;
import de.tum.in.dss.dao.GuestBookDao;
import de.tum.in.dss.model.GuestBook;
import de.tum.in.dss.model.GuestBookKeywordMap;
import de.tum.in.dss.model.Keyword;

public class GuestBookDaoImpl implements GuestBookDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
	}

	/**
	 * @return the namedParameterJdbcTemplate
	 */
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	/**
	 * @param namedParameterJdbcTemplate
	 *            the namedParameterJdbcTemplate to set
	 */
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int createGuestBookEntry(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createGuestBookEntry(GuestBook create) {
		int created = 0;

		String sqlQueryCreate1 = "INSERT INTO GUEST_BOOK (AUTHOR_NAME, AUTHOR_TITLE,BOOK_DATA,GUEST_EMAIL_ID,DATE_OF_CREATION,LAST_MODIFICATION_DATE) VALUES(?,?,?,?,?,?)";

		created = this.jdbcTemplate.update(
				sqlQueryCreate1,
				new Object[] { create.getName(), create.getTitle(),
						create.getText(), create.getEmail(),
						Calendar.getInstance().getTime(),
						Calendar.getInstance().getTime() });

		if (create.getKeywordList() != null
				&& create.getKeywordList().size() > 0) {
			try {
				insertKeywords(create.getKeywordList());
			} catch (Exception e) {
			}
			mappingGuestBookKeywords(create);

		}

		return created;

	}

	@Override
	public int[] insertKeywords(final List<String> keywords) {

		String sqlQueryCreate2 = "INSERT INTO KEYWORD (KEYWORD_VALUE) VALUES (?)";

		int[] rowsUpdated = this.jdbcTemplate.batchUpdate(sqlQueryCreate2,
				new BatchPreparedStatementSetter() {

					@Override
					public int getBatchSize() {
						return keywords.size();
					}

					@Override
					public void setValues(java.sql.PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, keywords.get(i));

					}
				});
		return rowsUpdated;
	}

	@Override
	public int updateKeyword(Keyword keywordUpdate, int keywordId) {
		int updated = 0;
		String sqlQueryUpdate = "UPDATE KEYWORD SET KEYWORD_VALUE = ? where KEYWORD_ID = ?";

		updated = this.jdbcTemplate.update(sqlQueryUpdate, new Object[] {
				keywordUpdate.getKeywordValue(), keywordId });

		return updated;
	}

	public void mappingGuestBookKeywords(GuestBook guestBookToMap) {
		final int guestBookId;
		String sqlQueryCreate2 = "INSERT INTO GUEST_BOOK_KEYWORD_MAPPING (BOOK_ID, KEYWORD_ID) VALUES (?,?)";

		if (guestBookToMap != null && guestBookToMap.getGuestBookId() <= 0) {
			List<GuestBook> guestBookList = retrieveGuestBookByGuestBookName(guestBookToMap);
			guestBookId = guestBookList.get(0).getGuestBookId();
		} else {
			guestBookId = guestBookToMap.getGuestBookId();
		}
		final List<Keyword> keywordObjList = retrieveKeywords(guestBookToMap
				.getKeywordList());
		// remove old mappings
		removeKeywordMappingByGuestBookId(guestBookId);

		if (keywordObjList != null && keywordObjList.size() > 0) {
			// insert new mappings
			this.jdbcTemplate.batchUpdate(sqlQueryCreate2,
					new BatchPreparedStatementSetter() {

						@Override
						public int getBatchSize() {
							return keywordObjList.size();
						}

						@Override
						public void setValues(java.sql.PreparedStatement ps,
								int i) throws SQLException {
							ps.setInt(1, guestBookId);
							ps.setInt(2, keywordObjList.get(i).getKeywordId());

						}
					});
		}

		// check and remove orphan keywords
		removeOrphanKeywords();
	}

	@Override
	public int removeOrphanKeywords() {
		int deleted = 0;
		List<Integer> orphanedkeywordIdList = retrieveKeywordIdWithNoMapping();

		String sqlQuerydelete = "DELETE FROM KEYWORD WHERE keyword_id in (:keywords)";

		if (orphanedkeywordIdList != null && orphanedkeywordIdList.size() > 0) {
			Map<String, List<Integer>> params = Collections.singletonMap(
					"keywords", orphanedkeywordIdList);
			deleted = this.namedParameterJdbcTemplate.update(sqlQuerydelete,
					params);
		}

		return deleted;

	}

	@SuppressWarnings("unchecked")
	public List<Integer> retrieveKeywordIdWithNoMapping() {
		String sql = "select b.keyword_id from KEYWORD b left outer join "
				+ "GUEST_BOOK_KEYWORD_MAPPING s on b.keyword_id = s.keyword_id "
				+ "where s.book_id is null";

		List<Integer> keywordIdList = null;
		keywordIdList = this.jdbcTemplate.queryForList(sql, Integer.class);
		return keywordIdList;
	}

	@Override
	public int removeKeywordMappingByGuestBookId(int guestBookId) {

		String sqlQuerydelete = "DELETE FROM GUEST_BOOK_KEYWORD_MAPPING WHERE BOOK_ID = ?";

		int deleted = this.jdbcTemplate.update(sqlQuerydelete,
				new Object[] { guestBookId });

		return deleted;
	}

	@SuppressWarnings("unchecked")
	public List<Keyword> retrieveKeywords(final List<String> keywords) {

		String sql = "SELECT * FROM KEYWORD WHERE KEYWORD_VALUE IN (:keywords)";

		if (keywords != null && keywords.size() > 0) {
			Map<String, List<String>> params = Collections.singletonMap(
					"keywords", keywords);

			// SqlParameterSource namedParameters = new
			// BeanPropertySqlParameterSource(keywords);
			Object obj = this.namedParameterJdbcTemplate.query(sql, params,
					new KeywordMapper());

			if (obj != null) {
				List<Keyword> keywordList = (List<Keyword>) obj;
				return keywordList;
			}
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestBook> retrieveGuestBook(GuestBook guestBook) {
		String sql = "select * from GUEST_BOOK where BOOK_ID = :guestBookId or AUTHOR_NAME = :name or AUTHOR_TITLE = :title or BOOK_DATA = :text or GUEST_EMAIL_ID = :email";

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				guestBook);
		Object obj = this.namedParameterJdbcTemplate.query(sql,
				namedParameters, new GuestBookMapper());

		if (obj != null) {
			List<GuestBook> guestBookList = (List<GuestBook>) obj;
			return guestBookList;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestBook> retrieveGuestBookWithKeywords(GuestBook guestBook) {
		String sql = "select * from GUEST_BOOK where BOOK_ID = :guestBookId or AUTHOR_NAME = :name or AUTHOR_TITLE = :title or BOOK_DATA = :text or GUEST_EMAIL_ID = :email";

		List<GuestBook> guestBookList = null;
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				guestBook);
		Object obj = this.namedParameterJdbcTemplate.query(sql,
				namedParameters, new GuestBookMapper());

		if (obj != null) {
			guestBookList = (List<GuestBook>) obj;
			Iterator<GuestBook> guestBookIt = guestBookList.iterator();

			while (guestBookIt != null && guestBookIt.hasNext()) {
				GuestBook book = guestBookIt.next();
				List<String> keywordsList = retrieveGuestBookKeywordsByGuestBookId(book
						.getGuestBookId());
				book.setKeywordList(keywordsList);
			}

		} else {
			return null;
		}

		return guestBookList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public GuestBook retrieveGuestBookByGuestBookId(int guestBookId) {
		String sql = "select * from GUEST_BOOK where BOOK_ID = ?";
		GuestBook book = null;
		Object obj = this.jdbcTemplate.query(sql, new Object[] { guestBookId },
				new GuestBookMapper());
		if (obj != null) {
			List<GuestBook> booklist = ((List<GuestBook>) obj);
			if (booklist != null && booklist.size() == 1) {
				book = booklist.get(0);
				List<String> keywordsList = retrieveGuestBookKeywordsByGuestBookId(book
						.getGuestBookId());
				book.setKeywordList(keywordsList);
			}
		}

		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestBook> retrieveGuestBookListByKeyword(String keyword) {
		String sql = "select * from GUEST_BOOK where BOOK_ID in (select book_id from GUEST_BOOK_KEYWORD_MAPPING where keyword_id in ( "
				+ "(select keyword_id from KEYWORD where keyword_value = ?)))";

		List<GuestBook> guestBookList = null;
		Object obj = this.jdbcTemplate.query(sql, new Object[] { keyword },
				new GuestBookMapper());

		if (obj != null) {
			guestBookList = (List<GuestBook>) obj;
			Iterator<GuestBook> guestBookIt = guestBookList.iterator();

			while (guestBookIt != null && guestBookIt.hasNext()) {
				GuestBook book = guestBookIt.next();
				List<String> keywordsList = retrieveGuestBookKeywordsByGuestBookId(book
						.getGuestBookId());
				book.setKeywordList(keywordsList);
			}

		} else {
			return null;
		}

		return guestBookList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestBook> retrieveGuestBookListByKeywords(
			final List<String> keywordList) {
		String sql = "select * from GUEST_BOOK where BOOK_ID in (select book_id from GUEST_BOOK_KEYWORD_MAPPING where keyword_id in ( "
				+ "select keyword_id from KEYWORD where keyword_value in (:keywords)))";

		List<GuestBook> guestBookList = new ArrayList<GuestBook>();

		if (keywordList != null && keywordList.size() > 0) {
			Map<String, List<String>> params = Collections.singletonMap(
					"keywords", keywordList);

			Object obj = this.namedParameterJdbcTemplate.query(sql, params,
					new GuestBookMapper());

			if (obj != null) {
				guestBookList = (List<GuestBook>) obj;
				Iterator<GuestBook> guestBookIt = guestBookList.iterator();

				while (guestBookIt != null && guestBookIt.hasNext()) {
					GuestBook book = guestBookIt.next();
					List<String> keywordsList = retrieveGuestBookKeywordsByGuestBookId(book
							.getGuestBookId());
					book.setKeywordList(keywordsList);
				}
			}
		}
		return guestBookList;
	}

	@SuppressWarnings("unchecked")
	public List<String> retrieveGuestBookKeywordsByGuestBookId(int guestBookId) {
		String sql = "select KEYWORD_VALUE from KEYWORD where KEYWORD_ID IN (select KEYWORD_ID from GUEST_BOOK_KEYWORD_MAPPING where BOOK_ID = ? )";

		// Map<String, List<Integer>> params =
		// Collections.singletonMap("guestBookId", guestBookId);

		List<String> guestBookKeywordsList = this.jdbcTemplate.query(sql,
				new Object[] { guestBookId }, new RowMapper() {
					public Object mapRow(ResultSet resultSet, int i)
							throws SQLException {
						return resultSet.getString(1);
					}
				});

		return guestBookKeywordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> retrieveGuestBookKeywords(List<String> keywordLikeList) {
		String sql = "select KEYWORD_VALUE from KEYWORD where KEYWORD_VALUE LIKE ?";
		List<String> guestBookKeywordsListFull = new ArrayList<String>();

		for (String keywordlike : keywordLikeList) {
			List<String> guestBookKeywordsList = this.jdbcTemplate.query(sql,
					new Object[] { CommonConstants.LIKE_OP + keywordlike
							+ CommonConstants.LIKE_OP }, new RowMapper() {
						public Object mapRow(ResultSet resultSet, int i)
								throws SQLException {
							return resultSet.getString(1);
						}
					});
			guestBookKeywordsListFull.addAll(guestBookKeywordsList);
		}

		guestBookKeywordsListFull = GuestBookAdditional
				.removeDuplicates(guestBookKeywordsListFull);
		return guestBookKeywordsListFull;
	}

	@SuppressWarnings("unchecked")
	public List<GuestBook> retrieveGuestBookByGuestBookName(GuestBook guestBook) {
		String sql = "select * from GUEST_BOOK where BOOK_ID = :guestBookId or AUTHOR_NAME = :name";

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				guestBook);
		Object obj = this.namedParameterJdbcTemplate.query(sql,
				namedParameters, new GuestBookMapper());

		if (obj != null) {
			List<GuestBook> guestBookList = (List<GuestBook>) obj;
			return guestBookList;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestBook> retrieveAllGuestBooks() {
		List<GuestBook> guestBookList = null;

		String query = "select * from GUEST_BOOK";
		Object obj = jdbcTemplate.query(query, new GuestBookMapper());

		if (obj != null) {
			guestBookList = (List<GuestBook>) obj;
			Iterator<GuestBook> guestBookIt = guestBookList.iterator();

			while (guestBookIt != null && guestBookIt.hasNext()) {
				GuestBook book = guestBookIt.next();
				List<String> keywordsList = retrieveGuestBookKeywordsByGuestBookId(book
						.getGuestBookId());
				book.setKeywordList(keywordsList);
			}
		}
		return guestBookList;
	}

	@Override
	public void deleteAllGuestBooks() {
		// TODO Auto-generated method stub

	}

	@Override
	public int deleteGuestBook(GuestBook delete) {
		int deleted = 0;
		// first remove all mappings with keywords
		removeKeywordMappingByGuestBookId(delete.getGuestBookId());

		deleted = this.jdbcTemplate.update(
				"DELETE from GUEST_BOOK where BOOK_ID = ?",
				new Object[] { delete.getGuestBookId() });

		// After deleting guestbook entry delete orphan keywords
		removeOrphanKeywords();

		return deleted;
	}

	private static final class GuestBookMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			GuestBook book = new GuestBook();

			book.setGuestBookId(rs.getInt("BOOK_ID"));
			book.setName(rs.getString("AUTHOR_NAME"));
			book.setTitle(rs.getString("AUTHOR_TITLE"));
			book.setText(rs.getString("BOOK_DATA"));
			book.setEmail(rs.getString("GUEST_EMAIL_ID"));
			book.setCreateDate(new java.sql.Date(rs.getTimestamp(
					"DATE_OF_CREATION").getTime()));
			book.setLastModifiedDate(new java.sql.Date(rs.getTimestamp(
					"LAST_MODIFICATION_DATE").getTime()));

			return book;
		}
	}

	private static final class KeywordMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Keyword keyword = new Keyword();

			keyword.setKeywordId(rs.getInt("KEYWORD_ID"));
			keyword.setKeywordValue(rs.getString("KEYWORD_VALUE"));
			return keyword;
		}
	}

	@SuppressWarnings("unused")
	private static final class GuestBookKeywordRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			GuestBookKeywordMap mappedObj = new GuestBookKeywordMap();

			mappedObj.setKeywordId(rs.getInt("KEYWORD_ID"));
			mappedObj.setGuestBookId(rs.getInt("BOOK_ID"));

			return mappedObj;
		}
	}

	@Override
	public List<GuestBook> retrieveGuestBook(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateGuestBookEntry(GuestBook updatedGuestBook, int guestBookId) {
		int updated = 0;
		String sqlQueryUpdate = "UPDATE GUEST_BOOK SET AUTHOR_NAME = ?, AUTHOR_TITLE = ?, BOOK_DATA = ?, GUEST_EMAIL_ID = ?,LAST_MODIFICATION_DATE = ? where BOOK_ID = ?";

		updated = this.jdbcTemplate.update(sqlQueryUpdate, new Object[] {
				updatedGuestBook.getName(), updatedGuestBook.getTitle(),
				updatedGuestBook.getText(), updatedGuestBook.getEmail(),
				Calendar.getInstance().getTime(), guestBookId });

		List<String> keywords = updatedGuestBook.getKeywordList();
		updatedGuestBook = retrieveGuestBookByGuestBookId(guestBookId);
		updatedGuestBook.setKeywordList(keywords);

		// update mappings too
		if (updatedGuestBook.getKeywordList() != null
				&& updatedGuestBook.getKeywordList().size() > 0) {
			try {
				insertKeywords(updatedGuestBook.getKeywordList());
			} catch (Exception e) {
			}
		}
		mappingGuestBookKeywords(updatedGuestBook);

		return updated;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Keyword> retrieveGuestBookKeywords(String keywordLike) {
		String sql = "select KEYWORD_VALUE from KEYWORD where KEYWORD_VALUE LIKE ?";

		List<Keyword> guestBookKeywordsList = this.jdbcTemplate.query(sql,
				new Object[] { CommonConstants.LIKE_OP + keywordLike
						+ CommonConstants.LIKE_OP }, new KeywordMapper());

		guestBookKeywordsList = GuestBookAdditional
				.removeDuplicateKeywordObjects(guestBookKeywordsList);
		return guestBookKeywordsList;
	}

	@Override
	public int updateGuestBookEntry(GuestBook update) {

		return updateGuestBookEntry(update, update.getGuestBookId());
	}

	@Override
	public List<GuestBook> retrieveGuestBookListByKeywordsInText(
			List<String> keywordList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addGuestBookEntries(List<GuestBook> entries) {
		int added = 0;

		if (entries != null) {
			Iterator<GuestBook> bookIt = entries.iterator();

			while (bookIt != null && bookIt.hasNext()) {
				GuestBook temp = bookIt.next();
				GuestBook found = retrieveGuestBookByGuestBookName(temp
						.getName());

				if (found == null) {
					temp.setGuestBookId(0);
					createGuestBookEntry(temp);
					added++;
				}
			}
		}
		return added;
	}

	@Override
	public GuestBook retrieveGuestBookByGuestBookName(String guestBookName) {
		GuestBook book = new GuestBook();
		book.setName(guestBookName);
		List<GuestBook> list = retrieveGuestBookByGuestBookName(book);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
