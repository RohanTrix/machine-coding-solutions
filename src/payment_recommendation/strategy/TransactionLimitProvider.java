package payment_recommendation.strategy;


import payment_recommendation.enums.InstrumentType;
import payment_recommendation.enums.LineOfBusiness;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TransactionLimitProvider {
    private static TransactionLimitProvider instance;
    private final Map<LineOfBusiness, Map<InstrumentType, BigDecimal>> transactionLimits;
    private static final double FIFTY_THOUSAND = 50000;
    private static final double ONE_LAKH = 1e5;
    private static final double TWO_LAKHS = 2e5;
    private static final double TWO_AND_HALF_LAKHS = TWO_LAKHS + FIFTY_THOUSAND;
    private static final double ONE_AND_HALF_LAKHS = ONE_LAKH + FIFTY_THOUSAND;

    private TransactionLimitProvider() {
        transactionLimits = new HashMap<>();
        enrichCCBillPaymentLimits
                .andThen(enrichCommercePaymentInstruments)
                .andThen(enrichInvestmentPaymentInstruments)
                .accept(transactionLimits);
    }

    public static TransactionLimitProvider getInstance() {
        if (instance == null) {
            instance = new TransactionLimitProvider();
        }
        return instance;
    }


    public Map<InstrumentType, BigDecimal> getTransactionLimit(LineOfBusiness instrumentType) {
        return transactionLimits.getOrDefault(instrumentType, new HashMap<>());
    }

    private static final Consumer<Map<LineOfBusiness, Map<InstrumentType, BigDecimal>>> enrichCCBillPaymentLimits = (limitMap) -> {
        Map<InstrumentType, BigDecimal> limits = new HashMap<>();
        limits.put(InstrumentType.UPI, BigDecimal.valueOf(TWO_LAKHS));
        limits.put(InstrumentType.DEBIT_CARD, BigDecimal.valueOf(TWO_LAKHS));
        limits.put(InstrumentType.NET_BANKING, BigDecimal.valueOf(TWO_LAKHS));
        limitMap.put(LineOfBusiness.CC_BILL_PAYMENT, limits);
    };
    private static final Consumer<Map<LineOfBusiness, Map<InstrumentType, BigDecimal>>> enrichCommercePaymentInstruments = (limitMap) -> {
        Map<InstrumentType, BigDecimal> limits = new HashMap<>();
        limits.put(InstrumentType.CREDIT_CARD, BigDecimal.valueOf(TWO_AND_HALF_LAKHS));
        limits.put(InstrumentType.UPI, BigDecimal.valueOf(ONE_LAKH));
        limits.put(InstrumentType.DEBIT_CARD, BigDecimal.valueOf(TWO_LAKHS));
        limitMap.put(LineOfBusiness.COMMERCE, limits);
    };

    private static final Consumer<Map<LineOfBusiness, Map<InstrumentType, BigDecimal>>> enrichInvestmentPaymentInstruments = (limitMap) -> {
        Map<InstrumentType, BigDecimal> limits = new HashMap<>();
        limits.put(InstrumentType.UPI, BigDecimal.valueOf(ONE_LAKH));
        limits.put(InstrumentType.DEBIT_CARD, BigDecimal.valueOf(ONE_AND_HALF_LAKHS));
        limits.put(InstrumentType.NET_BANKING, BigDecimal.valueOf(ONE_AND_HALF_LAKHS));
        limitMap.put(LineOfBusiness.INVESTMENT, limits);
    };

}
