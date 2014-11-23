package com.jobeye.EJB.Service;

import javax.ejb.*;
import com.jobeye.EJB.Entity.*;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import java.util.logging.*;

@Stateless
public class JobSession 
{
	 @PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.UserAdd");
	 private static FileHandler fh;
	 
	 public String AddJob (int companyId, String location, String position)
	 {
		 String[] param = new String[4];
		 param[0] = Integer.toString(companyId);
		 param[1] = location;
		 param[2] = position;
		 try
		 {
			 if(fh == null)
			 {
				 fh = new FileHandler("final.txt");
				 fh.setFormatter(new SimpleFormatter());
				 logger.addHandler(fh);
				 logger.setLevel(Level.ALL);
				 logger.entering("AddUser", "addUser", param);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return "Exists";
		 }
		 JobEntity job = new JobEntity();
		 job.setCompanyId(companyId);
		 job.setLocation(location);
		 job.setPosition(position);
		 
		try 
		{
			em.persist(job);
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

