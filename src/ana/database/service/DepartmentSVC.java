package ana.database.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ana.database.dao.DepartmentDAO;
import ana.database.hibernate.HibernateUtil;
import ana.util.converter.BeanToHashMap;
@SuppressWarnings({"rawtypes","unchecked"})
public class DepartmentSVC {
	
	public HashMap getDepartmentByID(long department_id){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    HashMap department = new HashMap();
	    DepartmentDAO d = (DepartmentDAO) session.get(DepartmentDAO.class,department_id);
	    if(d == null) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return null;
	    }
	    department = BeanToHashMap.directPack(d);
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return department;
	}
	
	public List<HashMap> getAllDepartment(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria c = session.createCriteria(DepartmentDAO.class);
	    List<DepartmentDAO> rs = c.list();
	    List<HashMap> result = new ArrayList<HashMap>();
	    for (Iterator<DepartmentDAO> i = rs.iterator();i.hasNext();){
	    	result.add(BeanToHashMap.directPack(i.next()));
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return result;
	}

}
