package servlet;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resources.MyResources;
import resources.MyResources_en;

@WebServlet(urlPatterns = "/language")
public class LanguageSelectorServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String language = req.getParameter("language");
		
		ResourceBundle rb;
		if (Objects.equals(language, "English")) {
			rb = new MyResources_en();
		} else {
			rb = new MyResources();
		}
		req.getSession().setAttribute("resource", rb);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}

}
