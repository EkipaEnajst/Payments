package api.v1;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class Server extends HttpServlet {

    @Override
    public void init() {
        // Set your secret Stripe API key during initialization.
        try {
            // Set Stripe API Key in a static block
            Stripe.apiKey = "sk_test_C8YmBFG9x8nTv1ZnC4kFd4CJ004r8nk3Rx"; // Ensure this is valid
        } catch (Exception e) {
            e.printStackTrace(); // Log any issues during initialization
            throw new RuntimeException("Failed to initialize Stripe API key", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String YOUR_DOMAIN = "http://localhost:4242";

        // Create Stripe session sparams
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "/success.html")
                        .setCancelUrl(YOUR_DOMAIN + "/cancel.html")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPrice("{{PRICE_ID}}") // Provide your Price ID
                                        .build())
                        .build();

        // Create a session
        Session session = null;
        try {
            session = Session.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        // Redirect to Stripe Checkout session URL
        response.sendRedirect(session.getUrl());
    }

    /*public static void main(String[] args) throws Exception {
        // You can use an embedded server like Jetty to run your servlet
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(4242);
        org.eclipse.jetty.servlet.ServletContextHandler context = new org.eclipse.jetty.servlet.ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);

        // Add your servlet class to handle POST requests
        context.addServlet(Server.class, "/checkout");

        // Start the server
        server.start();
        server.join();
    }*/
}
