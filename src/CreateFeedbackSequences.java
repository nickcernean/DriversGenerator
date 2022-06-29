import interfaces.ISequencesGenerator;
import logic.FeedbackGenerator;
import model.FeedbackSequence;
import tools.WriteToFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateFeedbackSequences {
    public static void main(String[] args) {
        WriteToFile fileWriter = new WriteToFile(WriteToFile.WORKDESTINATION);

        /* Object to be used to generate control sequences*/
        /*  Argument 1: number of rows to be generated
         *  Argument 2: number of columns to be generated (relevant for matrices)
         *  Argument 3: name of the command that will be generated
         *  Argument 4: a second name that will be generated /CAN BE NULL/
         *  (e.g. Mute Input 1 On, the "On" will be the second sequence name)
         *  Argument 5: command that will be generated (Output result: av input 1 On)
         *  Argument 6: a second command that will be generated, if you have any command after the row /CAN BE NULL/
         *  NB: The order of CR and LF will be same as in the object
         *  (e.g. mute output 1 on, "on" will be second part of the command)
         *  Argument 7: will the carriage return be generated or not (CAN BE TRUE/FALSE)
         *  Argument 8: will the line feed be generated or not (CAN BE TRUE/FALSE)
         * NB: The order of CR and LF will be same as in the object
         * */
        /*------------------------------------------------------------*/

        FeedbackSequence feedbackSequence = new FeedbackSequence(5, 2, "Mute Input State", "",
                "Mute Input ", "State", " Mute Input ", "On", null, true, true);

        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator feedbackGenerator = new FeedbackGenerator(feedbackSequence);

        /* The function that generates the sequences,
         * be aware that in order to generate the sequence the column has to be initialized to -1!!!
         *
         *  */

        fileWriter.writeTo(feedbackGenerator.generateSequence());
    }
}
