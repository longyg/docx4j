
package org.docx4j.com.microsoft.schemas.office.drawing.x2014.main;

import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jvnet.jaxb2_commons.ppp.Child;


/**
 * <p>Java class for CT_PredecessorDrawingElementReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_PredecessorDrawingElementReference"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="pred" type="{http://schemas.openxmlformats.org/drawingml/2006/main}ST_Guid" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CT_PredecessorDrawingElementReference")
public class CTPredecessorDrawingElementReference implements Child
{

    @XmlAttribute(name = "pred")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String pred;
    @XmlTransient
    private Object parent;

    /**
     * Gets the value of the pred property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPred() {
        return pred;
    }

    /**
     * Sets the value of the pred property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPred(String value) {
        this.pred = value;
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
