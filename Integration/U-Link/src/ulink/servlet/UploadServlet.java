package ulink.servlet;

import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonObject;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    public UploadServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
 	   DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
         
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
         
        // constructs the directory path to store upload file
        String uploadPath = getServletContext().getRealPath("")
            + File.separator + UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
     //PrintWriter to send the JSON response back
     PrintWriter out = response.getWriter();

   

     ServletFileUpload uploadHandler = new ServletFileUpload(factory);
     JsonObject myObj = new JsonObject();

     String fileName = null;
     String fullName = null;
     File file = null;

     try {

       
         List formItems = upload.parseRequest(request);
         Iterator iter = formItems.iterator();
          
         // iterates over form's fields
         while (iter.hasNext()) {
             FileItem item = (FileItem) iter.next();
             // processes only fields that are not form fields
             if (!item.isFormField()) {
                 fileName = new File(item.getName()).getName();
                 String filePath = uploadPath + File.separator + fileName;
                 File storeFile = new File(filePath);
                  
                 // saves the file on disk
                 item.write(storeFile);
             }
         }


         

         int count = 0;
         String extension = FilenameUtils.getExtension(fullName);
         if(extension.trim().equalsIgnoreCase("xlsx")){
             count = processExcelFile(file);
         }
         else if(extension.trim().equalsIgnoreCase("xls")){
             //process your binary excel file
         }
         if(extension.trim().equalsIgnoreCase("csv")){
             //process your CSV file
         }

         myObj.addProperty("success", true);
         myObj.addProperty("message", count + " item(s) were processed for file " + fileName);
         out.println(myObj.toString());

     }
     catch(FileUploadException ex) {
         log("Error encountered while parsing the request",ex);
         myObj.addProperty("success", false);
         out.println(myObj.toString());
     } catch(Exception ex) {
         log("Error encountered while uploading file",ex);
         myObj.addProperty("success", false);
         out.println(myObj.toString());
     }

     out.close();	

 }

 private int processExcelFile(File file){


     int count = 0;

     try{
    
         FileInputStream inputStream = new FileInputStream(file);
          
         Workbook workbook = new XSSFWorkbook(inputStream);
         Sheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> iterator = firstSheet.iterator();
          
         while (iterator.hasNext()) {
             Row nextRow = iterator.next();
             Iterator<Cell> cellIterator = nextRow.cellIterator();
              
             while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                 System.out.print(cell.toString());
                 System.out.print(" - ");
             }
             System.out.println();
         }
         
         workbook.close();
         inputStream.close();
     }
     catch (Exception e){
         e.printStackTrace(); 
     }

     return count;

 }
   
}