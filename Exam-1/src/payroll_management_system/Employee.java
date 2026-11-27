package payroll_management_system;

public abstract class Employee {
    private int id;
    private String name;
    private String department;

    Employee(int id, String name, String department) {
        setId(id);
        setName(name);
        setDepartment(department);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    abstract double calculateSalary();
}
