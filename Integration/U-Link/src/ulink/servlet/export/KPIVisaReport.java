package ulink.servlet.export;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import ulink.constructor.KPI;
import ulink.logic.TopK;
import ulink.logic.Utility;

/**
 * Servlet implementation class KPIReportExport
 */
@WebServlet("/KPIVisaReport")
public class KPIVisaReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KPIVisaReport() {
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
		
		PdfWriter writer = null;
		int width = 500;
		int height = 400;
		final String TMP_DIR_PATH = "/KPIVisaReport.pdf";
		final String image_path = "/ulink.jpg";
		String filePath = null;
		
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		String thisYearLastMonth = request.getParameter("thisYearLastMonth");
		String lastYearThisMonth = request.getParameter("lastYearThisMonth");
		
		
		// JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();
		
		
		
		
		JFreeChart genderAgeReport = generateBarChartKPIVisaMonth(type, date, thisYearLastMonth, lastYearThisMonth);
		JFreeChart genderAgeOverall = generateBarChartKPIVisaYear(type, date, thisYearLastMonth, lastYearThisMonth);
		Document document = new Document();

		try {
			
			filePath = getServletContext().getRealPath(TMP_DIR_PATH);

			String imagePath = getServletContext().getRealPath(image_path);
			// ByteArrayOutputStream baos = new ByteArrayOutputStream();
			response.addHeader("Content-Disposition", "attachment;  filename=" + filePath);
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			//System.out.println(filePath);
			document.open();
			// Display dashboard for Medical
			Image img = Image.getInstance(imagePath);
			img.scaleAbsolute(60f, 60f);
			img.setAlignment(img.ALIGN_CENTER);
			document.add(img);
			Paragraph p = new Paragraph("ULINK REPORTING SYSTEM – KPI VISA REPORT");
			p.setAlignment(p.ALIGN_CENTER);
			document.add(p);

			// Display dashboard for Medical
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
			genderAgeReport.draw(graphics2d, rectangle2d);
			Image chartImage = Image.getInstance(template);
			document.add(chartImage);
			graphics2d.dispose();

			// Display dashboard for Visa
			PdfContentByte contentByte2 = writer.getDirectContent();
			PdfTemplate template2 = contentByte2.createTemplate(width, height);
			Graphics2D graphics2d2 = template2.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d2 = new Rectangle2D.Double(0, 0, width, height);
			genderAgeOverall.draw(graphics2d2, rectangle2d2);
			Image chartImage2 = Image.getInstance(template2);
			document.add(chartImage2);
			graphics2d2.dispose();

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		
	}
	
	public static JFreeChart generateBarChartKPIVisaMonth(String type, String date, String thisYearLastMonth, String lastYearThisMonth) {

		TopK topk = new TopK();
		int year = Integer.parseInt(date.substring(0, 4));
		String month = date.substring(5);
		//System.out.println("TEST " + month);
		Utility utility = new Utility();
		String startDate = utility.getStartDateOfMonth(year+"-"+month+"-"+"01");
		String endDate = utility.getEndDateOfMonth(year+"-"+month+"-"+"01");
		int lastMonthDate = Integer.parseInt(thisYearLastMonth.substring(5));
		int lastMonthYear = Integer.parseInt(thisYearLastMonth.substring(0,4));
		int lastYearDate = Integer.parseInt(lastYearThisMonth.substring(0, 4));
		String startDatelastMonth = utility.getStartDateOfMonth(lastMonthYear+"-"+lastMonthDate+"-"+"01");
		String endDatelastMonth = utility.getEndDateOfMonth(lastMonthYear+"-"+lastMonthDate+"-"+"01");
		String startDateLastYear = utility.getStartDateOfMonth(lastYearDate+"-"+month+"-"+"01");
		String endDatelastYear = utility.getEndDateOfMonth(lastYearDate+"-"+month+"-"+"01");
		
		//System.out.println(startDate + " " + endDate);
		
		KPI kpi = topk.getKPIVisa(type, startDate,endDate);
		KPI lastMonth = topk.getKPIVisa(type, startDatelastMonth,endDatelastMonth);
	//	KPI lastyear = topk.getKPIVisa(type,startDateLastYear,endDatelastYear);
		//KPI LMLY = topk.getKPI(type,lastYearLastMonth);
		
		ArrayList<KPI> kpiList = new ArrayList<>();
		kpiList.add(kpi);
		kpiList.add(lastMonth);
		double outChange = 0;
		double inChange = 0;
		
		if (lastMonth.getInPatient() != 0){
			inChange = (1.0*kpi.getInPatient()-lastMonth.getInPatient())/lastMonth.getInPatient()*100;
		}
		
		if (lastMonth.getOutPatient() != 0){
			outChange = (1.0*kpi.getOutPatient()-lastMonth.getOutPatient())/lastMonth.getOutPatient()*100;
		}
		kpiList.add(new KPI("Increase\\Decrease (%)",Math.round(inChange),Math.round(outChange)));

		
		
		
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i= 0; i < kpiList.size()-1; i++) {
			KPI temp = kpiList.get(i);
			
			dataSet.setValue(temp.getInPatient(), temp.getDate(), "inPaitent");
			dataSet.setValue(temp.getOutPatient(), temp.getDate(), "outPaitent");
		}

		JFreeChart chart = ChartFactory.createBarChart("Overall results for Medical Team", "", "Number of clients",
				dataSet, PlotOrientation.VERTICAL, false, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);
		chart.setTitle(new TextTitle("Overall results for Visa Team in month", new Font("Times New Roman", Font.BOLD, 12)));
		final CategoryItemRenderer renderer = p.getRenderer();

		renderer.setSeriesItemLabelGenerator(0,
				new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));
		renderer.setSeriesItemLabelGenerator(1,
				new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));

		renderer.setSeriesItemLabelsVisible(0, true);
		renderer.setSeriesItemLabelsVisible(1, true);

		return chart;
	}
	
	//KPI medical current year vs last year same month
	public static JFreeChart generateBarChartKPIVisaYear(String type, String date, String thisYearLastMonth, String lastYearThisMonth) {


		TopK topk = new TopK();
		int year = Integer.parseInt(date.substring(0, 4));
		String month = date.substring(5);
		//System.out.println("TEST " + month);
		Utility utility = new Utility();
		String startDate = utility.getStartDateOfMonth(year+"-"+month+"-"+"01");
		String endDate = utility.getEndDateOfMonth(year+"-"+month+"-"+"01");
		int lastMonthDate = Integer.parseInt(thisYearLastMonth.substring(5));
		int lastMonthYear = Integer.parseInt(thisYearLastMonth.substring(0,4));
		int lastYearDate = Integer.parseInt(lastYearThisMonth.substring(0, 4));
		String startDatelastMonth = utility.getStartDateOfMonth(lastMonthYear+"-"+lastMonthDate+"-"+"01");
		String endDatelastMonth = utility.getEndDateOfMonth(lastMonthYear+"-"+lastMonthDate+"-"+"01");
		String startDateLastYear = utility.getStartDateOfMonth(lastYearDate+"-"+month+"-"+"01");
		String endDatelastYear = utility.getEndDateOfMonth(lastYearDate+"-"+month+"-"+"01");
		
		//System.out.println(startDate + " " + endDate);
		
		KPI kpi = topk.getKPIVisa(type, startDate,endDate);
		KPI lastMonth = topk.getKPIVisa(type, startDatelastMonth,endDatelastMonth);
		KPI lastyear = topk.getKPIVisa(type,startDateLastYear,endDatelastYear);
		//KPI LMLY = topk.getKPI(type,lastYearLastMonth);
		
		ArrayList<KPI> kpiList = new ArrayList<>();
		kpiList.add(kpi);
	//	kpiList.add(lastMonth);
		double outChange = 0;
		double inChange = 0;
	
	//	kpiList.add(new KPI("Increase\\Decrease (%)",Math.round(inChange),Math.round(outChange)));
		//kpiList.add(kpi);
		kpiList.add(lastyear);
		
		if (lastyear.getInPatient() != 0){
			inChange = (1.0*kpi.getInPatient()-lastyear.getInPatient())/lastyear.getInPatient()*100;
		} else {
			inChange = 0;
		}
		
		if (lastyear.getOutPatient() != 0){
			outChange = (1.0*kpi.getOutPatient()-lastyear.getOutPatient())/lastyear.getOutPatient()*100;
		} else {
			outChange = 0;
		}
		kpiList.add(new KPI("Increase\\Decrease (%)",Math.round(inChange),Math.round(outChange)));

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i= 0; i < kpiList.size()-1; i++) {
			KPI temp = kpiList.get(i);
			//System.out.println(temp.getInPatient()+ "ROWS");	
			
			dataSet.setValue(temp.getInPatient(), temp.getDate().substring(temp.getDate().length()-4), "inPaitent");
			dataSet.setValue(temp.getOutPatient(), temp.getDate().substring(temp.getDate().length()-4), "outPaitent");
		}

		JFreeChart chart = ChartFactory.createBarChart("Overall results for Visa Team year", "", "Number of clients",
				dataSet, PlotOrientation.VERTICAL, false, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);
		chart.setTitle(new TextTitle("Overall results for Visa Team", new Font("Times New Roman", Font.BOLD, 12)));
		final CategoryItemRenderer renderer = p.getRenderer();

		renderer.setSeriesItemLabelGenerator(0,
				new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));
		renderer.setSeriesItemLabelGenerator(1,
				new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));

		renderer.setSeriesItemLabelsVisible(0, true);
		renderer.setSeriesItemLabelsVisible(1, true);

		return chart;
	}

}
