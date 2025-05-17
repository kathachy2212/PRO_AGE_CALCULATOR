import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class AgeCalculator implements ActionListener {
    JFrame frame;
    JLabel birthdateLabel, resultLabel, currentDateLabel;
    JTextField birthdateField, customDateField;
    JButton calculateButton;
    Font font;
    JPanel panel;

    AgeCalculator() {
        // Set up the frame
        frame = new JFrame("Age Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(400, 380); // Increased size for custom date field
        frame.setLayout(null);

        // Font for the labels and buttons
        font = new Font("Arial", Font.PLAIN, 16);

        // Set up the birthdate label and text field
        birthdateLabel = new JLabel("Enter your Birthdate (YYYY-MM-DD):");
        birthdateLabel.setFont(font);
        birthdateLabel.setBounds(50, 50, 300, 30);
        frame.add(birthdateLabel);

        birthdateField = new JTextField();
        birthdateField.setFont(font);
        birthdateField.setBounds(50, 90, 250, 30);
        frame.add(birthdateField);

        // Set up the custom date label and text field (optional)
        currentDateLabel = new JLabel("Enter a Custom Date (YYYY-MM-DD):");
        currentDateLabel.setFont(font);
        currentDateLabel.setBounds(50, 130, 300, 30);
        frame.add(currentDateLabel);

        customDateField = new JTextField();
        customDateField.setFont(font);
        customDateField.setBounds(50, 170, 250, 30);
        frame.add(customDateField);

        // Set today's date as the default value in the custom date field
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        customDateField.setText(LocalDate.now().format(formatter));

        // Set up the calculate button
        calculateButton = new JButton("Calculate Age");
        calculateButton.setFont(font);
        calculateButton.setBounds(50, 210, 150, 40);
        calculateButton.setBackground(new Color(50, 150, 255));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(this);
        frame.add(calculateButton);

        // Set up the result label
        resultLabel = new JLabel("");
        resultLabel.setFont(font);
        resultLabel.setBounds(50, 260, 300, 30);
        frame.add(resultLabel);

        // Set the background color
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        // Make the frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            String birthdate = birthdateField.getText();
            String customDate = customDateField.getText();
            LocalDate currentDate;

            try {
                // Parse the birthdate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = LocalDate.parse(birthdate, formatter);

                // Determine the date to use (custom date or current date)
                if (customDate.isEmpty()) {
                    // Use current date if custom date field is empty
                    currentDate = LocalDate.now();
                } else {
                    // Parse the custom date if provided
                    currentDate = LocalDate.parse(customDate, formatter);
                }

                // Calculate the age
                Period period = Period.between(birthDate, currentDate);
                int age = period.getYears();
                int day = period.getDays();
                int month = period.getMonths();

                // Display the result
                resultLabel.setText("Your age is: " + age + " years " + month + " months " + day + " days.");
                resultLabel.setForeground(new Color(50, 150, 50));  // Set the result color to green
            } catch (Exception ex) {
                // Handle invalid input
                resultLabel.setText("Invalid input! Use YYYY-MM-DD format.");
                resultLabel.setForeground(Color.RED);
            }
        }
    }

   
}