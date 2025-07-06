package com.javaweb.controller.client;

import com.javaweb.domain.*;
import com.javaweb.service.OrderService;
import com.javaweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller(value = "productControllerOfClient")
public class ProductController {
    private final ProductService productService;
    public final OrderService orderService;

    public ProductController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/product/{id}")
    public String getProductDetailById(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("productDetail", this.productService.findProductById(id));
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable("id") long productId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        this.productService.handleProductAddToCart(email, productId, session, 1);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(HttpServletRequest request, Model model) {
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);
        Cart cart = this.productService.fetchByUser(currentUser);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetails) {
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        }
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/detail";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartDetail(@PathVariable("id") long cartDetailId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        this.productService.handleRemoveCartDetail(cartDetailId, session);

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.productService.fetchByUser(currentUser);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

        return "client/cart/checkout";
    }

    @PostMapping("/confirm-checkout")
    public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.productService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
            HttpServletRequest request,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);
        this.productService.handlePlaceOrder(currentUser, session, receiverName, receiverAddress, receiverPhone);

        return "redirect:/thanks";
    }

    @GetMapping("/thanks")
    public String getThankYouPage() {
        return "client/cart/thanks";
    }

    @GetMapping("/order-history")
    public String getOrderHistoryPage(Model model, HttpServletRequest request) {
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);
        List<Order> orders = this.orderService.fechByUser(currentUser);
        model.addAttribute("orders", orders);
        return "client/cart/order-history";
    }

    @PostMapping("/add-product-from-view-detail")
    public String handleAddProductFromViewDetail(@RequestParam("id") long id, @RequestParam("quantity") long quantity, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        this.productService.handleProductAddToCart(email, id, session, quantity);
        return "redirect:/product/" + id;
    }

    @GetMapping("/products")
    public String getProductPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            } else {

            }
        } catch (Exception e) {
        }
        Pageable pageable = PageRequest.of(page - 1, 3);
        Page<Product> products = this.productService.findAllProduct(pageable);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        return "client/product/home";
    }
}
