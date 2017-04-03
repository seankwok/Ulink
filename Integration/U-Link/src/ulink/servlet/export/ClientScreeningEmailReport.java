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
		DatabaseConnection connection = new DatabaseConnection();
		System.out.println("Enter email");
		// int age = Integer.parseInt(request.getParameter("age"));
		// String gender = request.getParameter("gender");
		// String type = request.getParameter("type");
		int ID = Integer.parseInt(request.getParameter("ID"));
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		//String direction = request.getParameter("direction");
		session.setAttribute("ID", ID);
		Condition condition = connection.retrieveAllConditionByID(ID);

		ArrayList<Client> clientList = connection.retrieveAllClientListEmail("age", "ASC");
		ArrayList<ClientByIllness> clientByIllnessList = new ArrayList<>();
		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			if ((condition.getType().toLowerCase().equals("male") || condition.getType().toLowerCase().equals("female"))
					&& client.getEmail().length() > 0) {
				if (client.getAge() >= condition.getAgeRequired()
						&& client.getGender().toLowerCase().equals(condition.getType().toLowerCase())) {
					clientByIllnessList
							.add(new ClientByIllness(client.getClientName(), client.getAge(), client.getEmail(),
									client.getGender(), condition.getScreening(), condition.getConditionName(),
									connection.retrieveLatestDateSend(client.getClientName(), condition.getScreening()),
									client.getFollowUpPerson()));
					// System.out.println("TQEQWEQWEQW " +
					// connection.retrieveLatestDateSend(client.getClientName(),condition.getScreening()));
				}
			} else if (client.getAge() <= 2 && client.getDateOfBirth().length() > 0) {
				String date = client.getDateOfBirth();
				// System.out.println(date + "qwewqewqewq");
				int day = Integer.parseInt(date.substring(0, 2));
				int month = Integer.parseInt(date.substring(3, 5)) - 1;
				int year = Integer.parseInt(date.substring(6)) + 1900;

				Date current = new Date();
				Date dob = new Date(day, month, year);

				int months = (current.getMonth() - dob.getMonth()) + (current.getYear() - dob.getYear()) * 12;

				if (months >= condition.getAgeRequired()) {
					clientByIllnessList
							.add(new ClientByIllness(client.getClientName(), client.getAge(), client.getEmail(),
									client.getGender(), condition.getScreening(), condition.getConditionName(),
									connection.retrieveLatestDateSend(client.getClientName(), condition.getScreening()),
									client.getFollowUpPerson()));

				}
			}
		}

		// TODO Auto-generated method stub
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("EmployeeData");

		// This data needs to be written (Object[])
		Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
		data.put(0, new Object[] { "S/N", "Name", "Gender", "Email" });
		for (int i = 0; i < clientByIllnessList.size(); i++) {
			data.put(i+1, new Object[] { i+1, clientByIllnessList.get(i).getName(), clientByIllnessList.get(i).getGender(), clientByIllnessList.get(i).getEmail() });
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
			String filePath = getServletContext().getRealPath("/ClientScreeningEmail.xlsx");
			response.addHeader("Content-Disposition", "attachment;  filename=" + filePath);
			
			FileOutputStream out = new FileOutputStream(filePath);
			workbook.write(out);
			out.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
