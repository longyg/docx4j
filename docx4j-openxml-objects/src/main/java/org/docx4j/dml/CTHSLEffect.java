/*
 *  Copyright 2007-2008, Plutext Pty Ltd.
 *   
 *  This file is part of docx4j.

    docx4j is licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 

    You may obtain a copy of the License at 

        http://www.apache.org/licenses/LICENSE-2.0 

    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS, 
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and 
    limitations under the License.

 */


package org.docx4j.dml;

import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.ppp.Child;


/**
 * <p>Java class for CT_HSLEffect complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_HSLEffect"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="hue" type="{http://schemas.openxmlformats.org/drawingml/2006/main}ST_PositiveFixedAngle" default="0" /&gt;
 *       &lt;attribute name="sat" type="{http://schemas.openxmlformats.org/drawingml/2006/main}ST_FixedPercentage" default="0" /&gt;
 *       &lt;attribute name="lum" type="{http://schemas.openxmlformats.org/drawingml/2006/main}ST_FixedPercentage" default="0" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CT_HSLEffect")
public class CTHSLEffect implements Child
{

    @XmlAttribute(name = "hue")
    protected Integer hue;
    @XmlAttribute(name = "sat")
    protected Integer sat;
    @XmlAttribute(name = "lum")
    protected Integer lum;
    @XmlTransient
    private Object parent;

    /**
     * Gets the value of the hue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getHue() {
        if (hue == null) {
            return  0;
        } else {
            return hue;
        }
    }

    /**
     * Sets the value of the hue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHue(Integer value) {
        this.hue = value;
    }

    /**
     * Gets the value of the sat property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getSat() {
        if (sat == null) {
            return  0;
        } else {
            return sat;
        }
    }

    /**
     * Sets the value of the sat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSat(Integer value) {
        this.sat = value;
    }

    /**
     * Gets the value of the lum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getLum() {
        if (lum == null) {
            return  0;
        } else {
            return lum;
        }
    }

    /**
     * Sets the value of the lum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLum(Integer value) {
        this.lum = value;
    }

    /**
     * Gets the parent object in the object tree representing the unmarshalled xml document.
     * 
     * @return
     *     The parent object.
     */
    public Object getParent() {
        return this.parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    /**
     * This method is invoked by the JAXB implementation on each instance when unmarshalling completes.
     * 
     * @param parent
     *     The parent object in the object tree.
     * @param unmarshaller
     *     The unmarshaller that generated the instance.
     */
    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        setParent(parent);
    }

}
