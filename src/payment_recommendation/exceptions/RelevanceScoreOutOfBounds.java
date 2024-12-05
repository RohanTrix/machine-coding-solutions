package payment_recommendation.exceptions;

public class RelevanceScoreOutOfBounds extends RuntimeException {
    public RelevanceScoreOutOfBounds(String string) {
        super(string);
    }
}
