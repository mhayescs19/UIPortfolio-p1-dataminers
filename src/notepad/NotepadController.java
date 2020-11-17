package notepad;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class NotepadController extends WindowAdapter implements ActionListener, DocumentListener {
	private NotepadModel Model;
	private NotepadView View;
	private File OpenedFile;
	private boolean TextChanged = false;

	public NotepadController(NotepadModel Model, NotepadView View) {
		this.Model = Model;
		this.View = View;

		// Listener methods are declare in the class (see below)
		View.AddGlobalActionListener(this);
		View.AddDocumentListener(this);
		View.AddWindowListener(this);
		View.setTitle("Untitled - Notepad");
	}

	private void UpdateTitle() {
		View.setTitle((TextChanged ? "*" : "") + (OpenedFile == null ? "Untitled" : OpenedFile.getName()) + " - Notepad");
	}

	// Writes to DestinationFile if not null; otherwise, prompts using View's method
	private boolean SaveToFile(File DestinationFile) {
		boolean Success;
		if (DestinationFile != null) {
			Success = Model.WriteFile(DestinationFile, View.GetText());
		} else {
			DestinationFile = View.ChooseFile("Save");
			if (DestinationFile != null) {
				OpenedFile = DestinationFile;
				Success = Model.WriteFile(DestinationFile, View.GetText());
			} else {
				return false;
			}
		}
		if (!Success) {
			JOptionPane.showMessageDialog(View, "Error saving file: " + DestinationFile.getPath() + " | Exception: " + Model.LastException);
		} else {
			TextChanged = false;
			UpdateTitle();
		}

		return Success;
	}

	private void PromptExit(Runnable Function) {
		if (TextChanged) {
			// 0 = save, 1 = don't save, 2 = cancel
			int Result = View.ChooseExitOption(OpenedFile == null ? "Untitled" : OpenedFile.getPath());
			switch (Result) {
				case 0:
					if (!SaveToFile(OpenedFile)) {
						break;
					}
				case 1:
					Function.run();
			}
		} else {
			Function.run();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		PromptExit(() -> View.dispose());
	}

	// Code for all menu items; connects View & Model
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "New" -> PromptExit(() -> {
				OpenedFile = null;
				View.SetText("");
				TextChanged = false;
				UpdateTitle();
			});
			case "New Window" -> new NotepadController(new NotepadModel(), new NotepadView());
			case "Open" -> {
				File SourceFile = View.ChooseFile("Open");
				if (SourceFile != null) {
					String Data = Model.ReadFile(SourceFile);
					if (Data != null) {
						OpenedFile = SourceFile;
						View.SetText(Data);
						TextChanged = false;
						UpdateTitle();
					} else {
						JOptionPane.showMessageDialog(View, "Error opening file: " + SourceFile.getPath() + " | Exception: " + Model.LastException);
					}
				}
			}
			case "Save" -> SaveToFile(OpenedFile);
			case "Save As" -> SaveToFile(null);
			case "Exit" -> View.dispose();
			case "Word Wrap" -> View.ToggleWordWrap();
			case "Zoom In" -> View.ZoomIn();
			case "Zoom Out" -> View.ZoomOut();
			case "Restore Default Zoom" -> View.ResetZoom();
			case "Status Bar" -> View.ToggleStatusBar();
		}
	}

	// These methods are called when the textbox is updated (need to keep track of when the file has been edited)
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
}
