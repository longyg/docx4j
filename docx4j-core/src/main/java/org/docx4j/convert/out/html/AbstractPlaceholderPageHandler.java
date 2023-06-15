package org.docx4j.convert.out.html;

import org.docx4j.convert.out.common.AbstractWmlConversionContext;
import org.docx4j.convert.out.common.writer.AbstractFldSimpleWriter.FldSimpleNodeWriterHandler;
import org.docx4j.model.fields.FldSimpleModel;
import org.docx4j.model.fields.FormattingSwitchHelper;
import org.docx4j.utils.FoNumberFormatUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.TransformerException;

/**
 * @author longyg
 * @Fixed by longyg @2023.6.14:
 * <p>
 * Customization for PAGE and NUMPAGES fldSimple handling.
 * We create a span with special attribute, it is helpful during export from HTML to word,
 * we can identify this special span and create fldSimple.
 */
public abstract class AbstractPlaceholderPageHandler implements FldSimpleNodeWriterHandler {
    public static final String WORD_PAGE_TAG = "data-word-page-tag";

    public abstract String getWordPageTag();

    @Override
    public String getName() {
        return "PAGE";
    }

    @Override
    public int getProcessType() {
        return PROCESS_APPLY_STYLE;
    }

    @Override
    public Node toNode(AbstractWmlConversionContext context, FldSimpleModel model, Document doc) throws TransformerException {
        String pageFormat = context.getSections().getCurrentSection().getPageNumberInformation().getPageFormat();
        pageFormat = FormattingSwitchHelper.getFoPageNumberFormat(pageFormat);
        String pageNum = FoNumberFormatUtil.format(1, pageFormat);
        return createNode(doc, pageNum);
    }

    protected Node createNode(Document doc, String text) {
        Element element = null;
        if (text != null) {
            element = doc.createElement("span");
            if (text.length() > 0) {
                element.setTextContent(text);
            }
            element.setAttribute(WORD_PAGE_TAG, getWordPageTag());
        }
        return element;
    }
}
