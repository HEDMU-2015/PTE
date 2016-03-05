package utils;

import java.io.File;

public interface FileUtils {
	public File createFile(String filePath) throws FileExistsException;
}
