package com.jobeye.EJB.Service;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import com.jobeye.EJB.Entity.UserEntity;

@Stateless
public class UserAdd {
	 @PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.UserAdd");
	 private static FileHandler fh;
	 
	 public int UserAdd(String name, String email, String phone, String password){
		 
		 String[] params = new String[4];
		 params[0] = name;
		 params[1] = email;
		 params[2] = phone;
		 params[3] = password;
		 
		 try{
			 if(fh == null)
			 	  fh = new FileHandler("final.txt");
			 	  fh.setFormatter(new SimpleFormatter());
			      logger.addHandler(fh);
			      logger.setLevel(Level.ALL);
			      logger.entering("AddUser", "addUser", params);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 UserEntity newEntity = new UserEntity();
		 
		 newEntity.setName(name);
		 newEntity.setPhone(phone);
		 newEntity.setEmail(email);
		 newEntity.setPassword(password);
		 
		 logger.exiting("AddUser", "addUser", "true");
		 
		 try{
			em.persist(newEntity);
			em.flush();
			return (int) newEntity.getUserId();
		 }
		 catch(EntityExistsException e){
				return -1;
		 }
		 catch(ConstraintViolationException e){
				return -1;
		 }
		 catch(Exception e){
				return -1;
		 }
	 }
	 
}
