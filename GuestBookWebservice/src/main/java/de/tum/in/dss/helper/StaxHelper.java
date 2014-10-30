/**
 * 
 */
package de.tum.in.dss.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import de.tum.in.dss.constant.GuestBookXMLConstants;
import de.tum.in.dss.model.GuestBook;


public class StaxHelper {
	
	private Resource fileResource;
	private Resource xsdFileResource;
	private File tempfileResource;
	private XMLStreamWriter writer;
	private XMLEventWriter eventWriter;
	private XMLStreamReader reader;
	private XMLEventReader eventReader;
	private XMLOutputFactory xmlOutputFactory;
	private XMLInputFactory xmlInputFactory;
	private XMLEventFactory  xmlEventFactory;

	/**
	 * @return the xsdFileResource
	 */
	public Resource getXsdFileResource() {
		return xsdFileResource;
	}

	/**
	 * @param xsdFileResource the xsdFileResource to set
	 */
	public void setXsdFileResource(Resource xsdFileResource) {
		this.xsdFileResource = xsdFileResource;
	}

	/**
	 * @return the eventReader
	 */
	public XMLEventReader getEventReader() {
		return eventReader;
	}

	/**
	 * @param eventReader the eventReader to set
	 */
	public void setEventReader(XMLEventReader eventReader) {
		this.eventReader = eventReader;
	}

	/**
	 * @return the reader
	 */
	public XMLStreamReader getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(XMLStreamReader reader) {
		this.reader = reader;
	}

	/**
	 * @return the xmlInputFactory
	 */
	public XMLInputFactory getXmlInputFactory() {
		return xmlInputFactory;
	}

	/**
	 * @param xmlInputFactory the xmlInputFactory to set
	 */
	public void setXmlInputFactory(XMLInputFactory xmlInputFactory) {
		this.xmlInputFactory = xmlInputFactory;
	}

	/**
	 * @return the fileResource
	 */
	public Resource getFileResource() {
		return fileResource;
	}

	/**
	 * @param fileResource the fileResource to set
	 */
	public void setFileResource(Resource fileResource) {
		this.fileResource = fileResource;
	}

	/**
	 * @return the writer
	 */
	public XMLStreamWriter getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	public void setWriter(XMLStreamWriter writer) {
		this.writer = writer;
	}

	/**
	 * @return the eventWriter
	 */
	public XMLEventWriter getEventWriter() {
		return eventWriter;
	}

	/**
	 * @param eventWriter the eventWriter to set
	 */
	public void setEventWriter(XMLEventWriter eventWriter) {
		this.eventWriter = eventWriter;
	}

	/**
	 * @return the xmlOutputFactory
	 */
	public XMLOutputFactory getXmlOutputFactory() {
		return xmlOutputFactory;
	}

	/**
	 * @param xmlOutputFactory the xmlOutputFactory to set
	 */
	public void setXmlOutputFactory(XMLOutputFactory xmlOutputFactory) {
		this.xmlOutputFactory = xmlOutputFactory;
	}
	
	/**
	 * @return the xmlEventFactory
	 */
	public XMLEventFactory getXmlEventFactory() {
		return xmlEventFactory;
	}

	/**
	 * @param xmlEventFactory the xmlEventFactory to set
	 */
	public void setXmlEventFactory(XMLEventFactory xmlEventFactory) {
		this.xmlEventFactory = xmlEventFactory;
	}

	public void createWriter(){
		try {
			tempfileResource = File.createTempFile("temp", "xml");
			writer = xmlOutputFactory.createXMLStreamWriter(new FileWriter(tempfileResource));
			eventWriter = xmlOutputFactory.createXMLEventWriter(new FileWriter(tempfileResource));
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createReader(){
		try {
			
			reader = xmlInputFactory.createXMLStreamReader(new FileReader(fileResource.getFile()));
			eventReader =xmlInputFactory.createXMLEventReader(new FileReader(fileResource.getFile()));
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object createReader(File fileResource,boolean isEventReader){

		try {
			if(!isEventReader){
				return (xmlInputFactory.createXMLStreamReader(new FileReader(fileResource)));
			}
			else{
				return (xmlInputFactory.createXMLEventReader(new FileReader(fileResource)));
			}
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int addGuestBookNode(GuestBook create, boolean isEmptyDocument) {

		removeAllWhitespace();
		int added = 0;
		try {
			createWriter();
			createReader();
			int nodeinsert = 0;
			
			// Validating existing XML
			validateXMLFile(reader);
			createReader(); 
			if(!isEmptyDocument){
			
				while (eventReader.hasNext()) {
					XMLEvent event = eventReader.nextEvent();
	
					if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
						StartElement startElement = event.asStartElement();
						if (startElement.getName().getLocalPart().equalsIgnoreCase(GuestBookXMLConstants.GUEST_BOOK_MAIN_SUFFIX)) {
							nodeinsert++;
						}
						//System.out.print(startElement.getName().getLocalPart());
					}
					//System.out.println("EventWriter: "+event.getEventType());
					eventWriter.add(event);
	
					if (nodeinsert == 1) {
						createNode(create);
						nodeinsert++;
					}
				}
			}
			else{
				 //XMLEvent end = xmlEventFactory.createDTD("\n ");
				
				 StartDocument startDocument = xmlEventFactory.createStartDocument("UTF-8", "1.0");
				 eventWriter.add(startDocument);
				// eventWriter.add(end);
				 
				 Namespace ns1 = xmlEventFactory.createNamespace("k", "http://dss.in.tum.de/keyword");
				 Namespace ns2 = xmlEventFactory.createNamespace("entry","http://dss.in.tum.de/guestbooktype");
				 Namespace ns3 = xmlEventFactory.createNamespace("gb","http://dss.in.tum.de/guestbook");
				 Namespace ns4 = xmlEventFactory.createNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
				 Namespace ns5 = xmlEventFactory.createNamespace("schemaLocation","http://dss.in.tum.de/guestbook xsd/guestbook.xsd");

				 List<Namespace> nsList = Arrays.asList(ns1,ns2,ns3,ns4,ns5);
				 
				 XMLEvent startElement = xmlEventFactory.createStartElement(new QName(GuestBookXMLConstants.GUEST_BOOK_ENTRY_FIRST_ELEMENT),null,nsList.iterator());
				 eventWriter.add(startElement);
				// eventWriter.add(end);
				 
				 createNode(create);
				 
				 XMLEvent endElement = xmlEventFactory.createEndElement( new QName(GuestBookXMLConstants.GUEST_BOOK_ENTRY_FIRST_ELEMENT),nsList.iterator());  
				 eventWriter.add(endElement);
				
				 eventWriter.add(xmlEventFactory.createEndDocument());
			}
			
			eventWriter.flush();
			eventWriter.close();
			
			// validating generated XML
			Object obj = createReader(tempfileResource,false);
			if(obj!=null){
				XMLStreamReader tempReader = (XMLStreamReader) obj;
				validateXMLFile(tempReader);
			}
			
			copyfile(tempfileResource, fileResource.getFile());
			tempfileResource.delete();
			added++;
		} catch (XMLStreamException | IOException | SAXException e) {
			e.printStackTrace();
			return added;
		}
		return added;
	}
	
	public void validateXMLFile(XMLStreamReader reader) throws SAXException, IOException, XMLStreamException{
		reader = new StreamReaderDelegate(reader) {
		     public int next() throws XMLStreamException {
		          int n = super.next();
		          return n;
		}};
		     
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);  
		Schema schema = sf.newSchema(xsdFileResource.getFile());  
		Validator validator = schema.newValidator();  
		validator.validate(new StAXSource(reader));
	}
	
	private void createNode(GuestBook create){
		
		try {
			/*XMLEvent end = xmlEventFactory.createDTD("\n ");
			XMLEvent tab = xmlEventFactory.createDTD("\t ");*/
			
			//System.out.println("EventWriter: " + end.getEventType());
			
			//eventWriter.add(end);
			
			XMLEvent event = xmlEventFactory.createStartElement(GuestBookXMLConstants.GUEST_BOOK_MAIN_PREFIX, "", GuestBookXMLConstants.GUEST_BOOK_ENTRY_SUFFIX);
			//eventWriter.add(tab);
			eventWriter.add(event);
			//eventWriter.add(end);
			
			createGuestBookElementTag(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX,GuestBookXMLConstants.BOOK_ID_SUFFIX,""+create.getGuestBookId());
			createGuestBookElementTag(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX,GuestBookXMLConstants.AUTHOR_TITLE_SUFFIX,create.getTitle());
			createGuestBookElementTag(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX,GuestBookXMLConstants.AUTHOR_NAME_SUFFIX,create.getName());
			createGuestBookElementTag(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX,GuestBookXMLConstants.BOOK_DATA_SUFFIX,create.getText());
			
			if(create.getEmail()!=null && !create.getEmail().trim().isEmpty()){
				createGuestBookElementTag(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX,GuestBookXMLConstants.GUEST_EMAIL_SUFFIX,create.getEmail());
			}
			createGuestBookElementTag(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX,GuestBookXMLConstants.GUEST_BOOK_DATE_OF_CREATION_SUFFIX,GuestBookHelper.formatDateStr(create.getCreateDate()));
			createGuestBookElementTag(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX,GuestBookXMLConstants.GUEST_BOOK_LAST_MODIFICATION_DATE_SUFFIX,GuestBookHelper.formatDateStr(create.getLastModifiedDate()));
			
			if(create.getKeywordList()!=null && create.getKeywordList().size()>0){
				createGuestBookElementKeywordsTag(create.getKeywordList());
			}
			
			event = xmlEventFactory.createEndElement(GuestBookXMLConstants.GUEST_BOOK_MAIN_PREFIX, "", GuestBookXMLConstants.GUEST_BOOK_ENTRY_SUFFIX);
			//eventWriter.add(tab);
			eventWriter.add(event);
			//eventWriter.add(end);
			

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void createGuestBookElementTag(String prefix,String suffix,String value) throws XMLStreamException {
		/*XMLEvent end = xmlEventFactory.createDTD("\n ");
		XMLEvent tab = xmlEventFactory.createDTD("\t ");*/
		XMLEvent event = xmlEventFactory.createStartElement(prefix, "", suffix);
		//eventWriter.add(tab);
		//eventWriter.add(tab);
		eventWriter.add(event);
		event = xmlEventFactory.createCharacters(value);
		eventWriter.add(event);
		event = xmlEventFactory.createEndElement(prefix, "", suffix);
		eventWriter.add(event);
		//eventWriter.add(end);
	}
	
	private void createGuestBookElementKeywordsTag(List<String> keywordList)
			throws XMLStreamException {
		/*XMLEvent end = xmlEventFactory.createDTD("\n ");
		XMLEvent tab = xmlEventFactory.createDTD("\t ");*/
		XMLEvent keywords = xmlEventFactory.createStartElement(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX, "",	GuestBookXMLConstants.GUEST_BOOK_KEYWORDS_SUFFIX);
		//eventWriter.add(tab);
		//eventWriter.add(tab);
		eventWriter.add(keywords);
		//eventWriter.add(end);
		for (String keyword : keywordList) {

			if (keyword != null && !keyword.trim().isEmpty()) {
				//eventWriter.add(tab);
				createGuestBookElementTag(GuestBookXMLConstants.GUEST_BOOK_KEYWORD_PREFIX,GuestBookXMLConstants.GUEST_BOOK_KEYWORD_SUFFIX,keyword);
			}
		}

		keywords = xmlEventFactory.createEndElement(GuestBookXMLConstants.GUEST_BOOK_ELEMENT_PREFIX, "",GuestBookXMLConstants.GUEST_BOOK_KEYWORDS_SUFFIX);
		//eventWriter.add(tab);
		//eventWriter.add(tab);
		eventWriter.add(keywords);
		//eventWriter.add(end);
	}
	
	public int removeGuestBookNode(GuestBook delete){
		removeAllWhitespace();
		int deleted = 1;
		try {
			createWriter();
			createReader();
			List<XMLEvent> eventList = new ArrayList<XMLEvent>();
			
			int idFound = 0 ;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
							
				if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
					StartElement startElement = event.asStartElement();
					//System.out.print(startElement.getName().getLocalPart());
					
					if (startElement.getName().getLocalPart().equalsIgnoreCase(GuestBookXMLConstants.GUEST_BOOK_ENTRY_SUFFIX)) {
						List<XMLEvent> tempeventList = new ArrayList<XMLEvent>();
	
						tempeventList.add(event);
						XMLEvent idevent = eventReader.nextEvent();
						tempeventList.add(idevent);
						
						if(idevent.getEventType() == XMLStreamConstants.START_ELEMENT){
							StartElement idstartElement = idevent.asStartElement();
							
							if (idstartElement.getName().getLocalPart().equalsIgnoreCase(GuestBookXMLConstants.BOOK_ID_SUFFIX)) {
								
								XMLEvent idValueEvent = eventReader.nextEvent();
								tempeventList.add(idValueEvent);
								if(idValueEvent.getEventType() == XMLStreamConstants.CHARACTERS){
									String idStr = idValueEvent.asCharacters().getData();
									//System.out.println("Ids:"+idStr);
									int id = Integer.parseInt(idStr);
									
									if(idFound ==0 && id == delete.getGuestBookId()){
										idFound++;
									}
								}
							}
						}
						if(idFound!=1){
							eventList.addAll(tempeventList);
						}
					}
					else if(idFound!=1){
						eventList.add(event);
					}
				}
				else if(event.getEventType() == XMLStreamConstants.END_ELEMENT) {
					EndElement endElement = event.asEndElement();
					
					if (endElement.getName().getLocalPart().equalsIgnoreCase(GuestBookXMLConstants.GUEST_BOOK_ENTRY_SUFFIX)) {
						
						if(idFound ==1){
							idFound++;
						}
						else{
							eventList.add(event);
						}
					}
					else if(idFound!=1){
						eventList.add(event);
					}
				}
				else if(idFound!=1){
					eventList.add(event);
				}
			}

			if(idFound == 0){
				// Guest Book Id Not found case
				return 0;
			}
			for(XMLEvent event: eventList){
				eventWriter.add(event);
			}
			
			eventWriter.flush();
			eventWriter.close();
			copyfile(tempfileResource, fileResource.getFile());
			tempfileResource.delete();
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
			return 0;
		}
		return deleted;
	}
	
	private void copyfile(File src,File dest) {
		try {
			File f1 = src;
			File f2 = dest;
			InputStream in = new FileInputStream(f1);
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
	}
	
	private void removeAllWhitespace(){
		createReader();
		createWriter();
	    try {

	    	while (eventReader.hasNext()) {
				boolean ignoreevent = false;
				XMLEvent event = eventReader.nextEvent();;

				if (event.isCharacters()) {
					Characters c = event.asCharacters();
					//System.out.print("Characters");
					if (c.isIgnorableWhiteSpace() || c.isWhiteSpace()) {
						ignoreevent = true;
					} 
				}
			
				if(!ignoreevent){
					eventWriter.add(event);
				}
			}
		      
			eventWriter.flush();
			eventWriter.close();
			copyfile(tempfileResource, fileResource.getFile());
			tempfileResource.delete();
		} catch (XMLStreamException | IOException e) {
			System.err.println("Error while removing whitespaces");
		}

	}
		 
}
