package ana.view.dbmapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ana.util.loader.XMLConfLoader;

@SuppressWarnings({"unchecked","rawtypes"})
public class OrderMapping {
	
	private static List<HashMap<String,String>> namemapping = XMLConfLoader.getDBMappingList("OrderMapping.xml");
	
	public List<HashMap> getMappedOrder(HashMap order){
		
		List<HashMap> orderinfo = new ArrayList<HashMap>();
		Set keyset = order.keySet();
		for(Iterator i = keyset.iterator(); i.hasNext();){
			String keyname = i.next().toString();
	
			for (Iterator<HashMap<String,String>> ii = namemapping.iterator(); ii.hasNext();){
				HashMap<String,String> temp = ii.next();
				
				if (temp.get("dbfield").equals(keyname)){
					HashMap oinfounit = new HashMap();
					oinfounit.put("KeyName", temp.get("name"));
					oinfounit.put("KeyValue", order.get(keyname));
					oinfounit.put("KeyPriority", temp.get("priority"));
					oinfounit.put("KeyLabel", temp.get("dbfield"));
					orderinfo.add(oinfounit);
					break;
				}
			}
		}
		return orderinfo;
	}

}
