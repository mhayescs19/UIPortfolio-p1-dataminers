package notepad;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class NotepadGUI extends JFrame {
	private JPanel MainPanel;
	private final FileManager FM;
	private final JTextArea TextArea;
	private boolean TextChanged = false;

	private JMenuItem CreateMenuItem(JMenu ParentMenu, String ItemName, ActionListener AL) {
		JMenuItem MenuItem = new JMenuItem(ItemName);
		MenuItem.addActionListener(AL);	// OnPressed: call AL's listener function
		ParentMenu.add(MenuItem);
		return MenuItem;
	}

	// Public functions used in FileManager
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

	public NotepadGUI() {
		// Manages all file operations
		FM = new FileManager(this);

		UpdateTitle();
		setSize(600, 500);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// MainPanel: boxlayout top -> down
		MainPanel = new JPanel();
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		MainPanel.setBackground(Color.WHITE);
		add(MainPanel);

		// Text area: the main body
		TextArea = new JTextArea();
		TextArea.setBorder(BorderFactory.createCompoundBorder(TextArea.getBorder(), BorderFactory.createEmptyBorder(0, 2, 0, 2))); // 2 px padding for the text in the textarea
		TextArea.setFont(new Font("Consolas", Font.PLAIN, 14));	// Default - will be customizable soon
		MainPanel.add(TextArea);

		// Determines if the TextArea is edited (if so, we change the TextChanged flag)
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
		MenuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220))); // 1 px line right under the menu bar
		setJMenuBar(MenuBar);
		JMenu FileMenu = new JMenu("File");
		MenuBar.add(FileMenu);

		// Add the various menu items
		CreateMenuItem(FileMenu, "Open", e -> FM.OpenFile());
		CreateMenuItem(FileMenu, "Save", e -> FM.SaveFile());
		CreateMenuItem(FileMenu, "Save As", e -> FM.SaveFileAs());

		setVisible(true);
	}
}
