package uk.ac.le.cs.CO3098;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.ac.le.cs.CO3098.bean.DBConnection;
import uk.ac.le.cs.CO3098.bean.HashGenerator;
import uk.ac.le.cs.CO3098.bean.UserDAO;

/**
 * Servlet implementation class LoginForAdminServlet
 */
@WebServlet("/LoginForAdminServlet")
public class LoginForAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  boolean verifyAdmin(String email, String password) {
		final String SQL_SELECT = "SELECT * FROM admin WHERE name = ? AND password = ?";

		try {
			
			PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(SQL_SELECT);
			pStmt.setString(1, email);
			String passwordHash = HashGenerator.getSHA256(password);
			pStmt.setString(2, passwordHash);
			try(ResultSet rs = pStmt.executeQuery();){
			if (rs.next()) {
			return true;
			}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginForAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if(verifyAdmin(name,password)){
			session.setAttribute("admin", name);
			response.sendRedirect("../manage.jsp");
		}else{
			response.sendRedirect("../errorForAdminLogin.jsp?errorid=1");
		}
	
	
	}

}
