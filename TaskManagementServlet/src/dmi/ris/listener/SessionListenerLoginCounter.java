package dmi.ris.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dim.ris.model.User;

@WebListener
public class SessionListenerLoginCounter implements HttpSessionListener, HttpSessionAttributeListener{
	ServletContext ctx=null;  
    static int total=0;
    static int current=1; 
    
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		User u=(User)se.getValue(); 
		System.out.println("logovao se korisnik " +u.getName());
		ctx=se.getSession().getServletContext();  
		System.out.println("Do sada je ukupno puta pristupljeno aplikaciji "+ ctx.getAttribute("totalusers"));
		System.out.println("Broj trenutno prijavljenih korisnika "+ ctx.getAttribute("currentusers"));

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		HttpSessionAttributeListener.super.attributeReplaced(se);
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		total = total +1;  
	    current = current +1; 
	    ctx=se.getSession().getServletContext();  
	    ctx.setAttribute("totalusers", total);  
	    ctx.setAttribute("currentusers", current); 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		 current--;  
	     ctx.setAttribute("currentusers",current);  
	}

}

 
