package ana.database.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ana.database.dao.GroupRoleDAO;
import ana.database.hibernate.HibernateUtil;
import ana.util.converter.BeanToHashMap;
@SuppressWarnings({"rawtypes","unchecked"})
public class GroupRoleSVC {
	
	public HashMap getGroupRoleByID(long group_role_id){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    HashMap grouprole = new HashMap();
	    GroupRoleDAO o = (GroupRoleDAO) session.get(GroupRoleDAO.class, group_role_id);
	    if(o == null) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return null;
	    }
	    grouprole = BeanToHashMap.directPack(o);
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return grouprole;
	}
	
	public List<HashMap> getAllGroupRole(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria c = session.createCriteria(GroupRoleDAO.class);
	    List<GroupRoleDAO> rs = c.list();
	    List<HashMap> result = new ArrayList<HashMap>();
	    for (Iterator<GroupRoleDAO> i = rs.iterator();i.hasNext();){
	    	result.add(BeanToHashMap.directPack(i.next()));
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return result;
	}

}
