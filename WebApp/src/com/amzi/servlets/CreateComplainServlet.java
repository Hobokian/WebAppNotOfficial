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
  
public class CreateComplainServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
          
        String email=request.getParameter("email");    
        String phone=request.getParameter("phone");   
        
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        {
        	session.setAttribute("email", email); 
        	session.setAttribute("phone", phone); 
        	session.setAttribute("province", LoginDao.getProvince());
        	session.setAttribute("city", LoginDao.getCity());
        }
        if (email.compareTo("")==0 && phone.compareTo("")==0)
        {
        	out.print("<p style=\"color:red\">Please use Anonymous access or enter email or phone</p>");
        	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
        	rd.include(request,response); 
        }
        else
        {
        	RequestDispatcher rd=request.getRequestDispatcher("report.jsp");    
            rd.forward(request,response); 
        }   
  
        out.close();    
    }    
}