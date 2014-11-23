package com.jobeye.EJB.Entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="PROFILE")
public class ProfileEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PROFILEID")
	private long profileId;
	
	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	@Column(name="PROFILETYPE")
	private String profileType;
	
	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	
	@Column(name="USERID")
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
