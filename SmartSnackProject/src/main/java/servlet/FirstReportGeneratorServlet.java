package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generator.FirstReportGenerator;
import generator.MachineGenerator;

public class FirstReportGeneratorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		new FirstReportGenerator().run();

		req.getRequestDispatcher("settings.jsp").forward(req, resp);
	}

}
