import interfaces.IControlGenerator;
import interfaces.ISourceGenerator;
import logic.ControlGenerator;
import logic.SourceGenerator;
import model.ControlSequence;
import model.SourceSequence;
import tools.WriteToFile;

public class Run {
    private final static String DESTINATION = "D:/Desktop/Result.txt";
    private final static String WORKDESTINATION = "C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt";

    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile(WORKDESTINATION);

        /* Object to be used to generate control sequences*/
        /* Argument 1: number of rows to be generated
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

        ControlSequence controlSequence = new ControlSequence(4, "Mute Input", "Off", "av input", "On", true, true);

        /* The interface that contains the functions to generate either sequence or matrix*/
        IControlGenerator controlGenerator = new ControlGenerator(controlSequence);

        /* The function that generates the sequences,
         * be aware that in order to generate the sequence the column has to be initialized to -1!!!
         *
         *  */

        //To use the function generator just remove the "//" before the method call
        //fileWriter.writeTo(controlGenerator.generateControlSequence());

        /*------------------------------------------------------------*/

        ControlSequence matrixSequence = new ControlSequence(4, 4, "Mute Input", "Off", "av input", "On", true, true);
        /* The function that generates the control matrices,
         * be aware that in order to generate the sequence the column should not be -1!!!
         *
         *  */
        IControlGenerator controlGenerator2 = new ControlGenerator(matrixSequence);
        //To use the function generator just remove the "//" before the method call
        //fileWriter.writeTo(controlGenerator2.generateMatrixControlSequence());

        /*------------------------------------------------------------*/


        SourceSequence sourceSequence = new SourceSequence(4, "src Input", "Off", "av input", "On", true, true);

        /* The interface that contains the functions to generate either sequence or matrix*/
        ISourceGenerator sourceGenerator = new SourceGenerator(sourceSequence);

        /* The function that generates the sequences,
         *
         *
         *  */
        //To use the function generator just remove the "//" before the method call
        //fileWriter.writeTo(sourceGenerator.generateSourceSequence());

        /*------------------------------------------------------------*/

        SourceSequence matrixSourceSequence = new SourceSequence(4, 4, "Source Input", "Off", "av input", "On", true, true);
        /* The function that generates the control matrices,
         * be aware that in order to generate the sequence the column should not be -1!!!
         *
         *  */
        ISourceGenerator matrixSourceSequenceGenerator = new SourceGenerator(matrixSourceSequence);
        //To use the function generator just remove the "//" before the method call
        fileWriter.writeTo(matrixSourceSequenceGenerator.generateMatrixSourceSequence());

        /*------------------------------------------------------------*/

    }
}

