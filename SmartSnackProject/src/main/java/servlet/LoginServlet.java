package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		// TODO fix it (secure login) !!!!
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		//

		if (user.getName() == name && user.getPassword() == password) {
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("loggedInUser", user.getName());
			request.getRequestDispatcher("tasks.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
