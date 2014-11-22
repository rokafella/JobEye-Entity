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
	 
	 public String UserAdd(String name){
		 
		 String param = name;
		 
		 try{
			 if(fh == null)
			 	  fh = new FileHandler("final.txt");
			 	  fh.setFormatter(new SimpleFormatter());
			      logger.addHandler(fh);
			      logger.setLevel(Level.ALL);
			      logger.entering("AddUser", "addUser", param);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 UserEntity newEntity = new UserEntity();
		 
		 newEntity.setName(name);
		 
		 logger.exiting("AddUser", "addUser", "true");
		 
		 try{
			em.persist(newEntity);
			em.flush();
		 }
		 catch(EntityExistsException e){
				return "Exists";
		 }
		 catch(ConstraintViolationException e){
				return "Exists";
		 }
		 catch(Exception e){
				return "Exists";
		 }
		 
		 return "true";
		 
	 }
	 
}
