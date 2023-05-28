import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class NumberGuessingGame {
    JFrame frame = new JFrame("Guess the number");
    JLabel label, label1, label2;
    JTextField textField = new JTextField(10);
    JPanel panel = new JPanel();

    JPanel panel1 = new JPanel();
    int score = 0;
    int attempts = 5;
    int rand;

    NumberGuessingGame() {

        rand = generateNumber();
        System.out.println(rand);
        label = new JLabel("Guess a Number : ");
        label.setBounds(10, 65, 120, 20);
        textField.setBounds(120, 65, 120, 20);
        frame.add(label);
        frame.add(textField);

        label1 = new JLabel(String.format("Score: %d ", (score)));

        label2 = new JLabel(String.format("Attempts: %d ", (attempts)));
        panel1.add(label1);
        panel1.add(label2);

        frame.setSize(350, 200);
        frame.add(panel);
        frame.add(panel1, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    int scoring(int a) {
        return (a + 1) * 5;
    }

    void setAction() {
        textField.addActionListener(e -> {

            if (Integer.parseInt(textField.getText()) > 100 || Integer.parseInt(textField.getText()) <= 0) {
                JOptionPane.showMessageDialog(frame, "Your number should be in between 1-100", "Error",
                        JOptionPane.ERROR_MESSAGE);
                textField.setText("");
                return;
            }
            if (Objects.equals(textField.getText(), "")) {
                JOptionPane.showMessageDialog(frame, "Enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            attempts--;
            label2.setText(String.format("Attempts: %d ", (attempts)));
            if (rand == Integer.parseInt(textField.getText())) {

                score += scoring(attempts);
                label1.setText(String.format("Score: %d ", (score)));

                Object[] options = { "Next Number" };
                int n = JOptionPane.showOptionDialog(frame,
                        "Correct choice",
                        "Success",
                        JOptionPane.YES_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (n == 0) {
                    rand = generateNumber();
                    attempts = 5;
                    label2.setText(String.format("Attempts: %d ", (attempts)));
                }

                textField.setText("");

            } else if (rand < Integer.parseInt(textField.getText())) {
                JOptionPane.showMessageDialog(frame,
                        String.format("Correct number is smaller than %d", Integer.parseInt(textField.getText())),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                textField.setText("");

            } else if (rand > Integer.parseInt(textField.getText())) {
                JOptionPane.showMessageDialog(frame,
                        String.format("Correct number is greater than %d", Integer.parseInt(textField.getText())),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                textField.setText("");
            }
            if (attempts == 0) {
                Object[] options = { "New Game",
                        "Try again" };
                int n = JOptionPane.showOptionDialog(frame,
                        "Attempts over!",
                        "Success",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[0]);

                score = 0;
                label1.setText(String.format("Score: %d ", (score)));
                textField.setText("");

                if (n == 0) {
                    attempts = 5;
                    label2.setText(String.format("Attempts: %d ", (attempts)));
                    rand = generateNumber();
                } else {
                    attempts = 1;
                    label2.setText(String.format("Attempts: %d ", (attempts)));

                }
            }
        });
    }

    int generateNumber() {
        int range = 100 - 1 + 1;
        return (int) (Math.random() * range) + 1;
    }

    public static void main(String[] args) {
        NumberGuessingGame numberGuessingGame = new NumberGuessingGame();
        numberGuessingGame.setAction();

    }
}
