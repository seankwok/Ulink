package ulink.servlet.export;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import ulink.constructor.Client;
import ulink.constructor.RankingDoctor;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class DashboardReport
 */
@WebServlet("/DashboardReport")
public class DashboardReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardReport() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		int width = 500;
		int height = 400;
		String filePath = null;
		// OutputStream out = response.getOutputStream();

		PdfWriter writer = null;
		final String TMP_DIR_PATH = "/dashboard.pdf";
		final String image_path = "/ulink.jpg";
		JFreeChart dashboardMedical = generateBarChartMedical();
		JFreeChart dashboardVisa = generateBarChartVisa();
		JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();

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
			Paragraph p = new Paragraph("ULINK REPORTING SYSTEM � DASHBOARD");
			p.setAlignment(p.ALIGN_CENTER);
			document.add(p);

			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
			dashboardMedical.draw(graphics2d, rectangle2d);
			Image chartImage = Image.getInstance(template);
			document.add(chartImage);
			graphics2d.dispose();

			// Display dashboard for Visa
			PdfContentByte contentByte2 = writer.getDirectContent();
			PdfTemplate template2 = contentByte2.createTemplate(width, height);
			Graphics2D graphics2d2 = template2.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d2 = new Rectangle2D.Double(0, 0, width, height);
			dashboardVisa.draw(graphics2d2, rectangle2d2);
			Image chartImage2 = Image.getInstance(template2);
			document.add(chartImage2);
			graphics2d2.dispose();

			// Display dashboard for visa type
			PdfContentByte contentByte3 = writer.getDirectContent();
			PdfTemplate template3 = contentByte3.createTemplate(width, height);
			Graphics2D graphics2d3 = template3.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d3 = new Rectangle2D.Double(0, 0, width, height);
			dashboardVisaRequested.draw(graphics2d3, rectangle2d3);
			Image chartImage3 = Image.getInstance(template3);
			document.add(chartImage3);
			graphics2d3.dispose();

		//	document.add(createFirstTable());
		} catch (Exception e) {
			e.printStackTrace();
		}

		document.close();
		PrintWriter out = response.getWriter();
		out.write(filePath);
		out.flush();
		// String contextPath = getServletContext().getRealPath(File.separator);

	}

	public static PdfPTable createFirstTable() {

		DatabaseConnection connection = new DatabaseConnection();
		String date = connection.retrieveLatestDate();
		Utility utility = new Utility();
		String startDate = utility.getStartDateOfMonth(date);
		String endDate = utility.getEndDateOfMonth(startDate);
		ArrayList<RankingDoctor> list = connection.retrieveAllRankingDoctorDashBoard(startDate, endDate);

		// a table with three columns
		PdfPTable table = new PdfPTable(4);
		// the cell object
		PdfPCell cell;
		// we add a cell with colspan 3
		cell = new PdfPCell(new Phrase("Ranking"));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Name"));
		table.addCell(cell);
		// now we add a cell with rowspan 2
		cell = new PdfPCell(new Phrase("Clinic"));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Speciality"));
		table.addCell(cell);
		// we add the four remaining cells with addCell()
		for (int i = 0; i < 5; i++) {
			table.addCell(list.get(i).getRanking() + "");
			table.addCell(list.get(i).getName());
			table.addCell(list.get(i).getClinic());
			table.addCell(list.get(i).getSpeciality());
		}

		return table;
	}

	public static JFreeChart generateBarChartMedical() {

		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		String date = connection.retrieveLatestDate();
		String startDate = utility.getStartDateOfMonth(date);
		String endDate = utility.getEndDateOfMonth(startDate);

		ArrayList<String> list = connection.retrievePastSixMonthRecord("Medical", startDate, endDate);
		LinkedHashMap<String, Integer> pastSixMonth = new LinkedHashMap<>();

		for (int i = 0; i < list.size(); i++) {

			// System.out.println(utility.getMonth(Integer.parseInt(list.get(i).substring(5,
			// 7))));
			String month = utility.getMonth(Integer.parseInt(list.get(i).substring(5, 7)));
			if (pastSixMonth.containsKey(month)) {
				int temp = pastSixMonth.get(month);
				pastSixMonth.put(month, temp + 1);
			} else {
				pastSixMonth.put(month, 1);
			}
		}

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (String key : pastSixMonth.keySet()) {
			dataSet.setValue(pastSixMonth.get(key), " Number of clients", key);
		}

		JFreeChart chart = ChartFactory.createBarChart("Number of Medical Client (Past 6 months)", "Month",
				"Number of clients", dataSet, PlotOrientation.VERTICAL, true, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);
		chart.setTitle(
				new TextTitle("Number of Medical Client (Past 6 months)", new Font("Times New Roman", Font.BOLD, 12)));

		return chart;
	}

	public static JFreeChart generateBarChartVisa() {

		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		String date = connection.retrieveLatestDate();
		String startDate = utility.getStartDateOfMonth(date);
		String endDate = utility.getEndDateOfMonth(startDate);

		ArrayList<String> list = connection.retrievePastSixMonthRecord("Visa", startDate, endDate);
		LinkedHashMap<String, Integer> pastSixMonth = new LinkedHashMap<>();

		for (int i = 0; i < list.size(); i++) {

			// System.out.println(utility.getMonth(Integer.parseInt(list.get(i).substring(5,
			// 7))));
			String month = utility.getMonth(Integer.parseInt(list.get(i).substring(5, 7)));
			if (pastSixMonth.containsKey(month)) {
				int temp = pastSixMonth.get(month);
				pastSixMonth.put(month, temp + 1);
			} else {
				pastSixMonth.put(month, 1);
			}
		}

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (String key : pastSixMonth.keySet()) {
			dataSet.setValue(pastSixMonth.get(key), "Number of clients", key);
		}

		JFreeChart chart = ChartFactory.createBarChart("Number of Visa Client (Past 6 months)", "Month",
				"Number of clients", dataSet, PlotOrientation.VERTICAL, true, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);
		chart.setTitle(
				new TextTitle("Number of Visa Client (Past 6 months)", new Font("Times New Roman", Font.BOLD, 12)));

		return chart;
	}

	public static JFreeChart generateBarChartVisaRequested() {
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Client> list = connection.retrieveAllClientListVisa();
		LinkedHashMap<String, Integer> visaTypeList = new LinkedHashMap<>();

		for (int i = 0; i < list.size(); i++) {
			Client c = list.get(i);
			if (c.getVisaType().length() > 0) {
				if (visaTypeList.containsKey(c.getVisaType())) {
					int temp = visaTypeList.get(c.getVisaType());
					visaTypeList.put(c.getVisaType(), temp + 1);
				} else {
					visaTypeList.put(c.getVisaType(), 1);
				}
			}
			// System.out.println(visaTypeList.keySet());
		}

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (String key : visaTypeList.keySet()) {
			dataSet.setValue(visaTypeList.get(key), "", key);
		}

		JFreeChart chart = ChartFactory.createBarChart("Type of Visa Requested", "", "", dataSet,
				PlotOrientation.HORIZONTAL, false, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);
		chart.setTitle(new TextTitle("Type of Visa Requested", new Font("Times New Roman", Font.BOLD, 12)));

		return chart;
	}

}
