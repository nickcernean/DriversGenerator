package model;

import org.jetbrains.annotations.Nullable;
import tools.Generator;

public class ControlSequence {

    private int row;
    @Nullable
    private int column;
    private boolean carriageReturn;
    private boolean lineFeed;
    private String sequenceCaption1;
    @Nullable
    private String sequenceCaption2;


    public ControlSequence(int row, @Nullable int column, String sequenceCaption1, @Nullable String sequenceCaption2, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.row = row;
        this.column = column;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;

    }

    public String sequenceGenerator() {
        String replySequence = "";
        replySequence = "<Sequence Name=\"" + Generator.sequenceNameGenerator() + "\" Caption=\"" + sequenceCaptionGenerator() + "\" DeviceMenu=\"True\" ProjectMenu=\"True\" Selectable=\"True\" SequenceType=\"Control\" Deletable=\"True\" HasData=\"False\" UseHeaderFooter=\"True\">\n" +
                "              <Description />\n" +
                "              <Image />\n" +
                "              <Command>\n" +
                "                <Data1>\n" +
                "                </Data1>\n" +
                "                <Data2 />\n" +
                "                <Lock1 Value=\"100\" />\n" +
                "                <Lock2 Value=\"2\" />\n" +
                "              </Command>\n" +
                "            </Sequence>";
        return replySequence;
    }


    public String sequenceCaptionGenerator() {
// TO DO
        if (column == 0 && sequenceCaption2 != null) {
            return sequenceCaption1 + " " + row;
        } if (column == 0 && sequenceCaption2 != null) {
            return sequenceCaption1 + " " + row;
        }

        return sequenceCaption1 + " " + row;
    }
}
