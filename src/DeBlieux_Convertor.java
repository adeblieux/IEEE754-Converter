public class DeBlieux_Convertor {

    /**
     * Converts a given decimal input to its IEEE 754 binary representation
     * for both single-precision (32-bit) and double-precision (64-bit).
     *
     * @param input the decimal number in string format
     * @return the IEEE 754 binary representation in string format
     */
    public static String IEEE754(String input) {
        // Convert input string to double
        double inputNumber = Double.parseDouble(input);
        
        // Separate whole and decimal parts
        int wholeNumber = (int) inputNumber;
        double decimalNumber = inputNumber - wholeNumber;
        
        // Flags to check sign and if the number is less than one
        boolean isNegative = false;
        boolean isLessThanOne = false;

        // StringBuilders for storing formatted binary strings
        StringBuilder format23 = new StringBuilder();
        StringBuilder final32 = new StringBuilder();
        StringBuilder format52 = new StringBuilder();
        StringBuilder final64 = new StringBuilder();

        // Check if the number is a positive decimal less than 1
        if (inputNumber > 0 && inputNumber < 1) {
            isLessThanOne = true;
        } else if (inputNumber < 0) { // Check and handle negative numbers
            isNegative = true;
            wholeNumber = Math.abs(wholeNumber);
            decimalNumber = Math.abs(decimalNumber);
        }

        // Convert whole and decimal parts to binary for 32-bit and 64-bit formats
        final32.append(convertToBinary(wholeNumber, 8)).append(".").append(convertToBinary(decimalNumber, 23));
        final64.append(convertToBinary(wholeNumber, 11)).append(".").append(convertToBinary(decimalNumber, 52));

        // Add sign bit
        if (isNegative) {
            format23.append("1 ");
            format52.append("1 ");
        } else {
            format23.append("0 ");
            format52.append("0 ");
        }

        // Append the shifted exponent and mantissa to the final format
        format23.append(shiftExponent(final32, 8, isLessThanOne));
        format52.append(shiftExponent(final64, 11, isLessThanOne));

        // Return the final 32-bit and 64-bit IEEE 754 representations as a string
        return format23.toString() + "\n" + format52.toString();
    }

    /**
     * Converts an integer to its binary representation.
     *
     * @param input the integer to convert
     * @param bits  the number of bits in the binary representation
     * @return the binary representation as a StringBuilder
     */
    public static StringBuilder convertToBinary(int input, int bits) {
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < bits; i++) {
            if ((input % 2) == 0) { // Add '0' if the remainder is 0
                binaryString.insert(0, "0");
            } else { // Add '1' if the remainder is 1
                binaryString.insert(0, "1");
            }
            input = input / 2; // Update input for the next iteration
        }
        return binaryString;
    }

    /**
     * Converts a decimal number to its binary representation.
     *
     * @param input the decimal number to convert
     * @param bits  the number of bits in the binary representation
     * @return the binary representation as a StringBuilder
     */
    public static StringBuilder convertToBinary(double input, int bits) {
        StringBuilder binaryString = new StringBuilder();
        double wholeNumber;
        for (int i = 0; i < bits; i++) {
            if (((int) (input * 2)) == 0) { // Add '0' if the integer part of input * 2 is 0
                binaryString.append("0");
            } else { // Add '1' otherwise
                binaryString.append("1");
            }
            wholeNumber = (int) (input * 2);
            input = (input * 2) - wholeNumber; // Update input for the next iteration
        }
        return binaryString;
    }

    /**
     * Shifts the exponent and adjusts the binary string according to IEEE 754 format.
     *
     * @param binaryString the binary representation of the number
     * @param bits         the number of bits for the exponent
     * @param isLessThanOne flag indicating if the number is less than one
     * @return the shifted exponent and mantissa as a string
     */
    public static String shiftExponent(StringBuilder binaryString, int bits, boolean isLessThanOne) {
        int decimalPosition = binaryString.indexOf("."); // Find the position of the decimal point
        int firstOne = 0, shiftVar = 0;
        int exponentUnadjusted, exponentAdjusted;

        // Find the position of the first '1'
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                firstOne = i;
                break;
            }
        }

        // Calculate the unadjusted exponent based on the position of the first '1'
        if (!isLessThanOne) {
            exponentUnadjusted = decimalPosition - firstOne - 1;
            shiftVar = 1;
        } else {
            exponentUnadjusted = decimalPosition - firstOne;
        }

        // Adjust the exponent using excess/bias notation
        exponentAdjusted = (int) (exponentUnadjusted + (Math.pow(2, (bits - 1))) - 1);

        // Replace the decimal point in the binary string
        binaryString.deleteCharAt(decimalPosition);
        binaryString.insert(firstOne + shiftVar, ".");

        // Convert the adjusted exponent to binary
        String shiftedString = DeBlieux_Convertor.convertToBinary(exponentAdjusted, bits).toString();
        // Isolate and save the decimals from the combined string
        String shiftedDecimals = binaryString.substring(binaryString.indexOf(".") + 1);

        // Delete excess bits if the number is less than zero
        if (!isLessThanOne) {
            shiftedDecimals = shiftedDecimals.substring(0, shiftedDecimals.length() - exponentUnadjusted);
        } else {
            // Fill lost bits with '0' if the number is less than zero
            for (int i = 0; i > exponentUnadjusted; i--) {
                shiftedDecimals += "0";
            }
        }
        // Return the shifted exponent and mantissa as a string
        return shiftedString + " " + shiftedDecimals;
    }
}
