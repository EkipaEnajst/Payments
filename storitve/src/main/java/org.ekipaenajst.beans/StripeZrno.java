package org.ekipaenajst.beans;

import java.util.Map;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.exception.StripeException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StripeZrno {

    //tole gre v env
    private final String secretKey = System.getenv("STRIPE_SECRET_KEY");

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    // Process a charge (e.g., for a payment) - scuffed ampak je osnova ki dela
    public Charge createCharge(String token, Long amount) throws StripeException {
        return Charge.create(Map.of(
                "amount", amount,
                "currency", "eur",
                "source", token
        ));
    }
}
