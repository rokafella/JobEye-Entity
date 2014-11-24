package com.jobeye.EJB.Entity;
import java.io.*;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "TASK")

public class TaskEntity implements Serializable
{

	public TaskEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TASKID")
	private int taskId;
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="APPLICATIONID")
	private int applicationId;

}
