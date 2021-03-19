package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Queries;

public class Query4Servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
		LocalDate endDate = LocalDate.parse(req.getParameter("endDate"));
		
		Queries query = new Queries();
		List<List<String>> machines = query.findTopMachines(startDate, endDate);
		
		req.setAttribute("machineList", machines);
		req.getRequestDispatcher("query4.jsp").forward(req, resp);
	}

}
