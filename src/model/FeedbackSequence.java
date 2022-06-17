package model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.Generators;

import static model.ControlSequence.getString;

public class FeedbackSequence extends Sequence {
    private final int rows;
    private final int columns;
    private final boolean carriageReturn;
    private final boolean lineFeed;

    private final String sequenceCaption1;
    @Nullable
    private final String sequenceCaption2;
    private final String replyCommand1;
    @Nullable
    private final String replyCommand2;
    @Nullable
    private final String replyCommand3;
    private final String requestCommand1;
    private final String requestCommand2;
    private final static char CR = '\r';
    private final static char LF = '\n';


    public FeedbackSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, String requestCommand1, String requestCommand2, String replyCommand1, @Nullable String replyCommand2, @Nullable String replyCommand3, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.replyCommand1 = replyCommand1;
        this.replyCommand2 = replyCommand2;
        this.replyCommand3 = replyCommand3;
        this.requestCommand1 = requestCommand1;
        this.requestCommand2 = requestCommand2;
    }

    private String dataGenerator(int row, int column) {
        return getString(row, column, replyCommand1, replyCommand2, replyCommand3, carriageReturn, lineFeed, CR, LF);
    }

    private String sequenceCaptionGenerator(int row, int replyNumber) {
        return getString(row, replyNumber, sequenceCaption1, sequenceCaption2);
    }

    @Override
    public String sequence(int row, int column) {
        return "<FeedbackSequence Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"" + feedbackSequenceGenerator(row) + "\" Mode=\"Pull\" UseHeaderFooter=\"True\">\n" +
                "              <RequestCommand>" + requestDataGenerator() + "</RequestCommand>\n" +
                "              <RequestInterval Value=\"3000\" />\n" +
                "              <ReplyDataType Value=\"String\" />\n" +
                "              <ReplyNumberRange Min=\"0\" Max=\"0\" />\n" +
                "              <ReplyByteOffset Value=\"0\" />\n" +
                "              <ReplyValueType Value=\"\" />\n" +
                "              <ReplyValueFormat Value=\"\" />\n" +
                "              <ReplyThousandSeperator Value=\"\" />\n" +
                "              <ReplyTimeFormat Value=\"\" />\n" +
                "              <ReplyByteOrder Value=\"\" />\n" +
                "              <ReplyMaxNumberOfBytesForValue Value=\"\" />\n" +
                "              <Replies>\n" +
                replySequence(row, column)
                +
                "                </Reply>\n" +
                "              </Replies>\n" +
                "            </FeedbackSequence>";
    }


    private String feedbackSequenceGenerator(int row) {
        row = row + 1;
        if (sequenceCaption2 == null) {
            return sequenceCaption1 + " " + row;
        } else {
            return sequenceCaption1 + " " + row + " " + sequenceCaption2;
        }
    }

    private String requestDataGenerator() {
        if (carriageReturn && lineFeed) {
            return Generators.dataEncoder(requestCommand1 + requestCommand2 + CR + LF);
        } else if (carriageReturn) {
            return Generators.dataEncoder(requestCommand1 + requestCommand2 + CR);
        } else if (lineFeed) {
            return Generators.dataEncoder(requestCommand1 + requestCommand2 + LF);
        }
        return Generators.dataEncoder(requestCommand1 + requestCommand2);
    }

    private String replySequence(int row, int column) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= column - 1; i++) {
            result.append("                <Reply Caption=\"").append(sequenceCaptionGenerator(row, column)).append(" reply").append("\" Guid=\"").append(Generators.sequenceNameGenerator()).append("\">\n").append("                  <Data>" + dataGenerator(row, column) + "</Data>\n").append("                  <MappedToSeq Value=\"\" />\n").append("                </Reply>\n");
        }
        return result.toString();
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }
}
