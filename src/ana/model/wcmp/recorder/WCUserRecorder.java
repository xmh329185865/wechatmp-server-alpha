package ana.model.wcmp.recorder;

import java.sql.Timestamp;
import java.util.HashMap;

import ana.database.service.Wechat_subscribersSVC;

@SuppressWarnings({"rawtypes","unchecked"})
public class WCUserRecorder extends Thread{
	
	private HashMap wcuser = new HashMap();
	private String operation;
	
	public WCUserRecorder(HashMap wcuser, String operation){
		this.wcuser.put("OpenID", wcuser.get("FromUserName").toString());
		this.wcuser.put("Timestamp", new Timestamp(
				1000*Long.parseLong(wcuser.get("CreateTime").toString())));
		this.wcuser.put("GroupID",(int)wcuser.get("GroupID"));
		this.operation = operation;
	}
	
	public void run(){
		Wechat_subscribersSVC w = new Wechat_subscribersSVC();
		if (this.operation.equalsIgnoreCase("add")){
			w.saveNewSubscribers(this.wcuser);
		} else if (this.operation.equalsIgnoreCase("delete")){
			w.deleteSubscribers(this.wcuser.get("OpenID").toString());
		}
	}
}
