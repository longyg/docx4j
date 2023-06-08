package org.docx4j.convert.out.common.preprocess;

import jakarta.xml.bind.JAXBElement;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.utils.CommonUtil;
import org.docx4j.wml.P;
import org.docx4j.wml.SdtBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longyg
 * @Fixed by longyg @2023.6.7, customization:
 * <p>
 * Process section grouping for a Word document, add sdtBlock to group a section,
 * a section contains all blocks between two headings.
 * This preprocessing step shall be executed before containerization and list collection preprocessing steps,
 * as this will restrict list and div do not across sections.
 * <p>
 */
public class SectionGrouping {
    private SectionGrouping() {
    }

    private static final String HEADING_TAG = "HEADING_ELEMENT";

    public static void process(WordprocessingMLPackage wmlPackage) {
        MainDocumentPart mainDocument = wmlPackage.getMainDocumentPart();
        groupSection(mainDocument, mainDocument.getJaxbElement().getBody().getContent());
    }

    protected static void groupSection(MainDocumentPart mainDocument, List<Object> content) {
        List<Object> groupedContent = null;
        groupedContent = groupBodyContent(mainDocument, content);
        if (groupedContent != null) {
            content.clear();
            content.addAll(groupedContent);
        }
    }

    protected static List<Object> groupBodyContent(MainDocumentPart mainDocument, List<Object> bodyElts) {
        List<Object> resultElts = new ArrayList<>();

        SdtBlock sectionBlock = null;
        for (Object o : bodyElts) {
            Object unwrapped;
            if (o instanceof JAXBElement) {
                unwrapped = ((JAXBElement) o).getValue();
            } else {
                unwrapped = o;
            }
            if (unwrapped instanceof P) {
                P p = (P) unwrapped;
                if (CommonUtil.isHeadingParagraph(mainDocument, p)) {
                    sectionBlock = CommonUtil.createSdtBlock(HEADING_TAG);
                    resultElts.add(sectionBlock);
                }
            }

            if (sectionBlock != null) {
                sectionBlock.getSdtContent().getContent().add(o);
            } else {
                resultElts.add(o);
            }
        }
        return resultElts;
    }

}
