package hana.corp.msa_main.controller;

import hana.corp.msa_main.domain.Order;
import hana.corp.msa_main.service.OrderService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderSsrController {
    private final OrderService orderService;

    public OrderSsrController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        
        // 서버의 hostname 가져오기
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            model.addAttribute("hostname", hostname);
        } catch (UnknownHostException e) {
            model.addAttribute("hostname", "Unknown");
        }
        
        return "orders/list";
    }
} 