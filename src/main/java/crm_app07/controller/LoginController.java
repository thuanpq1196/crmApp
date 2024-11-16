package crm_app07.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.services.UserServices;

@WebServlet(name ="loginController", urlPatterns= {"/login"})
public class LoginController extends HttpServlet{
	
	/**
	 * 
	 * Tính năng đăng nhập: Lấy email và mật khẩu người dùng nhập kiểm tra nó có tồn tại tròn CSDL hay không
	 * 
	 * */
	private UserServices us = new UserServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie emailCk = us.getCookieByKey("email", req, resp);
		Cookie passwordCk = us.getCookieByKey("password", req, resp);
		if(emailCk!=null) {
			req.setAttribute("email", emailCk.getValue());
		}
		if(passwordCk!= null) {
			req.setAttribute("password", passwordCk.getValue());
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String rememberMe = req.getParameter("remember");
		
		if(us.getUserByEmailAndPassword(email, password, rememberMe,req, resp)) {
			req.getRequestDispatcher("index.html").forward(req, resp);
		}else {
			System.out.println("Sai email hoặc mật khẩu");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
	
	
	

}
