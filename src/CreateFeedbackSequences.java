import interfaces.ISequencesGenerator;
import logic.FeedbackGenerator;
import model.FeedbackSequence;
import tools.WriteToFile;

public class CreateFeedbackSequences {
    public static void main(String[] args) {
        WriteToFile fileWriter = new WriteToFile();

        /*comment Object to be used to generate control sequences
         *  {rows}: number of feedbacks sequences to be generated
         *  {columns}: number of reply's that will be generated
         *  {sequenceCaption1}: first name of the command that will be generated
         *  {sequenceCaption2}:  a second name that will be generated (e.g. Mute Input 1 On, the "On" will be the second sequence name)
         *  {requestCommand1}: the first part of the request command that will be generated (Output result: av input 1 On)
         *  {requestCommand2}: the second part of the request command command that will be generated, if you have any command after the row number
         *  {replyCaption1}: first name of the reply that will be generated
         *  {replyCaption2}:  a second name that of the reply that will be generated (e.g. Mute Input 1 On, the "On" will be the second sequence name)
         *  {replyCommand1}: the first part of the reply command that will be generated (Output result: av input 1 On)
         *  {replyCommand2}: the second part of the reply command command that will be generated, if you have any command after the row
         *  {replyCommand3}: the third part of the reply command command that will be generated, if you have any command after the column
         *  {replyDataFormat}: the format type of the reply command (can either have just the ROW/COLUMN/ROW_AND_COLUMN)
         *  {carriageReturn}: will the carriage return be generated or not (CAN BE TRUE/FALSE)
         *  {lineFeed}: will the line feed be generated or not (CAN BE TRUE/FALSE)
         *  caution//CAUTION!!
         *   1) The order of CR and LF will be same as in the object
         *   2) if you don't want to have a second sequenceCaption2 or command2, just leave the field empty
         *   3) do not insert any special character nor sequenceCaption1 or sequenceCaption2 (e.g. !"#¤%&/()=?)
         */

        /*caution//------------------------------------------------------------*/
        //comment// Changes to be made here
        FeedbackSequence feedbackSequence = new FeedbackSequence(15, 10, "Audio In Video Input1", "State",
                "12", "", "Test Caption", "to", ""
                , "12", "", FeedbackSequence.ReplyDataFormat.RowAndColumn, false, false);
        /*caution//------------------------------------------------------------*/

        /* comment// the following function will start counting from zero.
         * caution// to remove this function simply add '//'*/
        //feedbackSequence.startFromZero();

        /* comment// the following function will add a zero to digits from 0-9.
         * caution// to remove this function simply add '//'*/
        //feedbackSequence.addLeadingZero();


        ISequencesGenerator feedbackGenerator = new FeedbackGenerator(feedbackSequence);
        fileWriter.writeTo(feedbackGenerator.generateSequence());
    }
}
