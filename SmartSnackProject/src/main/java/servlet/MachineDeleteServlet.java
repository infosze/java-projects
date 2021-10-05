package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.MachineDAOimpl;

@WebServlet("/MachineDeleteServlet")
public class MachineDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String machineId = request.getParameter("machineId");
		
		int	status = new MachineDAOimpl().deleteMachine(machineId);

//		request.setAttribute("status", status);

		if (status > 0) {
			response.sendRedirect("MachineViewServlet");
		} else {
			System.out.println("Nem sikerült az adatok törlése !");
//			request.getRequestDispatcher("machines.jsp").forward(request, response);
		}

	}

}
