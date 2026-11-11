import java.io.Serializable;

public class Student implements Serializable {

    private String rollNo;
    private String name;
    private int age;
    private int[] marks;
    private String grade;

    public Student(String rollNo, String name, int age, int[] marks, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.grade = grade;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int[] getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void displayStudent() {

        System.out.println("Roll Number : " + rollNo);
        System.out.println("Name        : " + name);
        System.out.println("Age         : " + age);

        System.out.print("Marks       : ");

        for (int i = 0; i < marks.length; i++) {
            System.out.print(marks[i] + " ");
        }

        System.out.println();
        System.out.println("Grade       : " + grade);
        System.out.println("--------------------------------");
    }
}