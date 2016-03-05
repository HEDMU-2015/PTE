####BEMÆRK! 
#####SimplePdfWriterImpl er wrapper klasse for ITEXTPDF java bibliotek, det betyder, at man skal tilføje itextpdf-x.x.x.jar til sin java buidpath!

######Det her er en eksempel, der viser hvordan man bruger FileUtilsImpl og SimplePdfWriterImpl klasser.
```java
FileUtils fileUtils = new FileUtilsImpl();
File file = null;
try {
 file = fileUtils.createFile("path/to/file/which/should/be/created");
} catch (FileExistsException e) {
 // handle exception
}
SimplePdfWriter pdf = new SimplePdfWriterImpl(file);
pdf.setPageTitle("Test title with custom font", FontFactory.getFont(FontFamily.COURIER.toString(), 24, BaseColor.BLUE));
pdf.setPageTitle("Test title with font style", FontStyle.UNDERLINE);
pdf.setPageTitle("Test title with font size", 40);

pdf.addParagraph("Test paragraph");
pdf.addParagraph("Test paragraph with custom font", FontFactory.getFont(FontFamily.COURIER.toString(), 24, BaseColor.BLUE));
pdf.addParagraph("Test paragraph with font style", FontStyle.UNDERLINE);
pdf.addParagraph("Test paragraph with font size", 40);
pdf.close();
```
