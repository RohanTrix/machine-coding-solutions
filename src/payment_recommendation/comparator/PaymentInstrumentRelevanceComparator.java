package payment_recommendation.comparator;


import payment_recommendation.models.payment.PaymentInstrument;

import java.util.Comparator;

public class PaymentInstrumentRelevanceComparator implements Comparator<PaymentInstrument> {
    @Override
    public int compare(PaymentInstrument o1, PaymentInstrument o2) {
        return o2.getRelevanceScore().compareTo(o1.getRelevanceScore());
    }
}
