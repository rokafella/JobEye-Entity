package com.jobeye.EJB.Entity;

import java.io.*;

import javax.persistence.*;

@Entity
@Table(name = "JOB")
public class JobEntity implements Serializable 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="JOBID")
	private long jobId;
	
	@Column(name="COMPANYID")
	private long companyId; 
	
	@Column(name="POSITION")
	private String position;
	
	@Column(name="LOCATION")
	private String location;

	public long getJobId() 
	{
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	} 
}
