package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TasksServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		User user =(User)request.getSession().getAttribute("loggedInUser");
//		TaskDao taskDao = new TaskDaoImp();
//		List<Task> tasks = taskDao.findAllTasksByUser(user);
//		
//		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("tasks.jsp").forward(request, response);
	}

}
