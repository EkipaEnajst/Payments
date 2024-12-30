package Servleti;


import com.stripe.exception.StripeException;
import org.ekipaenajst.beans.StripeZrno;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;
import com.stripe.model.Charge;
import java.io.IOException;

@WebServlet("charge")
public class ChargeServlet extends HttpServlet {

    @Inject
    private StripeZrno stripeService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String token = request.getParameter("stripeToken");
        long amount = Long.parseLong(request.getParameter("amount"));

        try {
            Charge charge = stripeService.createCharge(token, amount);
            request.setAttribute("id", charge.getId());
            request.setAttribute("status", charge.getStatus());
            request.setAttribute("chargeId", charge.getId());
            request.setAttribute("balance_transaction", charge.getBalanceTransaction());

            // Forward the request to the result.jsp page - spet frontend
            request.getRequestDispatcher("placeholderZaResultPage").forward(request, response);

        } catch(StripeException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Payment error: " + e.getMessage());
        }
    }
}