package crm_app07.services;

import java.util.List;

import crm_app07.entity.RoleEntity;
import crm_app07.repository.RoleRepository;

public class RoleServices {
	private RoleRepository rr = new RoleRepository();
	
	public List<RoleEntity> findAll(){
		return rr.getAllRole();
	}
	
	public RoleEntity findByID(int id) {
		return rr.findByID(id);
	}
	
	public boolean addRole(String name, String description) {
		return rr.addRole(name, description) > 0;
	}
	
	public boolean updateRole(RoleEntity re) {
		return rr.updateRole(re) > 0;
	}
	
	public boolean deleteRole(int id) {
		return rr.deleteRole(id) > 0;
	}

}
