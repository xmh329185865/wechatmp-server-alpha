package ana.model.wcmp.persistent.event;

/**
 * 扫码事件提取，包含信息：
 * 二维码内容：EventKey	二维码图片获取凭证：Ticket
 * 
 * @author Godrick
 *	Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
public class QRscanEvent extends CommonInfoEvent{
	
	private String EventKey;
	private String Ticket;
	
	public String getEventKey(){
		return EventKey;
	}
	
	public String getTicket(){
		return Ticket;
	}

	public void setEventKey(String neweventkey){
		EventKey = neweventkey;
	}
	
	public void setTicket(String newticket){
		Ticket = newticket;
	}
}
