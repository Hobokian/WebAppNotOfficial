package com.amzi.servlets;  
  
import java.io.IOException;  
import java.io.PrintWriter;  
  



import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;   
import javax.servlet.http.HttpSession;

import com.amzi.dao.LoginDao;
  
public class SubmitReportServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
          
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String phone = (String) session.getAttribute("phone");
        String plateNumber=request.getParameter("plateNumber");    
        String URLlink=request.getParameter("URLlink");  
        String street=request.getParameter("street"); 
        String streetNumber=request.getParameter("streetNumber");
        String selectProvince=request.getParameter("selectProvince");
        String selectCity=request.getParameter("selectCity");
        String postalCode=request.getParameter("postalCode");
        String description=request.getParameter("description");
        
        if (	email.compareTo("")==0 			&& 
        		phone.compareTo("")==0	 		&&
        		URLlink.compareTo("")==0	 	&&
      			selectProvince.compareTo("")==0	&&
       			selectCity.compareTo("")==0	 	&&
        		description.compareTo("")==0
        	)
        {
        	out.print("<p style=\"color:red\">Please fill up all fields</p>");
        	RequestDispatcher rd=request.getRequestDispatcher("report.jsp");
        	rd.include(request,response); 
        }
        else
        {
        	if(LoginDao.submitRecord(email, phone, plateNumber, URLlink, street, streetNumber, selectProvince, selectCity, postalCode, description))
        		out.print("<p style=\"color:red\">Thank you for your report</p>");
        	else
        		out.print("<p style=\"color:red\">Fail database. please try again later</p>");
        	RequestDispatcher rd=request.getRequestDispatcher("report.jsp");    
            rd.include(request,response); 
        }   
  
        out.close();    
    }    
}