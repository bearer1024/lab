<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link rel="stylesheet"
href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.cs
s">
 <script
src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"><
/script>
 <script
src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
</script>
    <title>Create an account</title>
    <link type="text/css" href="/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<h2>Create new Account</h2>
<form action="/account/save" method="post">
    <table class="table table-bordered">
        <tbody>
            <!--  <tr><th>id</th><td><input type="text" name="id" required="required"></td></tr>-->
            <tr><th>firstName</th><td><input type="text" name="firstName" required="required"></td></tr>
            <tr><th>lastName</th><td><input type="text" name="lastName" required="required"></td></tr>
           <tr><td colspan="2"><a href="account/listAll" class="btn btn-primary">Back</a> <input type="submit" value="Add" class="btn btn-success"></tr>
        </tbody>
    </table>
    
</form>

<script type="application/javascript" src="js/jquery.js"></script>
<script type="application/javascript" src="js/bootstrap.js"></script>


</body>
</html>