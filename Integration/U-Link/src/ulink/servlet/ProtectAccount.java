package ulink.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ProtectAccount
 */
@WebFilter("/ProtectAccount")
public class ProtectAccount implements Filter {

    /**
     * Default constructor. 
     */
    public ProtectAccount() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		
		
		HttpSession session = req.getSession();
		String roles = (String)session.getAttribute("admin");
		System.out.println(roles + " QWEWQRQWEW ");
		if(!roles.equals("admin") && !uri.contains("css") && !uri.contains("js")){
			
			res.sendRedirect("./index.html");
			return;
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
			return;
		}
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
