package notepad;

import java.io.File;
import java.nio.file.Files;

public class NotepadModel {
	public Exception LastException;

	public String ReadFile(File Source) {
		try {
			return new String(Files.readAllBytes(Source.toPath()));
		} catch (Exception Ex) {
			LastException = Ex;
			return null;
		}
	}

	public boolean WriteFile(File Destination, String Data) {
		try {
			Files.write(Destination.toPath(), Data.getBytes());
			return true;
		} catch (Exception Ex) {
			LastException = Ex;
			return false;
		}
	}
}
