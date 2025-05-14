package org.example.projectbcms.services.serviceInterfaces;

import org.example.projectbcms.dtos.OrderDetailDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailService {

    // tao chi tiet don hang
    OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO);

    // cap nhat
    OrderDetailDTO updateOrderDetail(OrderDetailDTO updateOrderDetailDTO, Long id);

    // lay chi tiet cua mot don hang
    List<OrderDetailDTO> getDetailsByOrderId(Long id);

    // tinh tong tien cua mot don hang chi tiet
    BigDecimal caculatorTotalAmountOfOrderDetail(Long id);

    // tinh tong tien cua mot don hang
    BigDecimal caculatorTotalAmountOfOrder (Long orderId);

    // tim chi tiet don hang theo id
    OrderDetailDTO getOrderDetailById(Long id);

}
