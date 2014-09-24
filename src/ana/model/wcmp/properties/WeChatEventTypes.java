package ana.model.wcmp.properties;

import java.util.HashMap;

public class WeChatEventTypes {
	
	public static final int EVENT_SUB = 1;
	public static final int EVENT_UNSUB = 2;
	public static final int EVENT_SCAN = 3;
	public static final int EVENT_LOC = 4;
	public static final int EVENT_CLICK = 5;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void formatType(HashMap reqMsg) {
		
		String rawEvent = reqMsg.get("Event").toString().toLowerCase();
		int event = -1;
		switch(rawEvent){
		case "subscribe":
			event = EVENT_SUB;
			break;
		case "unsubscribe":
			event = EVENT_UNSUB;
			break;
		case "scan":
			event = EVENT_SCAN;
			break;
		case "location":
			event = EVENT_LOC;
			break;
		case "click":
			event = EVENT_CLICK;
			break;
		default:
		}
		
		reqMsg.put("Event", event);
		
	}
	
}
