package org.docx4j.utils;

import org.docx4j.UnitsOfMeasurement;
import org.docx4j.jaxb.Context;
import org.docx4j.model.properties.run.FontColor;
import org.docx4j.wml.CTShd;
import org.docx4j.wml.STShd;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author longyg
 */
public class ShadingUtil {
    protected static final Map<String, Integer> PATTERN_PERCENTAGES = new TreeMap<String, Integer>();

    static {
	  /*
	   * These patterns cause a reset
	  PATTERN_PERCENTAGES.put("clear", -1);
	  PATTERN_PERCENTAGES.put("nil", -1);

	   * and these can't be aproximated by a background color
	   * so they are ignored
	  PATTERN_PERCENTAGES.put("diagStripe", -1);
	  PATTERN_PERCENTAGES.put("horzStripe", -1);
	  PATTERN_PERCENTAGES.put("thinDiagStripe", -1);
	  PATTERN_PERCENTAGES.put("thinHorzStripe", -1);
	  PATTERN_PERCENTAGES.put("thinReverseDiagStripe", -1);
	  PATTERN_PERCENTAGES.put("thinVertStripe", -1);
	  PATTERN_PERCENTAGES.put("vertStripe", -1);
	   */


        // These Patterns are aproximated by a background color
        PATTERN_PERCENTAGES.put("diagCross", 50);
        PATTERN_PERCENTAGES.put("horzCross", 50);

        PATTERN_PERCENTAGES.put("thinDiagCross", 25);
        PATTERN_PERCENTAGES.put("thinHorzCross", 25);

        PATTERN_PERCENTAGES.put("pct5", 5);
        PATTERN_PERCENTAGES.put("pct10", 10);
        PATTERN_PERCENTAGES.put("pct12", 12);
        PATTERN_PERCENTAGES.put("pct15", 15);
        PATTERN_PERCENTAGES.put("pct20", 20);
        PATTERN_PERCENTAGES.put("pct25", 25);
        PATTERN_PERCENTAGES.put("pct30", 30);
        PATTERN_PERCENTAGES.put("pct35", 35);
        PATTERN_PERCENTAGES.put("pct37", 37);
        PATTERN_PERCENTAGES.put("pct40", 40);
        PATTERN_PERCENTAGES.put("pct45", 45);
        PATTERN_PERCENTAGES.put("pct50", 50);
        PATTERN_PERCENTAGES.put("pct55", 55);
        PATTERN_PERCENTAGES.put("pct60", 60);
        PATTERN_PERCENTAGES.put("pct62", 62);
        PATTERN_PERCENTAGES.put("pct65", 65);
        PATTERN_PERCENTAGES.put("pct70", 70);
        PATTERN_PERCENTAGES.put("pct75", 75);
        PATTERN_PERCENTAGES.put("pct80", 80);
        PATTERN_PERCENTAGES.put("pct85", 85);
        PATTERN_PERCENTAGES.put("pct87", 87);
        PATTERN_PERCENTAGES.put("pct90", 90);
        PATTERN_PERCENTAGES.put("pct95", 95);
        PATTERN_PERCENTAGES.put("solid", 100);
    }

    public static CTShd calcShd(CTShd shd) {
        int bgColor = 0xffffff; //the background color of the page is assumed as white
        int fgColor = 0; //the default color of the font is assumed as black
        int pctPattern = -1;
        fgColor = extractColor(shd.getColor(), 0);
        if ((shd.getVal() != null) &&
                ("clear".equals(shd.getVal().value())) &&
                ("auto".equals(shd.getFill()))
        ) {
            pctPattern = -2;
        } else {
            pctPattern = (shd.getVal() != null ? extractPattern(shd.getVal().value()) : -1);
            bgColor = extractColor(shd.getFill(), bgColor);
        }

        if (pctPattern == -1) {
            return createShd(bgColor);
        } else {
            return createShd(fgColor, bgColor, pctPattern);
        }
    }

    public static CTShd createShd(int bgColor) {
        CTShd shd = Context.getWmlObjectFactory().createCTShd();
        shd.setVal(STShd.CLEAR);
        shd.setFill(calcHexColor(bgColor));
        return shd;
    }

    private static CTShd createShd(int fgColor, int bgColor, int pctFg) {
        int resColor = UnitsOfMeasurement.combineColors(fgColor, bgColor, pctFg);
        CTShd shd = Context.getWmlObjectFactory().createCTShd();
        shd.setVal(STShd.CLEAR);
        shd.setFill(calcHexColor(resColor));
        return shd;
    }
    protected static String calcHexColor(int value) {
        String ret = Integer.toHexString(value).toUpperCase();
        return (ret.length() < 6 ?
                "000000".substring(0, 6 - ret.length()) + ret :
                ret);
    }

    protected static FontColor createFontColor(Color color) {
        Color white = new Color(Integer.parseInt("ffffff", 16));
        Color black = new Color(Integer.parseInt("000000", 16));
        org.docx4j.wml.Color c = new org.docx4j.wml.Color();
        if (distance(color, white) > distance(color, black)) {
            c.setVal("ffffff");
        } else {
            c.setVal("000000");
        }
        return new FontColor(c);
    }

    public static FontColor createFontColor(CTShd shd) {
        String fill = shd.getFill();
        Color color = new Color(Integer.parseInt(fill, 16));
        return createFontColor(color);
    }

    protected static int distance(Color color1, Color color2) {
        int r1 = color1.getRed();
        int g1 = color1.getGreen();
        int b1 = color1.getBlue();
        int r2 = color2.getRed();
        int g2 = color2.getGreen();
        int b2 = color2.getBlue();
        return (Math.max(r1, r2) - Math.min(r1, r2))
                + (Math.max(g1, g2) - Math.min(g1, g2))
                + (Math.max(b1, b2) - Math.min(b1, b2));
    }

    private static int extractColor(String value, int defaultColor) {
        int ret = defaultColor;
        if ((value != null) && (!"auto".equals(value))) {
            try {
                ret = Integer.parseInt(value, 16);
            } catch (NumberFormatException nfe) {//noop
            }
        }
        return ret;
    }

    private static int extractPattern(String pattern) {
        return ((pattern != null) &&
                (PATTERN_PERCENTAGES.containsKey(pattern)) ?
                PATTERN_PERCENTAGES.get(pattern) : -1);
    }
}
