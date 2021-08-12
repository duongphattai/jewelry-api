package springboot.jewelry.api.product.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.service.itf.ProductService;



import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(products, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductCreateDto dto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productService.save(dto);

        return ResponseHandler.getResponse(newProduct, HttpStatus.OK);
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("product-id") Long id,
                                                 @Valid @RequestBody ProductCreateDto dto,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Product productUpdate = productService.updateProductInfo(dto, id);

        return ResponseHandler.getResponse(productUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("product-id") Long id) {

        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy ID: " + id, HttpStatus.OK);
        }
        productService.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }

}
