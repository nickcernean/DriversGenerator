import interfaces.ISequencesGenerator;
import logic.ControlGenerator;
import model.ControlSequence;
import tools.ChecksumCalculator;
import tools.Generators;
import tools.WriteToFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateControlSequences {
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

        ControlSequence controlSequence = new ControlSequence(4, "Mute Output", "Off",
                "ebf3df", "", "", true, true);

//        ControlSequence controlSequence = new ControlSequence(4, "Mute Output", "Off",
//                "MUTE OUTPUT1", "", "", true, false);
        //controlSequence.startFromZero();
        // controlSequence.addLeadingZero();
        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator controlGenerator = new ControlGenerator(controlSequence);

        /* The function that generates the sequences,
         * be aware that in order to generate the sequence the column has to be initialized to -1!!!
         *
         *  */

        fileWriter.writeTo(controlGenerator.generateSequence());

//        System.out.println(ChecksumCalculator.Add("474b169c89969f101286ca894305e9", 3, 5));
//
//       // System.out.println(ChecksumCalculator.hexToBinary("474b169c89969f101286ca894305e9"));
//        System.out.println(ChecksumCalculator.BitwiseAND("474b169c89969f101286ca894305e9",0,9));
//        System.out.println(ChecksumCalculator.hexToBinary("474b169c89969f101286ca894305e9"));
//        System.out.println(Integer.parseInt("01000111",2));


        System.out.println(isHexadecimal("ebf3df33d11fb5c76b11f534ce232771211131"));

    }

    protected static boolean isHexadecimal(String s) {
        boolean carryFlag;
        s = s.toUpperCase();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if ((ch < '0' || ch > '9')
                    && (ch < 'A' || ch > 'F')) {
                carryFlag = false;
            } else {
                carryFlag = true;
            }
            if (!carryFlag)
            {
                return carryFlag;
            }
        }
        return true;
    }
}
