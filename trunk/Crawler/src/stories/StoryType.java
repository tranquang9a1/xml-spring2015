//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.31 at 03:33:01 PM ICT 
//


package stories;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StoryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StoryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newest_chap" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="update_date" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="chapters" type="{http://www.fpt.edu.vn}chapter"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StoryType", propOrder = {
    "name",
    "author",
    "status",
    "source",
    "type",
    "image",
    "description",
    "newestChap",
    "updateDate",
    "chapters"
})
public class StoryType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String author;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String source;
    @XmlElement(required = true)
    protected List<String> type;
    @XmlElement(required = true)
    protected String image;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "newest_chap", required = true)
    protected String newestChap;
    @XmlElement(name = "update_date")
    protected long updateDate;
    @XmlElement(required = true)
    protected Chapter chapters;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the type property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getType().add(newItem);
     * </pre>
     *
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     * 
     */
    public void setType(List<String> type) {
        this.type = type;
    }

    
    
    
    public List<String> getType() {
        if (type == null) {
            type = new ArrayList<String>();
        }
        return this.type;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the newestChap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewestChap() {
        return newestChap;
    }

    /**
     * Sets the value of the newestChap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewestChap(String value) {
        this.newestChap = value;
    }

    /**
     * Gets the value of the updateDate property.
     * 
     */
    public long getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the value of the updateDate property.
     * 
     */
    public void setUpdateDate(long value) {
        this.updateDate = value;
    }

    /**
     * Gets the value of the chapters property.
     * 
     * @return
     *     possible object is
     *     {@link Chapter }
     *     
     */
    public Chapter getChapters() {
        return chapters;
    }

    /**
     * Sets the value of the chapters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Chapter }
     *     
     */
    public void setChapters(Chapter value) {
        this.chapters = value;
    }

}
