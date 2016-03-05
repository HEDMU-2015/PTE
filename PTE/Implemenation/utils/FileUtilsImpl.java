package utils;

import java.io.File;
import java.io.IOException;

public class FileUtilsImpl implements FileUtils {

	@Override
	public File createFile(String filePath) throws FileExistsException {
		File file = new File(filePath);
		try {
			if(!file.createNewFile()) {
				throw new FileExistsException("File " + filePath + " exists!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

}
