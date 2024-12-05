package payment_recommendation.exceptions;

public class LimitExceededException extends Exception {
    public LimitExceededException() {
    }

    public LimitExceededException(String message) {
        super(message);
    }
}
