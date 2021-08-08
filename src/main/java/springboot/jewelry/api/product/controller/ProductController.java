package springboot.jewelry.api.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.PagedResult;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductProjection;
import springboot.jewelry.api.product.service.itf.ProductService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> findProducts(
            @PageableDefault(size = 9, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        PagedResult<ProductProjection> products = productService.findProducts(pageable);

        if (products.getElements().isEmpty()) {
            return ResponseHandler.getResponse("Không có dữ liệu!", HttpStatus.OK);
        }
        return ResponseHandler.getResponse(products, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<Object> findListProduct(@RequestParam(name = "page", required = false,
                                                                defaultValue = "0") int pageIndex,
                                                  @RequestParam(name = "sort-by", required = false,
                                                                defaultValue = "id") String sortBy) {
        List<ProductProjection> products = productService.findListProduct(pageIndex, sortBy);
        if(products.isEmpty()){
            return ResponseHandler.getResponse("Không có dữ liệu!", HttpStatus.OK);
        }
        return ResponseHandler.getResponse(products, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Object> findProductById(@RequestParam Long id){

        Optional<Product>  product = productService.findById(id);

        if(!product.isPresent()){
            return ResponseHandler.getResponse("Không tìm thấy sản phầm có ID: " + id, HttpStatus.OK);
        }

        return ResponseHandler.getResponse(product, HttpStatus.OK);
    }

}
