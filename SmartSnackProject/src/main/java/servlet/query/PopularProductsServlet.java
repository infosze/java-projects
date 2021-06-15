package servlet.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Query;
import dao.impl.PopularProductsQueryImpl;

public class PopularProductsServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int year = Integer.parseInt(req.getParameter("year"));
		int month = req.getParameter("month").equals("") ? 0 : Integer.parseInt(req.getParameter("month"));
		
		Query query = new PopularProductsQueryImpl(year, month);
		List<List<String>> products = query.findDataForQuery();
		
		req.setAttribute("year", String.format("%d.", year));
		req.setAttribute("month", month > 0 ? String.format("%d.", month) : "");
		
		req.setAttribute("productList", products);
		req.getRequestDispatcher("popularProducts.jsp").forward(req, resp);
		
	}

}
