<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="uk.ac.le.cs.CO3098.bean.*"%><% 
HttpSession se=request.getSession();
User u=null;
if(se.getAttribute("User")!=null){
 	u=(User)se.getAttribute("User");
}else{
	response.sendRedirect("error.jsp?errorid=2");
}
if(u!=null){
%>
<html>
<head><title>User page</title></head>
<body>
<style>
form {
 width: 300px;
 overflow:hidden;}
label {
 clear: both;
 float: left;
 width: 40%;}
input {
 float: left;
 width: 55%;}
</style>
<h1>Hello</h1>
<div>
Welcome, <%= u.getFullname() %>! <br/>
<a href="./servlets/Logout">Logout</a>
</div>
</body></html>
<%}%>
