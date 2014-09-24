package ana.model.wcmp.persistent.event;

/**
 * 上传地理位置事件提取，包含信息：
 * 纬度：Latitude	经度：Longitude	地理位置精度：Precision
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
