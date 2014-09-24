package ana.model.wcmp.persistent.event;

/**
 * 事件中共有信息（基类）的封装，共有信息含：
 * 开发者微信号：ToUserName   发送方账号：FromUserName	消息创建时间（整型）：CreateTime
 * 消息类型：MsgType（event）	事件类型(关注/取消关注/扫码/上传地理位置/点击自定义菜单)：Event（subscribe/unsubscribe/scan/LOCATION/CLICK）
 * 
 * @author Godrick
 *	Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
public class CommonInfoEvent {
	
	private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	private String Event;
	
	public String getFromUserName(){
		return FromUserName;
	}

	public String getToUserName(){
		return ToUserName;
	}
	
	public long getCreateTime(){
		return CreateTime;
	}
	
	public String getMsgType(){
		return MsgType;
	}
	
	public String getEvent(){
		return Event;
	}
	
	public void setFromUserName(String newfromusername){
		FromUserName = newfromusername;
	}

	public void setToUserName(String newtousername){
		ToUserName = newtousername;
	}
	
	public void setCreateTime(long newcreatetime){
		CreateTime = newcreatetime;
	}
	
	public void setMsgType(String newmsgtype){
		MsgType = newmsgtype;
	}
	
	public void setEvent(String newevent){
		Event = newevent;
	}
}
