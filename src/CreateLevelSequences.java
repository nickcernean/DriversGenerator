import interfaces.ISequencesGenerator;
import logic.LevelGenerator;
import model.LevelSequence;
import tools.Enums;
import tools.WriteToFile;


public class CreateLevelSequences {
    private static String ESC = Character.toString((char) 27);

    public static void main(String[] args) {


        /*comment Object to be used to generate control sequences
         *  {rows}: number of commands to be generated
         *  {sequenceCaption1}: first name of the command that will be generated
         *  {sequenceCaption2}: a second name that will be generated (e.g. Mute Input 1 On, the "On" will be the second sequence name)
         *  {typeValue}: type of the level command (In/Decrement,Continuous,Start/Stop)
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
         *   4) be aware of what type of level command you are generating, because they have different arguments, and you may encounter an error when pasting in the Device Editor
         *   5) be extremely careful, if you are generating hex commands, do not insert "O" letter instead of "0"
         */

        /*caution//------------------------------------------------------------*/
        //comment// Changes to be made here
        WriteToFile fileWriter = new WriteToFile();

        LevelSequence incDecLevelSequence = new LevelSequence(10, "Set Input ", "Left Gain Mix-Point",
                Enums.TypeValues.InDecrement, "120A0B0C0D0E0F", "    ", false, false);

        /* comment// the following function will start counting from zero.
         * caution// to remove this function simply add '//'*/
        //matrixSourceSequence.startFromZero();

        /* comment// the following function will add a zero to digits from 0-9.
         * caution// to remove this function simply add '//'*/
        //matrixSourceSequence.addLeadingZero();

        /* comment// the following function will add string counter.
         * caution// to remove this function simply add '//'
         * comment
         *  {startByte}: starting byte to for count
         *  {endByte}: ending byte to for count
         *  {countFormat}: type of counter (Hexadecimal/Decimal)
         *  {repeatSpeed}: the speed for the next command to be set
         *  {stepValue}: the amount by which it will be counting
         *  {minimumValue}: lower boundary
         *  {maximumValue}: upper boundary */
        incDecLevelSequence.addStringCounter(3, 5, Enums.CountFormat.Decimal, 100, 1, -1000, 800);

        /* comment// the following function will add binary counter.
         * caution// to remove this function simply add '//'
         * comment
         *  {startByte}: starting byte to for count
         *  {endByte}: ending byte to for count
         *  {byteOrder}: type of counter (MSB(most valuable byte)/LSB(least valuable byte))
         *  {repeatSpeed}: the speed for the next command to be set
         *  {stepValue}: the amount by which it will be counting
         *  {minimumValue}: lower boundary
         *  {maximumValue}: upper boundary */
        //levelSequence.addBinaryCounter(6, 7, LevelSequence.ByteOrder.MSB, 100, 1, 0, 150);

        /* comment// the following function will calculate the checksum for the control sequence.
         * caution// to remove this function simply add '//'
         * Even though the checksum is placed after the end byte of the sequence, the checksum will be placed on the last byte
         * (e.g. length of the command is 5 bytes and checksum is placed on the 7'th, the checksum will be placed on the 6'th byte)
         */
        //incDecLevelSequence.addChecksum(Enums.ChecksumType.ADD,1,4,6);


        /*comment Object to be used to generate control sequences
         *  {rows}: number of commands to be generated
         *  {sequenceCaption1}: first name of the command that will be generated
         *  {sequenceCaption2}: a second name that will be generated (e.g. Mute Input 1 On, the "On" will be the second sequence name)
         *  {typeValue}: type of the level command (In/Decrement,Continuous,Start/Stop)
         *  {upCommand1}: the first part of the command that will be generated (Output result: av input 1 On)
         *  {upCommand2}: a second command that will be generated, if you have any command after the row
         *  {upCommand3}: a third command that will be generated.
         *  {downCommand1}: the first part of the command that will be generated (Output result: av input 1 On)
         *  {downCommand2}: a second command that will be generated, if you have any command after the row
         *  {downCommand3}: a third command that will be generated.
         *  (e.g. mute output 1 on, "on" will be second part of the command)
         *  {carriageReturn}: will the carriage return be generated or not (CAN BE TRUE/FALSE)
         *  {lineFeed}: will the line feed be generated or not (CAN BE TRUE/FALSE)
         *  caution//CAUTION!!
         *   1) The order of CR and LF will be same as in the object
         *   2) if you don't want to have a second sequenceCaption2 or command2, just leave the field empty
         *   3) do not insert any special character nor sequenceCaption1 or sequenceCaption2 (e.g. !"#¤%&/()=?)
         *   4) be aware of what type of level command you are generating, because they have different arguments and you encounter an error when pasting in the Device Editor
         */

        /*caution//------------------------------------------------------------*/
        //comment// Changes to be made here

        LevelSequence upDownLevelSequence = new LevelSequence(16, "BiampCount", "1",
                Enums.TypeValues.Continous, "0A0B", "0A0B", "0A0B",
         "0A0B","0A0B","0A0B", true, false);
        /*caution//------------------------------------------------------------*/

        /* comment// the following function will start counting from zero.
         * caution// to remove this function simply add '//'*/
        //matrixSourceSequence.startFromZero();

        /* comment// the following function will add a zero to digits from 0-9.
         * caution// to remove this function simply add '//'*/
        //matrixSourceSequence.addLeadingZero();

        /* comment Changes can be made here
        * caution// to switch between different types of level sequences change the name of the sequence
        *    (e.g. change the *incDecLevelSequence to upDownLevelSequence*)
        * */

        /* comment// the following function will calculate the checksum for the control sequence.
         * caution// to remove this function simply add '//'
         * Even though the checksum is placed after the end byte of the sequence, the checksum will be placed on the last byte
         * (e.g. length of the command is 5 bytes and checksum is placed on the 7'th, the checksum will be placed on the 6'th byte)
         */
        //upDownLevelSequence.addChecksum(Enums.ChecksumType.ADD,1,4,6);

        ISequencesGenerator levelGenerator = new LevelGenerator(upDownLevelSequence);
        fileWriter.writeTo(levelGenerator.generateSequence());
    }
}
