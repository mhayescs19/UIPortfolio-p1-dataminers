package notepad;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Manages all GUI-related components that are separate from actually saving/writing to the files
public class NotepadGUI extends JFrame {
	private final JPanel MainPanel;
	private final JPanel BottomPanel;
	private final JTextArea TextArea;
	private final JLabel ZoomPercentage;
	private final JLabel CursorPos;
	private final FileManager FM;

	private boolean TextChanged = false;
	private int ZoomValue = 100;

	//-- Number constants
	private final int BOTTOM_BAR_HEIGHT = 23;
	private final Color LINE_COLOR = new Color(220, 220, 220);

	private final int ZOOM_INCREMENT = 10;
	private final int MAX_ZOOM = 500;
	private final int MIN_ZOOM = 10;
	private final int DEFAULT_FONT_SIZE = 14;

	//-- Public functions used in FileManager
	public void UpdateTitle() {
		setTitle((TextChanged ? "*" : "") + FM.FileName + " - Notepad");
	}

	public void SetText(String Text) {
		TextArea.setText(Text);
	}

	public String GetText() {
		return TextArea.getText();
	}

	public void ResetTextChanged() {
		TextChanged = false;
	}

	//-- Private functions
	private void ChangeTextFont(int Size) {
		TextArea.setFont(new Font("Consolas", Font.PLAIN, Size));
	}
	private void ZoomIn() {
		if (ZoomValue < MAX_ZOOM) {
			ZoomValue += ZOOM_INCREMENT;
			ZoomPercentage.setText(ZoomValue + "%");
			ChangeTextFont((int)((ZoomValue / 100.0) * DEFAULT_FONT_SIZE));
		}
	}
	private void ZoomOut() {
		if (ZoomValue > MIN_ZOOM) {
			ZoomValue -= ZOOM_INCREMENT;
			ZoomPercentage.setText(ZoomValue + "%");
			ChangeTextFont((int)((ZoomValue / 100.0) * DEFAULT_FONT_SIZE));
		}
	}

	private JMenuItem CreateMenuItem(JMenu ParentMenu, String ItemName, ActionListener AL) {
		JMenuItem MenuItem = new JMenuItem(ItemName);
		MenuItem.addActionListener(AL);	// OnPressed: call AL's listener function
		ParentMenu.add(MenuItem);
		return MenuItem;
	}

	private JLabel CreateBottomLabel(JPanel Parent, int Width, String StartingText) {
		JLabel NewLabel = new JLabel();
		NewLabel.setMinimumSize(new Dimension (Width, BOTTOM_BAR_HEIGHT));
		NewLabel.setPreferredSize(new Dimension (Width, BOTTOM_BAR_HEIGHT));
		NewLabel.setMaximumSize(new Dimension (Width, BOTTOM_BAR_HEIGHT));
		NewLabel.setText(StartingText);
		NewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		NewLabel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, LINE_COLOR));
		NewLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, LINE_COLOR), BorderFactory.createEmptyBorder(0, 4, 0, 0)));
		Parent.add(NewLabel);
		return NewLabel;
	}

	/*
	WIP

	private void ShowSavePrompt() {
		JFrame PromptGUI = new JFrame();
		PromptGUI.setTitle("Notepad");
		PromptGUI.setSize(300, 100);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel NewPanel = new JPanel();
		NewPanel.setLayout(new BoxLayout(NewPanel, BoxLayout.Y_AXIS));
		NewPanel.setBackground(Color.WHITE);
		PromptGUI.add(NewPanel);

		JLabel MessageLabel = new JLabel();
		MessageLabel.setText("Do you want to save changes to " + (FM.OpenedFile != null ? FM.OpenedFile.getPath() : FM.FileName) + "?");
		MessageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		MessageLabel.setForeground(new Color(0, 51, 153));
		NewPanel.add(MessageLabel);

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.X_AXIS));
		ButtonPanel.setBackground(new Color(240, 240, 240));
		ButtonPanel.setMinimumSize(new Dimension (600, 30));
		ButtonPanel.setPreferredSize(new Dimension (600, 30));
		ButtonPanel.setMaximumSize(new Dimension (Integer.MAX_VALUE, 30));
		ButtonPanel.add(Box.createHorizontalGlue());
		PromptGUI.add(ButtonPanel, BorderLayout.PAGE_END);

		PromptGUI.setVisible(true);

	}*/

	public NotepadGUI() {
		// Manages all file operations
		FM = new FileManager(this);

		UpdateTitle();
		setSize(600, 500);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (TextChanged) {
					int Result = JOptionPane.showOptionDialog(null, "Do you want to save changes to " + (FM.OpenedFile != null ? FM.OpenedFile.getPath() : FM.FileName) + "?", "Notepad", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Save", "Don't Save", "Cancel"}, "Save");
					switch (Result) {
						case 0:
							FM.SaveFile();
						case 1:
							dispose();
					}
					System.out.println("Done");
				} else {
					dispose();
				}
			}
		});

		// MainPanel: boxlayout top -> down
		MainPanel = new JPanel();
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		MainPanel.setBackground(Color.WHITE);
		add(MainPanel);

		// Text area: the main body
		TextArea = new JTextArea();
		TextArea.setBorder(BorderFactory.createCompoundBorder(TextArea.getBorder(), BorderFactory.createEmptyBorder(0, 2, 0, 2))); // 2 px padding for the text in the textarea
		TextArea.setFont(new Font("Consolas", Font.PLAIN, 14));	// Default - will be customizable soon
		MainPanel.add(TextArea, BorderLayout.CENTER);

		// Bottom bar
		BottomPanel = new JPanel();
		BottomPanel.setLayout(new BoxLayout(BottomPanel, BoxLayout.LINE_AXIS));
		BottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, LINE_COLOR));
		BottomPanel.setBackground(new Color(240, 240, 240));
		BottomPanel.setMinimumSize(new Dimension (600, BOTTOM_BAR_HEIGHT));
		BottomPanel.setPreferredSize(new Dimension (600, BOTTOM_BAR_HEIGHT));
		BottomPanel.setMaximumSize(new Dimension (Integer.MAX_VALUE, BOTTOM_BAR_HEIGHT));
		BottomPanel.add(Box.createHorizontalGlue());
		add(BottomPanel, BorderLayout.PAGE_END);

		CursorPos = CreateBottomLabel(BottomPanel, 200, "Ln 1, Col 1");
		ZoomPercentage = CreateBottomLabel(BottomPanel, 50, "100%");

		// Track line & column
		TextArea.addCaretListener(e -> {
			int CaretPosition = e.getDot();
			try {
				int Line = TextArea.getLineOfOffset(CaretPosition);
				int Column = CaretPosition - TextArea.getLineStartOffset(Line);
				Line += 1;	// since getlineofoffset returns the first line as being line 0
				Column += 1;
				CursorPos.setText("Ln " + Line + ", Col " + Column);
			} catch (Exception ignore) { }
		});

		// Determines if the TextArea is edited (if so, we'll change the TextChanged flag)
		TextArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				TextChanged = true;
				UpdateTitle();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				TextChanged = true;
				UpdateTitle();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				TextChanged = true;
				UpdateTitle();
			}
		});

		JScrollPane ScrollPane = new JScrollPane(TextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScrollPane.setBorder(BorderFactory.createEmptyBorder());
		MainPanel.add(ScrollPane);

		// Top menu bar: where most of the action happens
		JMenuBar MenuBar = new JMenuBar();
		MenuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LINE_COLOR)); // 1 px line right under the menu bar
		setJMenuBar(MenuBar);

		JMenu FileMenu = new JMenu("File");
		MenuBar.add(FileMenu);
		JMenu ViewMenu = new JMenu("View");
		MenuBar.add(ViewMenu);

		JMenu ZoomBar = new JMenu("Zoom");
		ViewMenu.add(ZoomBar);

		CreateMenuItem(ZoomBar, "Zoom In", e -> ZoomIn());
		CreateMenuItem(ZoomBar, "Zoom Out", e -> ZoomOut());

		// Add the various menu items
		CreateMenuItem(FileMenu, "Open", e -> FM.OpenFile());
		CreateMenuItem(FileMenu, "Save", e -> FM.SaveFile());
		CreateMenuItem(FileMenu, "Save As", e -> FM.SaveFileAs());

		setVisible(true);
	}
}
