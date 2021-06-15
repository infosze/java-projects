package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.MachineDAOimpl;
import entity.Machine;

public class MachineEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("machineId");
		Machine machine = new  MachineDAOimpl().getMachineById(id);
		request.setAttribute("machine", machine);
		
		request.getRequestDispatcher("editmachine.jsp").forward(request, response);
	}

}
