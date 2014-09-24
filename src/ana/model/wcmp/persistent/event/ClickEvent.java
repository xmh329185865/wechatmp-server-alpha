package ana.model.wcmp.persistent.event;

/**
 * ����Զ���˵��¼���ȡ��������Ϣ��
 * ����Ĳ˵�����Զ���˵������и��˵����KEYֵ��Ӧ����EventKey
 * 
 * @author Godrick
 *	Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
public class ClickEvent extends CommonInfoEvent{
	
	private String EventKey;

	public String getEventKey(){
		return EventKey;
	}
	
	public void setEventKey(String neweventkey){
		EventKey = neweventkey;
	}
}
