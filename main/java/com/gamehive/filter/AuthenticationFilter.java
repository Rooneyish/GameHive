package com.gamehive.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.gamehive.util.SessionUtil;
import com.gamehive.util.StringUtil;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter extends HttpFilter implements Filter {

	private static final String LOGIN = "/login";
	private static final String REGISTER = "/register";
	private static final String HOME = "/home";
	private static final String ADMIN = "/admin";
	private static final String GAMERPORTAL = "/gamerportal";
	private static final String ABOUT = "/about";
	private static final String CONTACT = "/contact";
	private static final String ROOT = "/";

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public AuthenticationFilter() {
		super();
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// Cast request and response objects to HttpServletRequest and
		// HttpServletResponse for type safety
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get the requested URI
		String uri = req.getRequestURI();

		// Allow access to static resources (CSS, JS, images) and the index page without
		// checking
		// login
		if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")
				|| uri.endsWith(".jpeg") || uri.endsWith(".gif") || uri.endsWith(".svg")
				|| uri.contains("/resources/")) {
			chain.doFilter(request, response);
			return;
		}

		Object username = SessionUtil.getAttribute(req, StringUtil.USERNAME);
		Object role = SessionUtil.getAttribute(req, "role");

		boolean isLoggedIn = username != null;
		
		
		if (!isLoggedIn) {
			if (uri.endsWith(ABOUT) ||uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.equals(req.getContextPath() + "/")
					|| uri.equals("/")) {
				chain.doFilter(request, response);
				return;
			} else {
				res.sendRedirect(req.getContextPath() + ROOT);
				return;
			}
		}

		if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
			if ("admin".equals(role)) {
				res.sendRedirect(req.getContextPath() + ADMIN);
				return;
			} else if ("gamer".equals(role)) {
				res.sendRedirect(req.getContextPath() + GAMERPORTAL);
				return;
			}
		}

		if (uri.endsWith(GAMERPORTAL) && "admin".equals(role)) {
			res.sendRedirect(req.getContextPath() + ADMIN);
			return;
		}

		if (uri.endsWith(ADMIN) && "gamer".equals(role)) {
			res.sendRedirect(req.getContextPath() + GAMERPORTAL);
			return;
		}

		chain.doFilter(request, response);
	}

	// pass the request along the filter chain

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
