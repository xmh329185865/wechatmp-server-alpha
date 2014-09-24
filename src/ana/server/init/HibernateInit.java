package ana.server.init;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ana.database.hibernate.HibernateUtil;

public class HibernateInit {
	
	public void init(){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	}

}
