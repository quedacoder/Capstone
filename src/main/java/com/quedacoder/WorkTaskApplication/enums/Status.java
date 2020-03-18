package com.quedacoder.WorkTaskApplication.enums;

public enum Status {
	
	COMPLETE("Complete"),
	CANCELED("Canceled"),
	CLOSED("Closed"),
	NOT_STARTED("Not Started"),
	STARTED("Started"),
	ON_HOLD("On Hold");
	
	private final String status;
	
	Status(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

}
