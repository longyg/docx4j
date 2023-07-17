package org.docx4j.utils;

import org.docx4j.convert.out.html.HTMLConversionContext;
import org.docx4j.jaxb.Context;
import org.docx4j.model.styles.Node;
import org.docx4j.model.styles.StyleTree;
import org.docx4j.model.styles.Tree;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.util.List;

/**
 * @author longyg
 */
public class CommonUtil {

    private CommonUtil() {
    }

    private static final int MIN_HEADING_LVL = 1;
    private static final int MAX_HEADING_LVL = 6;
    public static final List<String> HEADINGS = List.of("h1", "h2", "h3", "h4", "h5", "h6");

    public static boolean isHeadingNode(String nodeName) {
        return HEADINGS.contains(nodeName);
    }

    public static boolean isHeadingParagraph(MainDocumentPart mainDocument, P p) {
        if (null == p || null == p.getPPr()) return false;
        PPrBase.PStyle pStyle = p.getPPr().getPStyle();
        if (null == pStyle || pStyle.getVal() == null) return false;
        return isHeadingStyle(mainDocument, pStyle.getVal());
    }

    public static boolean isHeadingStyle(MainDocumentPart mainDocument, String styleId) {
        return getHeadingLevel(mainDocument.getStyleTree(), styleId) != -1;
    }

    public static int getHeadingLevel(HTMLConversionContext context, String styleId) {
        return getHeadingLevel(context.getStyleTree(), styleId);
    }

    public static int getHeadingLevel(StyleTree styleTree, String styleId) {
        if (null == styleTree) return -1;
        Tree<StyleTree.AugmentedStyle> paragraphStylesTree = styleTree.getParagraphStylesTree();
        if (paragraphStylesTree == null) return -1;
        Node<StyleTree.AugmentedStyle> styleNode = paragraphStylesTree.get(styleId);
        return getHeadingLevel(styleNode);
    }

    public static int getHeadingLevel(Node<StyleTree.AugmentedStyle> styleNode) {
        if (null == styleNode) return -1;
        StyleTree.AugmentedStyle augmentedStyle = styleNode.getData();
        Node<StyleTree.AugmentedStyle> parentStyleNode = styleNode.getParent();
        if (null == augmentedStyle || null == augmentedStyle.getStyle()) {
            return getHeadingLevel(parentStyleNode);
        }
        Style style = augmentedStyle.getStyle();
        int lvl = getHeadingLevel(style);
        return lvl == -1 ? getHeadingLevel(parentStyleNode) : lvl;
    }

    public static int getHeadingLevel(Style style) {
        if (style.getPPr() == null || style.getPPr().getOutlineLvl() == null) return -1;
        PPrBase.OutlineLvl outlineLvl = style.getPPr().getOutlineLvl();
        int lvl = outlineLvl.getVal().intValue();
        if (lvl >= MIN_HEADING_LVL - 1 && lvl <= MAX_HEADING_LVL - 1) {
            return lvl + 1; // supported heading levels: 1 - 6
        }
        return -1;
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
