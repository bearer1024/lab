<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My first JSP</title>
</head>
<body>
<% 
int count=1;
try{
	String count_str=request.getParameter("count");
	if(count_str!=null){
		count=Integer.parseInt(count_str);
	}
}catch(Exception ex){
	out.println(ex.toString());
	ex.printStackTrace();
}	
out.println("<h1>Prints "+count+" line(s) </h1>");
for(int i=1;i<=count;i++){
%>
This is line No. <%=i%> <br/>
<%}%>
</body>
</html>