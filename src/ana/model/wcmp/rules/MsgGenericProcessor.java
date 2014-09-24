package ana.model.wcmp.rules;

import java.util.Date;
import java.util.HashMap;

import ana.model.wcmp.properties.WeChatMsgTypes;
import ana.view.wcmp.msgpool.MsgPool;

@SuppressWarnings({"rawtypes","unchecked"})
public class MsgGenericProcessor {
	
	private HashMap reqmsg = new HashMap();
	private HashMap respmsg = new HashMap();
	
	public MsgGenericProcessor(HashMap reqmsg){
		this.reqmsg = reqmsg;
		this.respmsg.put("FromUserName", this.reqmsg.get("ToUserName").toString());
		this.respmsg.put("ToUserName", this.reqmsg.get("FromUserName").toString());
		this.respmsg.put("CreateTime", new Date().getTime()/1000);
	}
	
	public boolean process(){
		switch((int)this.reqmsg.get("MsgType")){
		case WeChatMsgTypes.REQ_TEXT:
			TxtRespRules resptxt = new TxtRespRules(this.reqmsg);
			resptxt.fillReturnMsg(this.respmsg);
			break;
		case WeChatMsgTypes.REQ_IMG:
			break;
		case WeChatMsgTypes.REQ_LOC:
			break;
		case WeChatMsgTypes.REQ_LINK:
			break;
		case WeChatMsgTypes.REQ_VIDEO:
			break;
		case WeChatMsgTypes.REQ_VOICE:
			break;
		default:
			this.respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
			this.respmsg.put("Content", MsgPool.ERR_UNKNOWN_CONTENT);
		}
		return true;
	}

	public HashMap getReqmsg() {
		return reqmsg;
	}

	public void setReqmsg(HashMap reqmsg) {
		this.reqmsg = reqmsg;
	}

	public HashMap getRespmsg() {
		return respmsg;
	}

	public void setRespmsg(HashMap respmsg) {
		this.respmsg = respmsg;
	}

}
