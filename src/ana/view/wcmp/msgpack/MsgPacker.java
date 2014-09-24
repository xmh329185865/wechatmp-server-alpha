package ana.view.wcmp.msgpack;

import java.util.HashMap;
import java.util.List;

import ana.model.wcmp.persistent.respmsg.*;
import ana.model.wcmp.properties.WeChatMsgTypes;
import ana.util.converter.XMLparser;
import ana.view.wcmp.msgpool.MsgPool;

@SuppressWarnings({"rawtypes","unchecked"})
public class MsgPacker {
	
	private HashMap respMsg = new HashMap();
	
	public MsgPacker(HashMap respMsg){
		this.respMsg = respMsg;
	}

	public String packXMLMsg(){
		if(this.respMsg!=null){
			String respXML = null;
			XMLparser x = new XMLparser();
			int respType = (int)this.respMsg.get("MsgType");
			switch(respType){
			case WeChatMsgTypes.RESP_TEXT:
				this.respMsg.put("MsgType", "text");
				
				TextMsg t = new TextMsg();
				t.setFromUserName(this.respMsg.get("FromUserName").toString());
				t.setToUserName(this.respMsg.get("ToUserName").toString());
				t.setCreateTime((long)this.respMsg.get("CreateTime"));
				t.setMsgType(this.respMsg.get("MsgType").toString());
				t.setContent(this.respMsg.get("Content").toString());
				
				respXML = x.msgToXml(t);
				break;
			case WeChatMsgTypes.RESP_NEWS:
				this.respMsg.put("MsgType", "news");
				
				NewsMsg n = new NewsMsg();
				n.setFromUserName(this.respMsg.get("FromUserName").toString());
				n.setToUserName(this.respMsg.get("ToUserName").toString());
				n.setCreateTime((long)this.respMsg.get("CreateTime"));
				n.setMsgType(this.respMsg.get("MsgType").toString());
				n.setArticles((List<Article>) this.respMsg.get("Articles"));
				n.setArticleCount((int) this.respMsg.get("ArticleCount"));
				
				respXML = x.msgToXml(n);
				break;
			case WeChatMsgTypes.RESP_IMG:
				this.respMsg.put("MsgType", "image");
				
				ImgMsg i = new ImgMsg();
				i.setFromUserName(this.respMsg.get("FromUserName").toString());
				i.setToUserName(this.respMsg.get("ToUserName").toString());
				i.setCreateTime((long)this.respMsg.get("CreateTime"));
				i.setMsgType(this.respMsg.get("MsgType").toString());
				i.setImage((Image) this.respMsg.get("Image"));
				
				respXML = x.msgToXml(i);
				break;
			case WeChatMsgTypes.RESP_MUSIC:
				this.respMsg.put("MsgType", "music");
				
				MusicMsg m = new MusicMsg();
				m.setFromUserName(this.respMsg.get("FromUserName").toString());
				m.setToUserName(this.respMsg.get("ToUserName").toString());
				m.setCreateTime((long)this.respMsg.get("CreateTime"));
				m.setMsgType(this.respMsg.get("MsgType").toString());
				m.setMusic((Music) this.respMsg.get("Music"));
				
				respXML = x.msgToXml(m);
				break;
			case WeChatMsgTypes.RESP_VIDEO:
				this.respMsg.put("MsgType", "video");
				
				VideoMsg vi = new VideoMsg();
				vi.setFromUserName(this.respMsg.get("FromUserName").toString());
				vi.setToUserName(this.respMsg.get("ToUserName").toString());
				vi.setCreateTime((long)this.respMsg.get("CreateTime"));
				vi.setMsgType(this.respMsg.get("MsgType").toString());
				vi.setVideo((Video) this.respMsg.get("Video"));
				
				respXML = x.msgToXml(vi);
				break;
			case WeChatMsgTypes.RESP_VOICE:
				this.respMsg.put("MsgType", "voice");
				
				VoiceMsg vo = new VoiceMsg();
				vo.setFromUserName(this.respMsg.get("FromUserName").toString());
				vo.setToUserName(this.respMsg.get("ToUserName").toString());
				vo.setCreateTime((long)this.respMsg.get("CreateTime"));
				vo.setMsgType(this.respMsg.get("MsgType").toString());
				vo.setVoice((Voice) this.respMsg.get("Voice"));
				
				respXML = x.msgToXml(vo);
				break;
			default:
				this.respMsg.put("MsgType", "text");
				
				TextMsg t1 = new TextMsg();
				t1.setFromUserName(this.respMsg.get("FromUserName").toString());
				t1.setToUserName(this.respMsg.get("ToUserName").toString());
				t1.setCreateTime((long)this.respMsg.get("CreateTime"));
				t1.setContent(MsgPool.INNER_ERR);
				t1.setMsgType(this.respMsg.get("MsgType").toString());
				
				respXML = x.msgToXml(t1);
				System.out.println("Inner Resp Type Err!");
			}
			
			return respXML;
		} else return null;
	}
}
