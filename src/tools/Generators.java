package tools;

import model.Sequence;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Generators<T> {


    public Object[][] matrixSequenceGenerator(Sequence sequence, int rows, int columns) {
        Object[][] objectTypeArray = new Object[rows][columns];
        for (int i = 0; i <= rows - 1; i++) {
            for (int j = 0; j <= columns - 1; j++) {
                objectTypeArray[i][j] = sequence.sequence(i, j);
            }
        }
        return objectTypeArray;
    }

    public Object[] sequenceGenerator(Sequence sequence, int rows) {
        Object[] objectTypeArray = new Object[rows];
        for (int i = 0; i <= rows - 1; i++) {
            objectTypeArray[i] = sequence.sequence(i, -1);
        }
        return objectTypeArray;
    }

    public static String sequenceNameGenerator() {
        StringBuilder AlphaNumericString = new StringBuilder();
        AlphaNumericString.append("0123456789abcdefghijklmnopqrstuvxyz");
        StringBuilder sb = new StringBuilder();
        Random random;
        for (int i = 0; i < 36; i++) {
            if (i == 8 || i == 13 || i == 18 || i == 23) {
                sb.append("-");
            } else {
                random = new Random();
                int index = random.nextInt(AlphaNumericString.length());
                sb.append(AlphaNumericString.charAt(index));
            }
        }
        return sb.toString();
    }

    public static String dataEncoder(String dataPassed) {

        if (!isHexadecimal(dataPassed)) {
            StringBuilder resultString = new StringBuilder();
            byte[] byteArr = dataPassed.getBytes();
            for (byte bytePosition : byteArr) {
                resultString.append(String.format("%02X", bytePosition));
            }
            return resultString.toString();
        } else {
            return dataPassed;
        }
    }

    protected static boolean isHexadecimal(String s) {
        return s.chars()
                .skip(s.startsWith("-") ? 1 : 0)
                .allMatch(c -> "0123456789ABCDEFabcdef".indexOf(c) >= 0);
    }


}
