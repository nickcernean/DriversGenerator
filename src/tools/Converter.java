package tools;

import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;

public class Converter {
    public static String decToHexadecimal(int decimalNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(decimalNumber));
        if (sb.length() % 2 != 0) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    public static String[] stringToHexArray(String sequence) {
        String[] hexArray = new String[sequence.length() / 2];
        int index;
        for (int i = 0; i <= hexArray.length - 1; i++) {
            index = i * 2;
            hexArray[i] = sequence.substring(index, index + 2);
        }
        return hexArray;
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

    public static String dataEncoder(String command1, int row, String command2, @Nullable int column, @Nullable String command3, @Nullable char cr, @Nullable char lf) {
        String dataPassed = dataTypeEncoder(command1, String.valueOf(row), command2, String.valueOf(column), command3, cr, lf);
        if (!isHexadecimal(dataPassed)) {
            StringBuilder resultString = new StringBuilder();
            byte[] byteArr = dataPassed.getBytes(StandardCharsets.ISO_8859_1);
            for (byte bytePosition : byteArr) {
                resultString.append(String.format("%02X", bytePosition));
            }
            return resultString.toString();
        } else {
            dataPassed = hexDataTypeEncoder(command1, decToHexadecimal(row), command2, decToHexadecimal(column), command3, cr, lf);
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

    protected static String dataTypeEncoder(String command1, String row, String command2, String column, @Nullable String command3, char cr, char lf) {

        if (Integer.parseInt(column) == -1) {
            if (cr == ' ' && lf == ' ') {
                return command1 + row + command2;
            } else if (lf == ' ') {
                return command1 + row + command2 + cr;
            } else if (cr == ' ') {
                return command1 + row + command2 + lf;
            }
            return command1 + row + command2 + cr + lf;
        } else if (Integer.parseInt(row) == -1) {
            if (cr == ' ' && lf == ' ') {
                return command1 + command2 + column + command3;
            } else if (lf == ' ') {
                return command1 + command2 + column + command3 + cr;
            } else if (cr == ' ') {
                return command1 + command2 + column + command3 + lf;
            }
            return command1 + command2 + column + command3 + cr + lf;
        } else {
            if (cr == ' ' && lf == ' ') {
                return command1 + row + command2 + column + command3;
            } else if (lf == ' ') {
                return command1 + row + command2 + column + command3 + cr;
            } else if (cr == ' ') {
                return command1 + row + command2 + column + command3 + lf;
            }
            return command1 + row + command2 + column + command3 + cr + lf;
        }
    }

    protected static String hexDataTypeEncoder(String command1, String row, String command2, String column, @Nullable String command3, char cr, char lf) {

        if (hexToDecimal(column) == -1) {
            if (cr == ' ' && lf == ' ') {
                return command1 + row + command2;
            } else if (lf == ' ') {
                return command1 + row + command2 + cr;
            } else if (cr == ' ') {
                return command1 + row + command2 + lf;
            }
            return command1 + row + command2 + cr + lf;
        } else if (hexToDecimal(row) == -1) {
            if (cr == ' ' && lf == ' ') {
                return command1 + command2 + column + command3;
            } else if (lf == ' ') {
                return command1 + command2 + column + command3 + cr;
            } else if (cr == ' ') {
                return command1 + command2 + column + command3 + lf;
            }
            return command1 + command2 + column + command3 + cr + lf;
        } else {
            if (cr == ' ' && lf == ' ') {
                return command1 + row + command2 + column + command3;
            } else if (lf == ' ') {
                return command1 + row + command2 + column + command3 + cr;
            } else if (cr == ' ') {
                return command1 + row + command2 + column + command3 + lf;
            }
            return command1 + row + command2 + column + command3 + cr + lf;
        }
    }

    protected static boolean isHexadecimal(String hexadecimalPassed) {
        return hexadecimalPassed.chars().allMatch(hexadecimalSequence -> "0123456789ABCDEFabcdef\r\n ".indexOf(hexadecimalSequence) >= 0);
    }


}
