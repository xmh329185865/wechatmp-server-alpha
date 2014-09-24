package ana.model.staff.mgr;

import java.util.HashMap;

import ana.database.service.StaffSVC;

@SuppressWarnings({"rawtypes"})
public class StaffMgr {
	
	public String getStaffNameByID(String stuid){
		HashMap staff = new StaffSVC().getStaffByStuID(stuid);
		return staff.get("staff_name").toString();
	}

}
