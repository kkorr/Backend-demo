package com.amr.project.webapp.handler;

import com.amr.project.model.entity.Order;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.in.Customer;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Currency;
import java.util.UUID;

@Component
public class PaymentApi {
    private String secretKey = "eyJ2ZXJzaW9uIjoiUDJQIiwiZGF0YSI6eyJwYXlpbl9tZXJjaGFudF9zaXRlX3VpZCI6ImJtd256cC0wMCIsInVzZXJfaWQiOiI3OTk5NDk1NjYzNiIsInNlY3JldCI6IjYxMWU3ODM0ZDk2YTNmMDIxZjU0MDVhOGQzNjAzY2YyYjczOTMxODY5NjA5NGRmMTVjNzZiY2UyNWViZGI3NDYifX0=";
    private BillPaymentClient client = BillPaymentClientFactory.createDefault(secretKey);


    public HttpEntity<BillResponse> payUrl(Order order) throws URISyntaxException {
        CreateBillInfo billInfo = new CreateBillInfo(
                UUID.randomUUID().toString(),
                new MoneyAmount(order.getTotal(),
                        Currency.getInstance("RUB")
                ),
                "oplata zakaza",
                ZonedDateTime.now().plusDays(45),
                new Customer(
                        order.getUser().getEmail(),
                        UUID.randomUUID().toString(),
                        order.getBuyerPhone()
                ),
                "http://localhost:8888/home"
        );
        BillResponse response = client.createBill(billInfo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", " Bearer " + secretKey);
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Accept", "application/json");
        HttpEntity<BillResponse> billResponse = new HttpEntity<>(response, httpHeaders);
        return billResponse;

    }
}
