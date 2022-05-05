import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class WriteToFile {
    private final   File fileObject;
    public WriteToFile(String path) {
        fileObject = new File(path);
    }

    public void writeTo(Object[] objects) {
        try {
            FileWriter myWriter = new FileWriter(fileObject.getPath(), false);

            for (int i = 0; i <= objects.length - 1; i++) {
                myWriter.write(objects[i].toString());
            }
            myWriter.close();
            System.out.println("Successfully generated the commands to the source file.");
        } catch (IOException e) {
            System.out.println("An error occurred writing the content into the file.");
            e.printStackTrace();
        }

    }

}
