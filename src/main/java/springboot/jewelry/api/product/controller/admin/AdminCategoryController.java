package springboot.jewelry.api.product.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.dto.CategoryCreateDto;
import springboot.jewelry.api.product.dto.CategoryUpdateDto;
import springboot.jewelry.api.product.model.Category;
import springboot.jewelry.api.product.projection.CategoryProjection;
import springboot.jewelry.api.product.service.itf.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {

        List<CategoryProjection> categoryProjections = categoryService.findAllBy(CategoryProjection.class);
        if (categoryProjections.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(categoryProjections, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProductType(@Valid @RequestBody CategoryCreateDto dto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        Category newProductType = categoryService.save(dto);

        return ResponseHandler.getResponse(newProductType, HttpStatus.OK);
    }

    @PutMapping("/{product-type-id}")
    public ResponseEntity<Object> updateProductType(@PathVariable("product-type-id") Long id,
                                                @Valid @RequestBody CategoryUpdateDto dto,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Category productTypeUpdate = categoryService.updateProductTypeInfo(dto, id);

        return ResponseHandler.getResponse(productTypeUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{product-type-id}")
    public ResponseEntity<Object> deleteProductType(@PathVariable("product-type-id") Long id) {

        Optional<Category> productType = categoryService.findById(id);
        if (!productType.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy ID: " + id, HttpStatus.OK);
        }
        categoryService.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
