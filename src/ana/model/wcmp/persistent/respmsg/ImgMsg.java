package ana.model.wcmp.persistent.respmsg;

/**
 * ͼƬ��Ϣ�࣬����һ��ͼƬ�زģ���ͼƬ�������ʵ������
 * 
 * @author Godrick
 *	Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
public class ImgMsg extends CommonInfoResp{
	
	private Image Image;
	
	public Image getImage(){
		return Image;
	}
	
	public void setImage(Image newimage){
		Image = newimage;
	}

}
