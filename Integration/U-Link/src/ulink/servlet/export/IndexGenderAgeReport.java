package ulink.servlet.export;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
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

import ulink.constructor.AgeAndGender;
import ulink.logic.TopK;

/**
 * Servlet implementation class IndexGenderAgeReport
 */
@WebServlet("/IndexGenderAgeReport")
public class IndexGenderAgeReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexGenderAgeReport() {
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
		int width = 300;
		int height = 200;
		
		JFreeChart genderAgeReport = generateBarChartGenderAge();
		JFreeChart genderAgeOverall = generateBarChartGenderAgeOverall();
		// JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();

		Document document = new Document();

		try {
			Date date = new Date();
			String pdfFileName =  "GenderAge.pdf";
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
	
	public static JFreeChart generateBarChartGenderAge() {
		ArrayList<AgeAndGender> ageGenderList;
		TopK topk = new TopK();
		ageGenderList = topk.getAgeGenderReport();

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i = 0; i < ageGenderList.size(); i++) {
			AgeAndGender key = ageGenderList.get(i);
			dataSet.addValue(key.getMale(), "Male", key.getAge());
			dataSet.addValue(key.getFemale(), "Female", key.getAge());
		}

		JFreeChart chart = ChartFactory.createBarChart("Gender - Age Report", "", "", dataSet, PlotOrientation.VERTICAL,
				false, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);

		chart.setTitle(new TextTitle("Gender - Age Report", new Font("Times New Roman", Font.BOLD, 12)));

		return chart;
	}

	public static JFreeChart generateBarChartGenderAgeOverall() {

		ArrayList<AgeAndGender> ageGenderList;
		TopK topk = new TopK();
		ageGenderList = topk.getAgeGenderReport();

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i = 0; i < ageGenderList.size(); i++) {
			AgeAndGender key = ageGenderList.get(i);
			dataSet.addValue(key.getTotal(), "", key.getAge());

		}

		JFreeChart chart = ChartFactory.createBarChart("Gender - Age Report", "", "", dataSet, PlotOrientation.VERTICAL,
				false, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);

		chart.setTitle(new TextTitle("Gender - Age Report", new Font("Times New Roman", Font.BOLD, 12)));

		return chart;
	}


}
