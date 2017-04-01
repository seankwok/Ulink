package ulink.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

public class TestMain {

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		  Rectangle small = new Rectangle(290,100);
	        Font smallfont = new Font(FontFamily.HELVETICA, 10);
	        Document document = new Document(small, 5, 5, 5, 5);
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("qwe.pdf"));
	        document.open();
			DatabaseConnection connection = new DatabaseConnection();
			String date =  connection.retrieveLatestDate();
			Utility utility = new Utility();
			String startDate = utility.getStartDateOfMonth(date);
			String endDate = utility.getEndDateOfMonth(date);
			
			ArrayList<RankingReferredBy> list = connection.retrieveAllRankingReferredByDashBoard(startDate,endDate);
	        
	        
	        PdfPTable table = new PdfPTable(4);
	        table.setTotalWidth(new float[]{ 60, 60, 60, 60 });
	        table.setLockedWidth(true);
	        PdfContentByte cb = writer.getDirectContent();
	        
	        // first row
	        PdfPCell cell = new PdfPCell(new Phrase("TEST"));
	        cell.setFixedHeight(30);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(4);
	        table.addCell(cell);
	        
	        for(int i =0; i<5; i++){
	        
	        // second row
	        cell = new PdfPCell(new Phrase(list.get(i).getName()));
	        cell.setFixedHeight(30);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       // cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        Barcode128 code128 = new Barcode128();
	        code128.setCode("14785236987541");
	        code128.setCodeType(Barcode128.CODE128);
	        Image code128Image = code128.createImageWithBarcode(cb, null, null);
	        cell = new PdfPCell(code128Image, true);
	     //   cell.setBorder(Rectangle.NO_BORDER);
	        cell.setFixedHeight(30);
	        table.addCell(cell);
	        // third row
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("and something else here", smallfont));
	   //     cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        table.addCell(cell);
	        }
	        document.add(table);
	        document.close();

	}

}
