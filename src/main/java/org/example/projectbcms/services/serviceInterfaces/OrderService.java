package org.example.projectbcms.services.serviceInterfaces;

import org.example.projectbcms.dtos.OrderDTO;

import java.util.List;

// orderServiceInterface
public interface OrderService {

    //tao don hang
    OrderDTO createOrder(OrderDTO orderDTO);

    //xem chi tiet don hang
    OrderDTO orderDetail(OrderDTO order);

    //xem danh sach don hang
    List<OrderDTO> getAllOrder ();

    // cap nhat trang thai don hang
    OrderDTO updateOrderStatus(OrderDTO order, Long id);

    // huy don hang
    void cancelOrder(Long id);

    // xu ly thanh toan - lien ket ngan hang, luu thong tin da thanh toan hay chua
    // tinh nang nay dang phat trien
    OrderDTO paymentHanding (OrderDTO order);

    // in hoa don, gui email xac nhan
    OrderDTO confirmOrder (OrderDTO order);

    // tim kiem thong tin, loc thong tin don hang thong qua ma don hang
    OrderDTO getOrderById(Long id);

    // thong ke don hang - tinh tong doanh thu
    OrderDTO orderAnalytics (OrderDTO order);



}
