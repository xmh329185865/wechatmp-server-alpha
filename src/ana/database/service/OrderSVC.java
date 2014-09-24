package ana.database.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Calendar;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.LobHelper;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ana.database.dao.OrderDAO;
import ana.database.hibernate.HibernateUtil;
import ana.model.auth.check.WCMPChecker;
import ana.model.auth.properties.IDCheckMethod;
import ana.model.auth.properties.ProcessCode;
import ana.model.order.properties.OrderStatus;
import ana.util.converter.BeanToHashMap;
import ana.view.wcmp.msgpool.MsgPool;

@SuppressWarnings({"rawtypes","unchecked"})
public class OrderSVC {
	
	private String EncodingCharset = "utf-8";
	
	public long addNewBasicOrderToDB(HashMap orderinfo) {
	    Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    LobHelper l = session.getLobHelper();
	    
	    OrderDAO neworder = new OrderDAO();
	    neworder.setClient_name(orderinfo.get("client_name").toString());
	    neworder.setClient_cell(orderinfo.get("client_cell").toString());
	    neworder.setClient_building(orderinfo.get("client_building").toString());
	    neworder.setClient_room(orderinfo.get("client_room").toString());
	    neworder.setOrder_faultip(orderinfo.get("order_faultip").toString());
	    if(orderinfo.get("order_wechatid")!=null){
	    	neworder.setOrder_wechatid(orderinfo.get("order_wechatid").toString());
	    }

	    try {
			neworder.setOrder_description(l.createBlob(
					orderinfo.get("order_description").toString().getBytes(this.EncodingCharset)));
		} catch (UnsupportedEncodingException e) {
			neworder.setOrder_description(l.createBlob(
					orderinfo.get("order_description").toString().getBytes()));
			e.printStackTrace();
		}
	   
	    neworder.setOrder_status((int)orderinfo.get("order_status"));
	    neworder.setOrder_supadmin(orderinfo.get("order_supadmin").toString());
	    neworder.setOrder_cercode(orderinfo.get("order_cercode").toString());
	    neworder.setOrder_resvtime((Calendar) orderinfo.get("order_resvtime"));
	    
	    session.save(neworder);
	    long prmKey = neworder.getOrder_id();

	    tx.commit();
	    HibernateUtil.closeSession();
	    return prmKey;
	}
	
	public HashMap getCercodeByID(long order_id){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    OrderDAO o = (OrderDAO) session.get(OrderDAO.class, order_id);
	    HashMap order = new HashMap();
	    order.put("order_cercode", o.getOrder_cercode());
	    order.put("order_wechatid", o.getOrder_wechatid());
	    order.put("order_status", o.getOrder_status());
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return order;
	}
	
	public int rateOrder(long order_id, String order_ratedesc, int order_ratelevel, String wcid){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    OrderDAO o = (OrderDAO) session.get(OrderDAO.class, order_id);
	    if(o == null) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return ProcessCode.ERR_NO_SUCH_RECORD;
	    }
	    if(!WCMPChecker.orderCheckWCMPID(wcid, o, IDCheckMethod.ORDER_CHECK_INDIVIDUAL)) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return ProcessCode.ERR_INVALID_ID;
	    }
	    int status = o.getOrder_status();
	    if(status!=OrderStatus.FIXED_UNRATED) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return status;
	    }
	    o.setOrder_ratelevel(order_ratelevel);
	    o.setOrder_status(OrderStatus.FIXED_RATED);
	    try {
			o.setOrder_ratedesc(session.getLobHelper().createBlob(
					order_ratedesc.getBytes(this.EncodingCharset)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    session.update(o);
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return ProcessCode.DONE;
	}
	
	public List<HashMap> getOrderForWechatSearch(long order_id, String order_wechatid){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    List<HashMap> order = new ArrayList<HashMap>();
	    
	    HashMap ordertemp = new HashMap();
	    OrderDAO o = (OrderDAO) session.get(OrderDAO.class, order_id);
	    if(o == null) {
	    	ordertemp.put("KeyLabel", "err");
	    	ordertemp.put("KeyValue", MsgPool.ERR_NO_ORDER_FOUND);
	    	order.add(new HashMap(ordertemp));
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return order;
	    } else if (!WCMPChecker.orderCheckWCMPID(order_wechatid, o, IDCheckMethod.ORDER_CHECK_INCLUDE_ALLADMINS)) {
	    	ordertemp.put("KeyLabel", "err");
	    	ordertemp.put("KeyValue", MsgPool.ERR_INVALID_ID);
	    	order.add(new HashMap(ordertemp));
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return order;
	    }
	    
	    ordertemp.put("KeyLabel", "err");
	    ordertemp.put("KeyValue", "none");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_id");
	    ordertemp.put("KeyValue", o.getOrder_id());
	    ordertemp.put("KeyName", "故障单号");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "client_name");
	    ordertemp.put("KeyValue", o.getClient_name());
	    ordertemp.put("KeyName", "报修人姓名");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "client_cell");
	    ordertemp.put("KeyValue", o.getClient_cell());
	    ordertemp.put("KeyName", "报修人电话");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_timestamp");
	    ordertemp.put("KeyValue", o.getOrder_timestamp());
	    ordertemp.put("KeyName", "报修时间");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "client_building");
	    ordertemp.put("KeyValue", o.getClient_building());
	    ordertemp.put("KeyName", "报修人宿舍");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "client_room");
	    ordertemp.put("KeyValue", o.getClient_room());
	    ordertemp.put("KeyName", "报修人房号");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_faultip");
	    ordertemp.put("KeyValue", o.getOrder_faultip());
	    ordertemp.put("KeyName", "报修IP");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_description");
	    ordertemp.put("KeyValue", o.getOrder_description());
	    ordertemp.put("KeyName", "故障描述");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_status");
	    ordertemp.put("KeyValue", o.getOrder_status());
	    ordertemp.put("KeyName", "报修进度");
	    order.add(new HashMap(ordertemp));
	    
	    if(o.getOrder_resvtime()!=null){
	    	ordertemp.put("KeyLabel", "order_resvtime");
		    ordertemp.put("KeyValue", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.getOrder_resvtime().getTime()));
		    ordertemp.put("KeyName", "预约时间");
		    order.add(new HashMap(ordertemp));
	    }    
	    
	    ordertemp.put("KeyLabel", "order_nowadmin");
	    ordertemp.put("KeyValue", o.getOrder_nowadmin());
	    ordertemp.put("KeyName", "接单网管");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_diagnosis");
	    ordertemp.put("KeyValue", o.getOrder_diagnosis());
	    ordertemp.put("KeyName", "故障诊断");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_result");
	    ordertemp.put("KeyValue", o.getOrder_result());
	    ordertemp.put("KeyName", "处理结果");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_fixedtime");
	    ordertemp.put("KeyValue", o.getOrder_fixedtime());
	    ordertemp.put("KeyName", "完成时间");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_solveway");
	    ordertemp.put("KeyValue", o.getOrder_solveway());
	    ordertemp.put("KeyName", "解决途径");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_ratedesc");
	    ordertemp.put("KeyValue", o.getOrder_ratedesc());
	    ordertemp.put("KeyName", "评价描述");
	    order.add(new HashMap(ordertemp));
	    
	    ordertemp.put("KeyLabel", "order_ratelevel");
	    ordertemp.put("KeyValue", o.getOrder_ratelevel());
	    ordertemp.put("KeyName", "总体评价");
	    order.add(new HashMap(ordertemp));
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return order;
	    
	}
	
	public int deleteOrderByID(long order_id, String wcid){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    OrderDAO order = (OrderDAO) session.get(OrderDAO.class, order_id);
	    if (order == null) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return ProcessCode.ERR_NO_SUCH_RECORD;
	    }
	    if(!WCMPChecker.orderCheckWCMPID(wcid, order, IDCheckMethod.ORDER_CHECK_INDIVIDUAL)) {
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return ProcessCode.ERR_INVALID_ID;
	    }
	    int status = order.getOrder_status();
	    if(status!=OrderStatus.FIXED_UNRATED && status!=OrderStatus.PROCESSING_GETTED && status!=OrderStatus.PROCESSING_UNGETTED){
	    	tx.commit();
		    HibernateUtil.closeSession();
	    	return status;
	    }
	    order.setOrder_status(OrderStatus.FAILED_CANCELED);
	    session.update(order);
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return ProcessCode.DONE;
	}

	public HashMap getOrderById(long order_id){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    OrderDAO order = (OrderDAO) session.get(OrderDAO.class, order_id);
	    
	    if(order==null){
	    	tx.commit();
		    HibernateUtil.closeSession();
		    return null;
	    }
	    
	    HashMap result = BeanToHashMap.directPack(order);
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return result;
	}
	
	public List<HashMap> getOrdersForAdmin(
			int startpage, int pagelim, String id, String keyword, Timestamp tstart, Timestamp tend, Calendar rstart, Calendar rend, List<Integer> status){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria getorder = session.createCriteria(OrderDAO.class);
	    if (id != null){
	    	getorder.add(Restrictions.and(Restrictions.eq("order_id", Long.parseLong(id))));
	    }
	    if (keyword != null){
	    	keyword = "%"+keyword+"%";
	    	Criterion keywordsearch = Restrictions.or(
	    			Restrictions.like("order_faultip", keyword),Restrictions.like("client_name", keyword),Restrictions.like("client_cell", keyword),Restrictions.like("client_building", keyword),Restrictions.like("client_room", keyword));
	    	getorder.add(Restrictions.and(keywordsearch));
	    }
	    if (tstart != null){
	    	getorder.add(Restrictions.and(Restrictions.ge("order_timestamp", tstart)));
	    }
	    if (tend != null){
	    	getorder.add(Restrictions.and(Restrictions.le("order_timestamp", tend)));
	    }
	    if (rstart != null){
	    	getorder.add(Restrictions.and(Restrictions.ge("order_resvtime", rstart)));
	    }
	    if (rend != null){
	    	getorder.add(Restrictions.and(Restrictions.le("order_resvtime", rend)));
	    }
	    if (status != null){
	    	List<Criterion> criterions = new ArrayList<Criterion>();
	    	for (Iterator<Integer> i = status.iterator(); i.hasNext();){
		    	criterions.add(Restrictions.eq("order_status", i.next()));
		    }
	    	getorder.add(Restrictions.or(criterions.toArray(new Criterion[0])));
	    }
	    getorder.addOrder(Order.desc("order_timestamp"));
	    getorder.setFirstResult((startpage-1)*pagelim);
	    getorder.setMaxResults(pagelim);
	    List<OrderDAO> resulttemp = getorder.list();
	    List<HashMap> result = new ArrayList<HashMap>();
	    
	    for (Iterator<OrderDAO> i = resulttemp.iterator(); i.hasNext();){
	    	OrderDAO noworder = i.next();
	    	result.add(BeanToHashMap.directPack(noworder));
	    }
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return result;
	}
	
	public int getOrderCount(String id, String keyword, Timestamp tstart, Timestamp tend, Calendar rstart, Calendar rend, List<Integer> status){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria getorder = session.createCriteria(OrderDAO.class);
	    if (id != null){
	    	getorder.add(Restrictions.and(Restrictions.eq("order_id", Long.parseLong(id))));
	    }
	    if (keyword != null){
	    	keyword = "%"+keyword+"%";
	    	Criterion keywordsearch = Restrictions.or(
	    			Restrictions.like("order_faultip", keyword),Restrictions.like("client_name", keyword),Restrictions.like("client_cell", keyword),Restrictions.like("client_building", keyword),Restrictions.like("client_room", keyword));
	    	getorder.add(Restrictions.and(keywordsearch));
	    }
	    if (tstart != null){
	    	getorder.add(Restrictions.and(Restrictions.ge("order_timestamp", tstart)));
	    }
	    if (tend != null){
	    	getorder.add(Restrictions.and(Restrictions.le("order_timestamp", tend)));
	    }
	    if (rstart != null){
	    	getorder.add(Restrictions.and(Restrictions.ge("order_resvtime", rstart)));
	    }
	    if (rend != null){
	    	getorder.add(Restrictions.and(Restrictions.le("order_resvtime", rend)));
	    }
	    if (status != null){
	    	List<Criterion> criterions = new ArrayList<Criterion>();
	    	for (Iterator<Integer> i = status.iterator(); i.hasNext();){
		    	criterions.add(Restrictions.eq("order_status", i.next()));
		    }
	    	getorder.add(Restrictions.or(criterions.toArray(new Criterion[0])));
	    }
	    getorder.setProjection(Projections.rowCount()); 
	    int count = Integer.parseInt(getorder.uniqueResult().toString());
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	    return count;
	}
	
	public void adminGetOrder(List<Long> orderids, String adminid){
		Session session = HibernateUtil.currentSession();
	    Transaction tx = session.beginTransaction();
	    
	    Criteria c = session.createCriteria(OrderDAO.class);
	    c.setCacheMode(CacheMode.IGNORE);
	    
	    List<Criterion> criterions = new ArrayList<Criterion>();
	    for (Iterator<Long> i = orderids.iterator(); i.hasNext();){
	    	criterions.add(Restrictions.eq("order_id", i.next()));
	    }
	    c.add(Restrictions.or(criterions.toArray(new Criterion[0])));
	    
	    ScrollableResults orders= c.scroll(ScrollMode.FORWARD_ONLY);
	    while(orders.next()){
	    	OrderDAO o = (OrderDAO) orders.get(0);
	    	o.setOrder_status(OrderStatus.PROCESSING_GETTED);
	    	o.setOrder_nowadmin(adminid);
	    }  
	    session.flush();
	    session.clear();
	    
	    tx.commit();
	    HibernateUtil.closeSession();
	}
	
	public String getEncodingCharset() {
		return EncodingCharset;
	}

	public void setEncodingCharset(String encodingCharset) {
		EncodingCharset = encodingCharset;
	}

}
