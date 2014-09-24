package ana.database.dao;

import java.sql.Blob;

public class JobDAO {
	
	private long job_id;
	private String job_name;
	private Blob job_desc;
	private Integer job_auth;
	
	public long getJob_id() {
		return job_id;
	}
	public void setJob_id(long job_id) {
		this.job_id = job_id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public Blob getJob_desc() {
		return job_desc;
	}
	public void setJob_desc(Blob job_desc) {
		this.job_desc = job_desc;
	}
	public Integer getJob_auth() {
		return job_auth;
	}
	public void setJob_auth(Integer job_auth) {
		this.job_auth = job_auth;
	}

}
