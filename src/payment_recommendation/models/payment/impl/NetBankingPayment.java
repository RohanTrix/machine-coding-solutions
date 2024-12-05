package payment_recommendation.models.payment.impl;


import payment_recommendation.enums.InstrumentType;
import payment_recommendation.enums.Issuer;
import payment_recommendation.models.payment.PaymentInstrument;
import payment_recommendation.models.payment.RelevanceScore;

import java.util.UUID;

public class NetBankingPayment extends PaymentInstrument {
    public NetBankingPayment(UUID id, Issuer issuer, RelevanceScore relevanceScore) {
        super(id, InstrumentType.NET_BANKING, issuer, relevanceScore);
    }
}
