package crm_app07.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.JobEntity;
import crm_app07.entity.TaskEntity;
import crm_app07.services.JobServices;
import crm_app07.services.TaskServices;
import crm_app07.services.UserServices;
import dto.StatusPercent;
import utils.GetCookie;

@WebServlet(name ="loginController", urlPatterns= {"/login","/logout"})
public class LoginController extends HttpServlet{
	
	/**
	 * 
	 * Tính năng đăng nhập: Lấy email và mật khẩu người dùng nhập kiểm tra nó có tồn tại tròn CSDL hay không
	 * 
	 * */
	private UserServices us = new UserServices();
	private TaskServices ts = new TaskServices();
	private JobServices js = new JobServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getServletPath().equals("/logout")) {
			Cookie logined = GetCookie.getCookieByKey("logined", req, resp);
			logined.setValue("false");
			Cookie role = GetCookie.getCookieByKey("role", req, resp);
			role.setValue("");
			Cookie userID = GetCookie.getCookieByKey("userID", req, resp);
			userID.setValue("");
			resp.addCookie(logined);
			resp.addCookie(userID);
			resp.addCookie(role);
		}
		Cookie emailCk = GetCookie.getCookieByKey("email", req, resp);
		Cookie passwordCk = GetCookie.getCookieByKey("password", req, resp);
		if(emailCk!=null && !emailCk.getValue().equals("")) {
			req.setAttribute("email", emailCk.getValue());
		}
		if(passwordCk!= null && !passwordCk.getValue().equals("")) {
			req.setAttribute("password", passwordCk.getValue());
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case ("/login"):
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String rememberMe = req.getParameter("remember");
			
			if(us.getUserByEmailAndPassword(email, password, rememberMe,req, resp)) {
				resp.sendRedirect(req.getContextPath()+"/home");
				
			}else {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
			break;
		}
		
	}
	
	
	

}
