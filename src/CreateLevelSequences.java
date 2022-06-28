import interfaces.ISequencesGenerator;
import logic.LevelGenerator;
import model.LevelSequence;
import tools.WriteToFile;


public class CreateLevelSequences {


    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile(WriteToFile.WORKDESTINATION);
//        LevelSequence levelSequence = new LevelSequence(16, "Set Audio Gain Inc_Dec", "",
//                LevelSequence.TypeValues.InDecrement, "< REP ", " AUDIO_OUT_LVL_SWITCH MIC_LVL >", "", false, false);
        LevelSequence levelSequence = new LevelSequence(16, "BiampCount", "1",
                LevelSequence.TypeValues.Continous, "UpCommand1", "UpCommand1", "UPCommand1",
                "DOWNCommand1","DOWNCommand1","DOWNCommand1", false, false);

        /* To add counter to the level sequence remove each of the ´//´ before the line */

        //boolean isTypeOfCounterString=false;
        //levelSequence.addLeadingZero();

        /* To add string counter to the level sequence */
        //levelSequence.addStringCounter(7, 8, LevelSequence.CountFormat.Decimal, 100, 3, 0, 100);

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