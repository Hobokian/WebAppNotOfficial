<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Login Application</title>  
</head>  
	<body>    
		<table frame="box" border=2  width=50%> 
			<tr><td>
		    	<form action="createReport" method="post"> 
		        	<table>  
		                <tr>  
		                    <td>email</td>  
		                    <td><input type="text" name="email" /></td>  
		                </tr>
		                <tr> 
		                    <td>OR</td> 
		                </tr>  
		                <tr>  
		                    <td>phone</td>  
		                    <td><input type="text" name="phone" /></td>  
		                </tr>  
		                <tr>  
		                    <td><input type="submit" value="Create complaint" /></td>  
		                </tr>  
		            </table>  
		    	</form>  
			</td>
			<td ROWSPAN=2>
		    	<form action="loginServlet" method="post"> 
					<table>  
		                <tr>  
		                    <td>budge</td>  
		                    <td><input type="text" name="username" required="required" /></td>  
		                </tr> 
		                <tr>  
		                    <td>Password</td>  
		                    <td><input type="password" name="userpass" required="required" /></td>  
		                </tr>   
		                <tr>  
		                    <td><input type="submit" value="SignIn" /></td>  
		                </tr>  
		            </table> 
		    	</form>
				<form action="signUpPage" method="post"> 
					<a href="1.html"><input type="submit" value="SingUp" />
		    	</form>
			</td></tr>
			<tr><td>
		    	<form action="createReportAnon" method="post"> 
					<table>  
		                <tr>  
		                    <td>Anonymous</td>  
		                </tr>   
		                <tr>  
		                    <td><input type="submit" value="Create complaint" /></td>  
		                </tr>  
		            </table> 
		    	</form>
			</td></tr>
		</table>    
	</body>  
</html> 