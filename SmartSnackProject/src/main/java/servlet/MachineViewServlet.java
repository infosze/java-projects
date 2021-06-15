package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MachineDAO;
import dao.impl.MachineDAOimpl;
import entity.Machine;

@WebServlet("/MachineViewServlet")
public class MachineViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MachineDAO machineDAO = new MachineDAOimpl();
		List<Machine> machines = machineDAO.getAllMachines();
		request.getSession().setAttribute("machines", machines);
		request.getRequestDispatcher("machines.jsp").forward(request, response);
	}

}
