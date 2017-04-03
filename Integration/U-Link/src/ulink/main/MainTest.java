package ulink.main;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import ulink.constructor.KPI;
import ulink.constructor.PersonInCharge;
import ulink.constructor.RankingDoctor;
import ulink.dao.DatabaseConnection;
import ulink.logic.TopK;
import ulink.logic.Utility;

public class MainTest {
	public static void main(String[] args) throws IOException {
		DatabaseConnection database = new DatabaseConnection();
		ArrayList<RankingDoctor> doctorList;
		//String startDate = request.getParameter("startDate");
		//String endDate = request.getParameter("endDate");
		//System.out.println(startDate);
		Utility utility = new Utility();
		doctorList = database.retrieveAllRankingDoctor("2015/04/01","2017/01/01");
		System.out.println(doctorList.size());
	}

}