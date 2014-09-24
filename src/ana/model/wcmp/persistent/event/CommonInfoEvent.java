package ana.model.wcmp.persistent.event;

/**
 * �¼��й�����Ϣ�����ࣩ�ķ�װ��������Ϣ����
 * ������΢�źţ�ToUserName   ���ͷ��˺ţ�FromUserName	��Ϣ����ʱ�䣨���ͣ���CreateTime
 * ��Ϣ���ͣ�MsgType��event��	�¼�����(��ע/ȡ����ע/ɨ��/�ϴ�����λ��/����Զ���˵�)��Event��subscribe/unsubscribe/scan/LOCATION/CLICK��
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
