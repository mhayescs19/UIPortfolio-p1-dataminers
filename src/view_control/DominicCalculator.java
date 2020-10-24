package view_control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

// Specifically designed for a JButton; takes a target color and time and interpolates the current color of the Button to the desired color in the specified time
class TweenColor implements Runnable {
	private final Thread InternalThread;
	private final float DesiredTime;
	private final Color StartColor;
	private final Color DesiredColor;
	private final JButton Object;

	// Static: used to keep track of multiple threads that are running on the same object so that color tweening does not conflict (smooth!)
	private static final HashMap<JButton, Thread> Threads = new HashMap<>();

	public TweenColor(JButton Object, Color DesiredColor, long DesiredTime) {
		this.Object = Object;
		this.DesiredTime = DesiredTime;
		this.DesiredColor = DesiredColor;
		StartColor = Object.getBackground();
		InternalThread = new Thread(this);

		// Write the new thread
		Threads.put(Object, InternalThread);
		InternalThread.start();
	}

	private Color Interpolate(double Fraction) {
		// Formula: (end - start) * step + start; where step ranges from 0 to 1.0
		double R = (DesiredColor.getRed() - StartColor.getRed()) * Fraction + StartColor.getRed();
		double G = (DesiredColor.getGreen() - StartColor.getGreen()) * Fraction + StartColor.getGreen();
		double B = (DesiredColor.getBlue() - StartColor.getBlue()) * Fraction + StartColor.getBlue();
		return new Color((int)R, (int)G, (int)B);
	}

	public void run() {
		// The number of steps is always the same: 100; how long we wait depends on the desired time specified in the constructor
		for (double Step = 0; Step <= 1; Step += 0.01) {
			Object.setBackground(Interpolate(Step));
			try {
				// Step time: x * 100 = DesiredTime
				Thread.sleep((long)(DesiredTime / 100));
			} catch (Exception ignored) {}

			// Old threads end here if the new one takes charge
			if (Threads.get(Object) != InternalThread) {
				break;
			}
		}

	}
}

enum OP { ADD, SUB, MULT, DIV }

// Does the actual computation
class OpManager {
	private double Number1;
	private double Number2;
	private double Result;
	private OP Operation;

	// UpperText: the "history" like (150 + 5 =)
	// LowerText: the current input/result
	private final JLabel UpperText;
	private final JLabel LowerText;

	private String Input = "0";
	private boolean DidUseDecimal = false;	// Avoid multiple decimal points
	private boolean ClearNextPress = false;	// Signals that we need to clear the input upon the next press (e.g. we type 123 * --> and then press 5; we need to clear the 123 and replace it with a 5)
	private boolean ClearUpperTextNextPress = false;

	OpManager(JLabel UpperText, JLabel LowerText) {
		this.UpperText = UpperText;
		this.LowerText = LowerText;
	}

	// Value can only be a number between 0-9 or a decimal point
	void AppendValue(String Value) {
		if (ClearUpperTextNextPress) {
			ClearUpperTextNextPress = false;	// when we start a new operation, we'll clear the "history" which is the uppertext
			UpperText.setText("");
		}
		if (Value.equals(".") && !DidUseDecimal) {
			if (ClearNextPress) {
				Input = "0.";
			} else {
				Input += ".";
			}
			DidUseDecimal = true;
			ClearNextPress = false;
		} else if ((Value.equals("0") && !Input.equals("0")) || (!Value.equals(".") && !Value.equals("0"))) {
			if (ClearNextPress || Input.equals("0")) {
				Input = Value;
			} else {
				Input += Value;
			}
			ClearNextPress = false;
		}
		UpdateLowerText();
	}

	void DeleteValue() {
		if (Input.length() == 1) {
			Input = "0";
		} else {
			Input = Input.substring(0, Input.length() - 1);
		}
		UpdateLowerText();
	}

	void ClearCurrentInput() {
		Input = "0";
		DidUseDecimal = false;
		UpdateLowerText();
		if (ClearUpperTextNextPress) {
			ClearUpperTextNextPress = false;
			UpperText.setText("");
		}
	}

	void ClearAll() {
		ClearCurrentInput();
		Operation = null;
		UpperText.setText("");
	}

	void UpdateLowerText() {
		LowerText.setText(Input);
	}

	void SetOperation(OP DesiredOp) {
		if (Operation == null || ClearNextPress) {
			ClearUpperTextNextPress = false;
			Operation = DesiredOp;
			// Save current number
			Number1 = Double.parseDouble(Input);
			String OpStr = switch(DesiredOp) {
				case ADD -> "+";
				case DIV -> "÷";
				case SUB -> "−";
				case MULT -> "×";
			};
			UpperText.setText(Number1 + " " + OpStr);

			// Reset values
			DidUseDecimal = false;
			ClearNextPress = true;
		}

	}

	void FlipSign() {
		if (!Input.equals("0")) {
			if (Input.charAt(0) == '-') {
				Input = Input.substring(1);
			} else {
				Input = "-" + Input;
			}
		}
		UpdateLowerText();
	}

	void Calculate() {
		if (Operation != null) {
			Number2 = Double.parseDouble(Input);
			UpperText.setText(UpperText.getText() + " " + Number2);
			Result = switch (Operation) {
				case ADD -> (Number1 + Number2);
				case SUB -> (Number1 - Number2);
				case MULT -> (Number1 * Number2);
				case DIV -> (Number1 / Number2);
			};

			Input = String.valueOf(Result);
			DidUseDecimal = false;
			ClearNextPress = true;
			Operation = null;
			ClearUpperTextNextPress = true;	// after we finish an operation, we'll clear the history of this operation
			UpdateLowerText();
		}
	}
}

public class DominicCalculator extends JFrame {
	// Responsible for creating a button that is sized and positioned to fit the UI correctly
	private int ButtonIndex = 0;

	class ButtonWrapper {
		// Keeps track of how many buttons we use (since we position a button by its #)
		private final JButton Button;
		private boolean IsHovering = false;

		// HoverDelta: Change of r, g, b components when button is hovered; ClickDelta: same but when button is clicked
		ButtonWrapper(JPanel Parent, String Text, Color DefaultColor, int HoverDelta, int ClickDelta, int FontSize) {
			int Column = ButtonIndex % 4;
			int Row = (int)Math.floor((double)(ButtonIndex) / 4);

			Button = new JButton();

			// 10 = padding on the x, 120 = padding on the y; 2 pixel padding between each button
			Button.setBounds(10 + (Column * (85 + 2)), 120 + ((Row) * (60 + 2)), 85, 60);
			Button.setText(Text);
			Button.setForeground(Color.WHITE);
			Button.setBackground(DefaultColor);
			Button.setFont(new Font("Segoe UI", Font.PLAIN, FontSize));
			Button.setHorizontalTextPosition(SwingConstants.CENTER);
			Button.setVerticalTextPosition(SwingConstants.CENTER);
			Button.setBorderPainted(false);
			Button.setFocusPainted(false);

			int R = DefaultColor.getRed(), G = DefaultColor.getGreen(), B = DefaultColor.getBlue();
			Color HoverColor = new Color(R + HoverDelta, G + HoverDelta, B + HoverDelta);
			Color ClickColor = new Color(R + ClickDelta, G + ClickDelta, B + ClickDelta);

			// Responsible for tweening a button's color upon hover and click; we'll use 100ms
			Button.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					new TweenColor(Button, ClickColor, 100);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (IsHovering) {
						new TweenColor(Button, HoverColor, 100);
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					IsHovering = true;
					new TweenColor(Button, HoverColor, 100);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					IsHovering = false;
					new TweenColor(Button, DefaultColor, 100);
				}
			});

			Parent.add(Button);
			++ButtonIndex;
		}

		public JButton GetButtonObject() {
			return Button;
		}	// so the main function can edit the button if desired
	}

	public DominicCalculator() {
		setTitle("Calculator");
		setSize(380, 475);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);    // Fixed gui

		// Transparent icon... to avoid the default icon
		Image TransparentIcon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(TransparentIcon);

		JPanel MainPanel = new JPanel();
		MainPanel.setLayout(null);
		MainPanel.setBackground(new Color(50, 50, 50));

		JPanel TextContainer = new JPanel();
		TextContainer.setLayout(null);
		TextContainer.setBounds(10, 10, 346, 100);
		TextContainer.setBackground(new Color(35, 35, 35));
		MainPanel.add(TextContainer);

		JLabel UpperText = new JLabel();
		UpperText.setBounds(0, 5, 330, 30);
		UpperText.setHorizontalAlignment(SwingConstants.RIGHT);
		UpperText.setForeground(new Color(200, 200, 200));
		UpperText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		UpperText.setText("");
		TextContainer.add(UpperText);

		JLabel LowerText = new JLabel();
		LowerText.setBounds(0, 35, 330, 60);
		LowerText.setHorizontalAlignment(SwingConstants.RIGHT);
		LowerText.setForeground(Color.WHITE);
		LowerText.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 45));
		LowerText.setText("0");
		TextContainer.add(LowerText);

		JButton ButtonCE = new ButtonWrapper(MainPanel, "CE", new Color(40, 40, 40), 50, 80, 16).GetButtonObject();
		JButton ButtonC = new ButtonWrapper(MainPanel, "C", new Color(40, 40, 40), 50, 80, 16).GetButtonObject();
		JButton ButtonDelete = new ButtonWrapper(MainPanel, "DEL", new Color(40, 40, 40), 50, 80, 16).GetButtonObject();
		JButton ButtonDiv = new ButtonWrapper(MainPanel, "÷", new Color(40, 40, 40), 50, 80, 25).GetButtonObject();
		JButton Button1 = new ButtonWrapper(MainPanel, "1", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton Button2 = new ButtonWrapper(MainPanel, "2", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton Button3 = new ButtonWrapper(MainPanel, "3", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton ButtonMult = new ButtonWrapper(MainPanel, "×", new Color(40, 40, 40), 50, 80, 25).GetButtonObject();
		JButton Button4 = new ButtonWrapper(MainPanel, "4", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton Button5 = new ButtonWrapper(MainPanel, "5", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton Button6 = new ButtonWrapper(MainPanel, "6", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton ButtonSub = new ButtonWrapper(MainPanel, "−", new Color(40, 40, 40), 50, 80, 25).GetButtonObject();
		JButton Button7 = new ButtonWrapper(MainPanel, "7", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton Button8 = new ButtonWrapper(MainPanel, "8", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton Button9 = new ButtonWrapper(MainPanel, "9", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton ButtonPlus = new ButtonWrapper(MainPanel, "+", new Color(40, 40, 40), 50, 80, 25).GetButtonObject();
		JButton ButtonSign = new ButtonWrapper(MainPanel, "±", new Color(25, 25, 25), 50, 80, 25).GetButtonObject();
		JButton Button0 = new ButtonWrapper(MainPanel, "0", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton ButtonDecimal = new ButtonWrapper(MainPanel, ".", new Color(25, 25, 25), 50, 80, 20).GetButtonObject();
		JButton ButtonEqual = new ButtonWrapper(MainPanel, "=", new Color(35, 61, 100), 15, 30, 25).GetButtonObject();

		OpManager Calculator = new OpManager(UpperText, LowerText);

		// Actual functionality of buttons
		Button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("1");
			}
		});
		Button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("2");
			}
		});
		Button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("3");
			}
		});
		Button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("4");
			}
		});
		Button5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("5");
			}
		});
		Button6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("6");
			}
		});
		Button7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("7");
			}
		});
		Button8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("8");
			}
		});
		Button9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("9");
			}
		});
		Button0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue("0");
			}
		});
		ButtonDecimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.AppendValue(".");
			}
		});
		ButtonDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.DeleteValue();
			}
		});
		ButtonMult.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.SetOperation(OP.MULT);
			}
		});
		ButtonDiv.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.SetOperation(OP.DIV);
			}
		});
		ButtonPlus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.SetOperation(OP.ADD);
			}
		});
		ButtonSub.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.SetOperation(OP.SUB);
			}
		});
		ButtonEqual.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.Calculate();
			}
		});
		ButtonCE.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.ClearCurrentInput();
			}
		});
		ButtonC.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.ClearAll();
			}
		});
		ButtonSign.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Calculator.FlipSign();
			}
		});

		// Same as above, but for keystrokes (when the GUI is in focus)
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_NUMPAD1, KeyEvent.VK_1 -> Calculator.AppendValue("1");
					case KeyEvent.VK_NUMPAD2, KeyEvent.VK_2 -> Calculator.AppendValue("2");
					case KeyEvent.VK_NUMPAD3, KeyEvent.VK_3 -> Calculator.AppendValue("3");
					case KeyEvent.VK_NUMPAD4, KeyEvent.VK_4 -> Calculator.AppendValue("4");
					case KeyEvent.VK_NUMPAD5, KeyEvent.VK_5 -> Calculator.AppendValue("5");
					case KeyEvent.VK_NUMPAD6, KeyEvent.VK_6 -> Calculator.AppendValue("6");
					case KeyEvent.VK_NUMPAD7, KeyEvent.VK_7 -> Calculator.AppendValue("7");
					case KeyEvent.VK_NUMPAD8, KeyEvent.VK_8 -> Calculator.AppendValue("8");
					case KeyEvent.VK_NUMPAD9, KeyEvent.VK_9 -> Calculator.AppendValue("9");
					case KeyEvent.VK_NUMPAD0, KeyEvent.VK_0 -> Calculator.AppendValue("0");
					case KeyEvent.VK_BACK_SPACE, KeyEvent.VK_DELETE -> Calculator.DeleteValue();
					case KeyEvent.VK_ASTERISK, KeyEvent.VK_MULTIPLY -> Calculator.SetOperation(OP.MULT);
					case KeyEvent.VK_SLASH, KeyEvent.VK_DIVIDE -> Calculator.SetOperation(OP.DIV);
					case KeyEvent.VK_PLUS, KeyEvent.VK_ADD -> Calculator.SetOperation(OP.ADD);
					case KeyEvent.VK_MINUS, KeyEvent.VK_SUBTRACT -> Calculator.SetOperation(OP.SUB);
					case KeyEvent.VK_PERIOD, KeyEvent.VK_DECIMAL -> Calculator.AppendValue(".");
					case KeyEvent.VK_EQUALS, KeyEvent.VK_ENTER -> Calculator.Calculate();
				}
			}
			return false;
		});

		add(MainPanel);
	}
}