package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.MachineDAOimpl;
import entity.Machine;

@WebServlet("/MachineSaveServlet")
public class MachineSaveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String machineTypeId = request.getParameter("machineTypeId");
		String country = request.getParameter("country");
		String zipCode = request.getParameter("zipCode");
		String city = request.getParameter("city");
		String address = request.getParameter("address");

		Machine newMachine = new Machine();
		newMachine.setMachineTypeId(Integer.parseInt(machineTypeId));
		newMachine.setCoutry(country);
		newMachine.setZipCode(Integer.parseInt(zipCode));
		newMachine.setCity(city);
		newMachine.setAddress(address);
		int status = new MachineDAOimpl().addMachine(newMachine);

//		request.setAttribute("status", status);

		if (status > 0) {
			response.sendRedirect("MachineViewServlet");
		} else {
			System.out.println("Nem sikerült az adatok hozzáadása !");
//			request.getRequestDispatcher("machines.jsp").forward(request, response);
		}

	}

}
