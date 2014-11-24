package com.jobeye.EJB.Entity;

import java.io.Serializable;
import java.util.Date;

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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ACTIVITYID")
	private int activityId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="COMPANYID")
	private int companyId;
	
}
