package payment_recommendation.models.payment.impl;


import payment_recommendation.enums.InstrumentType;
import payment_recommendation.enums.Issuer;
import payment_recommendation.models.payment.PaymentInstrument;
import payment_recommendation.models.payment.RelevanceScore;

import java.util.UUID;

public class CreditCardPayment extends PaymentInstrument {

    public CreditCardPayment(UUID id, Issuer issuer, RelevanceScore relevanceScore) {
        super(id, InstrumentType.CREDIT_CARD, issuer, relevanceScore);
    }

    @Override
    public String toString() {
        return "CreditCardPayment {" +
                super.toString() +
                '}';
    }
}
