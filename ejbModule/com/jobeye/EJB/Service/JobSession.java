package com.jobeye.EJB.Service;

import javax.ejb.*;

import com.jobeye.EJB.Entity.*;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

@Stateless
public class JobSession implements Serializable
{
	 @PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.UserAdd");
	 private static FileHandler fh;
	 
	 public int AddJob (int companyId, String location, String position)
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
			 return -1;
		 }
		 JobEntity job = new JobEntity();
		 job.setCompanyId(companyId);
		 job.setLocation(location);
		 job.setPosition(position);
		 
		try 
		{
			em.persist(job);
			em.flush();
			return (int) job.getJobId();
		} catch (EntityExistsException e) {
			return -1;
		} catch (ConstraintViolationException e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
		
	 }
	 
	 public List<String> getApps(int jobId){
		 	String q = "select name, location, position from company inner join (select  companyId, location, position from job where jobid ='"+jobId+"') as T where company.companyId = T.companyId";
		 	Query query = em.createNativeQuery(q);
		
			List<String> res = query.getResultList();
			if(res==null){
				return null;
			}
			else{		
				return res;
			}
	 }
	 /*
	 int companyID = jobSession.getCompanyId(jobid);
		String location = jobSession.getLocation(jobid);
		String position = jobSession.getPosition(jobid);
		*/
	 
	 public int getCompanyId(int jobid){
		 try{
				Query query = em.createNativeQuery("select * from Job where JOBID = '" + jobid
						+ "'", JobEntity.class);
				
				JobEntity res = (JobEntity) query.getSingleResult();
				
				if(res==null){
					return -1;
				}
				else{
					return (int) res.getCompanyId();
				}
			}
			catch(Exception e){
				return -1;
			}
	 }
	 
	 public String getLocation(int jobid){
		 try{

				Query query = em.createNativeQuery("select * from Job where JOBID = '" + jobid
						+ "'", JobEntity.class);
				
				JobEntity res = (JobEntity) query.getSingleResult();
				
				if(res==null){
					return "false";
				}
				else{
					return (String) res.getLocation();
				}
			}
			catch(Exception e){
				return "false";
			}
	 }
	 
	 public String getPosition(int jobid){
		 try{

				Query query = em.createNativeQuery("select * from Job where JOBID = '" + jobid
						+ "'", JobEntity.class);
				
				JobEntity res = (JobEntity) query.getSingleResult();
				
				if(res==null){
					return "false";
				}
				else{
					return (String) res.getPosition();
				}
			}
			catch(Exception e){
				return "false";
			}
	 }
}

