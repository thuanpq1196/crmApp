package dto;

import java.util.List;

import crm_app07.entity.TaskEntity;

public class StatusPercent {
	private double notYet;
	private double onGoing;
	private double hadDone;

	public double getNotYet() {
		return notYet;
	}

	public void setNotYet(double notYet) {
		this.notYet = notYet;
	}

	public double getOnGoing() {
		return onGoing;
	}

	public void setOnGoing(double onGoing) {
		this.onGoing = onGoing;
	}

	public double getHadDone() {
		return hadDone;
	}

	public void setHadDone(double hadDone) {
		this.hadDone = hadDone;
	}
	
	

}
