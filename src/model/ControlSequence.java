package model;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.ChecksumCalculator;
import tools.Converter;
import tools.Generators;
import tools.Enums;
import java.nio.charset.StandardCharsets;

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
    private boolean startFromZero;

    private boolean leadingZero;
    private int startByteChecksum;
    private int endByteChecksum;
    private Enums.ChecksumType checksumType;
    private int checksumOnByte;

    private boolean checksum;

    private final static char LF = '\n';
    private final static char CR = '\r';

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
        this.startFromZero = false;
        this.leadingZero = false;
        this.checksum = false;
    }

    public ControlSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, String command1, @Nullable String command2, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = "";
        this.startFromZero = false;
        this.leadingZero = false;
        this.checksum = false;
    }

    private String dataGeneratorWithChecksum(int row, int column) {
        if (checksum) {
            return ChecksumCalculator.placeChecksumResult(dataGenerator(row, column), calculateChecksum(dataGenerator(row, column), checksumType, startByteChecksum, endByteChecksum), checksumOnByte);
        }
        return dataGenerator(row, column);
    }

    private String dataGenerator(int row, int column) {
        if (startFromZero) {
            if (column < 0) {
                return sequenceData(row, command1, command2, carriageReturn, lineFeed, CR, LF);
            } else {
                return sequenceMatrixData(row, column, command1, command2, command3, carriageReturn, lineFeed, CR, LF);
            }
        }
        if (column < 0) {
            row = row + 1;
            return sequenceData(row, command1, command2, carriageReturn, lineFeed, CR, LF);
        } else {
            row = row + 1;
            column = column + 1;
            return sequenceMatrixData(row, column, command1, command2, command3, carriageReturn, lineFeed, CR, LF);
        }
    }

    private String sequenceData(int row, String command1, String command2, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        if (leadingZero) {
            return sequenceDataWithLeadingZero(row, command1, command2, carriageReturn, lineFeed, cr, lf);
        }
        return sequenceDataWithoutLeadingZero(row, command1, command2, carriageReturn, lineFeed, cr, lf);
    }

    static String sequenceDataWithoutLeadingZero(int row, String command1, String command2, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        return sequenceDataFormat(row, command1, command2, carriageReturn, lineFeed, cr, lf);
    }

    static String sequenceDataWithLeadingZero(int row, String command1, String command2, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        if (isInteger(row)) {
            if (carriageReturn && lineFeed) {
                return Converter.dataEncoder(command1 + 0, row, command2, -1, "", cr, lf);
            } else if (carriageReturn) {
                return Converter.dataEncoder(command1 + 0, row, command2, -1, "", cr, ' ');
            } else if (lineFeed) {
                return Converter.dataEncoder(command1 + 0, row, command2, -1, "", ' ', lf);
            }
            return Converter.dataEncoder(command1 + 0, row, command2, -1, "", ' ', ' ');
        }
        return sequenceDataFormat(row, command1, command2, carriageReturn, lineFeed, cr, lf);
    }

    private static String sequenceDataFormat(int row, String command1, String command2, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        if (carriageReturn && lineFeed) {
            return Converter.dataEncoder(command1, row, command2, -1, "", cr, lf);
        } else if (carriageReturn) {
            return Converter.dataEncoder(command1, row, command2, -1, "", cr, ' ');
        } else if (lineFeed) {
            return Converter.dataEncoder(command1, row, command2, -1, "", ' ', lf);
        }
        return Converter.dataEncoder(command1, row, command2, -1, "", ' ', ' ');
    }


    private String sequenceMatrixData(int row, int column, String command1, String command2, String command3, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        if (leadingZero) {
            return sequenceMatrixDataWithLeadingZero(row, column, command1, command2, command3, carriageReturn, lineFeed, cr, lf);
        }
        return sequenceMatrixDataFormat(row, column, command1, command2, command3, carriageReturn, lineFeed, cr, lf);
    }

    static String sequenceMatrixDataWithLeadingZero(int row, int column, String command1, String command2, String command3, boolean carriageReturn, boolean lineFeed, char cr, char lf) {

        if (isInteger(row) && isInteger(column)) {
            if (carriageReturn && lineFeed) {
                return Converter.dataEncoder(command1 + 0, row, command2 + 0, column, command3, cr, lf);
            } else if (carriageReturn) {
                return Converter.dataEncoder(command1 + 0, row, command2 + 0, column, command3, cr, ' ');
            } else if (lineFeed) {
                return Converter.dataEncoder(command1 + 0, row, command2 + 0, column, command3, ' ', lf);
            }
            return Converter.dataEncoder(command1 + 0, row, command2 + 0, column, command3, ' ', ' ');
        }
        return sequenceMatrixDataFormat(row, column, command1, command2, command3, carriageReturn, lineFeed, cr, lf);
    }

    static String sequenceMatrixDataFormat(int row, int column, String command1, String command2, String command3, boolean carriageReturn, boolean lineFeed, char cr, char lf) {
        if (carriageReturn && lineFeed) {
            return Converter.dataEncoder(command1, row, command2, column, command3, cr, lf);
        } else if (carriageReturn) {
            return Converter.dataEncoder(command1, row, command2, column, command3, cr, ' ');
        } else if (lineFeed) {
            return Converter.dataEncoder(command1, row, command2, column, command3, ' ', lf);
        }
        return Converter.dataEncoder(command1, row, command2, column, command3, ' ', ' ');
    }

    private String calculateChecksum(String sequenceToCalculate, Enums.ChecksumType checksumType, int startByte, int endByte)
    {
        return getStringChecksum( sequenceToCalculate,  checksumType,  startByte,  endByte);
    }
    public static String getStringChecksum(String sequenceToCalculate, Enums.ChecksumType checksumType, int startByte, int endByte) {
        return switch (checksumType) {
            case ADD -> ChecksumCalculator.Add(sequenceToCalculate, startByte, endByte);
            case SUBTRACT -> ChecksumCalculator.Subtract(sequenceToCalculate, startByte, endByte);
            case BITWISE_AND -> ChecksumCalculator.BitwiseAND(sequenceToCalculate, startByte, endByte);
            case BITWISE_OR -> ChecksumCalculator.BitwiseOR(sequenceToCalculate, startByte, endByte);
        };
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

    private String sequenceCaptionGenerator(int row, int column) {
        return getString(row, column, sequenceCaption1, sequenceCaption2);
    }

    @NotNull
    static String getString(int row, int column, String sequenceCaption1, String sequenceCaption2) {
        if (column < 0) {
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
                return sequenceCaption1 + " " + row;
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
                + "<Data1>" + dataGeneratorWithChecksum(row, column) + "</Data1>\n"
                + "<Data2 />\n"
                + "<Lock1 Value=\"100\" />\n"
                + "<Lock2 Value=\"2\" />\n"
                + "</Command>\n"
                + "</Sequence>\n";
    }

    private static boolean isInteger(int integerPassed) {
        return String.valueOf(integerPassed).matches("\\b([0-9]|9)\\b");
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}
