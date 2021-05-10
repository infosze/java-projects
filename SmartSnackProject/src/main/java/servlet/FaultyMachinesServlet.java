package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FaultyMachinesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Query query = new FaultyMachinesQuepyImpl();
		//List<List<String>> list = query.findDataForQuery();
		//request.setAttribute("faultyMachineList", list);
		//boolean islistEmpty = list.isEmpty();
		//request.setAttribute("islistEmpty", islistEmpty);
		request.getRequestDispatcher("faultyMachines.jsp").forward(request, response);
	}

}
