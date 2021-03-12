package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Automat;
import servlet.listener.FindErrorHourly;

public class Query1Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Automat> list = FindErrorHourly.getWrongMachines();

		request.setAttribute("automatList", list);
		request.getRequestDispatcher("query1.jsp").forward(request, response);
	}

}
