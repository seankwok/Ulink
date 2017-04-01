package ulink.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
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
		
		  Rectangle small = new Rectangle(450,300);
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
	        
			//this month
			String month = utility.getMonth(Integer.parseInt(date.substring(5, 7)));
		
			
			//last month
			LocalDate myDate =LocalDate.parse(connection.retrieveLatestDate());
			String lastDate = myDate.minusMonths(1).toString();
		//	Utility utility = new Utility();
			String lastMonth = utility.getMonth(Integer.parseInt(lastDate.substring(5, 7)));
			
	        
	        PdfPTable table = new PdfPTable(6);
	        table.setTotalWidth(new float[]{ 60, 60, 60, 60, 60,60 });
	        table.setLockedWidth(true);
	        PdfContentByte cb = writer.getDirectContent();
	        
	        // first row
	        PdfPCell cell = new PdfPCell(new Phrase("View Top 5 referral sources"));
	        cell.setFixedHeight(30);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(6);
	        table.addCell(cell);
	        
	         cell = new PdfPCell(new Phrase(month));
	        cell.setFixedHeight(30);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	       // cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(lastMonth));
	        cell.setFixedHeight(30);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	       // cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        table.addCell(cell);
	        
	        for(int i =0; i<5; i++){
	        
	        // second row
	        cell = new PdfPCell(new Phrase(list.get(i).getRanking()+""));
	        cell.setFixedHeight(30);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	       // cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	       
	        cell = new PdfPCell(new Phrase(list.get(i).getName()));
	     //   cell.setBorder(Rectangle.NO_BORDER);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setFixedHeight(30);
	        table.addCell(cell);
	        // third row
	        
	        cell = new PdfPCell(new Phrase(new Phrase(list.get(i).getCount()+"")));
	   //     cell.setBorder(Rectangle.NO_BORDER);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(cell);
	        }
	        document.add(table);
	        document.close();

	}

}
