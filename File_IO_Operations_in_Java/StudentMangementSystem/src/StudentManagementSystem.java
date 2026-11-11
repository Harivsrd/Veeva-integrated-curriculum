import java.util.Scanner;
public class StudentManagementSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentManager manager = new StudentManager();

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by Roll Number");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Delete Student");
            System.out.println("6. Update Student");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent(sc, manager);
                    break;

                case 2:
                    manager.displayAllStudents();
                    break;

                case 3:
                    System.out.print("Enter Roll Number: ");
                    String rollNo = sc.nextLine();

                    manager.searchByRollNo(rollNo);
                    break;

                case 4:
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    manager.searchByName(name);
                    break;

                case 5:
                    System.out.print("Enter Roll Number to delete: ");
                    rollNo = sc.nextLine();

                    manager.deleteStudent(rollNo);
                    break;

                case 6:
                    System.out.print("Enter Roll Number to update: ");
                    rollNo = sc.nextLine();

                    updateStudent(sc, manager, rollNo);
                    break;

                case 7:
                    System.out.println("Thank you for using Student Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);

        sc.close();
    }

    public static void addStudent(Scanner sc, StudentManager manager) {

        System.out.print("Enter Roll Number: ");
        String rollNo = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        int[] marks = new int[5];

        System.out.println("Enter marks for 5 subjects:");

        for (int i = 0; i < marks.length; i++) {

            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        Student student = new Student(rollNo, name, age, marks, grade);

        manager.addStudent(student);
    }

    public static void updateStudent(Scanner sc, StudentManager manager, String rollNo) {

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();

        int[] marks = new int[5];

        System.out.println("Enter new marks for 5 subjects:");

        for (int i = 0; i < marks.length; i++) {

            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        sc.nextLine();

        System.out.print("Enter New Grade: ");
        String grade = sc.nextLine();

        manager.updateStudent(rollNo, name, age, marks, grade);
    }
}