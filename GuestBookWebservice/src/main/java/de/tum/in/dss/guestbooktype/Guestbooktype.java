
package de.tum.in.dss.guestbooktype;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.tum.in.dss.keyword.Keywords;
import org.apache.cxf.jaxb.JAXBToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;
import org.w3._2001.xmlschema.Adapter2;


/**
 * <p>Java class for guestbooktype complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="guestbooktype">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BOOK_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AUTHOR_TITLE">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AUTHOR_NAME">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="10"/>
 *               &lt;maxLength value="30"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BOOK_DATA">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="3"/>
 *               &lt;maxLength value="500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="GUEST_EMAIL_ID" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[_A-Za-z0-9\-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9\-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DATE_OF_CREATION" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="LAST_MODIFICATION_DATE" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="keywords" type="{http://dss.in.tum.de/keyword}keywords" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "guestbooktype", propOrder = {
    "bookid",
    "authortitle",
    "authorname",
    "bookdata",
    "guestemailid",
    "dateofcreation",
    "lastmodificationdate",
    "keywords"
})
public class Guestbooktype {

    @XmlElement(name = "BOOK_ID")
    protected int bookid;
    @XmlElement(name = "AUTHOR_TITLE", required = true)
    protected String authortitle;
    @XmlElement(name = "AUTHOR_NAME", required = true)
    protected String authorname;
    @XmlElement(name = "BOOK_DATA", required = true)
    protected String bookdata;
    @XmlElement(name = "GUEST_EMAIL_ID")
    protected String guestemailid;
    @XmlElement(name = "DATE_OF_CREATION", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Date dateofcreation;
    @XmlElement(name = "LAST_MODIFICATION_DATE", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Date lastmodificationdate;
    protected Keywords keywords;

    /**
     * Gets the value of the bookid property.
     * 
     */
    public int getBOOKID() {
        return bookid;
    }

    /**
     * Sets the value of the bookid property.
     * 
     */
    public void setBOOKID(int value) {
        this.bookid = value;
    }

    /**
     * Gets the value of the authortitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUTHORTITLE() {
        return authortitle;
    }

    /**
     * Sets the value of the authortitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUTHORTITLE(String value) {
        this.authortitle = value;
    }

    /**
     * Gets the value of the authorname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUTHORNAME() {
        return authorname;
    }

    /**
     * Sets the value of the authorname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUTHORNAME(String value) {
        this.authorname = value;
    }

    /**
     * Gets the value of the bookdata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBOOKDATA() {
        return bookdata;
    }

    /**
     * Sets the value of the bookdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBOOKDATA(String value) {
        this.bookdata = value;
    }

    /**
     * Gets the value of the guestemailid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGUESTEMAILID() {
        return guestemailid;
    }

    /**
     * Sets the value of the guestemailid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGUESTEMAILID(String value) {
        this.guestemailid = value;
    }

    /**
     * Gets the value of the dateofcreation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDATEOFCREATION() {
        return dateofcreation;
    }

    /**
     * Sets the value of the dateofcreation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATEOFCREATION(Date value) {
        this.dateofcreation = value;
    }

    /**
     * Gets the value of the lastmodificationdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getLASTMODIFICATIONDATE() {
        return lastmodificationdate;
    }

    /**
     * Sets the value of the lastmodificationdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLASTMODIFICATIONDATE(Date value) {
        this.lastmodificationdate = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * @return
     *     possible object is
     *     {@link Keywords }
     *     
     */
    public Keywords getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of the keywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link Keywords }
     *     
     */
    public void setKeywords(Keywords value) {
        this.keywords = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    public String toString() {
        return JAXBToStringBuilder.valueOf(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }

}
