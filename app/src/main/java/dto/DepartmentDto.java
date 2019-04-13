package dto;

public class DepartmentDto {

    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "departmentName='" + departmentName + '\'' +
                '}';
    }
}
