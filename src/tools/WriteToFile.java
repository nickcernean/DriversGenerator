package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class WriteToFile {
    private final   File fileObject;
    private final DateTimeFormatter dateTimeFormatter;

   // public final static String WORKDESTINATION = "C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt";
    public final static String WORKDESTINATION = "C:/Users/Michael.Bruun/OneDrive - Biamp Systems/Desktop/Result.txt";
    public WriteToFile(String path) {
        fileObject = new File(path);
        dateTimeFormatter=DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
    }

 public void writeTo(Object[] objects) {
     try {
         FileWriter myWriter = new FileWriter(fileObject.getPath(), false);
         for (int i = 0; i <= objects.length - 1; i++) {
             myWriter.write(objects[i].toString());
         }
         myWriter.close();
         System.out.println("Successfully generated the commands to the source file. "+LocalTime.now().format(dateTimeFormatter));
     } catch (IOException e) {
         System.out.println("An error occurred writing the content into the file. "+ LocalTime.now().format(dateTimeFormatter));
         e.printStackTrace();
     }
 }
    public void writeTo(Object[][] objects) {
        try {
            FileWriter myWriter = new FileWriter(fileObject.getPath(), false);

            for (int i = 0; i <= objects.length-1; i++) {
                for (int j = 0; j <= objects[i].length-1; j++) {
                    myWriter.write(objects[i][j].toString());
                }
            }
            myWriter.close();
            System.out.println("Successfully generated the commands to the source file. "+LocalTime.now().format(dateTimeFormatter));
        } catch (IOException e) {
            System.out.println("An error occurred writing the content into the file. "+LocalTime.now().format(dateTimeFormatter));
            e.printStackTrace();
        }
    }


}
