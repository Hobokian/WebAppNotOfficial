<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.sql.*"%>
<%@ page import="com.amzi.dao.LoginDao" %> 
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>welcome on create report page</title>  
</head>  
<body>  
    <h4>your info <%=session.getAttribute("email")%>  <%=session.getAttribute("phone")%></h4>  
    <%LoginDao.validate("s", "s"); %>
    <form action="complaintSevlet" method="post"> 
        <table>  
            <tr>  
                <td>Plate Number</td>  
                <td><input type="text" name="plateNumber" /></td>  
            </tr>
            <tr>  
                <td>URL Link</td>  
                <td><input type="text" name="URLlink" /></td>  
            </tr>
            <tr>  
                <td>Street</td>  
                <td><input type="text" name="street" /></td>  
            </tr>
            <tr>  
                <td>Street Number</td>  
                <td><input type="text" name="streetNumber" /></td>  
            </tr>
 			<tr>  
                <td>Province</td>  
                <td><%=session.getAttribute("province")%></td>  
            </tr>
            <tr>  
                <td>City</td>  
                <td><%=session.getAttribute("city")%></td>  
            </tr>
            <tr>  
                <td>Postal Code</td>  
                <td><input type="text" name="postalCode" /></td>  
            </tr>
            <tr>  
                <td>Description</td>  
                <td><textarea name="description" rows="4" cols="50"></textarea></td>  
            </tr>
            <tr>  
                <td><input type="submit" value="Report" /></td>  
            </tr>  
        </table>  
    </form> 
</body>  
</html>