package com.jobeye.EJB.Service;

import java.io.Serializable;
import java.util.logging.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import com.jobeye.EJB.Entity.StageEntity;

@SuppressWarnings("serial")
@Stateless
public class StageSession implements Serializable
{

	public StageSession() {
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext(unitName="JOBEYE")
	 EntityManager em;
	 
	 private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.StageSession");
	 private static FileHandler fh;

	 public int AddStage(String description, String stageType, int applicationId)
	 {
		 String[] param = new String[3];
		 param[0] = description;
		 param[1] = stageType;
		 param[2] = Integer.toString(applicationId);
		 try
		 {
			 if(fh == null)
			 {
				 fh = new FileHandler("final.txt");
				 fh.setFormatter(new SimpleFormatter());
				 logger.addHandler(fh);
				 logger.setLevel(Level.ALL);
				 logger.entering("StageSession", "AddStage", param);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return -1;
		 }
		 StageEntity stage = new StageEntity();
		 stage.setDescription(description);
		 stage.setApplicationId(applicationId);
		 stage.setStageType(stageType);
		try 
		{
			em.persist(stage);
			em.flush();
			return stage.getStageId();
		} catch (EntityExistsException e) {
			return -1;
		} catch (ConstraintViolationException e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
		
	 }

}
