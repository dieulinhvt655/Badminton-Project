package org.example.projectbcms.services;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.dtos.OrderDetailDTO;
import org.example.projectbcms.models.Order;
import org.example.projectbcms.models.OrderDetail;
import org.example.projectbcms.models.Product;
import org.example.projectbcms.repositories.OrderDetailRepository;
import org.example.projectbcms.repositories.OrderRepository;
import org.example.projectbcms.repositories.ProductRepository;
import org.example.projectbcms.services.serviceInterfaces.OrderDetailService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderDetailImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // tinh tong gia cua mot don hang chi tiet
    @Override
    public BigDecimal caculatorTotalAmountOfOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        Integer quantity = orderDetail.getQuantity();
        BigDecimal price = orderDetail.getPrice();

        BigDecimal total = BigDecimal.valueOf(quantity).multiply(price);

        return total;
    }
    // tinh tong gia tien cua mot don hang
    @Override
    public BigDecimal caculatorTotalAmountOfOrder(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);

        BigDecimal total = BigDecimal.ZERO;

        for (OrderDetail orderDetail : orderDetails) {
            BigDecimal itemTotal = BigDecimal.valueOf(orderDetail.getQuantity())
                    .multiply(orderDetail.getPrice());
            total = total.add(itemTotal);
        }

        return total;
    }

    // create
    @Override
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO) {
        // kiem tra va lay mot order
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(()-> new RuntimeException("Order not found"));

        //kiem tra va lay mot product
        Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found"));

        //tao moi mot order detail
        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setQuantity(orderDetailDTO.getQuantity());
        newOrderDetail.setPrice(product.getBuyPrice());
        newOrderDetail.setOrder(order);
        newOrderDetail.setProduct(product);

        orderDetailRepository.save(newOrderDetail);
        orderDetailDTO.setId(newOrderDetail.getId());
        orderDetailDTO.setPrice(newOrderDetail.getPrice());
        return orderDetailDTO;
    }

    // update
    @Override
    public OrderDetailDTO updateOrderDetail(OrderDetailDTO updateOrderDetailDTO, Long id) {
        // kiem tra order detail muon update co ton tai hay khong
        OrderDetail orderDetailPresent = orderDetailRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order detail not found"));
        // kiem tra xem order co ton tai khong
        Order order = orderRepository.findById(updateOrderDetailDTO.getOrderId())
                .orElseThrow(()-> new RuntimeException("Order not found"));
        // kiem tra xem san pham co ton tai khong
        Product product = productRepository.findById(updateOrderDetailDTO.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found"));

        orderDetailPresent.setQuantity(updateOrderDetailDTO.getQuantity());
        orderDetailPresent.setOrder(order);
        orderDetailPresent.setProduct(product);

        orderDetailRepository.save(orderDetailPresent);

        return OrderDetailDTO.builder()
                .id(orderDetailPresent.getId())
                .quantity(orderDetailPresent.getQuantity())
                .price(orderDetailPresent.getPrice())
                .orderId(orderDetailPresent.getOrder().getId())
                .productId(orderDetailPresent.getProduct().getId())
                .build();
    }

    @Override
    public List<OrderDetailDTO> getDetailsByOrderId(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Lấy toàn bộ order detail theo order id
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(id);

        // Chuyển sang DTO, đồng thời tính và in tổng từng order detail
        List<OrderDetailDTO> result = orderDetails.stream().map(detail -> {
            BigDecimal itemTotal = caculatorTotalAmountOfOrderDetail(detail.getId());

            // In ra tổng tiền của từng order detail
            System.out.println("OrderDetail ID " + detail.getId() +
                    ": quantity = " + detail.getQuantity() +
                    ", price = " + detail.getPrice() +
                    ", total = " + itemTotal);

            return OrderDetailDTO.builder()
                    .id(detail.getId())
                    .quantity(detail.getQuantity())
                    .price(detail.getPrice())
                    .productId(detail.getProduct().getId())
                    .orderId(detail.getOrder().getId())
                    .totalPrice(itemTotal) // Tổng tiền của 1 order detail
                    .build();
        }).collect(Collectors.toList());

        // Tính và in tổng tiền của toàn bộ đơn hàng
        BigDecimal total = caculatorTotalAmountOfOrder(order.getId());
        System.out.println("Tổng tiền của đơn hàng là: " + total);

        return result;
    }


    @Override
    public OrderDetailDTO getOrderDetailById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("OrderDetail not found"));

        BigDecimal total = caculatorTotalAmountOfOrderDetail(id);

        return OrderDetailDTO.builder()
                .id(orderDetail.getId())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .totalPrice(total)
                .orderId(orderDetail.getOrder().getId())
                .productId(orderDetail.getProduct().getId())
                .build();
    }
}
