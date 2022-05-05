package model;

import org.jetbrains.annotations.Nullable;
import tools.Generator;

public class ControlSequence {

    private final int rows;
    private final int columns;
    private final boolean carriageReturn;
    private final boolean lineFeed;
    private final String sequenceCaption1;
    @Nullable
    private final String sequenceCaption2;
    private final String command;

    public ControlSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, String command, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command = command;

    }
    private String data_generator(int row) {

        if (carriageReturn && lineFeed) {
            return command + row + "\\r" + "\\n";
        } else if (carriageReturn) {
            return command + row + "\\r";
        } else if (lineFeed) {
            return command + row + "\\n";
        }
        return command + row;
    }

    public String sequenceCaptionGenerator(int row) {
        if (sequenceCaption2 == null) {
            return sequenceCaption1 + " " + row;
        } else {
            return sequenceCaption1 + " " + row + " " + sequenceCaption2;

        }
    }
    public String matrixCaptionGenerator(int row, int column) {


        if (column == 0 && sequenceCaption2 == null) {
            return sequenceCaption1 + " " + rows;
        }

        if (column == 0 && sequenceCaption2 != null) {
            return sequenceCaption1 + " " + rows;
        }
        return sequenceCaption1 + " " + rows;
    }

    public String[] sequenceGenerator() {
        String[] objectTypeArray = new String[rows];
        for (int i = 0; i <= rows-1 ; i++) {
            objectTypeArray[i] = sequence(i);
        }
        return objectTypeArray;
    }
    public String sequence(int row) {

        return "<Sequence Name=\"" + Generator.sequenceNameGenerator() + "\" Caption=\"" + sequenceCaptionGenerator(row) + "\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" SequenceType=\"Control\" Deletable=\"True\" HasData=\"False\" UseHeaderFooter=\"True\">\n" +
                "              <Description />\n" +
                "              <Image />\n" +
                "              <Command>\n" +
                "                <Data1>\n" + data_generator(row) +
                "                </Data1>\n" +
                "                <Data2 />\n" +
                "                <Lock1 Value=\"100\" />\n" +
                "                <Lock2 Value=\"2\" />\n" +
                "              </Command>\n" +
                "            </Sequence>\n";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
