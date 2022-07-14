import interfaces.ISequencesGenerator;
import logic.LevelGenerator;
import model.LevelSequence;
import tools.WriteToFile;

public class CreateLevelMatrixSequences  {


    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile();
        /*comment Object to be used to generate control sequences
         *  {rows}: number of commands to be generated
         *  {columns}: number of columns in the matrix to be generated
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
         *   4) be aware of what type of level command you are generating, because they have different arguments and you encounter an error when pasting in the Device Editor
         */

        /*caution//------------------------------------------------------------*/
        //comment// Changes to be made here
        LevelSequence incDecMatrixLevelSequence = new LevelSequence(8, 2, "Mute Analog ", "Output On",
                LevelSequence.TypeValues.InDecrement, "SET/MEDIA/STREAMS/AUDIO/D", "O", "/PORT.VolumePercent=30", false, false);
        /*caution//------------------------------------------------------------*/

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
        incDecMatrixLevelSequence.addStringCounter(3, 5, LevelSequence.CountFormat.Decimal, 100, 1, -1000, 800);

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
        //incDecMatrixLevelSequence.addBinaryCounter(6, 7, LevelSequence.ByteOrder.MSB, 100, 1, 0, 150);

        /*comment Object to be used to generate control sequences
         *  {rows}: number of commands to be generated
         *  {columns}: number of columns in the matrix to be generated
         *  {sequenceCaption1}: first name of the command that will be generated
         *  {sequenceCaption2}: a second name that will be generated (e.g. Mute Input 1 On, the "On" will be the second sequence name)
         *  {typeValue}: type of the level command (In/Decrement,Continuous,Start/Stop)
         *  {upCommand1}: the first part of the command that will be generated (Output result: av input 1 On)
         *  {upCommand2}: a second command that will be generated, if you have any command after the row
         *  {upCommand3}: a third command that will be generated.
         *  {downCommand1}: the first part of the command that will be generated (Output result: av input 1 On)
         *  {downCommand2}: a second command that will be generated, if you have any command after the row
         *  {downCommand3}: a third command that will be generated. (e.g. mute output 1 on, "on" will be second part of the command)
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

        LevelSequence upDownLevelSequence = new LevelSequence(16,2, "BiampCount", "1",
                LevelSequence.TypeValues.Continous, "UpCommand1", "UpCommand1", "UPCommand1",
                "DOWNCommand1","DOWNCommand1","DOWNCommand1", true, false);
        /*caution//------------------------------------------------------------*/

        /* comment// the following function will start counting from zero.
         * caution// to remove this function simply add '//'*/
        //matrixSourceSequence.startFromZero();

        /* comment// the following function will add a zero to digits from 0-9.
         * caution// to remove this function simply add '//'*/
        //matrixSourceSequence.addLeadingZero();

        /* comment Changes can be made here
         * caution// to switch between different types of level sequences change the name of the sequence
         *    (e.g. change the *incDecMatrixLevelSequence to upDownMatrixLevelSequence*)
         * */
        ISequencesGenerator matrixLevelGenerator = new LevelGenerator(incDecMatrixLevelSequence);


        fileWriter.writeTo(matrixLevelGenerator.generateMatrixSequence());

        /*------------------------------------------------------------*/
    }
}
