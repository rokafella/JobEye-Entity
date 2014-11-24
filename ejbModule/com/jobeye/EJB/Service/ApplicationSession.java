package com.jobeye.EJB.Service;

import javax.ejb.*;

import com.jobeye.EJB.Entity.*;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import java.io.*;
import java.util.logging.*;

@Stateless
public class ApplicationSession implements Serializable
{
	 @PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.ApplicationSession");
	 private static FileHandler fh;
	 
	 public String AddApplication (int jobId, int profileId, String status)
	 {
		 String[] param = new String[3];
		 param[0] = Integer.toString(jobId);
		 param[1] = Integer.toString(profileId);;
		 param[2] = status;
		 try
		 {
			 if(fh == null)
			 {
				 fh = new FileHandler("final.txt");
				 fh.setFormatter(new SimpleFormatter());
				 logger.addHandler(fh);
				 logger.setLevel(Level.ALL);
				 logger.entering("ApplicationSession", "AddApplication", param);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return "Exists";
		 }
		 ApplicationEntity application = new ApplicationEntity();
		 application.setJobId(jobId);;
		 application.setProfileId(profileId);;
		 application.setStatus(status);
		 
		try 
		{
			em.persist(application);
			em.flush();
		} catch (EntityExistsException e) {
			return "Exists";
		} catch (ConstraintViolationException e) {
			return "Exists";
		} catch (Exception e) {
			return "Exists";
		}		
		return "true";
	 }
}

