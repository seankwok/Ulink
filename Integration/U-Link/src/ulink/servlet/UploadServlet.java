package ulink.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		String uploadedTime = request.getParameter("uploadedTime");

		// set content type and header attributes
		response.setContentType("text/html");
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
		JsonObject myObj = new JsonObject();

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

			int count = 0;
			String extension = FilenameUtils.getExtension(fullName);
			if (extension.trim().equalsIgnoreCase("xlsx")) {
				count = processExcelFile(file);
				// System.out.print(count);
				// out.write(count);
				// out.flush();
				connection.addDateTime(uploadedTime);

				request.getRequestDispatcher("./upload.html").forward(request, response);
				return;

			} else if (extension.trim().equalsIgnoreCase("xls")) {
				// process your binary excel file
				count = test(file);
				connection.addDateTime(uploadedTime);
				// out.write(count);
				// out.flush();
				// response.sendRedirect("./upload.html");
				request.getRequestDispatcher("./upload.html").forward(request, response);
				return;
			}

		} catch (FileUploadException ex) {
			log("Error encountered while parsing the request", ex);
			// myObj.addProperty("success", false);
			// out.println(myObj.toString());

			request.getRequestDispatcher("./upload.html").forward(request, response);
			return;
		} catch (Exception ex) {
			log("Error encountered while uploading file", ex);
			// myObj.addProperty("success", false);

			// out.println(myObj.toString());

			request.getRequestDispatcher("./upload.html").forward(request, response);
			return;
		}

		request.getRequestDispatcher("./upload.html").forward(request, response);
		// response.sendRedirect("./upload.html");
		return;
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
		String accountID = null;
		String clientOwner = null;
		String clientName = null;
		String clientType = null;
		String company = null;
		String nationality = null;
		String gender = null;
		String dateOfBirth = null;
		String email = null;
		String medical = null;
		String mainDiagnosis = null;
		String referredBy = null;
		String PIC = null;
		String appointment = null;
		String doctor = null;
		String specialty = null;
		String clinic = null;
		String otherDoctor = null;
		String followUpPerson = null;
		String followUpPIC = null;
		String hospitalAdmitted = null;
		String log = null;
		String claim = null;
		String visaRequestBy = null;
		String visa = null;
		String visaType = null;
		String visaType2 = null;
		String billingCity = null;
		String billingCode = null;
		String billingCountry = null;
		String billingState = null;
		String billingStreet = null;
		String createdTime = null;
		String phone = null;
		
		while (rowIter.hasNext()) {
			ID++;
			
			HSSFRow myRow = (HSSFRow) rowIter.next();
			Iterator<Cell> cellIter = myRow.cellIterator();
			
			XSSFCell temp = (XSSFCell) cellIter.next();
			int row = temp.getRowIndex();
			
			if (temp.toString().equals("ACCOUNTID") || temp.getRowIndex() == accountIDRow){
				accountIDRow = temp.getRowIndex();
				accountID = temp.toString();
			} else if (temp.toString().equals("Client Name") || temp.getRowIndex() == clientNameRow){
				clientNameRow = temp.getRowIndex();
				clientName = temp.toString();
			}else if (temp.toString().equals("Appointment date and time") || temp.getRowIndex() == appointmentRow){
				appointmentRow = temp.getRowIndex();
				appointment = temp.toString();
			}else if (temp.toString().equals("Billing City") || temp.getRowIndex() == billingCityRow){
				billingCityRow = temp.getRowIndex();
				billingCity = temp.toString();
			}else if (temp.toString().equals("Billing Code") || temp.getRowIndex() == billingCodeRow){
				billingCodeRow = temp.getRowIndex();
				billingCode = temp.toString();
			}else if (temp.toString().equals("Billing Country") || temp.getRowIndex() == billingCountryRow){
				billingCountryRow = temp.getRowIndex();
				billingCountry = temp.toString();
			}else if (temp.toString().equals("Billing State") || temp.getRowIndex() == billingStateRow){
				billingStateRow = temp.getRowIndex();
				billingState = temp.toString();
			}else if (temp.toString().equals("Billing Street") || temp.getRowIndex() == billingStreetRow){
				billingStreetRow = temp.getRowIndex();
				billingStreet = temp.toString();
			}else if (temp.toString().equals("Client Owner") || temp.getRowIndex() == clientOwnerRow){
				clientOwnerRow = temp.getRowIndex();
				clientOwner = temp.toString();
			}else if (temp.toString().equals("Client type") || temp.getRowIndex() == clientTypeRow){
				clientTypeRow = temp.getRowIndex();
				clientType = temp.toString();
			}else if (temp.toString().equals("Clinic") || temp.getRowIndex() == clinicRow){
				clinicRow = temp.getRowIndex();
				clinic = temp.toString();
			}else if (temp.toString().equals("Company (for employee only)") || temp.getRowIndex() == companyRow){
				companyRow = temp.getRowIndex();
				company = temp.toString();
			}else if (temp.toString().equals("Created Time") || temp.getRowIndex() == createdTimeRow){
				createdTimeRow = temp.getRowIndex();
				createdTime = temp.toString();
			}else if (temp.toString().equals("Date of birth") || temp.getRowIndex() == dateOfBirthRow){
				dateOfBirthRow = temp.getRowIndex();
				dateOfBirth = temp.toString();
			}else if (temp.toString().equals("Doctor") || temp.getRowIndex() == doctorRow){
				doctorRow = temp.getRowIndex();
				doctor = temp.toString();
			}else if (temp.toString().equals("Email") || temp.getRowIndex() == emailRow){
				emailRow = temp.getRowIndex();
				email = temp.toString();
			}else if (temp.toString().equals("Follow up person") || temp.getRowIndex() == followUpPersonRow){
				followUpPersonRow = temp.getRowIndex();
				followUpPerson = temp.toString();
			}else if (temp.toString().equals("Follow up PIC") || temp.getRowIndex() == followUpPICRow){
				followUpPICRow = temp.getRowIndex();
				followUpPIC = temp.toString();
			}else if (temp.toString().equals("Gender") || temp.getRowIndex() == genderRow){
				genderRow = temp.getRowIndex();
				gender = temp.toString();
			}else if (temp.toString().equals("Hospital admitted") || temp.getRowIndex() == hospitalAdmittedRow){
				hospitalAdmittedRow = temp.getRowIndex();
				hospitalAdmitted = temp.toString();
			}else if (temp.toString().equals("LOG - If yes, please tick") || temp.getRowIndex() == logRow){
				logRow = temp.getRowIndex();
				log = temp.toString();
			}else if (temp.toString().equals("Medical - If yes, please tick") || temp.getRowIndex() == medicalRow){
				medicalRow = temp.getRowIndex();
				medical = temp.toString();
			}else if (temp.toString().equals("Nationality") || temp.getRowIndex() == nationalityRow){
				nationalityRow = temp.getRowIndex();
				nationality = temp.toString();
			}else if (temp.toString().equals("Other doctor") || temp.getRowIndex() == otherDoctorRow){
				otherDoctorRow = temp.getRowIndex();
				otherDoctor = temp.toString();
			}else if (temp.toString().equals("Phone") || temp.getRowIndex() == phoneRow){
				phoneRow = temp.getRowIndex();
				phone = temp.toString();
			}else if (temp.toString().equals("PIC") || temp.getRowIndex() == PICRow){
				PICRow = temp.getRowIndex();
				PIC = temp.toString();
			}else if (temp.toString().equals("Referred by") || temp.getRowIndex() == referredByRow){
				referredByRow = temp.getRowIndex();
				referredBy = temp.toString();
			}else if (temp.toString().equals("Specialty") || temp.getRowIndex() == specialtyRow){
				specialtyRow = temp.getRowIndex();
				specialty = temp.toString();
			}else if (temp.toString().equals("Ulink Can Claim") || temp.getRowIndex() == claimRow){
				claimRow = temp.getRowIndex();
				claim = temp.toString();
			}else if (temp.toString().equals("Visa - If yes, please tick-") || temp.getRowIndex() == visaRow){
				visaRow = temp.getRowIndex();
				visa = temp.toString();
			}else if (temp.toString().equals("Visa requested by") || temp.getRowIndex() == visaRequestByRow){
				visaRequestByRow = temp.getRowIndex();
				visaRequestBy = temp.toString();
			}else if (temp.toString().equals("Visa type") || temp.getRowIndex() == visaTypeRow){
				visaTypeRow = temp.getRowIndex();
				visaType = temp.toString();
			}else if (temp.toString().equals("Visa type 2") || temp.getRowIndex() == visaType2Row){
				visaType2Row = temp.getRowIndex();
				visaType2 = temp.toString();
			}else if (temp.toString().equals("Main Diagnosis") || temp.getRowIndex() == mainDiagnosisRow){
				mainDiagnosisRow = temp.getRowIndex();
				mainDiagnosis = temp.toString();
			}


			clientList.add(new Client(ID, accountID, clientOwner, clientName,
					clientType, company, nationality, gender,
					dateOfBirth, email, medical, mainDiagnosis,
					referredBy, appointment, doctor, specialty,
					clinic, otherDoctor, followUpPerson, followUpPIC,
					PIC, hospitalAdmitted, log, claim,
					visaRequestBy, visa, visaType, visaType2,
					utility.getAge(dateOfBirth), billingCity, billingCode, billingCountry, 
					billingState, billingStreet, createdTime, phone));

			count++;

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
			String accountID = null;
			String clientOwner = null;
			String clientName = null;
			String clientType = null;
			String company = null;
			String nationality = null;
			String gender = null;
			String dateOfBirth = null;
			String email = null;
			String medical = null;
			String mainDiagnosis = null;
			String referredBy = null;
			String PIC = null;
			String appointment = null;
			String doctor = null;
			String specialty = null;
			String clinic = null;
			String otherDoctor = null;
			String followUpPerson = null;
			String followUpPIC = null;
			String hospitalAdmitted = null;
			String log = null;
			String claim = null;
			String visaRequestBy = null;
			String visa = null;
			String visaType = null;
			String visaType2 = null;
			String billingCity = null;
			String billingCode = null;
			String billingCountry = null;
			String billingState = null;
			String billingStreet = null;
			String createdTime = null;
			String phone = null;
			
			while (rowIter.hasNext()) {
				ID++;
				XSSFRow myRow = (XSSFRow) rowIter.next();
				Iterator<Cell> cellIter = myRow.cellIterator();
				
				XSSFCell temp = (XSSFCell) cellIter.next();
				int row = temp.getRowIndex();
				
				if (temp.toString().equals("ACCOUNTID") || temp.getRowIndex() == accountIDRow){
					accountIDRow = temp.getRowIndex();
					accountID = temp.toString();
				} else if (temp.toString().equals("Client Name") || temp.getRowIndex() == clientNameRow){
					clientNameRow = temp.getRowIndex();
					clientName = temp.toString();
				}else if (temp.toString().equals("Appointment date and time") || temp.getRowIndex() == appointmentRow){
					appointmentRow = temp.getRowIndex();
					appointment = temp.toString();
				}else if (temp.toString().equals("Billing City") || temp.getRowIndex() == billingCityRow){
					billingCityRow = temp.getRowIndex();
					billingCity = temp.toString();
				}else if (temp.toString().equals("Billing Code") || temp.getRowIndex() == billingCodeRow){
					billingCodeRow = temp.getRowIndex();
					billingCode = temp.toString();
				}else if (temp.toString().equals("Billing Country") || temp.getRowIndex() == billingCountryRow){
					billingCountryRow = temp.getRowIndex();
					billingCountry = temp.toString();
				}else if (temp.toString().equals("Billing State") || temp.getRowIndex() == billingStateRow){
					billingStateRow = temp.getRowIndex();
					billingState = temp.toString();
				}else if (temp.toString().equals("Billing Street") || temp.getRowIndex() == billingStreetRow){
					billingStreetRow = temp.getRowIndex();
					billingStreet = temp.toString();
				}else if (temp.toString().equals("Client Owner") || temp.getRowIndex() == clientOwnerRow){
					clientOwnerRow = temp.getRowIndex();
					clientOwner = temp.toString();
				}else if (temp.toString().equals("Client type") || temp.getRowIndex() == clientTypeRow){
					clientTypeRow = temp.getRowIndex();
					clientType = temp.toString();
				}else if (temp.toString().equals("Clinic") || temp.getRowIndex() == clinicRow){
					clinicRow = temp.getRowIndex();
					clinic = temp.toString();
				}else if (temp.toString().equals("Company (for employee only)") || temp.getRowIndex() == companyRow){
					companyRow = temp.getRowIndex();
					company = temp.toString();
				}else if (temp.toString().equals("Created Time") || temp.getRowIndex() == createdTimeRow){
					createdTimeRow = temp.getRowIndex();
					createdTime = temp.toString();
				}else if (temp.toString().equals("Date of birth") || temp.getRowIndex() == dateOfBirthRow){
					dateOfBirthRow = temp.getRowIndex();
					dateOfBirth = temp.toString();
				}else if (temp.toString().equals("Doctor") || temp.getRowIndex() == doctorRow){
					doctorRow = temp.getRowIndex();
					doctor = temp.toString();
				}else if (temp.toString().equals("Email") || temp.getRowIndex() == emailRow){
					emailRow = temp.getRowIndex();
					email = temp.toString();
				}else if (temp.toString().equals("Follow up person") || temp.getRowIndex() == followUpPersonRow){
					followUpPersonRow = temp.getRowIndex();
					followUpPerson = temp.toString();
				}else if (temp.toString().equals("Follow up PIC") || temp.getRowIndex() == followUpPICRow){
					followUpPICRow = temp.getRowIndex();
					followUpPIC = temp.toString();
				}else if (temp.toString().equals("Gender") || temp.getRowIndex() == genderRow){
					genderRow = temp.getRowIndex();
					gender = temp.toString();
				}else if (temp.toString().equals("Hospital admitted") || temp.getRowIndex() == hospitalAdmittedRow){
					hospitalAdmittedRow = temp.getRowIndex();
					hospitalAdmitted = temp.toString();
				}else if (temp.toString().equals("LOG - If yes, please tick") || temp.getRowIndex() == logRow){
					logRow = temp.getRowIndex();
					log = temp.toString();
				}else if (temp.toString().equals("Medical - If yes, please tick") || temp.getRowIndex() == medicalRow){
					medicalRow = temp.getRowIndex();
					medical = temp.toString();
				}else if (temp.toString().equals("Nationality") || temp.getRowIndex() == nationalityRow){
					nationalityRow = temp.getRowIndex();
					nationality = temp.toString();
				}else if (temp.toString().equals("Other doctor") || temp.getRowIndex() == otherDoctorRow){
					otherDoctorRow = temp.getRowIndex();
					otherDoctor = temp.toString();
				}else if (temp.toString().equals("Phone") || temp.getRowIndex() == phoneRow){
					phoneRow = temp.getRowIndex();
					phone = temp.toString();
				}else if (temp.toString().equals("PIC") || temp.getRowIndex() == PICRow){
					PICRow = temp.getRowIndex();
					PIC = temp.toString();
				}else if (temp.toString().equals("Referred by") || temp.getRowIndex() == referredByRow){
					referredByRow = temp.getRowIndex();
					referredBy = temp.toString();
				}else if (temp.toString().equals("Specialty") || temp.getRowIndex() == specialtyRow){
					specialtyRow = temp.getRowIndex();
					specialty = temp.toString();
				}else if (temp.toString().equals("Ulink Can Claim") || temp.getRowIndex() == claimRow){
					claimRow = temp.getRowIndex();
					claim = temp.toString();
				}else if (temp.toString().equals("Visa - If yes, please tick-") || temp.getRowIndex() == visaRow){
					visaRow = temp.getRowIndex();
					visa = temp.toString();
				}else if (temp.toString().equals("Visa requested by") || temp.getRowIndex() == visaRequestByRow){
					visaRequestByRow = temp.getRowIndex();
					visaRequestBy = temp.toString();
				}else if (temp.toString().equals("Visa type") || temp.getRowIndex() == visaTypeRow){
					visaTypeRow = temp.getRowIndex();
					visaType = temp.toString();
				}else if (temp.toString().equals("Visa type 2") || temp.getRowIndex() == visaType2Row){
					visaType2Row = temp.getRowIndex();
					visaType2 = temp.toString();
				}else if (temp.toString().equals("Main Diagnosis") || temp.getRowIndex() == mainDiagnosisRow){
					mainDiagnosisRow = temp.getRowIndex();
					mainDiagnosis = temp.toString();
				}


				clientList.add(new Client(ID, accountID, clientOwner, clientName,
						clientType, company, nationality, gender,
						dateOfBirth, email, medical, mainDiagnosis,
						referredBy, appointment, doctor, specialty,
						clinic, otherDoctor, followUpPerson, followUpPIC,
						PIC, hospitalAdmitted, log, claim,
						visaRequestBy, visa, visaType, visaType2,
						utility.getAge(dateOfBirth), billingCity, billingCode, billingCountry, 
						billingState, billingStreet, createdTime, phone));

				count++;

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		connection.createClient(clientList);
		return count;

	}

}