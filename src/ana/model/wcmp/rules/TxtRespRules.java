package ana.model.wcmp.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ana.model.order.mgr.WCOrderMgr;
import ana.model.wcmp.persistent.respmsg.Article;
import ana.model.wcmp.properties.WeChatMsgTypes;
import ana.server.properties.ServerProperties;
import ana.util.string.StringUtil;
import ana.view.wcmp.msgpool.MsgPool;

/**
 * �ı���Ϣ����Ӧ���򣨺�������Ӧ��Ϣ���ͣ�
 * 
 * @author Godrick
 * Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class TxtRespRules {
	
	private String ReqContent = "";
	private HashMap txtmsg;
	
	public TxtRespRules(HashMap txtmsg){
		this.txtmsg = txtmsg;
		this.ReqContent = StringUtil.fullToHalf(txtmsg.get("Content").toString()).trim();
	}
	
	public void fillReturnMsg(HashMap respmsg){
		
	    if (this.ReqContent.equals("��Ҫ����")) {
			
			respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
			respmsg.put("Content", MsgPool.DECLARE_FAULT.replaceFirst(":VOPENID", this.txtmsg.get("FromUserName").toString()));
			return;
					
		} else if (this.ReqContent.startsWith("��Ҫ����")) {
			
			respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
			respmsg.put("Content", MsgPool.HOW_TO_RATE);
			return;
			
		} else if (this.ReqContent.startsWith("ƾ֤��ȡ#")) {
			try {
				
				long orderid = Long.valueOf(this.ReqContent.split("#")[1]);
				WCOrderMgr wcou = new WCOrderMgr();
				wcou.getCercode(orderid,txtmsg.get("FromUserName").toString());
				
				respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
				respmsg.put("Content", wcou.getBackString());
				return;

			} catch (ArrayIndexOutOfBoundsException e11) {
				
				e11.printStackTrace();
				respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
				respmsg.put("Content", MsgPool.ERR_CONTENT);
				return;
				
			}			
					
		} else if (this.ReqContent.startsWith("ά������#")) {
			
			respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
			List rate = StringUtil.tokenizedStringToList(StringUtil.tokenInitializer(this.ReqContent, "#")
					, "#");
			rate.remove(0);
			if (rate.size()!=3) {
				respmsg.put("Content", MsgPool.ERR_UNMATCHED_CONTENT);
				return;
			}
			long orderid;
			int ratelevel;
			try{
				orderid = Long.parseLong(rate.get(0).toString());
				ratelevel = Integer.parseInt(rate.get(2).toString());
			}catch(NumberFormatException e){
				respmsg.put("Content", MsgPool.ERR_CONTENT);
				return;
			}
			if (ratelevel > 5 || ratelevel < 1 ) {
				respmsg.put("Content", MsgPool.ERR_INVALID_RATELEVEL);
				return;
			}
			
			WCOrderMgr wcou = new WCOrderMgr();
			wcou.rateOrder(orderid, rate.get(1).toString(), this.txtmsg.get("FromUserName").toString(), ratelevel);
			respmsg.put("Content", wcou.getBackString());
			return;
			
		} else if (this.ReqContent.equals("��Ҫ�鵥")) {
			
			respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
			respmsg.put("Content", MsgPool.TITLE_SEARCH_ORDER);
			return;
			
		} else if (this.ReqContent.startsWith("���ϵ���ѯ#")) {
			
			try {
				respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
				long orderid = Long.valueOf(this.ReqContent.split("#")[1]);
				WCOrderMgr wcou = new WCOrderMgr();
				wcou.getOrderByID(orderid, this.txtmsg.get("FromUserName").toString());
				respmsg.put("Content", wcou.getBackString());
				return;

			} catch (ArrayIndexOutOfBoundsException e11) {
				respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
				respmsg.put("Content", MsgPool.ERR_UNMATCHED_CONTENT);
				return;
			} catch (NumberFormatException e){
				respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
				respmsg.put("Content", MsgPool.ERR_CONTENT);
				return;
			}
	
		} else if (this.ReqContent.equals("����")){
			
			respmsg.put("MsgType", WeChatMsgTypes.RESP_NEWS);
			List<Article> s = new ArrayList<Article>();
			
			for (int i = 0; i < 6; i++) {
				Article temp = new Article();
				//temp.setPicUrl("");
				//temp.setUrl("");
				//temp.setDescription("");
				s.add(temp);
			}
			
			s.get(0).setPicUrl("http://"+ServerProperties.webrootpath+"/wcmp-materials/welcome_title.png");
			
			s.get(0).setTitle(MsgPool.TITLE_HELP_WELCOME);
			s.get(1).setTitle(MsgPool.TITLE_DECLARE_FAULT);
			s.get(2).setTitle(MsgPool.TITLE_ACCEPT_RANGE);
			s.get(3).setTitle(MsgPool.TITLE_SEARCH_ORDER);
			s.get(4).setTitle(MsgPool.TITLE_HOW_TO_RATE);
			s.get(5).setTitle(MsgPool.TITLE_GET_CERTIFACATION);	
			
			respmsg.put("Articles", s);
			respmsg.put("ArticleCount", s.size());
			return;
			
		} else if(this.ReqContent.equals("����Χ")) {
			
			respmsg.put("MsgType", WeChatMsgTypes.RESP_NEWS);
			List<Article> s = new ArrayList<Article>();
			
			for (int i = 0; i < MsgPool.SERVICE_RANGE.length; i++) {
				Article temp = new Article();
				//temp.setPicUrl("");
				//temp.setUrl("");
				//temp.setDescription("");
				temp.setTitle(MsgPool.SERVICE_RANGE[i]);
				s.add(temp);
			}
			
			respmsg.put("Articles", s);
			respmsg.put("ArticleCount", s.size());
			return;
			
		} else if(this.ReqContent.startsWith("ȡ������#")){
			try {
				respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
				long orderid = Long.valueOf(this.ReqContent.split("#")[1]);
				WCOrderMgr wcou = new WCOrderMgr();
				wcou.deleteOrder(orderid, this.txtmsg.get("FromUserName").toString());
				respmsg.put("Content", wcou.getBackString());
				return;

			} catch (ArrayIndexOutOfBoundsException e11) {
				respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
				respmsg.put("Content", MsgPool.ERR_UNMATCHED_CONTENT);
				return;
			} catch (NumberFormatException e){
				respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
				respmsg.put("Content", MsgPool.ERR_CONTENT);
				return;
			}
			
		} else {
			
			respmsg.put("MsgType", WeChatMsgTypes.RESP_TEXT);
			respmsg.put("Content", MsgPool.ERR_UNKNOWN_CONTENT);
			return;
			
		}
		
		//TODO �жϹ���
	    
	}

	public String getReqContent() {
		return ReqContent;
	}

	public void setReqContent(String reqContent) {
		ReqContent = reqContent;
	}
	
}
