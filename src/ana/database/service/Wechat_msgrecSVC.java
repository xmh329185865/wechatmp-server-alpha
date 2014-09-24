package ana.database.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;

import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ana.database.dao.Wechat_msgrecDAO;
import ana.database.hibernate.HibernateUtil;
import ana.model.wcmp.properties.WeChatEventTypes;
import ana.model.wcmp.properties.WeChatMsgTypes;
import ana.util.string.StringUtil;

@SuppressWarnings("rawtypes")
public class Wechat_msgrecSVC {
	
	private String EncodingCharset = "utf-8";
	private Session msgrec_session = HibernateUtil.currentSession();
	private Wechat_msgrecDAO w = new Wechat_msgrecDAO();
    private Transaction tx = msgrec_session.beginTransaction();
    private HashMap newmsg = new HashMap();
    
    public Wechat_msgrecSVC(HashMap newmsg){
    	this.newmsg = newmsg;
    }
	
	public void sortAndSave(){
		int msg_type = (int)this.newmsg.get("MsgType");
		
		this.w.setMsg_fromuser(this.newmsg.get("FromUserName").toString());
		this.w.setMsg_touser(this.newmsg.get("ToUserName").toString());
		this.w.setMsg_type(msg_type);
		this.w.setMsg_timestamp(
	    		new Timestamp(1000*Long.parseLong(this.newmsg.get("CreateTime").toString())));
		
		switch(msg_type){
		case WeChatMsgTypes.REQ_TEXT:
			this.w.setMsg_wcmsgid(this.newmsg.get("MsgId").toString());
			this.recordTextMsg();
			break;
		case WeChatMsgTypes.REQ_IMG:
			this.w.setMsg_wcmsgid(this.newmsg.get("MsgId").toString());
			this.recordImgMsg();
			break;
		case WeChatMsgTypes.REQ_LINK:
			this.w.setMsg_wcmsgid(this.newmsg.get("MsgId").toString());
			this.recordLinkMsg();
			break;
		case WeChatMsgTypes.REQ_LOC:
			this.w.setMsg_wcmsgid(this.newmsg.get("MsgId").toString());
			this.recordLocMsg();
			break;
		case WeChatMsgTypes.REQ_VIDEO:
			this.w.setMsg_wcmsgid(this.newmsg.get("MsgId").toString());
			this.recordVideoMsg();
			break;
		case WeChatMsgTypes.REQ_VOICE:
			this.w.setMsg_wcmsgid(this.newmsg.get("MsgId").toString());
			this.recordVoiceMsg();
			break;
		case WeChatMsgTypes.REQ_EVENT:
			this.recordEventMsg();
			break;
		default:
		}
		
		this.msgrec_session.save(this.w);
	    this.tx.commit();
	    HibernateUtil.closeSession();

	}

	private void recordEventMsg() {
		int event_type = (int)this.newmsg.get("Event");
		this.w.setEvent_type(event_type);
		switch(event_type){
//		case WeChatEventTypes.EVENT_SUB:
//			this.recordSubscribe();
//			break;
//		case WeChatEventTypes.EVENT_UNSUB:
//			this.recordUnsubscribe();
//			break;
		case WeChatEventTypes.EVENT_LOC:
			this.recordLocEvent();
			break;
		default:
		}
		
	}

	private void recordLocEvent() {
		this.w.setMsg_location_x(this.newmsg.get("Latitude").toString());
		this.w.setMsg_location_y(this.newmsg.get("Longitude").toString());
		this.w.setMsg_precision(this.newmsg.get("Precision").toString());
	}

//	private void recordUnsubscribe() {
		
//	}

//	private void recordSubscribe() {
		
//	}

	private void recordVoiceMsg() {
		LobHelper l = this.msgrec_session.getLobHelper();
		
		this.w.setMsg_mediaid(this.newmsg.get("MediaId").toString());
		this.w.setMsg_format(this.newmsg.get("Format").toString());
		try {
			this.w.setMsg_recognition(
					l.createBlob(this.newmsg.get("Recognition").toString().getBytes(this.EncodingCharset)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void recordVideoMsg() {
		this.w.setMsg_mediaid(this.newmsg.get("MediaId").toString());
		this.w.setMsg_thumbmediaid(this.newmsg.get("ThumbMediaId").toString());
	}

	private void recordLocMsg() {
		this.w.setMsg_location_x(this.newmsg.get("Location_X").toString());
		this.w.setMsg_location_y(this.newmsg.get("Location_Y").toString());
		this.w.setMsg_label(this.newmsg.get("Label").toString());
		this.w.setMsg_scale(this.newmsg.get("Scale").toString());
	}

	private void recordLinkMsg() {
		LobHelper l = this.msgrec_session.getLobHelper();
		
		this.w.setMsg_title(this.newmsg.get("Title").toString());
		this.w.setMsg_url(this.newmsg.get("Url").toString());
		try {
			this.w.setMsg_description(
					l.createBlob(StringUtil.fullToHalf(
							this.newmsg.get("Description").toString()).getBytes(this.EncodingCharset)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void recordImgMsg() {
		this.w.setMsg_picurl(this.newmsg.get("PicUrl").toString());
	}

	private void recordTextMsg() {
	    LobHelper l = this.msgrec_session.getLobHelper();
	   
	    try {
			this.w.setMsg_content(
					l.createBlob(StringUtil.fullToHalf(
							this.newmsg.get("Content").toString()).getBytes(EncodingCharset)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getEncodingCharset() {
		return EncodingCharset;
	}

	public void setEncodingCharset(String encodingCharset) {
		EncodingCharset = encodingCharset;
	}

}
