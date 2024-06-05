# IEEE-754 Converter
---
[![IEEE 754 Single Floating Point Format](http://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/IEEE_754_Single_Floating_Point_Format.svg/2000px-IEEE_754_Single_Floating_Point_Format.svg.png)](http://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/IEEE_754_Single_Floating_Point_Format.svg/2000px-IEEE_754_Single_Floating_Point_Format.svg.png)

### Overview

The IEEE-754 Converter is a simple Java application that converts decimal numbers into their IEEE-754 floating-point representation. It supports both 32-bit and 64-bit formats, providing insights into the binary representation of floating-point numbers. No built-in java functions for converting a number into binary were used.

### How to Run

1. **Prerequisites:**
    - Ensure you have [Java Development Kit (JDK)](https://openjdk.org/) installed on your system.

2. **Download the Source Code:**
    - Clone the repository from GitHub:
      ```
      git clone https://github.com/adeblieux/IEEE754-Converter.git
      ```

3. **Run the Application:**
    - After compiling, run the GUI class to launch the application:
      ```
      java GUI
      ```

4. **Usage:**
    - Enter a decimal number into the input field.
      ![Starting Screen for the GUI](/img/Input.png)
    - Click the "Run" button to convert the number into IEEE-754 format.
      ![Output Screen for the GUI](/img/Output.png)
    - The converted binary representation will be displayed in the output area, showing both 23-bit and 52-bit mantissas.

### Example Outputs:
Input: 19.59375 <br>
23-bit: 0 10000011 00111001100000000000000 <br>
52-bit: 0 10000000011 0011100110000000000000000000000000000000000000000000 <br>

Input: 0.085 <br>
23-bit: 0 01111011 01011100001010001110000 <br>
52-bit: 0 01111111011 0101110000101000111101011100001010001111010111000000 <br>

Input: -73.40<br>
23-bit: 1 10000101 00100101100110011001100<br>
52-bit: 1 10000000101 0010010110011001100110011001100110011001100110011010 <br>

### Features

- Converts decimal numbers into IEEE-754 floating-point format.
- Supports both 32-bit and 64-bit representations.
- User-friendly graphical interface for easy interaction.
- Provides detailed insights into the binary representation of floating-point numbers.

### Resources

For more information on IEEE-754 floating-point format, you can visit [this website](https://chortle.ccsu.edu/AssemblyTutorial/Chapter-30/ass30_01.html).

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to contribute, report issues, or provide feedback to improve this project!
