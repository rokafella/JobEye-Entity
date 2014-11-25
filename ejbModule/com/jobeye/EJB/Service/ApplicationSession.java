package com.jobeye.EJB.Service;

import javax.ejb.*;

import com.jobeye.EJB.Entity.*;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

@SuppressWarnings("serial")
@Stateless
public class ApplicationSession implements Serializable
{
	 @PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.ApplicationSession");
	 private static FileHandler fh;
	 
	 public int AddApplication (int jobId, int profileId, String status)
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
			 return -1;
		 }
		 ApplicationEntity application = new ApplicationEntity();
		 application.setJobId(jobId);;
		 application.setProfileId(profileId);;
		 application.setStatus(status);
		 
		try 
		{
			em.persist(application);
			em.flush();
			return (int) application.getApplicationId();
		} catch (EntityExistsException e) {
			return -1;
		} catch (ConstraintViolationException e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
	 }
	 
	 public List<Integer> getAllApps(int profileId, String status){
		 Query query = em.createNativeQuery("select * from APPLICATION where PROFILEID = '" + profileId
					+ "' and STATUS = '"+status+"'", ApplicationEntity.class);

			List<ApplicationEntity> res = query.getResultList();
			
			List<Integer> response = new ArrayList<Integer>();

			if(res==null){
				return null;
			}
			else{
				for(ApplicationEntity n: res){
					response.add((int) n.getJobId());
				}
			}
			return response;
	 }
}

