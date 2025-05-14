package org.example.projectbcms.controllers;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.dtos.OrderDetailDTO;
import org.example.projectbcms.services.serviceInterfaces.OrderDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@ResponseBody
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("/create-an-order-detail")
    public OrderDetailDTO createOrderDetail(@RequestBody OrderDetailDTO newOrderDetail) {
        return orderDetailService.createOrderDetail(newOrderDetail);
    }

    @PutMapping("/update-an-order-detail/{id}")
    public OrderDetailDTO updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetailDTO newOrderDetail) {
        return orderDetailService.updateOrderDetail(newOrderDetail, id);
    }

    @GetMapping("/display-an-order-detail/{id}")
    public OrderDetailDTO getOrderDetail(@PathVariable Long id) {
        return orderDetailService.getOrderDetailById(id);
    }

    @GetMapping("/display-all-order-detail/{id}")
    public List<OrderDetailDTO> getAllOrderDetails(@PathVariable Long id) {
        return orderDetailService.getDetailsByOrderId(id);
    }
}
