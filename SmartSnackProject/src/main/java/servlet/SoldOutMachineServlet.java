package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Query;
import dao.impl.SoldOutMachines;

public class SoldOutMachineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Query query = new SoldOutMachines();
		List<List<String>> machines = query.findDataForQuery();
		boolean islistEmpty = machines.isEmpty();
		request.setAttribute("islistEmpty", islistEmpty);
		request.setAttribute("machineList", machines);
		request.getRequestDispatcher("soldOutMachines.jsp").forward(request, response);
	}

}
