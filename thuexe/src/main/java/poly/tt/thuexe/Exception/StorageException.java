package poly.tt.thuexe.Exception;

public class StorageException extends RuntimeException{

	public StorageException(String message) {
		super(message);
		
	}

	public StorageException(String message, Exception e) {
		super(message,e);
	}

}
