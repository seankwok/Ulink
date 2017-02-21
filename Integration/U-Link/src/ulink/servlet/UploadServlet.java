package ulink.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonObject;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String TMP_DIR_PATH = "/MyTempFiles";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "/MySavedFiles";
	private File destinationDir;
	// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	// OR Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	// System.out.println(d.toString());

	public UploadServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		String realPath = getServletContext().getRealPath(TMP_DIR_PATH);
		tmpDir = new File(realPath);
		if (!tmpDir.isDirectory()) {
			throw new ServletException(TMP_DIR_PATH + " is not a directory");
		}

		realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH + " is not a directory");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// PrintWriter to send the JSON response back
		PrintWriter out = response.getWriter();
		// String uploadedTime = request.getParameter("uploadedTime");

		// set content type and header attributes
		// response.setContentType("UTF-8");
		// response.setHeader("Content-disposition",
		// "attachment;filename=myExcel.xls");
		// response.setContentType("application/vnd.ms-excel").
		// response.setContentType("APPLICATION/OCTET-STREAM");
		// System.out.println(response.getContentType());
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		DatabaseConnection connection = new DatabaseConnection();
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();

		// Set the size threshold, above which content will be stored on disk.
		fileItemFactory.setSizeThreshold(1 * 1024 * 1024); // 1 MB

		// Set the temporary directory to store the uploaded files of size above
		// threshold.
		fileItemFactory.setRepository(tmpDir);

		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		// JsonObject myObj = new JsonObject();

		String fileName = null;
		String fullName = null;
		File file = null;

		try {

			// Parse the request
			List items = uploadHandler.parseRequest(request);
			Iterator iterator = items.iterator();
			while (iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();

				// Handle Form Fields
				if (item.isFormField()) {
					System.out.println("Field Name = " + item.getFieldName() + ", Value = " + item.getString());
					if (item.getFieldName().trim().equalsIgnoreCase("filename")) {
						fileName = item.getString().trim();
					}
				}

				// Handle Uploaded files.
				else {
					System.out.println("Field Name = " + item.getFieldName() + ", File Name = " + item.getName()
							+ ", Content type = " + item.getContentType() + ", File Size = " + item.getSize());
					fullName = item.getName().trim();

					// Write file to the ultimate location.
					file = new File(destinationDir, item.getName());
					item.write(file);
				}

			}

			int count = -1;
			String extension = FilenameUtils.getExtension(fullName);
			if (extension.trim().equalsIgnoreCase("xlsx")) {
				System.out.println("test1");
				count = processExcelFile(file);
				// System.out.print(count);
				// out.write(count);
				// out.flush();
				TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

				Date date2 = new Date();

				connection.addDateTime(date2.toString());
				// PrintWriter out = response.getWriter();

				out.write("" + count);

				// request.getRequestDispatcher("./upload.html").forward(request,
				// response);
				out.flush();
				return;

			} else if (extension.trim().equalsIgnoreCase("xls")) {
				// process your binary excel file
				System.out.println("test2");
				count = test(file);
				TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

				Date date2 = new Date();

				connection.addDateTime(date2.toString());
				// out.write(count);
				// out.flush();
				// response.sendRedirect("./upload.html");

				out.write("" + count);

				// request.getRequestDispatcher("./upload.html").forward(request,
				// response);
				out.flush();
				return;
			} else {

				// request.getRequestDispatcher("./upload.html").forward(request,
				// response);
				out.write("Not excel file");
				out.flush();

				// response.sendRedirect("./upload.html");
				return;
			}

		} catch (FileUploadException ex) {
			log("Error encountered while parsing the request", ex);
			// myObj.addProperty("success", false);
			// out.println(myObj.toString());
			out.write("Not excel file");
			out.flush();
			return;
		} catch (Exception ex) {
			log("Error encountered while uploading file", ex);
			// myObj.addProperty("success", false);

			// out.println(myObj.toString());
			out.write("Not excel file");
			out.flush();
			return;
		}

	}

	private int test(File file) throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFCell cell;
		int count = 0;
		Utility utility = new Utility();
		DatabaseConnection connection = new DatabaseConnection();
		Iterator<Row> rowIter = sheet.rowIterator();
		rowIter.next();

		ArrayList<Client> clientList = new ArrayList<>();

		int ID = 0;
		int accountIDRow = 0;
		int clientOwnerRow = 0;
		int clientNameRow = 0;
		int clientTypeRow = 0;
		int companyRow = 0;
		int nationalityRow = 0;
		int genderRow = 0;
		int dateOfBirthRow = 0;
		int emailRow = 0;
		int medicalRow = 0;
		int mainDiagnosisRow = 0;
		int referredByRow = 0;
		int PICRow = 0;
		int appointmentRow = 0;
		int doctorRow = 0;
		int specialtyRow = 0;
		int clinicRow = 0;
		int otherDoctorRow = 0;
		int followUpPersonRow = 0;
		int followUpPICRow = 0;
		int hospitalAdmittedRow = 0;
		int logRow = 0;
		int claimRow = 0;
		int visaRequestByRow = 0;
		int visaRow = 0;
		int visaTypeRow = 0;
		int visaType2Row = 0;
		int billingCityRow = 0;
		int billingCodeRow = 0;
		int billingCountryRow = 0;
		int billingStateRow = 0;
		int billingStreetRow = 0;
		int createdTimeRow = 0;
		int phoneRow = 0;

		while (rowIter.hasNext()) {
			String accountID = "";
			String clientOwner = "";
			String clientName = "";
			String clientType = "";
			String company = "";
			String nationality = "";
			String gender = "";
			String dateOfBirth = "";
			String email = "";
			String medical = "";
			String mainDiagnosis = "";
			String referredBy = "";
			String PIC = "";
			String appointment = "";
			String doctor = "";
			String specialty = "";
			String clinic = "";
			String otherDoctor = "";
			String followUpPerson = "";
			String followUpPIC = "";
			String hospitalAdmitted = "";
			String log = "";
			String claim = "";
			String visaRequestBy = "";
			String visa = "";
			String visaType = "";
			String visaType2 = "";
			String billingCity = "";
			String billingCode = "";
			String billingCountry = "";
			String billingState = "";
			String billingStreet = "";
			String createdTime = "";
			String phone = "";

			ID++;

			HSSFRow myRow = (HSSFRow) rowIter.next();
			Iterator<Cell> cellIter = myRow.cellIterator();

			int numberOfRow = myRow.getPhysicalNumberOfCells();

			if (numberOfRow == 35) {
				for (int i = 0; i < numberOfRow; i++) {

					HSSFCell temp = (HSSFCell) cellIter.next();
					// System.out.println(temp.toString());
					// int row = myRow.getRowNum();
					// System.out.println(temp.toString() + " " +
					// temp.getColumnIndex() + " ROW");
					if (!temp.toString().equals("")) {
						if (temp.toString().equals("ACCOUNTID") || temp.getColumnIndex() == accountIDRow) {

							accountIDRow = temp.getColumnIndex();
							accountID = temp.toString();

						} else if (temp.toString().equals("Client Name") || temp.getColumnIndex() == clientNameRow) {
							clientNameRow = temp.getColumnIndex();
							clientName = temp.toString();
						} else if (temp.toString().contains("Appointment") || temp.getColumnIndex() == appointmentRow) {

							appointmentRow = temp.getColumnIndex();
							appointment = temp.toString();
						} else if (temp.toString().equals("Billing City") || temp.getColumnIndex() == billingCityRow) {
							billingCityRow = temp.getColumnIndex();
							billingCity = temp.toString();
						} else if (temp.toString().equals("Billing Code") || temp.getColumnIndex() == billingCodeRow) {
							billingCodeRow = temp.getColumnIndex();
							billingCode = temp.toString();
						} else if (temp.toString().equals("Billing Country")
								|| temp.getColumnIndex() == billingCountryRow) {
							billingCountryRow = temp.getColumnIndex();
							billingCountry = temp.toString();
						} else if (temp.toString().equals("Billing State")
								|| temp.getColumnIndex() == billingStateRow) {
							billingStateRow = temp.getColumnIndex();
							billingState = temp.toString();
						} else if (temp.toString().equals("Billing Street")
								|| temp.getColumnIndex() == billingStreetRow) {
							billingStreetRow = temp.getColumnIndex();
							billingStreet = temp.toString();
						} else if (temp.toString().equals("Client Owner") || temp.getColumnIndex() == clientOwnerRow) {
							clientOwnerRow = temp.getColumnIndex();
							clientOwner = temp.toString();
						} else if (temp.toString().equals("Client type") || temp.getColumnIndex() == clientTypeRow) {
							clientTypeRow = temp.getColumnIndex();
							clientType = temp.toString();
						} else if (temp.toString().equals("Clinic") || temp.getColumnIndex() == clinicRow) {
							clinicRow = temp.getColumnIndex();
							clinic = temp.toString();
						} else if (temp.toString().equals("Company (for employee only)")
								|| temp.getColumnIndex() == companyRow) {
							companyRow = temp.getColumnIndex();
							company = temp.toString();
						} else if (temp.toString().equals("Created Time") || temp.getColumnIndex() == createdTimeRow) {
							createdTimeRow = temp.getColumnIndex();
							createdTime = temp.toString();
						} else if (temp.toString().equals("Date of birth") || temp.getColumnIndex() == dateOfBirthRow) {
							dateOfBirthRow = temp.getColumnIndex();
							if (temp.toString().equals("Date of birth")) {
								dateOfBirth = "10/10/2000";
							} else {
								dateOfBirth = temp.toString();
							}

							// System.out.println(dateOfBirth);
						} else if (temp.toString().equals("Doctor") || temp.getColumnIndex() == doctorRow) {
							doctorRow = temp.getColumnIndex();
							doctor = temp.toString();
						} else if (temp.toString().equals("Email") || temp.getColumnIndex() == emailRow) {
							emailRow = temp.getColumnIndex();
							email = temp.toString();
						} else if (temp.toString().equals("Follow up person")
								|| temp.getColumnIndex() == followUpPersonRow) {
							followUpPersonRow = temp.getColumnIndex();
							followUpPerson = temp.toString();
						} else if (temp.toString().equals("Follow up PIC") || temp.getColumnIndex() == followUpPICRow) {
							followUpPICRow = temp.getColumnIndex();
							followUpPIC = temp.toString();
						} else if (temp.toString().equals("Gender") || temp.getColumnIndex() == genderRow) {
							genderRow = temp.getColumnIndex();
							gender = temp.toString();
						} else if (temp.toString().equals("Hospital admitted")
								|| temp.getColumnIndex() == hospitalAdmittedRow) {
							hospitalAdmittedRow = temp.getColumnIndex();
							hospitalAdmitted = temp.toString();
						} else if (temp.toString().equals("LOG - If yes, please tick")
								|| temp.getColumnIndex() == logRow) {
							logRow = temp.getColumnIndex();
							log = temp.toString();
						} else if (temp.toString().equals("Medical - If yes, please tick")
								|| temp.getColumnIndex() == medicalRow) {
							medicalRow = temp.getColumnIndex();
							medical = temp.toString();
						} else if (temp.toString().equals("Nationality") || temp.getColumnIndex() == nationalityRow) {
							nationalityRow = temp.getColumnIndex();
							nationality = temp.toString();
						} else if (temp.toString().equals("Other doctor") || temp.getColumnIndex() == otherDoctorRow) {
							otherDoctorRow = temp.getColumnIndex();
							otherDoctor = temp.toString();
						} else if (temp.toString().equals("Phone") || temp.getColumnIndex() == phoneRow) {
							phoneRow = temp.getColumnIndex();
							phone = temp.toString();
						} else if (temp.toString().equals("PIC") || temp.getColumnIndex() == PICRow) {
							PICRow = temp.getColumnIndex();
							PIC = temp.toString();
						} else if (temp.toString().equals("Referred by") || temp.getColumnIndex() == referredByRow) {
							referredByRow = temp.getColumnIndex();
							referredBy = temp.toString();
						} else if (temp.toString().equals("Specialty") || temp.getColumnIndex() == specialtyRow) {
							specialtyRow = temp.getColumnIndex();
							specialty = temp.toString();
						} else if (temp.toString().equals("Ulink Can Claim") || temp.getColumnIndex() == claimRow) {
							claimRow = temp.getColumnIndex();
							claim = temp.toString();
						} else if (temp.toString().equals("Visa - If yes, please tick-")
								|| temp.getColumnIndex() == visaRow) {
							visaRow = temp.getColumnIndex();
							visa = temp.toString();
						} else if (temp.toString().equals("Visa requested by")
								|| temp.getColumnIndex() == visaRequestByRow) {
							visaRequestByRow = temp.getColumnIndex();
							visaRequestBy = temp.toString();
						} else if (temp.toString().equals("Visa type") || temp.getColumnIndex() == visaTypeRow) {
							visaTypeRow = temp.getColumnIndex();
							visaType = temp.toString();
						} else if (temp.toString().equals("Visa type 2") || temp.getColumnIndex() == visaType2Row) {
							visaType2Row = temp.getColumnIndex();
							visaType2 = temp.toString();
						} else if (temp.toString().equals("Main Diagnosis")
								|| temp.getColumnIndex() == mainDiagnosisRow) {
							mainDiagnosisRow = temp.getColumnIndex();
							mainDiagnosis = temp.toString();
						}

					}
				}
				// System.out.print(clientName);
				if (!accountID.equals("") && !dateOfBirth.equals("")) {
					clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company, nationality,
							gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC, appointment, doctor,
							specialty, clinic, otherDoctor, followUpPerson, followUpPIC, hospitalAdmitted, log, claim,
							visa, visaRequestBy, visaType, visaType2, utility.getAge(dateOfBirth), billingCity,
							billingCode, billingCountry, billingState, billingStreet, createdTime, phone));
					// System.out.println(clientList.get(clientList.size() -
					// 1).getClientName());
					count++;
				} else if (!accountID.equals("")) {
					clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company, nationality,
							gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC, appointment, doctor,
							specialty, clinic, otherDoctor, followUpPerson, followUpPIC, hospitalAdmitted, log, claim,
							visa, visaRequestBy, visaType, visaType2, -1, billingCity, billingCode, billingCountry,
							billingState, billingStreet, createdTime, phone));
					// System.out.println(clientList.get(clientList.size() -
					// 1).getClientName());
					count++;
				}
			}

		}
		connection.createClient(clientList);
		return count;

	}

	private int processExcelFile(File file) {
		ArrayList<Client> clientList = new ArrayList<>();
		DatabaseConnection connection = new DatabaseConnection();
		int count = 0;

		try {
			// Creating Input Stream
			FileInputStream myInput = new FileInputStream(file);

			// Create a workbook using the File System
			XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);

			// Get the first sheet from workbook
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);

			/** We now need something to iterate through the cells. **/
			Iterator<Row> rowIter = mySheet.rowIterator();
			rowIter.next();

			Utility utility = new Utility();

			int ID = 0;
			int accountIDRow = 0;
			int clientOwnerRow = 0;
			int clientNameRow = 0;
			int clientTypeRow = 0;
			int companyRow = 0;
			int nationalityRow = 0;
			int genderRow = 0;
			int dateOfBirthRow = 0;
			int emailRow = 0;
			int medicalRow = 0;
			int mainDiagnosisRow = 0;
			int referredByRow = 0;
			int PICRow = 0;
			int appointmentRow = 0;
			int doctorRow = 0;
			int specialtyRow = 0;
			int clinicRow = 0;
			int otherDoctorRow = 0;
			int followUpPersonRow = 0;
			int followUpPICRow = 0;
			int hospitalAdmittedRow = 0;
			int logRow = 0;
			int claimRow = 0;
			int visaRequestByRow = 0;
			int visaRow = 0;
			int visaTypeRow = 0;
			int visaType2Row = 0;
			int billingCityRow = 0;
			int billingCodeRow = 0;
			int billingCountryRow = 0;
			int billingStateRow = 0;
			int billingStreetRow = 0;
			int createdTimeRow = 0;
			int phoneRow = 0;

			while (rowIter.hasNext()) {
				String accountID = "";
				String clientOwner = "";
				String clientName = "";
				String clientType = "";
				String company = "";
				String nationality = "";
				String gender = "";
				String dateOfBirth = "";
				String email = "";
				String medical = "";
				String mainDiagnosis = "";
				String referredBy = "";
				String PIC = "";
				String appointment = "";
				String doctor = "";
				String specialty = "";
				String clinic = "";
				String otherDoctor = "";
				String followUpPerson = "";
				String followUpPIC = "";
				String hospitalAdmitted = "";
				String log = "";
				String claim = "";
				String visaRequestBy = "";
				String visa = "";
				String visaType = "";
				String visaType2 = "";
				String billingCity = "";
				String billingCode = "";
				String billingCountry = "";
				String billingState = "";
				String billingStreet = "";
				String createdTime = "";
				String phone = "";

				ID++;
				XSSFRow myRow = (XSSFRow) rowIter.next();
				int numberOfRow = myRow.getPhysicalNumberOfCells();

				Iterator<Cell> cellIter = myRow.cellIterator();
				if (numberOfRow == 35) {
					for (int i = 0; i < numberOfRow; i++) {

						XSSFCell temp = (XSSFCell) cellIter.next();
						if (!temp.toString().equals("")) {
							if (temp.toString().equals("ACCOUNTID") || temp.getColumnIndex() == accountIDRow) {

								accountIDRow = temp.getColumnIndex();
								accountID = temp.toString();
								// System.out.println(accountID);
							} else if (temp.toString().equals("Client Name")
									|| temp.getColumnIndex() == clientNameRow) {
								clientNameRow = temp.getColumnIndex();
								clientName = temp.toString();
							} else if (temp.toString().contains("Appointment")
									|| temp.getColumnIndex() == appointmentRow) {

								appointmentRow = temp.getColumnIndex();
								appointment = temp.toString();
							} else if (temp.toString().equals("Billing City")
									|| temp.getColumnIndex() == billingCityRow) {
								billingCityRow = temp.getColumnIndex();
								billingCity = temp.toString();
							} else if (temp.toString().equals("Billing Code")
									|| temp.getColumnIndex() == billingCodeRow) {
								billingCodeRow = temp.getColumnIndex();
								billingCode = temp.toString();
							} else if (temp.toString().equals("Billing Country")
									|| temp.getColumnIndex() == billingCountryRow) {
								billingCountryRow = temp.getColumnIndex();
								billingCountry = temp.toString();
							} else if (temp.toString().equals("Billing State")
									|| temp.getColumnIndex() == billingStateRow) {
								billingStateRow = temp.getColumnIndex();
								billingState = temp.toString();
							} else if (temp.toString().equals("Billing Street")
									|| temp.getColumnIndex() == billingStreetRow) {
								billingStreetRow = temp.getColumnIndex();
								billingStreet = temp.toString();
							} else if (temp.toString().equals("Client Owner")
									|| temp.getColumnIndex() == clientOwnerRow) {
								clientOwnerRow = temp.getColumnIndex();
								clientOwner = temp.toString();
							} else if (temp.toString().equals("Client type")
									|| temp.getColumnIndex() == clientTypeRow) {
								clientTypeRow = temp.getColumnIndex();
								clientType = temp.toString();
							} else if (temp.toString().equals("Clinic") || temp.getColumnIndex() == clinicRow) {
								clinicRow = temp.getColumnIndex();
								clinic = temp.toString();
							} else if (temp.toString().equals("Company (for employee only)")
									|| temp.getColumnIndex() == companyRow) {
								companyRow = temp.getColumnIndex();
								company = temp.toString();
							} else if (temp.toString().equals("Created Time")
									|| temp.getColumnIndex() == createdTimeRow) {
								createdTimeRow = temp.getColumnIndex();
								createdTime = temp.toString();
							} else if (temp.toString().equals("Date of birth")
									|| temp.getColumnIndex() == dateOfBirthRow) {
								dateOfBirthRow = temp.getColumnIndex();
								if (temp.toString().equals("Date of birth")) {
									dateOfBirth = "10/10/2000";
								} else {
									dateOfBirth = temp.toString();
								}

							} else if (temp.toString().equals("Doctor") || temp.getColumnIndex() == doctorRow) {
								doctorRow = temp.getColumnIndex();
								doctor = temp.toString();
							} else if (temp.toString().equals("Email") || temp.getColumnIndex() == emailRow) {
								emailRow = temp.getColumnIndex();
								email = temp.toString();
							} else if (temp.toString().equals("Follow up person")
									|| temp.getColumnIndex() == followUpPersonRow) {
								followUpPersonRow = temp.getColumnIndex();
								followUpPerson = temp.toString();
							} else if (temp.toString().equals("Follow up PIC")
									|| temp.getColumnIndex() == followUpPICRow) {
								followUpPICRow = temp.getColumnIndex();
								followUpPIC = temp.toString();

							} else if (temp.toString().equals("Gender") || temp.getColumnIndex() == genderRow) {
								genderRow = temp.getColumnIndex();
								gender = temp.toString();
							} else if (temp.toString().equals("Hospital admitted")
									|| temp.getColumnIndex() == hospitalAdmittedRow) {
								hospitalAdmittedRow = temp.getColumnIndex();
								hospitalAdmitted = temp.toString();
							} else if (temp.toString().equals("LOG - If yes, please tick")
									|| temp.getColumnIndex() == logRow) {
								logRow = temp.getColumnIndex();
								log = temp.toString();
							} else if (temp.toString().equals("Medical - If yes, please tick")
									|| temp.getColumnIndex() == medicalRow) {
								medicalRow = temp.getColumnIndex();
								medical = temp.toString();
							} else if (temp.toString().equals("Nationality")
									|| temp.getColumnIndex() == nationalityRow) {
								nationalityRow = temp.getColumnIndex();
								nationality = temp.toString();
							} else if (temp.toString().equals("Other doctor")
									|| temp.getColumnIndex() == otherDoctorRow) {
								otherDoctorRow = temp.getColumnIndex();
								otherDoctor = temp.toString();
							} else if (temp.toString().equals("Phone") || temp.getColumnIndex() == phoneRow) {
								phoneRow = temp.getColumnIndex();
								phone = temp.toString();
							} else if (temp.toString().equals("PIC") || temp.getColumnIndex() == PICRow) {
								PICRow = temp.getColumnIndex();
								PIC = temp.toString();
							} else if (temp.toString().equals("Referred by")
									|| temp.getColumnIndex() == referredByRow) {
								referredByRow = temp.getColumnIndex();
								referredBy = temp.toString();
							} else if (temp.toString().equals("Specialty") || temp.getColumnIndex() == specialtyRow) {
								specialtyRow = temp.getColumnIndex();
								specialty = temp.toString();
							} else if (temp.toString().equals("Ulink Can Claim") || temp.getColumnIndex() == claimRow) {
								claimRow = temp.getColumnIndex();
								claim = temp.toString();
							} else if (temp.toString().equals("Visa - If yes, please tick-")
									|| temp.getColumnIndex() == visaRow) {
								visaRow = temp.getColumnIndex();
								visa = temp.toString();
							} else if (temp.toString().equals("Visa requested by")
									|| temp.getColumnIndex() == visaRequestByRow) {
								visaRequestByRow = temp.getColumnIndex();
								visaRequestBy = temp.toString();
							} else if (temp.toString().equals("Visa type") || temp.getColumnIndex() == visaTypeRow) {
								visaTypeRow = temp.getColumnIndex();
								visaType = temp.toString();
							} else if (temp.toString().equals("Visa type 2") || temp.getColumnIndex() == visaType2Row) {
								visaType2Row = temp.getColumnIndex();
								visaType2 = temp.toString();
							} else if (temp.toString().equals("Main Diagnosis")
									|| temp.getColumnIndex() == mainDiagnosisRow) {
								mainDiagnosisRow = temp.getColumnIndex();
								mainDiagnosis = temp.toString();
							}

						}
					}

					// System.out.print(clientName);
					if (!accountID.equals("") && !dateOfBirth.equals("")) {
						clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company,
								nationality, gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC,
								appointment, doctor, specialty, clinic, otherDoctor, followUpPerson, followUpPIC,
								hospitalAdmitted, log, claim, visa, visaRequestBy, visaType, visaType2,
								utility.getAge(dateOfBirth), billingCity, billingCode, billingCountry, billingState,
								billingStreet, createdTime, phone));
						// System.out.println(clientList.get(clientList.size() -
						// 1).getClientName());
						count++;
					} else if (!accountID.equals("")) {
						clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company,
								nationality, gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC,
								appointment, doctor, specialty, clinic, otherDoctor, followUpPerson, followUpPIC,
								hospitalAdmitted, log, claim, visa, visaRequestBy, visaType, visaType2, -1, billingCity,
								billingCode, billingCountry, billingState, billingStreet, createdTime, phone));
						// System.out.println(clientList.get(clientList.size() -
						// 1).getClientName());
						count++;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.print(clientList.size());
		connection.createClient(clientList);
		return count;

	}

}