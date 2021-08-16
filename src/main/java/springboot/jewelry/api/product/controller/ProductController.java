package springboot.jewelry.api.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductProjection;
import springboot.jewelry.api.product.service.itf.ProductService;

import java.util.List;
import java.util.Optional;


@RestController()
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/find-all")
    public ResponseEntity<Object> findAll(@RequestParam(name = "page", required = false,
                                                        defaultValue = "0") int pageIndex,
                                          @RequestParam(name = "sort-by", required = false,
                                                        defaultValue = "id") String sortBy) {

        List<Product> products = productService.findAllProductWithPage(pageIndex, sortBy);
        if (products.isEmpty()) {
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
