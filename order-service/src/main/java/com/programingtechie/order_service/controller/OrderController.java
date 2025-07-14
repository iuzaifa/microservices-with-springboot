package com.programingtechie.order_service.controller;


import com.programingtechie.order_service.dto.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    public String createOrders(@RequestBody OrderRequest orderRequest){
        return "Order Successfully Added !";
    }
}
