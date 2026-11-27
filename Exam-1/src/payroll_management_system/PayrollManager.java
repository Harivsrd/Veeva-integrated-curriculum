package payroll_management_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayrollManager {

    private List<Employee> employees;

    public PayrollManager() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public Map<String, Double> getDepartmentWiseTotalSalary() {

        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee -> Employee.getDepartment(),
                        Collectors.summingDouble(Employee -> Employee.calculateSalary())
                ));
    }

    // Employees whose salary > threshold
    public List<String> getTopEarners(double threshold) {

        return employees.stream()
                .filter(emp -> emp.calculateSalary() > threshold)
                .map(Employee::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        PayrollManager manager = new PayrollManager();

        manager.addEmployee(new FullTimeEmployee(101, "Alice", "IT", 60000, 10000));

        manager.addEmployee(new ContractEmployee(102, "Bob", "IT", 500, 100));

        manager.addEmployee(new FullTimeEmployee(103, "Charlie", "HR", 55000, 5000));

        manager.addEmployee(new ContractEmployee(104, "David", "Finance", 600, 120));

        System.out.println("Department Wise Salary:");

        manager.getDepartmentWiseTotalSalary()
                .forEach((dept, totalSalary) -> System.out.println(dept + " -> " + totalSalary));

        double threshold = 65000;
        System.out.println("\nTop Earners "+threshold+" :");

        List<String> topEarners =
                manager.getTopEarners(threshold);

        topEarners.forEach(System.out::println);
    }
}