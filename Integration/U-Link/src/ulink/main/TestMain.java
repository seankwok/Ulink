package ulink.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ulink.constructor.RankingReferredBy;
import ulink.constructor.RankingSpecialty;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

public class TestMain {

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		// TODO Auto-generated method stub

		DatabaseConnection database = new DatabaseConnection();
		ArrayList<RankingSpecialty> SpecialtyList;

		Utility utility = new Utility();
		SpecialtyList = database.retrieveAllRankingSpecialty("2015-01-01", "2017-04-01");

		System.out.println(SpecialtyList.size());

		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		// This data needs to be written (Object[])
		Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();

		// data.put("1", new Object[] { "S/N", "Name", "Gender", "Email" });

		for (int i = 0; i < SpecialtyList.size(); i++) {
			RankingSpecialty temp = SpecialtyList.get(i);

			data.put(i, new Object[] { temp.getCount(), temp.getSpecialty() });
		}

		// Iterate over data and write to sheet
		Set<Integer> keyset = data.keySet();

		int rownum = 0;
		for (Integer key : keyset) {
			// create a row of excelsheet
			Row row = sheet.createRow(rownum++);

			// get object array of prerticuler key
			Object[] objArr = data.get(key);

			int cellnum = 0;

			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				}
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
