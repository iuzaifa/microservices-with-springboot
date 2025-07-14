package com.programingtechie.order_service.service;

import com.programingtechie.order_service.dto.OrderLineItemsDTO;
import com.programingtechie.order_service.dto.OrderRequest;
import com.programingtechie.order_service.model.OrderLineItems;
import com.programingtechie.order_service.model.Orders;
import com.programingtechie.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest){
        Orders orders = new Orders();
        orders.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDto()
                .stream()
                .map(this::mapToDTO)
                .toList();

        orders.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(orders);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems =new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        return orderLineItems;
    }
}
