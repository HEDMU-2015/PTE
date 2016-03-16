package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Font.FontStyle;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * @author Tsvetelin Tsonev <tsvetelin.tsonev@yahoo.co.uk>
 * This class is a simple wrapper for ITEXPDF library and is providing simple API to work with PDF documents.
 */
public class SimplePdfWriterImpl implements SimplePdfWriter {
	
	String filePath = null;
	File pdfFile = null;
	Document document;
	FontFamily fontFamily = FontFamily.HELVETICA;
	float fontSize = 12;
	
	public SimplePdfWriterImpl(String filePath) {
		this.filePath = filePath;
		initializePdfWriter();
	}
	
	public SimplePdfWriterImpl(File file) {
		this.pdfFile = file;
		initializePdfWriter();
	}
	
	private void initializePdfWriter() {
		document = new Document(PageSize.A4, 50,50,50,50);
		try {
			if(this.pdfFile != null) {
				PdfWriter.getInstance(document, new FileOutputStream(this.pdfFile));
			} else {
				PdfWriter.getInstance(document, new FileOutputStream(this.filePath));
			}
		} catch (FileNotFoundException e) {
			// Should never happen :)
		} catch (DocumentException e) {
			// Not sure when it will be thrown, couldn't find it in the ITEXTPDF API. 
		}
		document.open();
	}
	
	private void addToDocument(Element elem) {
		try {
			document.add(elem);
		} catch (DocumentException e) {
			// Should never happen
		}
	}
	
	@Override
	public void setPageTitle(String title) {
		addParagraph(title);
	}
	
	@Override
	public void setPageTitle(String title, int fontSize) {
		addParagraph(title, fontSize);
	}
	
	@Override
	public void setPageTitle(String title, FontStyle fontStyle) {
		addParagraph(title, fontStyle);
	}
	
	@Override
	public void setPageTitle(String title, Font font) {
		addParagraph(title, font);
	}
	
	@Override
	public void addParagraph(String text) {
		Paragraph paragraph = new Paragraph(text,  FontFactory.getFont(fontFamily.toString(), fontSize));
		addToDocument(paragraph);
	}
	
	@Override
	public void addParagraph(String text, int fontSize) {
		Paragraph paragraph = new Paragraph(text,  FontFactory.getFont(fontFamily.toString(), fontSize));
		addToDocument(paragraph);
	}
	
	@Override
	public void addParagraph(String text, FontFamily fontFamily) {
		Paragraph paragraph = new Paragraph(text,  FontFactory.getFont(fontFamily.toString(), fontSize));
		addToDocument(paragraph);
	}
	
	@Override
	public void addParagraph(String text, FontStyle fontStyle) {
		Paragraph paragraph = new Paragraph(text, FontFactory.getFont(fontFamily.toString(), fontSize, fontStyle.ordinal()));
		addToDocument(paragraph);
	}
	
	@Override
	public void addParagraph(String text, Font font) {
		Paragraph paragraph = new Paragraph(text, font);
		addToDocument(paragraph);
	}
	
	@Override
	public void setFontFamily(FontFamily fontFamily) {
		this.fontFamily = fontFamily;
	}
	
	@Override
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	@Override
	public void createNewPage() {
		document.newPage();
	}
	
	@Override
	public void close() {
		document.close();
	}

	@Override
	public void addImage(String imagePath, float height, float width) {
		Image iTextImage = null;
		
			try {
				iTextImage = Image.getInstance(imagePath);
			} catch (BadElementException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		iTextImage.scaleToFit(width, height);
		addToDocument(iTextImage);
	}
	
	public static PdfPCell createImageCell(Image image) {
	    PdfPCell cell = new PdfPCell(image, true);
	    cell.setBorder(Rectangle.NO_BORDER);
	    return cell;
	}
	
	public static PdfPCell createTextCell(String text) {
	    PdfPCell cell = new PdfPCell();
	    Paragraph p = new Paragraph(text);
	    p.setAlignment(Element.ALIGN_RIGHT);
	    cell.setPaddingTop(100);
	    cell.addElement(p);
	    cell.setVerticalAlignment(Element.ALIGN_TOP);
	    cell.setBorder(Rectangle.NO_BORDER);
	    return cell;
	}

	@Override
	public void addImageWithText(String imagePath, String text) {
		Image image = null;
		try {
			image = Image.getInstance(imagePath);
		} catch (BadElementException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    PdfPTable table = new PdfPTable(2);
		    table.setWidthPercentage(100);
		    try {
				table.setWidths(new int[]{2, 2});
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		    table.addCell(createImageCell(image));
		    table.addCell(createTextCell(text));
		    addToDocument(table);
	}
}
