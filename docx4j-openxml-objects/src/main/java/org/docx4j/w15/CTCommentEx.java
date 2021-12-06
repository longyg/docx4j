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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;



/**
 * <p>Java class for CT_CommentEx complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_CommentEx">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="paraId" use="required" type="{http://schemas.openxmlformats.org/wordprocessingml/2006/main}ST_LongHexNumber" />
 *       &lt;attribute name="paraIdParent" type="{http://schemas.openxmlformats.org/wordprocessingml/2006/main}ST_LongHexNumber" />
 *       &lt;attribute name="done" type="{http://schemas.openxmlformats.org/wordprocessingml/2006/main}ST_OnOff" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CT_CommentEx")
public class CTCommentEx implements Child
{

    @XmlAttribute(name = "paraId", namespace = "http://schemas.microsoft.com/office/word/2012/wordml", required = true)
    protected String paraId;
    @XmlAttribute(name = "paraIdParent", namespace = "http://schemas.microsoft.com/office/word/2012/wordml")
    protected String paraIdParent;
    @XmlAttribute(name = "done", namespace = "http://schemas.microsoft.com/office/word/2012/wordml")
    protected String done;
    @XmlTransient
    private Object parent;

    /**
     * Gets the value of the paraId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParaId() {
        return paraId;
    }

    /**
     * Sets the value of the paraId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParaId(String value) {
        this.paraId = value;
    }

    /**
     * Gets the value of the paraIdParent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParaIdParent() {
        return paraIdParent;
    }

    /**
     * Sets the value of the paraIdParent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParaIdParent(String value) {
        this.paraIdParent = value;
    }

    /**
     * Gets the value of the done property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDone() {
        return done;
    }

    /**
     * Sets the value of the done property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDone(String value) {
        this.done = value;
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
