package org.ekipaenajst.beans;

import com.stripe.model.Charge;

import javax.enterprise.context.ApplicationScoped;
import java.util.Currency;

@ApplicationScoped
public class StripeZrno {

    private Currency currency;

    @Value("${stripe.secret-key}")
    private String secretKey;

    /*public Charge getCharge() {
        // TODO ....
        return null;
    }*/

    public StripeService() {
        Stripe.apiKey = secretKey;
    }

    public PaymentIntent createPaymentIntent(Long amount) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .build();

        return PaymentIntent.create(params);
    }

    // Process a charge (e.g., for a payment)
    public Charge createCharge(String token, Long amount) throws StripeException {
        return Charge.create(Map.of(
                "amount", amount,
                "currency", currency,
                "source", token
        ));
    }
}
