package dmi.ris.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="loginFilter",urlPatterns = { "/pages/*","/UserServlet/*"})
public class LoginFilter implements Filter {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//samo logovani korisnici mogu pristupiti ovim stranicama
		if(((HttpServletRequest) request).getSession().getAttribute("logedUser")!=null){
			chain.doFilter(request, response);
		}else {	
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	

}














