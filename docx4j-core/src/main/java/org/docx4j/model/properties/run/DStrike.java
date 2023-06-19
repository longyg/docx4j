package org.docx4j.model.properties.run;

import org.docx4j.dml.CTTextCharacterProperties;
import org.docx4j.dml.STTextStrikeType;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.RPr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

/**
 * @author longyg
 * @Fixed by longyg @2023.6.19:
 * <p>
 * support &lt;w:dstrike>
 */
public class DStrike extends AbstractRunProperty {
    protected static Logger log = LoggerFactory.getLogger(Strike.class);

    public final static String CSS_NAME = "text-decoration";
    public final static String FO_NAME  = "text-decoration";

    public String getCssName() {
        return CSS_NAME;
    }

    public DStrike(BooleanDefaultTrue val) {
        this.setObject(val);
    }

    public DStrike(CSSValue value) {
        BooleanDefaultTrue bdt = Context.getWmlObjectFactory().createBooleanDefaultTrue();
        if (!isDoubleLineThrough(value)) {
            bdt.setVal(Boolean.FALSE);
        }
        this.setObject( bdt  );
    }

    private boolean isDoubleLineThrough(CSSValue value) {
        String cssText = value.getCssText().toLowerCase();
        return cssText.contains("line-through") && cssText.contains("double");
    }

    @Override
    public String getCssProperty() {

        if (((BooleanDefaultTrue)this.getObject()).isVal() ) {
            return composeCss(CSS_NAME, "line-through double");
        } else {
            return composeCss(CSS_NAME, "none");
        }
    }


    @Override
    public void setXslFO(Element foElement) {
        if (((BooleanDefaultTrue)this.getObject()).isVal() ) {
            foElement.setAttribute(FO_NAME, "line-through double" );
        } else {
            foElement.setAttribute(FO_NAME, "none" );
        }

    }

    @Override
    public void set(RPr rPr) {
        rPr.setDstrike( (BooleanDefaultTrue)this.getObject() );
    }

    @Override
    public void set(CTTextCharacterProperties rPr) {
        if(((BooleanDefaultTrue)this.getObject()).isVal()) {
            rPr.setStrike(STTextStrikeType.DBL_STRIKE);
        } else {
            rPr.setStrike(STTextStrikeType.NO_STRIKE);
        }
    }
}
