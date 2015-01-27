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
  
public class CreateAnonymousComplainServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();      
        
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        {
        	session.setAttribute("email", "Anonymous"); 
        	session.setAttribute("phone", "Anonymous"); 
        	session.setAttribute("province", LoginDao.getProvince());
        	session.setAttribute("city", LoginDao.getCity());
        }

        RequestDispatcher rd=request.getRequestDispatcher("report.jsp");    
        rd.forward(request,response); 

        out.close();    
    }    
}