import java.io.File;
import java.io.IOException;

class CreateFile {
    public static void main(String args[]) throws IOException {
        File obj = new File("sample.txt");

        if(obj.createNewFile()) {
            System.out.println("File created successfully");
            System.out.println(obj.getName());
        }
        else{
            System.out.println("File already existed");
        }
    }
}