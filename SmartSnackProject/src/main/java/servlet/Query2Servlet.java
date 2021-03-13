package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AutomatDAO;
import entity.Automat;
import servlet.listener.HourlyTasks;

public class Query2Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AutomatDAO automatDAO = new AutomatDAO();
		List<Automat> list = automatDAO.getOfflineMachines();

		request.setAttribute("automatList", list);
		request.getRequestDispatcher("query2.jsp").forward(request, response);
	}

}