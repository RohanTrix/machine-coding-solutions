package payment_recommendation.models.payment.impl;

import payment_recommendation.enums.InstrumentType;
import payment_recommendation.enums.Issuer;
import payment_recommendation.models.payment.PaymentInstrument;
import payment_recommendation.models.payment.RelevanceScore;

import java.util.UUID;

public class DebitCardPayment extends PaymentInstrument {
    public DebitCardPayment(UUID id, Issuer issuer, RelevanceScore relevanceScore) {
        super(id, InstrumentType.DEBIT_CARD, issuer, relevanceScore);
    }
}
