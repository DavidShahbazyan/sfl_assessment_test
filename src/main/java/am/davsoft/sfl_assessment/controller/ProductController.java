package am.davsoft.sfl_assessment.controller;

import am.davsoft.sfl_assessment.entity.Product;
import am.davsoft.sfl_assessment.exception.ProductNotFoundException;
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
@RequestMapping("/v1/products")
public class ProductController {
    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity loadAll() {
        return ok(this.repository.findAll());
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody Product product, HttpServletRequest request) {
        Product saved = this.repository.save(new Product());
        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/products/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        return ok(this.repository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Product product) {
        Product existed = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        existed.setName(product.getName());
        existed.setDescription(product.getDescription());
        existed.setPrice(product.getPrice());
        this.repository.save(existed);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Product existed = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(existed);
        return noContent().build();
    }
}
