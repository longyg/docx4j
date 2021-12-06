
package org.docx4j.com.microsoft.schemas.office.drawing.x2012.chart;

import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import org.docx4j.dml.chart.CTSurfaceSer;
import org.jvnet.jaxb2_commons.ppp.Child;


/**
 * <p>Java class for CT_FilteredSurfaceSer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_FilteredSurfaceSer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ser" type="{http://schemas.openxmlformats.org/drawingml/2006/chart}CT_SurfaceSer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CT_FilteredSurfaceSer", propOrder = {
    "ser"
})
public class CTFilteredSurfaceSer implements Child
{

    @XmlElement(required = true)
    protected CTSurfaceSer ser;
    @XmlTransient
    private Object parent;

    /**
     * Gets the value of the ser property.
     * 
     * @return
     *     possible object is
     *     {@link CTSurfaceSer }
     *     
     */
    public CTSurfaceSer getSer() {
        return ser;
    }

    /**
     * Sets the value of the ser property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTSurfaceSer }
     *     
     */
    public void setSer(CTSurfaceSer value) {
        this.ser = value;
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
