
package de.tum.in.dss.guestbook.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import de.tum.in.dss.guestbooktype.Guestbooktype;
import de.tum.in.dss.keyword.Keywords;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.tum.in.dss.guestbook.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Keywords1_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "keywords1");
    private final static QName _Keywords4_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "keywords4");
    private final static QName _Keywords2_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "keywords2");
    private final static QName _GuestBooktype_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "guestBooktype");
    private final static QName _Keywords3_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "keywords3");
    private final static QName _Resultcount1_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "resultcount1");
    private final static QName _GuestBooktype2_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "guestBooktype2");
    private final static QName _Resultcount4_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "resultcount4");
    private final static QName _GuestBooktype1_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "guestBooktype1");
    private final static QName _Resultcount3_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "resultcount3");
    private final static QName _Resultcount2_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "resultcount2");
    private final static QName _GuestBookId_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "guestBookId");
    private final static QName _GuestBooktype3_QNAME = new QName("http://dss.in.tum.de/guestbook/wsdl", "guestBooktype3");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.tum.in.dss.guestbook.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Keywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "keywords1")
    public JAXBElement<Keywords> createKeywords1(Keywords value) {
        return new JAXBElement<Keywords>(_Keywords1_QNAME, Keywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Keywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "keywords4")
    public JAXBElement<Keywords> createKeywords4(Keywords value) {
        return new JAXBElement<Keywords>(_Keywords4_QNAME, Keywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Keywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "keywords2")
    public JAXBElement<Keywords> createKeywords2(Keywords value) {
        return new JAXBElement<Keywords>(_Keywords2_QNAME, Keywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Guestbooktype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "guestBooktype")
    public JAXBElement<Guestbooktype> createGuestBooktype(Guestbooktype value) {
        return new JAXBElement<Guestbooktype>(_GuestBooktype_QNAME, Guestbooktype.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Keywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "keywords3")
    public JAXBElement<Keywords> createKeywords3(Keywords value) {
        return new JAXBElement<Keywords>(_Keywords3_QNAME, Keywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "resultcount1")
    public JAXBElement<Integer> createResultcount1(Integer value) {
        return new JAXBElement<Integer>(_Resultcount1_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Guestbooktype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "guestBooktype2")
    public JAXBElement<Guestbooktype> createGuestBooktype2(Guestbooktype value) {
        return new JAXBElement<Guestbooktype>(_GuestBooktype2_QNAME, Guestbooktype.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "resultcount4")
    public JAXBElement<Integer> createResultcount4(Integer value) {
        return new JAXBElement<Integer>(_Resultcount4_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Guestbooktype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "guestBooktype1")
    public JAXBElement<Guestbooktype> createGuestBooktype1(Guestbooktype value) {
        return new JAXBElement<Guestbooktype>(_GuestBooktype1_QNAME, Guestbooktype.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "resultcount3")
    public JAXBElement<Integer> createResultcount3(Integer value) {
        return new JAXBElement<Integer>(_Resultcount3_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "resultcount2")
    public JAXBElement<Integer> createResultcount2(Integer value) {
        return new JAXBElement<Integer>(_Resultcount2_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "guestBookId")
    public JAXBElement<Integer> createGuestBookId(Integer value) {
        return new JAXBElement<Integer>(_GuestBookId_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Guestbooktype }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dss.in.tum.de/guestbook/wsdl", name = "guestBooktype3")
    public JAXBElement<Guestbooktype> createGuestBooktype3(Guestbooktype value) {
        return new JAXBElement<Guestbooktype>(_GuestBooktype3_QNAME, Guestbooktype.class, null, value);
    }

}
