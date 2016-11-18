package uk.ac.le.cs.CO3098;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.ac.le.cs.CO3098.bean.User;
import uk.ac.le.cs.CO3098.bean.UserVerification;


public class Login extends HttpServlet {
		public void doGet(HttpServletRequest req, 
				        HttpServletResponse res) 
		            throws ServletException, IOException {

			PrintWriter out = res.getWriter();	
			String name=req.getParameter("name");
			String pass=req.getParameter("pass");
			
			UserVerification dbOperator=new UserVerification();
			User u=dbOperator.checkUser(name, pass);
			HttpSession se=req.getSession();

			if(u!=null){
				se.setAttribute("User",u);
				res.sendRedirect("../user.jsp");
			}else{
				res.sendRedirect("../error.jsp?errorid=1");
			}
			out.close();
		}
		public void doPost(HttpServletRequest req, 
					HttpServletResponse res) 
		            throws ServletException, IOException {
			doGet(req, res);}
	}


