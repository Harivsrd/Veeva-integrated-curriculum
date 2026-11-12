import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeStreamOperations {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 28, "IT"),
                new Employee("Bob", 34, "HR"),
                new Employee("Charlie", 25, "IT"),
                new Employee("David", 40, "Finance"),
                new Employee("Eva", 25, "HR")
        );

        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept));

        employeesByDept.forEach((dept, empList) -> {
            System.out.println(dept + " -> " + empList);
        });

        Map<Integer, List<Employee>> employeesByAge = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge));

        employeesByAge.forEach((age, empList) -> {
            System.out.println(age + " years -> " + empList);
        });

        System.out.println("\n--- Employee with Least Age ---");
        employees.stream()
                .min(Comparator.comparingInt(Employee::getAge))
                .ifPresentOrElse(
                        emp -> System.out.println("Youngest Employee: " + emp),
                        () -> System.out.println("No employees found.")
                );
    }
}