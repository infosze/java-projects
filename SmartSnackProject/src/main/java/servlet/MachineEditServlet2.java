package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.MachineDAOimpl;
import entity.Machine;

@WebServlet("/MachineEditServlet2")  
public class MachineEditServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String machineId = request.getParameter("machineId");
		String machineTypeId = request.getParameter("machineTypeId");
		String country = request.getParameter("country");
		String zipCode = request.getParameter("zipCode");
		String city = request.getParameter("city");
		String address = request.getParameter("address");

		Machine updatedMachine = new Machine();
		updatedMachine.setMachineId(machineId);
		updatedMachine.setMachineTypeId(Integer.parseInt(machineTypeId));
		updatedMachine.setCoutry(country);
		updatedMachine.setZipCode(Integer.parseInt(zipCode));
		updatedMachine.setCity(city);
		updatedMachine.setAddress(address);
		boolean status = new MachineDAOimpl().modifyMachine(updatedMachine);

		request.setAttribute("status", status);
		
		if(status){  
            response.sendRedirect("MachineViewServlet");  
        }else{  
            System.out.println("Nem sikerült az adatok módosítása !");  
            request.getRequestDispatcher("machines.jsp").forward(request, response);
        }  

	}

}
