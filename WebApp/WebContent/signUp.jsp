<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="signUpServlet" method="post"> 
        <table>  
            <tr>  
                <td>budge ID</td>  
                <td><input type="text" name="budge" /></td>  
            </tr>
            <tr>  
                <td>Access Code</td>  
                <td><input type="text" name="accessCode" /></td>  
            </tr>
            <tr>  
                <td>Email Address</td>  
                <td><input type="text" name="email" /></td>  
            </tr>
            <tr>  
                <td>Phone Number</td>  
                <td><input type="text" name="phoneNumber" /></td>  
            </tr>
            <tr>  
                <td>Password</td>  
                <td><input type="text" name="password" /></td>  
            </tr>
            <tr>  
                <td>Confirm Password</td>  
                <td><input type="text" name="confirmPassword" /></td>  
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
                <td><input type="submit" value="signUp" /></td>  
            </tr>  
        </table>  
    </form> 
</body>
</html>