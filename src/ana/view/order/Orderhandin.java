package ana.view.order;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ana.server.context.ServContext;

@SuppressWarnings({"unchecked","rawtypes"})
public class Orderhandin {
	
	public String getBuildingList(){
		StringBuffer selectlist = new StringBuffer("<select id=\'client_building\' name=\'client_building\' size=\"1\" style=\"width:120px\" >");
			for (Iterator<HashMap> i = ((List<HashMap>) ServContext.getAttribute("SupportedBuilding")).iterator();i.hasNext();){
				HashMap temp = i.next();
				selectlist.append("<option value=\'");
				selectlist.append(temp.get("building_name").toString());
				selectlist.append("\'>");
				selectlist.append(temp.get("building_name").toString());
				selectlist.append("</option>");
		}
		selectlist.append("</select>");
		return selectlist.toString();
	}
}
