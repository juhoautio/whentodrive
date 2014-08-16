
package com.gofore.sujuvuus.schemas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="timestamp" type="{http://www.gofore.com/sujuvuus/schemas}ObstimeType"/>
 *         &lt;element name="laststaticdataupdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="linkdynamicdata">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="linkstat" type="{http://www.gofore.com/sujuvuus/schemas}LinkStatType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "timestamp",
    "laststaticdataupdate",
    "linkdynamicdata"
})
@XmlRootElement(name = "TrafficFluencyResponse")
public class TrafficFluencyResponse {

    @XmlElement(required = true)
    protected ObstimeType timestamp;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar laststaticdataupdate;
    @XmlElement(required = true)
    protected Linkdynamicdata linkdynamicdata;

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link com.gofore.sujuvuus.schemas.ObstimeType }
     *     
     */
    public ObstimeType getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.gofore.sujuvuus.schemas.ObstimeType }
     *     
     */
    public void setTimestamp(ObstimeType value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the laststaticdataupdate property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.datatype.XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLaststaticdataupdate() {
        return laststaticdataupdate;
    }

    /**
     * Sets the value of the laststaticdataupdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.datatype.XMLGregorianCalendar }
     *     
     */
    public void setLaststaticdataupdate(XMLGregorianCalendar value) {
        this.laststaticdataupdate = value;
    }

    /**
     * Gets the value of the linkdynamicdata property.
     * 
     * @return
     *     possible object is
     *     {@link com.gofore.sujuvuus.schemas.TrafficFluencyResponse.Linkdynamicdata }
     *     
     */
    public Linkdynamicdata getLinkdynamicdata() {
        return linkdynamicdata;
    }

    /**
     * Sets the value of the linkdynamicdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.gofore.sujuvuus.schemas.TrafficFluencyResponse.Linkdynamicdata }
     *     
     */
    public void setLinkdynamicdata(Linkdynamicdata value) {
        this.linkdynamicdata = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="linkstat" type="{http://www.gofore.com/sujuvuus/schemas}LinkStatType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "linkstat"
    })
    public static class Linkdynamicdata {

        @XmlElement(required = true)
        protected List<LinkStatType> linkstat;

        /**
         * Gets the value of the linkstat property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the linkstat property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLinkstat().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link com.gofore.sujuvuus.schemas.LinkStatType }
         * 
         * 
         */
        public List<LinkStatType> getLinkstat() {
            if (linkstat == null) {
                linkstat = new ArrayList<LinkStatType>();
            }
            return this.linkstat;
        }

    }

}
