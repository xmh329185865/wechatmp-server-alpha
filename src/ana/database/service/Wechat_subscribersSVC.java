package ana.database.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ana.database.dao.Wechat_subscribersDAO;
import ana.database.hibernate.HibernateUtil;

@SuppressWarnings("rawtypes")
public class Wechat_subscribersSVC {
	
	public void saveNewSubscribers(HashMap subscriberinfo){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
		
	    Wechat_subscribersDAO subs = new Wechat_subscribersDAO();
	    subs.setWcuser_openid(subscriberinfo.get("OpenID").toString());
		subs.setSubscribe_timestamp((Timestamp)subscriberinfo.get("Timestamp"));
		if (subscriberinfo.get("GroupID") != null)
			subs.setWcuser_group_id((int)subscriberinfo.get("GroupID"));
		
		session.save(subs);
		
	    tx.commit();
	    HibernateUtil.closeSession();
	}
	
	public void deleteSubscribers(String openid){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    String hql = "delete Wechat_subscribersDAO where wcuser_openid = '"+openid+"'";
	    Query q = session.createQuery(hql);
	    q.executeUpdate();
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	}
	
	public void checkSaveSubs(String openid){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria subc = session.createCriteria(Wechat_subscribersDAO.class);
	    subc.add(Restrictions.eq("wcuser_openid", openid));
	    List s = subc.list();
	    if (s==null || s.size() <= 0) {
	    	Wechat_subscribersDAO newuser = new Wechat_subscribersDAO();
	    	newuser.setWcuser_group_id(0);
	    	newuser.setWcuser_openid(openid);
	    	newuser.setSubscribe_timestamp(new Timestamp(new Date().getTime()));
	    	session.save(newuser);
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	}

}
