package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AutomatDAO;
import dao.Query;
import dao.impl.AutomatDAOimpl;
import dao.impl.FaultyMachinesQuepyImpl;
import entity.Automat;

public class TasksServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AutomatDAO automatDAO = new AutomatDAOimpl();
		List<Automat> soldOutProductMachinesList = automatDAO.getSoldOutProductMachines();
		Query query = new FaultyMachinesQuepyImpl();
		List<List<String>> list = query.findDataForQuery();
		request.getSession().setAttribute("isEmptyQ1", soldOutProductMachinesList.isEmpty());
		request.getSession().setAttribute("listSizeQ1", soldOutProductMachinesList.size());
		request.getSession().setAttribute("isEmptyQ2", list.isEmpty());
		request.getSession().setAttribute("listSizeQ2", list.size());
		request.getRequestDispatcher("tasks.jsp").forward(request, response);
	}

}
