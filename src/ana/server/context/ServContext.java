package ana.server.context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import ana.database.service.BuildingSVC;
import ana.database.service.StaffSVC;

public class ServContext {

	private static HashMap<String,Object> context = new HashMap<String,Object>();
	
	static{
		setAttribute("AdminList", new StaffSVC().getAdminForServletContext());
		setAttribute("SupportedBuilding", new BuildingSVC().getAllBuilding());
	}
	
	public static void globalinit(){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+" "+ServContext.class.getName()+" ��̬����������ϣ�"+context.getClass().getName());		
	}
	
	public synchronized static void setAttribute(String attrname, Object attr) {
		context.put(attrname, attr);
	}

	public static Object getAttribute(String attrname) {
		return context.get(attrname);
	}

}
