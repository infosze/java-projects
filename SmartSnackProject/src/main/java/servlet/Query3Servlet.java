package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Queries;

public class Query3Servlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int year = Integer.parseInt(req.getParameter("year"));
		int month = req.getParameter("month").equals("") ? 0 : Integer.parseInt(req.getParameter("month"));
		
		Queries query = new Queries();
		List<List<String>> products = query.findQueryData(year, month);
		
		req.setAttribute("productList", products);
		req.getRequestDispatcher("query3.jsp").forward(req, resp);
		
	}

}
