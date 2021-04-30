package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Query;
import dao.impl.FaultyMachinesQuepyImpl;

public class Query2Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Query query = new FaultyMachinesQuepyImpl();
		List<List<String>> list = query.findDataForQuery();
		request.setAttribute("product_machineList", list);
		boolean islistEmpty = list.isEmpty();
		request.setAttribute("islistEmpty", islistEmpty);
		request.getRequestDispatcher("query2.jsp").forward(request, response);
	}

}
