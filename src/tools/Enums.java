package tools;

public class Enums {
    public enum ChecksumType {
        ADD, SUBTRACT, BITWISE_AND, BITWISE_OR
    }
    public enum ReplyDataFormat {
        Row, Column, RowAndColumn
    }
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
}
