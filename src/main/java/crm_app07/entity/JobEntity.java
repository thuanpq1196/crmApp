package crm_app07.entity;

import java.sql.Date;
import java.util.List;

public class JobEntity {
	private int id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int creatorId;
	private List<TaskEntity> tasks;
	private double percentCompleted;
	private double percentOnGoing;
	private double percentNotYetImplemented;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TaskEntity> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}
	public double getPercentCompleted() {
		return percentCompleted;
	}
	public void setPercentCompleted(double percentCompleted) {
		this.percentCompleted = percentCompleted;
	}
	public double getPercentOnGoing() {
		return percentOnGoing;
	}
	public void setPercentOnGoing(double percentOnGoing) {
		this.percentOnGoing = percentOnGoing;
	}
	public double getPercentNotYetImplemented() {
		return percentNotYetImplemented;
	}
	public void setPercentNotYetImplemented(double percentNotYetImplemented) {
		this.percentNotYetImplemented = percentNotYetImplemented;
	}
	
	
	

}
