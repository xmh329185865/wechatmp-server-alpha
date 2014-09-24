package ana.database.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ana.database.dao.BuildingDAO;
import ana.database.hibernate.HibernateUtil;
import ana.util.converter.BeanToHashMap;
@SuppressWarnings({"rawtypes","unchecked"})
public class BuildingSVC {
	
	public HashMap getBuildingByID(long building_id){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    HashMap building = new HashMap();
	    BuildingDAO d = (BuildingDAO) session.get(BuildingDAO.class,building_id);
	    if(d == null) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return null;
	    }
	    building = BeanToHashMap.directPack(d);
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return building;
	}
	
	public List<HashMap> getAllBuilding(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria c = session.createCriteria(BuildingDAO.class);
	    List<BuildingDAO> rs = c.list();
	    List<HashMap> result = new ArrayList<HashMap>();
	    for (Iterator<BuildingDAO> i = rs.iterator();i.hasNext();){
	    	result.add(BeanToHashMap.directPack(i.next()));
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return result;
	}

}
