package com.jobeye.EJB.Service;

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
}
