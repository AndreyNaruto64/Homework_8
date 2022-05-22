import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Calculator extends JFrame {

    private Double leftOperand;
    private String operation;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 325, 450);
        setLayout(new BorderLayout());


        JLabel display = new JLabel("0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Time New Roman", Font.BOLD, 48));
        add(display, BorderLayout.CENTER);

        ActionListener numberListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String text = button.getText();
                String displayText = display.getText();

                if (".".equals(text) && displayText.contains(".")) {
                    return;
                }

                if ("0".equals(displayText) && !".".equals(text)) {
                    displayText = "";
                }


                displayText += text;
                display.setText(displayText);
            }
        };

        ActionListener buttonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String action = source.getText();
                Double rightOperand = Double.parseDouble(display.getText());

                if("=".equals(action)){
                    if (leftOperand != null) {

                        switch (operation){
                            case "+":
                                display.setText(String.valueOf(leftOperand+rightOperand));
                                break;
                            case "-":
                                display.setText(String.valueOf(leftOperand-rightOperand));
                                break;
                            case "*":
                                display.setText(String.valueOf(leftOperand*rightOperand));
                                break;
                            case "/":
                                display.setText(String.valueOf(leftOperand/rightOperand));
                                break;
                            case "%":
                                display.setText(String.valueOf(leftOperand%rightOperand));
                                break;
                            case "2":
                                display.setText(String.valueOf(leftOperand*leftOperand));
                                break;
                            case "3":
                                display.setText(String.valueOf(leftOperand*leftOperand*leftOperand));
                                break;
                            case "S":
                                display.setText(String.valueOf(sqrt(leftOperand)));
                                break;
                            case "A":
                                display.setText(String.valueOf(abs(leftOperand)));
                                break;
                            case "R":
                                display.setText(String.valueOf(-(leftOperand)));
                                break;




                        }
                        leftOperand=Double.parseDouble((display.getText()));
                        operation=null;
                    }
                    return;
                }
                leftOperand=Double.parseDouble((display.getText()));
                operation=action;
                display.setText("0");

            }
        };

        JPanel numberPanel = new JPanel();
        GridLayout numberLayout = new GridLayout(4, 4, 30, 20);
        numberPanel.setLayout(numberLayout);

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(numberListener);
            numberPanel.add(button);

            if (i == 9){
                button = new JButton(String.valueOf("0"));
                button.addActionListener(numberListener);
                numberPanel.add(button);
            }




        }





        JButton pointButton = new JButton(".");
        pointButton.addActionListener(numberListener);

        numberPanel.add(pointButton);


        JPanel buttonPanel = new JPanel();
        GridLayout buttonLayout = new GridLayout(2, 3, 10, 10);
        buttonPanel.setLayout(buttonLayout);




        for (char c : "C+-*/=%23SAR".toCharArray()) {

            JButton button = new JButton(String.valueOf(c));
            button.addActionListener(buttonListener);
            buttonPanel.add(button);
        }
        add(numberPanel, BorderLayout.SOUTH);
        add(buttonPanel,BorderLayout.NORTH);
        setVisible(true);


    }

    public static void main(String[] args) {
        new Calculator();
    }
}
