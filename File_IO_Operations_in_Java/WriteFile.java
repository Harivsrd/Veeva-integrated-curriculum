import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static void main(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter("sample.txt")) {
            writer.write("Files in java are seriously good!");
            writer.write("Hi");
            System.out.println("Successfully written");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
