package com.amzi.servlets;  
  
import java.io.IOException;  
import java.io.PrintWriter;  
  

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  

import com.amzi.dao.LoginDao;  
  
public class SignUpServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
          
        String budge=request.getParameter("budge");    
        String accessCode=request.getParameter("accessCode");
        String email=request.getParameter("email");  
        String phone=request.getParameter("phoneNumber");  
        String password=request.getParameter("password");
        String passwordConfirm=request.getParameter("confirmPassword");  
        String selectProvince=request.getParameter("selectProvince");
        String selectCity=request.getParameter("selectCity");
        
        if(!LoginDao.checkAccessCode(accessCode, selectProvince, selectCity))
        {
        	out.print("<p style=\"color:red\">wrong Access Code</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("signUp.jsp");    
            rd.include(request,response);  
        }
        else
        {
        	if(password.compareTo(passwordConfirm)==0)
        	{
        		if(LoginDao.addUser(budge, email, phone, password, selectProvince, selectCity))
        		{
        			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");    
                    rd.forward(request,response); 
        		}
        		else
        		{
        			out.print("<p style=\"color:red\">there is no match password and confirm password field</p>");    
                    RequestDispatcher rd=request.getRequestDispatcher("signUp.jsp");    
                    rd.include(request,response);
        		}
        		
        	}
        	else
        	{
        		out.print("<p style=\"color:red\">there is no match password and confirm password field</p>");    
                RequestDispatcher rd=request.getRequestDispatcher("signUp.jsp");    
                rd.include(request,response); 
        	}
        	
        }
        out.close();    
    }    
}