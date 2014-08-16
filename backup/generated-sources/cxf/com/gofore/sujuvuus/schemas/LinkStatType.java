
package com.gofore.sujuvuus.schemas;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LinkStatType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LinkStatType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="linkno" type="{http://www.gofore.com/sujuvuus/schemas}LinkNumberType"/>
 *         &lt;element name="measurementtime" type="{http://www.gofore.com/sujuvuus/schemas}ObstimeType"/>
 *         &lt;element name="journeytimenow" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="midspeednow">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *               &lt;minInclusive value="0.0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fluencyclassnow" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="nobs" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinkStatType", propOrder = {
    "linkno",
    "measurementtime",
    "journeytimenow",
    "midspeednow",
    "fluencyclassnow",
    "nobs"
})
public class LinkStatType {

    @XmlElement(required = true)
    protected BigInteger linkno;
    @XmlElement(required = true)
    protected ObstimeType measurementtime;
    @XmlElement(required = true)
    protected String journeytimenow;
    protected float midspeednow;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger fluencyclassnow;
    @XmlElement(required = true)
    protected BigInteger nobs;

    /**
     * Gets the value of the linkno property.
     * 
     * @return
     *     possible object is
     *     {@link java.math.BigInteger }
     *     
     */
    public BigInteger getLinkno() {
        return linkno;
    }

    /**
     * Sets the value of the linkno property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.math.BigInteger }
     *     
     */
    public void setLinkno(BigInteger value) {
        this.linkno = value;
    }

    /**
     * Gets the value of the measurementtime property.
     * 
     * @return
     *     possible object is
     *     {@link com.gofore.sujuvuus.schemas.ObstimeType }
     *     
     */
    public ObstimeType getMeasurementtime() {
        return measurementtime;
    }

    /**
     * Sets the value of the measurementtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.gofore.sujuvuus.schemas.ObstimeType }
     *     
     */
    public void setMeasurementtime(ObstimeType value) {
        this.measurementtime = value;
    }

    /**
     * Gets the value of the journeytimenow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJourneytimenow() {
        return journeytimenow;
    }

    /**
     * Sets the value of the journeytimenow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJourneytimenow(String value) {
        this.journeytimenow = value;
    }

    /**
     * Gets the value of the midspeednow property.
     * 
     */
    public float getMidspeednow() {
        return midspeednow;
    }

    /**
     * Sets the value of the midspeednow property.
     * 
     */
    public void setMidspeednow(float value) {
        this.midspeednow = value;
    }

    /**
     * Gets the value of the fluencyclassnow property.
     * 
     * @return
     *     possible object is
     *     {@link java.math.BigInteger }
     *     
     */
    public BigInteger getFluencyclassnow() {
        return fluencyclassnow;
    }

    /**
     * Sets the value of the fluencyclassnow property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.math.BigInteger }
     *     
     */
    public void setFluencyclassnow(BigInteger value) {
        this.fluencyclassnow = value;
    }

    /**
     * Gets the value of the nobs property.
     * 
     * @return
     *     possible object is
     *     {@link java.math.BigInteger }
     *     
     */
    public BigInteger getNobs() {
        return nobs;
    }

    /**
     * Sets the value of the nobs property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.math.BigInteger }
     *     
     */
    public void setNobs(BigInteger value) {
        this.nobs = value;
    }

}
