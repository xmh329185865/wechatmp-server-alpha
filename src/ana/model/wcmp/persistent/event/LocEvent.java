package ana.model.wcmp.persistent.event;

/**
 * �ϴ�����λ���¼���ȡ��������Ϣ��
 * γ�ȣ�Latitude	���ȣ�Longitude	����λ�þ��ȣ�Precision
 * 
 * @author Godrick
 *	Referring to <WeChat Public Platform Application Developing> (by Liufeng) 
 */
public class LocEvent extends CommonInfoEvent{
	
	private String Latitude;
	private String Longitude;
	private String Precision;
	
	public String getLatitude(){
		return Latitude;		
	}
	
	public String getLongitude(){
		return Longitude;	
	}

	public String getPrecision(){
		return Precision;
	}

	public void setLatitude(String newlat){
		Latitude = newlat;
	}

	public void setLongitude(String newlon){
		Longitude = newlon;
	}
	
	public void setPrecision(String newpre){
		Precision = newpre;
	}
}
