package ulink.servlet.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ulink.constructor.RankingReferredBy;
import ulink.constructor.RankingSpecialty;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class RankingReferralbyReport
 */
@WebServlet("/RankingSpecialtyReport")
public class RankingSpecialtyReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingSpecialtyReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		DatabaseConnection database = new DatabaseConnection();
		ArrayList<RankingSpecialty> SpecialtyList;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Utility utility = new Utility();
		System.out.println(startDate);
		SpecialtyList = database.retrieveAllRankingSpecialty(utility.changeDateExportFormat(startDate), utility.changeDateExportFormat(endDate));
		
		
		int width = 500;
		int height = 400;
		String filePath = null;
		// OutputStream out = response.getOutputStream();

		PdfWriter writer = null;
		final String TMP_DIR_PATH = "/RankingSpecialty.pdf";
		final String image_path = "/ulink.jpg";
		
		Document document = new Document();

		try {
			response.setContentType("application/pdf");

			filePath = getServletContext().getRealPath(TMP_DIR_PATH);

			String imagePath = getServletContext().getRealPath(image_path);
			// ByteArrayOutputStream baos = new ByteArrayOutputStream();
			response.addHeader("Content-Disposition", "attachment;  filename=" + filePath);
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			System.out.println(filePath);
			document.open();
			// Display dashboard for Medical
			Image img = Image.getInstance(imagePath);
			img.scaleAbsolute(60f, 60f);
			img.setAlignment(img.ALIGN_CENTER);
			document.add(img);
			Paragraph p = new Paragraph("ULINK REPORTING SYSTEM");
			p.setAlignment(p.ALIGN_CENTER);
			document.add(p);
			
			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(new float[] { 120, 120 });
			table.setLockedWidth(true);
			PdfContentByte cb = writer.getDirectContent();

			// first row
			PdfPCell cell = new PdfPCell(new Phrase("Criteria : Specialty - " + startDate + " to " + endDate));
			cell.setFixedHeight(30);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);

	
			cell = new PdfPCell(new Phrase("Specialty"));
			cell.setFixedHeight(30);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(1);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Count"));
			cell.setFixedHeight(30);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(1);
			table.addCell(cell);
		
			
			for (int i = 0; i < SpecialtyList.size(); i++) {
				
				// second row
				cell = new PdfPCell(new Phrase(SpecialtyList.get(i).getSpecialty()));
				cell.setFixedHeight(30);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell.setBorder(Rectangle.NO_BORDER);
				cell.setColspan(1);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(SpecialtyList.get(i).getCount()+""));
				System.out.println(SpecialtyList.get(i).getCount());
				// cell.setBorder(Rectangle.NO_BORDER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setFixedHeight(30);
				cell.setColspan(1);
				table.addCell(cell);

				// third row


		


			}
			document.add(table);
			
			
		} catch(Exception e){
			
		}
		document.close();
	}

}
