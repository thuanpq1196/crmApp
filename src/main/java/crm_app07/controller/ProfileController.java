package crm_app07.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.UserEntity;
import crm_app07.entity.StatusEntity;
import crm_app07.entity.TaskEntity;
import crm_app07.services.StatusServices;
import crm_app07.services.TaskServices;
import crm_app07.services.UserServices;
import dto.ProfileDTO;
import dto.StatusPercent;
import utils.GetCookie;

import java.util.List;

@WebServlet(name ="profileController", urlPatterns= {"/profile","/profile-edit","/profile-update"})
public class ProfileController extends HttpServlet{
	
	private UserServices us = new UserServices();
	private TaskServices ts = new TaskServices();
	private StatusServices ss = new StatusServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie userId = GetCookie.getCookieByKey("userID", req, resp);
		int id = Integer.parseInt(userId.getValue());
		switch(req.getServletPath()) {
		case("/profile"):
			ProfileDTO profile = new ProfileDTO();
			UserEntity user = us.findByID(id);
			List<TaskEntity> tasks = ts.findTaskByUserID(id);
			StatusPercent statusPercent = ts.calculatedPercent(tasks);
			profile.setUser(user);
			profile.setTasks(tasks);
			profile.setStatus(statusPercent);
			
			req.setAttribute("profile", profile);
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			break;
		case("/profile-edit"):
			int taskId = Integer.parseInt(req.getParameter("taskId"));
			TaskEntity task = ts.findTaskByID(taskId);
			List<StatusEntity> statusList = ss.getAll();
			req.setAttribute("task", task);
			req.setAttribute("statusList", statusList);
			req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
			break;
		case("/profile-update"):
			UserEntity userEntity = us.findByID(id);
			req.setAttribute("user", userEntity);
			req.getRequestDispatcher("self-profile.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch(req.getServletPath()){
		case("/profile-update"):
			int userId = Integer.parseInt(req.getParameter("id"));
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			int roleId = Integer.parseInt(req.getParameter("role"));
			String address = req.getParameter("address");
			String phoneNumber = req.getParameter("phoneNumber");
			int isActive = Integer.parseInt(req.getParameter("isActive"));
			UserEntity ue = new UserEntity();
			ue.setId(userId);
			ue.setFullName(fullname);
			ue.setEmail(email);
			ue.setPassword(password);
			ue.setAddress(address);
			ue.setPhoneNumber(phoneNumber);
			ue.setRoleId(roleId);
			ue.setIsActive(isActive);
			us.updateUser(ue);
			resp.sendRedirect(req.getContextPath()+"/profile");
			break;
		case("/profile-edit"):
			int id = Integer.parseInt(req.getParameter("id"));
			int status = Integer.parseInt(req.getParameter("status"));
			if(ts.updateStatusById(id, status)) {
				resp.sendRedirect(req.getContextPath()+"/profile");
			}else
				req.getRequestDispatcher("404.html").forward(req, resp);
			break;
		}
	}
}
