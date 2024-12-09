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
import crm_app07.entity.TaskEntity;
import crm_app07.entity.UserEntity;
import crm_app07.services.RoleServices;
import crm_app07.services.TaskServices;
import crm_app07.services.UserServices;
import dto.StatusPercent;
@WebServlet(name = "userController", urlPatterns = {"/users","/user-add","/user-update","/user-delete","/user-detail"})
public class UserController extends HttpServlet{
	 
	private UserServices us = new UserServices();
	private RoleServices rs = new RoleServices();
	private TaskServices ts = new TaskServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = 0;
		switch(req.getServletPath()) {
		case("/users") :
			loadUsers(req, resp);
			break;
		case("/user-add"): 
			loadRoles(req, resp);
			break;
		case("/user-update"):
			id = Integer.parseInt(req.getParameter("id"));
			UserEntity ue = us.findByID(id);
			req.setAttribute("user", ue);
			List<RoleEntity> roles = rs.findAll();
			req.setAttribute("roles", roles);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		case("/user-delete"):
			id = Integer.parseInt(req.getParameter("id"));
			us.deleteByID(id);
			resp.sendRedirect(req.getContextPath()+"/users");
			break;
		case("/user-detail"):
			id = Integer.parseInt(req.getParameter("id"));
			UserEntity user = new UserEntity();
			user = us.findByID(id);
			List<TaskEntity> tasks = new ArrayList<>();
			tasks = ts.findTaskByUserID(id);
			System.out.println(tasks.size());
			if(tasks.size() > 0) {
				List<TaskEntity> notYet = new ArrayList<>();
				List<TaskEntity> onGoing = new ArrayList<>();
				List<TaskEntity> hadDone = new ArrayList<>();
				for(TaskEntity te : tasks) {
					if(te.getStatus() == 1) {
						notYet.add(te);
					}else if(te.getStatus() == 2) {
						onGoing.add(te);
					}else if(te.getStatus() == 3) {
						hadDone.add(te);
					}
				}
				req.setAttribute("notYet", notYet);
				req.setAttribute("onGoing", onGoing);
				req.setAttribute("hadDone", hadDone);
				System.out.println(notYet.size());
				System.out.println(onGoing.size());
				System.out.println(hadDone.size());
			}
			StatusPercent sp = new StatusPercent();
			sp = ts.calculatedPercent(tasks);
			req.setAttribute("status", sp);
			req.setAttribute("user", user);
			
			req.getRequestDispatcher("user-details.jsp").forward(req, resp);
			
		}
		
	}
	
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case("/user-add"):
			addUser(req, resp);
			break;
		case("/user-update"):
			updateUser(req, resp);
			break;
		}
		
	}



	private void loadUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		req.setCharacterEncoding("utf-8");
		String email = req.getParameter("email");
		String fullName = req.getParameter("fullname");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phoneNumber");
		int roleID = Integer.parseInt(req.getParameter("role"));
		if(us.addUser(email, password, fullName,address,phoneNumber, roleID)) {
			resp.sendRedirect(req.getContextPath()+"/users");
		}else {
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		}
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("email");
		String fullName = req.getParameter("fullname");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phoneNumber");
		int roleID = Integer.parseInt(req.getParameter("role"));
		int isActive = Integer.parseInt(req.getParameter("isActive")); 
		System.out.println(roleID);
		UserEntity ue = new UserEntity();
		ue.setId(id);
		ue.setFullName(fullName);
		ue.setEmail(email);
		ue.setAddress(address);
		ue.setPhoneNumber(phoneNumber);
		ue.setPassword(password);
		ue.setRoleId(roleID);
		ue.setIsActive(isActive);
		if(us.updateUser(ue)) {
			resp.sendRedirect(req.getContextPath()+"/users");
		}else {
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		}
	}
	
}
