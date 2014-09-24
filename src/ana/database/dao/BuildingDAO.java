package ana.database.dao;

public class BuildingDAO {
	
	private long building_id;
	private String building_name;
	private String building_campus;
	private String building_loc;
	
	public long getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(long building_id) {
		this.building_id = building_id;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getBuilding_campus() {
		return building_campus;
	}
	public void setBuilding_campus(String building_campus) {
		this.building_campus = building_campus;
	}
	public String getBuilding_loc() {
		return building_loc;
	}
	public void setBuilding_loc(String building_loc) {
		this.building_loc = building_loc;
	}

}
