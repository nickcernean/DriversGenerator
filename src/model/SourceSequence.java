package model;

import org.jetbrains.annotations.Nullable;
import tools.Generators;

import static model.ControlSequence.getString;

public class SourceSequence extends Sequence {


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
    private final static char CR = '\r';
    private final static char LF = '\n';


    public SourceSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, String command1, @Nullable String command2, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
    }

    public SourceSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, String command1, @Nullable String command2, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
    }

    @Override
    public String sequence(int row, int column) {

        return "<Sequence Name=\"" + Generators.sequenceNameGenerator() + "\"Caption=\"" + sequenceCaptionGenerator(row, column) + "\" " +
                "\"DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" SequenceType=\"Source\" Deletable=\"True\" HasData=\"False\" UseHeaderFooter=\"True\">\n"
                + "<Reply Name=\"" + Generators.sequenceNameGenerator() + "\"Caption=\"Reply\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" SequenceType=\"Reply\" UseHeaderFooter=\"True\">\n"
                + "<Image />\n"
                + "<Command>\n"
                + "<Data1 />\n"
                + "< SeekOffset Value = \"0\"/>\n"
                + "</Command >\n"
                + "</Reply >\n"
                + "<Description/>\n"
                + "<Image / >\n"
                + "<Command >\n"
                + "<Data1>" + dataGenerator(row, column) + "</Data1>\n"
                + "<Data2/>\n"
                + "<Lock1 Value = \"100\"/>\n"
                + "<Lock2 Value = \"2\"/>\n"
                + "</Command >\n"
                + "</Sequence >\n";

    }

    private String dataGenerator(int row, int column) {
        return getString(row, column, carriageReturn, lineFeed, command1, CR, LF, command2);
    }

    private String sequenceCaptionGenerator(int row, int column) {
        return getString(row, column, sequenceCaption1, sequenceCaption2);
    }

    @Override
    public int getRows() {
        return 0;
    }

    @Override
    public int getColumns() {
        return 0;
    }
}
