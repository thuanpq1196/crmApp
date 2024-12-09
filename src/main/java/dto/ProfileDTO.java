package dto;

import java.util.List;

import crm_app07.entity.TaskEntity;
import crm_app07.entity.UserEntity;

public class ProfileDTO {
	private UserEntity user;
	private StatusPercent status;
	private List<TaskEntity> tasks;
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public StatusPercent getStatus() {
		return status;
	}
	public void setStatus(StatusPercent status) {
		this.status = status;
	}
	public List<TaskEntity> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}
	
	
}
