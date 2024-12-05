package payment_recommendation.service;


import payment_recommendation.comparator.PaymentInstrumentRelevanceComparator;
import payment_recommendation.enums.InstrumentType;
import payment_recommendation.models.cart.Cart;
import payment_recommendation.models.payment.PaymentInstrument;
import payment_recommendation.models.user.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentRecommenderService {

    private final PaymentInstrumentRelevanceComparator comparator;

    public PaymentRecommenderService(PaymentInstrumentRelevanceComparator comparator) {
        this.comparator = comparator;
    }

    public List<PaymentInstrument> recommendPaymentInstruments(final User user, final Cart cart) {
        List<InstrumentType> allowedInstrumentTypes = cart.getAllowedInstrumentTypes();
        Map<InstrumentType, List<PaymentInstrument>> allowedInstruments = user.getPaymentInstruments()
                .stream()
                .filter(paymentInstrument -> allowedInstrumentTypes.contains(paymentInstrument.getInstrumentType()))
                .collect(Collectors.groupingBy(PaymentInstrument::getInstrumentType));
        allowedInstruments.forEach((key, value) -> value.sort(comparator));
        return allowedInstrumentTypes
                .stream()
                .map(instrumentType -> allowedInstruments.getOrDefault(instrumentType, List.of()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
