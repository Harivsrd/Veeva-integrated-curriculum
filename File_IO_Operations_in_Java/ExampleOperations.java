import java.io.File;

public class ExampleOperations {
    public static void main(String args[]) {
        File obj = new File("sample.txt");
        if(obj.exists()) {
            System.out.println("The file exists");
            System.out.println(obj.getAbsolutePath());
        }
        else{
            System.out.println("The file does not exists");
        }

        if(obj.delete()) {
            System.out.println(obj.getName()+" is deleted!");
        }
        else{
            System.out.println("Failed to delete the file");
        }

        if(obj.exists()) {
            System.out.println("The file exists");
            System.out.println(obj.getAbsolutePath());
        }
        else{
            System.out.println("The file does not exists");
        }
    }
}
