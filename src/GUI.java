import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    public static void main(String[] args) {
        // Create a new JPanel
        JPanel panel = new JPanel();
        
        // Create a new JFrame and set its properties
        JFrame frame = new JFrame("IEEE-754 Convertor");
        frame.setSize(500, 300); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        frame.add(panel); // Add the panel to the frame

        panel.setLayout(null); // Set the layout manager to null

        // Create and configure the title label
        JLabel titleLabel = new JLabel("IEEE-754 Convertor");
        titleLabel.setBounds(150, 10, 200, 25); // Set the position and size of the title label
        panel.add(titleLabel); // Add the title label to the panel

        // Create and configure the input label
        JLabel label = new JLabel("Input: ");
        label.setBounds(10, 50, 80, 25); // Set the position and size of the input label
        panel.add(label); // Add the input label to the panel

        // Create and configure the text field for user input
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 50, 250, 25); // Set the position and size of the text field
        panel.add(userText); // Add the text field to the panel

        // Create and configure the button
        JButton button = new JButton("Run");
        button.setBounds(360, 50, 80, 25); // Set the position and size of the button
        panel.add(button); // Add the button to the panel

        // Create and configure the label for output
        JLabel outputLabel = new JLabel("");
        outputLabel.setBounds(10, 100, 460, 150); // Set the position and size of the output label
        panel.add(outputLabel); // Add the output label to the panel

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input from the text field
                String input = userText.getText();
                
                // Convert the input using the IEEE754 method from the Convertor class
                String output = DeBlieux_Convertor.IEEE754(input);
                
                // Set the output text, differentiate 23-bit and 52-bit mantissa, and display on separate lines
                String[] outputs = output.split("\n");
                String formattedOutput = "<html><div style='text-align: left;'>"
                        + "23-bit: " + "<br>" + outputs[0] + "<br>" + "<br>"
                        + "52-bit: " + "<br>" + outputs[1] + "</div></html>";
                outputLabel.setText(formattedOutput);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
