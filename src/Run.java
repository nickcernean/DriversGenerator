import interfaces.IControlGenerator;
import logic.ControlGenerator;
import model.ControlSequence;
import tools.WriteToFile;

public class Run {
   private static String DESTINATION="D:/Desktop/Result.txt";
    private static String WORKDESTINATION="C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt";
    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile(DESTINATION);

        ControlSequence sequence = new ControlSequence(10, -1, "Input", "Output", "av input", "output", true, true);


        IControlGenerator controlGenerator = new ControlGenerator(sequence);
        //fileWriter.writeTo(controlGenerator.generateMatrixControlSequence());
        fileWriter.writeTo(controlGenerator.generateControlSequence());


        //fileWriter.writeTo(sequence.sequenceGenerator());


    }
}

