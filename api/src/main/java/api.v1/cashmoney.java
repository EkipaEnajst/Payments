package api.v1;


import org.ekipaenajst.stripeintegration.StripeZrno;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private StripeZrno stripeService;

    @PostMapping("/create-intent")
    public PaymentIntent createPaymentIntent(@RequestParam Long amount) throws StripeException {
        return stripeService.createPaymentIntent(amount);
    }
}