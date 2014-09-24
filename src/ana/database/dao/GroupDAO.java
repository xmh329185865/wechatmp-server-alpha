package ana.database.dao;

import java.sql.Blob;

public class GroupDAO {
	
	private long group_id;
	private String group_name;
	private Blob group_desc;
	private Blob group_domain;
	
	public long getGroup_id() {
		return group_id;
	}
	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public Blob getGroup_desc() {
		return group_desc;
	}
	public void setGroup_desc(Blob group_desc) {
		this.group_desc = group_desc;
	}
	public Blob getGroup_domain() {
		return group_domain;
	}
	public void setGroup_domain(Blob group_domain) {
		this.group_domain = group_domain;
	}

}
