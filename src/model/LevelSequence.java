package model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.Generators;

import static model.ControlSequence.getString;

public class LevelSequence extends Sequence {
    private final int rows;
    private final int columns;
    private final boolean carriageReturn;
    private final boolean lineFeed;
    private final int minimumValue;
    private final int maximumValue;
    private final int stepValue;
    private final TypeValues typeValues;

    private final String sequenceCaption1;
    @Nullable
    private final String sequenceCaption2;
    private final String command1;
    @Nullable
    private final String command2;
    private final static char CR = '\r';
    private final static char LF = '\n';
    private final static int REPEATSPEED = 100;

    public LevelSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, TypeValues typeValue, String command1, @Nullable String command2, int minimumValue, int maximumValue, int stepValue, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.stepValue = stepValue;
        this.typeValues = typeValue;
    }

    public LevelSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2, @Nullable TypeValues typeValue, String command1, @Nullable String command2, int minimumValue, int maximumValue, int stepValue, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = command1;
        this.command2 = command2;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.stepValue = stepValue;
        this.typeValues = typeValue;
    }
    public LevelSequence(int rows, String sequenceCaption1, @Nullable String sequenceCaption2) {
        this.carriageReturn = true;
        this.lineFeed = true;
        this.rows = rows;
        this.columns = -1;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.command1 = "";
        this.command2 = "";
        this.minimumValue = 12;
        this.maximumValue = 0;
        this.stepValue = 0;
        this.typeValues = TypeValues.Continous;
    }

    @Override
    public String sequence(int row, int column) {
        return "<Sequence Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"" + sequenceCaptionGenerator(row, column) + "\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" Deletable=\"True\" SequenceType=\"Volume\" UseHeaderFooter=\"True\">\n" +
                "              <Description />\n" +
                "              <Image />\n" +
                "              <Type Value=\"InDecrement\" />\n" +
                "              <Command>\n" +
                "                <Data1>B321060102000001B5C8</Data1>\n" +
                "                <Data2 />\n" +
                "                <Data3 />\n" +
                "                <Data4 />\n" +
                "                <CountStart Value=\"9\" />\n" +
                "                <CountStop Value=\"10\" />\n" +
                "                <SecondCountStart Value=\"0\" />\n" +
                "                <SecondCountStop Value=\"0\" />\n" +
                "                <Delay Value=\"500\" />\n" +
                "                <Delay2 Value=\"500\" />\n" +
                "                <MinimumVolume Value=\"-7965\" />\n" +
                "                <MaximumVolume Value=\"-20476\" />\n" +
                "                <VolumeStep Value=\"1\" />\n" +
                "                <RepeatSpeed Value=\"100\" />\n" +
                "                <CountType Value=\"Binary\" />\n" +
                "                <ByteOrder Value=\"MSB\" />\n" +
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
                "                <CountFormat Value=\"Hexdecimal\" />\n" +
                "              </Command>\n" +
                "            </Sequence>";
    }

    private String dataGenerator(int row, int column) {
        return getString(row, column, carriageReturn, lineFeed, command1, CR, LF, command2);
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
