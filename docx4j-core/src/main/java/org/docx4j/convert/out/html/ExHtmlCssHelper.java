package org.docx4j.convert.out.html;

import org.docx4j.model.properties.run.Bold;
import org.docx4j.model.styles.Node;
import org.docx4j.model.styles.StyleTree;
import org.docx4j.model.styles.Tree;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.CTTblStylePr;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STTblStyleOverrideType;
import org.docx4j.wml.Style;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.docx4j.wml.STTblStyleOverrideType.*;

/**
 * @author longyg
 */
public class ExHtmlCssHelper {
    private static final Logger log = LoggerFactory.getLogger(ExHtmlCssHelper.class);
    public static final String DOC_DEFAULTS_CSS_NAME = "DocDefaults";
    private static final String FONT_WEIGHT = "font-weight";
    private static final String DEFAULT_FONT_WEIGHT_CSS = FONT_WEIGHT + ": normal;";

    private ExHtmlCssHelper() {
    }

    /**
     * It is very important that the classes order defined in head style, because that will decide the display priority in browser.
     * Based on word style priority, the order should be:
     * rStyle > pStyle > Default paragraph (Normal) > table conditional formatting > tblStyle > docDefaults.
     * The low priority style should be firstly generated.
     */
    public static void createCssForStyles(OpcPackage opcPackage, StyleTree styleTree, StringBuilder result) {
        if (!(opcPackage instanceof WordprocessingMLPackage)) return;

        WordprocessingMLPackage wmlPackage = (WordprocessingMLPackage) opcPackage;
        Set<Style> addedPStyles = new HashSet<>();

        // The doc default styles have the lowest priority, should be added at first.
        createCssForDocDefault(opcPackage, styleTree, result, addedPStyles);

        // Next, create css for table styles
        createCssForTableStyles(wmlPackage, styleTree, result);

        // Next, create css for pPr/rPr of tblStyle
        createCssForPPrOfTblStyles(wmlPackage, styleTree, result);

        // Next, create css for table conditional formatting styles
        createCssForTableConditionalFormattingStyles(wmlPackage, styleTree, result);

        // Next, create css for paragraph styles, exclude DocDefaults
        createCssForParagraphStyles(wmlPackage, styleTree, result, addedPStyles);

        // Last, create css for character styles
        createCssForCharacterStyles(wmlPackage, styleTree, result);
    }

    private static void createCssForCharacterStyles(WordprocessingMLPackage wmlPackage, StyleTree styleTree, StringBuilder result) {
        Tree<StyleTree.AugmentedStyle> characterStylesTree = styleTree.getCharacterStylesTree();
        result.append("\n /* CHARACTER STYLES */ \n");
        if (null == characterStylesTree) return;
        walk(characterStylesTree, s -> {
            result.append("span.").append(s.getStyleId()).append(" {display:inline;");
            if (s.getRPr() == null) {
                log.warn("! null rPr for character style '{}'", s.getStyleId());
            } else {
                HtmlCssHelper.createCss(wmlPackage, s.getRPr(), result);
            }
            result.append("}\n");
        });
    }

    private static void createCssForTableStyles(WordprocessingMLPackage wmlPackage, StyleTree styleTree, StringBuilder result) {
        result.append("\n /* TABLE STYLES */ \n");
        Tree<StyleTree.AugmentedStyle> tableStylesTree = styleTree.getTableStylesTree();
        if (null == tableStylesTree) return;
        walk(tableStylesTree, s -> {
            result.append("table.").append(s.getStyleId()).append(" {display:table;");

            // TblPr
            if (s.getTblPr() != null) {
                log.debug("Applying tblPr..");
                HtmlCssHelper.createCss(s.getTblPr(), result);
            }

            // TblStylePr - STTblStyleOverrideType stuff
            if (s.getTblStylePr() != null) {
                log.debug("Applying tblStylePr.. TODO!");
                // Its a list, created automatically
                HtmlCssHelper.createCss(s.getTblStylePr(), result);
            }

            // TrPr - eg jc, trHeight, wAfter, tblCellSpacing
            if (s.getTrPr() != null) {
                log.debug("Applying trPr.. TODO!");
                HtmlCssHelper.createCss(s.getTrPr(), result);
            }

            // TcPr - includes includes TcPrInner.TcBorders, CTShd, TcMar, CTVerticalJc
            if (s.getTcPr() != null) {
                log.debug("Applying tcPr.. ");
                HtmlCssHelper.createCss(s.getTcPr(), result);
            }

            if (s.getPPr() != null) {
                HtmlCssHelper.createCss(wmlPackage, s.getPPr(), result, false, false);
            }
            if (s.getRPr() != null) {
                HtmlCssHelper.createCss(wmlPackage, s.getRPr(), result);
            }
            result.append("}\n");
        });
    }

    private static void createCssForParagraphStyles(WordprocessingMLPackage wmlPackage, StyleTree styleTree,
                                                    StringBuilder result, Set<Style> addedPStyles) {
        result.append("\n /* PARAGRAPH STYLES */ \n");
        Tree<StyleTree.AugmentedStyle> paragraphStylesTree = styleTree.getParagraphStylesTree();
        if (null == paragraphStylesTree) return;
        walk(paragraphStylesTree, style -> {
            if (!addedPStyles.contains(style)) {
                appendParagraphStyle(wmlPackage, style, result);
            }
        });
    }

    private static void createCssForDocDefault(OpcPackage opcPackage, StyleTree styleTree, StringBuilder result, Set<Style> addedPStyles) {
        result.append("\n /* DOC DEFAULT STYLES */ \n");
        Style docDefaultStyle = getDocDefaultStyle(styleTree);
        if (null != docDefaultStyle) {
            appendParagraphStyle(opcPackage, docDefaultStyle, result);
            addedPStyles.add(docDefaultStyle);
        }
    }

    private static void createCssForPPrOfTblStyles(WordprocessingMLPackage wmlPackage, StyleTree styleTree, StringBuilder result) {
        result.append("\n /* TABLE PARAGRAPH STYLES */ \n");
        Tree<StyleTree.AugmentedStyle> tableStylesTree = styleTree.getTableStylesTree();
        if (null == tableStylesTree) return;
        walk(tableStylesTree, style -> appendPStyle(wmlPackage, style, style.getStyleId() + "-PPR", result));
    }

    private static void createCssForTableConditionalFormattingStyles(
            WordprocessingMLPackage wmlPackage, StyleTree styleTree, StringBuilder result) {
        result.append("\n /* TABLE CONDITIONAL FORMATTING STYLES */ \n");
        Tree<StyleTree.AugmentedStyle> tableStylesTree = styleTree.getTableStylesTree();
        if (null == tableStylesTree) return;
        walk(tableStylesTree, style -> {
            // conditional formatting priority:
            // band1Horz = band2Horz < band1Vert = band2Vert < firstCol = lastCol <
            // firstRow = lastRow < nwCell = neCell = swCell = seCell
            STTblStyleOverrideType[] styleTypes =
                    new STTblStyleOverrideType[]{BAND_1_HORZ, BAND_2_HORZ, BAND_1_VERT, BAND_2_VERT,
                            FIRST_COL, LAST_COL, FIRST_ROW, LAST_ROW, NW_CELL, NE_CELL, SW_CELL, SE_CELL};
            for (STTblStyleOverrideType styleType : styleTypes) {
                CTTblStylePr tblStylePr = getTblStylePr(style, styleType);
                if (null != tblStylePr) {
                    appendCTTblStylePrStyle(wmlPackage, tblStylePr,
                            style.getStyleId() + "-" + styleType.value(), result);
                }
            }
        });
    }

    public static Style getDocDefaultStyle(StyleTree styleTree) {
        Tree<StyleTree.AugmentedStyle> paragraphStylesTree = styleTree.getParagraphStylesTree();
        if (null == paragraphStylesTree) return null;
        Node<StyleTree.AugmentedStyle> rootElement = paragraphStylesTree.getRootElement();
        if (null == rootElement) return null;
        if (null == rootElement.getData()) return null;
        Style style = rootElement.getData().getStyle();
        if (null == style || null == style.getName()) return null;
        if (DOC_DEFAULTS_CSS_NAME.equals(style.getName().getVal())) {
            return style;
        }
        return null;
    }

    private static void appendParagraphStyle(OpcPackage opcPackage, Style s, StringBuilder result) {
        appendPStyle(opcPackage, s, s.getStyleId(), result);
    }

    private static void appendPStyle(OpcPackage opcPackage, Style s, String cssName, StringBuilder result) {
        StringBuilder sb = new StringBuilder();
        sb.append(".").append(cssName).append(" {display:block;");
        if (s.getPPr() == null) {
            log.debug("null pPr for style '{}'", s.getStyleId());
        } else {
            HtmlCssHelper.createCss(opcPackage, s.getPPr(), sb, false, false);
        }
        if (s.getRPr() == null) {
            log.debug("null rPr for style '{}'", s.getStyleId());
        } else {
            HtmlCssHelper.createCss(opcPackage, s.getRPr(), sb);
        }
        appendFontStyles(s, sb);
        sb.append("}\n");
        result.append(sb);
    }

    private static void appendCTTblStylePrStyle(OpcPackage opcPackage, CTTblStylePr ctTblStylePr, String cssName, StringBuilder result) {
        result.append(".").append(cssName).append(" {display:block;");
        if (null != ctTblStylePr.getPPr()) {
            HtmlCssHelper.createCss(opcPackage, ctTblStylePr.getPPr(), result, false, false);
        }
        if (null != ctTblStylePr.getRPr()) {
            HtmlCssHelper.createCss(opcPackage, ctTblStylePr.getRPr(), result);
        }
        result.append("}\n");
    }

    private static CTTblStylePr getTblStylePr(Style style, STTblStyleOverrideType type) {
        List<CTTblStylePr> tblStylePrList = style.getTblStylePr();
        for (CTTblStylePr ctTblStylePr : tblStylePrList) {
            if (ctTblStylePr.getType().equals(type)) {
                return ctTblStylePr;
            }
        }
        return null;
    }

    public static void appendFontStyles(Style style, StringBuilder result) {
        if (null == style || null == style.getRPr()) return;
        String fontWeight = getFontWeight(style.getRPr());
        appendFontWeight(fontWeight, result);
    }

    public static void appendFontWeight(String fontWeightCss, StringBuilder result) {
        int i = result.indexOf(FONT_WEIGHT);
        if (i < 0) {
            result.append(fontWeightCss);
        } else {
            int endIndex = result.indexOf(";", i);
            if (endIndex < 0) {
                int lastIndex = result.indexOf("}", i);
                if (lastIndex > -1) {
                    endIndex = lastIndex - 1;
                } else {
                    endIndex = result.length() - 1;
                }
            }
            result.replace(i, endIndex + 1, fontWeightCss);
        }
    }

    private static String getFontWeight(RPr rPr) {
        if (rPr.getB() != null) {
            Bold bold = new Bold(rPr.getB());
            return bold.getCssProperty();
        }
        return DEFAULT_FONT_WEIGHT_CSS;
    }


    public static void walk(Tree<StyleTree.AugmentedStyle> tree, Consumer<Style> consumer) {
        for (Node<StyleTree.AugmentedStyle> n : tree.toList()) {
            if (n.getData() == null) {
                if (n.equals(tree.getRootElement())) {
                    // that's ok
                } else {
                    log.error("Node<AugmentedStyle> unexpectedly null data: {}", n);
                }
                continue;
            }
            Style s = n.getData().getStyle();
            if (null != s) {
                consumer.accept(s);
            }
        }
    }

}
