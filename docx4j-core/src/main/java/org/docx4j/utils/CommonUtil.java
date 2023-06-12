package org.docx4j.utils;

import org.docx4j.jaxb.Context;
import org.docx4j.model.styles.Node;
import org.docx4j.model.styles.StyleTree;
import org.docx4j.model.styles.Tree;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longyg
 */
public class CommonUtil {

    private CommonUtil() {
    }

    private static final List<String> HEADING_NAMES = new ArrayList<>();

    static {
        for (int i = 1; i <= 6; i++) {
            HEADING_NAMES.add("heading " + i);
        }
    }

    public static boolean isHeadingParagraph(MainDocumentPart mainDocument, P p) {
        if (null == p || null == p.getPPr()) return false;
        PPrBase.PStyle pStyle = p.getPPr().getPStyle();
        if (null == pStyle || pStyle.getVal() == null) return false;
        return isHeadingStyle(mainDocument, pStyle.getVal());
    }

    public static boolean isHeadingStyle(MainDocumentPart mainDocument, String styleId) {
        StyleTree styleTree = mainDocument.getStyleTree();
        if (null == styleTree) return false;
        Tree<StyleTree.AugmentedStyle> paragraphStylesTree = styleTree.getParagraphStylesTree();
        if (paragraphStylesTree == null) return false;
        Node<StyleTree.AugmentedStyle> styleNode = paragraphStylesTree.get(styleId);
        return isHeadingStyleNode(styleNode);
    }

    public static boolean isHeadingStyleNode(Node<StyleTree.AugmentedStyle> styleNode) {
        if (null == styleNode) return false;
        StyleTree.AugmentedStyle augmentedStyle = styleNode.getData();
        Node<StyleTree.AugmentedStyle> parentStyleNode = styleNode.getParent();
        if (null == augmentedStyle) {
            return isHeadingStyleNode(parentStyleNode);
        }
        Style style = augmentedStyle.getStyle();
        if (null == style) {
            return isHeadingStyleNode(parentStyleNode);
        }
        if (HEADING_NAMES.contains(style.getName().getVal())) {
            return true;
        }
        return isHeadingStyleNode(parentStyleNode);
    }

    public static SdtBlock createSdtBlock(String tagValue) {
        ObjectFactory objectFactory = Context.getWmlObjectFactory();
        SdtBlock sdtBlock = objectFactory.createSdtBlock();
        SdtPr sdtPr = objectFactory.createSdtPr();
        sdtBlock.setSdtPr(sdtPr);

        SdtContentBlock sdtContent = objectFactory.createSdtContentBlock();
        sdtBlock.setSdtContent(sdtContent);

        Tag tag = objectFactory.createTag();
        tag.setVal(tagValue);

        sdtPr.setTag(tag);

        return sdtBlock;
    }
}
