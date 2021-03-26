package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Query;
import dao.impl.RefillsQueryImpl;

public class RefillsQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate startLocalDate = LocalDate.parse(request.getParameter("startDate"));
		LocalDate endLocalDate = LocalDate.parse(request.getParameter("endDate"));
		Query query = new RefillsQueryImpl(startLocalDate, endLocalDate);
		List<List<String>> list = query.findDataForQuery();
		for(List<String> l : list) {
			System.out.println(l.toString());
		}
		request.setAttribute("product_machineList", list);
		request.getRequestDispatcher("refills.jsp").forward(request, response);
	}

}
