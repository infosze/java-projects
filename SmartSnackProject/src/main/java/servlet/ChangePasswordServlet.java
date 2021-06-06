package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dao.impl.ChangePasswordImpl;
import util.PasswordUtils;

@WebServlet(urlPatterns = "/passwordChange")
public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String name = request.getParameter("name");

		String name = (String) request.getSession().getAttribute("loggedInUser");
		String password = request.getParameter("newPassword");

		// Generate Salt. The generated value can be stored in DB.
		String salt = PasswordUtils.getSalt(30);

		// Protect user's password. The generated value can be stored in DB.
		String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);

		UserDAO userDao = new ChangePasswordImpl(name, mySecurePassword, salt);
		int successed = userDao.updateUserPassword();
		request.setAttribute("successed", successed);
		request.getRequestDispatcher("passwordChange.jsp").forward(request, response);
	}

}
