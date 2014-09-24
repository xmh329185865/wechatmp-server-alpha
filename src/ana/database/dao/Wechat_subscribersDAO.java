package ana.database.dao;

import java.sql.Timestamp;

public class Wechat_subscribersDAO {
	
	private long wcuser_id;
	private String wcuser_openid;
	private Timestamp subscribe_timestamp;
	private Integer wcuser_group_id;
	private String wcuser_wechatacc;
	
	public long getWcuser_id() {
		return wcuser_id;
	}
	public void setWcuser_id(long wcuser_id) {
		this.wcuser_id = wcuser_id;
	}
	public String getWcuser_openid() {
		return wcuser_openid;
	}
	public void setWcuser_openid(String wcuser_openid) {
		this.wcuser_openid = wcuser_openid;
	}
	public Timestamp getSubscribe_timestamp() {
		return subscribe_timestamp;
	}
	public void setSubscribe_timestamp(Timestamp subscribe_timestamp) {
		this.subscribe_timestamp = subscribe_timestamp;
	}
	public Integer getWcuser_group_id() {
		return wcuser_group_id;
	}
	public void setWcuser_group_id(Integer wcuser_group_id) {
		this.wcuser_group_id = wcuser_group_id;
	}
	public String getWcuser_wechatacc() {
		return wcuser_wechatacc;
	}
	public void setWcuser_wechatacc(String wcuser_wechatacc) {
		this.wcuser_wechatacc = wcuser_wechatacc;
	}

}
