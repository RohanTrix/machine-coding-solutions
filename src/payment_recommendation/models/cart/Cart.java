package payment_recommendation.models.cart;


import payment_recommendation.enums.InstrumentType;
import payment_recommendation.enums.LineOfBusiness;
import payment_recommendation.exceptions.LimitExceededException;
import payment_recommendation.models.payment.PaymentInstrument;
import payment_recommendation.strategy.PaymentStrategyProvider;
import payment_recommendation.strategy.TransactionLimitProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Cart {
    private final LineOfBusiness lineOfBusiness;
    private final List<InstrumentType> allowedInstrumentTypes;
    private final List<CartItem> cartItems;
    private final Map<InstrumentType, BigDecimal> transactionLimits;
    public Cart(LineOfBusiness lineOfBusiness, List<CartItem> cartItems) {
        this.lineOfBusiness = lineOfBusiness;
        this.cartItems = Optional.ofNullable(cartItems).orElse(new ArrayList<>());
        allowedInstrumentTypes = PaymentStrategyProvider.getInstance().getAllowedInstrumentTypes(lineOfBusiness);
        this.transactionLimits = TransactionLimitProvider.getInstance().getTransactionLimit(lineOfBusiness);
    }
    public BigDecimal getTotalAmount() {
        return cartItems.stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public LineOfBusiness getLineOfBusiness() {
        return lineOfBusiness;
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public List<InstrumentType> getAllowedInstrumentTypes() {
        return allowedInstrumentTypes;
    }

    public Map<InstrumentType, BigDecimal> getTransactionLimits() {
        return transactionLimits;
    }
    public void pay(PaymentInstrument paymentInstrument) throws LimitExceededException {
        BigDecimal amount = getTotalAmount();
        BigDecimal limit = transactionLimits.get(paymentInstrument.getInstrumentType());
        if (amount.compareTo(limit) > 0) {
            throw new LimitExceededException("Payment Amount more than limit : " + limit.toString());
        }
        System.out.println("Amount Paid");
    }

}
