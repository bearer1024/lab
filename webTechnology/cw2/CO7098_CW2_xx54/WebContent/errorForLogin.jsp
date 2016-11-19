<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head><title>Error</title></head>
<body>
<style>
form {
 width: 500px;
 overflow:hidden;}
label {
 clear: both;
 float: left;
 width: 40%;}
input {
 float: left;
 width: 55%;}
</style>
<h1>Error</h1>
<%
String errorMsg="Access denied";
String errorid=request.getParameter("errorid");
if(errorid!=null){
	if(errorid.equals("1")){
		errorMsg+= " - This email is already used";
	}else if(errorid.equals("2")){
		errorMsg+= " - This code is already used.";
	}else if(errorid.equals("3")){
		errorMsg+=" - This code is invalid.";
	}else{
		errorMsg+=" - You are not authorized to access this page.";
	}
}else{
	 errorMsg+=" - An unexpected error has occurred.";
}
%>
<div>
	<label for="link"><%=errorMsg%></label>
	<label>Please <a href="login.html">login</a></label>
</div>
</body></html>
