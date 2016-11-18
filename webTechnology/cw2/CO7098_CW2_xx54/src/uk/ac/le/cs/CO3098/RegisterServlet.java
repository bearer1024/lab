package uk.ac.le.cs.CO3098;

import java.io.IOException;

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
		int codeStatus = userDAO.checkCodeStatus(securityCode);
		switch(codeStatus){
		
		case 1:
			int result = userDAO.createUser(userName, userEmail, userFullName, dateOfBirth,homeAddress,password,securityCode);
			if (result == 1) {
				response.sendRedirect("success.jsp");
			}
			break;
		case 2:
			System.out.println("this code is used");
			break;
		case 3:
			System.out.println("this code doesn't exist");
			break;
		}

		
	}
}