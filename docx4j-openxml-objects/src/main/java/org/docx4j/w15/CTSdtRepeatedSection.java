/*
 *  Copyright 2013, Plutext Pty Ltd.
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


package org.docx4j.w15; 

import org.jvnet.jaxb2_commons.ppp.Child;

import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTString;



/**
 * <p>Java class for CT_SdtRepeatedSection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_SdtRepeatedSection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sectionTitle" type="{http://schemas.openxmlformats.org/wordprocessingml/2006/main}CT_String" minOccurs="0"/>
 *         &lt;element name="doNotAllowInsertDeleteSection" type="{http://schemas.openxmlformats.org/wordprocessingml/2006/main}BooleanDefaultTrue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CT_SdtRepeatedSection", propOrder = {
    "sectionTitle",
    "doNotAllowInsertDeleteSection"
})
public class CTSdtRepeatedSection implements Child
{

    protected CTString sectionTitle;
    protected BooleanDefaultTrue doNotAllowInsertDeleteSection;
    @XmlTransient
    private Object parent;

    /**
     * Gets the value of the sectionTitle property.
     * 
     * @return
     *     possible object is
     *     {@link CTString }
     *     
     */
    public CTString getSectionTitle() {
        return sectionTitle;
    }

    /**
     * Sets the value of the sectionTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTString }
     *     
     */
    public void setSectionTitle(CTString value) {
        this.sectionTitle = value;
    }

    /**
     * Gets the value of the doNotAllowInsertDeleteSection property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanDefaultTrue }
     *     
     */
    public BooleanDefaultTrue getDoNotAllowInsertDeleteSection() {
        return doNotAllowInsertDeleteSection;
    }

    /**
     * Sets the value of the doNotAllowInsertDeleteSection property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanDefaultTrue }
     *     
     */
    public void setDoNotAllowInsertDeleteSection(BooleanDefaultTrue value) {
        this.doNotAllowInsertDeleteSection = value;
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
