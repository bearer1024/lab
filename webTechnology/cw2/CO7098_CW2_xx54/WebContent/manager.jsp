<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%
HttpSession se = request.getSession();
String identify =(String) se.getAttribute("admin");
if(identify!="admin"){
	response.sendRedirect("errorForAdminLogin.jsp");
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage voting here</title>
</head>
<body>
<p><input type="button" onclick="location.href='./editQuestion.jsp'" value="edit question and options"></p>
<p><input type="button" onclick="location.href='./setDeadline.jsp'" value="set deadline"></p>
<p><input type="button" onclick="location.href='./viewResults.jsp'" value="view results"></p>
</body>
</html>