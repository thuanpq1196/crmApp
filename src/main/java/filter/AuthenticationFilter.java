package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "authenFilter", servletNames = {"loginController"})
public class AuthenticationFilter extends HttpFilter{	

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if(!req.getServletPath().equals("/login") && req.getCookies() != null) {
			Cookie[] cookies = req.getCookies();
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("logined") && cookie.getValue().equals("true")) {
						chain.doFilter(req, res);
						return;
					}
				}
			res.sendRedirect(req.getContextPath()+"/login");
		}else
			chain.doFilter(req, res);
	}
	
	

}
