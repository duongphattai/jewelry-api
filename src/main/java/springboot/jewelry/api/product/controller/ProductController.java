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
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.service.itf.ProductService;

import java.util.List;


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

    @GetMapping("/filter")
    public ResponseEntity<Object> findProductsByFilter(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String productType,
                                                       @RequestParam(required = false) Double goldType,
                                                       @RequestParam(required = false) Double minPrice,
                                                       @RequestParam(required = false) Double maxPrice) {
        List<ProductFilterDto> products = productService.findProductsByFilter(name, productType, goldType, minPrice, maxPrice);
        if (products.isEmpty()) {
            return ResponseHandler.getResponse("Không tìm thấy sản phẩm!", HttpStatus.OK);
        }
        return ResponseHandler.getResponse(products, HttpStatus.OK);
    }

}
