package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Font.FontStyle;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
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
}
