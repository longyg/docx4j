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

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ST_ElementType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ST_ElementType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="all"/&gt;
 *     &lt;enumeration value="doc"/&gt;
 *     &lt;enumeration value="node"/&gt;
 *     &lt;enumeration value="norm"/&gt;
 *     &lt;enumeration value="nonNorm"/&gt;
 *     &lt;enumeration value="asst"/&gt;
 *     &lt;enumeration value="nonAsst"/&gt;
 *     &lt;enumeration value="parTrans"/&gt;
 *     &lt;enumeration value="pres"/&gt;
 *     &lt;enumeration value="sibTrans"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ST_ElementType")
@XmlEnum
public enum STElementType {


    /**
     * All
     * 
     */
    @XmlEnumValue("all")
    ALL("all"),

    /**
     * Document
     * 
     */
    @XmlEnumValue("doc")
    DOC("doc"),

    /**
     * Node
     * 
     */
    @XmlEnumValue("node")
    NODE("node"),

    /**
     * Normal
     * 
     */
    @XmlEnumValue("norm")
    NORM("norm"),

    /**
     * Non Normal
     * 
     */
    @XmlEnumValue("nonNorm")
    NON_NORM("nonNorm"),

    /**
     * Assistant
     * 
     */
    @XmlEnumValue("asst")
    ASST("asst"),

    /**
     * Non Assistant
     * 
     */
    @XmlEnumValue("nonAsst")
    NON_ASST("nonAsst"),

    /**
     * Parent Transition
     * 
     */
    @XmlEnumValue("parTrans")
    PAR_TRANS("parTrans"),

    /**
     * Presentation
     * 
     */
    @XmlEnumValue("pres")
    PRES("pres"),

    /**
     * Sibling Transition
     * 
     */
    @XmlEnumValue("sibTrans")
    SIB_TRANS("sibTrans");
    private final String value;

    STElementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static STElementType fromValue(String v) {
        for (STElementType c: STElementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
