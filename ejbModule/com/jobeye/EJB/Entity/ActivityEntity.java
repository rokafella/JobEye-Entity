package com.jobeye.EJB.Entity;

import java.io.Serializable;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "ACTIVITY")
public class ActivityEntity implements Serializable 
{
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ACTIVITYID")
	private int activityId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DATE")
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="COMPANY")
	private String company;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
}