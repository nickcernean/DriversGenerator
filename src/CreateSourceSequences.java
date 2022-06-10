import interfaces.ISequencesGenerator;
import logic.SourceGenerator;
import model.SourceSequence;
import tools.Run;
import tools.WriteToFile;

public class CreateSourceSequences extends Run {
    private final static String WORKDESTINATION = "C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt";

    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile(WORKDESTINATION);

        SourceSequence sourceSequence = new SourceSequence(48, "Switch Input", "to All Outputs", "CALL /MEDIA/XP/VIDEO:switch(I4:O1)", ")", "", true, true);

        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator sourceGenerator = new SourceGenerator(sourceSequence);

        /* The function that generates the sequences,
         *
         *  */
        //To use the function generator just remove the "//" before the method call
        fileWriter.writeTo(sourceGenerator.generateSequence());

        /*------------------------------------------------------------*/
    }
}
