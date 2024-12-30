package Servleti;

import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import com.stripe.Stripe;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("checkout")
public class CheckoutServlet extends HttpServlet {

    // The public Stripe key, which can be loaded from an environment variable or configuration file
    private final String publicKey = System.getenv("STRIPE_PUBLIC_KEY");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the attributes to be used in the JSP view - tj frontend
        request.setAttribute("amount", 50 * 100); //placeholder holičina
        request.setAttribute("stripePublicKey", publicKey);
        request.setAttribute("currency", "EUR");

        // Forward the request to the checkout JSP page - smz da bl frontend, za pol
        request.getRequestDispatcher("placeholderZaCheckoutPage").forward(request, response);
    }
}
