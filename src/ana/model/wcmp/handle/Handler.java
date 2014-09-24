package ana.model.wcmp.handle;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import ana.model.wcmp.properties.WeChatEventTypes;
import ana.model.wcmp.properties.WeChatMsgTypes;
import ana.model.wcmp.recorder.MsgRecorder;
import ana.model.wcmp.rules.EventGenericProcessor;
import ana.model.wcmp.rules.MsgGenericProcessor;
import ana.util.converter.XMLparser;
import ana.view.wcmp.msgpack.MsgPacker;

public class Handler {
	
	@SuppressWarnings("rawtypes")
	public String processReq(HttpServletRequest request){
		//将要返回的xml消息
		String respXml = null;
		
		HashMap reqMsg = new HashMap();
		HashMap respMsg = new HashMap();
		
		try {
			reqMsg = XMLparser.parseXml(request);
			new WeChatMsgTypes().formatType(reqMsg);
			
			//增加聊天记录
//			Wechat_msgrecSVC msgsvc = new Wechat_msgrecSVC(reqMsg);
//			msgsvc.sortAndSave();
			
			if((int)reqMsg.get("MsgType")==WeChatMsgTypes.REQ_EVENT) {	
				new WeChatEventTypes().formatType(reqMsg);
				EventGenericProcessor eventprocessor = new EventGenericProcessor(reqMsg);
				eventprocessor.process();
				respMsg = eventprocessor.getRespmsg();
			} else {
				MsgRecorder mrec = new MsgRecorder(reqMsg);
				mrec.start();
				MsgGenericProcessor msgprocessor = new MsgGenericProcessor(reqMsg);
				msgprocessor.process();
				respMsg = msgprocessor.getRespmsg();
			}
			
			MsgPacker mpack = new MsgPacker(respMsg);
			respXml = mpack.packXMLMsg();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return respXml;
	}

}
