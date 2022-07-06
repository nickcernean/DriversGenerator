package tools;

import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class ChecksumCalculator {


    public static String Add(String sequenceToCalculate, int startByte, int endByte) {

//        int result = 0;
//        // Traverse the array compute AND
//        result = hexToDecimal((String)sequenceToCalculate.charAt(startByte));
//        for (int i = startByte; i <= endByte - 1; i++) {
//            if (i == startByte) {
//                result = (result & hexToDecimal(sequenceToCalculate.charAt(i+1));
//                i++;
//            }
//            else {
//                result = (result & hexToDecimal(sequenceToCalculate.charAt(i));
//            }
//        }
//        return decToHexadecimal(result);
        return "";
    }

    public static String BitwiseAND(String argumentToAdd, int startByte, int endByte) {
        String binaryRepr = hexToBinary(argumentToAdd);
//        int i = startByte;
//        int j = endByte * 4;
//        String temp = "";
//        long result = 0;
//        while (i <= j) {
//            temp = binaryRepr.substring(i, i + 2);
//            if (i == 0) {
//                result = hexToDec(temp) & Integer.parseInt(binaryRepr.substring(4, 4 + 4));
//                i += 4;
//            } else {
//                result = result & Integer.parseInt(temp);
//            }
//            i += 4;
//        }
        return "";
    }

    public static String CRC_8(String argumentToAdd) {


        return "";
    }

    public static String OnesCompliment(String argumentToAdd) {
        return "";
    }

    public static String BitwiseOR(String argumentToAdd) {
        return "";
    }

    public static String Subtract(String argumentToAdd) {
        return "";
    }

    public static String TwosCompliment(String argumentToAdd) {
        return "";
    }

    private static int hexToDec(char x) {
        String charStr = Character.toString(x).toUpperCase();
        x = charStr.charAt(0);
        switch (x) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;

            case 'D':
                return 13;

            case 'E':
                return 14;
            case 'F':
                return 15;
            default:
                return x - '0';
        }
    }

    public static String decimalToBinary(int decimal) {

        // variable to store the converted
        // binary string
        String binaryString = "";

        // loop to generate the binary
        while (decimal != 0) {

            // concatenating the remainder
            // on dividing by 2 to the
            // binary string
            binaryString = (decimal % 2) + binaryString;

            // updating the decimal integer
            // by dividing by 2 in each iteration
            decimal /= 2;
        }

        // loop to ensure that each
        // Hexadecimal character is
        // represented by 4 bits
        while (binaryString.length() % 4 != 0) {
            // adding leading 0's if the
            // character is represented by less
            // than 4 bits
            binaryString = "0" + binaryString;
        }

        // returning the converted binary string
        return binaryString;
    }

    public static String hexToBinary(String hexadecimal) {

        // declaring the variables
        int i;
        char ch;
        String binary = "";
        int returnedBinary;

        // converting the accepted Hexadecimal
        // String to upper case
        hexadecimal = hexadecimal.toUpperCase();

        // loop to iterate through the length
        // of the Hexadecimal String
        for (i = 0; i < hexadecimal.length(); i++) {

            // extracting the characters
            ch = hexadecimal.charAt(i);

            // condition to check if
            // the character is not a valid Hexadecimal
            // character
            if (!Character.isDigit(ch)
                    && !((int) ch >= 65 && (int) ch <= 70)) {

                // returning Invalid Hexadecimal
                // String for the invalid Hexadecimal
                // character
                binary = "Invalid Hexadecimal String";
                return binary;
            }

            // checking if the character is a valid
            // Hexadecimal alphabet
            else if ((int) ch >= 65 && (int) ch <= 70)

                // converting alphabet to
                // corresponding value such as 10
                // for A and so on using ASCII code
                returnedBinary = (int) ch - 55;
            else
                returnedBinary
                        = Integer.parseInt(String.valueOf(ch));

            // converting the decimal to binary
            // by calling the decimalToBinary() method
            binary += decimalToBinary(returnedBinary);
        }

        // returning the converted binary sequence
        return binary;
    }

    public static int binaryToDecimal(long binary) {

        // variable to store the converted
        // binary number
        int decimalNumber = 0, i = 0;

        // loop to extract the digits of the binary
        while (binary > 0) {

            // extracting the digits by getting
            // remainder on dividing by 10 and
            // multiplying by increasing integral
            // powers of 2
            decimalNumber
                    += Math.pow(2, i++) * (binary % 10);

            // updating the binary by eliminating
            // the last digit on division by 10
            binary /= 10;
        }

        // returning the decimal number
        return decimalNumber;
    }

    // method to convert decimal to hexadecimal
    public static String decimalToHex(long binary) {
        // variable to store the output of the
        // binaryToDecimal() method
        int decimalNumber = binaryToDecimal(binary);

        // converting the integer to the desired
        // hex string using toHexString() method
        String hexNumber
                = Integer.toHexString(decimalNumber);

        // converting the string to uppercase
        // for uniformity
        hexNumber = hexNumber.toUpperCase();

        // returning the final hex string
        return hexNumber;
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
}
