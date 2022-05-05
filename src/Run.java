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

        ControlSequence sequence = new ControlSequence(20, 0, "Mute", null, "av mute ", true, true);




        WriteToFile fileWriter = new WriteToFile("C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt");
        fileWriter.writeTo(sequence.sequenceGenerator());

    }
}

