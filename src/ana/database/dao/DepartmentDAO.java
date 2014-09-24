package ana.database.dao;

import java.sql.Blob;

public class DepartmentDAO {
	
	private long department_id;
	private String department_name;
	private Blob department_desc;
	
	public long getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(long department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public Blob getDepartment_desc() {
		return department_desc;
	}
	public void setDepartment_desc(Blob department_desc) {
		this.department_desc = department_desc;
	}
	
}
