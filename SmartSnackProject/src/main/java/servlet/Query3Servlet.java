package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoimpl.PopularProductsDAO;
import entity.PopularProduct;

public class Query3Servlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"));
		
		PopularProductsDAO popularProdDao = new PopularProductsDAO(year, month);
		List<PopularProduct> products = popularProdDao.getPopularProducts();
		
		req.setAttribute("productList", products);
		req.getRequestDispatcher("query3.jsp").forward(req, resp);
		//resp.sendRedirect(req.getServletContext().getContextPath() + "/query3");
		
		
	}

}
