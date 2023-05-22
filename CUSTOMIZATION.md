# Customization

Here are customization histories made based on official [plutext/docx4j](https://github.com/plutext/docx4j),
the histories are listed in descending order by date.

## 2023.5.22
### Changes
1. Enhance `PropertyFactory` to handle small caps/all caps for font, they are converted to specific css styles as well.
2. Comment out `ensureFoTableBody()` method call in `AbstractTableWriterModel.build()`, because there is issue for handling colspan after making the last header row as body row.

## 2023.5.18
### Changes
1. Enhance `ListsToContentControls` to be able to get customized implementation according to `ListsToContentControlsFactory`. Users can provide themselves implementation class through `HTMLSettings`.
2. Enhance `ExHtmlCssHelper`, it is able to create css styles for `marker` of list item. 

## 2023.5.11
### Changes
1. The color is also possible be set as `auto` for text in paragraph, it should also be calculated based on shading. Enhanced `HtmlCssHelper` and `PropertyFactory` to support this.
2. For headings, we need always set it's font-weight, because the default font-weight is `bold` in browser. However if it is not set in Word, it is by default `normal`. Enhanced `ExHtmlCssHelper` to support this. 

## 2023.5.9
### Changes
1. Rewrite `HtmlCssHelper.createDefaultCss` by `ExHtmlCssHelper.createDefaultCss`, do not generate header and footer css, because those styles will impact HTML export to word.
2. Enhance image handling in `AbstractWordXmlPicture`, generate additional `div` element with height style as parent of `img` in case the image is rotated.

## 2023.5.6
### Changes
1. When color is set as `auto` for table in Word, it should be calculated if it should be white or black according to shading.
   Enhanced `AbstractTableWriter` to handle auto font color for table cell.
2. Update `Underline` to map Word's heavy underline to corresponding HTML non-heavy underline.

## 2023.5.4
### Changes
1. Add new extension point (`postProcess()` method) in `org.docx4j.convert.out.common.writer.AbstractTableWriter`, which will be implemented in subclass, purpose is to set additional attributes for created cell node.

## 2023.4.28
### Changes
1. Solved one bug regarding highlight and shading in `org.docx4j.model.properties.PropertyFactory`. The shading should take effect only when highlight is not defined.
2. Rewrote `HtmlCssHelper.createCssForStyles()`, delegate to `ExHtmlCssHelper.createCssForStyles()`, in that, implemented customized logic.

## 2023.4.18

First time fork, start customization based on [plutext/docx4j](https://github.com/plutext/docx4j) the latest branch at that
time: **_VERSION_11_4_10_**.

Renamed groupId to `io.github.longyg`, this is need to publish jar to myself maven central repository.

The version is `11.4.10-<alphaX>` for customization based on version `11.4.10`.

### Changes

1. Update one test case which can't pass on my local laptop: RunFontSelectorCalibriCheckBoxTest.testFont()
2. Fix `table-layout: fixed` property issue at `org.docx4j.model.properties.PropertyFactory`:

   ```
   tblPr.getTblW().getW() != BigInteger.ZERO) 
   ```
   changed to:

   ```
   !tblPr.getTblW().getW().equals(BigInteger.ZERO)
   ```
3. Add method in `org.docx4j.convert.out.common.writer.AbstractTableWriter`:
   
   ```
   protected void createCellProperties(List<Property> properties, 
          AbstractTableWriterModel table, 
          int rowIndex, int columnIndex) {}
   ```

   It is implemented in subclass.
4. Merge strike and underline property to single text-decoration css in `org.docx4j.convert.out.html.HtmlCssHelper`.
   Also add color and style of underline if defined.

5. Enhance underline handling, supports underline color and underline style.
   `org.docx4j.model.properties.run.Underline`