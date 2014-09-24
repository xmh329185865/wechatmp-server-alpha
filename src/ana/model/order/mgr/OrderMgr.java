package ana.model.order.mgr;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import ana.database.service.OrderSVC;
import ana.model.order.properties.OrderStatus;
import ana.server.context.ServContext;
import ana.util.converter.BeanToHashMap;
import ana.util.string.StringUtil;

import java.util.Set;

@SuppressWarnings({"rawtypes","unchecked"})
public class OrderMgr {
	
	private static final char[] codecharlib = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l',
		'm','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U',
		'V','W','X','Y','Z'};
	
	public long saveNewOrder(HashMap neworder){
		List<HashMap> supadmins = getAdminByBuilding(neworder.get("client_building").toString());
		StringBuffer supadminstuid = new StringBuffer();
		for(Iterator<HashMap> i = supadmins.iterator();i.hasNext();){
			supadminstuid.append(i.next().get("StuID").toString()+"@");
		}
		neworder.put("order_supadmin", supadminstuid.toString());
		neworder.put("order_cercode", OrderMgr.generateCode(64));
		neworder.put("order_status", OrderStatus.PROCESSING_UNGETTED);
		if(neworder.get("order_wechatid").toString().equalsIgnoreCase("null") || neworder.get("order_wechatid").toString().equals("")){
			neworder.put("order_wechatid",null);
		}
		if(neworder.containsKey("order_resvtime")){
			String rawtime = neworder.get("order_resvtime").toString();
			neworder.put("order_resvtime", rawtime.replace("T", " "));
		}
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date d = df.parse(neworder.get("order_resvtime").toString());
			Calendar cal=Calendar.getInstance();
			cal.setTime(d);
			neworder.put("order_resvtime", cal);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		OrderSVC o = new OrderSVC();
		return o.addNewBasicOrderToDB(neworder);
	}

	//生成凭证的方法传入参数为凭证长度，可以任意指定。由于没有防重复机制，为了减少重复的可能性，本案例中指定了64位，在案例对应的数据量下可以视为不会重复
	private static String generateCode(int size){
		//由于需要多次添加操作，此处利用StringBuffer优化程序性能。
		StringBuffer code = new StringBuffer("");
		//建立随机对象
		Random r = new Random();
		
		int range = codecharlib.length;
		int tempcursor;		//库指针
		for (int i = 0; i < size; i++) {	//在库中随机取size次，以生成指定位数的凭证（密钥）
			tempcursor = r.nextInt(range);	//获取一个库目录范围内的随机数作为库指针的指向
			code.append(codecharlib[tempcursor]);	//将指向的字符加入结果		
		}
			
		return code.toString();
	}

	//通过给定的宿舍楼号获取管理员的姓名和联系电话
	public List<HashMap> getAdminByBuilding(String building){
		List<HashMap> admins = (List<HashMap>) ServContext.getAttribute("AdminList");
		List<HashMap> supadmins = new ArrayList<HashMap>();
		for(Iterator<HashMap> i = admins.iterator();i.hasNext();){
			HashMap admin = i.next();
			List<String> domain = (List<String>) admin.get("Domain");
			for(Iterator<String> k = domain.iterator();k.hasNext();){
				if (k.next().equals(building)){
					supadmins.add(admin);
					break;
				}
			}
		}
		
		return supadmins;
	}
	
	public String getAdminNameByStuID(String stuid){
		List<HashMap> admins = (List<HashMap>) ServContext.getAttribute("AdminList");
		for(Iterator<HashMap> i = admins.iterator();i.hasNext();){
			HashMap admin = i.next();
			if(admin.get("StuID").toString().equals(stuid)) return admin.get("Name").toString();
		}
		
		return stuid;
	}

	public HashMap<String,String> getOrderByIDnoNULLS(Long order_id){
		HashMap<String,String> order = new OrderSVC().getOrderById(order_id);
		
		if(order==null){
			return null;
		}
		
		order = BeanToHashMap.anyToString(order);
		Set keyset = order.keySet();
		for(Iterator i = keyset.iterator(); i.hasNext();){
			String keyname = i.next().toString();
			if (order.get(keyname)==null) i.remove();
		}
		return order;
	}
	
	public List<HashMap<String,String>> getOrderlist(int startpage, int pagelim, HashMap<String,String> searchreq){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String search_id = (searchreq.get("search_id") == null || searchreq.get("search_id").equals("")) ? null:searchreq.get("search_id");
		String search_keyword = (searchreq.get("search_keyword") == null || searchreq.get("search_keyword").equals("")) ? null:searchreq.get("search_keyword");
		Timestamp search_timestamp_start,search_timestamp_end;
		Calendar search_resvtime_start,search_resvtime_end;
		if (searchreq.get("search_resvtime_start") == null || searchreq.get("search_resvtime_start").equals("")){
			search_resvtime_start = null;
		} else {
			search_resvtime_start = Calendar.getInstance();
			try {
				search_resvtime_start.setTime(df.parse(searchreq.get("search_resvtime_start")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (searchreq.get("search_resvtime_end") == null || searchreq.get("search_resvtime_end").equals("")){
			search_resvtime_end = null;
		} else {
			search_resvtime_end = Calendar.getInstance();
			try {
				search_resvtime_end.setTime(df.parse(searchreq.get("search_resvtime_end")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		try {
			search_timestamp_start = (searchreq.get("search_timestamp_start") == null || searchreq.get("search_timestamp_start").equals("")) ? 
					null:new Timestamp(df.parse(searchreq.get("search_timestamp_start")).getTime());
			search_timestamp_end = (searchreq.get("search_timestamp_end") == null || searchreq.get("search_timestamp_end").equals("")) ? 
					null:new Timestamp(df.parse(searchreq.get("search_timestamp_end")).getTime());
		} catch (ParseException e) {
			search_timestamp_start = null;
			search_timestamp_end = null;
			e.printStackTrace();
		}
		List<String> search_statustemp = (searchreq.get("search_status") == null || searchreq.get("search_status").equals("")) ? null:StringUtil.tokenizedStringToList(searchreq.get("search_status"),"@");
		List<Integer> search_status = null;
		if (search_statustemp != null){
			search_status = new ArrayList<Integer>();
			for (Iterator<String> i = search_statustemp.iterator(); i.hasNext();){
				search_status.add(Integer.parseInt(i.next()));
			}
		}
		List<HashMap> ordersraw = new OrderSVC().getOrdersForAdmin(
				startpage, pagelim, search_id, search_keyword,search_timestamp_start,search_timestamp_end,search_resvtime_start,search_resvtime_end,search_status);
		
		List<HashMap<String,String>> orders = new ArrayList<HashMap<String,String>>();
		
		for(Iterator<HashMap> i = ordersraw.iterator(); i.hasNext();){
			HashMap order = i.next();
			orders.add(BeanToHashMap.anyToString(order));
		}
		return orders;
	}
	
	public int getOrderCount(HashMap<String,String> searchreq){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String search_id = (searchreq.get("search_id") == null || searchreq.get("search_id").equals("")) ? null:searchreq.get("search_id");
		String search_keyword = (searchreq.get("search_keyword") == null || searchreq.get("search_keyword").equals("")) ? null:searchreq.get("search_keyword");
		Timestamp search_timestamp_start,search_timestamp_end;
		Calendar search_resvtime_start,search_resvtime_end;
		if (searchreq.get("search_resvtime_start") == null || searchreq.get("search_resvtime_start").equals("")){
			search_resvtime_start = null;
		} else {
			search_resvtime_start = Calendar.getInstance();
			try {
				search_resvtime_start.setTime(df.parse(searchreq.get("search_resvtime_start")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (searchreq.get("search_resvtime_end") == null || searchreq.get("search_resvtime_end").equals("")){
			search_resvtime_end = null;
		} else {
			search_resvtime_end = Calendar.getInstance();
			try {
				search_resvtime_end.setTime(df.parse(searchreq.get("search_resvtime_end")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		try {
			search_timestamp_start = (searchreq.get("search_timestamp_start") == null || searchreq.get("search_timestamp_start").equals("")) ? 
					null:new Timestamp(df.parse(searchreq.get("search_timestamp_start")).getTime());
			search_timestamp_end = (searchreq.get("search_timestamp_end") == null || searchreq.get("search_timestamp_end").equals("")) ? 
					null:new Timestamp(df.parse(searchreq.get("search_timestamp_end")).getTime());
		} catch (ParseException e) {
			search_timestamp_start = null;
			search_timestamp_end = null;
			e.printStackTrace();
		}
		List<String> search_statustemp = (searchreq.get("search_status") == null || searchreq.get("search_status").equals("")) ? null:StringUtil.tokenizedStringToList(searchreq.get("search_status"),"@");
		List<Integer> search_status = null;
		if (search_statustemp != null){
			search_status = new ArrayList<Integer>();
			for (Iterator<String> i = search_statustemp.iterator(); i.hasNext();){
				search_status.add(Integer.parseInt(i.next()));
			}
		}
		return new OrderSVC().getOrderCount(search_id, search_keyword, search_timestamp_start, search_timestamp_end,search_resvtime_start,search_resvtime_end,search_status);
	}
	
	public void adminGetOrder(String orderlist, String adminid){
		List<String> temp = StringUtil.tokenizedStringToList(orderlist, "@");
		List<Long> orderids = new ArrayList<Long>();
		for (Iterator<String> i = temp.iterator(); i.hasNext();){
			orderids.add(Long.parseLong(i.next()));
		}
		new OrderSVC().adminGetOrder(orderids, adminid);
	}
}
