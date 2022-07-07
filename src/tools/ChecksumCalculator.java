package tools;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ChecksumCalculator {


    public static String Add(String sequenceToCalculate, int startByte, int endByte) {
        String[] hexToCalculate = stringToHexArray(sequenceToCalculate);
        int result = 0;
        result = hexToDecimal(hexToCalculate[startByte]);
        for (int i = startByte; i <= endByte; i++) {
            if (i == startByte) {
                result = result + hexToDecimal(hexToCalculate[i + 1]);
                i++;
            } else {
                result = result + hexToDecimal(hexToCalculate[i]);
            }
        }
        return decToHexadecimal(result);
    }

    public static String BitwiseAND(String sequenceToCalculate, int startByte, int endByte) {
        String[] hexToCalculate = stringToHexArray(sequenceToCalculate);
        int result = 0;
        result = hexToDecimal(hexToCalculate[startByte]);
        for (int i = startByte; i <= endByte; i++) {
            if (i == startByte) {
                result = result & hexToDecimal(hexToCalculate[i + 1]);
                i++;
            } else {
                result = result & hexToDecimal(hexToCalculate[i]);
            }
        }
        return decToHexadecimal(result);
    }

    public static String CRC_8(String argumentToAdd) {


        return "";
    }

    public static String OnesCompliment(String argumentToAdd) {
        return "";
    }

    public static String BitwiseOR(String sequenceToCalculate, int startByte, int endByte) {
        String[] hexToCalculate = stringToHexArray(sequenceToCalculate);
        int result = 0;
        result = hexToDecimal(hexToCalculate[startByte]);
        for (int i = startByte; i <= endByte; i++) {
            if (i == startByte) {
                result = result | hexToDecimal(hexToCalculate[i + 1]);
                i++;
            } else {
                result = result | hexToDecimal(hexToCalculate[i]);
            }
        }
        return decToHexadecimal(result);
    }

    public static String Subtract(String sequenceToCalculate, int startByte, int endByte) {
        String[] hexToCalculate = stringToHexArray(sequenceToCalculate);
        int result = 0;
        result = hexToDecimal(hexToCalculate[startByte]);
        for (int i = startByte; i <= endByte; i++) {
            if (i == startByte) {
                result = result - hexToDecimal(hexToCalculate[i + 1]);
                i++;
            } else {
                result = result - hexToDecimal(hexToCalculate[i]);
            }
        }
        return decToHexadecimal(result);
    }

    public static String TwosCompliment(String argumentToAdd) {
        return "";
    }

    public static int hexToDecimal(String hexNumber) {
        hexNumber = hexNumber.toUpperCase();
        int len = hexNumber.length();
        int base = 1;
        int decimalValue = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (hexNumber.charAt(i) >= '0' && hexNumber.charAt(i) <= '9') {
                decimalValue += (hexNumber.charAt(i) - 48) * base;
                base = base * 16;
            } else if (hexNumber.charAt(i) >= 'A' && hexNumber.charAt(i) <= 'F') {
                decimalValue += (hexNumber.charAt(i) - 55) * base;
                base = base * 16;
            }
        }
        return decimalValue;
    }

    public static String decToHexadecimal(int decimalNumber) {
        return Integer.toHexString(decimalNumber);
    }

    private static String[] stringToHexArray(String sequence) {
        String[] hexArray = new String[sequence.length() / 2];
        int index;
        for (int i = 0; i <= hexArray.length - 1; i++) {
            index = i * 2;
            hexArray[i] = sequence.substring(index, index + 2);
        }
        return hexArray;
    }
}
