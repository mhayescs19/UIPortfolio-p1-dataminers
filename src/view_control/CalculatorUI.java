package view_control;

import util.Operation.OPERATOR;
import util.Operation;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class CalculatorUI extends JFrame {
	private final JLabel calcArea = new JLabel("");

	private boolean initialCalcAreaInputState;
	private enum STATE { INITIAL, SAVE1, SAVE2, CALC } // defines the structure of what the STATE enum is

	private STATE mathState;
	
	// calculator values
    private Operation.OPERATOR mathOp;
	private double arg1;
    private double arg2;
    private double calcAnswer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CalculatorUI frame = new CalculatorUI();
				frame.setVisible(true); // inherited from JFrame "magic method"
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private void calculateAnswer()  // method to perform calculation
	{
	    calcAnswer = util.Operation.calculateIt(arg1, mathOp, arg2);
		calcArea.setText(String.valueOf(calcAnswer));
	    arg1 = Double.parseDouble(calcArea.getText());
	    mathState = STATE.CALC;
		initialCalcAreaInputState = true;
	}

	private void oneOpHelper() {
		arg2 = 0;
		calculateAnswer();
	}
	
	private void updateCalcArea(String string) {
		if (initialCalcAreaInputState) {  // sets text to string on initial key typed
			calcArea.setText(string);
			initialCalcAreaInputState = false;
	    } else  {                         // concatenates string to end of text subsequent keys typed
			calcArea.setText(calcArea.getText() + string);
	    }
	}
	
	/**
	 * Save values for Calculator.
	 */
	private void saveValueOfArg1() { // method to store 1st value in calculation (arg1)
	    arg1 = Double.parseDouble((calcArea.getText()));
	    mathState = STATE.SAVE1;
		initialCalcAreaInputState = true;
	}
	
	private void saveValueOfArg2() { // method to store 2nd value in calculation (arg2)
		if (mathState != STATE.CALC) {
			arg2 = Double.parseDouble((calcArea.getText()));
			mathState = STATE.SAVE2;
		}
	}
	
	private void saveValueOfMathOp(OPERATOR op) { // method to store operator
		mathOp = op;
	}

	private void oneArgCalculate() {
		calculateAnswer();
	}
	
	private void clearCalculator() {  // helper method to clear and update calculator to default
		// calculator control
		String calcAreaDefault = "0.0";
		calcArea.setText(calcAreaDefault);
		mathState = STATE.INITIAL;
		initialCalcAreaInputState = true;
		arg1 = 0.0;
		arg2 = 0.0;
		calcAnswer = 0.0;
	}

	/**
	 * Create the frame.
	 */
	public CalculatorUI() { // constructor for main method, makes the buttons "alive"
		// sets up the frame
		getContentPane().setBackground(Color.decode("#4C4C4C"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 340); // -25
		int offset = 25;
		getContentPane().setLayout(null);
		calcArea.setForeground(Color.WHITE);
		calcArea.setBackground(Color.BLACK);
		calcArea.setFont(new Font("San Francisco", Font.PLAIN, 45));
		calcArea.setHorizontalAlignment(SwingConstants.RIGHT);
		calcArea.setBounds(18, 0, 377, 67);
		getContentPane().add(calcArea);
		
		JButton button_1 = new JButton("1"); // text displayed on button
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_1.setBackground(Color.decode("#9E9E9E"));
			} // change color to get an interaction
			@Override
			public void mouseReleased(MouseEvent e) {
				button_1.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_1.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_1.setOpaque(true);
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_1.setBackground(Color.decode("#B4B4B4"));
		button_1.addActionListener(e -> updateCalcArea(button_1.getText()));
		button_1.setBounds(167, 192 + offset, 62, 50);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("2");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_2.setBackground(Color.decode("#9E9E9E"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_2.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_2.addActionListener(e -> updateCalcArea(button_2.getText()));
		button_2.setOpaque(true);
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_2.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_2.setBackground(Color.decode("#B4B4B4"));
		button_2.setBounds(229, 192 + offset, 62, 50);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("3");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_3.setBackground(Color.decode("#9E9E9E"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_3.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_3.addActionListener(e -> updateCalcArea(button_3.getText()));
		button_3.setOpaque(true);
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_3.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_3.setBackground(Color.decode("#B4B4B4"));
		button_3.setBounds(291, 192 + offset, 62, 50);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("4");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_4.setBackground(Color.decode("#9E9E9E"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_4.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_4.addActionListener(e -> updateCalcArea(button_4.getText()));
		button_4.setOpaque(true);
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_4.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_4.setBackground(Color.decode("#B4B4B4"));
		button_4.setBounds(167, 142 + offset, 62, 50);
		getContentPane().add(button_4);
		
		JButton button_5 = new JButton("5");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_5.setBackground(Color.decode("#9E9E9E"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_5.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_5.addActionListener(e -> updateCalcArea(button_5.getText()));
		button_5.setOpaque(true);
		button_5.setForeground(Color.WHITE);
		button_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_5.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_5.setBackground(Color.decode("#B4B4B4"));
		button_5.setBounds(229, 142 + offset, 62, 50);
		getContentPane().add(button_5);
		
		JButton button_6 = new JButton("6");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_6.setBackground(Color.decode("#9E9E9E"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_6.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_6.addActionListener(e -> updateCalcArea(button_6.getText()));
		button_6.setOpaque(true);
		button_6.setForeground(Color.WHITE);
		button_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_6.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_6.setBackground(Color.decode("#B4B4B4"));
		button_6.setBounds(291, 142 + offset, 62, 50);
		getContentPane().add(button_6);
		
		JButton button_7 = new JButton("7");
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_7.setBackground(Color.decode("#9E9E9E"));
			}
			
			public void mouseReleased(MouseEvent e) {
				button_7.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_7.addActionListener(e -> updateCalcArea(button_7.getText()));
		button_7.setOpaque(true);
		button_7.setForeground(Color.WHITE);
		button_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_7.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_7.setBackground(Color.decode("#B4B4B4"));
		button_7.setBounds(167, 92 + offset, 62, 50);
		getContentPane().add(button_7);
		
		JButton button_8 = new JButton("8");
		button_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_8.setBackground(Color.decode("#9E9E9E"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_8.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_8.addActionListener(e -> updateCalcArea(button_8.getText()));
		button_8.setOpaque(true);
		button_8.setForeground(Color.WHITE);
		button_8.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_8.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_8.setBackground(Color.decode("#B4B4B4"));
		button_8.setBounds(229, 92 + offset, 62, 50);
		getContentPane().add(button_8);
		
		JButton button_9 = new JButton("9");
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_9.setBackground(Color.decode("#9E9E9E"));
			}
			
			public void mouseReleased(MouseEvent e) {
				button_9.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_9.addActionListener(e -> updateCalcArea(button_9.getText()));
		button_9.setOpaque(true);
		button_9.setForeground(Color.WHITE);
		button_9.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_9.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_9.setBackground(Color.decode("#B4B4B4"));
		button_9.setBounds(291, 92 + offset, 62, 50);
		getContentPane().add(button_9);
		
		JButton button_0 = new JButton("0");
		button_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_0.setBackground(Color.decode("#9E9E9E"));
			}
			
			public void mouseReleased(MouseEvent e) {
				button_0.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_0.addActionListener(e -> updateCalcArea(button_0.getText()));
		button_0.setOpaque(true);
		button_0.setForeground(Color.WHITE);
		button_0.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_0.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_0.setBackground(Color.decode("#B4B4B4"));
		button_0.setBounds(167, 242 + offset, 124, 50);
		getContentPane().add(button_0);

		JButton button_divide = new JButton("÷");
		button_divide.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_divide.setBackground(Color.decode("#E8AC2D"));
			}

			public void mouseReleased(MouseEvent e) {
				button_divide.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_divide.addActionListener(e -> { //
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.DIVIDE);
		});
		button_divide.setOpaque(true);
		button_divide.setForeground(Color.WHITE);
		button_divide.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_divide.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_divide.setBackground(Color.decode("#FFBD33"));
		button_divide.setBounds(353, 42 + offset, 62, 50);
		getContentPane().add(button_divide);

		JButton button_mult = new JButton("x");
		button_mult.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_mult.setBackground(Color.decode("#E8AC2D"));
			}

			public void mouseReleased(MouseEvent e) {
				button_mult.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_mult.addActionListener(e -> { //
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.MULTIPLY);
		});
		button_mult.setOpaque(true);
		button_mult.setForeground(Color.WHITE);
		button_mult.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_mult.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_mult.setBackground(Color.decode("#FFBD33"));
		button_mult.setBounds(353, 92 + offset, 62, 50);
		getContentPane().add(button_mult);

		JButton button_decimal = new JButton(".");
		button_decimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_decimal.setBackground(Color.decode("#9E9E9E"));
			}

			public void mouseReleased(MouseEvent e) {
				button_decimal.setBackground(Color.decode("#B4B4B4"));
			}
		});
		button_decimal.addActionListener(e -> { //
			updateCalcArea(".");
		});
		button_decimal.setOpaque(true);
		button_decimal.setForeground(Color.WHITE);
		button_decimal.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_decimal.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_decimal.setBackground(Color.decode("#B4B4B4"));
		button_decimal.setBounds(291, 242 + offset, 62, 50);
		getContentPane().add(button_decimal);

		JButton button_plus = new JButton("+");
		button_plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_plus.setBackground(Color.decode("#E8AC2D"));
			}
			
			public void mouseReleased(MouseEvent e) {
				button_plus.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_plus.addActionListener(e -> { //
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.PLUS);
		});
		button_plus.setOpaque(true);
		button_plus.setForeground(Color.WHITE);
		button_plus.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_plus.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_plus.setBackground(Color.decode("#FFBD33"));
		button_plus.setBounds(353, 192 + offset, 62, 50);
		getContentPane().add(button_plus);
		
		JButton button_minus = new JButton("-");
		button_minus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_minus.setBackground(Color.decode("#E8AC2D"));
			}
			
			public void mouseReleased(MouseEvent e) {
				button_minus.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_minus.addActionListener(e -> {
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.MINUS);
		});
		button_minus.setOpaque(true);
		button_minus.setForeground(Color.WHITE);
		button_minus.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_minus.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_minus.setBackground(Color.decode("#FFBD33"));
		button_minus.setBounds(353, 142 + offset, 62, 50);
		getContentPane().add(button_minus);

		JButton button_LOG = new JButton("log");
		button_LOG.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_LOG.setBackground(Color.decode("#E8AC2D"));
			}

			public void mouseReleased(MouseEvent e) {
				button_LOG.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_LOG.addActionListener(e -> { //
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.LOG);
			oneOpHelper();
		});
		button_LOG.setOpaque(true);
		button_LOG.setForeground(Color.WHITE);
		button_LOG.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_LOG.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_LOG.setBackground(Color.decode("#FFBD33"));
		button_LOG.setBounds(105, 92 + offset, 62, 50);
		getContentPane().add(button_LOG);

		JButton button_square = new JButton("<html>x<small style='font-size: 15px';><sup>2</sup></small></html>");
		button_square.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_square.setBackground(Color.decode("#E8AC2D"));
			}

			public void mouseReleased(MouseEvent e) {
				button_square.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_square.addActionListener(e -> { //
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.SQ);
			oneOpHelper();
		});
		button_square.setOpaque(true);
		button_square.setForeground(Color.WHITE);
		button_square.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_square.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_square.setBackground(Color.decode("#FFBD33"));
		button_square.setBounds(43, 92 + offset, 62, 50);
		getContentPane().add(button_square);

		JButton button_powerY = new JButton("<html>x<small style='font-size: 15px';><sup>y</sup></small></html>");
		button_powerY.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_powerY.setBackground(Color.decode("#E8AC2D"));
			}

			public void mouseReleased(MouseEvent e) {
				button_powerY.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_powerY.addActionListener(e -> { //
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.POWERy);
		});
		button_powerY.setOpaque(true);
		button_powerY.setForeground(Color.WHITE);
		button_powerY.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_powerY.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_powerY.setBackground(Color.decode("#FFBD33"));
		button_powerY.setBounds(43, 142 + offset, 62, 50);
		getContentPane().add(button_powerY);

		JButton button_SQRTy = new JButton("<html><small style='font-size: 15px';><sup>x</sup></small>√y</html>"); // HTML code for subscript
		button_SQRTy.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_SQRTy.setBackground(Color.decode("#E8AC2D"));
			}

			public void mouseReleased(MouseEvent e) {
				button_SQRTy.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_SQRTy.addActionListener(e -> {
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.SQRTy);
		});
		button_SQRTy.setOpaque(true);
		button_SQRTy.setForeground(Color.WHITE);
		button_SQRTy.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		button_SQRTy.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_SQRTy.setBackground(Color.decode("#FFBD33"));
		button_SQRTy.setBounds(105, 192 + offset, 62, 50);
		getContentPane().add(button_SQRTy);

		JButton button_SQRT = new JButton("√x"); // HTML code for subscript
		button_SQRT.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_SQRTy.setBackground(Color.decode("#E8AC2D"));
			}

			public void mouseReleased(MouseEvent e) {
				button_SQRTy.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_SQRT.addActionListener(e -> {
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.SQRT);
			oneOpHelper();
		});
		button_SQRT.setOpaque(true);
		button_SQRT.setForeground(Color.WHITE);
		button_SQRT.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		button_SQRT.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_SQRT.setBackground(Color.decode("#FFBD33"));
		button_SQRT.setBounds(43, 192 + offset, 62, 50);
		getContentPane().add(button_SQRT);

		JButton button_LOGxY = new JButton("<html>log<small style='font-size: 13px';><sub>x</sub></small>y</html>"); // HTML code for subscript
		button_LOGxY.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_LOGxY.setBackground(Color.decode("#E8AC2D"));
			}

			public void mouseReleased(MouseEvent e) {
				button_LOGxY.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_LOGxY.addActionListener(e -> {
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.LOGy);
		});
		button_LOGxY.setOpaque(true);
		button_LOGxY.setForeground(Color.WHITE);
		button_LOGxY.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		button_LOGxY.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_LOGxY.setBackground(Color.decode("#FFBD33"));
		button_LOGxY.setBounds(105, 142 + offset, 62, 50);
		getContentPane().add(button_LOGxY);

		JButton button_equals = new JButton("=");
		button_equals.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_equals.setBackground(Color.decode("#E8AC2D"));
			}
			
			public void mouseReleased(MouseEvent e) {
				button_equals.setBackground(Color.decode("#FFBD33"));
			}
		});
		button_equals.addActionListener(e -> {
			saveValueOfArg2();
			calculateAnswer();
		});
		button_equals.setOpaque(true);
		button_equals.setForeground(Color.WHITE);
		button_equals.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_equals.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_equals.setBackground(Color.decode("#FFBD33"));
		button_equals.setBounds(353, 242 + offset, 62, 50);
		getContentPane().add(button_equals);
		
		JButton button_clear = new JButton("AC");
		button_clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_clear.setBackground(Color.decode("#878787"));
			}
			
			public void mouseReleased(MouseEvent e) {
				button_clear.setBackground(Color.decode("#787777"));
			}
		});
		button_clear.addActionListener(e -> clearCalculator());
		button_clear.setOpaque(true);
		button_clear.setForeground(Color.WHITE);
		button_clear.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_clear.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_clear.setBackground(Color.decode("#787777"));
		button_clear.setBounds(167, 42 + offset, 62, 50);
		getContentPane().add(button_clear);

		JButton button_posneg = new JButton("+/-");
		button_posneg.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_posneg.setBackground(Color.decode("#878787"));
			}

			public void mouseReleased(MouseEvent e) {
				button_posneg.setBackground(Color.decode("#787777"));
			}
		});
		button_posneg.addActionListener(e -> {
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.POSNEG);
			oneOpHelper();
		});
		button_posneg.setOpaque(true);
		button_posneg.setForeground(Color.WHITE);
		button_posneg.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		button_posneg.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_posneg.setBackground(Color.decode("#787777"));
		button_posneg.setBounds(229, 42 + offset, 62, 50);
		getContentPane().add(button_posneg);

		JButton button_fraction = new JButton("<html>1<p>━</p>x</html>");
		button_fraction.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_fraction.setBackground(Color.decode("#878787"));
			}

			public void mouseReleased(MouseEvent e) {
				button_fraction.setBackground(Color.decode("#787777"));
			}
		});
		button_fraction.addActionListener(e -> {
			saveValueOfArg1();
			saveValueOfMathOp(OPERATOR.FRACTION);
			oneOpHelper();
		});
		button_fraction.setOpaque(true);
		button_fraction.setForeground(Color.WHITE);
		button_fraction.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		button_fraction.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		button_fraction.setBackground(Color.decode("#787777"));
		button_fraction.setBounds(291, 42 + offset, 62, 50);
		getContentPane().add(button_fraction);
		
		JLabel label_title = new JLabel("PEGG, HAYES");
		label_title.setBounds(6, 6, 134, 16);
		label_title.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		label_title.setForeground(Color.WHITE);

		getContentPane().add(label_title);

	}
}






	
