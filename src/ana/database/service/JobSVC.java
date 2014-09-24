package ana.database.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ana.database.dao.JobDAO;
import ana.database.hibernate.HibernateUtil;
import ana.util.converter.BeanToHashMap;
@SuppressWarnings({"rawtypes","unchecked"})
public class JobSVC {
	
	public HashMap getJobByID(long job_id){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    HashMap job = new HashMap();
	    JobDAO j = (JobDAO) session.get(JobDAO.class,job_id);
	    if(j == null) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return null;
	    }
	    job = BeanToHashMap.directPack(j);
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return job;
	}
	
	public List<HashMap> getAllJob(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria c = session.createCriteria(JobDAO.class);
	    List<JobDAO> rs = c.list();
	    List<HashMap> result = new ArrayList<HashMap>();
	    for (Iterator<JobDAO> i = rs.iterator();i.hasNext();){
	    	result.add(BeanToHashMap.directPack(i.next()));
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return result;
	}

}
