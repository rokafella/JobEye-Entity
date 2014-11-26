package com.jobeye.EJB.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import com.jobeye.EJB.Entity.ActivityEntity;


@SuppressWarnings("serial")
@Stateless
public class ActivitySession implements Serializable
{

	
	@PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.ActivitySession");
	 private static FileHandler fh;
	 
	 public int AddActivity (String title, Date date, String description, int companyId)
	 {
		 String[] param = new String[4];
		 param[0] = Integer.toString(companyId);
		 param[1] = date.toString();
		 param[2] = title;
		 param[3] = description;
		 try
		 {
			 if(fh == null)
			 {
				 fh = new FileHandler("final.txt");
				 fh.setFormatter(new SimpleFormatter());
				 logger.addHandler(fh);
				 logger.setLevel(Level.ALL);
				 logger.entering("ActivitySession", "AddActivity", param);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return -1;
		 }
		 ActivityEntity activity = new ActivityEntity();
		 activity.setCompanyId(companyId);
		 activity.setTitle(title);
		 activity.setDate(date);
		 activity.setDescription(description);
		 
		try 
		{
			em.persist(activity);
			em.flush();
			return (int) activity.getActivityId();
		} catch (EntityExistsException e) {
			return -1;
		} catch (ConstraintViolationException e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
	 }
	 

	 public List<String> findActivity(String cid){
	    	List<String> activity = new ArrayList<String>();
	    	String query1 = "select a.* from Activity a inner join Company c on a.companyid = c.companyid where c.name like '" + cid+ "';";
	    	if(fh == null){
				try {
					fh = new FileHandler("final.txt");
				} catch (SecurityException | IOException e) {
					e.printStackTrace();
				}
				fh.setFormatter(new SimpleFormatter());
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);
				logger.entering("ActivitySession", "AddActivity", query1);
			}
			else
			{
				fh.setFormatter(new SimpleFormatter());
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);
				logger.entering("ActivitySession", "AddActivity", query1);
			}
	    	
	    	Query query = em.createNativeQuery
					(query1 , ActivityEntity.class);
	    	
	    	ActivityEntity centity = new ActivityEntity();
			try {
				centity = (ActivityEntity) query.getSingleResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				activity.add("");
				activity.add("");
				activity.add("");
				return activity;
			}

			String date = centity.getDate().toString();
			String description = centity.getDescription();
			String title = centity.getTitle();
			activity.add(date);
			activity.add(title);
			activity.add(description);
	    	return activity;
	    }

}
