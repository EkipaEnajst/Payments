package api.v1;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;

import org.ekipaenajst.beans.StripeZrno;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.inject.Inject;

@Path("payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StripeVir {

    @Inject
    private StripeZrno stripeService;

    @Path("/create-intent") // ?
    @POST //nimam pojma
    public PaymentIntent createPaymentIntent(Long amount) throws StripeException {
        return stripeService.createPaymentIntent(amount);
    }
}