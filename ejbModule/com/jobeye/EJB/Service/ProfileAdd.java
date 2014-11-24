package com.jobeye.EJB.Service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import com.jobeye.EJB.Entity.ProfileEntity;
import com.jobeye.EJB.Entity.UserEntity;

@Stateless
public class ProfileAdd {
	@PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	
	public String addProfile(String type, String email){
		try{
			Query query = em.createNativeQuery("select * from USER where email like '" + email
					+ "'", UserEntity.class);
			
			UserEntity user = (UserEntity) query.getSingleResult();
			
			if(user==null){
				return "false";
			}
			
			ProfileEntity newEntity = new ProfileEntity();
			
			newEntity.setProfileType(type);
			newEntity.setUserId(user.getUserId());
			
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
			Query query = em.createNativeQuery("select * from PROFILE where USERID like '" + userId
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
}
