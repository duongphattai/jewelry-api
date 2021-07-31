package springboot.jewelry.api.product.controller;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.product.service.itf.ProductService;

import java.util.List;


@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/paging")
    public ResponseEntity<Object> findAll(@RequestParam(name = "page", required = false,
                                                        defaultValue = "0") int pageIndex ,
                                          @RequestParam(name = "sort-by", required = false,
                                                        defaultValue = "id")  String sortBy){

        List<Product> products = productService.findAllProductWithPage(pageIndex, sortBy);
        if(products.isEmpty()){
            return ResponseHandler.getResponse("Không có dữ liệu!", HttpStatus.OK);
        }
        return  ResponseHandler.getResponse(products, HttpStatus.OK);
    }

}
