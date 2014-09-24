package ana.model.auth.check;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ana.database.dao.OrderDAO;
import ana.database.service.StaffSVC;
import ana.model.auth.properties.IDCheckMethod;
import ana.util.converter.BeanToHashMap;

@SuppressWarnings("rawtypes")
public class WCMPChecker {
	
	public static List<HashMap> alladmins = new ArrayList<HashMap>();
	static {
		List<HashMap> rawadmins = new StaffSVC().getAllJoinGroupRoleAuth();
		for (Iterator<HashMap> i = rawadmins.iterator();i.hasNext();){
			HashMap temp = i.next();
			
			alladmins.add(BeanToHashMap.anyToString(temp));
		}
		
	}
	
	public static void globalinit(){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+" "+WCMPChecker.class.getName()+" 静态参数加载完毕！"+alladmins.getClass().getName());
	}
	
	public static boolean orderCheckWCMPID(String fromid, OrderDAO order, int checkMethod){
		
		StaffSVC staffsvc = new StaffSVC();
		switch(checkMethod){
		case IDCheckMethod.ORDER_CHECK_INDIVIDUAL:
			if (fromid.equals(order.getOrder_wechatid())) return true;
			return false;
		case IDCheckMethod.ORDER_CHECK_INCLUDE_NOWADMIN:
			if (fromid.equals(order.getOrder_wechatid()) || fromid.equals(getStaffByStuID(order.getOrder_nowadmin()).get("staff_wechatopenid").toString())) return true;
			return false;
		case IDCheckMethod.ORDER_CHECK_INCLUDE_LEADERS:
			if (fromid.equals(order.getOrder_wechatid())) return true;
			List<HashMap> temp = staffsvc.getAllJoinGroupRoleAuth();
			for (Iterator<HashMap> i = temp.iterator(); i.hasNext();){
				HashMap ttemp = i.next();
				if (ttemp.get("staff_wechatopenid").toString().equals(fromid)){
					if((int)ttemp.get("staff_group_role_auth")>9){
						return true;
					} else {
						return false;
					}
				}
			}
			return false;
		case IDCheckMethod.ORDER_CHECK_INCLUDE_GROUPMEMBERS:
			if (fromid.equals(order.getOrder_wechatid())) return true;
			String nowadmin = order.getOrder_nowadmin();
			if ( nowadmin == null || nowadmin.equals("")) return false;
			HashMap staff = staffsvc.getStaffByStuID(nowadmin);
			List<HashMap> groupmembers = staffsvc.getAllGroupMembers((int)staff.get("staff_group_id"));
			for (Iterator<HashMap> i = groupmembers.iterator();i.hasNext();){
				HashMap member = i.next();
				if(member.get("staff_wechatopenid").toString().equals(fromid)) return true;
			}
			return false;
		case IDCheckMethod.ORDER_CHECK_INCLUDE_ALLADMINS:
			if (fromid.equals(order.getOrder_wechatid())) return true;
			try{
				for (Iterator<HashMap> i = alladmins.iterator(); i.hasNext();){
					String openid = i.next().get("staff_wechatopenid").toString();
					
					if(openid.equals(fromid)) return true;
				}
			} catch(NullPointerException e){
				return false;
			}
			return false;
		default:
			if (fromid.equals(order.getOrder_wechatid())) return true;
			return false;
		}
			
	}

	private static HashMap getStaffByStuID(String StuID){
		
		for (Iterator<HashMap> i = alladmins.iterator(); i.hasNext();){
			HashMap temp = i.next();
			
			if(temp.get("staff_stuid").equals(StuID)) return temp;
		}
		
		return null;
	}
	
}
