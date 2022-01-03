package pack.LoginSystem;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameInput = request.getParameter("usernameInput");
		String passwordInput = request.getParameter("passwordInput");
		
		
		
		ServletContext context = request.getServletContext();
		AccountManager accountManager = (AccountManager) context.getAttribute("accountManager");
		//request.setAttribute("userName", usernameInput);
		request.setAttribute("userName", accountManager.getLastAddedPerson());
		
		if(accountManager.createAccount(usernameInput, passwordInput)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("accountInUse.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}