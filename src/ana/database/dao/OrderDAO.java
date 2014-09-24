package ana.database.dao;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Calendar;

public class OrderDAO {
	
	private long order_id;
	private String client_name;
	private String client_cell;
	private String client_building;
	private String client_room;
	private Timestamp order_timestamp;
	private Calendar order_resvtime;
	private Blob order_description;
	private Integer order_status;
	private String order_supadmin;
	private String order_nowadmin;
	private Blob order_diagnosis;
	private String order_wechatid;
	private Blob order_ratedesc;
	private Blob order_result;
	private String order_cercode;
	private Blob order_solveway;
	private Integer order_ratelevel;
	private String order_faultip;
	private Timestamp order_fixedtime;
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_cell() {
		return client_cell;
	}
	public void setClient_cell(String client_cell) {
		this.client_cell = client_cell;
	}
	public String getClient_building() {
		return client_building;
	}
	public void setClient_building(String client_building) {
		this.client_building = client_building;
	}
	public String getClient_room() {
		return client_room;
	}
	public void setClient_room(String client_room) {
		this.client_room = client_room;
	}
	public Timestamp getOrder_timestamp() {
		return order_timestamp;
	}
	public void setOrder_timestamp(Timestamp order_timestamp) {
		this.order_timestamp = order_timestamp;
	}
	public Calendar getOrder_resvtime() {
		return order_resvtime;
	}
	public void setOrder_resvtime(Calendar order_resvtime) {
		this.order_resvtime = order_resvtime;
	}
	public Blob getOrder_description() {
		return order_description;
	}
	public void setOrder_description(Blob order_description) {
		this.order_description = order_description;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public String getOrder_supadmin() {
		return order_supadmin;
	}
	public void setOrder_supadmin(String order_supadmin) {
		this.order_supadmin = order_supadmin;
	}
	public String getOrder_nowadmin() {
		return order_nowadmin;
	}
	public void setOrder_nowadmin(String order_nowadmin) {
		this.order_nowadmin = order_nowadmin;
	}
	public Blob getOrder_diagnosis() {
		return order_diagnosis;
	}
	public void setOrder_diagnosis(Blob order_diagnosis) {
		this.order_diagnosis = order_diagnosis;
	}
	public String getOrder_wechatid() {
		return order_wechatid;
	}
	public void setOrder_wechatid(String order_wechatid) {
		this.order_wechatid = order_wechatid;
	}
	public Blob getOrder_ratedesc() {
		return order_ratedesc;
	}
	public void setOrder_ratedesc(Blob order_ratedesc) {
		this.order_ratedesc = order_ratedesc;
	}
	public Blob getOrder_result() {
		return order_result;
	}
	public void setOrder_result(Blob order_result) {
		this.order_result = order_result;
	}
	public String getOrder_cercode() {
		return order_cercode;
	}
	public void setOrder_cercode(String order_cercode) {
		this.order_cercode = order_cercode;
	}
	public Blob getOrder_solveway() {
		return order_solveway;
	}
	public void setOrder_solveway(Blob order_solveway) {
		this.order_solveway = order_solveway;
	}
	public Integer getOrder_ratelevel() {
		return order_ratelevel;
	}
	public void setOrder_ratelevel(Integer order_ratelevel) {
		this.order_ratelevel = order_ratelevel;
	}
	public String getOrder_faultip() {
		return order_faultip;
	}
	public void setOrder_faultip(String order_faultip) {
		this.order_faultip = order_faultip;
	}
	public Timestamp getOrder_fixedtime() {
		return order_fixedtime;
	}
	public void setOrder_fixedtime(Timestamp order_fixedtime) {
		this.order_fixedtime = order_fixedtime;
	}

}
