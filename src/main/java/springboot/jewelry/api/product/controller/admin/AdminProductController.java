package springboot.jewelry.api.product.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductCreatedProjection;
import springboot.jewelry.api.product.projection.ProductProjection;
import springboot.jewelry.api.product.service.itf.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(@RequestParam(name = "page", required = false,
            defaultValue = "0") int pageIndex,
                                          @RequestParam(name = "sort-by", required = false,
                                                  defaultValue = "id") String sortBy) {

        return ResponseHandler.getResponse("123", HttpStatus.OK);
    }

    @GetMapping("/by-id")
    public ResponseEntity<Object> findProductById(@RequestParam Long id) {

        Optional<Product> product = productService.findById(id);

        if (!product.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy sản phầm có ID: " + id, HttpStatus.OK);
        }

        return ResponseHandler.getResponse(product, HttpStatus.OK);
    }

    @PostMapping(value = "",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> addProduct(@RequestPart("dto") @Valid ProductCreateDto dto,
                                             @RequestPart(value = "images[]", required = false) List<MultipartFile> images,
                                             @RequestPart(value = "avatar", required = false) MultipartFile avatar,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        if(images != null) dto.setImages(images);
        if(avatar != null) dto.setAvatar(avatar);

        ProductCreatedProjection newProduct = productService.save(dto);

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
