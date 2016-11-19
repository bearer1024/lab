package uk.ac.le.cs.CO3098; 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.ac.le.cs.CO3098.bean.User;
import uk.ac.le.cs.CO3098.bean.UserDAO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String error;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		UserDAO userDAO = new UserDAO();
		User user = null;
		user = userDAO.verifyUser(email, password);
		if (user == null) {
			error = "Invalid Email or password";
			session.setAttribute("error", error);
			response.sendRedirect("../errorForLogin.jsp?errorid=1");
		} else {
			session.setAttribute("User", user);
			response.sendRedirect("../user.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if ("logout".equalsIgnoreCase(request.getParameter("param"))) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
	}
}