package model;


import org.jetbrains.annotations.Nullable;
import tools.ChecksumCalculator;
import tools.Converter;
import tools.Enums;
import tools.Generators;

import static model.ControlSequence.getStringChecksum;

public class FeedbackSequence extends Sequence {

    private final int rows;
    private final int columns;
    private final boolean carriageReturn;
    private final boolean lineFeed;

    private final String sequenceCaption1;
    @Nullable
    private final String sequenceCaption2;
    private final String replyCaption1;
    @Nullable
    private final String replyCaption2;
    private final String replyCommand1;
    @Nullable
    private final String replyCommand2;
    @Nullable
    private final String replyCommand3;
    private final String requestCommand1;
    private final String requestCommand2;
    private final Enums.ReplyDataFormat replyDataFormat;
    private boolean startFromZero;
    private boolean leadingZero;
    private int startByteChecksum;
    private int endByteChecksum;
    private Enums.ChecksumType checksumType;
    private int checksumOnByte;
    private boolean checksum;

    private int replyStartByteChecksum;
    private int replyEndByteChecksum;
    private Enums.ChecksumType replyChecksumType;
    private int replyChecksumOnByte;
    private boolean replyChecksum;
    private final static char CR = '\r';
    private final static char LF = '\n';


    public FeedbackSequence(int rows, int columns, String sequenceCaption1, @Nullable String sequenceCaption2, String requestCommand1, String requestCommand2, String replyCaption1, @Nullable String replyCaption2, String replyCommand1, @Nullable String replyCommand2, @Nullable String replyCommand3, Enums.ReplyDataFormat replyDataFormat, boolean carriageReturn, boolean lineFeed) {
        this.carriageReturn = carriageReturn;
        this.lineFeed = lineFeed;
        this.rows = rows;
        this.columns = columns;
        this.sequenceCaption1 = sequenceCaption1;
        this.sequenceCaption2 = sequenceCaption2;
        this.replyCaption1 = replyCaption1;
        this.replyCaption2 = replyCaption2;
        this.replyCommand1 = replyCommand1;
        this.replyCommand2 = replyCommand2;
        this.replyCommand3 = replyCommand3;
        this.requestCommand1 = requestCommand1;
        this.requestCommand2 = requestCommand2;
        this.replyDataFormat = replyDataFormat;
        this.startFromZero = false;
        this.leadingZero = false;
        this.checksum = false;
        this.replyChecksum = false;
    }

    @Override
    public String sequence(int row, int column) {
        return "<FeedbackSequence Name=\"" + Generators.sequenceNameGenerator() + "\" Caption=\"" + feedbackCaption(row) + "\" Mode=\"Pull\" UseHeaderFooter=\"True\">\n" + "              <RequestCommand>" + dataGeneratorWithChecksum(row) + "</RequestCommand>\n" + "              <RequestInterval Value=\"3000\" />\n" + "              <ReplyDataType Value=\"String\" />\n" + "              <ReplyNumberRange Min=\"0\" Max=\"0\" />\n" + "              <ReplyByteOffset Value=\"0\" />\n" + "              <ReplyValueType Value=\"\" />\n" + "              <ReplyValueFormat Value=\"\" />\n" + "              <ReplyThousandSeperator Value=\"\" />\n" + "              <ReplyTimeFormat Value=\"\" />\n" + "              <ReplyByteOrder Value=\"\" />\n" + "              <ReplyMaxNumberOfBytesForValue Value=\"\" />\n" + "                 <Replies>" + replySequence(row, column) + "\n               </Replies>\n" + "            </FeedbackSequence>\n";
    }

    private String feedbackCaption(int row) {
        row += 1;
        if (sequenceCaption2 == null) {
            return sequenceCaption1 + " " + row;
        } else {
            return sequenceCaption1 + " " + row + " " + sequenceCaption2;
        }
    }

    private String replyCaptionFormat(int row, int column) {

        return switch (replyDataFormat) {
            case RowAndColumn -> replyCaption(row, column);
            case Row -> replyRowCaption(row);
            case Column -> replyColumnCaption(column);
        };
    }

    private String replyCaption(int row, int column) {
        row += 1;
        column += 1;
        return replyCaption1 + " " + row + " " + replyCaption2 + " " + column + " reply";
    }

    private String replyRowCaption(int row) {
        row += 1;
        if (replyCaption2 == null) {
            return replyCaption1 + " " + row + " reply";
        } else {
            return replyCaption1 + " " + row + " " + replyCaption2 + " reply";
        }
    }

    private String replyColumnCaption(int column) {
        column += 1;
        if (replyCaption2 == null) {
            return replyCaption1 + " " + column + " reply";
        } else {
            return replyCaption1 + " " + column + " " + replyCaption2 + " reply";
        }
    }

    private String dataGeneratorWithChecksum(int row) {
        if (checksum) {
            return ChecksumCalculator.placeChecksumResult(requestCommandSequence(row), getStringChecksum(requestCommandSequence(row), checksumType, startByteChecksum, endByteChecksum), checksumOnByte);
        }
        return requestCommandSequence(row);
    }

    private String replyDataGeneratorWithChecksum(int row, int column) {
        if (replyChecksum) {
            return ChecksumCalculator.placeChecksumResult(replyCommandSequence(row, column), getStringChecksum(replyCommandSequence(row, column), replyChecksumType, replyStartByteChecksum, replyEndByteChecksum), replyChecksumOnByte);
        }
        return replyCommandSequence(row, column);
    }

    private String requestCommandSequence(int row) {
        if (!startFromZero) {
            row += 1;
            return requestCommandFormatWithLeadingZero(row);
        }
        return requestCommandFormatWithLeadingZero(row);
    }

    private String requestCommandFormatWithLeadingZero(int row) {
        if (leadingZero) {
            if (isInteger(row)) {
                if (carriageReturn && lineFeed) {
                    return Converter.dataEncoder(requestCommand1 + 0, row, requestCommand2, -1, "", CR, LF);
                } else if (carriageReturn) {
                    return Converter.dataEncoder(requestCommand1 + 0, row, requestCommand2, -1, "", CR, ' ');
                } else if (lineFeed) {
                    return Converter.dataEncoder(requestCommand1 + 0, row, requestCommand2, -1, "", ' ', LF);
                }
                return Converter.dataEncoder(requestCommand1 + 0, row, requestCommand2, -1, "", ' ', ' ');
            }
            return requestCommandFormat(row);
        }
        return requestCommandFormat(row);
    }

    private String requestCommandFormat(int row) {
        if (carriageReturn && lineFeed) {
            return Converter.dataEncoder(requestCommand1, row, requestCommand2, -1, "", CR, LF);
        } else if (carriageReturn) {
            return Converter.dataEncoder(requestCommand1, row, requestCommand2, -1, "", CR, ' ');
        } else if (lineFeed) {
            return Converter.dataEncoder(requestCommand1, row, requestCommand2, -1, "", ' ', LF);
        }
        return Converter.dataEncoder(requestCommand1, row, requestCommand2, -1, "", ' ', ' ');
    }

    private String replyCommandSequence(int row, int column) {
        if (!startFromZero) {
            row += 1;
            column += 1;
            return replyDataFormat(row, column);
        }
        return replyDataFormat(row, column);
    }


    private String replyDataFormat(int row, int column) {

        return switch (replyDataFormat) {
            case RowAndColumn -> replyDataFormatWithLeadingZero(row, column);
            case Row -> rowReplyDataFormatWithLeadingZero(row);
            case Column -> columnReplyDataFormatWithLeadingZero(column);
        };
    }

    private String replyDataFormatWithLeadingZero(int row, int column) {
        if (leadingZero) {
            if (isInteger(row) && isInteger(column)) {
                return Converter.dataEncoder(replyCommand1 + 0, row, replyCommand2 + 0, column, replyCommand3, ' ', ' ');
            } else if (isInteger(row)) {
                return Converter.dataEncoder(replyCommand1 + 0, row, replyCommand2, column, replyCommand3, ' ', ' ');
            } else if (isInteger(column)) {
                return Converter.dataEncoder(replyCommand1, row, replyCommand2 + 0, column, replyCommand3, ' ', ' ');
            }
            return Converter.dataEncoder(replyCommand1, row, replyCommand2, column, replyCommand3, ' ', ' ');
        }
        return Converter.dataEncoder(replyCommand1, row, replyCommand2, column, replyCommand3, ' ', ' ');
    }

    private String rowReplyDataFormatWithLeadingZero(int row) {
        if (leadingZero) {
            if (isInteger(row)) {
                return Converter.dataEncoder(replyCommand1 + 0, row, replyCommand2, -1, "", ' ', ' ');
            }
            return Converter.dataEncoder(replyCommand1, row, replyCommand2, -1, "", ' ', ' ');
        }
        return Converter.dataEncoder(replyCommand1, row, replyCommand2, -1, "", ' ', ' ');
    }

    private String columnReplyDataFormatWithLeadingZero(int column) {
        if (leadingZero) {
            if (isInteger(column)) {
                return Converter.dataEncoder(replyCommand1, -1, replyCommand2 + 0, column, replyCommand3, ' ', ' ');
            }
            return Converter.dataEncoder(replyCommand1, -1, replyCommand2, column, replyCommand3, ' ', ' ');
        }
        return Converter.dataEncoder(replyCommand1, -1, replyCommand2, column, replyCommand3, ' ', ' ');
    }

    private String replySequence(int row, int column) {
        // StringBuilder result = new StringBuilder();
        String result = "";
        for (int i = 0; i <= column - 1; i++) {
            result += "\n                  <Reply Caption=\"" + replyDataGeneratorWithChecksum(row, i) + "\" Guid=\"" + Generators.sequenceNameGenerator() + "\">\n" + "                      <Data>" + replyCommandSequence(row, i) + "</Data>\n" + "                      <MappedToSeq Value=\"\" />\n" + "                   </Reply>";
        }
        return result;
    }

    private static boolean isInteger(int integerPassed) {
        return String.valueOf(integerPassed).matches("\\b([0-9]|9)\\b");
    }

    public void addChecksum(Enums.ChecksumType checksumType, int startByte, int endByte, int placeChecksumOnByte) {
        this.checksum = true;
        this.checksumType = checksumType;
        this.startByteChecksum = startByte;
        this.endByteChecksum = endByte;
        this.checksumOnByte = placeChecksumOnByte;
    }

    public void addReplyChecksum(Enums.ChecksumType replyChecksumType, int replyStartByteChecksum, int replyEndByteChecksum, int replyChecksumOnByte) {
        this.replyChecksum = true;
        this.replyChecksumType = replyChecksumType;
        this.replyStartByteChecksum = replyStartByteChecksum;
        this.replyEndByteChecksum = replyEndByteChecksum;
        this.replyChecksumOnByte = replyChecksumOnByte;
    }

    public void startFromZero() {
        startFromZero = true;
    }

    public void addLeadingZero() {
        leadingZero = true;
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
