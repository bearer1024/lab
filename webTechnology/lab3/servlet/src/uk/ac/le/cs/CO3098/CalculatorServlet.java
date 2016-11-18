package uk.ac.le.cs.CO3098;

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;

public class CalculatorServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, 
			        HttpServletResponse res) 
	            throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		String num1_str=req.getParameter("num1");
		String num2_str=req.getParameter("num2");
		String operator_str=req.getParameter("operator");
		
		try{
		 int num1= Integer.parseInt(num1_str);
		 int num2=Integer.parseInt(num2_str);
		 int result=0;
		 switch(operator_str){
		 case "+": result=num1+num2 ;break;
		 case "-": result=num1-num2;break;	 
		 case "*": result=num1*num2;break;
		 case "%": result=num1/num2;break;
		 default :
			 throw new IllegalArgumentException("Invalid input(s).");
		 } 	
		 
		 
		}catch(Exception ex){
			System.out.println(ex.toString());
			res.sendRedirect("../calculator.html");
		}
		out.close();
		}
	public void doGet(HttpServletRequest req, 
				HttpServletResponse res) 
	            throws ServletException, IOException {
		doGet(req, res);}
}