// your package
package ulink.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

@WebListener
public class BackgroundJobManager implements ServletContextListener {

	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		scheduler = Executors.newSingleThreadScheduledExecutor();
	//	scheduler.scheduleAtFixedRate(new DailyJob(), 0, 1, TimeUnit.DAYS);
		// scheduler.scheduleAtFixedRate(new HourlyJob(), 0, 1, TimeUnit.HOURS);
		// scheduler.scheduleAtFixedRate(new MinJob(), 0, 1, TimeUnit.MINUTES);
		// scheduler.scheduleAtFixedRate(new SecJob(), 0, 15, TimeUnit.SECONDS);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		scheduler.shutdownNow();
	}

	public class DailyJob implements Runnable {

		@Override
		public void run() {
			// Do your hourly job here.
			System.out.println("Job trigged by scheduler");
			
			DatabaseConnection database = new DatabaseConnection();
			database.clearDatatable();
			
			int startCount = 1;
			int endCount = 200;
			String file = "";
			ArrayList<Client> clientList = new ArrayList<>();
			DatabaseConnection connection = new DatabaseConnection();
			int count = 0;
			while (!file.contains("<message>There is no data to show</message>")) {
				file = "";
				URL placeURL = null;
				try {
					placeURL = new URL(
							"https://crm.zoho.com/crm/private/xml/Accounts/getRecords?newFormat=1&authtoken=18944606ffa38be63b40b56d3412f1cb&scope=crmapi&fromIndex="
									+ startCount + "&toIndex=" + endCount);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				URLConnection con;
				try {
					con = placeURL.openConnection();

					InputStream is = con.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));

					// variable line
					String line = null;
					int ID = 0;

					// constuctor

					// System.out.println(br.readLine());
					Utility utility = new Utility();

					// System.out.println(br.readLine());
					while ((line = br.readLine()) != null) {
						file += line;
					}
					// System.out.println(file);
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
					String[] temp2 = file.split("</FL>");
					ID++;

					for (int i = 0; i < temp2.length; i++) {
						// System.out.println("test test");
						String temp = temp2[i];
						// System.out.println(temp2[i]);
						if (temp.contains("ACCOUNTID")) {
							int start = temp.indexOf(">");

							// System.out.println(temp);
							accountID = temp.substring(start + 1);

						} else if (temp.contains("val=\"Account Name")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							// System.out.println(temp.substring(start+7,end));
							clientName = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Appointment")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							appointment = temp.substring(start + 7, end);

							// System.out.println(appointment);
						} else if (temp.contains("val=\"Billing City")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							// System.out.println(temp);
							billingCity = temp.substring(start + 7, end);
							// System.out.println(billingCity);
						} else if (temp.contains("val=\"Billing Code")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							billingCode = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Billing Country")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							billingCountry = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Billing State")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							billingState = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Billing Street")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							billingStreet = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Client Owner")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							clientOwner = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Client type")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							clientType = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Clinic")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							clinic = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Company (for employee only)")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							company = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Created Time")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							count++;
							// System.out.println(count);
							createdTime = temp.substring(start + 7, end);
							// System.out.println(createdTime+ "Check");
						} else if (temp.contains("val=\"Date of birth")) {

							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							dateOfBirth = temp.substring(start + 7, end);

							// System.out.println(dateOfBirth);
						} else if (temp.contains("val=\"Doctor")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							doctor = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Email")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							email = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Follow up person")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							followUpPerson = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Follow up PIC")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							followUpPIC = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Gender")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							gender = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Hospital admitted")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							hospitalAdmitted = temp.substring(start + 7, end);
						} else if (temp.contains("LOG - If yes, please tick")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							log = temp.substring(start + 7, end);
						} else if (temp.contains("Medical - If yes, please tick")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							medical = temp.substring(start + 7, end);
						} else if (temp.contains("Nationality")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							nationality = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Other doctor")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							otherDoctor = temp.substring(start + 7, end);
						} else if (temp.contains("Phone")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							phone = temp.substring(start + 7, end);
						} else if (temp.contains("PIC")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							PIC = temp.substring(start + 7, end);
						} else if (temp.contains("val=\"Referred by")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							// System.out.println(temp);
							referredBy = temp.substring(start + 7, end);
						} else if (temp.contains("Specialty")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");
							// System.out.println(temp.substring(start+7, end));

							specialty = temp.substring(start + 7, end);

						} else if (temp.contains("Ulink Can Claim")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							claim = temp.substring(start + 7, end);
						} else if (temp.contains("Visa - If yes, please tick-")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							visa = temp.substring(start + 7, end);
						} else if (temp.contains("Visa requested by")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							visaRequestBy = temp.substring(start + 7, end);
						} else if (temp.contains("Visa type")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							visaType = temp.substring(start + 7, end);
						} else if (temp.contains("Visa type 2")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							visaType2 = temp.substring(start + 7, end);
						} else if (temp.contains("Main Diagnosis")) {
							int start = temp.indexOf("[CDATA[");
							int end = temp.indexOf("]");

							mainDiagnosis = temp.substring(start + 7, end);
						}

						// System.out.println(specialty);
						if (temp.contains("</row>") && !dateOfBirth.equals("") && createdTime.length() >= 1) {
							// System.out.println(createdTime+"check");
							clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company,
									nationality, gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC,
									appointment, doctor, specialty, clinic, otherDoctor, followUpPerson, followUpPIC,
									hospitalAdmitted, log, claim, visa, visaRequestBy, visaType, visaType2,
									utility.getAge(dateOfBirth), billingCity, billingCode, billingCountry, billingState,
									billingStreet, createdTime, phone));
							// System.out.println(clientList.get(clientList.size()
							// -
							// 1).getClientName());
							accountID = "";
							clientOwner = "";
							clientName = "";
							clientType = "";
							company = "";
							nationality = "";
							gender = "";
							dateOfBirth = "";
							email = "";
							medical = "";
							mainDiagnosis = "";
							referredBy = "";
							PIC = "";
							appointment = "";
							doctor = "";
							specialty = "";
							clinic = "";
							otherDoctor = "";
							followUpPerson = "";
							followUpPIC = "";
							hospitalAdmitted = "";
							log = "";
							claim = "";
							visaRequestBy = "";
							visa = "";
							visaType = "";
							visaType2 = "";
							billingCity = "";
							billingCode = "";
							billingCountry = "";
							billingState = "";
							billingStreet = "";
							createdTime = "";
							phone = "";

						} else if (temp.contains("</row>") && createdTime.length() >= 1) {
							// System.out.println(createdTime+"check");
							clientList.add(new Client(ID, accountID, clientOwner, clientName, clientType, company,
									nationality, gender, dateOfBirth, email, medical, mainDiagnosis, referredBy, PIC,
									appointment, doctor, specialty, clinic, otherDoctor, followUpPerson, followUpPIC,
									hospitalAdmitted, log, claim, visa, visaRequestBy, visaType, visaType2, -1,
									billingCity, billingCode, billingCountry, billingState, billingStreet, createdTime,
									phone));
							// System.out.println(clientList.get(clientList.size()
							// -
							// 1).getClientName());
							accountID = "";
							clientOwner = "";
							clientName = "";
							clientType = "";
							company = "";
							nationality = "";
							gender = "";
							dateOfBirth = "";
							email = "";
							medical = "";
							mainDiagnosis = "";
							referredBy = "";
							PIC = "";
							appointment = "";
							doctor = "";
							specialty = "";
							clinic = "";
							otherDoctor = "";
							followUpPerson = "";
							followUpPIC = "";
							hospitalAdmitted = "";
							log = "";
							claim = "";
							visaRequestBy = "";
							visa = "";
							visaType = "";
							visaType2 = "";
							billingCity = "";
							billingCode = "";
							billingCountry = "";
							billingState = "";
							billingStreet = "";
							createdTime = "";
							phone = "";

						}
						// System.out.println(temp);
					}

					startCount += 200;
					endCount += 200;

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			System.out.println(count + " = Count");
			System.out.println(clientList.size());
			connection.createClient(clientList);


		}
	}
}