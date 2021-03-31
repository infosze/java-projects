package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Query;
import dao.impl.Top10MachinesQueryImpl;

public class Top10MachinesQueryServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
		LocalDate endDate = LocalDate.parse(req.getParameter("endDate"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
		Query query = new Top10MachinesQueryImpl(startDate, endDate);
		List<List<String>> machines = query.findDataForQuery();
		req.setAttribute("machineList", machines);
		req.setAttribute("startDate", formatter.format(startDate));
		req.setAttribute("endDate", formatter.format(endDate));
		
		req.getRequestDispatcher("top10Machines.jsp").forward(req, resp);
	}

}
