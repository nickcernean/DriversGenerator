import interfaces.IControlGenerator;
import logic.ControlGenerator;
import model.ControlSequence;
import tools.Generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Run {
    public static void main(String[] args) {
        WriteToFile fileWriter = new WriteToFile("C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt");
        ControlSequence sequence = new ControlSequence(20, 0, "Mute", null, "av mute ", null, true, true);
        IControlGenerator controlGenerator = new ControlGenerator(sequence);
        fileWriter.writeTo(controlGenerator.generateControlSequence());
        System.out.println(Arrays.toString( controlGenerator.generateControlSequence()));
        //fileWriter.writeTo(sequence.sequenceGenerator());


    }
}

