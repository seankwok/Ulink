package ulink.servlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    private static final String TMP_DIR_PATH = "/MyTempFiles";
    private File tmpDir;
    private static final String DESTINATION_DIR_PATH ="/MySavedFiles";
    private File destinationDir;
 
    public UploadServlet() {
        super();
    }
 
    public void init(ServletConfig config) throws ServletException {
 
        super.init(config);
        String realPath = getServletContext().getRealPath(TMP_DIR_PATH);
        tmpDir = new File(realPath);
        if(!tmpDir.isDirectory()) {
            throw new ServletException(TMP_DIR_PATH + " is not a directory");
        }
 
        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
        destinationDir = new File(realPath);
        if(!destinationDir.isDirectory()) {
            throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
        }
 
    }
 
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        //PrintWriter to send the JSON response back
        PrintWriter out = response.getWriter();
 
       // set content type and header attributes
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
 
        DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
 
        //Set the size threshold, above which content will be stored on disk.
        fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
 
        //Set the temporary directory to store the uploaded files of size above threshold.
        fileItemFactory.setRepository(tmpDir);
 
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        JsonObject myObj = new JsonObject();
 
        String fileName = null;
        String fullName = null;
        File file = null;
    	
        try {
 
            //Parse the request
            List items = uploadHandler.parseRequest(request);
            Iterator iterator = items.iterator();
            while(iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
 
                //Handle Form Fields
                if(item.isFormField()) {
                    System.out.println("Field Name = " + item.getFieldName() + ", Value = " + item.getString());
                    if(item.getFieldName().trim().equalsIgnoreCase("filename")){
                        fileName = item.getString().trim();
                    }
                } 
 
                //Handle Uploaded files.
                else {
                    System.out.println("Field Name = " + item.getFieldName()+
                            ", File Name = "+ item.getName()+
                            ", Content type = "+item.getContentType()+
                            ", File Size = "+item.getSize());
                    fullName = item.getName().trim();
 
                    //Write file to the ultimate location.
                    file = new File(destinationDir,item.getName());
                    item.write(file);
                }
 
 
 
            }
 
            int count = 0;
            String extension = FilenameUtils.getExtension(fullName);
            if(extension.trim().equalsIgnoreCase("xlsx")){
                count = processExcelFile(file);
               // System.out.print(count);
        		//out.write(count);
        		//out.flush();
                request.getRequestDispatcher("./upload.html").forward(request, response);
        		return;
        		
            }
            else if(extension.trim().equalsIgnoreCase("xls")){
                //process your binary excel file
            	  count = test(file);

          		//out.write(count);
          		//out.flush();
          		//response.sendRedirect("./upload.html");
          		request.getRequestDispatcher("./upload.html").forward(request, response);
          		return;
            }
            
     
 
        }
        catch(FileUploadException ex) {
            log("Error encountered while parsing the request",ex);
            //myObj.addProperty("success", false);
            //out.println(myObj.toString());
            
            request.getRequestDispatcher("./upload.html").forward(request, response);
            return;
        } catch(Exception ex) {
            log("Error encountered while uploading file",ex);
           // myObj.addProperty("success", false);
           
            // out.println(myObj.toString());
            
            request.getRequestDispatcher("./upload.html").forward(request, response);
            return;
        }
 
        request.getRequestDispatcher("./upload.html").forward(request, response);
        //response.sendRedirect("./upload.html");
        return;
    }
    
    private int test(File file) throws FileNotFoundException, IOException{
    	 POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
    	    HSSFWorkbook wb = new HSSFWorkbook(fs);
    	    HSSFSheet sheet = wb.getSheetAt(0);
    	    HSSFRow row;
    	    HSSFCell cell;
    	    int count = 0;
    	    Utility utility = new Utility();
    	    DatabaseConnection connection = new DatabaseConnection();
    	    Iterator<Row> rowIter = sheet.rowIterator();
    	    rowIter.next();
            rowIter.next();
            while(rowIter.hasNext()){
            	 row = (HSSFRow) rowIter.next();
                 Iterator<Cell> cellIter = row.cellIterator();
                 																				
                 		HSSFCell accountID = (HSSFCell) cellIter.next();
                 		HSSFCell clientOwner = (HSSFCell) cellIter.next();
                 		HSSFCell clientName = (HSSFCell) cellIter.next();
                 		HSSFCell clientType = (HSSFCell) cellIter.next();
                 		HSSFCell company = (HSSFCell) cellIter.next();
                 		HSSFCell nationality = (HSSFCell) cellIter.next();
                 		HSSFCell gender = (HSSFCell) cellIter.next();
                 		HSSFCell dateOfBirth = (HSSFCell) cellIter.next();
                 		HSSFCell email = (HSSFCell) cellIter.next();
                 		HSSFCell medical = (HSSFCell) cellIter.next();
                 		HSSFCell mainDiagnosis = (HSSFCell) cellIter.next();
                 		HSSFCell referredByPIC = (HSSFCell) cellIter.next();
                 		HSSFCell appointment = (HSSFCell) cellIter.next();
                 		HSSFCell doctor = (HSSFCell) cellIter.next();
                 		HSSFCell specialty = (HSSFCell) cellIter.next();
                 		HSSFCell clinic = (HSSFCell) cellIter.next();
                 		HSSFCell otherDoctor = (HSSFCell) cellIter.next();
                 		HSSFCell followUpPerson = (HSSFCell) cellIter.next();
                 		HSSFCell followUp = (HSSFCell) cellIter.next();
                 		HSSFCell PIC = (HSSFCell) cellIter.next();
                 		HSSFCell hospitalAdmitted = (HSSFCell) cellIter.next();
                 		HSSFCell log = (HSSFCell) cellIter.next();
                 		HSSFCell claim = (HSSFCell) cellIter.next();
                 		HSSFCell visaRequestBy = (HSSFCell) cellIter.next();
                 		HSSFCell visa = (HSSFCell) cellIter.next();
                 		HSSFCell visaType = (HSSFCell) cellIter.next();
                 		HSSFCell visaType2 = (HSSFCell) cellIter.next();
                 		System.out.println(clientName.toString().length());
                 		connection.createClient(accountID.toString(), clientOwner.toString(), clientName.toString(), clientType.toString(), company.toString(), nationality.toString(), gender.toString(), dateOfBirth.toString(), email.toString(), medical.toString(), mainDiagnosis.toString(), referredByPIC.toString(), appointment.toString(), doctor.toString(), specialty.toString(), clinic.toString(), otherDoctor.toString(), followUpPerson.toString(), followUp.toString(), PIC.toString(), hospitalAdmitted.toString(), log.toString(), claim.toString(), visaRequestBy.toString(), visa.toString(), visaType.toString(), visaType2.toString(), utility.getAge(dateOfBirth.toString()));
                 	    count++;
                     
            }
           
    	    return count;
    	    
    }
 
    private int processExcelFile(File file){
 
    	DatabaseConnection connection = new DatabaseConnection();
    	int count = 0;
 
        try{
            // Creating Input Stream 
            FileInputStream myInput = new FileInputStream(file);
 
            // Create a workbook using the File System 
            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
 
            // Get the first sheet from workbook 
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
 
            /** We now need something to iterate through the cells.**/
            Iterator<Row> rowIter = mySheet.rowIterator();
            rowIter.next();
            rowIter.next();
            Utility utility = new Utility();
            while(rowIter.hasNext()){
            	
                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                
                
                
                																						
                		XSSFCell accountID = (XSSFCell) cellIter.next();
                		XSSFCell clientOwner = (XSSFCell) cellIter.next();
                		XSSFCell clientName = (XSSFCell) cellIter.next();
                		XSSFCell clientType = (XSSFCell) cellIter.next();
                		XSSFCell company = (XSSFCell) cellIter.next();
                		XSSFCell nationality = (XSSFCell) cellIter.next();
                		XSSFCell gender = (XSSFCell) cellIter.next();
                		XSSFCell dateOfBirth = (XSSFCell) cellIter.next();
                		XSSFCell email = (XSSFCell) cellIter.next();
                		XSSFCell medical = (XSSFCell) cellIter.next();
                		XSSFCell mainDiagnosis = (XSSFCell) cellIter.next();
                		XSSFCell referredBy = (XSSFCell) cellIter.next();
                		XSSFCell PIC = (XSSFCell) cellIter.next();
                		XSSFCell appointment = (XSSFCell) cellIter.next();
                		XSSFCell doctor = (XSSFCell) cellIter.next();
                		XSSFCell specialty = (XSSFCell) cellIter.next();
                		XSSFCell clinic = (XSSFCell) cellIter.next();
                		XSSFCell otherDoctor = (XSSFCell) cellIter.next();
                		XSSFCell followUpPerson = (XSSFCell) cellIter.next();
                		XSSFCell followUpPIC = (XSSFCell) cellIter.next();
                		XSSFCell hospitalAdmitted = (XSSFCell) cellIter.next();
                		XSSFCell log = (XSSFCell) cellIter.next();
                		XSSFCell claim = (XSSFCell) cellIter.next();
                		XSSFCell visaRequestBy = (XSSFCell) cellIter.next();
                		XSSFCell visa = (XSSFCell) cellIter.next();
                		XSSFCell visaType = (XSSFCell) cellIter.next();
                		XSSFCell visaType2 = (XSSFCell) cellIter.next();
                		System.out.println(clientName.toString().length());
                		connection.createClient(accountID.toString(), clientOwner.toString(), clientName.toString(), clientType.toString(), company.toString(), nationality.toString(), gender.toString(), dateOfBirth.toString(), email.toString(), medical.toString(), mainDiagnosis.toString(), referredBy.toString(), PIC.toString(), appointment.toString(), doctor.toString(), specialty.toString(), clinic.toString(), otherDoctor.toString(), followUpPerson.toString(), followUpPIC.toString(), hospitalAdmitted.toString(), log.toString(), claim.toString(), visaRequestBy.toString(), visa.toString(), visaType.toString(), visaType2.toString(), utility.getAge(dateOfBirth.toString()));
                	    count++;
                    
 
                
 
            }
        }
        catch (Exception e){
            e.printStackTrace(); 
        }
 
        return count;
 
    }
    
}