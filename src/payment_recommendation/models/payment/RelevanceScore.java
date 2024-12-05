package payment_recommendation.models.payment;


import payment_recommendation.exceptions.RelevanceScoreOutOfBounds;

import java.math.BigDecimal;

public class RelevanceScore extends BigDecimal {

    private RelevanceScore(BigDecimal val) {
        super(String.valueOf(val));
    }

    public static RelevanceScore from(BigDecimal val) {
        if(val.compareTo(BigDecimal.ZERO) < 0 || val.compareTo(BigDecimal.ONE) > 0) {
            throw new RelevanceScoreOutOfBounds("Relevance Score should be between 0 and 1");
        }
        return new RelevanceScore(val);
    }
}
