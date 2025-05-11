package hana.corp.msa_main.controller;

import hana.corp.msa_main.domain.Order;
import hana.corp.msa_main.domain.OrderRequest;
import hana.corp.msa_main.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    @RequestMapping("/hash")
    public String getHash(String input) throws NoSuchAlgorithmException {
        System.out.println("input : " + input);
        String output = input;
        for(int i = 0; i < 100_000; i++){
            output = getMD5(output);
            if (i % 1_000 == 0) {
                System.out.print(".");
            }
        }
        System.out.println();
        return output;
    }

    private String getMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String resultHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return resultHash;
    }

}
