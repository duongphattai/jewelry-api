package springboot.jewelry.api.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.PagedResult;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.dto.ProductDetailsDto;
import springboot.jewelry.api.product.dto.ShortProductDto;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.service.itf.ProductService;

import java.util.List;
import java.util.Optional;

import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.service.itf.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> findProducts(
            @PageableDefault(size = 9, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        PagedResult<ShortProductDto> shortProducts = productService.findShortProducts(pageable);
        // chưa kiểm tra khi không có dữ liệu
        return ResponseHandler.getResponse(shortProducts, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Object> findProductDetails(@PathVariable(value = "slug") String slug) {
        ProductDetailsDto productDetails = productService.findProductDetails(slug);
        if(productDetails == null) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.getResponse(productDetails, HttpStatus.OK);
    }
}
