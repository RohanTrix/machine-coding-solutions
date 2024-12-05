package payment_recommendation;


import payment_recommendation.comparator.PaymentInstrumentRelevanceComparator;
import payment_recommendation.enums.Issuer;
import payment_recommendation.enums.LineOfBusiness;
import payment_recommendation.models.cart.Cart;
import payment_recommendation.models.cart.CartItem;
import payment_recommendation.models.payment.PaymentInstrument;
import payment_recommendation.models.payment.RelevanceScore;
import payment_recommendation.models.payment.impl.CreditCardPayment;
import payment_recommendation.models.payment.impl.UPIPayment;
import payment_recommendation.models.user.DeviceContext;
import payment_recommendation.models.user.User;
import payment_recommendation.models.user.UserContext;
import payment_recommendation.service.PaymentRecommenderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentRecommendationApplication {
    public static void main(String[] args) {
        User user = getUser();
        Cart cart = getCart();
        PaymentInstrumentRelevanceComparator instrumentRelevanceComparator = new PaymentInstrumentRelevanceComparator();
        PaymentRecommenderService paymentRecommenderService = new PaymentRecommenderService(instrumentRelevanceComparator);
        paymentRecommenderService.recommendPaymentInstruments(user, cart)
                .forEach(System.out::println);
    }

    private static Cart getCart() {
        CartItem item1 = new CartItem(UUID.randomUUID(), "BALL", new BigDecimal("100"));
        CartItem item2 = new CartItem(UUID.randomUUID(), "BAT", new BigDecimal("200"));
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        return new Cart(LineOfBusiness.CC_BILL_PAYMENT, itemList);
    }

    private static User getUser() {
        DeviceContext deviceContext = getDeviceContext();
        UserContext userContext = getUserContext(deviceContext);
        List<PaymentInstrument> paymentInstruments = getPaymentInstruments();
        return new User("rohantrix", userContext, paymentInstruments);
    }

    private static List<PaymentInstrument> getPaymentInstruments() {
        CreditCardPayment creditCardPayment = new CreditCardPayment(UUID.randomUUID(), Issuer.AXIS, RelevanceScore.from(BigDecimal.valueOf(1.0)));
        UPIPayment upiPayment = new UPIPayment(UUID.randomUUID(), Issuer.AMEX, RelevanceScore.from(BigDecimal.valueOf(0.5)));
        return List.of(creditCardPayment, upiPayment);
    }

    private static UserContext getUserContext(DeviceContext deviceContext) {
        return new UserContext(deviceContext);
    }

    private static DeviceContext getDeviceContext() {
        return new DeviceContext(false);
    }
}