package notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;

// Responsible solely for saving/writing to files
public class FileManager {
	public String FileName = "Untitled";
	private File OpenedFile;
	private final JFileChooser FileChooser = new JFileChooser();
	private final NotepadGUI GUI;

	FileManager(NotepadGUI GUI) {
		FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.txt)", "txt"));
		FileChooser.setAcceptAllFileFilterUsed(true);
		this.GUI = GUI;
	}

	// Opens a FileChooser dialog that lets users choose a file to name
	// Writes to FileManager.OpenedData
	// Returns true or false to indicate success
	public boolean OpenFile() {
		int Result = FileChooser.showOpenDialog(GUI);
		if (Result == JFileChooser.APPROVE_OPTION) {
			File SelectedFile = FileChooser.getSelectedFile();
			try {
				String OpenedData = new String(Files.readAllBytes(SelectedFile.toPath()));
				FileName = SelectedFile.getName();
				OpenedFile = SelectedFile;
				GUI.SetText(OpenedData);
				GUI.ResetTextChanged();
				GUI.UpdateTitle();
				return true;
			} catch (IOException Ex) {
				// TO-DO: More specialized exception messages (e.g. file not found)
				JOptionPane.showMessageDialog(GUI, "Error opening file: " + SelectedFile.getPath() + " | Exception: " + Ex);
			}
		}
		return false;
	}

	// Writes to a File; used by both SaveFile and SaveFileAs
	private void SaveToFile(File SelectedFile) {
		try {
			Files.write(SelectedFile.toPath(), GUI.GetText().getBytes());
			FileName = SelectedFile.getName();
			OpenedFile = SelectedFile;
			GUI.ResetTextChanged();
			GUI.UpdateTitle();
		} catch (IOException Ex) {
			JOptionPane.showMessageDialog(GUI, "Error saving file: " + SelectedFile.getPath() + " | Exception: " + Ex);
		}
	}

	// Opens a new dialog for the user to choose which file they want to save to
	public void SaveFileAs() {
		int Result = FileChooser.showSaveDialog(GUI);
		if (Result == JFileChooser.APPROVE_OPTION) {
			File SelectedFile = FileChooser.getSelectedFile();
			SaveToFile(SelectedFile);
		}
	}

	// Saves to the current file, if one was previously opened; otherwise, it prompts the user to pick a file
	public void SaveFile() {
		// We already have a path (no need to open a dialog)
		if (OpenedFile != null) {
			SaveToFile(OpenedFile);
		} else {
			SaveFileAs();
		}
	}
}
