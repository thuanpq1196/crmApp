package crm_app07.services;

import java.sql.Date;
import java.util.List;

import crm_app07.entity.TaskEntity;
import crm_app07.repository.TaskRepository;
import dto.StatusPercent;

public class TaskServices {
	private TaskRepository tr = new TaskRepository();
	
	public List<TaskEntity> getAllTask(){
		return tr.getAllTask();
	}
	
	public TaskEntity findTaskByID(int id) {
		return tr.findByID(id);
	}
	
	public boolean addTask(String name, String description, Date startDate, Date endDate, int statusID, int userID, int jobID) {
		return tr.addTask(name, description, startDate, endDate, statusID, userID, jobID) >0;
	}
	
	public boolean updateTask(TaskEntity te) {
		return tr.updateTask(te) >0;
	}
	
	public boolean deleteTask(int taskID) {
		return tr.deleteTask(taskID) > 0;
	}
	public List<TaskEntity> findTaskByJobID(int jobID){
		return tr.findTaskByJobID(jobID);
	}
	
	public List<TaskEntity> findTaskByUserID(int userID){
		return tr.findTaskByUserID(userID);
	}
	
	public StatusPercent calculatedPercent(List<TaskEntity> tasks) {
		StatusPercent sp = new StatusPercent();
		if(tasks.size() > 0) {
			int countNotYet = 0;
			int countOnGoing = 0;
			int countHadDone = 0;
			for(TaskEntity te : tasks) {
				if(te.getStatus() == 1) {
					countNotYet ++;
				}else if(te.getStatus() == 2) {
					countOnGoing ++;
				}else if(te.getStatus() == 3) {
					countHadDone ++;
				}
			}
			sp.setNotYet(countNotYet *100 / tasks.size());
			sp.setOnGoing(countOnGoing *100 / tasks.size());
			sp.setHadDone(countHadDone *100 / tasks.size());
		}else {
			sp.setNotYet(0);
			sp.setOnGoing(0);
			sp.setHadDone(0);
		}
		return sp;
	}
	
	public boolean deleteTaskByJobID(int id) {
		return tr.deleteTaskByJobId(id) >0;
	}
	
	public boolean updateStatusById(int id, int statusId) {
		return tr.updateTaskStatusById(id, statusId) >0;
	}

}
