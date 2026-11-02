import java.io.File;
import java.io.IOException;

class FilePractice {
    public static void main(String args[]) throws IOException {
        File obj = new File("sample.txt");

        if(obj.createNewFile()) {
            System.out.println("File created successfully");
        }
        else{
            System.out.println("File already existed");
        }
    }
}