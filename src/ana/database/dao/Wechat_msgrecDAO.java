package ana.database.dao;

import java.sql.Blob;
import java.sql.Timestamp;

public class Wechat_msgrecDAO {
	
	private long msg_id;
	private String msg_wcmsgid;
	private Timestamp msg_timestamp;
	private Blob msg_content;
	private String msg_fromuser;
	private Integer msg_type;
	private String msg_touser;
	private String msg_picurl;
	private String msg_mediaid;
	private String msg_format;
	private Blob msg_recognition;
	private String msg_thumbmediaid;
	private String msg_location_x;
	private String msg_location_y;
	private String msg_scale;
	private String msg_label;
	private String msg_title;
	private Blob msg_description;
	private String msg_url;
	private Integer event_type;
	private String msg_precision;
	
	public long getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(long msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg_wcmsgid() {
		return msg_wcmsgid;
	}
	public void setMsg_wcmsgid(String msg_wcmsgid) {
		this.msg_wcmsgid = msg_wcmsgid;
	}
	public Timestamp getMsg_timestamp() {
		return msg_timestamp;
	}
	public void setMsg_timestamp(Timestamp msg_timestamp) {
		this.msg_timestamp = msg_timestamp;
	}
	public Blob getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(Blob msg_content) {
		this.msg_content = msg_content;
	}
	public String getMsg_fromuser() {
		return msg_fromuser;
	}
	public void setMsg_fromuser(String msg_fromuser) {
		this.msg_fromuser = msg_fromuser;
	}
	public Integer getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(Integer msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_touser() {
		return msg_touser;
	}
	public void setMsg_touser(String msg_touser) {
		this.msg_touser = msg_touser;
	}
	public String getMsg_picurl() {
		return msg_picurl;
	}
	public void setMsg_picurl(String msg_picurl) {
		this.msg_picurl = msg_picurl;
	}
	public String getMsg_mediaid() {
		return msg_mediaid;
	}
	public void setMsg_mediaid(String msg_mediaid) {
		this.msg_mediaid = msg_mediaid;
	}
	public String getMsg_format() {
		return msg_format;
	}
	public void setMsg_format(String msg_format) {
		this.msg_format = msg_format;
	}
	public Blob getMsg_recognition() {
		return msg_recognition;
	}
	public void setMsg_recognition(Blob msg_recognition) {
		this.msg_recognition = msg_recognition;
	}
	public String getMsg_thumbmediaid() {
		return msg_thumbmediaid;
	}
	public void setMsg_thumbmediaid(String msg_thumbmediaid) {
		this.msg_thumbmediaid = msg_thumbmediaid;
	}
	public String getMsg_location_x() {
		return msg_location_x;
	}
	public void setMsg_location_x(String msg_location_x) {
		this.msg_location_x = msg_location_x;
	}
	public String getMsg_location_y() {
		return msg_location_y;
	}
	public void setMsg_location_y(String msg_location_y) {
		this.msg_location_y = msg_location_y;
	}
	public String getMsg_scale() {
		return msg_scale;
	}
	public void setMsg_scale(String msg_scale) {
		this.msg_scale = msg_scale;
	}
	public String getMsg_label() {
		return msg_label;
	}
	public void setMsg_label(String msg_label) {
		this.msg_label = msg_label;
	}
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	public Blob getMsg_description() {
		return msg_description;
	}
	public void setMsg_description(Blob msg_description) {
		this.msg_description = msg_description;
	}
	public String getMsg_url() {
		return msg_url;
	}
	public void setMsg_url(String msg_url) {
		this.msg_url = msg_url;
	}
	public Integer getEvent_type() {
		return event_type;
	}
	public void setEvent_type(Integer event_type) {
		this.event_type = event_type;
	}
	public String getMsg_precision() {
		return msg_precision;
	}
	public void setMsg_precision(String msg_precision) {
		this.msg_precision = msg_precision;
	}

}
