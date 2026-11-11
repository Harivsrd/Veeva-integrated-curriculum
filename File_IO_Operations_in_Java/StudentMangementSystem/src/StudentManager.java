import java.io.*;
import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManager() {
        students = loadStudents();
    }

    public ArrayList<Student> loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Student> studentList =
                    (ArrayList<Student>) ois.readObject();

            ois.close();
            fis.close();

            return studentList;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading student records.");

            return new ArrayList<>();
        }
    }

    public void saveStudents() {

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(students);

            oos.close();
            fos.close();

        } catch (IOException e) {

            System.out.println("Error saving student records.");
        }
    }

    public void addStudent(Student student) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getRollNo().equalsIgnoreCase(student.getRollNo())) {
                System.out.println("Roll number already exists.");
                return;
            }
        }

        students.add(student);
        saveStudents();

        System.out.println("Student added successfully.");
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (int i = 0; i < students.size(); i++) {

            students.get(i).displayStudent();
        }
    }

    public void searchByRollNo(String rollNo) {

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getRollNo().equalsIgnoreCase(rollNo)) {

                students.get(i).displayStudent();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Search student by name
    public void searchByName(String name) {

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getName().equalsIgnoreCase(name)) {

                students.get(i).displayStudent();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent(String rollNo) {

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getRollNo().equalsIgnoreCase(rollNo)) {

                students.remove(i);
                saveStudents();

                System.out.println("Student deleted successfully.");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Update student details
    public void updateStudent(String rollNo, String name, int age,
                              int[] marks, String grade) {

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getRollNo().equalsIgnoreCase(rollNo)) {

                Student student = students.get(i);

                student.setName(name);
                student.setAge(age);
                student.setMarks(marks);
                student.setGrade(grade);

                saveStudents();

                System.out.println("Student updated successfully.");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
}