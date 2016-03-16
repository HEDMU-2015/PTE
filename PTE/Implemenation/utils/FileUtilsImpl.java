package utils;

import java.io.File;
import java.io.IOException;

public class FileUtilsImpl implements FileUtils {

	@Override
	public File createFile(String filePath) {
		File file = new File(filePath);
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

}
