package payment_recommendation.models.user;


import payment_recommendation.enums.InstrumentType;
import payment_recommendation.models.payment.PaymentInstrument;

import java.util.List;

public class User {
    private final String id;

    private final UserContext userContext;
    private final List<PaymentInstrument> paymentInstruments;
    public User(String id, UserContext userContext, List<PaymentInstrument> paymentInstruments) {
        this.id = id;
        this.userContext = userContext;
        this.paymentInstruments = paymentInstruments;
    }
    public String getId() {
        return id;
    }

    public UserContext getUserContext() {
        return userContext;
    }

    public List<PaymentInstrument> getPaymentInstruments() {
        if(!userContext.isUpiEnabled()) {
            return paymentInstruments.stream().filter(paymentInstrument -> !InstrumentType.UPI.equals(paymentInstrument.getInstrumentType())).toList();
        }
        return paymentInstruments;
    }
}
