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


package org.docx4j.dml.diagram;

import org.docx4j.dml.ArrayListDml;
import java.util.List;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import org.docx4j.dml.CTOfficeArtExtensionList;
import org.jvnet.jaxb2_commons.ppp.Child;


/**
 * <p>Java class for CT_ColorTransform complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_ColorTransform"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="title" type="{http://schemas.openxmlformats.org/drawingml/2006/diagram}CT_CTName" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="desc" type="{http://schemas.openxmlformats.org/drawingml/2006/diagram}CT_CTDescription" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="catLst" type="{http://schemas.openxmlformats.org/drawingml/2006/diagram}CT_CTCategories" minOccurs="0"/&gt;
 *         &lt;element name="styleLbl" type="{http://schemas.openxmlformats.org/drawingml/2006/diagram}CT_CTStyleLabel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="extLst" type="{http://schemas.openxmlformats.org/drawingml/2006/main}CT_OfficeArtExtensionList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="uniqueId" type="{http://www.w3.org/2001/XMLSchema}string" default="" /&gt;
 *       &lt;attribute name="minVer" type="{http://www.w3.org/2001/XMLSchema}string" default="http://schemas.openxmlformats.org/drawingml/2006/diagram" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CT_ColorTransform", propOrder = {
    "title",
    "desc",
    "catLst",
    "styleLbl",
    "extLst"
})
@XmlRootElement(name="colorsDef")
public class CTColorTransform implements Child
{

    protected List<CTCTName> title = new ArrayListDml<CTCTName>(this);
    protected List<CTCTDescription> desc = new ArrayListDml<CTCTDescription>(this);
    protected CTCTCategories catLst;
    protected List<CTCTStyleLabel> styleLbl = new ArrayListDml<CTCTStyleLabel>(this);
    protected CTOfficeArtExtensionList extLst;
    @XmlAttribute(name = "uniqueId")
    protected String uniqueId;
    @XmlAttribute(name = "minVer")
    protected String minVer;
    @XmlTransient
    private Object parent;

    /**
     * Gets the value of the title property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the title property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CTCTName }
     * 
     * 
     */
    public List<CTCTName> getTitle() {
        if (title == null) {
            title = new ArrayListDml<CTCTName>(this);
        }
        return this.title;
    }

    /**
     * Gets the value of the desc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the desc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDesc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CTCTDescription }
     * 
     * 
     */
    public List<CTCTDescription> getDesc() {
        if (desc == null) {
            desc = new ArrayListDml<CTCTDescription>(this);
        }
        return this.desc;
    }

    /**
     * Gets the value of the catLst property.
     * 
     * @return
     *     possible object is
     *     {@link CTCTCategories }
     *     
     */
    public CTCTCategories getCatLst() {
        return catLst;
    }

    /**
     * Sets the value of the catLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTCTCategories }
     *     
     */
    public void setCatLst(CTCTCategories value) {
        this.catLst = value;
    }

    /**
     * Gets the value of the styleLbl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the styleLbl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStyleLbl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CTCTStyleLabel }
     * 
     * 
     */
    public List<CTCTStyleLabel> getStyleLbl() {
        if (styleLbl == null) {
            styleLbl = new ArrayListDml<CTCTStyleLabel>(this);
        }
        return this.styleLbl;
    }

    /**
     * Gets the value of the extLst property.
     * 
     * @return
     *     possible object is
     *     {@link CTOfficeArtExtensionList }
     *     
     */
    public CTOfficeArtExtensionList getExtLst() {
        return extLst;
    }

    /**
     * Sets the value of the extLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTOfficeArtExtensionList }
     *     
     */
    public void setExtLst(CTOfficeArtExtensionList value) {
        this.extLst = value;
    }

    /**
     * Gets the value of the uniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqueId() {
        if (uniqueId == null) {
            return "";
        } else {
            return uniqueId;
        }
    }

    /**
     * Sets the value of the uniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqueId(String value) {
        this.uniqueId = value;
    }

    /**
     * Gets the value of the minVer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinVer() {
        if (minVer == null) {
            return "http://schemas.openxmlformats.org/drawingml/2006/diagram";
        } else {
            return minVer;
        }
    }

    /**
     * Sets the value of the minVer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinVer(String value) {
        this.minVer = value;
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
