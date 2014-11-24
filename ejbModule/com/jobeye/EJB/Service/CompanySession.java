package com.jobeye.EJB.Service;

import java.util.logging.*;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import com.jobeye.EJB.Entity.CompanyEntity;;

public class CompanySession {

	public CompanySession() {
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.CompanySession");
	 private static FileHandler fh;
	 
	 public String AddCompany(String name, String headQuarters, int userId)
	 {
		 String[] param = new String[3];
		 param[0] = name;
		 param[1] = headQuarters;
		 param[2] = Integer.toString(userId);
		 try
		 {
			 if(fh == null)
			 {
				 fh = new FileHandler("final.txt");
				 fh.setFormatter(new SimpleFormatter());
				 logger.addHandler(fh);
				 logger.setLevel(Level.ALL);
				 logger.entering("CompanySession", "AddCompany", param);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return "Exists";
		 }
		 CompanyEntity company = new CompanyEntity();
		 company.setName(name);;
		 company.setHeadQuarters(headQuarters);
		 company.setUserId(userId);
		 
		try 
		{
			em.persist(company);
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
