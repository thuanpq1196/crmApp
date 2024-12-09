package crm_app07.services;

import java.sql.Date;
import java.util.List;

import crm_app07.entity.JobEntity;
import crm_app07.repository.JobRepository;
import crm_app07.repository.TaskRepository;

public class JobServices {
	private JobRepository jr = new JobRepository();
	private TaskRepository tr = new TaskRepository();
	
	public List<JobEntity> getAll(){
		return jr.getAllJob();
	}
	
	public JobEntity findJobByID(int id) {
		return jr.findByID(id);
	}
	public List<JobEntity> findJobByLeaderId(int id){
		return jr.findAllJobByLeaderId(id);
	}
	
	public boolean addJob(String name, String description, Date startDate, Date endDate, int creatorID) {
		return jr.addJob(name, description, startDate, endDate, creatorID)>0;
	}
	public boolean updateJob(JobEntity je) {
		return jr.updateJob(je)>0;
	}
	public boolean deleteJob(int id) {
		int deleted = jr.deleteJob(id);
		if(deleted > 0) {
			tr.deleteTaskByJobId(id);
		}
		return deleted > 0;
	}

}
