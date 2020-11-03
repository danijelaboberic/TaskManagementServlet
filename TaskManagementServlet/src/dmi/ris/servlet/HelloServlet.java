package dmi.ris.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/helloCookie")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String lastVisitedPage = null;
		
		if ((username!=null && password!=null)&&(username.compareTo("danijela")==0) && (password.compareTo("123456")==0)) {
			lastVisitedPage="/pages/home.jsp";
			request.getSession().setAttribute("last", lastVisitedPage);
			Cookie cookie = new Cookie("lastVisitedPage", lastVisitedPage);
			cookie.setMaxAge(60); //60s je validan cookie
			response.addCookie(cookie);		
		}else {
	       Cookie [] cookies = request.getCookies();
	       if (cookies != null) {
	        for (Cookie c : cookies) {
	    	      if (c.getName().equalsIgnoreCase("lastVisitedPage")) {
	    	    	  lastVisitedPage = c.getValue();
	    		   break;
	    	       }
	         }
	       }
		}
	    if(lastVisitedPage !=null) {
	    	request.getRequestDispatcher(lastVisitedPage).forward(request, response);
	    }else {
	    	
		PrintWriter out = response.getWriter();
		out.append("<html>");
		out.append("<body>");
		out.append("<h3>Došlo je do greške. Pogrešno korisničko ime ili lozinka</h3>");
		out.append("</body>");
		out.append("</html>");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Hello "+request.getParameter("name"));
	}

}
