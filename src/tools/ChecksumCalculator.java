package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChecksumCalculator {

    public static String Add(String sequenceToCalculate, int startByte, int endByte) {
        String[] hexToCalculate = Converter.stringToHexArray(sequenceToCalculate);
        int result = Converter.hexToDecimal(hexToCalculate[startByte]);
        for (int i = startByte; i <= endByte; i++) {
            if (i == startByte) {
                result = result + Converter.hexToDecimal(hexToCalculate[i + 1]);
                i++;
            } else {
                result = result + Converter.hexToDecimal(hexToCalculate[i]);
            }
        }
        return Converter.decToHexadecimal(result);
    }

    public static String BitwiseAND(String sequenceToCalculate, int startByte, int endByte) {
        String[] hexToCalculate = Converter.stringToHexArray(sequenceToCalculate);
        int result = Converter.hexToDecimal(hexToCalculate[startByte]);
        for (int i = startByte; i <= endByte; i++) {
            if (i == startByte) {
                result = result & Converter.hexToDecimal(hexToCalculate[i + 1]);
                i++;
            } else {
                result = result & Converter.hexToDecimal(hexToCalculate[i]);
            }
        }
        return Converter.decToHexadecimal(result);
    }

    public static String CRC_8(String argumentToAdd) {


        return "";
    }

    public static String OnesCompliment(String argumentToAdd) {
        return "";
    }

    public static String BitwiseOR(String sequenceToCalculate, int startByte, int endByte) {
        String[] hexToCalculate = Converter.stringToHexArray(sequenceToCalculate);

        int result = Converter.hexToDecimal(hexToCalculate[startByte]);
        for (int i = startByte; i <= endByte; i++) {
            if (i == startByte) {
                result = result | Converter.hexToDecimal(hexToCalculate[i + 1]);
                i++;
            } else {
                result = result | Converter.hexToDecimal(hexToCalculate[i]);
            }
        }
        return Converter.decToHexadecimal(result);
    }

    public static String placeChecksumResult(String sequenceToCalculate, String checksumResult, int checksumByte) {
        StringBuilder sb = new StringBuilder();
        String[] hexToModify = Converter.stringToHexArray(sequenceToCalculate);
        List<String> list = new ArrayList<>(Arrays.asList(hexToModify));
        if (checksumByte <= hexToModify.length - 1) {
            list.add(checksumByte - 1, checksumResult);
        } else {
            list.add(checksumResult);
        }
        hexToModify = list.toArray(hexToModify);
        for (String element : hexToModify) {
            sb.append(element);
        }
        return sb.toString();
    }

    public static String Subtract(String sequenceToCalculate, int startByte, int endByte) {
        String[] hexToCalculate = Converter.stringToHexArray(sequenceToCalculate);
        int result = Converter.hexToDecimal(hexToCalculate[startByte]);
        for (int i = startByte; i <= endByte; i++) {
            if (i == startByte) {
                result = result - Converter.hexToDecimal(hexToCalculate[i + 1]);
                i++;
            } else {
                result = result - Converter.hexToDecimal(hexToCalculate[i]);
            }
        }
        return Converter.decToHexadecimal(result);
    }

    public static String TwosCompliment(String argumentToAdd) {
        return "";
    }


}
