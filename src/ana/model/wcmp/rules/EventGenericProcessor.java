package ana.model.wcmp.rules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ana.model.wcmp.persistent.respmsg.Article;
import ana.model.wcmp.properties.WCUserGroup;
import ana.model.wcmp.properties.WeChatEventTypes;
import ana.model.wcmp.properties.WeChatMsgTypes;
import ana.model.wcmp.recorder.WCUserRecorder;
import ana.server.properties.ServerProperties;
import ana.view.wcmp.msgpool.MsgPool;

@SuppressWarnings({"rawtypes","unchecked"})
public class EventGenericProcessor {
	
	private HashMap reqmsg = new HashMap();
	private HashMap respmsg = new HashMap();
	
	public EventGenericProcessor(HashMap reqmsg){
		this.reqmsg = reqmsg;
		this.respmsg.put("FromUserName", reqmsg.get("ToUserName").toString());
		this.respmsg.put("ToUserName", reqmsg.get("FromUserName").toString());
		this.respmsg.put("CreateTime", new Date().getTime()/1000);
	}
	
	public boolean process(){
		
		switch((int)this.reqmsg.get("Event")){
		case WeChatEventTypes.EVENT_SUB:
			this.respmsg.put("MsgType", WeChatMsgTypes.RESP_NEWS);
			List<Article> s = new ArrayList<Article>();
			for (int i = 0; i < 6; i++) {
				Article temp = new Article();
				s.add(temp);
			}
			s.get(0).setPicUrl("http://"+ServerProperties.webrootpath+"/materials/welcome_title.png");
			s.get(0).setTitle(MsgPool.SUB_WELCOME[0]);
			s.get(1).setTitle(MsgPool.TITLE_DECLARE_FAULT);
			s.get(2).setTitle(MsgPool.TITLE_ACCEPT_RANGE);
			s.get(3).setTitle(MsgPool.TITLE_SEARCH_ORDER);
			s.get(4).setTitle(MsgPool.TITLE_HOW_TO_RATE);
			s.get(5).setTitle(MsgPool.SUB_WELCOME[1]);
			this.respmsg.put("Articles", s);
			this.respmsg.put("ArticleCount", s.size());
			
			this.reqmsg.put("GroupID", WCUserGroup.DEFAULT_GROUP);
			WCUserRecorder recadd = new WCUserRecorder(this.reqmsg,"add");
			recadd.start();
			break;
		case WeChatEventTypes.EVENT_UNSUB:
			this.respmsg = null;
			this.reqmsg.put("GroupID", WCUserGroup.DEFAULT_GROUP);
			WCUserRecorder recdelete = new WCUserRecorder(this.reqmsg,"delete");
			recdelete.start();
			break;
		case WeChatEventTypes.EVENT_LOC:
			break;
		case WeChatEventTypes.EVENT_SCAN:
			break;
		case WeChatEventTypes.EVENT_CLICK:
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
