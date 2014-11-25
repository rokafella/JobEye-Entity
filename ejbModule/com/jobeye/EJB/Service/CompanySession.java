package com.jobeye.EJB.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import com.jobeye.EJB.Entity.CompanyEntity;
import com.jobeye.EJB.Entity.ProfileEntity;

@Stateless
public class CompanySession implements Serializable{
	@PersistenceContext(unitName="JOBEYE")
	EntityManager em;

	private static Logger logger = Logger.getLogger("com.jobeye.EJB.Service.CompanySession");
	private static FileHandler fh;

	public int AddCompany(String name, int userId){
		String[] param = new String[2];
		param[0] = name;
		param[1] = Integer.toString(userId);
		try{
			if(fh == null){
				fh = new FileHandler("final.txt");
				fh.setFormatter(new SimpleFormatter());
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);
				logger.entering("CompanySession", "AddCompany", param);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		CompanyEntity company = new CompanyEntity();
		company.setName(name);;
		company.setUserId(userId);

		try {
			em.persist(company);
			em.flush();
		} catch (EntityExistsException e) {
			return -1;
		} catch (ConstraintViolationException e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}

		try{
			Query query = em.createNativeQuery("select * from COMPANY where name like '" + name
					+ "'" + " and userId = " + userId + ";", CompanyEntity.class);

			CompanyEntity companyFromDB = (CompanyEntity) query.getSingleResult();
			if(companyFromDB != null){
				return companyFromDB.getCompanyId();
			}
		}
		catch(Exception e){
			return -1;
		}
		return -1;
	}

	public List<String> allCompanies(int userId){
		try{
			Query query = em.createNativeQuery("select * from COMPANY where USERID = '" + userId
					+ "'", CompanyEntity.class);

			List<CompanyEntity> res = query.getResultList();
			List<String> response = new ArrayList<String>();

			if(res==null){
				return null;
			}
			else{
				for(CompanyEntity n: res){
					response.add(n.getName());
				}
			}
			return response;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public int getCompany(String name, int userId){
		Query query = em.createNativeQuery("select * from COMPANY where name like '" + name
				+ "'" + " and userId = " + userId + ";", CompanyEntity.class);
		CompanyEntity companyFromDB = (CompanyEntity) query.getSingleResult();
		if(companyFromDB != null){
			return companyFromDB.getCompanyId();
		}
		return -1;
	}
}
