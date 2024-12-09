package crm_app07.services;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.UserEntity;
import crm_app07.repository.UserRepository;
import utils.GetCookie;
import utils.MD5;

public class UserServices {
	private UserRepository ur = new UserRepository();
	public boolean getUserByEmailAndPassword(String email, String password, String rememberMe, HttpServletRequest req, HttpServletResponse resp){
		String passwordEncoded = password;
		List<UserEntity> users = ur.findByEmailAndPassword(email, passwordEncoded);
		if(users.size() > 0) {
			if(rememberMe!= null) {
				Cookie emailCK = new Cookie("email", email);
				Cookie passwordCK = new Cookie("password",password);
				resp.addCookie(emailCK);
				resp.addCookie(passwordCK);
			}else {
				Cookie emailCk = GetCookie.getCookieByKey("email", req, resp);
				if(emailCk != null) {
					emailCk.setValue("");
					resp.addCookie(emailCk);
				}
				Cookie passwordCk = GetCookie.getCookieByKey("password", req, resp);
				if(passwordCk != null) {
					passwordCk.setValue("");
					resp.addCookie(passwordCk);
				}
			}
					
			Cookie loginedCK = GetCookie.getCookieByKey("logined", req, resp);
			if(loginedCK == null) {
				loginedCK =	new Cookie("logined","true");
			}else
				loginedCK.setValue("true");
			Cookie userIDCK = GetCookie.getCookieByKey("userID", req, resp);
			if(userIDCK == null) {
				userIDCK = new Cookie("userID",Integer.toString(users.get(0).getId()));
			}else
				userIDCK.setValue(Integer.toString(users.get(0).getId()));
			Cookie roleCk = GetCookie.getCookieByKey("role", req, resp);
			if(roleCk == null) {
				roleCk = new Cookie("role",users.get(0).getRoleName());
			}else
				roleCk.setValue(users.get(0).getRoleName());
			resp.addCookie(loginedCK);
			resp.addCookie(userIDCK);
			resp.addCookie(roleCk);
			return true;
		}else
			return false;
	}
	
	public List<UserEntity> getAll(){
		return ur.findAll();
	}
	
	public boolean deleteByID(int id) {
		return ur.deleteById(id) > 0;
	}
	
	public boolean addUser(String email, String password, String fullName,String address, String phoneNumber, int roleID) {
		return ur.addUser(email, password, fullName, address, phoneNumber, roleID) > 0;
	}
	
	public boolean updateUser(UserEntity ue) {
		return ur.updateUser(ue) > 0;
	}
	public UserEntity findByID(int id) {
		return ur.findByID(id);
	}
	
	public List<UserEntity> findUser(){
		return ur.findByRole();
	}
}
