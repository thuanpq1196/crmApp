package filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.SecurityConfig;
import crm_app07.services.UserServices;
import utils.GetCookie;

@WebFilter(filterName="authorFilter", servletNames = {"jobController", "roleController", "userController", "taskController", "profileController"})
public class AuthorizationFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String strServlet = req.getServletPath();
			Cookie role = GetCookie.getCookieByKey("role", req, res);
			if(role == null) {
				res.sendRedirect(req.getContextPath()+"/login");
			}else {
				String roleName = role.getValue();
				if(SecurityConfig.getAllAppRoles().contains(roleName)) {
					List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(roleName);
					if(urlPatterns.contains(strServlet)) {
						chain.doFilter(req, res);
					}else {
						req.getRequestDispatcher("403.jsp").forward(req, res);
					}
				}
				else {
					req.getRequestDispatcher("404.html").forward(req, res);
				}
			}
	}		
}
