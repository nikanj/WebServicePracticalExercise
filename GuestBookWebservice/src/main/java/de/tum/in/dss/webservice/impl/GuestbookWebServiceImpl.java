
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package de.tum.in.dss.webservice.impl;

import java.util.List;
import java.util.logging.Logger;

import de.tum.in.dss.dao.GuestBookDao;
import de.tum.in.dss.guestbook.Guestbook;
import de.tum.in.dss.guestbook.wsdl.GuestBookService;
import de.tum.in.dss.guestbooktype.Guestbooktype;
import de.tum.in.dss.helper.ModelConverterHelper;
import de.tum.in.dss.helper.WebserviceHelper;
import de.tum.in.dss.keyword.Keywords;
import de.tum.in.dss.model.GuestBook;

/**
 * This class was generated by Apache CXF 2.7.1
 * 2012-12-20T00:52:49.112+01:00
 * Generated source version: 2.7.1
 * 
 */

@javax.jws.WebService(
        serviceName = "GuestBookServiceLocator",
        portName = "GuestBookServiceStarter",
        targetNamespace = "http://dss.in.tum.de/guestbook/wsdl",
        wsdlLocation = "classpath:guestbook.wsdl",
        endpointInterface = "de.tum.in.dss.guestbook.wsdl.GuestBookService")

public class GuestbookWebServiceImpl implements GuestBookService {

	private WebserviceHelper helper;
	private GuestBookDao guestBookDao;
	private ModelConverterHelper modelConverterHelper;
	
    private static final Logger LOG = Logger.getLogger(GuestbookWebServiceImpl.class.getName());

    /**
	 * @return the helper
	 */
	public WebserviceHelper getHelper() {
		return helper;
	}

	/**
	 * @param helper the helper to set
	 */
	public void setHelper(WebserviceHelper helper) {
		this.helper = helper;
	}

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

/*	private void getHelperBeanFromContext(){
    	if(helper==null){
	    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beanContext.xml"});
	    	helper = (WebserviceHelper) context.getBean("helper");
    	}
    }*/

	@Override
	public int addGuestBookEntries(Guestbook request6PartInput) {
		LOG.info("Executing operation addGuestBookEntries");
		return guestBookDao.addGuestBookEntries(modelConverterHelper.copyGuestbooktypeListToGuestBookList(request6PartInput.getEntry()));
	}

	@Override
	public Guestbook retrieveAllGuestBooks() {
		LOG.info("Executing operation retrieveAllGuestBooks");
		List<GuestBook> booklist = guestBookDao.retrieveAllGuestBooks();
		return modelConverterHelper.copyGuestBookListToGuestbook(booklist);
	}

	@Override
	public Guestbook retrieveGuestBookListByKeywords(Keywords request4PartInput) {
		LOG.info("Executing operation retrieveGuestBookListByKeywords");
		List<GuestBook> booklist = guestBookDao.retrieveGuestBookListByKeywords(request4PartInput.getKeyword());
		return modelConverterHelper.copyGuestBookListToGuestbook(booklist);
	}

	@Override
	public Guestbook retrieveGuestBookListByKeywordsInText(Keywords request3PartInput) {
		LOG.info("Executing operation retrieveGuestBookListByKeywordsInText");
		List<GuestBook> booklist = guestBookDao.retrieveGuestBookListByKeywordsInText(request3PartInput.getKeyword());
		return modelConverterHelper.copyGuestBookListToGuestbook(booklist);
	}

	@Override
	public int deleteGuestBook(Guestbooktype request9PartInput) {
		LOG.info("Executing operation deleteGuestBook");
		return guestBookDao.deleteGuestBook(modelConverterHelper.copyGuestbooktypeToGuestBook(request9PartInput));
	}

	@Override
	public Keywords retrieveGuestBookKeywords(Keywords request5PartInput) {
		LOG.info("Executing operation retrieveGuestBookKeywords");
		List<String> keywordsList = guestBookDao.retrieveGuestBookKeywords(request5PartInput.getKeyword());
		return modelConverterHelper.copyKeywordListToKeywords(keywordsList);
	}

	@Override
	public int updateGuestBookEntry(Guestbooktype request8PartInput) {
		LOG.info("Executing operation retrieveAllGuestBooks");
		return guestBookDao.updateGuestBookEntry(modelConverterHelper.copyGuestbooktypeToGuestBook(request8PartInput));
	}

	@Override
	public Guestbooktype retrieveGuestBookByGuestBookId(int request2PartInput) {
		LOG.info("Executing operation retrieveGuestBookByGuestBookId");
		GuestBook book = guestBookDao.retrieveGuestBookByGuestBookId(request2PartInput);
		return modelConverterHelper.copyGuestBookToGuestbooktype(book);
	}

	@Override
	public int createGuestBookEntry(Guestbooktype request7PartInput) {
		LOG.info("Executing operation createGuestBookEntry");
		return guestBookDao.createGuestBookEntry(modelConverterHelper.copyGuestbooktypeToGuestBook(request7PartInput));
	}
}
