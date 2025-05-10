package org.example.projectbcms.services;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.dtos.OrderDTO;
import org.example.projectbcms.models.Order;
import org.example.projectbcms.models.User;
import org.example.projectbcms.repositories.OrderRepository;
import org.example.projectbcms.repositories.UserRepository;
import org.example.projectbcms.services.serviceInterfaces.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//orderService
@Service
@RequiredArgsConstructor
public class OrderImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    // tao don hang moi
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order newOrder = new Order();
        newOrder.setOrderDate(LocalDate.now());
        // khách hàng nhập ngày yêu cầu gửi, nếu không có thì mặc định hàng sẽ nhận được sau 3 ngày
        newOrder.setRequiredDate(orderDTO.getRequiredDate() != null ? orderDTO.getRequiredDate() : LocalDate.now().plusDays(3));
        newOrder.setOrderStatus("Pending"); //gán trạng thái mặc định là đang chờ xử lý
        newOrder.setComments(orderDTO.getComments());
        newOrder.setUser(user);

        orderRepository.save(newOrder);
        orderDTO.setId(newOrder.getId());

        return orderDTO;
    }

    // xem chi tiet don hang
    @Override
    public OrderDTO orderDetail(OrderDTO order) {
        return null;
    }

    // lay danh sach don hang
    @Override
    public List<OrderDTO> getAllOrder() {
        // tạo ra một danh sách  - rỗng
        List<OrderDTO> orders = new ArrayList<>();
        // duyệt qua tất cả các đơn hàng trong cơ sở dữ liệu
        // forEach() là một phương thức duyệt
        orderRepository.findAll().forEach(
                // biểu thức lambda -> đại diện cho hàm vô danh (hàm không tên).
                // order là tham số, đại diện cho mỗi phần tử trong danh sách trả về từ findAll()
                order -> {
                    OrderDTO orderDTO = new OrderDTO();
                    // chuyển đổi thông tin thành đối tượng OrderDTO
                    orderDTO.setId(order.getId());
                    orderDTO.setUserId(order.getUser().getId());
                    orderDTO.setOrderDate(order.getOrderDate());
                    orderDTO.setRequiredDate(order.getRequiredDate());
                    orderDTO.setShippedDate(order.getShippedDate());
                    orderDTO.setOrderStatus(order.getOrderStatus());
                    orderDTO.setComments(order.getComments());

                    // add vào danh sách
                    orders.add(orderDTO);
                }
        );
        return orders;
    }

    // cap nhat don hang
    @Override
    public OrderDTO updateOrderStatus(OrderDTO order, Long id) {
        // Kiểm tra nếu không tìm thấy đơn hàng với ID
        Order orderPresent = orderRepository.findById(id)
                .orElseThrow();

        orderPresent.setRequiredDate(order.getRequiredDate());
        orderPresent.setComments(order.getComments());

        orderRepository.save(orderPresent);
        return OrderDTO.builder()
                .id(id)
                .requiredDate(order.getRequiredDate())
                .comments(orderPresent.getComments())
                .orderStatus(orderPresent.getOrderStatus())
                .build();
    }

    //huy don hang
    @Override
    public OrderDTO cancelOrder(OrderDTO order) {
        return null;
    }

    private static final Logger logger = LoggerFactory.getLogger(OrderImpl.class);

    // xu ly thanh toan - lien ket ngan hang, luu thong tin da thanh toan hay chua
    // tinh nang nay dang phat trien
    @Override
    public OrderDTO paymentHanding(OrderDTO order) {

         return null;
    }


    // in hoa don, gui email xac nhan
    // tinh nang nay dang phat trien
    @Override
    public OrderDTO confirmOrder(OrderDTO order) {
        logger.info("Tính năng này đang phát triển.");
        return null;
    }

    // tim kiem thong tin, loc thong tin don hang thong qua ma don hang
    @Override
    public OrderDTO getOrderById(Long id) {
        return null;
    }

    // thong ke don hang - tinh tong doanh thu
    @Override
    public OrderDTO orderAnalytics(OrderDTO order) {
        return null;
    }
}
