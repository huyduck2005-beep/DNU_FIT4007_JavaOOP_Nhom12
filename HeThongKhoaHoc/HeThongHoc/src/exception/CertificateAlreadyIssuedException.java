package exception;

public class CertificateAlreadyIssuedException extends Exception {
    public CertificateAlreadyIssuedException(String message) {
        super(message);
    }
}