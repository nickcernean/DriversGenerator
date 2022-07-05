import interfaces.ISequencesGenerator;
import logic.LevelGenerator;
import model.LevelSequence;
import tools.WriteToFile;


public class CreateLevelSequences {


    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile(WriteToFile.WORKDESTINATION);
        LevelSequence levelSequence = new LevelSequence(4, "Volume Output", "",
                LevelSequence.TypeValues.InDecrement, "#AUD-LVL 2,", ",-35,", "", true, false);
//        LevelSequence levelSequence = new LevelSequence(8, "Volume Input ", "",
//                LevelSequence.TypeValues.Continous, "#AUD-LVL 0,", ",++", "",
//                "#AUD-LVL 0,", ",--", "", true, false);

        /* To add counter to the level sequence remove each of the ´//´ before the line */
        //levelSequence.addLeadingZero();
        //levelSequence.startFromZero();

        /* To add string counter to the level sequence */
        levelSequence.addStringCounter(14, 16, LevelSequence.CountFormat.Decimal, 100, 3, -78, 9);

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
