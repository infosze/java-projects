package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Query;
import dao.impl.RunOutOfCoinsQueryImpl;

public class RunOutOfCoinsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate localDate = LocalDate.parse(request.getParameter("date"));
		Query query = new RunOutOfCoinsQueryImpl(localDate);
		List<List<String>> list = query.findDataForQuery();
		request.setAttribute("machineList", list);
		request.getRequestDispatcher("runOutOfCoins.jsp").forward(request, response);
	}

}
