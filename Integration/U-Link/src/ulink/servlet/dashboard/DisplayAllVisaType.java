package ulink.servlet.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ulink.constructor.Client;
import ulink.dao.DatabaseConnection;
import ulink.logic.Utility;

/**
 * Servlet implementation class DisplayAllVisaType
 */
@WebServlet("/DisplayAllVisaType")
public class DisplayAllVisaType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllVisaType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		DatabaseConnection connection = new DatabaseConnection();
		ArrayList<Client> list = connection.retrieveAllClientListVisa();
		LinkedHashMap<String,Integer> visaTypeList = new LinkedHashMap<>();
		
		Utility utility = new Utility();
		
		for (int i=0; i <list.size(); i++){
			Client c = list.get(i);
			if(c.getVisaType().length() > 0){
			if (visaTypeList.containsKey(c.getVisaType())){
				int temp = visaTypeList.get(c.getVisaType());
				visaTypeList.put(c.getVisaType(), temp+1);
			}else {
				visaTypeList.put(c.getVisaType(), 1);
			}
			}
			//System.out.println(visaTypeList.keySet());
		}
		
		Gson gson = new Gson();
		String arrayListToJson = gson.toJson(visaTypeList);
		out.write(arrayListToJson);
		out.flush();
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
