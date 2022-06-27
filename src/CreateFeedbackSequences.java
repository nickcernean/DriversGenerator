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
//        String string1 = "DW882ST;DX813;DW814;DX881ST";
//        String string2 = "DX3350;DW3320;DH3331;DH3330;DU3340;DU3341";
//        String string3 = "VS-44DT;VSM-4x4HFS;VS-44HN;VS-66HN;VS-84HN;VS-1616DN;VS-88DT;VS-62HA;VS-42UHD;VS-88UHDA;VSM-4x4A;VS-62H;VS-44UHDA;VS-48HN;VM-24HC;VS-1616D;VP-16x18AK;VP-8x4AK;VP-8x8AK;VS-88HDxl;VP-4x4K;VS-88HN;VS-3232DN;VS-82HDxl;VS-42HDCP;VS-44HDCP;VP-4x8AK;VP-885;VP-88K;VP-8x8TP;VS-88SDI;VS-1616DN-EM;VS-3232DN-EM;VS-88UHD;VS-62DT";
//
//
//        List<String> l1 = new ArrayList<>(Arrays.asList(string1.split(";", 100)));
//        List<String> l2 = new ArrayList<>(Arrays.asList(string2.split(";", 100)));
//        List<String> l3 = new ArrayList<>(Arrays.asList(string3.split(";", 100)));
//        System.out.println(l1.removeAll(l2));
//
//        System.out.println(Arrays.asList(string2.split(";", 100)).contains("NX9"));
    }
}
