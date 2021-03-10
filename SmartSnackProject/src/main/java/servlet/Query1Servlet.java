package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Automat;
import servlet.listener.FindErrorHourly;


public class Query1Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		FindErrorHourly error = new FindErrorHourly();
		List<Automat> list = FindErrorHourly.getWrongMachines(); //new AutomatErrorDAO().findSoldOutProductMachines();
//		for (Automat e : list) {
//			System.out.println(e.toString() + " ----> servlet---- termék kifogyott...");  // TODO test. fix it later.
//		}
		
//		List<Automat> list = List.of(new Automat("ss000006", 4, "hungary", 1111, "pest", "fehérvári út"));
		request.setAttribute("automatlist", list);
		request.getRequestDispatcher("query1.jsp").forward(request, response);
	}

}
