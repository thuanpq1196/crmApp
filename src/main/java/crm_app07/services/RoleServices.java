package crm_app07.services;

import java.util.List;

import crm_app07.entity.RoleEntity;
import crm_app07.repository.RoleRepository;

public class RoleServices {
	private RoleRepository rr = new RoleRepository();
	
	public List<RoleEntity> findAll(){
		return rr.getAllRole();
	}

}
