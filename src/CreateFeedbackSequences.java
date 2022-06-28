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

        String string1 = "SW8 HD 4K;SW6 HD 4K;SW4 HD 4K;SW2 HD 4K;SW2 HD 4K PLUS;SW4 HD 4K PLUS;SW6 HD 4K PLUS;SW8 HD 4K PLUS;SW2 HD 4K PLUS";
        String string2 = "X1173;X1173A;X1273;X1140;X1140A;X1240;S1210;S1212;S1213";
        String string3 = "NP-P554U;NP-P474U;NP-P554W;NP-P474W;NP-P604X;NP-P603X;NP-P523X;NP-PE523X";


        List<String> l1 = new ArrayList<>(Arrays.asList(string1.split(";", 100)));
        List<String> l2 = new ArrayList<>(Arrays.asList(string2.split(";", 100)));
        List<String> l3 = new ArrayList<>(Arrays.asList(string3.split(";", 100)));
        System.out.println("Comparisons"+l2.removeAll(l1));
        System.out.println("Comparisons2"+l1.removeAll(l2));

        System.out.println("Contains"+ l1.indexOf("S1213"));

        System.out.println(Arrays.asList(string2.split(";", 100)).contains("PT-LB330J"));
    }
}
