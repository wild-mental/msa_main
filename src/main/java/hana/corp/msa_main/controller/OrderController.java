package hana.corp.msa_main.controller;

import hana.corp.msa_main.domain.Order;
import hana.corp.msa_main.domain.OrderRequest;
import hana.corp.msa_main.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 2.1 Create Order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order createdOrder = orderService.createOrder(orderRequest);
        if (createdOrder == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdOrder);
    }

    // 2.2 Get Order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // 2.3 Get All Orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/update-check")
    public ResponseEntity<String> checkUpdate() {
        String response = "<h1>MSA-MAIN : GitOps Implemented!</h1>";
        return ResponseEntity.ok()
            .header("Content-Type", "text/html")
            .body(response);
    }

}
