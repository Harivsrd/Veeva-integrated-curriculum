import java.io.*;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final String FILE_NAME = "student.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Record");
            System.out.println("2. Read All");
            System.out.println("3. Read Record on Roll no");
            System.out.println("4. Read Record on Name");
            System.out.println("5. Delete Record on Roll no");
            System.out.println("6. Update Record on Roll no");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRecord(scanner);
                    break;
                case 2:
                    readAllRecords();
                    break;
                case 3:
                    readByRollNo(scanner);
                    break;
                case 4:
                    readByName(scanner);
                    break;
                case 5:
                    deleteByRollNo(scanner);
                    break;
                case 6:
                    updateByRollNo(scanner);
                    break;
                case 7:
                    System.out.println("Exiting application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose between 1 and 7.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void addRecord(Scanner scanner) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter Roll No: ");
            String rollNo = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            String age = scanner.nextLine();
            System.out.print("Enter Marks (M1 M2 M3 M4 M5 separated by space): ");
            String marksInput = scanner.nextLine();
            System.out.print("Enter Grade: ");
            String grade = scanner.nextLine();

            String record = rollNo + "," + name + "," + age + "," + marksInput.replace(" ", ",") + "," + grade;
            writer.println(record);
            System.out.println("Record added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void readAllRecords() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No student records found yet.");
            return;
        }

        System.out.println("\n--- All Student Records ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("Roll No, Name, Age, M1, M2, M3, M4, M5, Grade");
            System.out.println("------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 9) {
                    System.out.println(data[0]+","+ data[1]+","+ data[2]+","+ data[3]+","+ data[4]+","+ data[5]+","+ data[6]+","+ data[7]+","+ data[8]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void readByRollNo(Scanner scanner) {
        System.out.print("Enter Roll No to search: ");
        String targetRollNo = scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equalsIgnoreCase(targetRollNo)) {
                    printRecordHeader();
                    printFormattedRow(data);
                    found = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No student records file found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Record with Roll No '" + targetRollNo + "' not found.");
        }
    }

    private static void readByName(Scanner scanner) {
        System.out.print("Enter Name to search: ");
        String targetName = scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 1 && data[1].equalsIgnoreCase(targetName)) {
                    if (!found) {
                        printRecordHeader();
                    }
                    printFormattedRow(data);
                    found = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No student records file found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Record with Name '" + targetName + "' not found.");
        }
    }

    private static void deleteByRollNo(Scanner scanner) {
        System.out.print("Enter Roll No to delete: ");
        String targetRollNo = scanner.nextLine();
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_student.txt");

        boolean deleted = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equalsIgnoreCase(targetRollNo)) {
                    deleted = true;
                    continue; // Skip writing this record
                }
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
            return;
        }

        if (deleted) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("Error updating the file structure.");
            }
        } else {
            tempFile.delete();
            System.out.println("Record with Roll No '" + targetRollNo + "' not found.");
        }
    }

    private static void updateByRollNo(Scanner scanner) {
        System.out.print("Enter Roll No to update: ");
        String targetRollNo = scanner.nextLine();
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_student.txt");

        boolean updated = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equalsIgnoreCase(targetRollNo)) {
                    System.out.println("Record found. Enter new details:");
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Age: ");
                    String newAge = scanner.nextLine();
                    System.out.print("Enter New Marks (M1 M2 M3 M4 M5): ");
                    String newMarks = scanner.nextLine();
                    System.out.print("Enter New Grade: ");
                    String newGrade = scanner.nextLine();

                    String updatedRecord = targetRollNo + "," + newName + "," + newAge + "," + newMarks.replace(" ", ",") + "," + newGrade;
                    writer.println(updatedRecord);
                    updated = true;
                } else {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
            return;
        }

        if (updated) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("Error updating the file structure.");
            }
        } else {
            tempFile.delete();
            System.out.println("Record with Roll No '" + targetRollNo + "' not found.");
        }
    }

    private static void printRecordHeader() {
        System.out.println("Roll No, Name, Age, M1, M2, M3, M4, M5, Grade");
        System.out.println("------------------------------------------------------------------");
    }

    private static void printFormattedRow(String[] data) {
        System.out.println(data[0]+","+ data[1]+","+ data[2]+","+ data[3]+","+ data[4]+","+ data[5]+","+ data[6]+","+ data[7]+","+ data[8]);
    }
}