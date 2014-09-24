package ana.database.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ana.database.dao.GroupDAO;
import ana.database.hibernate.HibernateUtil;
import ana.util.converter.BeanToHashMap;
@SuppressWarnings({"rawtypes","unchecked"})
public class GroupSVC {
	
	public HashMap getGroupByID(long group_id){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    HashMap group = new HashMap();
	    GroupDAO g = (GroupDAO) session.get(GroupDAO.class,group_id);
	    if(g == null) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return null;
	    }
	    group = BeanToHashMap.directPack(g);
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return group;
	}
	
	public List<HashMap> getAllGroup(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria c = session.createCriteria(GroupDAO.class);
	    List<GroupDAO> rs = c.list();
	    List<HashMap> result = new ArrayList<HashMap>();
	    for (Iterator<GroupDAO> i = rs.iterator();i.hasNext();){
	    	result.add(BeanToHashMap.directPack(i.next()));
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return result;
	}

}
