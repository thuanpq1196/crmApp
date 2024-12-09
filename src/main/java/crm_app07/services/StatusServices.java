package crm_app07.services;

import java.util.List;

import crm_app07.entity.StatusEntity;
import crm_app07.repository.StatusRepository;

public class StatusServices {
	private StatusRepository sr = new StatusRepository();
	
	public List<StatusEntity> getAll(){
		return sr.getAll();
	}
}
