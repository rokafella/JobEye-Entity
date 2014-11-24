package com.jobeye.EJB.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import com.jobeye.EJB.Entity.ProfileEntity;
import com.jobeye.EJB.Entity.UserEntity;

@Stateless
public class ProfileAdd implements Serializable
{
	@PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	
	public String addProfile(String type, int userId){
		try{
			ProfileEntity newEntity = new ProfileEntity();
			
			newEntity.setProfileType(type);
			newEntity.setUserId(userId);
			
			em.persist(newEntity);
			em.flush();
		}
		catch(Exception e){
			return "false";
		}
		return "true";
	}
	
	public List<String> oldProfileValues(int userId){
		try{
			Query query = em.createNativeQuery("select * from PROFILE where USERID = '" + userId
					+ "'", ProfileEntity.class);
			
			List<ProfileEntity> res = query.getResultList();
			List<String> response = new ArrayList<String>();
			
			if(res==null){
				return null;
			}
			else{
				for(ProfileEntity n: res){
					response.add(n.getProfileType());
				}
			}
			return response;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public int getProfileIdFromDb(String type){
		try{
			Query query = em.createNativeQuery("select * from PROFILE where Profiletype = '" + type
					+ "'", ProfileEntity.class);
			
			ProfileEntity res = (ProfileEntity) query.getSingleResult();
			
			if(res==null){
				return -1;
			}
			else{
				return (int) res.getProfileId();
			}
		}
		catch(Exception e){
			return -1;
		}
	}
}
