package servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/language")
public class LanguageSelectorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String language = req.getParameter("language");
		ResourceBundle rb = Objects.equals(language, "Magyar") ? ResourceBundle.getBundle("Resources", new Locale("hu"))
				: ResourceBundle.getBundle("Resources", new Locale("en"));
		req.getSession().setAttribute("resource", rb);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}

}
