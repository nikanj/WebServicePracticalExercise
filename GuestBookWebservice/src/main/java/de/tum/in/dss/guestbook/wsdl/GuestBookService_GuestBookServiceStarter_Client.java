
package de.tum.in.dss.guestbook.wsdl;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.1
 * 2013-06-30T22:57:12.227+02:00
 * Generated source version: 2.7.1
 * 
 */
public final class GuestBookService_GuestBookServiceStarter_Client {

    private static final QName SERVICE_NAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "GuestBookServiceLocator");

    private GuestBookService_GuestBookServiceStarter_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = GuestBookServiceLocator.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        GuestBookServiceLocator ss = new GuestBookServiceLocator(wsdlURL, SERVICE_NAME);
        GuestBookService port = ss.getGuestBookServiceStarter();  
        
        {
        System.out.println("Invoking addGuestBookEntries...");
        de.tum.in.dss.guestbook.Guestbook _addGuestBookEntries_request6PartInput = new de.tum.in.dss.guestbook.Guestbook();
        java.util.List<de.tum.in.dss.guestbooktype.Guestbooktype> _addGuestBookEntries_request6PartInputEntry = new java.util.ArrayList<de.tum.in.dss.guestbooktype.Guestbooktype>();
        de.tum.in.dss.guestbooktype.Guestbooktype _addGuestBookEntries_request6PartInputEntryVal1 = new de.tum.in.dss.guestbooktype.Guestbooktype();
        _addGuestBookEntries_request6PartInputEntryVal1.setBOOKID(1388419417);
        _addGuestBookEntries_request6PartInputEntryVal1.setAUTHORTITLE("AUTHORTITLE-2128776936");
        _addGuestBookEntries_request6PartInputEntryVal1.setAUTHORNAME("AUTHORNAME444607792");
        _addGuestBookEntries_request6PartInputEntryVal1.setBOOKDATA("BOOKDATA1350583929");
        _addGuestBookEntries_request6PartInputEntryVal1.setGUESTEMAILID("GUESTEMAILID1553871873");
        java.util.Date _addGuestBookEntries_request6PartInputEntryVal1DATEOFCREATION = null;
        _addGuestBookEntries_request6PartInputEntryVal1.setDATEOFCREATION(_addGuestBookEntries_request6PartInputEntryVal1DATEOFCREATION);
        java.util.Date _addGuestBookEntries_request6PartInputEntryVal1LASTMODIFICATIONDATE = null;
        _addGuestBookEntries_request6PartInputEntryVal1.setLASTMODIFICATIONDATE(_addGuestBookEntries_request6PartInputEntryVal1LASTMODIFICATIONDATE);
        de.tum.in.dss.keyword.Keywords _addGuestBookEntries_request6PartInputEntryVal1Keywords = new de.tum.in.dss.keyword.Keywords();
        java.util.List<java.lang.String> _addGuestBookEntries_request6PartInputEntryVal1KeywordsKeyword = new java.util.ArrayList<java.lang.String>();
        _addGuestBookEntries_request6PartInputEntryVal1Keywords.getKeyword().addAll(_addGuestBookEntries_request6PartInputEntryVal1KeywordsKeyword);
        _addGuestBookEntries_request6PartInputEntryVal1.setKeywords(_addGuestBookEntries_request6PartInputEntryVal1Keywords);
        _addGuestBookEntries_request6PartInputEntry.add(_addGuestBookEntries_request6PartInputEntryVal1);
        _addGuestBookEntries_request6PartInput.getEntry().addAll(_addGuestBookEntries_request6PartInputEntry);
        int _addGuestBookEntries__return = port.addGuestBookEntries(_addGuestBookEntries_request6PartInput);
        System.out.println("addGuestBookEntries.result=" + _addGuestBookEntries__return);


        }
        {
        System.out.println("Invoking retrieveAllGuestBooks...");
        de.tum.in.dss.guestbook.Guestbook _retrieveAllGuestBooks__return = port.retrieveAllGuestBooks();
        System.out.println("retrieveAllGuestBooks.result=" + _retrieveAllGuestBooks__return);


        }
        {
        System.out.println("Invoking retrieveGuestBookListByKeywords...");
        de.tum.in.dss.keyword.Keywords _retrieveGuestBookListByKeywords_request4PartInput = new de.tum.in.dss.keyword.Keywords();
        java.util.List<java.lang.String> _retrieveGuestBookListByKeywords_request4PartInputKeyword = new java.util.ArrayList<java.lang.String>();
        java.lang.String _retrieveGuestBookListByKeywords_request4PartInputKeywordVal1 = "_retrieveGuestBookListByKeywords_request4PartInputKeywordVal-1454441830";
        _retrieveGuestBookListByKeywords_request4PartInputKeyword.add(_retrieveGuestBookListByKeywords_request4PartInputKeywordVal1);
        _retrieveGuestBookListByKeywords_request4PartInput.getKeyword().addAll(_retrieveGuestBookListByKeywords_request4PartInputKeyword);
        de.tum.in.dss.guestbook.Guestbook _retrieveGuestBookListByKeywords__return = port.retrieveGuestBookListByKeywords(_retrieveGuestBookListByKeywords_request4PartInput);
        System.out.println("retrieveGuestBookListByKeywords.result=" + _retrieveGuestBookListByKeywords__return);


        }
        {
        System.out.println("Invoking retrieveGuestBookListByKeywordsInText...");
        de.tum.in.dss.keyword.Keywords _retrieveGuestBookListByKeywordsInText_request3PartInput = new de.tum.in.dss.keyword.Keywords();
        java.util.List<java.lang.String> _retrieveGuestBookListByKeywordsInText_request3PartInputKeyword = new java.util.ArrayList<java.lang.String>();
        java.lang.String _retrieveGuestBookListByKeywordsInText_request3PartInputKeywordVal1 = "_retrieveGuestBookListByKeywordsInText_request3PartInputKeywordVal2124778792";
        _retrieveGuestBookListByKeywordsInText_request3PartInputKeyword.add(_retrieveGuestBookListByKeywordsInText_request3PartInputKeywordVal1);
        _retrieveGuestBookListByKeywordsInText_request3PartInput.getKeyword().addAll(_retrieveGuestBookListByKeywordsInText_request3PartInputKeyword);
        de.tum.in.dss.guestbook.Guestbook _retrieveGuestBookListByKeywordsInText__return = port.retrieveGuestBookListByKeywordsInText(_retrieveGuestBookListByKeywordsInText_request3PartInput);
        System.out.println("retrieveGuestBookListByKeywordsInText.result=" + _retrieveGuestBookListByKeywordsInText__return);


        }
        {
        System.out.println("Invoking deleteGuestBook...");
        de.tum.in.dss.guestbooktype.Guestbooktype _deleteGuestBook_request9PartInput = new de.tum.in.dss.guestbooktype.Guestbooktype();
        _deleteGuestBook_request9PartInput.setBOOKID(-113247150);
        _deleteGuestBook_request9PartInput.setAUTHORTITLE("AUTHORTITLE-1156979756");
        _deleteGuestBook_request9PartInput.setAUTHORNAME("AUTHORNAME-797715849");
        _deleteGuestBook_request9PartInput.setBOOKDATA("BOOKDATA-353424899");
        _deleteGuestBook_request9PartInput.setGUESTEMAILID("GUESTEMAILID895899514");
        java.util.Date _deleteGuestBook_request9PartInputDATEOFCREATION = null;
        _deleteGuestBook_request9PartInput.setDATEOFCREATION(_deleteGuestBook_request9PartInputDATEOFCREATION);
        java.util.Date _deleteGuestBook_request9PartInputLASTMODIFICATIONDATE = null;
        _deleteGuestBook_request9PartInput.setLASTMODIFICATIONDATE(_deleteGuestBook_request9PartInputLASTMODIFICATIONDATE);
        de.tum.in.dss.keyword.Keywords _deleteGuestBook_request9PartInputKeywords = new de.tum.in.dss.keyword.Keywords();
        java.util.List<java.lang.String> _deleteGuestBook_request9PartInputKeywordsKeyword = new java.util.ArrayList<java.lang.String>();
        java.lang.String _deleteGuestBook_request9PartInputKeywordsKeywordVal1 = "_deleteGuestBook_request9PartInputKeywordsKeywordVal-149176674";
        _deleteGuestBook_request9PartInputKeywordsKeyword.add(_deleteGuestBook_request9PartInputKeywordsKeywordVal1);
        _deleteGuestBook_request9PartInputKeywords.getKeyword().addAll(_deleteGuestBook_request9PartInputKeywordsKeyword);
        _deleteGuestBook_request9PartInput.setKeywords(_deleteGuestBook_request9PartInputKeywords);
        int _deleteGuestBook__return = port.deleteGuestBook(_deleteGuestBook_request9PartInput);
        System.out.println("deleteGuestBook.result=" + _deleteGuestBook__return);


        }
        {
        System.out.println("Invoking retrieveGuestBookKeywords...");
        de.tum.in.dss.keyword.Keywords _retrieveGuestBookKeywords_request5PartInput = new de.tum.in.dss.keyword.Keywords();
        java.util.List<java.lang.String> _retrieveGuestBookKeywords_request5PartInputKeyword = new java.util.ArrayList<java.lang.String>();
        java.lang.String _retrieveGuestBookKeywords_request5PartInputKeywordVal1 = "_retrieveGuestBookKeywords_request5PartInputKeywordVal-1606400589";
        _retrieveGuestBookKeywords_request5PartInputKeyword.add(_retrieveGuestBookKeywords_request5PartInputKeywordVal1);
        _retrieveGuestBookKeywords_request5PartInput.getKeyword().addAll(_retrieveGuestBookKeywords_request5PartInputKeyword);
        de.tum.in.dss.keyword.Keywords _retrieveGuestBookKeywords__return = port.retrieveGuestBookKeywords(_retrieveGuestBookKeywords_request5PartInput);
        System.out.println("retrieveGuestBookKeywords.result=" + _retrieveGuestBookKeywords__return);


        }
        {
        System.out.println("Invoking updateGuestBookEntry...");
        de.tum.in.dss.guestbooktype.Guestbooktype _updateGuestBookEntry_request8PartInput = new de.tum.in.dss.guestbooktype.Guestbooktype();
        _updateGuestBookEntry_request8PartInput.setBOOKID(2061786009);
        _updateGuestBookEntry_request8PartInput.setAUTHORTITLE("AUTHORTITLE1152425115");
        _updateGuestBookEntry_request8PartInput.setAUTHORNAME("AUTHORNAME1538753002");
        _updateGuestBookEntry_request8PartInput.setBOOKDATA("BOOKDATA2144834696");
        _updateGuestBookEntry_request8PartInput.setGUESTEMAILID("GUESTEMAILID1130553108");
        java.util.Date _updateGuestBookEntry_request8PartInputDATEOFCREATION = null;
        _updateGuestBookEntry_request8PartInput.setDATEOFCREATION(_updateGuestBookEntry_request8PartInputDATEOFCREATION);
        java.util.Date _updateGuestBookEntry_request8PartInputLASTMODIFICATIONDATE = null;
        _updateGuestBookEntry_request8PartInput.setLASTMODIFICATIONDATE(_updateGuestBookEntry_request8PartInputLASTMODIFICATIONDATE);
        de.tum.in.dss.keyword.Keywords _updateGuestBookEntry_request8PartInputKeywords = new de.tum.in.dss.keyword.Keywords();
        java.util.List<java.lang.String> _updateGuestBookEntry_request8PartInputKeywordsKeyword = new java.util.ArrayList<java.lang.String>();
        java.lang.String _updateGuestBookEntry_request8PartInputKeywordsKeywordVal1 = "_updateGuestBookEntry_request8PartInputKeywordsKeywordVal332785074";
        _updateGuestBookEntry_request8PartInputKeywordsKeyword.add(_updateGuestBookEntry_request8PartInputKeywordsKeywordVal1);
        _updateGuestBookEntry_request8PartInputKeywords.getKeyword().addAll(_updateGuestBookEntry_request8PartInputKeywordsKeyword);
        _updateGuestBookEntry_request8PartInput.setKeywords(_updateGuestBookEntry_request8PartInputKeywords);
        int _updateGuestBookEntry__return = port.updateGuestBookEntry(_updateGuestBookEntry_request8PartInput);
        System.out.println("updateGuestBookEntry.result=" + _updateGuestBookEntry__return);


        }
        {
        System.out.println("Invoking retrieveGuestBookByGuestBookId...");
        int _retrieveGuestBookByGuestBookId_request2PartInput = -440654612;
        de.tum.in.dss.guestbooktype.Guestbooktype _retrieveGuestBookByGuestBookId__return = port.retrieveGuestBookByGuestBookId(_retrieveGuestBookByGuestBookId_request2PartInput);
        System.out.println("retrieveGuestBookByGuestBookId.result=" + _retrieveGuestBookByGuestBookId__return);


        }
        {
        System.out.println("Invoking createGuestBookEntry...");
        de.tum.in.dss.guestbooktype.Guestbooktype _createGuestBookEntry_request7PartInput = new de.tum.in.dss.guestbooktype.Guestbooktype();
        _createGuestBookEntry_request7PartInput.setBOOKID(-454891398);
        _createGuestBookEntry_request7PartInput.setAUTHORTITLE("AUTHORTITLE-1393846987");
        _createGuestBookEntry_request7PartInput.setAUTHORNAME("AUTHORNAME-1139877811");
        _createGuestBookEntry_request7PartInput.setBOOKDATA("BOOKDATA-1905318751");
        _createGuestBookEntry_request7PartInput.setGUESTEMAILID("GUESTEMAILID-757524569");
        java.util.Date _createGuestBookEntry_request7PartInputDATEOFCREATION = null;
        _createGuestBookEntry_request7PartInput.setDATEOFCREATION(_createGuestBookEntry_request7PartInputDATEOFCREATION);
        java.util.Date _createGuestBookEntry_request7PartInputLASTMODIFICATIONDATE = null;
        _createGuestBookEntry_request7PartInput.setLASTMODIFICATIONDATE(_createGuestBookEntry_request7PartInputLASTMODIFICATIONDATE);
        de.tum.in.dss.keyword.Keywords _createGuestBookEntry_request7PartInputKeywords = new de.tum.in.dss.keyword.Keywords();
        java.util.List<java.lang.String> _createGuestBookEntry_request7PartInputKeywordsKeyword = new java.util.ArrayList<java.lang.String>();
        java.lang.String _createGuestBookEntry_request7PartInputKeywordsKeywordVal1 = "_createGuestBookEntry_request7PartInputKeywordsKeywordVal136193336";
        _createGuestBookEntry_request7PartInputKeywordsKeyword.add(_createGuestBookEntry_request7PartInputKeywordsKeywordVal1);
        _createGuestBookEntry_request7PartInputKeywords.getKeyword().addAll(_createGuestBookEntry_request7PartInputKeywordsKeyword);
        _createGuestBookEntry_request7PartInput.setKeywords(_createGuestBookEntry_request7PartInputKeywords);
        int _createGuestBookEntry__return = port.createGuestBookEntry(_createGuestBookEntry_request7PartInput);
        System.out.println("createGuestBookEntry.result=" + _createGuestBookEntry__return);


        }

        System.exit(0);
    }

}