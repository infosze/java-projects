package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Queries;

public class Query5Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate localDate = LocalDate.parse(request.getParameter("date"));
		Queries q = new Queries();
		List<List<String>> list = q.findSoldOutCoinMachine(localDate);
		request.setAttribute("machineList", list);
		request.getRequestDispatcher("query5.jsp").forward(request, response);
	}

}
