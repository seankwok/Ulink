package ulink.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.Document;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import ulink.constructor.AgeAndGender;
import ulink.constructor.Client;
import ulink.constructor.Index;
import ulink.constructor.PersonInCharge;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {
	public static void main(String[] args) {
		writeChartToPDF(300, 200, "C:\\Users\\Sean\\Desktop\\barchart.pdf");
		writeGenderAgePDF(300, 200, "C:\\Users\\Sean\\Desktop\\genderAge.pdf");

		// writeChartToPDF(generatePieChart(), 500, 800,
		// "C:\\Users\\Sean\\Desktop\\piechart.pdf");
	}

	public static void writeIndexVisa(int width, int height, String fileName) {
		PdfWriter writer = null;

		JFreeChart genderAgeReport = generateBarChartIndexVisa();
		JFreeChart genderAgeOverall = generateBarChartIndexVisaByPerson();
		// JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
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
	
	public static void writeIndexMedical(int width, int height, String fileName) {
		PdfWriter writer = null;

		JFreeChart genderAgeReport = generateBarChartIndexMedical();
		JFreeChart genderAgeOverall = generateBarChartIndexMedicalByPerson();
		// JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
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

	public static void writeGenderAgePDF(int width, int height, String fileName) {
		PdfWriter writer = null;

		JFreeChart genderAgeReport = generateBarChartGenderAge();
		JFreeChart genderAgeOverall = generateBarChartGenderAgeOverall();
		// JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
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

	public static void writeChartToPDF(int width, int height, String fileName) {
		PdfWriter writer = null;

		JFreeChart dashboardMedical = generateBarChartMedical();
		JFreeChart dashboardVisa = generateBarChartVisa();
		JFreeChart dashboardVisaRequested = generateBarChartVisaRequested();

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
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

	public static JFreeChart generateBarChartIndexMedical() {

		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		String startDate = "01/01/2016";
		String endDate = "01/01/2017";
		String team = "Medical";
		ArrayList<Index> indexList = connection.retrieveAllIndex(utility.changeDateFormatDatabase(startDate),
				utility.changeDateFormatDatabase(endDate), team);
		LinkedHashMap<Integer, Double> pointSystem = utility.getIndexCount(indexList);

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (Integer key : pointSystem.keySet()) {
			dataSet.setValue(pointSystem.get(key), "", key);
		}

		JFreeChart chart = ChartFactory.createBarChart("Overall results for Medical Team", "", "Number of clients",
				dataSet, PlotOrientation.VERTICAL, false, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);
		chart.setTitle(new TextTitle("Overall results for Medical Team", new Font("Times New Roman", Font.BOLD, 12)));

		return chart;
	}

	public static JFreeChart generateBarChartIndexVisa() {

		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		String startDate = "01/01/2016";
		String endDate = "01/01/2017";
		String team = "Visa";
		ArrayList<Index> indexList = connection.retrieveAllIndex(utility.changeDateFormatDatabase(startDate),
				utility.changeDateFormatDatabase(endDate), team);
		LinkedHashMap<Integer, Double> pointSystem = utility.getIndexCount(indexList);

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (Integer key : pointSystem.keySet()) {
			dataSet.setValue(pointSystem.get(key), "", key);
		}

		JFreeChart chart = ChartFactory.createBarChart("Overall results for Visa Team", "", "Number of clients",
				dataSet, PlotOrientation.VERTICAL, false, true, true);
		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis axis = p.getRangeAxis();

		CategoryAxis axis2 = p.getDomainAxis();
		Font font = new Font("Dialog", Font.PLAIN, 6);
		axis.setTickLabelFont(font);
		axis2.setTickLabelFont(font);
		chart.setTitle(new TextTitle("Overall results for Visa Team", new Font("Times New Roman", Font.BOLD, 12)));

		return chart;
	}

	
	public static JFreeChart generateBarChartIndexMedicalByPerson() {

		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		String date = connection.retrieveLatestDate();
		String startDate = utility.getStartDateOfMonth(date);
		String endDate = utility.getEndDateOfMonth(startDate);
		String team = "";
		ArrayList<String> personInChargeList = connection.retrieveAllPersonInCharge();
		ArrayList<PersonInCharge> listAllPIC = new ArrayList<>();

		for (int i = 0; i < personInChargeList.size(); i++) {
			String temp = personInChargeList.get(i);
			ArrayList<Index> indexList = connection.retrieveAllIndexByPerson(
					utility.changeDateFormatDatabase(startDate), utility.changeDateFormatDatabase(endDate), team, temp);
			LinkedHashMap<Integer, Double> pointSystem = utility.getIndexCount(indexList);
			listAllPIC.add(new PersonInCharge(temp, pointSystem));
		}

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i = 0; i < listAllPIC.size(); i++) {
			PersonInCharge personInCharge = listAllPIC.get(i);
			for (Integer key : personInCharge.getPointSystem().keySet()) {
				dataSet.setValue(personInCharge.getPointSystem().get(key), "", personInCharge.getName());
			}
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

	public static JFreeChart generateBarChartIndexVisaByPerson() {

		DatabaseConnection connection = new DatabaseConnection();
		Utility utility = new Utility();
		String date = connection.retrieveLatestDate();
		String startDate = utility.getStartDateOfMonth(date);
		String endDate = utility.getEndDateOfMonth(startDate);
		String team = "Visa";
		ArrayList<String> personInChargeList = connection.retrieveAllPersonInCharge();
		ArrayList<PersonInCharge> listAllPIC = new ArrayList<>();

		for (int i = 0; i < personInChargeList.size(); i++) {
			String temp = personInChargeList.get(i);
			ArrayList<Index> indexList = connection.retrieveAllIndexByPerson(
					utility.changeDateFormatDatabase(startDate), utility.changeDateFormatDatabase(endDate), team, temp);
			LinkedHashMap<Integer, Double> pointSystem = utility.getIndexCount(indexList);
			listAllPIC.add(new PersonInCharge(temp, pointSystem));
		}

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (int i = 0; i < listAllPIC.size(); i++) {
			PersonInCharge personInCharge = listAllPIC.get(i);
			for (Integer key : personInCharge.getPointSystem().keySet()) {
				dataSet.setValue(personInCharge.getPointSystem().get(key), "", personInCharge.getName());
			}
		}
		
		
		JFreeChart chart = ChartFactory.createBarChart("Number of Visa Client (Past 6 months)", "",
				"Number of clients", dataSet, PlotOrientation.VERTICAL, false, true, true);
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