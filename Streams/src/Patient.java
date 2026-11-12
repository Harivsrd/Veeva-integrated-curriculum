import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Patient {
    int patientId;
    String patientName;
    String dept;
    String doctorName;
    String diagnosis;
    String treatment;
    String patientType;
    int heartRate;
    int bp;
    int sugarValue;

    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();

    }

    public static class StreamPracticalExamples {
        public static void main(String[] args) {
            List<String> toolsAndLanguages = Arrays.asList(
                    "Java", "JavaScript", "Python", "PostgreSQL", "MySQL",
                    "Oracle", "Linux", "Windows", "Docker", "Kubernetes", "Git", "Apache"
            );

            char targetLetter = 'J';
            char targetLower = 'j';

            List<String> task1 = toolsAndLanguages.stream()
                    .filter(s -> s.toLowerCase().startsWith("a") && s.toLowerCase().endsWith("n"))
                    .collect(Collectors.toList());
            System.out.println(task1);

            List<String> task2 = toolsAndLanguages.stream()
                    .filter(s -> s.startsWith(String.valueOf(targetLetter)))
                    .collect(Collectors.toList());
            System.out.println(task2);

            List<String> task3 = toolsAndLanguages.stream()
                    .filter(s -> s.startsWith(String.valueOf(targetLetter)))
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println(task3);

            List<String> task4 = toolsAndLanguages.stream()
                    .filter(s -> s.startsWith(String.valueOf(targetLetter)))
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(task4);

            List<String> task5 = toolsAndLanguages.stream()
                    .filter(s -> s.startsWith(String.valueOf(targetLetter)))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(task5);

            long task6 = toolsAndLanguages.stream()
                    .filter(s -> s.startsWith(String.valueOf(targetLetter)))
                    .distinct()
                    .count();
            System.out.println("Count: " + task6);

            toolsAndLanguages.stream()
                    .filter(s -> s.toLowerCase().startsWith("s"))
                    .findFirst()
                    .ifPresentOrElse(
                            val -> System.out.println("Found: " + val),
                            () -> System.out.println("No element starts with 's'")
                    );

            boolean anyMatch = toolsAndLanguages.stream()
                    .anyMatch(s -> s.toLowerCase().startsWith("s"));
            System.out.println("Any match: " + anyMatch);

            boolean allMatch = toolsAndLanguages.stream()
                    .allMatch(s -> s.toLowerCase().startsWith("s"));
            System.out.println("All match: " + allMatch);
        }
    }
}
