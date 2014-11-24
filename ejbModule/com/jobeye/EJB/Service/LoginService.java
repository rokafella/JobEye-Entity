package com.jobeye.EJB.Service;

import javax.ejb.Stateless;
import javax.persistence.*;

import com.jobeye.EJB.Entity.UserEntity;

@Stateless
public class LoginService {
	@PersistenceContext(unitName="JOBEYE")
	EntityManager em;
	
	public String validate(String email, String password){
		try{
			Query query = em.createNativeQuery("select * from USER where email like '" + email
					+ "'" + " and PASSWORD like '" + password + "'", UserEntity.class);
			
			UserEntity user = (UserEntity) query.getSingleResult();
			
			if(user!=null){
				return user.getName();
			}
		}
		catch(Exception e){
			return "false";
		}
		return "false";
	}
	
	public int getTheId(String email){
		try{
			Query query = em.createNativeQuery("select * from USER where email like '" + email
					+ "'", UserEntity.class);
			
			UserEntity user = (UserEntity) query.getSingleResult();
			
			if(user!=null){
				return (int) user.getUserId();
			}
		}
		catch(Exception e){
			return 0;
		}
		return 0;
	}
}
