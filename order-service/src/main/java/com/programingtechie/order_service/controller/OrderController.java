package com.programingtechie.order_service.controller;


import com.programingtechie.order_service.dto.OrderRequest;
import com.programingtechie.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrders(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Successfully Added !";
    }
}
