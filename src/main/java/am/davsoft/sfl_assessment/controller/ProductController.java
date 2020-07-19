package am.davsoft.sfl_assessment.controller;

import am.davsoft.sfl_assessment.core.controller.BaseController;
import am.davsoft.sfl_assessment.entity.Product;
import am.davsoft.sfl_assessment.entity.User;
import am.davsoft.sfl_assessment.exception.ProductNotFoundException;
import am.davsoft.sfl_assessment.helper.PermissionEnum;
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
public class ProductController extends BaseController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public ResponseEntity loadAll() {
        return ok(this.productRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody Product product, HttpServletRequest request) {
        checkUserPermission((User) request.getUserPrincipal(), PermissionEnum.CREATE_PRODUCT);
        Product saved = this.productRepository.save(product);
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
        return ok(this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Product product) {
        Product existed = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        existed.setName(product.getName());
        existed.setDescription(product.getDescription());
        existed.setPrice(product.getPrice());
        this.productRepository.save(existed);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        if (isDeleteAllowed) {
            Product existed = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
            this.productRepository.delete(existed);
            return noContent().build();
        } else {
            return badRequest().build();
        }
    }
}
