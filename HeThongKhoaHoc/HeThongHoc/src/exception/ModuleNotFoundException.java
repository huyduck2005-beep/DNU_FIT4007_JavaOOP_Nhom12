package exception;

public class ModuleNotFoundException extends Exception {
    public ModuleNotFoundException(String message) {
        super(message);
    }
}