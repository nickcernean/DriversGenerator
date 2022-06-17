import interfaces.ISequencesGenerator;
import logic.ControlGenerator;
import model.ControlSequence;
import tools.WriteToFile;

public class CreateControlMatrixSequences{
    private final static String WORKDESTINATION = "C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt";


    /*             Create Matrix Control Sequences      */
    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile(WORKDESTINATION);

        /* Object to be used to generate control sequences*/
        /*  Argument 1: number of commands to be generated
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

        /* The function that generates the control matrices,
         * be aware that in order to generate the sequence the column should not be -1 or 0!!!
         *
         *  */

        ControlSequence matrixSequence = new ControlSequence("Input ", 1, "to Output ", 4,
                "av input", "On", "", true, true);
        /* The function that generates the control matrices,
         * be aware that in order to generate the sequence the column should not be -1 or 0!!!
         *
         *  */

        ISequencesGenerator controlGenerator = new ControlGenerator(matrixSequence);
        //To use the function generator just remove the "//" before the method call
        fileWriter.writeTo(controlGenerator.generateMatrixSequence());

        /*------------------------------------------------------------*/
    }
}
