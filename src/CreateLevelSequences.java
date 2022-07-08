import interfaces.ISequencesGenerator;
import logic.LevelGenerator;
import model.LevelSequence;
import tools.WriteToFile;

import java.nio.charset.Charset;


public class CreateLevelSequences {
    private static String ESC = Character.toString((char) 27);

    public static void main(String[] args) {


        WriteToFile fileWriter = new WriteToFile(WriteToFile.WORKDESTINATION);
        LevelSequence levelSequence = new LevelSequence(10, "Set Input ", "Left Gain Mix-Point",
                LevelSequence.TypeValues.InDecrement, "    ", "    ", false, false);
        //LevelSequence levelSequence = new LevelSequence(16, "BiampCount", "1",
        // LevelSequence.TypeValues.Continous, "UpCommand1", "UpCommand1", "UPCommand1",
        // "DOWNCommand1","DOWNCommand1","DOWNCommand1", true, false);

        /* To add counter to the level sequence remove each of the ´//´ before the line */
        // levelSequence.addLeadingZero();
        //levelSequence.startFromZero();

        /* To add string counter to the level sequence */
        levelSequence.addStringCounter(3, 5, LevelSequence.CountFormat.Decimal, 100, 1, -1000, 800);

        /* To add binary counter to the level sequence */
        //levelSequence.addBinaryCounter(6, 7, LevelSequence.ByteOrder.MSB, 100, 1, 0, 150);


        ISequencesGenerator levelGenerator = new LevelGenerator(levelSequence);

        /* The function that generates the sequences,
         *
         *
         *  */
        fileWriter.writeTo(levelGenerator.generateSequence());

        /*------------------------------------------------------------*/
    }
}
