package ana.model.wcmp.persistent.event;

/**
 * 点击自定义菜单事件提取，包含信息：
 * 点击的菜单项（与自定义菜单定义中各菜单项的KEY值对应）：EventKey
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
