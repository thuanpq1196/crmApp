package crm_app07.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.RoleEntity;
import crm_app07.services.RoleServices;

@WebServlet(name = "roleController", urlPatterns = {"/roles","/role-update","/role-add","/role-delete"})
public class RoleController extends HttpServlet{
	
	private RoleServices rs = new RoleServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = 0;
		switch(req.getServletPath()) {
		case ("/roles"):
			List<RoleEntity> roles = rs.findAll();
			req.setAttribute("roles", roles);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		case ("/role-update"):
			id = Integer.parseInt(req.getParameter("id"));
			RoleEntity role = rs.findByID(id);
			req.setAttribute("role", role);
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		case ("/role-delete"):
			id = Integer.parseInt(req.getParameter("id"));
			rs.deleteRole(id);
			resp.sendRedirect(req.getContextPath()+"/roles");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = 0;
		String name = "";
		String description = "";
		req.setCharacterEncoding("utf-8");
		switch(req.getServletPath()) {
		case ("/role-add"):
			name = req.getParameter("name");
			description = req.getParameter("description");
			if(rs.addRole(name, description)) {
				resp.sendRedirect(req.getContextPath()+"/roles");
			}else {
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			}
			break;
		case ("/role-update"):
			id = Integer.parseInt(req.getParameter("id"));
			name = req.getParameter("name");
			description = req.getParameter("description");
			RoleEntity re = new RoleEntity();
			re.setId(id);
			re.setName(name);
			re.setDescription(description);
			if(rs.updateRole(re)) {
				resp.sendRedirect(req.getContextPath()+"/roles");
			}else {
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			}
			break;
		}
	}
	
}
