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

    public Object[] sequenceGenerator(Sequence sequence, int rows, int columns) {
        Object[] objectTypeArray = new Object[rows];
        for (int i = 0; i <= rows - 1; i++) {
            objectTypeArray[i] = sequence.sequence(i, columns);
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
            byte[] byteArr = dataPassed.getBytes(StandardCharsets.US_ASCII);
            for (byte bytePosition : byteArr) {
                resultString.append(String.format("%02X", bytePosition));
            }
            return resultString.toString();
        } else {
            StringBuilder resultString = new StringBuilder();
            dataPassed = dataPassed.toUpperCase();
            resultString.append(dataPassed);
            if (dataPassed.charAt(dataPassed.length() - 2) == '\r' && dataPassed.charAt(dataPassed.length() - 1) == '\n') {
                resultString.replace(dataPassed.length() - 2, dataPassed.length() - 1, "ODOA");
            } else if (dataPassed.charAt(dataPassed.length() - 1) == '\r') {
                resultString.replace(dataPassed.length() - 1, dataPassed.length() - 1, "OD");
            } else if (dataPassed.charAt(dataPassed.length() - 1) == '\n') {
                resultString.replace(dataPassed.length() - 1, dataPassed.length() - 1, "OA");
            }

            return resultString.toString();
        }

    }

    protected static boolean isHexadecimal(String hexadecimalPassed) {
        return hexadecimalPassed.chars().allMatch(hexadecimalSequence -> "0123456789ABCDEFabcdef\r\n".indexOf(hexadecimalSequence) >= 0);
    }

}
