package model;

import org.jetbrains.annotations.Nullable;
import tools.ChecksumCalculator;
import tools.Enums;
import tools.Generators;

import static model.ControlSequence.*;

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
    @Nullable
    private final String command3;
    private boolean startFromZero;
    private boolean leadingZero;
    private boolean checksum;
    private Enums.ChecksumType checksumType;
    private int startByteChecksum;
    private int endByteChecksum;
    private int checksumOnByte;
    private final static char CR = '\r';
    private final static char LF = '\n';


    public SourceSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, String command1, @Nullable String command2, @Nullable String command3, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.startFromZero = false;
        this.leadingZero = false;
        this.checksum = false;
    }

    public SourceSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, String command1, @Nullable String command2, @Nullable String command3, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.startFromZero = false;
        this.leadingZero = false;
        this.checksum = false;
    }

    @Override
    public String sequence(int row, int column) {
        return "<Sequence Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"" + sequenceCaptionGenerator(row, column) + "\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" SequenceType=\"Source\" Deletable=\"True\" HasData=\"False\" UseHeaderFooter=\"True\">\n" +
                "              <Reply Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"Reply\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" SequenceType=\"Reply\" UseHeaderFooter=\"True\">\n" +
                "                <Image />\n" +
                "                <Command>\n" +
                "                  <Data1 />\n" +
                "                  <SeekOffset Value=\"0\" />\n" +
                "                </Command>\n" +
                "              </Reply>\n" +
                "              <Description />\n" +
                "              <Image />\n" +
                "              <Command>\n" +
                "                <Data1>" + dataGeneratorWithChecksum(row, column) +
                "</Data1>\n" +
                "                <Data2 />\n" +
                "                <Lock1 Value=\"100\" />\n" +
                "                <Lock2 Value=\"2\" />\n" +
                "              </Command>\n" +
                "            </Sequence>\n";
    }

    private String dataGeneratorWithChecksum(int row, int column) {
        if (checksum) {
            return ChecksumCalculator.placeChecksumResult(dataGenerator(row, column), getStringChecksum(dataGenerator(row, column), checksumType, startByteChecksum, endByteChecksum), checksumOnByte);
        }
        return dataGenerator(row, column);
    }

    private String dataGenerator(int row, int column) {
        if (startFromZero) {
            if (column <= 0) {
                return sequenceData(row, command1, command2, carriageReturn, lineFeed, CR, LF);
            } else {
                return sequenceMatrixData(row, column, command1, command2, command3, carriageReturn, lineFeed, CR, LF);
            }
        }
        if (column <= 0) {
            row = row + 1;
            return sequenceData(row, command1, command2, carriageReturn, lineFeed, CR, LF);
        } else {
            row = row + 1;
            column = column + 1;
            return sequenceMatrixData(row, column, command1, command2, command3, carriageReturn, lineFeed, CR, LF);
        }
    }

    private String sequenceCaptionGenerator(int row, int column) {
        return getString(row, column, sequenceCaption1, sequenceCaption2);
    }

    private String sequenceData(int row, String command1, String command2, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        if (leadingZero) {
            return sequenceDataWithLeadingZero(row, command1, command2, carriageReturn, lineFeed, cr, lf);
        }
        return sequenceDataWithoutLeadingZero(row, command1, command2, carriageReturn, lineFeed, cr, lf);
    }

    private String sequenceMatrixData(int row, int column, String command1, String command2, String command3, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        if (leadingZero) {
            return sequenceMatrixDataWithLeadingZero(row, column, command1, command2, command3, carriageReturn, lineFeed, cr, lf);
        }
        return sequenceMatrixDataFormat(row, column, command1, command2, command3, carriageReturn, lineFeed, cr, lf);
    }

    public void startFromZero() {
        startFromZero = true;
    }

    public void addLeadingZero() {
        leadingZero = true;
    }

    public void addChecksum(Enums.ChecksumType checksumType, int startByte, int endByte, int placeChecksumOnByte) {
        this.checksum = true;
        this.checksumType = checksumType;
        this.startByteChecksum = startByte;
        this.endByteChecksum = endByte;
        this.checksumOnByte = placeChecksumOnByte;
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
