import interfaces.ISequencesGenerator;
import logic.ControlGenerator;
import logic.LevelGenerator;
import logic.SourceGenerator;
import model.ControlSequence;
import model.LevelSequence;
import model.SourceSequence;
import tools.WriteToFile;

public class Run {
    private final static String DESTINATION = "D:/Desktop/Result.txt";
    private final static String WORKDESTINATION = "C:/Users/Nicolae.Cernean/OneDrive - Biamp Systems/Desktop/Result.txt";

    public static void main(String[] args) {

        WriteToFile fileWriter = new WriteToFile(WORKDESTINATION);

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

        ControlSequence controlSequence = new ControlSequence(30, "Mute Input", "On", "CALL/MEDIA/XP/VIDEO:muteSource(I", ")", "", true, true);

        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator controlGenerator = new ControlGenerator(controlSequence);

        /* The function that generates the sequences,
         * be aware that in order to generate the sequence the column has to be initialized to -1!!!
         *
         *  */

        //To use the function generator just remove the "//" before the method call
        fileWriter.writeTo(controlGenerator.generateSequence());

        /*------------------------------------------------------------*/

        ControlSequence matrixSequence = new ControlSequence(4, 4, "Mute Input", "Off", "av input", "On", null, true, true);
        /* The function that generates the control matrices,
         * be aware that in order to generate the sequence the column should not be -1!!!
         *
         *  */

        ISequencesGenerator controlGenerator2 = new ControlGenerator(matrixSequence);
        //To use the function generator just remove the "//" before the method call
        //fileWriter.writeTo(controlGenerator2.generateMatrixControlSequence());

        /*------------------------------------------------------------*/


        SourceSequence sourceSequence = new SourceSequence(48, "Switch Input", "to All Outputs", "CALL /MEDIA/XP/VIDEO:switch(I4:O1)", ")", "", true, true);

        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator sourceGenerator = new SourceGenerator(sourceSequence);

        /* The function that generates the sequences,
         *
         *
         *  */
        //To use the function generator just remove the "//" before the method call
        //fileWriter.writeTo(sourceGenerator.generateSequence());

        /*------------------------------------------------------------*/

        SourceSequence matrixSourceSequence = new SourceSequence(48, 48, "Switch Input", "to Output", "CALL/MEDIA/XP/VIDEO:switch(I", ":O", ")", true, true);
        /* The function that generates the control matrices,
         * be aware that in order to generate the sequence the column should not be -1!!!
         *
         *  */
        ISequencesGenerator matrixSourceSequenceGenerator = new SourceGenerator(matrixSourceSequence);
        //To use the function generator just remove the "//" before the method call
        //fileWriter.writeTo(matrixSourceSequenceGenerator.generateMatrixSequence());

        /*------------------------------------------------------------*/


        LevelSequence levelSequence = new LevelSequence(2, "SET•/MEDIA/STREAMS/AUDIO/<in|out>/Port.VolumedB=", "Mute On", LevelSequence.TypeValues.InDecrement, "1A2B3C5D7F4312", "4312", "", false, false);

        levelSequence.addStringCounter(6, 7, LevelSequence.CountFormat.Decimal, 100, 1, 0, 100);
        //levelSequence.addBinaryCounter(6,7,LevelSequence.ByteOrder.LSB,100,1,0,150);
        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator levelGenerator = new LevelGenerator(levelSequence);

        /* The function that generates the sequences,
         *
         *
         *  */
        //To use the function generator just remove the "//" before the method call
        //fileWriter.writeTo(levelGenerator.generateSequence());

        /*------------------------------------------------------------*/

        LevelSequence matrixLevelSequence = new LevelSequence(2, 2, "Mute Analog ", "Output On", LevelSequence.TypeValues.InDecrement, "SET•/MEDIA/STREAMS/AUDIO/D", "O", "/PORT.VolumePercent=30", true, true);

        //matrixLevelSequence.addStringCounter(50, 51, LevelSequence.CountFormat.Decimal, 100, 1, 0, 100);
        //matrixLevelSequence.addBinaryCounter(6,7,LevelSequence.ByteOrder.LSB,100,1,0,150);
        /* The interface that contains the functions to generate either sequence or matrix*/
        ISequencesGenerator matrixLevelGenerator = new LevelGenerator(matrixLevelSequence);

        /* The function that generates the sequences,
         *
         *
         *  */
        //To use the function generator just remove the "//" before the method call
        //fileWriter.writeTo(matrixLevelGenerator.generateMatrixLevelSequence());

        /*------------------------------------------------------------*/

    }

}

