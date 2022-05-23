package model;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.Generators;

public class ControlSequence extends Sequence {

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


    public ControlSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, String command1, @Nullable String command2, @Nullable String command3, boolean carriageReturn, boolean lineFeed) {
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

    public ControlSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, String command1, @Nullable String command2, @Nullable String command3, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
    }

    private String dataGenerator(int row, int column) {
        return getString(row, column, command1, command2, command3, carriageReturn, lineFeed, CR, LF);
    }

    @NotNull
    static String getString(int row, int column, String command1, String command2, String command3, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        if (column == -1) {
            row = row + 1;
            if (carriageReturn && lineFeed) {
                return Generators.dataEncoder(command1 + row + command2 + command3 + cr + lf);
            } else if (carriageReturn) {
                return Generators.dataEncoder(command1 + row + command2 + command3 + cr);
            } else if (lineFeed) {
                return Generators.dataEncoder(command1 + row + command2 + command3 + lf);
            }
            return Generators.dataEncoder(command1 + row + command2 + command3);
        } else {

            if (carriageReturn && lineFeed) {
                return Generators.dataEncoder(command1 + row + command2 + column + command3 + cr + lf);
            } else if (carriageReturn) {
                return Generators.dataEncoder(command1 + row + command2 + column + command3 + cr);
            } else if (lineFeed) {
                return Generators.dataEncoder(command1 + row + command2 + column + command3 + lf);
            }
            return Generators.dataEncoder(command1 + row + command2 + column + command3);
        }
    }

    private String sequenceCaptionGenerator(int row, int column) {
        return getString(row, column, sequenceCaption1, sequenceCaption2);
    }

    @NotNull
    static String getString(int row, int column, String sequenceCaption1, String sequenceCaption2) {
        if (column == -1) {
            row = row + 1;
            if (sequenceCaption2 == null) {
                return sequenceCaption1 + " " + row;
            } else {
                return sequenceCaption1 + " " + row + " " + sequenceCaption2;
            }
        } else {
            row = row + 1;
            column = column + 1;
            if (sequenceCaption2 == null) {
                return sequenceCaption1 + " " + row + " " + column;
            } else {
                return sequenceCaption1 + " " + row + " " + sequenceCaption2 + " " + column;
            }
        }
    }

    public String sequence(int row, int column) {

        return "<Sequence Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"" + sequenceCaptionGenerator(row, column) +
                "\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" SequenceType=\"Control\" Deletable=\"True\" HasData=\"False\" UseHeaderFooter=\"True\">\n"
                + "<Description />\n"
                + "<Image />\n"
                + "<Command>\n"
                + "<Data1>" + dataGenerator(row, column) + "</Data1>\n"
                + "<Data2 />\n"
                + "<Lock1 Value=\"100\" />\n"
                + "<Lock2 Value=\"2\" />\n"
                + "</Command>\n"
                + "</Sequence>\n";
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
