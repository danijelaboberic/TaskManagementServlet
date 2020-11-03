package dmi.ris.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dim.ris.controller.JPAUtil;
import dim.ris.controller.UserController;
import dim.ris.model.Role;
import dim.ris.model.User;
import dmi.ris.utility.BcryptUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("delete")) {
			  int id = Integer.parseInt(request.getParameter("userId"));
		       UserController.delete(id);
		}
		List<User> users = UserController.getAllUsers();
		  request.setAttribute("users" , users);  
		  request.getRequestDispatcher("/pages/registerUser.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
	  User u = new User();
	  u.setName(request.getParameter("name"));
	  u.setUsername(request.getParameter("username"));
	  u.setPassword(BcryptUtil.hash(request.getParameter("password")));
	  int idRole = Integer.parseInt(request.getParameter("role"));
	  Role r = UserController.getRole(idRole);
	  u.setRole(r);
	  boolean ok = UserController.register(u);
	  if(ok) {
		  List<User> users = UserController.getAllUsers();
		  request.setAttribute("users" , users); 
		  request.setAttribute("message" , "Uspešno je registrovan korisnik!"); 
	  }else {
		  request.setAttribute("message" , "Već postoji uneto korisnicko ime."); 
	  }
	  request.getRequestDispatcher("/pages/registerUser.jsp").forward(request,response);
	}

}
