package test.exception;

public class ParameterLessException extends RuntimeException {
    public ParameterLessException(String msg) {
        super(msg);
    }
}
