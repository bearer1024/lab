package uk.ac.le.cs.CO3098;

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, 
			        HttpServletResponse res) 
	            throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("holy shit"); 
		out.println("what the fuck"); 
		out.close();}
	public void doPost(HttpServletRequest req, 
				HttpServletResponse res) 
	            throws ServletException, IOException {
		doGet(req, res);}
}

