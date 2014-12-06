package com.jobeye.EJB.Service;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import com.jobeye.EJB.Entity.TaskEntity;

import java.io.*;
import java.util.Date;
import java.util.logging.*;

@SuppressWarnings("serial")
@Stateless
public class TaskSession implements Serializable
{

	public TaskSession() {
		// TODO Auto-generated constructor stub
	}

	 @PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.TaskSession");
	 private static FileHandler fh;

	 public int AddTask(Date date, String description, int applicationId)
	 {
		 String[] param = new String[3];
		 param[0] = date.toString();
		 param[1] = Integer.toString(applicationId);
		 param[2] = description;
		 try
		 {
			 if(fh == null)
			 {
				 fh = new FileHandler("finalTask.txt");
				 fh.setFormatter(new SimpleFormatter());
				 logger.addHandler(fh);
				 logger.setLevel(Level.ALL);
				 logger.entering("TaskSession", "AddTask", param);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return -1;
		 }
		 TaskEntity task = new TaskEntity();
		 task.setDate(date);
		 task.setDescription(description);
		 task.setApplicationId(applicationId);
		 
		try 
		{
			em.persist(task);
			em.flush();
			return task.getTaskId();
		} catch (EntityExistsException e) {
			return -1;
		} catch (ConstraintViolationException e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
		
	 }
}
