package utils;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Font.FontStyle;
/**
 * @author Tsvetelin Tsonev <tsvetelin.tsonev@yahoo.co.uk>
 * This class is a simple wrapper for ITEXPDF library and is providing simple API to work with PDF documents.
 */
public interface SimplePdfWriter {
	/**
	 * Adds a paragraph as title to PDF document. Must be called first, to set the first paragraph as title
	 * @param title - The "title" of the PDF document.
	 */
	public void setPageTitle(String title);
	/**
	 * Adds a paragraph as title to PDF document. Must be called first, to set the first paragraph as title
	 * @param title - The "title" of the PDF document.
	 * @param fontSize - Font size of the "title".
	 */
	public void setPageTitle(String title, int fontSize);
	/**
	 * Adds a paragraph as title to PDF document. Must be called first, to set the first paragraph as title
	 * @param title - The "title" of the PDF document.
	 * @param fontStyle - Font style of the "title".
	 */
	public void setPageTitle(String title, FontStyle fontStyle);
	/**
	 * Adds a paragraph as title to PDF document. Must be called first, to set the first paragraph as title
	 * @param title - The "title" of the PDF document.
	 * @param font - Font of the "title".
	 */
	public void setPageTitle(String title, Font font);
	/**
	 * Adds a paragraph as title to PDF document.
	 * @param text - Paragraph content.
	 */
	public void addParagraph(String text);
	/**
	 * Adds a paragraph as title to PDF document.
	 * @param text - Paragraph content.
	 * @param fontSize - Paragraph font size.
	 */
	public void addParagraph(String text, int fontSize);
	/**
	 * Adds a paragraph as title to PDF document.
	 * @param text - Paragraph content.
	 * @param fontFamily - Paragraph font family.
	 */
	public void addParagraph(String text, FontFamily fontFamily);
	/**
	 * Adds a paragraph as title to PDF document.
	 * @param text - Paragraph content.
	 * @param fontStyle - Paragraph font style.
	 */
	public void addParagraph(String text, FontStyle fontStyle);
	/**
	 * Adds a paragraph as title to PDF document.
	 * @param text - Paragraph content.
	 * @param font - Paragraph font.
	 */
	public void addParagraph(String text, Font font);
	/**
	 * Sets document's font family
	 * @param fontFamily
	 */
	public void setFontFamily(FontFamily fontFamily);
	/** 
	 * Sets document's font size
	 * @param fontSize
	 */
	public void setFontSize(int fontSize);
	/**
	 * Creates new page in PDF document. 
	 */
	public void createNewPage();
	/**
	 * Closes the document. After the document is closed it is no longer possible to add content to document's body.
	 */
	public void close();
}
