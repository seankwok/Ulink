package ulink.servlet.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ulink.constructor.RankingDoctor;
import ulink.constructor.RankingReferredBy;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class RankingReferralReport
 */
@WebServlet("/RankingReferralReport")
public class RankingReferralReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingReferralReport() {
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
		DatabaseConnection database = new DatabaseConnection();
		
		ArrayList<RankingReferredBy> referredByList;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		//dd/mm/yyyy >> yyyy/mm/dd
		Utility utility = new Utility();
		referredByList = database.retrieveAllRankingReferredBy(utility.changeDateExportFormat(startDate), utility.changeDateExportFormat(endDate));
		

		
		int width = 500;
		int height = 400;
		String filePath = null;
		// OutputStream out = response.getOutputStream();

		PdfWriter writer = null;
		final String TMP_DIR_PATH = "/RankingReferral.pdf";
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
			Paragraph p = new Paragraph("ULINK REPORTING SYSTEM");
			p.setAlignment(p.ALIGN_CENTER);
			document.add(p);
			
			PdfPTable table = new PdfPTable(3);
			table.setTotalWidth(new float[] {60, 60, 60 });
			table.setLockedWidth(true);
			PdfContentByte cb = writer.getDirectContent();

			// first row
			PdfPCell cell = new PdfPCell(new Phrase("Criteria : Referral"));
			cell.setFixedHeight(30);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Ranking"));
			cell.setFixedHeight(30);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(1);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Name"));
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
		
			
			for (int i = 0; i < referredByList.size(); i++) {
				
				// second row
				cell = new PdfPCell(new Phrase(referredByList.get(i).getRanking()));
				cell.setFixedHeight(30);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(referredByList.get(i).getName()));
				// cell.setBorder(Rectangle.NO_BORDER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setFixedHeight(30);
				table.addCell(cell);

				// third row

				cell = new PdfPCell(new Phrase(new Phrase(referredByList.get(i).getCount())));
				// cell.setBorder(Rectangle.NO_BORDER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

		


			}
			document.add(table);
			
			
		} catch(Exception e){
			
		}
	}

}
