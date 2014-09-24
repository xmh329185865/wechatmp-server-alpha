package ana.model.wcmp.persistent.reqmsg;

/**
 * 图片位置消息提取，包含信息：
 * 图片地址：PicUrl
 * 
 * @author Godrick
 *	Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
public class PicMsg extends CommonInfoMsg{

	private String PicUrl;
	
	public String getPicUrl(){
		return PicUrl;
	}

	public void setPicUrl(String newpicurl){
		PicUrl = newpicurl;
	}
}
