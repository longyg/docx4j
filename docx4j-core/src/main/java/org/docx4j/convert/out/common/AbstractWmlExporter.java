/*
   Licensed to Plutext Pty Ltd under one or more contributor license agreements.  
   
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
package org.docx4j.convert.out.common;

import java.io.OutputStream;

import org.docx4j.convert.out.AbstractConversionSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

/** Superclass for the export of WordprocessingMLPackage(s)
 * 
 * @param <CS>
 */
public abstract class AbstractWmlExporter<CS extends AbstractConversionSettings, CC extends AbstractWmlConversionContext> extends AbstractExporter<CS, CC, WordprocessingMLPackage> {
	protected AbstractExporterDelegate<CS, CC> exporterDelegate = null;

	protected AbstractWmlExporter(AbstractExporterDelegate<CS, CC> exporterDelegate) {
		this.exporterDelegate = exporterDelegate;
	}

	@Override
	protected WordprocessingMLPackage preprocess(CS conversionSettings) throws Docx4JException {
	WordprocessingMLPackage wmlPackage = null;
		try {
			wmlPackage = (WordprocessingMLPackage)conversionSettings.getOpcPackage();
		}
		catch (ClassCastException cce) {
			throw new Docx4JException("Invalid document package in the settings, it isn't a WordprocessingMLPackage");
		}
		if (wmlPackage == null) {
			throw new Docx4JException("Missing WordprocessingMLPackage in the conversion settings");
		}
		else if (wmlPackage.getMainDocumentPart() == null) {
			throw new Docx4JException("MainDocumentPart missing");
		}
		else if (wmlPackage.getMainDocumentPart().getContents() == null
				|| wmlPackage.getMainDocumentPart().getContents().getBody() == null
				|| wmlPackage.getMainDocumentPart().getContents().getBody().getContent().size() == 0
				) {
			
			/* Would result in:
			 * 
				Caused by: org.apache.fop.fo.ValidationException: null:1:1602: "fo:flow" is missing child elements. Required content model: marker* (%block;)+ (See position 1:1602)
					at org.apache.fop.events.ValidationExceptionFactory.createException(ValidationExceptionFactory.java:38)
					at org.apache.fop.events.EventExceptionManager.throwException(EventExceptionManager.java:58)
					at org.apache.fop.events.DefaultEventBroadcaster$1.invoke(DefaultEventBroadcaster.java:173)
					at com.sun.proxy.$Proxy36.missingChildElement(Unknown Source)
					at org.apache.fop.fo.FONode.missingChildElementError(FONode.java:608)
				 */
			throw new Docx4JException("MainDocumentPart empty");
		}
		return Preprocess.process(wmlPackage, conversionSettings, conversionSettings.getFeatures());
	}

	@Override
	protected ConversionSectionWrappers createWrappers(CS conversionSettings, WordprocessingMLPackage preprocessedPackage) throws Docx4JException {
	ConversionSectionWrappers ret = null;
		ret = CreateWrappers.process(preprocessedPackage, conversionSettings.getFeatures());
		return ret;
	}

	@Override
	protected void process(CS conversionSettings, CC conversionContext, OutputStream outputStream) throws Docx4JException {
		exporterDelegate.process(conversionSettings, conversionContext, outputStream);
	}
}
