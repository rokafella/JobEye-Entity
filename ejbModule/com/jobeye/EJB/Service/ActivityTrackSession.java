package com.jobeye.EJB.Service;

import java.io.*;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jobeye.EJB.Entity.ActivityEntity;

@SuppressWarnings("serial")
@Stateless
public class ActivityTrackSession implements Serializable{

	@PersistenceContext(unitName="JOBEYE")
	EntityManager em;

	public List<ActivityEntity> findActivity(String company){

		String query1 = "select * from Activity where company = '" + company+ "';";

		Query query = em.createNativeQuery(query1 , ActivityEntity.class);

		@SuppressWarnings("unchecked")
		List<ActivityEntity> res = query.getResultList();

		return res;
	}
	
	public List<String> allCompanies(){

		String query1 = "select company from Activity;";
		
		Query query = em.createNativeQuery(query1);

		@SuppressWarnings("unchecked")
		List<String> res = query.getResultList();

		return res;
	}
	
}