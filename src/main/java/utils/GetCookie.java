package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCookie {
	public static Cookie getCookieByKey(String key, HttpServletRequest req, HttpServletResponse resp) {
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
}
