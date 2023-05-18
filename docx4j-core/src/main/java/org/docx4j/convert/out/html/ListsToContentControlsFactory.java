package org.docx4j.convert.out.html;

import org.apache.commons.lang3.StringUtils;
import org.docx4j.convert.out.AbstractConversionSettings;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * @author longyg
 */
public class ListsToContentControlsFactory {
    public static Logger log = LoggerFactory.getLogger(ListsToContentControlsFactory.class);

    private ListsToContentControlsFactory() {
    }

    public static ListsToContentControls getInstance(WordprocessingMLPackage wmlPackage, AbstractConversionSettings conversionSettings) {
        if (!(conversionSettings instanceof HTMLSettings)) {
            return null;
        }
        HTMLSettings htmlSettings = (HTMLSettings) conversionSettings;
        String className = htmlSettings.getListToContentControlsImplClass();
        if (StringUtils.isBlank(className)) return null;
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(WordprocessingMLPackage.class);
            return (ListsToContentControls) constructor.newInstance(wmlPackage);
        } catch (Exception e) {
            log.error("Couldn't create instance of class: {}", className);
        }
        return null;
    }
}
