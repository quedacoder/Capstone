package com.quedacoder.WorkTaskApplication.enums;

public enum TaskType {
	
	ENHANCEMENT("Enhancement"),
	NEW_DEVELOPMENT("New Development"),
	SUPPORT("Support"),
	RESEARCH("Research"),
	MISC("Misc"),
	RETROFIT("Retrofit"),
	DEFECT("Defect");
	
	private final String taskType;
	
	TaskType(String taskType) {
		this.taskType = taskType;
	}
	
	public String getTaskType() {
		return taskType;
	}

}
