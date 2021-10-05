package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dao.impl.UserDAOimpl;
import entity.User;
import util.PasswordUtils;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		UserDAO userDao= new UserDAOimpl();
		User user = userDao.findUserByName(name);
		 
//		boolean passwordMatch = PasswordUtils.verifyUserPassword(password, user.getPassword(), user.getSalt());   //TODO   INACTIVATED
		
//		if (user.getName().equals(name)&& passwordMatch == true) {  //TODO   INACTIVATED
		if (name.equals(user.getName())) {                                     
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("loggedInUser", user);
			response.sendRedirect("tasks");
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
		

}
