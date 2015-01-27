<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Welcome <%=session.getAttribute("name")%></title>  


</head>  
<body>  
    <h3>Login successful!!!</h3>  
    <h4>  
        Hello,  
        <%=session.getAttribute("name")%></h4> 
    
    <%=session.getAttribute("table")%> 


<script>
function popUpWindow(url,plateNumber,description) {
    var myWindow = window.open("", "MsgWindow", "width=600, height=420");
    myWindow.document.write("<table frame=\"box\" border=2><tr><td ROWSPAN=2><iframe width=\"420\" height=\"345\" src=\""+
    		url+"\"></iframe></td><td>Plate number:<br>"+
    		plateNumber+"</td></tr><tr><td>Description:<br>"+
    		description+"</td></tr></table>");
}
</script>

</body>  
</html>