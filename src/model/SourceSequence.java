package model;

import org.jetbrains.annotations.Nullable;

public class SourceSequence extends Sequence{





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
    private final static char CR= '\r';
    private final static char LF= '\n';


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

    @Override
    public String sequence(int row,int columns) {
        return null;
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
