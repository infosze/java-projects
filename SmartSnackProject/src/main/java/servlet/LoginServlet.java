package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Query;
import dao.impl.LogInQueryImpl;
import entity.PasswordUtils;
import entity.User;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
				
		Query query = new LogInQueryImpl(name);
		List<List<String>> list = query.findDataForQuery();
		List<String> userDatas = new ArrayList<>();
		if(!list.isEmpty()) {
			userDatas = list.get(0);
		} else {
			userDatas.add(0, " ");
			userDatas.add(1, " ");
			userDatas.add(2, " ");
		}
//		String userName = userDatas.get(0);
//		String userPassword = userDatas.get(1);
//		String userSalt = userDatas.get(2);

		User user = new User();
//		user.setName(userName);
//		user.setPassword(userPassword);
		user.setName(name);
		user.setPassword(password);
		
//		boolean passwordMatch = PasswordUtils.verifyUserPassword(password, userPassword, userSalt);

		if (user.getName().equals(name) && user.getPassword().equals(password)) {
//		if (user.getName().equals(name) && passwordMatch == true) {
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("loggedInUser", user.getName());
			request.getRequestDispatcher("tasks").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
