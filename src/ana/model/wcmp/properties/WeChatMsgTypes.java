package ana.model.wcmp.properties;

import java.util.HashMap;

public class WeChatMsgTypes {
	
	public static final int REQ_TEXT = 0;
	public static final int REQ_IMG = 1;
	public static final int REQ_VOICE = 2;
	public static final int REQ_VIDEO = 3;
	public static final int REQ_LOC = 4;
	public static final int REQ_LINK = 5;
	public static final int REQ_EVENT = 6;
	
	public static final int RESP_TEXT = 10;
	public static final int RESP_IMG = 11;
	public static final int RESP_VOICE = 12;
	public static final int RESP_VIDEO = 13;
	public static final int RESP_MUSIC = 14;
	public static final int RESP_NEWS = 15;

	//请求消息分类
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void formatType(HashMap newmsg){
		String rawtype = newmsg.get("MsgType").toString().toLowerCase();
		int type = -1;
		switch(rawtype){
		case "text":
			type = REQ_TEXT;
			break;
		case "image":
			type = REQ_IMG;
			break;
		case "voice":
			type = REQ_VOICE;
			break;
		case "video":
			type = REQ_VIDEO;
			break;
		case "location":
			type = REQ_LOC;
			break;
		case "link":
			type = REQ_LINK;
			break;
		case "event":
			type = REQ_EVENT;
			break;
		default:
		}
		newmsg.put("MsgType", type);
	}
	
}
