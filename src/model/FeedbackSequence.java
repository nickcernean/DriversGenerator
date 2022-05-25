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
    private final String command1;
    @Nullable
    private final String command2;
    @Nullable
    private final String command3;

    private final static char CR = '\r';
    private final static char LF = '\n';


    public FeedbackSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, String command1, @Nullable String command2, @Nullable String command3, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
    }

    private String dataGenerator(int row, int column) {
        return getString(row, column, command1, command2, command3, carriageReturn, lineFeed, CR, LF);
    }

    private String sequenceCaptionGenerator(int row, int column) {
        return getString(row, column, sequenceCaption1, sequenceCaption2);
    }

    @Override
    public String sequence(int row, int column) {
        return "<FeedbackSequence Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"" + sequenceCaptionGenerator(row, column) + "\" Mode=\"Pull\" UseHeaderFooter=\"True\">\n" +
                "              <RequestCommand>" + dataGenerator(row, column) + "</RequestCommand>\n" +
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
                "                <Reply Caption=\"Biamp reply\" Guid=\"" + Generators.sequenceNameGenerator() + "\">\n" +
                "                  <Data>5341534153415341534153</Data>\n" +
                "                  <MappedToSeq Value=\"\" />\n" +
                "                </Reply>\n" +
                "              </Replies>\n" +
                "            </FeedbackSequence>";
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
