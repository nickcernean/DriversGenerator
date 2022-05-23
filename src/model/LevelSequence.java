package model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.Generators;

import static model.ControlSequence.getString;

public class LevelSequence extends Sequence {
    public enum CountType {
        String, Binary
    }

    public enum CountFormat {
        Binary, Decimal, Hexdecimal
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
    private int countStartByte;
    private int countEndByte;
    private final static char CR = '\r';
    private final static char LF = '\n';
    private int repeatSpeed;
    private CountType countType;
    private ByteOrder byteOrder;

    public LevelSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, TypeValues typeValue, String command1, @Nullable String command2, @Nullable String command3, int minimumValue, int maximumValue, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.typeValues = typeValue;
        this.countStartByte = 0;
        this.countEndByte = 0;
        this.repeatSpeed = 500;
        this.stepValue = 1;
    }

    public LevelSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, TypeValues typeValue, String command1, @Nullable String command2, @Nullable String command3, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.typeValues = typeValue;
        this.countStartByte = 0;
        this.countEndByte = 0;
        this.repeatSpeed = 500;
        this.stepValue = 1;
    }

    public LevelSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, @Nullable TypeValues typeValue, String command1, @Nullable String command2, @Nullable String command3, int minimumValue, int maximumValue, int stepValue, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.stepValue = stepValue;
        this.typeValues = typeValue;
        this.countStartByte = 0;
        this.countEndByte = 0;
        this.repeatSpeed = 500;
        this.countType = CountType.String;
    }

    public LevelSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, @Nullable TypeValues typeValue, String command1, @Nullable String command2, @Nullable String command3, int minimumValue, int maximumValue, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.stepValue = 100;
        this.typeValues = typeValue;
        this.countStartByte = 0;
        this.countEndByte = 0;
        this.repeatSpeed = 500;
        this.countType = CountType.String;
    }

    @Override
    public String sequence(int row, int column) {
        return "    <Sequence Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"" + sequenceCaptionGenerator(row, column) + "\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" Deletable=\"True\" SequenceType=\"Volume\" UseHeaderFooter=\"True\">\n" +
                "      <Description />\n" +
                "      <Image />\n" +
                "      <Type Value=\"" + typeValues + "\" />\n" +
                "      <Command>\n" +
                "        <Data1>\n" + dataGenerator(row, column) +
                "       </Data1>\n" +
                "        <Data2 />\n" +
                "        <Data3 />\n" +
                "        <Data4 />\n" +
                "        <CountStart Value=\"" + countStartByte + "\" />\n" +
                "        <CountStop Value=\"" + countEndByte + "\" />\n" +
                "        <SecondCountStart Value=\"0\" />\n" +
                "        <SecondCountStop Value=\"0\" />\n" +
                "        <Delay Value=\"500\" />\n" +
                "        <Delay2 Value=\"500\" />\n" +
                "        <MinimumVolume Value=\"" + minimumValue + "\" />\n" +
                "        <MaximumVolume Value=\"" + maximumValue + "\" />\n" +
                "        <VolumeStep Value=\"" + stepValue + "\" />\n" +
                "        <RepeatSpeed Value=\"" + repeatSpeed + "\" />\n" +
                "        <CountType Value=\"String\" />\n" +
                "        <ByteOrder Value=\"LSB\" />\n" +
                "        <CheckSum Name=\"\" Caption=\"\" Value=\"None\">\n" +
                "          <Type>_</Type>\n" +
                "          <FromByte>0</FromByte>\n" +
                "          <ToByte>0</ToByte>\n" +
                "          <TargetByte>0</TargetByte>\n" +
                "          <CRCPoly>0</CRCPoly>\n" +
                "          <CRCIntVal>0</CRCIntVal>\n" +
                "          <CRCFinalXorVal>0</CRCFinalXorVal>\n" +
                "          <CRCRevDataByte>0</CRCRevDataByte>\n" +
                "          <CRCRevFinalCRC>0</CRCRevFinalCRC>\n" +
                "          <CRCBitNumber>0</CRCBitNumber>\n" +
                "        </CheckSum>\n" +
                "        <CountFormat Value=\"Decimal\" />\n" +
                "      </Command>\n" +
                "    </Sequence>";
    }

    public void addStringCounter(int startByte, int endByte, CountType countType, int repeatSpeed, int minimumValue, int maximumValue) {
        this.countStartByte = startByte;
        this.countEndByte = endByte;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.repeatSpeed = repeatSpeed;
        this.countType = countType;
    }

    public void addBinaryCounter(int startByte, int endByte, CountType countType, ByteOrder byteOrder, int repeatSpeed, int minimumValue, int maximumValue) {
        this.countStartByte = startByte;
        this.countEndByte = endByte;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.repeatSpeed = repeatSpeed;
        this.countType = countType;
        this.byteOrder = byteOrder;
    }

    private String dataGenerator(int row, int column) {
        return getString(row, column, command1, command2, command3, carriageReturn, lineFeed, CR, LF);
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

