package hana.corp.msa_main.service;

import hana.corp.msa_main.domain.Order;
import hana.corp.msa_main.domain.Item;
import hana.corp.msa_main.domain.OrderRequest;
import hana.corp.msa_main.domain.User;
import hana.corp.msa_main.repository.OrderRepository;
import hana.corp.msa_main.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    // 2.1 Create Order
    public Order createOrder(OrderRequest orderRequest) {
        Long userId = orderRequest.getUserId();
        Long productId = orderRequest.getProductId();
        Integer quantity = orderRequest.getQuantity();

        // Check if the user exists
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            return null; // Or throw an exception if preferred
        }

        // Create a new order
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setUser(user.get());

        // Create a new item for the order
        Item item = new Item();
        item.setProduct(productRepository.getReferenceById(productId));
        item.setQuantity(quantity);
        order.setItem(item);  // Assuming Order has setItem method

        return orderRepository.save(order);
    }

    // 2.2 Get Order by ID
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // 2.3 Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
