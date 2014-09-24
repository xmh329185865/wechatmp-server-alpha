package ana.database.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Blob;

import ana.database.dao.GroupRoleDAO;
import ana.database.dao.StaffDAO;
import ana.database.hibernate.HibernateUtil;
import ana.util.converter.BeanToHashMap;
import ana.util.converter.BlobProcess;
import ana.util.string.StringUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class StaffSVC {
	
	public List<HashMap> getAdminForServletContext(){
 		List<HashMap> admins = new ArrayList<HashMap>();
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria criteria =session.createCriteria(StaffDAO.class);
	    List l = criteria.list();
	    Iterator i = l.iterator();
	    while(i.hasNext()){
	    	HashMap admin = new HashMap();
	    	StaffDAO s = (StaffDAO) i.next();
	    	admin.put("Name",s.getStaff_name());
	    	admin.put("StuID",s.getStaff_stuid());
	    	admin.put("Cell", s.getStaff_cell());
	    	admin.put("QQ", s.getStaff_qq());
	    	admin.put("Domain", 
	    			StringUtil.tokenizedStringToList(
	    					BlobProcess.blobToStr((Blob) s.getStaff_domain(), null),"@"));
	    	admins.add(admin);
	    }
	    
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    
		return admins;
	}
	
	public String getWCOpenIDByStuID(String StuID){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria criteria =session.createCriteria(StaffDAO.class);
	    criteria.add(Restrictions.eq("staff_stuid", StuID));
	    List<StaffDAO> l = criteria.list();
	    if(l==null || l.size()<1 ) return "null";
	    String s = l.get(0).getStaff_wechatopenid();
	
	    tx.commit();
	    HibernateUtil.closeSession();
	    
	    return s;
	}

	public List<String> getWCOpenIDs(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria criteria =session.createCriteria(StaffDAO.class);
	    List<StaffDAO> l = criteria.list();
	    List<String> openids = new ArrayList<String>();
	    for (Iterator<StaffDAO> i = l.iterator();i.hasNext();){
	    	StaffDAO staff = i.next();
	    	openids.add(staff.getStaff_wechatopenid());
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    
	    return openids;
	}
	
	public List<HashMap> getAllJoinGroupRoleAuth(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria rolecri =session.createCriteria(GroupRoleDAO.class);
	    List<GroupRoleDAO> lgr = rolecri.list();
	    Criteria staffcri =session.createCriteria(StaffDAO.class);
	    List<StaffDAO> ls = staffcri.list();
	    
	    List<HashMap> result = new ArrayList<HashMap>();
	    for (Iterator<StaffDAO> i = ls.iterator();i.hasNext();){
	    	StaffDAO temp = i.next();
	    	HashMap staff = new HashMap(BeanToHashMap.directPack(temp));
	    	int sgrID = temp.getStaff_group_role_id();
	    	for (Iterator<GroupRoleDAO> ii = lgr.iterator(); ii.hasNext();){
	    		GroupRoleDAO g = ii.next();
	    		if (g.getGroup_role_id()==sgrID) {
	    			staff.put("staff_group_role_auth", g.getGroup_role_auth());
	    			break;
	    		}
	    	}
	    	result.add(staff);
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    
	    return result;
	}
	
	public List<HashMap> getAllGroupMembers(int group_id){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    List<HashMap> result = new ArrayList<HashMap>();
	    Criteria staffcri =session.createCriteria(StaffDAO.class);
	    staffcri.add(Restrictions.eq("staff_group_id", group_id));
	    List<StaffDAO> ls = staffcri.list();
	    for (Iterator<StaffDAO> i = ls.iterator();i.hasNext();){
	    	StaffDAO temp = i.next();
	    	HashMap staff = new HashMap(BeanToHashMap.directPack(temp));
	    	result.add(staff);
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    
	    return result;
	}
	
	public HashMap getStaffByStuID(String StuID){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria staffcri =session.createCriteria(StaffDAO.class);
	    staffcri.add(Restrictions.eq("staff_stuid", StuID));
	    try{
	    	StaffDAO staff = (StaffDAO) staffcri.list().get(0);
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return BeanToHashMap.directPack(staff);
	    } catch (NullPointerException | IndexOutOfBoundsException e) {	
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return null;
	    }
	}
	
	public void setWCOpenIDByStuID(String StuID, String openid){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria staffcri =session.createCriteria(StaffDAO.class);
	    staffcri.add(Restrictions.eq("staff_stuid", StuID));
	    try{
	    	StaffDAO staff = (StaffDAO) staffcri.list().get(0);
	    	staff.setStaff_wechatopenid(openid);
	    	session.save(staff);
	    	
	    	tx.commit();
		    HibernateUtil.closeSession();
		    return;
	    } catch (NullPointerException | IndexOutOfBoundsException e) {	
	    	tx.rollback();
		    HibernateUtil.closeSession();
	    	return;
	    }
	}
	
	public List<HashMap> getAllStaff(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria staffcri =session.createCriteria(StaffDAO.class);
	    List<StaffDAO> allstaff = staffcri.list();
	    List<HashMap> result = new ArrayList<HashMap>();
	    for (Iterator<StaffDAO> i = allstaff.iterator(); i.hasNext();){
	    	StaffDAO nowstaff = i.next();
	    	
	    	HashMap temp = BeanToHashMap.directPack(nowstaff);
	    	result.add(temp);
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return result;
	}
}
