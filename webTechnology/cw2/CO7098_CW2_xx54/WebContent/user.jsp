<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="uk.ac.le.cs.CO3098.bean.*"%><% 
HttpSession se=request.getSession();
/* User user=null; */
String userName;
User user = null;
if(se.getAttribute("User")!=null){
	user=(User)se.getAttribute("User");
}else{
	response.sendRedirect("errorForLogin.jsp");
}
if(user!=null){
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
Welcome, <%= user.getUserName()%>! <br/>
<p><a href="voting.jsp">go to vote </a></p>
<a href="./servlets/Logout">Logout</a>
</div>
</body></html>
<%}%>
