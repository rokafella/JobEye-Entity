package com.jobeye.EJB.Entity;

import java.io.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "APPLICATION")
public class ApplicationEntity implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="APPLICATIONID")
	private long applicationId;
	
	@Column(name="JOBID")
	private long jobId; 
	
	@Column(name="PROFILEID")
	private long profileId;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DESCRIPTION")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
