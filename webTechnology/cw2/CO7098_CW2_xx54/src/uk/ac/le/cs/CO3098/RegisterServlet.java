package uk.ac.le.cs.CO3098;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.le.cs.CO3098.bean.UserDAO;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("email");
		String userFullName = request.getParameter("fullname");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String homeAddress = request.getParameter("homeAddress");
		String securityCode = request.getParameter("securityCode");
		String password = request.getParameter("password");

		UserDAO userDAO = new UserDAO();
		
		if(!userDAO.emailIsRegistered(userEmail)){
			int codeStatus = userDAO.checkCodeStatus(securityCode);
			switch(codeStatus){
			
			case 1:
				int result = userDAO.createUser(userName, userEmail, userFullName, dateOfBirth,homeAddress,password,securityCode);
				if (result == 1 && userDAO.markCodeBeUsed(securityCode)) {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					String loginUrl = "../login.html";
					out.println("<html><head><title>register successful" +  
					     "</title></head>"); 
					out.println("<body><h1>register successful</h1>");
					out.println("<p><b><a href='"+loginUrl+"'>");
					out.println("clik here to login<a></b></p>");
					out.println("</body></html>"); 
//					response.sendRedirect("../login.html");
				}
				break;
			case 2:
				response.sendRedirect("../errorForRegister.jsp?errorid=2");
	//			System.out.println("this code is used");
				break;
			case 3:
				response.sendRedirect("../errorForRegister.jsp?errorid=3");
	//			System.out.println("this code doesn't exist");
				break;
			default:
				PrintWriter out = response.getWriter();
				out.println("unexcepted error 10001 in register");
			}
		}else{
				response.sendRedirect("../errorForRegister.jsp?errorid=1");
	//			System.out.println("this email has been registered");
		}
		
	}
}