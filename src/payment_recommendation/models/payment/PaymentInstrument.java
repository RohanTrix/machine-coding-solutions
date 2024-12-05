package payment_recommendation.models.payment;


import payment_recommendation.enums.InstrumentType;
import payment_recommendation.enums.Issuer;

import java.util.UUID;

public abstract class PaymentInstrument implements Comparable<PaymentInstrument> {
    protected final UUID id;

    protected final InstrumentType instrumentType;
    protected final Issuer issuer;
    protected RelevanceScore relevanceScore;
    public PaymentInstrument(UUID id, InstrumentType instrumentType, Issuer issuer, RelevanceScore relevanceScore) {
        this.id = id;
        this.instrumentType = instrumentType;
        this.issuer = issuer;
        this.relevanceScore = relevanceScore;
    }

    public UUID getId() {
        return id;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public RelevanceScore getRelevanceScore() {
        return relevanceScore;
    }

    @Override
    public int compareTo(PaymentInstrument that) {
        return that.getRelevanceScore().compareTo(this.getRelevanceScore());
    }

    @Override
    public String toString() {
        return "PaymentInstrument{" +
                "id=" + id +
                ", instrumentType=" + instrumentType +
                ", issuer=" + issuer +
                ", relevanceScore=" + relevanceScore +
                '}';
    }
}
