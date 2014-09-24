package ana.model.wcmp.persistent.event;

/**
 * ɨ���¼���ȡ��������Ϣ��
 * ��ά�����ݣ�EventKey	��ά��ͼƬ��ȡƾ֤��Ticket
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
