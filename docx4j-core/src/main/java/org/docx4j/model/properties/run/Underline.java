/*
 *  Copyright 2009, Plutext Pty Ltd.
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
package org.docx4j.model.properties.run;

import org.docx4j.dml.CTTextCharacterProperties;
import org.docx4j.dml.STTextUnderlineType;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.RPr;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

import java.util.HashMap;
import java.util.Map;

public class Underline extends AbstractRunProperty {

	protected static Logger log = LoggerFactory.getLogger(Underline.class);

	public final static String CSS_NAME = "text-decoration";
	public final static String FO_NAME  = "text-decoration";

	/**
	 * @since 2.7.2
	 */
	public String getCssName() {
		return CSS_NAME;
	}

	public Underline(U u) {
		this.setObject(u);
	}

	public Underline(CSSValue value) {

		//debug(CSS_NAME, value);

		U u = Context.getWmlObjectFactory().createU();

		// @Fixed by longyg @2023.4.18
		// handle text-decoration with color and style
		String cssText = value.getCssText().toLowerCase();
		if (equals(cssText, "none")) {
            u.setVal(UnderlineEnumeration.NONE);
        } else if (cssText.contains("underline")) {
            UnderlineEnumeration val = UnderlineEnumeration.SINGLE;
			for (Map.Entry<UnderlineEnumeration, HtmlUnderlineStyleEnum> entry : getStylesMap().entrySet()) {
				if (cssText.contains(entry.getValue().value())) {
					val = entry.getKey();
					cssText = cssText.replace(entry.getValue().value(), "");
					break;
				}
			}
			u.setVal(val);

			// only support hex color
			int colorStartIndex = cssText.indexOf("#");
			if (colorStartIndex > -1) {
				String colorVal = cssText.substring(colorStartIndex + 1, colorStartIndex + 7);
				u.setColor(colorVal);
			}
		}

//		if (value.getCssText().toLowerCase().equals("underline")
//				|| value.getCssText().toLowerCase().equals("[underline]")) {
//			u.setVal(UnderlineEnumeration.SINGLE);
////		} else if (value.getCssText().toLowerCase().equals("underline")) {
////			u.setVal(UnderlineEnumeration.NONE);
//		} else
//			log.error("How to handle " + CSS_NAME + " " + value.getCssText().toLowerCase());
//		}

		this.setObject( u );

	}

	private boolean equals(String cssText, String text) {
		return cssText.equals(text) || cssText.equals("[" + text + "]");
	}

	@Override
	public String getCssProperty() {

		if (((U)this.getObject()).getVal()==null ) {
			// This does happen
			return composeCss(CSS_NAME, "underline");
		} else if (!((U)this.getObject()).getVal().equals( UnderlineEnumeration.NONE ) ) {
			return composeCss(CSS_NAME, "underline");
		} else {
			return CSS_NULL;  // text-decoration is also used for Strike (a la line-through)
		}
		// How to handle <w:u w:color="FF0000"> ie coloured underline?

	}


	@Override
	public void setXslFO(Element foElement) {

		if (((U)this.getObject()).getVal()==null ) {
			// This does happen
			foElement.setAttribute(FO_NAME, "underline");
		} else if (!((U)this.getObject()).getVal().equals( UnderlineEnumeration.NONE ) ) {
			foElement.setAttribute(FO_NAME, "underline");
		} else {
			//
		}

	}

	@Override
	public void set(RPr rPr) {
		rPr.setU((U)this.getObject());
	}

    @Override
    public void set(CTTextCharacterProperties rPr) {
        if (((U)this.getObject()).getVal()==null ) {
            rPr.setU(STTextUnderlineType.SNG);
        } else if (!((U)this.getObject()).getVal().equals( UnderlineEnumeration.NONE ) ) {
            rPr.setU(STTextUnderlineType.SNG);
        } else {
            //
        }
    }

	// @Fixed by longyg @2023.4.18
	// handle text-decoration with color and style
	public static final Map<UnderlineEnumeration, HtmlUnderlineStyleEnum> STYLES_MAP = new HashMap<>();
	static {
		STYLES_MAP.put(UnderlineEnumeration.DOTTED, HtmlUnderlineStyleEnum.DOTTED);
		STYLES_MAP.put(UnderlineEnumeration.DOUBLE, HtmlUnderlineStyleEnum.DOUBLE);
		STYLES_MAP.put(UnderlineEnumeration.DASH, HtmlUnderlineStyleEnum.DASHED);
		STYLES_MAP.put(UnderlineEnumeration.WAVE, HtmlUnderlineStyleEnum.WAVY);
	}

	public Map<UnderlineEnumeration, HtmlUnderlineStyleEnum> getStylesMap() {
		return STYLES_MAP;
	}

	public enum HtmlUnderlineStyleEnum {
		DASHED("dashed"),
		WAVY("wavy"),
		DOTTED("dotted"),
		DOUBLE("double"),
		SOLID("solid"),
		NONE("none");

		private final String value;

		HtmlUnderlineStyleEnum(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}

		public static HtmlUnderlineStyleEnum fromValue(String v) {
			for (HtmlUnderlineStyleEnum c: HtmlUnderlineStyleEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
	}

}
