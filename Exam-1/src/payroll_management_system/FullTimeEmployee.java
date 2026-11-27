package payroll_management_system;

public class FullTimeEmployee extends Employee {

    private double baseSalary;
    private double bonus;

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    FullTimeEmployee(int id, String name, String department, double baseSalary, double bonus) {
        super(id, name, department);
        setBaseSalary(baseSalary);
        setBaseSalary(bonus);
    }

    @Override
    double calculateSalary() {
        return baseSalary + bonus;
    }
}
