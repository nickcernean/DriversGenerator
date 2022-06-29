package model;

import org.jetbrains.annotations.Nullable;
import tools.Generators;

import static model.ControlSequence.getString;
import static model.ControlSequence.sequenceDataWithoutLeadingZero;

public class LevelSequence extends Sequence {
    public enum CountType {
        String, Binary
    }

    public enum CountFormat {
        Decimal, Hexdecimal
    }

    public enum TypeValues {
        Continous, InDecrement, StartStop
    }

    public enum ByteOrder {
        MSB, LSB
    }

    private final int rows;
    private final int columns;
    private final boolean carriageReturn;
    private final boolean lineFeed;
    private int minimumValue;
    private int maximumValue;
    private int stepValue;
    private final TypeValues typeValues;

    private final String sequenceCaption1;
    @Nullable
    private final String sequenceCaption2;
    private final String command1;
    @Nullable
    private final String command2;
    @Nullable
    private final String command3;
    @Nullable
    private final String downCommand1;
    @Nullable
    private final String downCommand2;
    @Nullable
    private final String downCommand3;
    private int countStartByte;
    private int countEndByte;
    private final static char CR = '\r';
    private final static char LF = '\n';
    private int repeatSpeed;
    private CountType countType;
    private ByteOrder byteOrder;
    private CountFormat countFormat;
    private boolean startFromZero;
    private boolean leadingZero;


    public LevelSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, TypeValues typeValue, String command1, @Nullable String command2, @Nullable String command3, boolean carriageReturn, boolean lineFeed) {
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.downCommand1 = null;
        this.downCommand2 = null;
        this.downCommand3 = null;
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.repeatSpeed = 500;
        this.typeValues = typeValue;
        this.countType = CountType.String;
        this.byteOrder = ByteOrder.LSB;
        this.countFormat = CountFormat.Decimal;
        this.stepValue = 1;
        this.startFromZero = false;
        this.leadingZero = false;
    }

    public LevelSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, @Nullable TypeValues typeValue, String command1, @Nullable String command2, @Nullable String command3, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.downCommand1 = null;
        this.downCommand2 = null;
        this.downCommand3 = null;
        this.repeatSpeed = 500;
        this.typeValues = typeValue;
        this.countType = CountType.String;
        this.byteOrder = ByteOrder.LSB;
        this.countFormat = CountFormat.Decimal;
        this.stepValue = 1;
        this.startFromZero = false;
        this.leadingZero = false;
    }

    public LevelSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, TypeValues typeValue, String upCommand1, @Nullable String upCommand2, @Nullable String upCommand3, @Nullable String downCommand1, @Nullable String downCommand2, @Nullable String downCommand3, boolean carriageReturn, boolean lineFeed) {
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = upCommand1;
        this.command2 = upCommand2;
        this.command3 = upCommand3;
        this.downCommand1 = downCommand1;
        this.downCommand2 = downCommand2;
        this.downCommand3 = downCommand3;
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.repeatSpeed = 500;
        this.typeValues = typeValue;
        this.countType = CountType.String;
        this.byteOrder = ByteOrder.LSB;
        this.countFormat = CountFormat.Decimal;
        this.stepValue = 1;
        this.startFromZero = false;
        this.leadingZero = false;
    }

    public LevelSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, @Nullable TypeValues typeValue, String upCommand1, @Nullable String upCommand2, @Nullable String upCommand3, @Nullable String downCommand1, @Nullable String downCommand2, @Nullable String downCommand3, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = upCommand1;
        this.command2 = upCommand2;
        this.command3 = upCommand3;
        this.downCommand1 = downCommand1;
        this.downCommand2 = downCommand2;
        this.downCommand3 = downCommand3;
        this.repeatSpeed = 500;
        this.typeValues = typeValue;
        this.countType = CountType.String;
        this.byteOrder = ByteOrder.LSB;
        this.countFormat = CountFormat.Decimal;
        this.stepValue = 1;
        this.startFromZero = false;
        this.leadingZero = false;
    }

    @Override
    public String sequence(int row, int column) {
        return "<Sequence Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"" + sequenceCaptionGenerator(row, column) + "\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" Deletable=\"True\" SequenceType=\"Volume\" UseHeaderFooter=\"True\">\n" +
                "              <Description />\n" +
                "              <Image />\n" +
                "              <Type Value=\"" + typeValues + "\" />\n" +
                "              <Command>\n" +
                "                <Data1>" + dataGenerator(row, column) + "</Data1>\n" +
                "                <Data2 />\n" +
                "                <Data3>" + data2Generator(row, column) + "</Data3>\n" +
                "                <Data4 />\n" +
                "                <CountStart Value=\"" + countStartByte + "\" />\n" +
                "                <CountStop Value=\"" + countEndByte + "\" />\n" +
                "                <SecondCountStart Value=\"0\" />\n" +
                "                <SecondCountStop Value=\"0\" />\n" +
                "                <Delay Value=\"500\" />\n" +
                "                <Delay2 Value=\"500\" />\n" +
                "                <MinimumVolume Value=\"" + minimumValue + "\" />\n" +
                "                <MaximumVolume Value=\"" + maximumValue + "\" />\n" +
                "                <VolumeStep Value=\"" + stepValue + "\" />\n" +
                "                <RepeatSpeed Value=\"" + repeatSpeed + "\" />\n" +
                "                <CountType Value=\"" + countType + "\" />\n" +
                "                <ByteOrder Value=\"" + byteOrder + "\" />\n" +
                "                <CheckSum Name=\"\" Caption=\"\" Value=\"None\">\n" +
                "                  <Type>_</Type>\n" +
                "                  <FromByte>0</FromByte>\n" +
                "                  <ToByte>0</ToByte>\n" +
                "                  <TargetByte>0</TargetByte>\n" +
                "                  <CRCPoly>0</CRCPoly>\n" +
                "                  <CRCIntVal>0</CRCIntVal>\n" +
                "                  <CRCFinalXorVal>0</CRCFinalXorVal>\n" +
                "                  <CRCRevDataByte>0</CRCRevDataByte>\n" +
                "                  <CRCRevFinalCRC>0</CRCRevFinalCRC>\n" +
                "                  <CRCBitNumber>0</CRCBitNumber>\n" +
                "                </CheckSum>\n" +
                "                <CountFormat Value=\"" + countFormat + "\" />\n" +
                "                <AddLeadingZeros>" + leadingZero + "</AddLeadingZeros>" +
                "              </Command>\n" +
                "            </Sequence>";
    }


    public void addStringCounter(int startByte, int endByte, CountFormat countFormat, int repeatSpeed, int stepValue, int minimumValue, int maximumValue) {
        this.countStartByte = startByte;
        this.countEndByte = endByte;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.repeatSpeed = repeatSpeed;
        this.countType = CountType.String;
        this.countFormat = countFormat;
        this.stepValue = stepValue;
    }

    public void addBinaryCounter(int startByte, int endByte, ByteOrder byteOrder, int repeatSpeed, int stepValue, int minimumValue, int maximumValue) {
        this.countStartByte = startByte;
        this.countEndByte = endByte;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.repeatSpeed = repeatSpeed;
        this.countType = CountType.Binary;
        this.byteOrder = byteOrder;
        this.stepValue = stepValue;
    }

    private String dataGenerator(int row, int column) {
        if (startFromZero) {
            if (column <= 0) {
                return sequenceData(row, command1, command2, carriageReturn, lineFeed);
            } else {
                return matrixData(row, column, command1, command2, command3, carriageReturn, lineFeed);
            }
        }
        if (column <= 0) {
            row = row + 1;
            return sequenceData(row, command1, command2, carriageReturn, lineFeed);
        } else {
            row = row + 1;
            column = column + 1;
            return matrixData(row, column, command1, command2, command3, carriageReturn, lineFeed);
        }
    }

    private String data2Generator(int row, int column) {
        if (typeValues.equals(TypeValues.Continous)) {
            if (startFromZero) {
                if (column <= 0) {
                    return sequenceData(row, downCommand1, downCommand2, carriageReturn, lineFeed);
                } else {
                    return matrixData(row, column, downCommand1, downCommand2, downCommand3, carriageReturn, lineFeed);
                }
            }
            if (column <= 0) {
                row = row + 1;
                return sequenceData(row, downCommand1, downCommand2, carriageReturn, lineFeed);
            } else {
                row = row + 1;
                column = column + 1;
                return matrixData(row, column, downCommand1, downCommand2, downCommand3, carriageReturn, lineFeed);
            }
        } else return "<Data3/>\n";
    }

    private static String sequenceData(int row, String command1, String command2, boolean carriageReturn, boolean lineFeed) {
        return sequenceDataWithoutLeadingZero(row, command1, command2, carriageReturn, lineFeed, LevelSequence.CR, LevelSequence.LF);
    }

    private static String matrixData(int row, int column, String command1, String command2, String command3, boolean carriageReturn, boolean lineFeed) {
        return getString(row, column, command1, command2, command3, carriageReturn, lineFeed, LevelSequence.CR, LevelSequence.LF);
    }

    public void startFromZero() {
        startFromZero = true;
    }
    public void addLeadingZero() {
        this.leadingZero = true;
    }

    private String sequenceCaptionGenerator(int row, int column) {
        return getString(row, column, sequenceCaption1, sequenceCaption2);
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

