package org.example.projectbcms.controllers;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.dtos.OrderDTO;
import org.example.projectbcms.services.serviceInterfaces.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/tao-don-hang")
    public OrderDTO createOrder(@RequestBody OrderDTO newOrder) {
        return orderService.createOrder(newOrder);
    }

    @GetMapping("/hien-thi-danh-sach-don-dat-hang")
    public List<OrderDTO> getAllOrders(OrderDTO order) {
        return orderService.getAllOrder();
    }

    @PutMapping("/cap-nhat-don-hang/{orderId}")
    public OrderDTO updateOrder(@RequestBody OrderDTO newOrder, @PathVariable Long orderId) {
        return orderService.updateOrderStatus(newOrder, orderId);
    }
    // xu ly thanh toan - lien ket ngan hang, luu thong tin da thanh toan hay chua
}
