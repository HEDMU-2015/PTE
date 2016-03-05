package utils;

public class FileExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public FileExistsException(String message) {
		super(message);
	}
}
