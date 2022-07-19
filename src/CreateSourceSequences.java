import interfaces.ISequencesGenerator;
import logic.SourceGenerator;
import model.SourceSequence;
import tools.Enums;
import tools.WriteToFile;

public class CreateSourceSequences {


    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile();

        /*comment Object to be used to generate control sequences
         *  {rows}: number of commands to be generated
         *  {sequenceCaption1}: first name of the command that will be generated
         *  {sequenceCaption2}: a second name that will be generated (e.g. Mute Input 1 On, the "On" will be the second sequence name)
         *  {command1}: the first part of the command that will be generated (Output result: av input 1 On)
         *  {command2}: a second command that will be generated, if you have any command after the row
         *  {command3}: a third command that will be generated.
         *  (e.g. mute output 1 on, "on" will be second part of the command)
         *  {carriageReturn}: will the carriage return be generated or not (CAN BE TRUE/FALSE)
         *  {lineFeed}: will the line feed be generated or not (CAN BE TRUE/FALSE)
         *  caution//CAUTION!!
         *   1) The order of CR and LF will be same as in the object
         *   2) if you don't want to have a second sequenceCaption2 or command2, just leave the field empty
         *   3) do not insert any special character nor sequenceCaption1 or sequenceCaption2 (e.g. !"#¤%&/()=?)
         *   4) be extremely careful, if you are generating hex commands, do not insert "O" letter instead of "0"
         */

        /*caution//------------------------------------------------------------*/
        //comment// Changes to be made here
        SourceSequence sourceSequence = new SourceSequence(16, "Switch Audio Output Mic", "", "< SET ",
                " AUDIO_OUT_LVL_SWITCH MIC_LVL >", "", true, true);
        /*caution------------------------------------------------------------*/

        /* comment// the following function will start counting from zero.
         * caution// to remove this function simply add '//'*/

        //sourceSequence.startFromZero();

        /* comment// the following function will add a zero to digits from 0-9.
         * caution// to remove this function simply add '//'*/

        //sourceSequence.addLeadingZero();

        /* comment// the following function will calculate the checksum for the control sequence.
         * caution// to remove this function simply add '//'
         * Even though the checksum is placed after the end byte of the sequence, the checksum will be placed on the last byte
         * (e.g. length of the command is 5 bytes and checksum is placed on the 7'th, the checksum will be placed on the 6'th byte)
         */
        //sourceSequence.addChecksum(Enums.ChecksumType.ADD,1,4,6);

        ISequencesGenerator sourceGenerator = new SourceGenerator(sourceSequence);
        fileWriter.writeTo(sourceGenerator.generateSequence());
    }
}
