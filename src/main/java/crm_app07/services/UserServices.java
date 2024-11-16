package crm_app07.services;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.UserEntity;
import crm_app07.repository.UserRepository;
import utils.MD5;

public class UserServices {
	private UserRepository ur = new UserRepository();
	public boolean getUserByEmailAndPassword(String email, String password, String rememberMe, HttpServletRequest req, HttpServletResponse resp){
		String passwordEncoded = MD5.getMd5(password);
		List<UserEntity> users = ur.findByEmailAndPassword(email, passwordEncoded);
		if(users.size() > 0) {
			if(rememberMe!= null) {
				Cookie emailCK = new Cookie("email", email);
				Cookie passwordCK = new Cookie("password",password);
				Cookie loginedCK = new Cookie("logined","true");

				resp.addCookie(emailCK);
				resp.addCookie(passwordCK);
				resp.addCookie(loginedCK);
				
				}
			return true;
		}else
			return false;
	}
	public Cookie getCookieByKey(String key, HttpServletRequest req, HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
		if(cookies == null) {
			return null;
		}
		for(Cookie item : cookies) {
			if(item.getName().equals(key)) {
				return item;
			}
		}
		return null;
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
}