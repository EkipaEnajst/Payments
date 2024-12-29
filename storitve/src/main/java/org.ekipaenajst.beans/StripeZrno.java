package org.ekipaenajst.beans;

import java.util.Map;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;

import javax.enterprise.context.ApplicationScoped;
import java.util.Currency;

@ApplicationScoped
public class StripeZrno {

    //private Currency currency; //ta currency ne pride glih prov rn

    private final String secretKey = System.getenv("${stripe.secret-key}");

    public void StripeService() {
        Stripe.apiKey = secretKey;
    }

    public PaymentIntent createPaymentIntent(Long amount) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency("eur")
                .build();

        return PaymentIntent.create(params);
    }

    // Process a charge (e.g., for a payment)
    public Charge createCharge(String token, Long amount) throws StripeException {
        return Charge.create(Map.of(
                "amount", amount,
                "currency", "eur",
                "source", token
        ));
    }
}
