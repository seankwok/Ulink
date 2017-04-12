package ulink.servlet.export;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
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
		doGet(request, response);
		PdfWriter writer = null;
		int width = 500;
		int height = 400;
		final String TMP_DIR_PATH = "/AgeGenderReport.pdf";
		final String image_path = "/ulink.jpg";
		String filePath = null;
		JFreeChart genderAgeReport = generateBarChartGenderAge();
		JFreeChart genderAgeOverall = generateBarChartGenderAgeOverall();
		// JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();

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
			Paragraph p = new Paragraph("ULINK REPORTING SYSTEM – GENDER AGE REPORT");
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

		JFreeChart chart = ChartFactory.createBarChart("Age Report", "Age", "Percentage Per Age", dataSet, PlotOrientation.VERTICAL,
				true, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);

		chart.setTitle(new TextTitle("Gender - Age Report", new Font("Times New Roman", Font.BOLD, 12)));
		final CategoryItemRenderer renderer = p.getRenderer();

		renderer.setSeriesItemLabelGenerator(1,
				new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));
		renderer.setSeriesItemLabelGenerator(0,
				new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));

		renderer.setSeriesItemLabelsVisible(0, true);
		renderer.setSeriesItemLabelsVisible(1, true);

		return chart;
	}

	public static JFreeChart generateBarChartGenderAgeOverall() {

		ArrayList<AgeAndGender> ageGenderList;
		TopK topk = new TopK();
		ageGenderList = topk.getAgeGenderReport();

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i = 0; i < ageGenderList.size(); i++) {
			AgeAndGender key = ageGenderList.get(i);
			dataSet.addValue(key.getTotal(), "Total", key.getAge());

		}

		JFreeChart chart = ChartFactory.createBarChart("Age Report", "Age", "Percentage Per Age", dataSet, PlotOrientation.VERTICAL,
				true, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);

		chart.setTitle(new TextTitle("Gender - Age Report", new Font("Times New Roman", Font.BOLD, 12)));
		final CategoryItemRenderer renderer = p.getRenderer();

		renderer.setSeriesItemLabelGenerator(0,
				new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));

		renderer.setSeriesItemLabelsVisible(0, true);

		return chart;
	}

}
