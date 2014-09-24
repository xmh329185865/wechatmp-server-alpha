package ana.database.dao;

import java.sql.Blob;

public class GroupRoleDAO {
	
	private long group_role_id;
	private String group_role_name;
	private Blob group_role_desc;
	private Integer group_role_auth;
	
	public long getGroup_role_id() {
		return group_role_id;
	}
	public void setGroup_role_id(long group_role_id) {
		this.group_role_id = group_role_id;
	}
	public String getGroup_role_name() {
		return group_role_name;
	}
	public void setGroup_role_name(String group_role_name) {
		this.group_role_name = group_role_name;
	}
	public Blob getGroup_role_desc() {
		return group_role_desc;
	}
	public void setGroup_role_desc(Blob group_role_desc) {
		this.group_role_desc = group_role_desc;
	}
	public Integer getGroup_role_auth() {
		return group_role_auth;
	}
	public void setGroup_role_auth(Integer group_role_auth) {
		this.group_role_auth = group_role_auth;
	}

}
