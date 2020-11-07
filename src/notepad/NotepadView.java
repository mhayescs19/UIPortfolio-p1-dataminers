package notepad;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

public class NotepadView extends JFrame {
	//-- View-related Constants
	private final int WINDOW_START_WIDTH = 600;
	private final int WINDOW_START_HEIGHT = 500;
	private final int BOTTOM_BAR_HEIGHT = 23;
	private final Color PRIMARY_LINE_COLOR = new Color(240, 240, 240);	// these colors deal w/ creating the gui
	private final Color SECONDARY_LINE_COLOR = new Color(215, 215, 215);

	private final int ZOOM_INCREMENT_PERCENT = 10;
	private final int MAX_ZOOM_PERCENT = 500;
	private final int MIN_ZOOM_PERCENT = 10;
	private final int DEFAULT_FONT_SIZE = 14;

	//-- Data variables
	private int ZoomValue = 100;

	//-- GUI Components
	private JPanel MainPanel;
	private JPanel BottomPanel;
	private JTextArea TextInput;
	private JLabel ZoomPercentageLabel;
	private JLabel CursorPositionLabel;
	private JCheckBoxMenuItem StatusBarCheckBox;
	private ArrayList<JMenuItem> MenuItems = new ArrayList<>();
	private JFileChooser FileChooser;

	public NotepadView() {
		ConfigureWindow();
		InitializeComponents();
		AddViewListeners();
		setVisible(true);	// setvisible at the end

		FileChooser = new JFileChooser();
		FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.txt)", "txt"));
		FileChooser.setAcceptAllFileFilterUsed(true);
	}

	//-- Public Methods

	public void ChangeFontSize(int Size) {
		Font CurrentFont = TextInput.getFont();
		TextInput.setFont(new Font(CurrentFont.getFontName(), CurrentFont.getStyle(), Size));
	}

	public void SetText(String Text) {
		TextInput.setText(Text);
	}

	public String GetText() {
		return TextInput.getText();
	}

	public void ZoomIn() {
		if (ZoomValue < MAX_ZOOM_PERCENT) {
			ZoomValue += ZOOM_INCREMENT_PERCENT;
			ZoomPercentageLabel.setText(ZoomValue + "%");
			ChangeFontSize((int)((ZoomValue / 100.0) * DEFAULT_FONT_SIZE));
		}
	}

	public void ZoomOut() {
		if (ZoomValue > MIN_ZOOM_PERCENT) {
			ZoomValue -= ZOOM_INCREMENT_PERCENT;
			ZoomPercentageLabel.setText(ZoomValue + "%");
			ChangeFontSize((int)((ZoomValue / 100.0) * DEFAULT_FONT_SIZE));
		}
	}

	public void ResetZoom() {
		ZoomValue = 100;
		ZoomPercentageLabel.setText("100%");
		ChangeFontSize(DEFAULT_FONT_SIZE);
	}

	public void ToggleStatusBar() {
		BottomPanel.setVisible(StatusBarCheckBox.getState());
	}

	// These are called in the Controller; provide connection between the two
	public void AddGlobalActionListener(ActionListener AL) {
		for (JMenuItem MenuItem : MenuItems) {
			MenuItem.addActionListener(AL);
		}
	}

	public void AddDocumentListener(DocumentListener DL) {
		TextInput.getDocument().addDocumentListener(DL);
	}

	public void AddWindowListener(WindowListener WL) {
		addWindowListener(WL);
	}

	public File ChooseFile(String PromptType) {
		int Result = (PromptType.equals("Open") ? FileChooser.showOpenDialog(this) : FileChooser.showSaveDialog(this));
		if (Result == JFileChooser.APPROVE_OPTION) {
			return FileChooser.getSelectedFile();
		} else {
			return null;
		}
	}

	public int ChooseExitOption(String FilePath) {
		return JOptionPane.showOptionDialog(null, "Do you want to save changes to " + FilePath + "?", "Notepad", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Save", "Don't Save", "Cancel"}, "Save");
	}

	//-- Private Methods
	private void ConfigureWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignore) {}
		setSize(WINDOW_START_WIDTH, WINDOW_START_HEIGHT);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);	// will implement custom close operation
	}

	private void InitializeComponents() {
		// MainPanel: boxlayout top -> down
		MainPanel = new JPanel();
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		MainPanel.setBackground(Color.WHITE);
		add(MainPanel);

		// Text area: the main body
		TextInput = new JTextArea();
		TextInput.setBorder(BorderFactory.createCompoundBorder(TextInput.getBorder(), BorderFactory.createEmptyBorder(0, 2, 0, 2))); // 2 px padding for the text in the textarea
		TextInput.setFont(new Font("Consolas", Font.PLAIN, DEFAULT_FONT_SIZE));	// Default - will be customizable soon

		JScrollPane ScrollPane = new JScrollPane(TextInput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScrollPane.setBorder(BorderFactory.createEmptyBorder());
		MainPanel.add(ScrollPane);

		// Bottom bar
		BottomPanel = new JPanel();
		BottomPanel.setLayout(new BoxLayout(BottomPanel, BoxLayout.LINE_AXIS));
		BottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, SECONDARY_LINE_COLOR));
		BottomPanel.setBackground(PRIMARY_LINE_COLOR);
		BottomPanel.setMinimumSize(new Dimension(WINDOW_START_WIDTH, BOTTOM_BAR_HEIGHT));
		BottomPanel.setPreferredSize(new Dimension(WINDOW_START_WIDTH, BOTTOM_BAR_HEIGHT));
		BottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, BOTTOM_BAR_HEIGHT));
		BottomPanel.add(Box.createHorizontalGlue());
		add(BottomPanel, BorderLayout.PAGE_END);

		CursorPositionLabel = CreateBottomLabel(200, "Ln 1, Col 1");
		ZoomPercentageLabel = CreateBottomLabel(50, "100%");

		// Top (file menu) bar
		JMenuBar MenuBar = new JMenuBar();
		MenuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, PRIMARY_LINE_COLOR)); // 1 px line right under the menu bar
		setJMenuBar(MenuBar);

		JMenu FileMenu = CreateMenu(MenuBar, "File");
		JMenu ViewMenu = CreateMenu(MenuBar, "View");
		JMenu ZoomTab = CreateMenu(ViewMenu, "Zoom");

		// Add the various file menu items w/ keybinds if necessary
		CreateMenuItem(FileMenu, "Open", KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_O, new int[][]{});
		CreateMenuItem(FileMenu, "Save", KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_S, new int[][]{});
		CreateMenuItem(FileMenu, "Save As", KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, 0, new int[][]{});
		FileMenu.addSeparator();
		CreateMenuItem(FileMenu, "Exit", 0, 0, 0, new int[][]{});

		// Special menu item (checkbox); does not use the standard function
		StatusBarCheckBox = new JCheckBoxMenuItem("Status Bar");
		StatusBarCheckBox.setState(true);
		MenuItems.add(StatusBarCheckBox);
		ViewMenu.add(StatusBarCheckBox);

		CreateMenuItem(ZoomTab, "Zoom In", KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK, 0, new int[][]{{KeyEvent.VK_ADD, KeyEvent.CTRL_DOWN_MASK}});
		CreateMenuItem(ZoomTab, "Zoom Out", KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK, 0, new int[][]{{KeyEvent.VK_SUBTRACT, KeyEvent.CTRL_DOWN_MASK}});
		CreateMenuItem(ZoomTab, "Restore Default Zoom", KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK, 0, new int[][]{{KeyEvent.VK_NUMPAD0, KeyEvent.CTRL_DOWN_MASK}});

	}

	private JMenu CreateMenu(Object Parent, String Text) {
		JMenu NewMenu = new JMenu(Text);
		if (Parent instanceof JMenu) {
			((JMenu)Parent).add(NewMenu);
		} else if (Parent instanceof JMenuBar) {
			((JMenuBar)Parent).add(NewMenu);
		} else {
			return null;
		}
		return NewMenu;
	}

	// KeyShortcut & Modifiers are optional parameters to specify the main keybinds (these show up in the file menu item); AdditionalShortcuts can be provided -- these are not the ones shown up in the file menu item though
	private JMenuItem CreateMenuItem(JMenu Parent, String Name, int KeyShortcut, int Modifiers, int Mnemonic, int[][] AdditionalShortcuts) {
		JMenuItem NewMenuItem = new JMenuItem(Name);
		if (KeyShortcut != 0) {
			NewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyShortcut, Modifiers));
		}
		for (int[] KeyData : AdditionalShortcuts) {
			// Additional shortcuts require extra code
			if (KeyData.length == 2) {
				KeyStroke KS = KeyStroke.getKeyStroke(KeyData[0], KeyData[1]);
				NewMenuItem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS, KS.toString());
				NewMenuItem.getActionMap().put(KS.toString(), new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Hook it up to the main action listener; copy over the event source and ID but change the name
						NewMenuItem.getActionListeners()[0].actionPerformed(new ActionEvent(e.getSource(), e.getID(), Name));
					}
				});
			}
		}
		if (Mnemonic != 0) {
			NewMenuItem.setMnemonic(Mnemonic);
		}
		Parent.add(NewMenuItem);
		MenuItems.add(NewMenuItem);	// Register this button to be added to the Controller's ActionListener
		return NewMenuItem;
	}

	private JLabel CreateBottomLabel(int Width, String StartingText) {
		JLabel NewLabel = new JLabel();
		NewLabel.setMinimumSize(new Dimension (Width, BOTTOM_BAR_HEIGHT));
		NewLabel.setPreferredSize(new Dimension (Width, BOTTOM_BAR_HEIGHT));
		NewLabel.setMaximumSize(new Dimension (Width, BOTTOM_BAR_HEIGHT));
		NewLabel.setText(StartingText);
		NewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		NewLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, SECONDARY_LINE_COLOR), BorderFactory.createEmptyBorder(0, 4, 0, 0)));
		BottomPanel.add(NewLabel);
		return NewLabel;
	}

	// Listeners independent of Model/Control (aka View only)
	private void AddViewListeners() {
		TextInput.addCaretListener(e -> {
			int CaretPosition = e.getDot();
			try {
				int Line = TextInput.getLineOfOffset(CaretPosition);
				int Column = CaretPosition - TextInput.getLineStartOffset(Line);
				Line += 1;	// since getlineofoffset returns the first line as being line 0
				Column += 1;
				CursorPositionLabel.setText("Ln " + Line + ", Col " + Column);
			} catch (Exception ignore) { }
		});
	}
}
