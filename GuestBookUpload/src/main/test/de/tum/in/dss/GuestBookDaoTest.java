/**
 * 
 */
package de.tum.in.dss;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tum.in.dss.additional.GuestBookAdditional;
import de.tum.in.dss.constant.CommonConstants;
import de.tum.in.dss.dao.GuestBookDao;
import de.tum.in.dss.model.GuestBook;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-dao-config.xml"})
public class GuestBookDaoTest {

	@Autowired
	private GuestBookDao guestBookDao;
	
	/**
	 * @return the guestBookDao
	 */
	public GuestBookDao getGuestBookDao() {
		return guestBookDao;
	}

	/**
	 * @param guestBookDao the guestBookDao to set
	 */
	public void setGuestBookDao(GuestBookDao guestBookDao) {
		this.guestBookDao = guestBookDao;
	}

	@Before
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
	}

	@After
	public void tearDown() throws Exception {
		// TODO Auto-generated method stub
	}
	
	
	//@Test
    public void testRetrieveAllGuestBooks() {
        // logic which does not modify database state
		List<GuestBook> list = null;
		try {
			list = guestBookDao.retrieveAllGuestBooks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list);
    }
	
	@Test
    public void testRetrieveGuestBookKeywords() {
        // logic which does not modify database state
		List<String> list = null;
		try {
			List<String> keywordLikeList =  new ArrayList<String>();
			keywordLikeList.add("4 ");
			keywordLikeList.add("22 ");
			keywordLikeList.add("1  1");
			keywordLikeList.add("test ");
			list = guestBookDao.retrieveGuestBookKeywords(keywordLikeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list);
    }
	
	//@Test
    public void testRetrieveGuestBookListByKeyword() {
        // logic which does not modify database state
		List<GuestBook> list = null;
		try {
			list = guestBookDao.retrieveGuestBookListByKeyword("444");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Found: "+ list);
    }
	
	//@Test
    public void testInsertKeywords() {
        // logic which does not modify database state
		
		try {
			List<String> keywordList = new ArrayList<String>();
			keywordList.add("11");
			keywordList.add("111");
			keywordList.add("33");
			keywordList.add("222");
			int[] rowsinserted= guestBookDao.insertKeywords(keywordList);
			System.out.println("Rows inserted: "+ rowsinserted.length);
		} catch (Exception e) {
			System.out.println("Keyword already present");
		}
		
    }
	
	@Test
    public void testRetrieveGuestBookByGuestBookId() {
        // logic which does not modify database state
		GuestBook book = null;
		book = guestBookDao.retrieveGuestBookByGuestBookId(3);
		System.out.println("Found: "+ book);
    }
	
	
	@Test
    public void testRetrieveGuestBookListByKeywords(){
		List<GuestBook> list = null;
		try {
			List<String> keywordList = GuestBookAdditional.separateKeywordsToList("4 1 3    22 ", CommonConstants.WHITESPACE);
		
			list = guestBookDao.retrieveGuestBookListByKeywords(keywordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Found: "+ list);
	}
	
	//@Test
    public void testRetrieveGuestBook() {
        // logic which does not modify database state
		List<GuestBook> list = null;
		try {
			list = guestBookDao.retrieveGuestBook(new GuestBook("mr","jitin","some text",null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Found: "+ list);
    }
	
    //@Test
    public void testRemoveOrphanKeywords(){
    	int deleted = 0;
    	deleted = guestBookDao.removeOrphanKeywords();
		System.out.println("Orphan keywords deleted: "+ deleted);
    }
	
	@Test
    public void testCreateGuestBookEntry() {
        // logic which does not modify database state
		GuestBook testGuestBook = new GuestBook();
		testGuestBook.setName("testname14");
		testGuestBook.setTitle("testtitle");
		testGuestBook.setText("test text");
		testGuestBook.setEmail("test@email");
		List<String> keywordList = new ArrayList<String>();
		keywordList.add("1");
		keywordList.add("2");
		keywordList.add("3");
		keywordList.add("4");
		keywordList.add("44444");
		keywordList.add("5555555");
		keywordList.add("66666666");
		testGuestBook.setKeywordList(keywordList);
		try {
			System.out.println("Create status: "+guestBookDao.createGuestBookEntry(testGuestBook));
			System.out.println(guestBookDao.retrieveGuestBook(testGuestBook));
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Guest Book entry already exist");
			//e.printStackTrace();
		}
		
    }
	
	//@Test
    public void testdeleteGuestBookEntry() {
        // logic which does not modify database state
		GuestBook delete = new GuestBook();
		delete.setGuestBookId(2);
		try {
			System.out.println("delete status: "+guestBookDao.deleteGuestBook(delete));
		} 
		catch (Exception e) {
			System.out.println("Guest Book entry not exist");
			e.printStackTrace();
		}
		
    }

}
