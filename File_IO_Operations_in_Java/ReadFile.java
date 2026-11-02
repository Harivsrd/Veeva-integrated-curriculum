import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) throws FileNotFoundException {
        File obj = new File("sample.txt");
        Scanner Reader = new Scanner(obj);

        while(Reader.hasNextLine()) {
            String data = Reader.nextLine();
            System.out.println(data);
        }
        Reader.close();
    }
}
