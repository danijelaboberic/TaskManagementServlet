package dmi.ris.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dim.ris.controller.UserController;
import dim.ris.model.User;
import dmi.ris.utility.BcryptUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("logout")) {
			 request.getSession().invalidate();
			 request.getRequestDispatcher("/login.jsp").forward(request,response);
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("login")) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				User u = UserController.login(username);
				if (u != null /*&& BcryptUtil.verifiy(password, u.getPassword())*/) {		
					 request.getSession().setAttribute("logedUser", u);	
					 request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
				} else {
					request.setAttribute("loginFailed", true);	
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				
		 }
	}

}
