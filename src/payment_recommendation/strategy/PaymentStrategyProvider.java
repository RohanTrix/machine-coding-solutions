package payment_recommendation.strategy;


import payment_recommendation.enums.InstrumentType;
import payment_recommendation.enums.LineOfBusiness;

import java.util.*;
import java.util.function.Consumer;

public class PaymentStrategyProvider {

    private static PaymentStrategyProvider paymentStrategyProvider;
    private final EnumMap<LineOfBusiness, List<InstrumentType>> paymentMap;

    public static PaymentStrategyProvider getInstance() {
        if (paymentStrategyProvider == null) {
            paymentStrategyProvider = new PaymentStrategyProvider();
        }
        return paymentStrategyProvider;
    }
    private PaymentStrategyProvider() {
        this.paymentMap = new EnumMap<>(LineOfBusiness.class);
        enrichCCPaymentInstruments.andThen(enrichCommercePaymentInstruments)
                .andThen(enrichInvestmentPaymentInstruments).accept(paymentMap);
    }

    public List<InstrumentType> getAllowedInstrumentTypes(LineOfBusiness lineOfBusiness) {
        return Optional.ofNullable(paymentMap.get(lineOfBusiness)).orElse(Collections.emptyList());
    }

    private static final Consumer<Map<LineOfBusiness, List<InstrumentType>>> enrichCCPaymentInstruments = (paymentMap) -> {
        List<InstrumentType> allowedInstrumentTypes = List.of(InstrumentType.UPI, InstrumentType.NET_BANKING, InstrumentType.DEBIT_CARD);
        paymentMap.put(LineOfBusiness.CC_BILL_PAYMENT, allowedInstrumentTypes);
    };
    private static final Consumer<Map<LineOfBusiness, List<InstrumentType>>> enrichCommercePaymentInstruments = (paymentMap) -> {
        List<InstrumentType> allowedInstrumentTypes = List.of(InstrumentType.CREDIT_CARD, InstrumentType.UPI, InstrumentType.DEBIT_CARD);
        paymentMap.put(LineOfBusiness.COMMERCE, allowedInstrumentTypes);
    };

    private static final Consumer<Map<LineOfBusiness, List<InstrumentType>>> enrichInvestmentPaymentInstruments = (paymentMap) -> {
        List<InstrumentType> allowedInstrumentTypes = List.of(InstrumentType.UPI, InstrumentType.NET_BANKING, InstrumentType.DEBIT_CARD);
        paymentMap.put(LineOfBusiness.INVESTMENT, allowedInstrumentTypes);
    };
}
