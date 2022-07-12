import interfaces.ISequencesGenerator;
import logic.SourceGenerator;
import model.SourceSequence;
import tools.WriteToFile;

public class CreateSourceSequences {


    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile();

        SourceSequence sourceSequence = new SourceSequence(16, "Switch Audio Output Mic", "", "< SET ", " AUDIO_OUT_LVL_SWITCH MIC_LVL >", "", true, true);

        sourceSequence.startFromZero();
        sourceSequence.addLeadingZero();
        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator sourceGenerator = new SourceGenerator(sourceSequence);

        /* The function that generates the sequences,
         *
         *  */
        //To use the function generator just remove the "//" before the method call
        fileWriter.writeTo(sourceGenerator.generateSequence());

        /*-----------------------------------------------------------*/
    }
}
