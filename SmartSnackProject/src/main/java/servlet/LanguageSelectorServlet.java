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
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/language")
public class LanguageSelectorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();   // a path változó a jsp-ben a session-ben van beállítva ezért kell egy session.
		String uri = session.getAttribute("path").toString(); // lekérjük a path -ot.
		String jspName = uri.substring(uri.lastIndexOf("/") + 1); // kiszedjük a teljes elérési útból az AKTUÁLISOLDAL.jsp -t
		String language = req.getParameter("language");
		ResourceBundle rb = Objects.equals(language, "Magyar") ? ResourceBundle.getBundle("Resources", new Locale("hu"))
				: ResourceBundle.getBundle("Resources", new Locale("en"));
		req.getSession().setAttribute("resource", rb);
		req.getRequestDispatcher(jspName).forward(req, resp);
	}

}
