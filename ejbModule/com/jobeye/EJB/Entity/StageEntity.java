package com.jobeye.EJB.Entity;

import java.io.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "STAGE")
public class StageEntity implements Serializable
{

	public StageEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public int getStageId() {
		return stageId;
	}

	public void setStageId(int stageId) {
		this.stageId = stageId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStageType() {
		return stageType;
	}

	public void setStageType(String stageType) {
		this.stageType = stageType;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="STAGEID")
	private int stageId;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="STAGETYPE")
	private String stageType;
	
	@Column(name="APPLICATIONID")
	private int applicationId;

}
