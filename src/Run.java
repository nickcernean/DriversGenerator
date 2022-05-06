import interfaces.IControlGenerator;
import logic.ControlGenerator;
import model.ControlSequence;
import tools.WriteToFile;

import java.util.Arrays;

public class Run {
    public static void main(String[] args) {
        WriteToFile fileWriter = new WriteToFile("C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt");

        ControlSequence sequence = new ControlSequence(30, 20, "Input", "Output", "av input", "output", true, true);


        IControlGenerator controlGenerator = new ControlGenerator(sequence);
        fileWriter.writeTo(controlGenerator.generateMatrixControlSequence());
        //fileWriter.writeTo(controlGenerator.generateControlSequence());


        //fileWriter.writeTo(sequence.sequenceGenerator());


    }
}

