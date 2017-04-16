package ulink.servlet.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

import ulink.constructor.Client;
import ulink.constructor.ClientByIllness;
import ulink.constructor.Condition;
import ulink.dao.DatabaseConnection;

/**
 * Servlet implementation class ClientScreeningEmail
 */
@WebServlet("/ClientScreeningEmail")
public class ClientScreeningEmailReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientScreeningEmailReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("TEST");

		DatabaseConnection connection = new DatabaseConnection();
		System.out.println("Enter email");
		// int age = Integer.parseInt(request.getParameter("age"));
		// String gender = request.getParameter("gender");
		// String type = request.getParameter("type");
		int ID = Integer.parseInt(request.getParameter("ID"));
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String [] clients = request.getParameterValues("clients");
		//String[] email = request.getParameterValues("email");
		clients = clients[0].split(",");
	//	System.out.println(ID);
		//System.out.println(clients[0]);
		// String direction = request.getParameter("direction");
		session.setAttribute("ID", ID);
		Condition condition = connection.retrieveAllConditionByID(ID);

		
		String filePath = null;
		// OutputStream out = response.getOutputStream();

		PdfWriter writer = null;
		final String TMP_DIR_PATH = "/ClientScreeningEmail.pdf";
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

			PdfPTable table = new PdfPTable(4);
			table.setTotalWidth(new float[] { 120, 120, 120, 120 });
			table.setLockedWidth(true);
			PdfContentByte cb = writer.getDirectContent();

			// first row
			PdfPCell cell = new PdfPCell(new Phrase(condition.getConditionName()));
			cell.setFixedHeight(30);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("S/N"));
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
			cell = new PdfPCell(new Phrase("Gender"));
			cell.setFixedHeight(30);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(1);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("Email"));
			cell.setFixedHeight(30);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(1);
			table.addCell(cell);

			for (int i = 0; i < clients.length; i++) {
				
				ClientByIllness clientByIllness = connection.getClientByEmail(clients[i]);
				
				// second row
				cell = new PdfPCell(new Phrase((i + 1) + ""));
				cell.setFixedHeight(30);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(clientByIllness.getName()));
				// cell.setBorder(Rectangle.NO_BORDER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setFixedHeight(30);
				table.addCell(cell);

				// third row

				cell = new PdfPCell(new Phrase(new Phrase(clientByIllness.getGender())));
				// cell.setBorder(Rectangle.NO_BORDER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(clientByIllness.getEmail()));
				cell.setFixedHeight(30);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

			}
			document.add(table);

		} catch (Exception e) {

		} 
			document.close();
		
	}

}
