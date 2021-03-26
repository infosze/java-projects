package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Queries;

public class Query6Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate startLocalDate = LocalDate.parse(request.getParameter("startDate"));
		LocalDate endLocalDate = LocalDate.parse(request.getParameter("endDate"));
		Queries q = new Queries();
		List<List<String>> list = q.findUploads(startLocalDate, endLocalDate);
		request.setAttribute("product_machineList", list);
		request.getRequestDispatcher("query6.jsp").forward(request, response);
	}

}
