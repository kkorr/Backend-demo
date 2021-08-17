package com.amr.project.webapp.handler;

import com.amr.project.model.entity.Order;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.in.Customer;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.UUID;

@Component
public class PaymentApi {
    private String secretKey = "eyJ2ZXJzaW9uIjoiUDJQIiwiZGF0YSI6eyJwYXlpbl9tZXJjaGFudF9zaXRlX3VpZCI6ImJtd256cC0wMCIsInVzZXJfaWQiOiI3OTk5NDk1NjYzNiIsInNlY3JldCI6IjYxMWU3ODM0ZDk2YTNmMDIxZjU0MDVhOGQzNjAzY2YyYjczOTMxODY5NjA5NGRmMTVjNzZiY2UyNWViZGI3NDYifX0=";
    private BillPaymentClient client = BillPaymentClientFactory.createDefault(secretKey);


    public HttpEntity<BillResponse> payUrl(Order order){
        CreateBillInfo billInfo = new CreateBillInfo(
                UUID.randomUUID().toString(),
                new MoneyAmount(order.getTotal(),
                        Currency.getInstance("RUB")
                ),
                "oplata zakaza " + order.getDate(),
                ZonedDateTime.now().plusDays(45),
                new Customer(
                        order.getUser().getEmail(),
                        UUID.randomUUID().toString(),
                        order.getBuyerPhone()
                ),
                "http://localhost:8888/home"
        );
        BillResponse response = null;
        try {
            response = client.createBill(billInfo);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new HttpEntity<>(response);

    }
    //Лучше сделать цикл при вызове метода, а из метода
    //возвращать статус
    public Boolean getStatus(String billId){
        String status = client.getBillInfo(billId).getStatus().getValue().toString();
        while (!status.contains("PAID")) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            status = client.getBillInfo(billId).getStatus().getValue().toString();
        }
        return true;

    }
}
