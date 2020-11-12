/*

    Created by Michael Hayes with additions by Andrew Pegg
    2020

 */

package control_calculator;

import util.Operation;
import util.Operation.OPERATOR;
import view_control.CalculatorUI;

public class Calculate {

    /*
      Calculate Object Definitions
     */
    public boolean initialCalcAreaInputState;

    private enum STATE {INITIAL, SAVE1, SAVE2, CALC} // defines the structure of what the STATE enum is

    private STATE mathState;

    private Operation.OPERATOR mathOp;
    private double arg1;
    private double arg2;

    private double calcAnswer;
    private String calcArea;

    public CalculatorUI view;

    public Calculate() {

        this.view = new CalculatorUI(this);
        view.setVisible(true);

    }

    public double getArg1() {
        return this.arg1;
    }

    public double getArg2() {
        return this.arg2;
    }

    public String getterCalcArea() {
        return this.calcArea;
    }

    public void setCalcArea(String currentDisplay) { // due to MVC, method grabs value from calculator view (might be not entirely control)
        this.calcArea = currentDisplay;
    }

    public String getCalcArea() { // returns calcArea which updates as new numbers are added or an answer is calculated
        return this.calcArea;
    }

    public void calculateAnswer() {
        calcAnswer = util.Operation.calculateIt(arg1, mathOp, arg2);
        calcArea = String.valueOf(calcAnswer);
        arg1 = Double.parseDouble(calcArea);
        mathState = Calculate.STATE.CALC;
        initialCalcAreaInputState = true;
    }

    public void oneOpHelper() { // operation helper to evaluate first arg only after one value math function is applied
        arg2 = 0;
        calculateAnswer();
    }

    public void saveValueOfMathOp(OPERATOR op) { // method to store operator
        this.mathOp = op;
    }

    public void updateCalcArea(String input) { // attempted to move into view (CalculatorUI) but was experiencing issues; this view code is in control
        if (initialCalcAreaInputState) {
            calcArea = input;
            initialCalcAreaInputState = false;
        } else {
            calcArea = calcArea + input;
        }
    }

    public void clearCalculator() {  // helper method to clear and update calculator to default
        // calculator control
        String calcAreaDefault = "0.0";
        calcArea = calcAreaDefault;
        mathState = Calculate.STATE.INITIAL;
        initialCalcAreaInputState = true;
        arg1 = 0.0;
        arg2 = 0.0;
        calcAnswer = 0.0;
    }

    /**
     * Save values for Calculator.
     */
    public void saveValueOfArg1() { // method to store 1st value in calculation (arg1)
        arg1 = Double.parseDouble(calcArea);
        mathState = Calculate.STATE.SAVE1;
        initialCalcAreaInputState = true;
    }

    public void saveValueOfArg2() { // method to store 2nd value in calculation (arg2)
        if (mathState != Calculate.STATE.CALC) {
            arg2 = Double.parseDouble(calcArea);
            mathState = Calculate.STATE.SAVE2;
        }
    }

    public static void main(String[] args) {
        new Calculate();
    }
}
