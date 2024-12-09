package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MANAGER = "ROLE_MANAGER";
	public static final String ROLE_USER = "ROLE_USER";
	private static final Map<String, List<String>> securityMap = new HashMap<String, List<String>>();
	
	static {
		init();
	}

	private static void init() {
		
		List<String> urlPatternsAdmin = new ArrayList<String>();
		urlPatternsAdmin.add("/jobs");
		urlPatternsAdmin.add("/job-add");
		urlPatternsAdmin.add("/job-update");
		urlPatternsAdmin.add("/job-delete");
		urlPatternsAdmin.add("/job-detail");
		urlPatternsAdmin.add("/roles");
		urlPatternsAdmin.add("/role-update");
		urlPatternsAdmin.add("/role-add");
		urlPatternsAdmin.add("/role-delete");
		urlPatternsAdmin.add("/tasks");
		urlPatternsAdmin.add("/task-add");
		urlPatternsAdmin.add("/task-update");
		urlPatternsAdmin.add("/task-delete");
		urlPatternsAdmin.add("/task-detail");
		urlPatternsAdmin.add("/users");
		urlPatternsAdmin.add("/user-add");
		urlPatternsAdmin.add("/user-update");
		urlPatternsAdmin.add("/user-delete");
		urlPatternsAdmin.add("/user-detail");
		urlPatternsAdmin.add("/profile");
		urlPatternsAdmin.add("/profile-update");
		urlPatternsAdmin.add("/profile-edit");
		urlPatternsAdmin.add("/home");
		urlPatternsAdmin.add("/error");
		securityMap.put(ROLE_ADMIN, urlPatternsAdmin);

		
		List<String> urlPatternsManager = new ArrayList<String>();

		urlPatternsManager.add("/users");
		urlPatternsManager.add("/jobs");
		urlPatternsManager.add("/job-add");
		urlPatternsManager.add("/job-update");
		urlPatternsManager.add("/job-delete");
		urlPatternsManager.add("/job-detail");
		urlPatternsManager.add("/tasks");
		urlPatternsManager.add("/task-add");
		urlPatternsManager.add("/task-update");
		urlPatternsManager.add("/task-delete");
		urlPatternsManager.add("/task-detail");
		urlPatternsManager.add("/profile");
		urlPatternsManager.add("/profile-update");
		urlPatternsManager.add("/profile-edit");
		urlPatternsManager.add("/home");
		urlPatternsManager.add("/error");

		securityMap.put(ROLE_MANAGER, urlPatternsManager);
		
		List<String> urlPatternsUser = new ArrayList<String>();
		urlPatternsUser.add("/profile");
		urlPatternsUser.add("/profile-update");
		urlPatternsUser.add("/profile-edit");
		urlPatternsUser.add("/home");
		urlPatternsUser.add("/error");

		securityMap.put(ROLE_USER, urlPatternsUser);
	}

	public static Set<String> getAllAppRoles() {
		return securityMap.keySet();
	}

	public static List<String> getUrlPatternsForRole(String role) {
		return securityMap.get(role);
	}
}
