import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Main module to start the Property tax class.
class Tax {
    public static void main(String[] args) {

        //Create Property Tax instance.
        new PropertyTax();
    }
}

//This class calculates property tax based off user input.

public class PropertyTax extends JFrame {
    //Declare components
    private JLabel assessmentLabel;
    private JLabel assessmentValueLabel;
    private JLabel valueLabel;
    private JLabel taxLabel;
    private JLabel totalTaxLabel;

    private JTextField valueField;

    private JButton calcButton;
    private JButton exitButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    //Constructor
    public PropertyTax() {

        //Title of Window
        setTitle("Property Tax Calculator");

        //Exit button action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Label component creator
        valueLabel = new JLabel("What is the current value of your property? $");
        assessmentLabel = new JLabel("The Assessment Value of the property is: $");
        assessmentValueLabel = new JLabel("");
        taxLabel = new JLabel("The total tax is: $");
        totalTaxLabel = new JLabel(" ");

        //Text components
        valueField = new JTextField(5);

        //Create buttons.
        calcButton = new JButton("Calculate Property Tax");
        exitButton = new JButton("Exit");

        //Button Listeners
        calcButton.addActionListener(new CalcButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        //Panels to position/arrange the components
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();


        //Panel 1
        panel1.add(valueLabel);
        panel1.add(valueField);

        //Panel 2
        panel2.add(assessmentLabel);
        panel2.add(assessmentValueLabel);

        //Panel 3
        panel3.add(taxLabel);
        panel3.add(totalTaxLabel);

        //Panel 4
        panel4.add(calcButton);
        panel4.add(exitButton);

        setLayout(new GridLayout(5,1));

        //Add panels to the window
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        pack();
        setVisible(true);
    }
    public class CalcButtonListener implements ActionListener {

        //Will execute when calcButton is clicked.
        @Override
        public void actionPerformed(ActionEvent e) {
            //Local variables
            double value, assessmentRate, taxRate, assessmentCalc, taxCalc;

            value = Double.parseDouble(valueField.getText());

            assessmentRate = .6;
            taxRate = .0064;

            //Calculate and display the Assessment of the property to the user.
            assessmentCalc = (value * assessmentRate);
            assessmentValueLabel.setText(Double.toString(assessmentCalc));

            //Calculate and display the tax to the user.
            taxCalc = (assessmentCalc * taxRate);
            totalTaxLabel.setText(Double.toString(taxCalc));

        }
    }

    public class ExitButtonListener implements ActionListener {

        //Will execute when exitButton is clicked.
        public void actionPerformed(ActionEvent e) {

            //Close the application.
            System.exit(0);
        }
    }
}
