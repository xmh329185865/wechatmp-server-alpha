package ana.view.order;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ana.database.service.StaffSVC;

@SuppressWarnings({"rawtypes"})
public class Orderhanded {
	
	public String getAdminsContactByStuID(List<String> admins){
		StringBuffer backstring = new StringBuffer();
		
		StaffSVC s = new StaffSVC();
		for(Iterator<String> ii = admins.iterator();ii.hasNext();){
			String adminstuid = ii.next();
			HashMap admin = s.getStaffByStuID(adminstuid);
			backstring.append("<div align=\"center\"><label style=\"font-size:14\">");
			backstring.append(admin.get("staff_name").toString());
			backstring.append("£º</label><label style=\"font-size:14\">");
			backstring.append(admin.get("staff_cell").toString());
			backstring.append("£»</label></div>");	
		}
		return backstring.toString();
	}

}
