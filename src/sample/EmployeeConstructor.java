package sample;

public class EmployeeConstructor {
	private int EmpID;
	private String EmpFirst;
	private String EmpLast;
	private int EmpPhone;
	private String EmpLocation;
	private String Role;
	private String Day;
	private String StartTime;
	private String EndTime;

	public EmployeeConstructor(int EmpID, String EmpFirst, String EmpLast, int EmpPhone, String EmpLocation,
			String Role, String Day, String StartTime, String EndTime) {
		this.EmpID = EmpID;
		this.EmpFirst = EmpFirst;
		this.EmpLast = EmpLast;
		this.EmpPhone = EmpPhone;
		this.EmpLocation = EmpLocation;
		this.Role = Role;
		this.Day = Day;
		this.StartTime = StartTime;
		this.EndTime = EndTime;
	}

	public int getEmpID() {
		return EmpID;
	}

	public void setEmpID(int empID) {
		EmpID = empID;
	}

	public String getEmpFirst() {
		return EmpFirst;
	}

	public void setEmpFirst(String empFirst) {
		EmpFirst = empFirst;
	}

	public String getEmpLast() {
		return EmpLast;
	}

	public void setEmpLast(String empLast) {
		EmpLast = empLast;
	}

	public int getEmpPhone() {
		return EmpPhone;
	}

	public void setEmpPhone(int empPhone) {
		EmpPhone = empPhone;
	}

	public String getEmpLocation() {
		return EmpLocation;
	}

	public void setEmpLocation(String empLocation) {
		EmpLocation = empLocation;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
}
