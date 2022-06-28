import interfaces.ISequencesGenerator;
import logic.FeedbackGenerator;
import logic.LevelGenerator;
import model.FeedbackSequence;
import model.LevelSequence;
import tools.WriteToFile;

public class CreateFeedbackMatrixSequences {
    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile(WriteToFile.WORKDESTINATION);
        FeedbackSequence matrixFeedbackSequence = new FeedbackSequence(5, 2, "Mute Analog ", "Output On", "SET/MEDIA/STREAMS/AUDIO/D", "O", "/PORT.VolumePercent=30", null, null, true, true);
        /* To add counter to the level sequence remove each of the ´//´ before the line */

        /* To add string counter to the level sequence */
        //matrixLevelSequence.addStringCounter(6, 7, LevelSequence.CountFormat.Decimal, 100, 1, 0, 100);

        /* To add binary counter to the level sequence */
        //matrixLevelSequence.addBinaryCounter(6, 7, LevelSequence.ByteOrder.LSB, 100, 1, 0, 150);

        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator matrixFeedbackGenerator = new FeedbackGenerator(matrixFeedbackSequence);

        /* The function that generates the sequences,
         *
         *  */

        fileWriter.writeTo(matrixFeedbackGenerator.generateMatrixSequence());

        /*------------------------------------------------------------*/

    }
}
