package springboot.jewelry.api.product.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.dto.ProductTypeCreateDto;
import springboot.jewelry.api.product.dto.ProductTypeUpdateDto;
import springboot.jewelry.api.product.model.ProductType;
import springboot.jewelry.api.product.service.itf.ProductTypeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product-type")
public class AdminProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        List<ProductType> productTypes = productTypeService.findAll();
        if (productTypes.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(productTypes, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProductType(@Valid @RequestBody ProductTypeCreateDto dto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        ProductType newProductType = productTypeService.save(dto);

        return ResponseHandler.getResponse(newProductType, HttpStatus.OK);
    }

    @PutMapping("/{product-type-id}")
    public ResponseEntity<Object> updateProductType(@PathVariable("product-type-id") Long id,
                                                @Valid @RequestBody ProductTypeUpdateDto dto,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        ProductType productTypeUpdate = productTypeService.updateProductTypeInfo(dto, id);

        return ResponseHandler.getResponse(productTypeUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{product-type-id}")
    public ResponseEntity<Object> deleteProductType(@PathVariable("product-type-id") Long id) {

        Optional<ProductType> productType = productTypeService.findById(id);
        if (!productType.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy ID: " + id, HttpStatus.OK);
        }
        productTypeService.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
