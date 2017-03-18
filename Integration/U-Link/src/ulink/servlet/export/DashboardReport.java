package ulink.servlet.export;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import ulink.constructor.Client;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int width = 300;
		int height = 200;
		
		PdfWriter writer = null;

		JFreeChart dashboardMedical = generateBarChartMedical();
		JFreeChart dashboardVisa = generateBarChartVisa();
		JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();

		Document document = new Document();

		try {
			
			Date date = new Date();
			String pdfFileName  = "dashboard.pdf";
			String home = System.getProperty("user.home");
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=" + pdfFileName);
			writer = PdfWriter.getInstance(document, new FileOutputStream(home +"/Downloads/"+ pdfFileName));
			document.open();
			// Display dashboard for Medical
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
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
			dataSet.setValue(pastSixMonth.get(key), "", key);
		}

		JFreeChart chart = ChartFactory.createBarChart("Number of Medical Client (Past 6 months)", "",
				"Number of clients", dataSet, PlotOrientation.VERTICAL, false, true, true);
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
			dataSet.setValue(pastSixMonth.get(key), "", key);
		}

		JFreeChart chart = ChartFactory.createBarChart("Number of Visa Client (Past 6 months)", "", "Number of clients",
				dataSet, PlotOrientation.VERTICAL, false, true, true);
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
