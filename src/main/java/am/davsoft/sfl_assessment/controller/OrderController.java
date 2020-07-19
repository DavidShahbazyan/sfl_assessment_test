package am.davsoft.sfl_assessment.controller;

import am.davsoft.sfl_assessment.core.controller.BaseController;
import am.davsoft.sfl_assessment.entity.CafeOrder;
import am.davsoft.sfl_assessment.entity.ProductInOrder;
import am.davsoft.sfl_assessment.exception.OrderNotFoundException;
import am.davsoft.sfl_assessment.exception.ProductNotFoundException;
import am.davsoft.sfl_assessment.repository.OrderRepository;
import am.davsoft.sfl_assessment.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.*;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@RestController
@RequestMapping("/v1/orders")
public class OrderController extends BaseController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public ResponseEntity loadAll() {
        return ok(this.orderRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody CafeOrder cafeOrder, HttpServletRequest request) {
        CafeOrder saved = this.orderRepository.save(cafeOrder);
        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/orders/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        return ok(this.orderRepository.findById(id).orElseThrow(OrderNotFoundException::new));
    }

    @PutMapping("/{id}/addProduct")
    public ResponseEntity addProduct(@PathVariable("id") Integer id, @RequestParam Integer productId, @RequestParam Integer amount) {
        CafeOrder cafeOrder = this.orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        if (cafeOrder.isOpen()) {
            ProductInOrder productInOrder = new ProductInOrder();
            productInOrder.setProduct(this.productRepository.findById(productId).orElseThrow(ProductNotFoundException::new));
            productInOrder.setAmount(amount);
            cafeOrder.getProductsList().add(productInOrder);
            cafeOrder.calculateTotalAmount();
            this.orderRepository.save(cafeOrder);
        }
        return noContent().build();
    }

    @PutMapping("/{id}/close")
    public ResponseEntity close(@PathVariable("id") Integer id) {
        CafeOrder existed = this.orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        if (existed.isOpen()) {
            existed.setOpen(false);
            this.orderRepository.save(existed);
        }
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        if (isDeleteAllowed) {
            CafeOrder existed = this.orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
            this.orderRepository.delete(existed);
            return noContent().build();
        } else {
            return badRequest().build();
        }
    }
}
