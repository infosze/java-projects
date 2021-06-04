package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Query;
import dao.impl.FaultyMachinesQuepyImpl;
import dao.impl.SoldOutMachines;

public class TasksServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getSession().getAttribute("loggedInUser");
		System.out.println(name);
		
		
		Query soldOutQuery = new SoldOutMachines();
		List<List<String>> machines = soldOutQuery.findDataForQuery();

		Query faultyQuery = new FaultyMachinesQuepyImpl();
		List<List<String>> machines2 = faultyQuery.findDataForQuery();

		request.getSession().setAttribute("soldOutMachinesList", machines);
		request.getSession().setAttribute("faultyMachinesList", machines2);
		request.getRequestDispatcher("tasks.jsp").forward(request, response);
		
	}

}
