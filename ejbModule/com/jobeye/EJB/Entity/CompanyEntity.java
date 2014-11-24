package com.jobeye.EJB.Entity;

import java.io.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "COMPANY")

public class CompanyEntity implements Serializable
{
	
	public CompanyEntity()
	{
		// TODO Auto-generated constructor stub
	}
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadQuarters() {
		return headQuarters;
	}
	public void setHeadQuarters(String headQuarters) {
		this.headQuarters = headQuarters;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COMPANYID")
	private int companyId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="HEADQUARTERS")
	private String headQuarters;
	
	@Column(name="USERID")
	private int userId;
	
}
