package crm_app07.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.RoleEntity;
import crm_app07.entity.UserEntity;
import crm_app07.services.RoleServices;
import crm_app07.services.UserServices;
@WebServlet(name = "userController", urlPatterns = {"/users","/user-add"})
public class UserController extends HttpServlet{
	 
	private UserServices us = new UserServices();
	private RoleServices rs = new RoleServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/users")) {
			loadUsers(req, resp);
		}else if(path.equals("/user-add")) {
			loadRoles(req, resp);
		}
	}
	
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/users")) {
		}else if(path.equals("/user-add")) {
			addUser(req, resp);
		}
	}



	private void loadUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if(id!= null) {
			us.deleteByID(Integer.parseInt(id));
		}
		List<UserEntity> users = us.getAll();
		req.setAttribute("users", users);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
	
	private void loadRoles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> roles = rs.findAll();
		req.setAttribute("roles", roles);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String fullName = req.getParameter("fullname");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phoneNumber");
		int roleID = Integer.parseInt(req.getParameter("role"));
		us.addUser(email, password, fullName,address,phoneNumber, roleID);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

	
	
}
