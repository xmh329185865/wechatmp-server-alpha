package ana.model.wcmp.persistent.reqmsg;

/**
 * ͼƬλ����Ϣ��ȡ��������Ϣ��
 * ͼƬ��ַ��PicUrl
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
