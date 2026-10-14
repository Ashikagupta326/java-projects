package com.example.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** A two-number calculator that also keeps a session history. */
public class CalculatorApp extends JFrame implements ActionListener {
    private final JTextField firstNumber = new JTextField();
    private final JTextField secondNumber = new JTextField();
    private final JLabel resultLabel = new JLabel("Result: —");
    private final JTextArea historyArea = new JTextArea(7, 28);
    private final JButton addButton = new JButton("ADD");
    private final JButton subtractButton = new JButton("SUBTRACT");
    private final JButton multiplyButton = new JButton("MULTIPLY");
    private final JButton divideButton = new JButton("DIVIDE");
    private final JButton clearButton = new JButton("CLEAR INPUTS");
    private final JButton clearHistoryButton = new JButton("CLEAR HISTORY");
    private final JButton exitButton = new JButton("EXIT");

    public CalculatorApp() {
        setTitle("Everyday Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(440, 510);
        setLocationRelativeTo(null);

        JPanel inputs = new JPanel(new GridLayout(2, 2, 8, 8));
        inputs.setBorder(BorderFactory.createTitledBorder("Numbers"));
        inputs.add(new JLabel("First number:"));
        inputs.add(firstNumber);
        inputs.add(new JLabel("Second number:"));
        inputs.add(secondNumber);

        JPanel operations = new JPanel(new GridLayout(2, 2, 8, 8));
        operations.setBorder(BorderFactory.createTitledBorder("Operation"));
        operations.add(addButton);
        operations.add(subtractButton);
        operations.add(multiplyButton);
        operations.add(divideButton);

        JPanel top = new JPanel(new BorderLayout(8, 8));
        top.add(inputs, BorderLayout.NORTH);
        top.add(operations, BorderLayout.CENTER);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 16f));
        top.add(resultLabel, BorderLayout.SOUTH);

        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBorder(BorderFactory.createTitledBorder("Calculation history"));

        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomButtons.add(clearButton);
        bottomButtons.add(clearHistoryButton);
        bottomButtons.add(exitButton);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        content.add(top, BorderLayout.NORTH);
        content.add(historyScroll, BorderLayout.CENTER);
        content.add(bottomButtons, BorderLayout.SOUTH);
        setContentPane(content);

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        clearButton.addActionListener(this);
        clearHistoryButton.addActionListener(this);
        exitButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == exitButton) {
            dispose();
            return;
        }
        if (source == clearButton) {
            firstNumber.setText("");
            secondNumber.setText("");
            resultLabel.setText("Result: —");
            return;
        }
        if (source == clearHistoryButton) {
            historyArea.setText("");
            return;
        }

        try {
            double a = Double.parseDouble(firstNumber.getText().trim());
            double b = Double.parseDouble(secondNumber.getText().trim());
            String symbol;
            double result;

            if (source == addButton) {
                symbol = "+"; result = a + b;
            } else if (source == subtractButton) {
                symbol = "−"; result = a - b;
            } else if (source == multiplyButton) {
                symbol = "×"; result = a * b;
            } else if (source == divideButton) {
                if (b == 0.0) {
                    JOptionPane.showMessageDialog(this, "The second number cannot be zero when dividing.",
                            "Division error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                symbol = "÷"; result = a / b;
            } else {
                return;
            }

            String formatted = String.format("%s %s %s = %s",
                    format(a), symbol, format(b), format(result));
            resultLabel.setText("Result: " + format(result));
            historyArea.append(formatted + System.lineSeparator());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number in both fields.",
                    "Invalid input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static String format(double value) {
        if (Double.isFinite(value) && value == Math.rint(value)) {
            return String.format("%.0f", value);
        }
        return Double.toString(value);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorApp::new);
    }
}
