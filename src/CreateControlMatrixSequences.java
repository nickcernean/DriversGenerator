import interfaces.ISequencesGenerator;
import logic.ControlGenerator;
import model.ControlSequence;
import tools.WriteToFile;

public class CreateControlMatrixSequences {


    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile();

        /*comment Object to be used to generate control sequences
         *  {rows}: number of rows in the matrix to be generated
         *  {columns}: number of columns in the matrix to be generated
         *  {sequenceCaption1}: first name of the command that will be generated
         *  {sequenceCaption2}:  a second name that will be generated (e.g. Mute Input 1 On, the "On" will be the second sequence name)
         *  {command1}: the first part of the command that will be generated (Output result: av input 1 On)
         *  {command2}:a second command that will be generated, if you have any command after the row
         *  {command3}:a third command that will be generated, will be inserted after column in the command sequence.(e.g. mute output 1 on, "on" will be second part of the command)
         *  {carriageReturn}: will the carriage return be generated or not (CAN BE TRUE/FALSE)
         *  {lineFeed}: will the line feed be generated or not (CAN BE TRUE/FALSE)
         *  caution//CAUTION!!
         *   1) The order of CR and LF will be same as in the object
         *   2) if you don't want to have a second sequenceCaption2 or command2, just leave the field empty
         *   3) do not insert any special character nor sequenceCaption1 or sequenceCaption2 (e.g. !"#¤%&/()=?)
         */


        /*caution//------------------------------------------------------------*/
        //comment// Changes to be made here
        ControlSequence matrixControlSequence = new ControlSequence(10, 8, "Set Audio In Video Input ", "to Status ",
                "", "*", "!", false, false);
        /*caution//------------------------------------------------------------*/

        /* comment// the following function will start counting from zero.
         * caution// to remove this function simply add '//'*/
        //matrixControlSequence.startFromZero();

        /* comment// the following function will add a zero to digits from 0-9.
         * caution// to remove this function simply add '//'*/
        //matrixControlSequence.addLeadingZero();

        ISequencesGenerator controlGenerator = new ControlGenerator(matrixControlSequence);


        fileWriter.writeTo(controlGenerator.generateMatrixSequence());

        /*------------------------------------------------------------*/
    }
}
